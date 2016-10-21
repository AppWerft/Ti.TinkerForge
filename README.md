#Ti.TinkerForge

<img src="http://download.tinkerforge.com/press/media/brick_stack_back.jpg" width=280/>
TinkerForge is a system of open source hardware building blocks that allows you to combine sensor and actuator blocks by plug and play. You can create your individual hardware system by choosing the necessary building blocks for your project and combine it with other home automation products. There are many blocks available e.g for temperature, humidity or air pressure measurement as well as for I/O, LCDs and motor control. 

##Usage

###Constants
```javascript
var TF = require("ti.tinkerforge");
TF.CONNECT_REASON_REQUEST
TF.CONNECT_REASON_AUTO_RECONNECT
TF.ENUMERATION_TYPE_CONNECTED
TF.ENUMERATION_TYPE_DISCONNECTED
```

###Connect to device
First we have to connect to Tinkerforge and enumerate all connected bricklets:
```javascript
var TF = require("ti.tinkerforge");
var ENDPOINT = "192.168.3.4:4223";
var IPconn = TF.createConnection(ENDPOINT);
```
The IPconn has to events:

```javascript
IPconn.addEventListener("connected",function(e) {
	console.log(e)
});
IPconn.addEventListener("enumerated",function(e) {
	// every bricklet send an event, maybe you can collect in an array
	console.log(e)
});

```
If you need authentication you can use a property in tiapp.xml:
```xml
<property name="TIFORGE_SECRET" type="string">Rumpelstilzchen</property>
```
For the second event you can alternatively listen as second parameter in creator:
 ```javascript
var TF = require("ti.tinkerforge");
var ENDPOINT = "192.168.3.4:4223";
var IPconn = TF.createConnection(ENDPOINT,function() {
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

Now we have all uids from all bricklets and we can communicate with bricklets. 
All bricklets have the same host/port, but different uid. Therefore address the bricklets by UUID. 

##BrickletTemperature
With this simple bricklet we can measure the temperature. We have simple getter, a periodically "getter" and a  treshold.
<img src="http://www.tinkerforge.com/en/doc/_images/Bricklets/bricklet_temperature_tilted_600.jpg" width=400/>

###Simple getter:
```javascript
var TF = require("ti.tinkerforge");
var IPconn = TF.createConnection(ENDPOINT);
var BrickletTemperature = TF.createBrickletTemperatur(UID,IPconn);
console.log(BrickletTemperature.getTemperature());
BrickletTemperature.release();
```

###Periodical
```javascript
var TF = require("ti.tinkerforge");
var IPconn = TF.createConnection(ENDPOINT);
var BrickletTemperature = TF.createBrickletTemperatur(UID,IPconn);
BrickletTemperature.setInterval(function(e){
	console.log(e);
}, 1000);

```
###Treshold
```javascript
var TF = require("ti.tinkerforge");
var IPconn = TF.createConnection(ENDPOINT);
var BrickletTemperature = TF.createBrickletTemperatur(UID,IPconn);
// Get threshold callbacks with a debounce time of 10 seconds (10000ms)
BrickletTemperature.setDebouncePeriod(10000);
BrickletTemperature.setTemperatureThreshold('>30',function(e){
	console.log("It is too hot, we need air conditioning!");
});
```

##RGBLED
<img src="http://www.tinkerforge.com/en/doc/_images/Bricklets/bricklet_rgb_led_tilted_350.jpg" width=400 />
```javascript
var TF = require("ti.tinkerforge");
var IPconn = TF.createConnection(ENDPOINT);
var BrickletRGBLED = TF.createBrickletTemperatur(UID,IPconn);
BrickletRGBLED.setRGBValue("#FF0000");
var rgb = BrickletRGBLED.getRGBValue();
console.log(rgb);
});
```
##BrickletSolidStateRelay
<img src="http://www.tinkerforge.com/en/doc/_images/Bricklets/bricklet_ssr_w_ssr_350.jpg" width=400 />
```javascript
var TF = require("ti.tinkerforge");
var IPConn = TF.createConnection(ENDPOINT);
var BrickletSolidStateRelay = TF.createBrickletSolidStateRelay(UID,IPconn);
BrickletSolidStateRelay.setState(true);
setTimeout(function(){
	BrickletSolidStateRelay.setState(false);
	}, 10000);

```





