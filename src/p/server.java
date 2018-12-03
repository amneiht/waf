package p;

import java.io.IOException;
import java.net.ServerSocket;

import log.luat;
import log.pk;

public class server {
	public static void main(String[] args) {
		ServerSocket ser;
		pk.init();
		int v = luat.getReset() / 2;
		boolean less = true;
		try {
			ser = new ServerSocket(7777);
			while (true) {
				new Thread(new pk(ser.accept())).start();
				int k = (int) ((System.currentTimeMillis() / 1000) % luat.getReset());
				if (k > v) {
					if (less) {
						less = false;
						pk.xoa();
					}				} else {
					less = true;
				}
				
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
