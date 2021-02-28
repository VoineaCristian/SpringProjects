package com.project.MyApp.Service.Token;

import java.util.List;

import com.project.MyApp.entity.EmailToken;

public interface EmailTokenService {

	public List<EmailToken> getTokens();

	public void saveToken(EmailToken theToken);

	public EmailToken getToken(int theId);

	public void deleteToken(int theId);
	
	public EmailToken getByConfirmationToken(String CF);

}
