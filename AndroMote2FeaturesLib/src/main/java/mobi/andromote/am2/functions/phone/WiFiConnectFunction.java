package mobi.andromote.am2.functions.phone;

import java.util.List;

import mobi.andromote.functionalityFramework.datatypes.ActionResult;
import mobi.andromote.functionalityFramework.datatypes.FunctionParam;
import mobi.andromote.functionalityFramework.functions.BaseDeviceFunction;
import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;

public class WiFiConnectFunction extends BaseDeviceFunction {
	public enum WIFI implements FunctionParam {
		/**
		 * Nazwa dodawanej sieci
		 */
		SSID,
		/**
		 * Tryb {@link WIFI_MODE} zabezpieczenia sieci WIFI
		 */
		MODE,
		/**
		 * Hasło do sieci
		 */
		PASS;
	}
	public enum WIFI_MODE implements FunctionParam {
		OPEN,
		WEPx,
		WPAx;
	}
	private WIFI_MODE networkMode = WIFI_MODE.OPEN;
	private String networkSSID = "";
	private String networkPass = "";
	
	public WiFiConnectFunction(Context context) {
		super(context);		
	}

	@Override
	public ActionResult run() {
		connectToWiFI();
		return ActionResult.COMPLETED;
	}

	private void connectToWiFI() {
		if(params.containsKey(WIFI.SSID)) {
			networkSSID = (String)params.get(WIFI.SSID);
		}
		if(params.containsKey(WIFI.PASS)) {
			networkPass = (String)params.get(WIFI.PASS);
		}
		if(params.containsKey(WIFI.PASS)) {
			networkMode = WIFI_MODE.valueOf((String)params.get(WIFI.MODE));
		}
		WifiManager wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
		ConnectToWIFITask connectTask = new ConnectToWIFITask(wifiManager);
		connectTask.execute();
	}

	/**
	 * Sprawdza czy dana siec istnieje i aktualizuje jej konfiguracje je�li
	 * ta zostanie znaleziona
	 * @param config nowa konfiguracja sieci
	 * @return identyfikator sieci, do ktorej podlaczono
	 */

	private void updateNetwork(WifiManager wifiManager, WifiConfiguration config) {
		Integer foundNetworkId = findNetworkInExistingConfig(wifiManager, config.SSID);
		if (foundNetworkId != null) {
			logger.debug("Removing old configuration of " + config.SSID);
			wifiManager.removeNetwork(foundNetworkId);
			wifiManager.saveConfiguration();
		}
		int networkId = wifiManager.addNetwork(config);
		if (networkId >= 0) {
			if (wifiManager.enableNetwork(networkId, false)) {
				logger.debug("Connecting to network " + config.SSID);
				wifiManager.saveConfiguration();
			} else {
				logger.debug("Failed to enable network " + config.SSID);
			}
		} else {
			logger.debug("Unable to add network " + config.SSID);
		}
	}

	private WifiConfiguration clearNetworkConf(String ssid) {
		WifiConfiguration config = new WifiConfiguration();
		config.allowedAuthAlgorithms.clear();
		config.allowedGroupCiphers.clear();
		config.allowedKeyManagement.clear();
		config.allowedPairwiseCiphers.clear();
		config.allowedProtocols.clear();
		
		config.SSID = convertToQuotedString(ssid);
		return config;
	}

	
	private void changeNetworkWEP(WifiManager wifiManager, String ssid, String password) {
		WifiConfiguration config = clearNetworkConf(ssid);
		config.wepKeys[0] = convertToQuotedString(password);
		config.wepTxKeyIndex = 0;
		config.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.SHARED);
		config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
		config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
		config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
		config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
		config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);
		updateNetwork(wifiManager, config);
	}

	private void changeNetworkWPA(WifiManager wifiManager, String ssid, String password) {
		WifiConfiguration config = clearNetworkConf(ssid);
		config.preSharedKey = convertToQuotedString(password);
		config.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
		config.allowedProtocols.set(WifiConfiguration.Protocol.WPA); // WPA
		config.allowedProtocols.set(WifiConfiguration.Protocol.RSN); // WPA2
		config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
		config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_EAP);
		config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
		config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
		config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
		config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
		updateNetwork(wifiManager, config);
	}

	private void changeNetworkUnEncrypted(WifiManager wifiManager, String ssid) {
		WifiConfiguration config = clearNetworkConf(ssid);
		config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
		updateNetwork(wifiManager, config);
	}

	private Integer findNetworkInExistingConfig(WifiManager wifiManager, String ssid) {
		List<WifiConfiguration> existingConfigs = wifiManager.getConfiguredNetworks();
		for (WifiConfiguration existingConfig : existingConfigs) {
			if (existingConfig.SSID.equals(ssid)) {
				return existingConfig.networkId;
			}
		}
		return null;
	}

	
	  private static String convertToQuotedString(String string) {
	    if (string == null || string.length() == 0) {
	      return null;
	    }
	    if (string.charAt(0) == '"' && string.charAt(string.length() - 1) == '"') {
	      return string;
	    }
	    return '\"' + string + '\"';
	  }
	  
	/**
	 * Przeprowadza proces uruchomienia komunikacji WIFI, dodaje/aktualizuje konfiguracje sieci
	 * i podejmuje probe polaczenia z dodana siecia
	 * @author Sebastian Łuczak
	 *
	 */
	private class ConnectToWIFITask extends AsyncTask<Void, Void, Boolean> {

		private WifiManager wifiManager;
		
		public ConnectToWIFITask(WifiManager wifiManager) {
			super();
			this.wifiManager = wifiManager;
		}

		@Override
		protected Boolean doInBackground(Void... params) {
			if(!wifiManager.isWifiEnabled()) {
				logger.debug("Enabling WIFI...");
				if(wifiManager.setWifiEnabled(true)) {
					logger.debug("WIFI Enabled");
				} else {
					logger.debug("Failed to enable WIFI");
					return false;
				}

				int count = 0;
				while(!wifiManager.isWifiEnabled()) {
					if(count >= 10) {
						logger.debug("Enable WIFI timeout");
						return false;
					}
					logger.debug("Waiting for WIFI...");
					try {
						Thread.sleep(1000L);
					} catch (InterruptedException e) {

					}
					count++;
				}
			}
			
			logger.debug(networkSSID);

			switch(networkMode) {
			//OPEN
			case OPEN: 
				changeNetworkUnEncrypted(wifiManager, networkSSID);
				break;
			//WEP
			case WEPx: 
				changeNetworkWEP(wifiManager, networkSSID, networkPass);
				break;
			//WPA/WPA2
			case WPAx: 
				changeNetworkWPA(wifiManager, networkSSID, networkPass);
				break;
			default:
				logger.debug("Network mode not recognized, attempting to connect to open network");
				changeNetworkUnEncrypted(wifiManager, networkSSID);
			}
			return true;
		}
		
		@Override
		protected void onPostExecute(Boolean result) {
			
			if(result) {
				logger.info("Connected to " + networkSSID);
			} else {
				logger.info("Failed to connect to " + networkSSID);			}
			super.onPostExecute(result);
		}

		
	  }

	@Override
	public void putParam(String propertyName, String value) {
		params.put(WIFI.valueOf(propertyName), value);
	}

}
