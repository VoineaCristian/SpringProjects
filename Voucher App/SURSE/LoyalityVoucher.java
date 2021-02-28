
public class LoyalityVoucher extends Voucher{
	
	float percentage;
	
	LoyalityVoucher(int Id, String code , String stat, String mail, int campaignid,float Value) 
	{
		super(Id,code,stat,mail,campaignid);
		percentage = Value;
		
		
	}
	public String toString()
	{
		return"[" + id + ";" + Status +  ";" + Email +  ";"  + percentage+";"+ CampaignId  + ";" + Date + "]";	 
	}
	

}
