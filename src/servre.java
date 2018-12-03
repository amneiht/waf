

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import log.luat;
import log.pk;
import log.list.queue;

public class servre implements Runnable {
	static queue<Socket> stack = new queue<Socket>();

	// @SuppressWarnings("resource")
	public static void main(String[] args) {
		ServerSocket ser;
		pk.init();
		new Thread(new servre()).start();
		try {
			ser = new ServerSocket(7777);
			while (true) {
				stack.add(ser.accept());
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
	//	int xoa = 0;
		int v = luat.getReset() / 2;
		boolean less = true;
		while (true) {
			if (pk.isSem()) {
			
				// System.out.println("do");
				if (stack.Nrong())
				{
					System.gc();
					try {
						new Thread(new pk(stack.get())).start();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						 stack = new queue<Socket>();
					System.out.println("coloi");
					}
				}
			}

			// bo dinh gio
			int k = (int) ((System.currentTimeMillis() / 1000) % luat.getReset());
			if (k > v) {
				if (less) {
					less = false;
					pk.xoa();
				}
			} else {
				less = true;
			}

		}

	}

}
