package com.project.MyApp.Rest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.project.MyApp.Enums.FriendshipStatusEnum;
import com.project.MyApp.Service.User.UserService;
import com.project.MyApp.entity.Friendship;
import com.project.MyApp.entity.User;
import com.project.MyApp.entity.UserImages;

@RestController
public class NavigateRestController {

	@Autowired
	UserService userService;

	public static String uploadDirectory = System.getProperty("user.dir") + "/target/classes/static";

	@GetMapping("/home")
	public String UploadPage(Model model) {
		return "individual-profile";
	}

	@RequestMapping(value = "/{userName}/friends", method = RequestMethod.GET)
	public ModelAndView friendsList(@PathVariable String userName, ModelAndView model, Authentication auth) {

		userName = auth.getName();
		User user = userService.getUserByUsername(userName);
		System.out.println();
		model.addObject("profilePhotoPath", user.getImages().getProfilePhoto());
		model.addObject("user", user);
		System.out.println(userService.getFriends(user, FriendshipStatusEnum.Friends));
		model.addObject("friends", userService.getFriends(user, FriendshipStatusEnum.Friends));
		model.setViewName("friends-list");
		return model;
	}

	@RequestMapping(value = "/{userName}/friends", method = RequestMethod.POST)
	public ModelAndView operationFriends(@PathVariable String userName, @RequestParam("Req") String requester,
			ModelAndView model, Authentication auth) {

		userName = auth.getName();
		User user = userService.getUserByUsername(userName);
		User req = userService.getUserByUsername(requester);
		model.addObject("userName", user.getUsername());
		userService.deleteFriend(req, user);
		model.setViewName("friends-request-list");
		return new ModelAndView("redirect:/" + userName + "/friends");
	}

