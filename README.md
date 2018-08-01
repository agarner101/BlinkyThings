# BlinkyThings
Android Things LED-Blink Project

Part **2** of Android Things Tutorial. For initial setup and Hello World, see [Part 1](https://github.com/agarner101/HelloThings)

To see the main java code, go to [HomeActivity.java](app/src/main/java/io/andrewgarner/blinkythings/HomeActivity.java)

![alt text](https://media.giphy.com/media/5Yfaif8BvQv6RUvH2N/giphy.gif)

#### Materials
1. Raspberry Pi 3 Model B
2. Breadboard
3. LED
4. 1K Ohm Resistor
5. Two Male/Female Jumper Wires

#### Raspberry Pi 3 I/O
This is the pin configuration for Android Things for Raspberry Pi found [here](https://developer.android.com/things/hardware/raspberrypi-io)
![alt text](https://i.imgur.com/tgKhOJX.png)

For our purposes, we will be using:
- BCM2 - Pin 3
- Ground - Pin 6

#### 1. Hook up LED
Wire up the Pi following this schematic:
![alt text](https://i.imgur.com/2xIoNEz.png)

#### 2. ADB Connect and Run App
See [Tutorial Part 1](https://github.com/agarner101/HelloThings/blob/master/README.md) step 5 and 6 for help on how to do this.

### Results:
LED will turn on and off in 1 second increments.

![alt text](https://media.giphy.com/media/5Yfaif8BvQv6RUvH2N/giphy.gif)
