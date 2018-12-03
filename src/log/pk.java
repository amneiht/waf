package log;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import p.waf;

public class pk implements Runnable {
	static final String path = System.getenv("BTL_WAF");
	static final String spr = File.separator;
	static String log = path + spr + "blog.txt";
	static int lo = 7 * 3600;
	static List<note> brr = Collections.synchronizedList(new ArrayList<note>());
	static List<note> arr = Collections.synchronizedList(new ArrayList<note>());
	static int lag = 60;
	static int clear = 300;

	public static boolean isSem() {
		return sem;
	}

	static boolean sem = true;
	Socket p;

	public pk(Socket d) {
		p = d;
	}

	public static void init() {
		arr.add(new note("0.0.0.0", luat.timemin));
		arr.add(new note("255.255.255.255", luat.timemax));
		brr.add(new note("0.0.0.0", luat.timemin));
		brr.add(new note("255.255.255.255", luat.timemax));

	}

	/**
	 * them 1 dia chi ip va tra ve so luong truy cap trong 1 khoang thoi gian
	 * 
	 * @param l
	 * @return
	 */
	synchronized static int add(String sd, List<note> arr) {
		// System.out.println(sd);
		note l = new note(sd);
		int d = 0, cuoi = arr.size() - 1;
		int mid, cmp;
		mid = 0;
		while (cuoi > d) {
			mid = (d + cuoi) / 2;
			cmp = compare(l, arr.get(mid));
			if (cmp == 0) {
				arr.get(mid).incre();
				return arr.get(mid).getsl();
			}
			if (mid == d)
				break;
			if (cmp == 1) {
				d = mid;
			} else {
				cuoi = mid;
			}
		}

		// System.out.println(mid);
		arr.add(mid + 1, l);
		return 1;
	}

	public static void xoa() {
		sem = false;// cam cac hoat dong
		int t = note.cretime();
		synchronized (arr) {
			for (int i = arr.size() - 2; i > 1; i--) {
				if (arr.get(i).getTime() != t) {
					arr.remove(i);
				}
			}
		}
		sem = true;
	}

	/**
	 * 1 la lon hon -1 nho hon 0 bang
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	static int compare(note a, note b) {
		if (a.getName() > b.getName())
			return 1;
		if (a.getName() < b.getName())
			return -1;
		if (a.getTime() > b.getTime())
			return 1;
		if (a.getTime() < b.getTime())
			return -1;
		return 0;
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			String c = p.getInetAddress().getHostAddress();
			if (check(c)) {
				// System.out.println("loai bo"+c);
				p.close();
				return;
			} else {
				int v = add(c, arr);
				if (v > luat.getMaxip()) {
					System.out.println(v);
					p.close();
					// synchronized(brr) // ngan chan tac dong
					{
						add(c, brr);
					}
				} else {
					new Thread(new waf(p)).start();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * kiem tra tem mien co trong black list ko
	 */
	private boolean check(String sd) {
		// TODO Auto-generated method stub
		note l = new note(sd);
		int d = 0, cuoi = brr.size() - 1;
		int mid, cmp;
		mid = 0;
		while (cuoi > d) {
			mid = (d + cuoi) / 2;
			cmp = bcompare(l, brr.get(mid));
			if (cmp == 0) {
				return true;
			}
			if (mid == d)
				break;
			if (cmp == 1) {
				d = mid;
			} else {
				cuoi = mid;
			}
		}
		return false;
	}
	static int bcompare(note a, note b) {
		if (a.getName() > b.getName())
			return 1;
		if (a.getName() < b.getName())
			return -1;
		return 0;
	}
}
