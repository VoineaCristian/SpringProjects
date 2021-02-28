import java.util.StringTokenizer;

public class TIME {


	
	String year;
	String month;
	String day;
	String hour;
	String minute;
	
	
	TIME(String y, String m, String d, String h, String mn)
	{
		year = y;
		month = m;
		day = d;
		hour = h;
		minute = mn;
	}
	TIME(int y, int m, int  d, int  h, int mn)
	{
		year = String.valueOf(y);
		month = String.valueOf(m);
		day = String.valueOf(d);
		hour = String.valueOf(h);
		minute = String.valueOf(mn);
	}
	public String toString()
	{
		return year + "-" + month + "-" + day+ "  " + hour + ":" + minute;
	}
	TIME(String S)
	{
		
		StringTokenizer ST = new StringTokenizer(S,"; - :");
		year = ST.nextToken();
		month = ST.nextToken();
		day = ST.nextToken();
		hour = ST.nextToken();
		minute = ST.nextToken();
		
	}
	



}
