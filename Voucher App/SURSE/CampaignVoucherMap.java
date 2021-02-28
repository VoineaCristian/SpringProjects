import java.util.Vector;


public class CampaignVoucherMap extends ArrayMap {
	
	boolean addVoucher(Voucher voucher)
	{
		
		Vector vector;
		if(super.getValues(voucher.Email) == null)
		{
			
			vector = new Vector();
			vector.add(voucher);
			put(voucher.Email,vector);
			
		}
		else
		{
			vector =(Vector) super.getValues(voucher.Email);
			
			if(vector.contains(voucher) == false)
				vector.add(voucher);
		}
		return true;
			
		
	}
		
}