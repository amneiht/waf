package p;

import java.io.IOException;
import java.net.ServerSocket;

import log.luat;
import log.pk;

public class server {
	static boolean safe = false;

	public static void main(String[] args) {
		ServerSocket ser;
		pk.init();
		int v = 0, d = 0;
		try {
			ser = new ServerSocket(7777);
			while (true) {
				int k = (int) ((System.currentTimeMillis() / 1000));
				int z=k / luat.getReset();
				if (z != v) {
					v =z;
					pk.xoa();
				}
				z=k / luat.getClear();
				if (z != d) {
					d = z;
					pk.clear();
				}
				if (safe)
					new Thread(new pk(ser.accept())).start();
				else
					new Thread(new waf(ser.accept())).start();
				//System.out.println("cmn");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