	@RequestMapping(value = "/{userName}/friends-request", method = RequestMethod.GET)
	public ModelAndView friendsRequestList(@PathVariable String userName, ModelAndView model, Authentication auth) {

		userName = auth.getName();
		User user = userService.getUserByUsername(userName);
		System.out.println();
		model.addObject("profilePhotoPath", user.getImages().getProfilePhoto());
		model.addObject("user", user);
		model.addObject("requests", userService.getFriends(user, FriendshipStatusEnum.NotAccepted));
		model.setViewName("friends-request-list");
		return model;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView explore(ModelAndView model, Authentication auth) {

		String username = auth.getName();
		User user = userService.getUserByUsername(username);
		System.out.println();
		model.addObject("profilePhotoPath", user.getImages().getProfilePhoto());
		model.addObject("user", user);
		model.addObject("photos", user.getImages().getPhotos());
		model.addObject("requests", userService.getFriends(user, FriendshipStatusEnum.NotAccepted));
		model.setViewName("explore");
		return model;
	}


	@RequestMapping(value = "/{userName}/friends-request", method = RequestMethod.POST)
	public ModelAndView acceptFriendRequest(@PathVariable String userName, @RequestParam("response") String response,
			@RequestParam("Req") String requester, ModelAndView model, Authentication auth) {

		userName = auth.getName();
		User user = userService.getUserByUsername(userName);
		User req = userService.getUserByUsername(requester);
		model.addObject("userName", user.getUsername());
		if (response.equals("accept")) {
			userService.acceptFriendRequest(user, req);
		} else if (response.equals("decline")) {
			userService.deleteFriendRequest(req, user);
		}
		model.setViewName("friends-request-list");
		return new ModelAndView("redirect:/" + userName + "/friends-request");
	}

	@PostMapping(value = "/home/change-profile-photo")
	public ModelAndView upload(ModelAndView model, @RequestParam("files") MultipartFile[] files) {
		StringBuilder fileNames = new StringBuilder();
		for (MultipartFile file : files) {
			Path fileNameAndPath = Paths.get(uploadDirectory, "1.jpg");
			fileNames.append(file.getOriginalFilename() + " ");
			try {
				Files.write(fileNameAndPath, file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println(uploadDirectory);
		model.setViewName("individual-profile.html");
		return model;
	}

	@GetMapping(value = "/{userName}")
	public ModelAndView homePages(@PathVariable String userName, ModelAndView model, Authentication auth) {

		User user = userService.getUserByUsername(userName);
		System.out.println(userName);
		String username = auth.getName();
		User userLogged = userService.getUserByUsername(username);
		Friendship request = userService.getRequest(userLogged, user);
		Friendship requestReverse = userService.getRequest(user, userLogged);
		model.addObject("friends", userService.getFriends(user, FriendshipStatusEnum.Friends));
		if (!username.equals(userName)) {

			if (requestReverse != null && request != null) {
				model.addObject("button_text", "Delete Friend");
				model.addObject("action", "delete");
			} else if (requestReverse != null) {
				model.addObject("button_text", "Accept Request");
				model.addObject("action", "accept");
			} else if (request == null) {
				model.addObject("button_text", "Send Friend Request");
				model.addObject("action", "send");
			} else if (request.getStatus() == FriendshipStatusEnum.NotAccepted) {
				model.addObject("button_text", "Delete Friend Request");
				model.addObject("action", "delete request");
			}
			model.setViewName("user-page");

		} else {

			model.setViewName("individual-profile");
		}

		model.addObject("photos", user.getImages().getPhotos());
		model.addObject("user", user);
		model.addObject("profilePhotoPath", user.getImages().getProfilePhoto());

		return model;
	}

	@PostMapping(value = "/{userName}/friend-request")
	public ModelAndView friendRequest(@PathVariable String userName, @RequestParam("user") String addressee,
			@RequestParam("action") String action, ModelAndView model, Authentication auth) {

		userName = addressee;
		User userTo = userService.getUserByUsername(userName);
		String username = auth.getName();
		User userFrom = userService.getUserByUsername(username);

		model.addObject("user", userTo);
		if (action.equals("send")) {
			userService.sendFriendRequest(userFrom, userTo);
		} else if (action.equals("delete request")) {
			userService.deleteFriendRequest(userFrom, userTo);
		} else if (action.equals("delete")) {
			userService.deleteFriend(userFrom, userTo);
		} else if (action.equals("accept")) {
			userService.acceptFriendRequest(userFrom, userTo);
		}
		return new ModelAndView("redirect:/" + userName);
	}

	@RequestMapping(value = "/{userName}/profilePhoto", method = RequestMethod.POST, consumes = {
			"multipart/form-data" })
	public ModelAndView changeProfilePhoto(@PathVariable String userName, ModelAndView model,
			@RequestParam("file") MultipartFile file, Authentication auth) {

		userName = auth.getName();
		StringBuilder fileNames = new StringBuilder();

		Path fileNameAndPath = Paths.get(uploadDirectory, "/images/" + auth.getName() + "-ProfilePhoto");
		fileNames.append(file.getOriginalFilename() + " ");
		try {
			Files.write(fileNameAndPath, file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}

		userService.setProfileImage(auth.getName(), "/images/" + auth.getName() + "-ProfilePhoto");
		model.setViewName("individual-profile");
		return new ModelAndView("redirect:/" + userName);
	}

	@RequestMapping(value = "/{userName}/add-photos", method = RequestMethod.POST, consumes = { "multipart/form-data" })
	public ModelAndView addPhotos(@PathVariable String userName, ModelAndView model,
			@RequestParam("files") MultipartFile[] files, Authentication auth) {

		userName = auth.getName();
		StringBuilder fileNames = new StringBuilder();
		ArrayList<String> paths = new ArrayList<String>();
		User user = userService.getUserByUsername(auth.getName());
		int count = user.getImages().getImageCount();

		for (MultipartFile file : files) {

			String path = new String("/images/" + auth.getName() + String.valueOf(count));
			count++;
			Path fileNameAndPath = Paths.get(uploadDirectory, path);
			System.out.println(fileNameAndPath);
			paths.add(path);
			fileNames.append(file.getOriginalFilename() + " ");
			try {
				Files.write(fileNameAndPath, file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		userService.addPhotos(auth.getName(), paths);
		model.setViewName("individual-profile");
		return new ModelAndView("redirect:/" + userName);
	}

	@RequestMapping(value = "/{userName}/delete")
	public ModelAndView deletePhoto(@PathVariable String userName, @RequestParam("Photo") String path,
			ModelAndView model, Authentication auth) {

		userName = auth.getName();
		userService.deletePhoto(path);
		System.out.println(path);
		return new ModelAndView("redirect:/" + userName);
	}

}
