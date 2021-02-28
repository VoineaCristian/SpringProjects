package com.project.MyApp.Rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.project.MyApp.Service.User.UserService;
import com.project.MyApp.Utils.NewPassword;
import com.project.MyApp.Service.Email.EmailSenderService;
import com.project.MyApp.Service.Token.EmailTokenService;
import com.project.MyApp.entity.EmailToken;
import com.project.MyApp.entity.User;
import com.project.MyApp.entity.UserImages;

@RestController
public class UserRestController {

	@Autowired
	private UserService userService;

	@Autowired
	private EmailTokenService emailTokenService;

	@Autowired
	private EmailSenderService emailSenderService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@RequestMapping(value = "/login", method = { RequestMethod.GET })
	public ModelAndView displayLogin(ModelAndView modelAndView, User user) {

		modelAndView.addObject("user", new User());
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@RequestMapping(value = "/login", method = { RequestMethod.POST })
	public ModelAndView login(ModelAndView modelAndView, User user) {

		modelAndView.setViewName("login");
		return modelAndView;
	}

	@RequestMapping(value = "/forgotPassword", method = RequestMethod.GET)
	public ModelAndView displayForgotPassword(ModelAndView modelAndView, User user) {
		modelAndView.addObject("user", new User());
		modelAndView.setViewName("forgot-password");
		return modelAndView;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView displayRegister(ModelAndView modelAndView, User user) {
		modelAndView.addObject("user", new User());
		modelAndView.setViewName("register");
		return modelAndView;
	}

	@RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
	public ModelAndView forgotPasswordSendEmail(ModelAndView modelAndView, User theUser) {

		// extract the users based on the email and username
		User existingUserEmail = userService.getUserByEmail(theUser.getEmail());
		User existingUsername = userService.getUserByUsername(theUser.getUsername());

		if (existingUserEmail == null || existingUsername == null
				|| !existingUsername.getUsername().equals(existingUserEmail.getUsername())) {

			modelAndView.addObject("message", "Username and Email doest't match");
			modelAndView.setViewName("forgot-password");

		} else if (existingUserEmail.isEnabled() == false) {
			modelAndView.addObject("message", "Your accout has not been activated");
			modelAndView.setViewName("forgot-password");
		} else {

			EmailToken theToken = new EmailToken(existingUserEmail);
			emailTokenService.saveToken(theToken);

			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(theUser.getEmail());
			mailMessage.setSubject("Complete Registration!");
			mailMessage.setFrom("vasilicavasiliu@gmail.com");
			mailMessage.setText("To reset your password, please click here : "
					+ "http://localhost:8080/resetPassword?token=" + theToken.getConfirmationToken());

			emailSenderService.sendEmail(mailMessage);

			modelAndView.addObject("emailId", theUser.getEmail());

			modelAndView.setViewName("succesfulRegistration");
		}

		return modelAndView;
	}

	@RequestMapping(value = "/resetPassword", method = { RequestMethod.GET })
	public ModelAndView displayResetPassword(ModelAndView modelAndView, NewPassword password,
			@RequestParam("token") String CF) {

		EmailToken token = emailTokenService.getByConfirmationToken(CF);
		if (token == null) {
			modelAndView.addObject("message", "The link is invalid");
			modelAndView.setViewName("error");
		} else if (token.isUsed() == true) {

			modelAndView.addObject("message", "The token has expired");
			modelAndView.setViewName("error");

		} else {

			token.setUsed(true);
			User user = userService.getUserByEmail(token.getUser().getEmail());
			modelAndView.addObject("password", new NewPassword());
			modelAndView.addObject("actualUsername", user.getUsername());
			modelAndView.setViewName("reset-password");

		}

		return modelAndView;
	}

	@RequestMapping(value = "/resetPassword", method = { RequestMethod.POST })
	public ModelAndView resetPassword(ModelAndView modelAndView, NewPassword password) {

		modelAndView.addObject(password);
		if (password.verifyMatch() == false) {
			modelAndView.addObject("password", password);
			modelAndView.addObject("actualUsername", password.getUsername());
			modelAndView.addObject("message", "Passwords doesn't match");
			modelAndView.setViewName("reset-password");
		} else {
			User user = userService.getUserByUsername(password.getUsername());
			user.setPassword(passwordEncoder.encode(password.getPassword()));
			userService.saveUser(user);
			modelAndView.addObject("message", "The password has been changed");
			modelAndView.setViewName("succes");
		}

		return modelAndView;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView registerUser(ModelAndView modelAndView, User theUser) {

		User existingUserEmail = userService.getUserByEmail(theUser.getEmail());
		User existingUsername = userService.getUserByUsername(theUser.getUsername());
		if (existingUserEmail != null) {
			modelAndView.addObject("message", "This email already exists!");
		} else if (existingUsername != null) {
			modelAndView.addObject("message", "This username already exists!");
		} else {
			// in saveOrUpdate 0 means add
			theUser.setId(0);

			System.out.println(theUser.getPassword());
			theUser.setPassword(passwordEncoder.encode(theUser.getPassword()));
			System.out.println(theUser.getPassword());

			// add the user to database
			userService.saveUser(theUser);

			EmailToken theToken = new EmailToken(theUser);

			emailTokenService.saveToken(theToken);

			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(theUser.getEmail());
			mailMessage.setSubject("Complete Registration!");
			mailMessage.setFrom("vasilicavasiliu@gmail.com");
			mailMessage.setText("To confirm your account, please click here : "
					+ "http://localhost:8080/confirm-account?token=" + theToken.getConfirmationToken());

			emailSenderService.sendEmail(mailMessage);

			modelAndView.addObject("emailId", theUser.getEmail());

			modelAndView.setViewName("succesfulRegistration");
		}

		return modelAndView;
	}

	@RequestMapping(value = "/confirm-account", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token") String CF) {
		EmailToken token = emailTokenService.getByConfirmationToken(CF);

		if (token != null) {
			User user = userService.getUserByEmail(token.getUser().getEmail());

			if (user.isEnabled() == false) {

				user.setAuthority("USER");
				user.setEnabled(true);
				user.setImages(new UserImages());
				userService.saveUser(user);
				modelAndView.setViewName("congratualtion");
			} else {
				modelAndView.addObject("message", "The account has already been activated");
				modelAndView.setViewName("error");
			}
		} else {
			modelAndView.addObject("message", "The link is invalid or broken!");
			modelAndView.setViewName("error");
		}

		return modelAndView;
	}
	
	

}
