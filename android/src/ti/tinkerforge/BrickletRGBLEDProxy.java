/**
 * This file was auto-generated by the Titanium Module SDK helper for Android
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2010 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 *
 */
package ti.tinkerforge;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.kroll.common.Log;

import com.tinkerforge.BrickletTemperature;
import com.tinkerforge.IPConnection;
import com.tinkerforge.NotConnectedException;
import com.tinkerforge.TimeoutException;

// This proxy can be created by calling Tinkerforge.createExample({message: "hello world"})
@Kroll.proxy(creatableInModule = TinkerforgeModule.class)
public class BrickletRGBLEDProxy extends KrollProxy {
	private static final String LCAT = "TiFo";
	private IPConnection ipcon;
	private String UID;
	public KrollProxy proxy = null;
	private BrickletTemperature bricklet;

	public BrickletRGBLEDProxy(KrollProxy proxy) {
		super();
		this.proxy = proxy;
	}

	private void readArgs(Object[] args) {
		if (args.length != 2) {
			Log.d(LCAT, "two paramters (UID + endpoint) aspected");
			return;
		}
		if (!(args[0] instanceof String)) {
			Log.d(LCAT, "UID is missing");
			return;
		}
		if (!(args[1] instanceof ConnectionProxy)) {
			Log.d(LCAT, "Connection is missing");
			return;
		}

		if (args[0] instanceof String) {
			this.UID = (String) args[0];
		}
		if (args[1] instanceof ConnectionProxy) {
			ipcon = ((ConnectionProxy) args[1]).getConnection();
		}
	}

	@Override
	public void handleCreationArgs(KrollModule createdInModule, Object[] args) {
		readArgs(args);

		BrickletTemperature bricklet = new BrickletTemperature(UID, ipcon);

	}

	@Kroll.method
	public KrollDict getTemperature() {
		KrollDict res = new KrollDict();
		try {
			res.put("ok", true);
			res.put("data", bricklet.getTemperature());
			return res;
		} catch (TimeoutException e) {
			res.put("error", e.getMessage());
			e.printStackTrace();
			return res;
		} catch (NotConnectedException e) {
			e.printStackTrace();
			res.put("error", e.getMessage());
			return res;
		}
	}
}