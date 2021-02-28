import  java.util.*;

public class User {
	
	private int id;
	private String name;
	private String password;
	public String email;
	private UserType Type;
	private UserVoucherMap Vouchere;
	private Vector<Notification> Notifications;
	public int logat = 0;
	
	
	User(int I, String N,  String P,String mail, String T)
	{
		id = I;
		email = mail;
		name = N;
		password = P;
		
		
		this.Type = UserType.GUEST;
		if(T.equals("ADMIN"))
		{
			this.Type = UserType.ADMIN;
		}
		
		
	}
	void update(Notification N)
	{
		
	}
	
	void Notify(Notification N)
	{
		if(Notifications == null)
			Notifications =new Vector<Notification>();
		this.Notifications.add(N);
		this.updateVouchers(N);
	}
	 public String toString()
	 {
		return "[" + this.id + ";" + this.name + ";" + this.email +";"+ this.Type + "]";
	 }
	 
	 int getId()
	 {
		 return id;
	 }
	 UserType getStat()
	 {
		 return this.Type;
	 }
	 
	 UserVoucherMap GetVouchers()
	 {
		 if(Vouchere == null)
		 {
			 this.Vouchere = new UserVoucherMap();
		 }
		
		 return this.Vouchere;
	 }
	 
	 Vector<Notification> getNotifications()
	 {
		 if(Notifications == null)
			 Notifications = new Vector<Notification>();
		return this.Notifications;
	 }
	 
	public  String getName()
	 {
		 return this.name;
	 }
	 
	public void updateVouchers(Notification N)
	{
		
		 Vector<Voucher> V = (Vector<Voucher>) this.GetVouchers().getValues(N.id);
		 if(V != null)
			 for(int i = 0 ; i < V.size(); i++ )
			 {
				 if(N.Type == NotificationType.CANCEL)
					 V.get(i).Status = VoucherStatusType.EXPIRED;
				 
			 }
	}
	public  String getPass()
	{
		return this.password;
	}

}
