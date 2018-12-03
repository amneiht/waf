package log;

import java.util.Date;

import ipv4.Cip;

public class note {
	private long s;
	private int time;
	private int lan=1;
	public note(String t) {
		s = Cip.tolong(t);
		time = (int) ((new Date().getTime() / 1000 + luat.lo) % 86400)/luat.khoang;
	}
	public note(String t,int tim) {
		s = Cip.tolong(t);
		time = tim;
	}
	public void pr()
	{
		System.out.println(s+"  "+time+" "+lan);
	}
	public int getsl()
	{
		return lan;
	}
	public void incre()
	{
		lan=lan+1;
	}
	public static int cretime()
	{
		return (int) ((new Date().getTime() / 1000 + luat.lo) % 86400)/luat.khoang;
	}
	public long getName() {
		return s;
	}

	public int getTime() {
		return time;
	}

}
