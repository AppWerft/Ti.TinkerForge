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
		console.log("Connected UID:     " + e.uid);
		console.log("Device Identifier: " + e.identifier);
		connection.disconnect();
	});

```

