#Ti.TinkerForge


<img qlign="right" src="http://download.tinkerforge.com/press/media/brick_stack_back.jpg" width=200/>Titanium module for communication with [TinkerForge system](http://tinkerforge.com).
The TinkerForge system is a collection of sensor and actors you can access with a simple API.


##Usage

###Connect to device
First we have to connect to Tinkerforge and enumerate all connected bricklets:
```javascript
var TF = require("ti.tinkerforge");
var ENDPOINT = "192.168.3.4:4223";
var connection = TF.createConnection(ENDPOINT);
The last endpoint (combination of host and port) will cached in properties. Therefore you can dismiss in next requests.

connection.addEventListener("found",function(e) {
	// every bricklet send an event, maybe you can collect in an array
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
With this simple bricklet we can measure the temperature. We have simple getter, a periodically "getter" and a  treshold.
<img src="http://www.tinkerforge.com/en/doc/_images/Bricklets/bricklet_temperature_tilted_600.jpg" width=400/>

###Simple getter:
```javascript
var TF = require("ti.tinkerforge");
var BrickletTemperature = TF.createBrickletTemperatur(UID,ENDPOINT);
console.log(BrickletTemperature.getTemperature());
BrickletTemperature.release();
```

###Periodical
```javascript
var TF = require("ti.tinkerforge");

var BrickletTemperature = TF.createBrickletTemperatur(UID,ENDPOINT);
BrickletTemperature.setInterval(function(e){
	console.log(e);
}, 1000);

```
###Treshold
```javascript
var TF = require("ti.tinkerforge");
var BrickletTemperature = TF.createBrickletTemperatur(UID,ENDPOINT);
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
var BrickletRGBLED = TF.createBrickletTemperatur(UID,ENDPOINT);
BrickletRGBLED.setRGBValue("#FF0000");
var rgb = BrickletRGBLED.getRGBValue();
console.log(rgb);
});
```
##BrickletSolidStateRelay
<img src="http://www.tinkerforge.com/en/doc/_images/Bricklets/bricklet_ssr_w_ssr_350.jpg" width=400 />
```javascript
var TF = require("ti.tinkerforge");
var BrickletSolidStateRelay = TF.createBrickletSolidStateRelay(UID,ENDPOINT);
BrickletSolidStateRelay.setState(true);
setTimeout(function(){
	BrickletSolidStateRelay.setState(false);
	}, 10000);

```





