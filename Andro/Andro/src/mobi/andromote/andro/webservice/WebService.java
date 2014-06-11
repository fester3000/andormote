package mobi.andromote.andro.webservice;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Date;
import java.util.Enumeration;

import mobi.andromote.andro.MainActivity;
import mobi.andromote.andro.androscript.AndroscriptProcessor;
import mobi.andromote.andro.androscript.datatypes.UnverifiedScript;

import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.ProtocolVersion;
import org.apache.http.impl.DefaultHttpServerConnection;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

public class WebService {
	private final Logger log = Logger.getLogger(WebService.class);
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
				toastMessage("I'm waiting here: " + serverSocket.getLocalPort());

				while (true) {
					Socket socket = serverSocket.accept();
					count++;
					message += "#" + count + " from " + socket.getInetAddress()
							+ ":" + socket.getPort() + "\n";
					toastMessage(message);

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
			Authenticator authenticator = new Authenticator(); 
			UnverifiedScript unverifiedScript = UnverifiedScript.nullValue();
			DefaultHttpServerConnection connection = new DefaultHttpServerConnection();
			try {
				connection.bind(hostThreadSocket, new BasicHttpParams());
				HttpRequest request = connection.receiveRequestHeader();
				connection.receiveRequestEntity((HttpEntityEnclosingRequest)request);
				HttpEntity entity = ((HttpEntityEnclosingRequest)request).getEntity();
				String content = EntityUtils.toString(entity);
				toastMessage(content);
				connection.sendResponseHeader(new BasicHttpResponse(new BasicStatusLine(new ProtocolVersion("HTTP", 1, 1), 200, "OK")));
				//TODO Test
				unverifiedScript = new UnverifiedScript("Script1", content, new Date());
				//TODO Test
				connection.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (HttpException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(authenticator.isAuthenticated().equals(AuthenticationStatus.OK)) {
				AndroscriptProcessor.INSTANCE.process(unverifiedScript, mainActivity);
			}
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

	private void toastMessage(final String text) {
		mainActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(mainActivity, text, Toast.LENGTH_SHORT).show();
			}
		});
	}
}