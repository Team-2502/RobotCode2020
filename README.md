# RobotCode2020

Code for Team 2502's 2020 robot for Infinite Recharge: Orion

## Features

* Accurately score balls into the outer port anywhere 10-25ft from the target
  * Lookup table + interpolation lets us convert from Limelight `ty` to distance from target
  * Another lookup table + interpolation converts from distance to shooter rpm
  * Fallback speed provided if Limelight doesn't detect the target

* Vision alignment routine automatically stops driver from getting too close to the target

