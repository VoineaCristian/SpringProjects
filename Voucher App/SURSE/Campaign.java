import java.time.LocalDateTime;
import java.util.*;




public class Campaign {

	
	public int Id;
	public String name;
	public String Description;
	public int VoucherNrMax;
	public int VoucherNrAvailabe;
	public CampaignStatusType Status = CampaignStatusType.NEW;
	public Vector<Voucher> Vouchers;
	public CampaignVoucherMap SharedV;
	public Vector<User> Users;
	private int Count = 1;
	public TIME start;
	public TIME stop;
	public float budget;
	public String Strategy;
	public int StrUs = 0;
	
	
	Campaign()
	{
		
	}
	Campaign(int ID,String N,String D ,TIME S, TIME St, float B, String Strat)
	{
		Id = ID;
		name = N;
		
		Description = D;
		start = S;
		stop = St;
		Strategy = Strat;
		budget = B;
	}
	Vector<Voucher> GetVouchers()
	{
		return Vouchers;
	}
	
	Voucher GetVoucher(String code)
	{
		for(int i = 0 ; i < Vouchers.size();i++)
		{
			if(Vouchers.get(i).Code.equals(code))
			{
				return Vouchers.get(i);
			}
		}
		return null;
		
	}
	
	Voucher GenerateVoucher(String email, String voucherType, float value)
	{
		Voucher voucher;
		
			
			if(this.Status == CampaignStatusType.NEW)
				this.Status = CampaignStatusType.STARTED;
				
			Random element = new Random();
			int ok = 0;
			String cod = new String();
			if(Vouchers == null)
			{
				Vouchers = new Vector<Voucher>();
			}
			while(ok == 0 && Vouchers.size() != 0)
			{
				cod = String.valueOf(element.nextInt(99999));
				for(int i = 0; i < Vouchers.size(); i++)
				{
					if(Vouchers.get(i).Code.equals(cod))
					{
						break;
					}
				}
				ok = 1;
			}
			cod = String.valueOf(element.nextInt(99999));
			if(voucherType.equals("GiftVoucher"))
			{
				voucher = new GiftVoucher(Count, cod, "UNUSED", email,Id,value);
				System.out.println("cadou");
			}else 
				
				voucher = new LoyalityVoucher(Count, cod, "UNUSED", email,Id,value);
			voucher.CampaignId = this.Id;
			this.Count++;
			Vouchers.add(voucher);
			if(SharedV == null)
				SharedV = new CampaignVoucherMap();
			
			SharedV.addVoucher(voucher);
			
			System.out.println(voucher);
			voucher.Date = this.stop;
			return voucher;
			
		}
   void redeemVoucher(int Id,TIME D)
   {
	   for(int i = 0; i < Users.size();i++)
	   {
		  Vector<Voucher> V = (Vector<Voucher>)SharedV.getValues(Users.get(i).email);
		  for(int j = 0; j < V.size(); j++)
		  {
			  if(V.get(j).id == Id)
			  {
				  V.get(j).Status = VoucherStatusType.USED;
				  V.get(j).UDate = D;
			  }
		  }
	   }
   }
	Vector<User> getObserver()
		{
			return Users;
		}
	void addObserver(User user)
	{
		if(Users == null )
			Users = new Vector<User>();
		if(!Users.contains(user))
			Users.add(user);
	}
	
	void removeObserver(User user)
	{
		Users.remove(Users.indexOf(user));
	}
	
	void notifyAllObservers(Notification notification)
	{
		if(Users != null)
			for(int i = 0; i < Users.size(); i++)
			{
				List<String> CodeList = new ArrayList<String>();
				
				Vector<Voucher> V = (Vector<Voucher>) SharedV.getValues(Users.get(i).email);
				if(V != null)
				{
					
					for(int j = 0; j < V.size(); j++)
					{
					
						CodeList.add(V.get(j).Code);
					}
				}
				
				Users.get(i).Notify(new Notification(notification.date, notification.id, CodeList,notification.Type));
			}
	}
	
	void modify(Campaign C)
	{
		this.name = C.name;
		this.start = C.start;
		this.stop = C.stop;
		this.Description = C.Description;
	}
	
	
	public String toString()
	{
		return "[" + this.Id + " " + this.name + "  " + this.Description + " " + this.start + " " + this.stop +" "+ this.budget + " " + this.Strategy + "]";
	}
	
}
