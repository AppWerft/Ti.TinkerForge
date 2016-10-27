package ti.tinkerforge;

import org.appcelerator.kroll.common.Log;

import com.tinkerforge.IPConnection;

public class BrickletUri {
	private String LCAT = TinkerforgeModule.LCAT;
	public String UID;
	public IPConnection ipConn;

	public String getUID() {
		return UID;
	}

	public IPConnection getIPconn() {
		return ipConn;
	}

	public BrickletUri(Object[] args) {
		if (args.length != 2) {
			Log.e(LCAT, "two paramters (UID + endpoint) aspected");
			return;
		}
		if (args[0] instanceof String) {
			this.UID = (String) args[0];
		} else {
			Log.e(LCAT, "UID is missing");
			return;
		}
		if (args[1] instanceof ConnectionProxy) {
			ipConn = ((ConnectionProxy) args[1]).getConnection();
		} else {
			Log.e(LCAT, "Connection is missing");
			return;
		}
	}
}
