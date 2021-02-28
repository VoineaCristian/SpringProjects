import java.util.*;
public class VMS {
	
	Vector<Campaign> Campaigns;
	Vector<User> AllUsers;
	TIME Actual;
	int ActiveC;
	static VMS instance = null;
	
	public static  VMS getInstance()
	{
		if(instance == null)
			instance = new VMS();
		return instance;
	}
	Vector<Campaign> GetCampaigns()
	{
		return Campaigns;
	}
	
	Campaign GetCampaign(int ID)
	{
		for(int i = 0; i < Campaigns.size(); i++)
		{
			if(Campaigns.get(i).Id == ID)
			{
				return Campaigns.get(i);
			}
		}
		return null;
	}
	
	User GetUser(int I)
	{
		for(int i = 0; i < AllUsers.size();i++)
		{
			if(AllUsers.get(i).getId() == I)
			{
				return AllUsers.get(i);
			}
		}
		return null;
	}
	User getUserEmail(String Email)
	{
		for(int i = 0; i < AllUsers.size(); i++)
		{
			if(AllUsers.get(i).email.equals(Email))
			{
			
				return AllUsers.get(i);
			}
		}
		return null;
	}
	Vector<Campaign> AddCampaign(Campaign C)
	{
		if(Campaigns == null)
			Campaigns = new Vector<Campaign>();
		Campaigns.add(C);
		this.ActiveC++;
		return Campaigns;
	}
	
	 void UpdateCampaign(int ID, Campaign Camp)
	 {
		 for(int i = 0; i < Campaigns.size(); i++)
		 {
			 if(Campaigns.get(i).Id == ID)
			 {
				 Campaigns.get(i).modify(Camp);
				 Campaigns.get(i).notifyAllObservers(new Notification(Actual, Campaigns.get(i).Id,null,NotificationType.EDIT));;
			 }
		 }
		 
	 }
	 
	 void CancelCampaign(int id)
	 {
		 for(int i = 0; i < Campaigns.size();i++)
		 {
			 if(Campaigns.get(i).Id == id)
			 {
				
				 Campaigns.get(i).Status = CampaignStatusType.CANCELLED;
				 if(Campaigns.get(i).getObserver() != null)
				 {
					 Campaigns.get(i).notifyAllObservers(new Notification(Actual, Campaigns.get(i).Id,null,NotificationType.CANCEL));
					 
				 }
			 }
		 }
		 
	 }
	 
	 Vector<User> GetUsers()
	 {
		 return AllUsers;
	 }
	 
	 void AddUser(User user)
	 {
		 if(AllUsers == null)
			 AllUsers = new Vector<User>();
		 AllUsers.add(user);
	 }
	 
	 
	 
	 
	
	

}

