# rgatest
Creating Nike´s Fuelband Animation on Android

## Goals
Create an animation of the circular Fuelband logo using the provided assets(font and png files).
You can see some sample animations of the logo in this video on YouTube:
<a href="http://youtu.be/LC6wptkt2Wk?t=27s" target="_blank">video</a>

The only elements you need to recreate are the circular logo in the middle and the large number indicating number of points. The rest of the UI can be ignored.

### Solution ###

* Project Type: *Android Studio*
* Platform: *Android*
* Min SDK: *10*
* Target SDK: *23*
* Gradle: *2.1.3*

### Third party libs ###

Using the following dependencies:

* **Animations**
    * <a href="https://github.com/JakeWharton/NineOldAndroids" target="_blank">NineOldAndroids 2.4.0</a>

### Tested Devices ###

The project has been tested on the following devices in ** *DEBUG BUILD VARIANT* ** :

* **Genymotion**
    * *Android v2.3.7/SDK 10*
    * *Android v4.4.4/SDK 19*
* **Motorola X Style**
    * *Android v6.0/SDK 23*

### Things to improve ###

* Encasuplate the GaugeView within the TextView in a Compound Control View
* Handle the *OnMeasure* callback accordingly
