package com.project.MyApp.Service.Token;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.project.MyApp.entity.EmailToken;
import com.project.MyApp.DAO.EmailToken.EmailTokenDAO;

@Service
public class EmailTokenServiceImpl implements EmailTokenService {

	// need to inject emailTokenDAO
	@Autowired
	private EmailTokenDAO emailTokenDAO;

	@Override
	@Transactional
	public List<EmailToken> getTokens() {

		return emailTokenDAO.getEmailTokens();

	}

	@Override
	@Transactional
	public void saveToken(EmailToken theToken) {

		emailTokenDAO.saveEmailToken(theToken);

	}

	@Override
	@Transactional
	public EmailToken getToken(int theId) {

		return emailTokenDAO.getEmailToken(theId);
	}

	@Override
	@Transactional
	public void deleteToken(int theId) {

		emailTokenDAO.deleteEmailToken(theId);

	}
	
	@Override
	@Transactional
	public EmailToken getByConfirmationToken(String CF) {
		
		return emailTokenDAO.getByConfirmationToken(CF);
		
	}

}
