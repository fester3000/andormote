package mobi.andromote.andro.webservice;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;

import android.app.Activity;
import android.widget.Toast;

public class WebService {
	
	Activity mainActivity; 
	String message = "";
	ServerSocket serverSocket;
	
	public WebService(Activity activity) {
		mainActivity = activity;
	}

	public void start() {
		Thread socketServerThread = new Thread(new SocketServerThread());
		socketServerThread.start();
	}

	public void destroy() {
		if (serverSocket != null) {
			try {
				serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private class SocketServerThread extends Thread {
		static final int SocketServerPORT = 8080;
		int count = 0;

		@Override
		public void run() {
			try {
				serverSocket = new ServerSocket(SocketServerPORT);
//				mainActivity.runOnUiThread(new Runnable() {
//					@Override
//					public void run() {
//						Toast.makeText(mainActivity, "I'm waiting here: "
//								+ serverSocket.getLocalPort(), Toast.LENGTH_SHORT).show();
//					}
//				});

				while (true) {
					Socket socket = serverSocket.accept();
					count++;
					message += "#" + count + " from " + socket.getInetAddress()
							+ ":" + socket.getPort() + "\n";

//					mainActivity.runOnUiThread(new Runnable() {
//						@Override
//						public void run() {
//							Toast.makeText(mainActivity, message, Toast.LENGTH_SHORT).show();
//						}
//					});

					SocketServerReplyThread socketServerReplyThread = new SocketServerReplyThread(
							socket, count);
					socketServerReplyThread.run();

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private class SocketServerReplyThread extends Thread {

		private Socket hostThreadSocket;
		int cnt;

		SocketServerReplyThread(Socket socket, int c) {
			hostThreadSocket = socket;
			cnt = c;
		}

		@Override
		public void run() {
			OutputStream outputStream;
			String msgReply = "Hello from Android, you are #" + cnt;

			try {
				outputStream = hostThreadSocket.getOutputStream();
				PrintStream printStream = new PrintStream(outputStream);
				printStream.print(msgReply);
				printStream.close();

				message += "replayed: " + msgReply + "\n";

//				mainActivity.runOnUiThread(new Runnable() {
//					@Override
//					public void run() {
//						Toast.makeText(mainActivity, message, Toast.LENGTH_SHORT).show();
//					}
//				});
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				message += "Something wrong! " + e.toString() + "\n";
			}

//			mainActivity.runOnUiThread(new Runnable() {
//				@Override
//				public void run() {
//					Toast.makeText(mainActivity, message, Toast.LENGTH_SHORT).show();
//				}
//			});
		}

	}

	private String getIpAddress() {
		String ip = "";
		try {
			Enumeration<NetworkInterface> enumNetworkInterfaces = NetworkInterface
					.getNetworkInterfaces();
			while (enumNetworkInterfaces.hasMoreElements()) {
				NetworkInterface networkInterface = enumNetworkInterfaces
						.nextElement();
				Enumeration<InetAddress> enumInetAddress = networkInterface
						.getInetAddresses();
				while (enumInetAddress.hasMoreElements()) {
					InetAddress inetAddress = enumInetAddress.nextElement();

					if (inetAddress.isSiteLocalAddress()) {
						ip += "SiteLocalAddress: " 
								+ inetAddress.getHostAddress() + "\n";
					}

				}

			}

		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ip += "Something Wrong! " + e.toString() + "\n";
		}

		return ip;
	}
}