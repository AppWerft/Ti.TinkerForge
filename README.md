#Ti.TinkerForge


Titanium module for communication with [TinkerForge](http://tinkerforge.com).

<img src="http://www.tinkerforge.com/static/images/wit-einfach.png" />

##Usage

###Connect to device
First we can connect to Tinkerforge and enumerate all stuff:
```javascript
var TF = require("ti.tinkerforge");

var connection = TF.createConnection("192.168.3.4",4223);
connection.addEventListener("disconnected",function(con) {
});
connection.addEventListener("connected",function(con) {
	console.log(con);
	connection.authenticate("SehrGeheim"); // optional
	connection.enumerate();
});
connection.addEventListener("enumerated",function(e) {
	console.log(e)
});
```
The connection enumerator has seven parameters:
####uid: 
The UID of the device.
####connectedUID: 
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

Now we can communicate with bricklets. All bricklets have the same host/port, but different uid. Therefore you have to request the uids with enumerate. 


##BrickletTemperature

###Simple getter:
```javascript
var TF = require("ti.tinkerforge");
var IpConn = TF.createConnection("192.168.3.4",4223);
var BrickletTemperature = TF.createBrickletTemperatur(UID, IpConn);
console.log(BrickletTemperature.getTemperature());
IpConn.disconnect();
```

###Periodical
```javascript
var TF = require("ti.tinkerforge");
var IpConn = TF.createConnection("192.168.3.4",4223);
var BrickletTemperature = TF.createBrickletTemperatur(UID, IpConn);
BrickletTemperature.setInterval(function(e){
	console.log(e);
}, 1000);

```
###Treshold
```javascript
var TF = require("ti.tinkerforge");
var IpConn = TF.createConnection("192.168.3.4",4223);
var BrickletTemperature = TF.createBrickletTemperatur(UID, IpConn);
// Get threshold callbacks with a debounce time of 10 seconds (10000ms)
BrickletTemperature.etDebouncePeriod(10000);
BrickletTemperature.setTemperatureCallbackThreshold('>30',function(e){
	console.log("It is too hot, we need air conditioning!");
});


```

