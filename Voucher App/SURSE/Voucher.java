import java.time.LocalDateTime;

abstract class Voucher {
	
	public int id;
	public String Code;
	public VoucherStatusType Status;
	public TIME Date;
	public String Email;
	public int CampaignId;
	public TIME UDate = null ;
	
	
	
	Voucher(int Id, String code , String stat, String mail, int campaignid) 
	{
		id = Id;
		Code = code;
		Date = null;
		Email = mail;
		CampaignId = campaignid;
		Status = VoucherStatusType.UNUSED;

		
	}
	
	

}
