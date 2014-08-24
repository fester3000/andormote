package mobi.andromote.andro.webservice;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Date;
import java.util.Enumeration;

import mobi.andromote.andro.androscript.AndroscriptProcessor;

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

import pl.fester3k.androcode.datatypes.Script;
import pl.fester3k.androcode.datatypes.ScriptProcessStatus;
import pl.fester3k.androcode.interpreter.device.CapabilitiesAnalyzer;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

public class WebService {
	private final Logger log = Logger.getLogger(WebService.class);
	Context context; 
	String message = "";
	ServerSocket serverSocket;
	SocketServerThread socketServerThread;

	public WebService(Context context) {
		this.context = context;
	}

	public void start() {
		socketServerThread = new SocketServerThread();
		socketServerThread.start();
	}

	public void destroy() {
		if (serverSocket != null) {
			socketServerThread.tryToStopMe();
		}
	}

	private class SocketServerThread extends Thread {
		static final int SocketServerPORT = 8080;
		int count = 0;
		private boolean isFinished = false;

		@Override
		public void run() {
			try {
				serverSocket = new ServerSocket(SocketServerPORT);
				toastMessage("I'm waiting here: " + getIpAddress() + ":" + serverSocket.getLocalPort());

				while (!isFinished) {
					Socket socket = serverSocket.accept();
					count++;
					message += "#" + count + " from " + socket.getInetAddress()	+ ":" + socket.getPort() + "\n";
					toastMessage(message);
					SocketServerReplyThread socketServerReplyThread = new SocketServerReplyThread(socket, count);
					socketServerReplyThread.run();

				}
			} catch(SocketException e) {
				log.debug(e.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void tryToStopMe() {
			try {
				serverSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			isFinished = true;
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
			Script script = Script.nullValue();
			DefaultHttpServerConnection connection = new DefaultHttpServerConnection();
			try {
				connection.bind(hostThreadSocket, new BasicHttpParams());
				String content = getContentFromHttpRequest(connection);
				toastMessage(content);
				if(authenticator.isAuthenticated().equals(AuthenticationStatus.OK)) {
					script = new Script(Script.generateScriptName(), content, new Date());
					connection.sendResponseHeader(new BasicHttpResponse(new BasicStatusLine(new ProtocolVersion("HTTP", 1, 1), 200, "OK")));
					AndroscriptProcessor.INSTANCE.process(script, context);
				} else {
					connection.sendResponseHeader(new BasicHttpResponse(new BasicStatusLine(new ProtocolVersion("HTTP", 1, 1), 403, "Forbidden")));
				}
				connection.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (HttpException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		private String getContentFromHttpRequest(
				DefaultHttpServerConnection connection) throws HttpException,
				IOException {
			HttpRequest request = connection.receiveRequestHeader();
			connection.receiveRequestEntity((HttpEntityEnclosingRequest)request);
			HttpEntity entity = ((HttpEntityEnclosingRequest)request).getEntity();
			String content = EntityUtils.toString(entity);
			return content;
		}

	}

	private String getIpAddress() {
		String ip = "";
		try {
			Enumeration<NetworkInterface> enumNetworkInterfaces = NetworkInterface
					.getNetworkInterfaces();
			while (enumNetworkInterfaces.hasMoreElements()) {
				NetworkInterface networkInterface = enumNetworkInterfaces.nextElement();
				Enumeration<InetAddress> enumInetAddress = networkInterface.getInetAddresses();
				while (enumInetAddress.hasMoreElements()) {
					InetAddress inetAddress = enumInetAddress.nextElement();
					if (inetAddress.isSiteLocalAddress()) {
						ip += inetAddress.getHostAddress();
						break;
					}

				}
			}

		} catch (SocketException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return ip;
	}

	private void toastMessage(final String text) {
		log.info(text);
		Intent intent = new Intent("message-event");
		intent.putExtra("message", text);
		LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
	}
}