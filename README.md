#Ti.TinkerForge


Titanium module for communication with [TinkerForge](http://tinkerforge.com).

<img src="http://www.tinkerforge.com/static/images/wit-einfach.png" />

##Usage

###Connect to device
First we can connect to Tinkerforge and enumerate all stuff:
```javascript
var TF = require("ti.tinkerforge");
var connection = TF.createConnection({
	ip : "192.168.3.4",
	port : 4223,
	onload : function(e) {
		console.log(e);
		connection.disconnect();
	});
```
The connection has seven paramters:
####uid: 
The UID of the device.
###connectedUID: 
UID where the device is connected to. For a Bricklet this will be a UID of the Brick where it is connected to. For a Brick it will be the UID of the bottom Master Brick in the stack. For the bottom Master Brick in a stack this will be "0". With this information it is possible to reconstruct the complete network topology.

####position: 
For Bricks: '0' - '8' (position in stack). For Bricklets: 'a' - 'd' (position on Brick).

####hardwareVersion: 
Major, minor and release number for hardware version.

####firmwareVersion: 
Major, minor and release number for firmware version.

####deviceIdentifier:
A number that represents the device.

####enumerationType: 
Type of enumeration.

[More about this parameters](http://www.tinkerforge.com/en/doc/Software/IPConnection_Java.html#ipcon-java).
