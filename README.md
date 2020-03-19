# RobotCode2020

Code for our 2020 robot: Orion

If you're reading this after 2020 there's a good chance you're trying to fix something immediately before or in the middle of some kind of demo :P

## Features

* Accurately score balls into the outer port from anywhere from 10-25ft from the target
  * Lookup table + interpolation lets us convert from limelight `ty` to distance from target
  * Another lookup table + interpolation converts from distance to shooter rpm
  * fallback speed provided if limelight doesn't detect the target
  
* Autononomous ingredient: drive straight w/ NavX

* Vision alignment routine automatically stops driver from getting too close to the target

