/**
 * This file was auto-generated by the Titanium Module SDK helper for Android
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2010 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 *
 */
package ti.tinkerforge;

import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.titanium.TiApplication;

import com.tinkerforge.IPConnection;

@Kroll.module(name = "Tinkerforge", id = "ti.tinkerforge")
public class TinkerforgeModule extends KrollModule {

	public static final String LCAT = "TiFo 🚧";
	@Kroll.constant
	public static final int CONNECT_REASON_REQUEST = IPConnection.CONNECT_REASON_REQUEST;
	@Kroll.constant
	public static final int CONNECT_REASON_AUTO_RECONNECT = IPConnection.CONNECT_REASON_AUTO_RECONNECT;
	@Kroll.constant
	public static final int ENUMERATION_TYPE_CONNECTED = IPConnection.ENUMERATION_TYPE_CONNECTED;
	@Kroll.constant
	public static final int ENUMERATION_TYPE_DISCONNECTED = IPConnection.ENUMERATION_TYPE_DISCONNECTED;

	public TinkerforgeModule() {
		super();
	}

	@Kroll.onAppCreate
	public static void onAppCreate(TiApplication app) {
		Log.d(LCAT, "inside onAppCreate");
	}

}
