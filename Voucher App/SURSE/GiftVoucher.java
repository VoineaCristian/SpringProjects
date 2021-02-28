
public class GiftVoucher extends Voucher{
	
	float value;
	
	GiftVoucher(int Id, String code , String stat, String mail, int campaignid,float Value) 
	{
		super(Id,code,stat,mail,campaignid);
		value = Value;
		
		
	}
	public String toString()
	{
		return"[" + id + ";" + Status +  ";" + Email +  ";"  + value+";"+ CampaignId  + ";" + Date + "]";	 
	}

	

}
