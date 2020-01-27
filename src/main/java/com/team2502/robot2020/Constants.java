/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team2502.robot2020;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final class OI{

        public static final int JOYSTICK_DRIVE_RIGHT = 0;
        public static final int JOYSTICK_DRIVE_LEFT = 1;

        public static final int BUTTON_VISION_ALIGN = 1;
    }

    public static final class RobotMap{

        public static final class Motors {

            public static final int DRIVE_FRONT_RIGHT = 6;
            public static final int DRIVE_FRONT_LEFT = 10;
            public static final int DRIVE_BACK_RIGHT = 5;
            public static final int DRIVE_BACK_LEFT = 9;
        }
    }

    public static final class Field{

        public static final double TARGET_HEIGHT = 83;
    }

    public static final class Robot{
        public static final class Vision{
            public static final int LIMELIGHT_HEIGHT = 23;
            public static final int LIMELIGHT_MOUNTING_ANGLE = 43;

            public static final double kTurnToleranceDeg = 1;
            public static final double kTurnRateToleranceDegPerS = 5; // degrees per second
        }
    }
}
