package log;

public class luat {

	static int khoang = 10;// gom 10 s lam nhom
	static int lo = 7 * 3600;
	static int timemax = 86401;
	static int timemin = -1;
	static int maxip = 1000;
	static int nguong = 50; // so truy cap trong khoang thoi gian
	static int reset = 20; // 20 giay reset 1 lan
	static int clear = 300;
	static int maxtimeout = 20000;
	private static int delay = 5000;

	public static int maxout() {
		return maxtimeout;
	}

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

	public static int getClear() {
		return clear;
	}

	public static int getNguong() {
		return nguong;
	}

	public static int getDelay() {
		return delay;
	}

}
