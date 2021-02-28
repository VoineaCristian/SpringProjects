import java.time.LocalDateTime;
import java.util.*;

public class Notification {

	public TIME date;
	public int id;
	public ArrayList<String> VoucherCodeList;
	public NotificationType Type;
	
	Notification()
	{
		
	}
	Notification(TIME D,int ID, List<String> Codes, NotificationType tp)
	{
		date = D;
		id = ID;
		
		VoucherCodeList = (ArrayList<String>) Codes;
		Type = tp;
	}
	
	public String toString()
	{
		return "[" + id + ";" + VoucherCodeList + ";" + date + ";" + Type + "]";
	}
}
