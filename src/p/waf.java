package p;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import log.luat;

public class waf implements Runnable {
	Socket s;

	public waf(Socket k) {
		s = k;
	}

	static String host = "localhost";
	static int port = 8080;
	static int length = 50;
	static byte[] gend = { '\r', '\n', '\r', '\n' };

	@Override
	public void run() {
		Socket k = null;
		try {
			boolean alert = false;
			Long p, dem = 0L;
			s.setSoTimeout(2000);
			k = new Socket(host, port);
			InputStream in1 = s.getInputStream();
			OutputStream out2 = k.getOutputStream();
			byte[] buff = new byte[length + 3];
			int i;
			while (true) {
				p = System.currentTimeMillis();
				i = in1.read(buff, 3, length);
				if (i > 0) {
					out2.write(buff, 3, i);
					// System.out.print(new String(buff, 3, i));
				}
				save(buff, i);
				if (!nend(buff, i))
					break;
				// s.close();
				p = System.currentTimeMillis() - p;
				dem = dem + p;
				if (!alert) {
					if (dem > luat.getDelay()) {
						System.out.println("delay bat thuong");
					}
				} else if (dem > luat.maxout()) {
					{
						out2.write(gend);
						k.close();
						s.close();
						System.out.println("nodata");
						return;
					}
				}
			}
			// System.out.println("nodata");
			InputStream in2 = k.getInputStream();
			OutputStream out1 = s.getOutputStream();
			while (true) {
				i = in2.read(buff);
				if (i > 0)
					out1.write(buff, 0, i);
				if (i < length)
					break;
			}
			k.close();
			s.close();
			p = System.currentTimeMillis() - p;
			// System.out.println("end +"+p);
		} catch (Exception e) {
			try {
				s.close();
				if (k != null)
					k.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
			}
			// e.printStackTrace();

		}
	}

	static boolean nend(byte[] d, int i) {
		i = i + 3;
		if (i < 4)
			return false;
		if (d[i - 4] == '\r' && d[i - 3] == '\n' && d[i - 2] == '\r' && d[i - 1] == '\n')
			return false;
		return true;
	}

	static void save(byte[] buff, int i) {
		if (i > 0) {
			i = i + 3;
			buff[0] = buff[i - 3];
			buff[1] = buff[i - 2];
			buff[2] = buff[i - 1];
		}
	}
}
