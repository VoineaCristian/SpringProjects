package com.project.MyApp.DAO.EmailToken;

import java.util.List;

import com.project.MyApp.entity.EmailToken;

public interface EmailTokenDAO {

	public List<EmailToken> getEmailTokens();

	public void saveEmailToken(EmailToken theEmailToken);
	
	public EmailToken getEmailToken(int theId);

	public void deleteEmailToken(int theId);
	
	public EmailToken getByConfirmationToken(String CF);
	
}
