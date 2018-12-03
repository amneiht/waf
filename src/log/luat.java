package log;

public class luat {

	static int khoang = 10;// gom 10 s lam nhom
	static int lo = 7 * 3600;
	static int timemax = 86401;
	static int timemin = -1;
	static int maxip = 200000; // so truy cap trong khoang thoi gian
	static int reset = 20;  // 20 giay reset 1 lan
	public static int getKhoang() {
		return khoang;
	}
	public static int getLo() {
		return lo;
	}
	public static int getTimemax() {
		return timemax;
	}
	public static int getTimemin() {
		return timemin;
	}
	public static int getMaxip() {
		return maxip;
	}
	public static int getReset() {
		return reset;
	}
}
