package mobi.andromote.andro.webservice;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Date;
import java.util.Enumeration;

import mobi.andromote.andro.androscript.AndroCodeProcessor;
import mobi.andromote.androcode.datatypes.Script;
import mobi.andromote.functionalityFramework.datatypes.BroadcastIntentFilters;

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

import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;

public class WebService {
	private final Logger log = Logger.getLogger(WebService.class);
	private final Context  context; 
	private String connectionList = "";
	private ServerSocket serverSocket;
	private SocketServerThread socketServerThread;


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

				while (!isFinished()) {
					Socket socket = serverSocket.accept();
					count++;
					connectionList += "#" + count + " from " + socket.getInetAddress()	+ ":" + socket.getPort() + "\n";
					toastMessage(connectionList);
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

		public synchronized void tryToStopMe() {
			try {
				serverSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			setFinished(true);
		}

		public synchronized boolean isFinished() {
			return isFinished;
		}

		public synchronized void setFinished(boolean isFinished) {
			this.isFinished = isFinished;
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
			Script script = Script.nullValue();
			DefaultHttpServerConnection connection = new DefaultHttpServerConnection();
			try {
				connection.bind(hostThreadSocket, new BasicHttpParams());
				String content = getContentFromHttpRequest(connection);
				toastMessage(content);

				script = new Script(Script.generateScriptName(), content, new Date());
				connection.sendResponseHeader(new BasicHttpResponse(new BasicStatusLine(new ProtocolVersion("HTTP", 1, 1), 200, "OK")));
				connection.close();
				try {
				    Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
				    Ringtone r = RingtoneManager.getRingtone(context.getApplicationContext(), notification);
				    r.play();
				} catch (Exception e) {
				    e.printStackTrace();
				}
				AndroCodeProcessor.INSTANCE.process(script, context);
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
		Intent intent = new Intent(BroadcastIntentFilters.TOAST);
		intent.putExtra("message", text);
		LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
	}
}