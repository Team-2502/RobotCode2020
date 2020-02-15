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
        public static final int JOYSTICK_OPERATOR = 2;

        public static final int BUTTON_SHIFT = 1;
        public static final int BUTTON_DEPLOY_INTAKE = 1;
        public static final int BUTTON_RETRACT_INTAKE = 1;
        public static final int BUTTON_RUN_INTAKE = 1;
        public static final int BUTTON_RUN_INTAKE_BACKWARDS = 1;
        public static final int BUTTON_HOPPER_CONTINUOUS = 1;
        public static final int RUN_SHOOTER = 1;
        public static final int BUTTON_VISION_ALIGN = 1;
        public static final int BUTTON_CLIMBER = 0;
    }

    public static final class RobotMap{

        public static final class Motors {

            public static final int DRIVE_FRONT_RIGHT = 1;
            public static final int DRIVE_FRONT_LEFT = 3;
            public static final int DRIVE_BACK_RIGHT = 2;
            public static final int DRIVE_BACK_LEFT = 4;

            public static final int HOPPER_SIDE_BELTS = 0;
            public static final int HOPPER_BOTTOM_BELTS = 0;
            public static final int HOPPER_EXIT_WHEEL = 0;

            public static final int SHOOTER_LEFT = 1;
            public static final int SHOOTER_Right = 2;

            public static final int CLIMBER_MOTOR = 0;

            public static final int SQUEEZE_MOTOR = 0;
            public static final int INTAKE_MOTOR = 0;
        }

        public static final class Solenoid
        {
            public static final int TRANSMISSION = 0;
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
