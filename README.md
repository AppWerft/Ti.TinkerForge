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
For result you can use  an event:

```javascript
IPconn.addEventListener("connected",function(e) {
	console.log(e)
});
```
Alternatively  you can listen to the second parameter in creator:
 ```javascript
var TF = require("ti.tinkerforge");
var ENDPOINT = "192.168.3.4:4223";
var IPconn = TF.createConnection(ENDPOINT,function() {
});
```

If you need authentication you can use a property in tiapp.xml:
```xml
<property name="TIFORGE_SECRET" type="string">Rumpelstilzchen</property>
```
or you use the special format in URI 
```
var ENDPOINT = "Rumpelstilzchen:192.168.3.4:4223";
```
If you are connected, you can enumerate:

```javascript
var TF = require("ti.tinkerforge");
var ENDPOINT = "192.168.3.4:4223";
var IPconn = TF.createConnection(ENDPOINT,function(e) {
	if (e.connected) {
		IPconn.enumerate();
		IPconn.addEventListener("enumerated",function(e) {
			console.log(e)
		});
	}	
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
The Temperature Bricklet can be used to extend the features of Bricks by the capability to measure temperature. The measured temperature can be read out in °C. With configurable events it is possible to react on changing temperatures without polling.
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
The RGB LED Bricklet can be used to extend the features of Bricks with the capability to control a RGB LED. Each of the three channels (red, green, blue) can be controlled individually with 8 bit resolution.
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
The Solid State Relay Bricklet can be used to extend Bricks by the possibility to switch solid state relays (SSR).

With solid state relays large loads can be switched while being galvanically isolated. Mechanical relays can create switching sparks and other interferences. Solid state relays do not. Furthermore solid state relays are wearless and allow higher switching frequencies.

The maximum switching capacity depends on the connected solid state relay, which is controlled by the Solid State Relay Bricklet.
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
##BrickletColor
<img src="http://www.tinkerforge.com/en/doc/_images/Bricklets/bricklet_color_tilted_350.jpg" width=400 />

The Color Bricklet can be used to extend the features of Bricks with the capability to measure color, color temperature and illuminance of a light source. Thus the Bricklet can measure the color of an object via its reflected light. To create a defined illumination and color temperature the Bricklet is equipped with a API switchable LED.
```javascript
var TF = require("ti.tinkerforge");
var IPConn = TF.createConnection(ENDPOINT);
var BrickletColor = TF.createBrickletColor(UID,IPConn);
var color = BrickletColor.getColor();
console.log("red="+color.r+ " green="+color.g+ " blue="+color.blue+ " clear="+color.c);
BrickletColor.lightOn();
BrickletColor.lightOff();
BrickletColor.getIlluminance();
BrickletColor.getColorTemperature(); //Returns the color temperature in Kelvin.
```





