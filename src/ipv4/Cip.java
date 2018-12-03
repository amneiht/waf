package ipv4;

public class Cip {
public static long tolong(String c)
{
	String[] t=c.split("\\."); 
	if(t.length==4)  return toint(t);
	t=c.split(":");
	return cvip(t);
}
private static long cvip(String[] t) {
	// TODO Auto-generated method stub
	long p=0;
	for(int i=0;i<8;i++)
	{
		p=p<<16;
		p=p+Integer.parseInt(t[i],16);
		
	}
	return p;
}
private static long toint(String[] t) {
	
	return (Long.parseLong(t[0])<<24)|(Integer.parseInt(t[1])<<16)|(Integer.parseInt(t[2])<<8)|Integer.parseInt(t[3]);
}
}
