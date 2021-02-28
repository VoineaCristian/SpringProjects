import java.util.*;


public class UserVoucherMap extends ArrayMap {
	
	boolean addVoucher(Voucher voucher)
	{
		
		Vector vector;
		if(super.getValues(voucher.CampaignId) == null)
		{
			System.out.println("nou");
			vector = new Vector();
			vector.add(voucher);
			put(voucher.CampaignId,vector);
			
		}
		else
		{
			System.out.println("vechi");
			vector =(Vector) super.getValues(voucher.CampaignId);
			
			if(vector.contains(voucher) == false)
				vector.add(voucher);
		}
		return true;
			
		
	}
		
}
