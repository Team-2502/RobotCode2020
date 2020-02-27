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

    public static final class OI {

        public static final int JOYSTICK_DRIVE_RIGHT = 0;
        public static final int JOYSTICK_DRIVE_LEFT = 1;
        public static final int JOYSTICK_OPERATOR = 2;

        // DRIVE_RIGHT
        public static final int BUTTON_SHIFT = 1;

        // DRIVE_LEFT
        public static final int BUTTON_VISION_ALIGN = 1;

        // OPERATOR
        public static final int BUTTON_RUN_SHOOTER_FULL = 6;
        public static final int BUTTON_RUN_SHOOTER_TRENCH = 4;
        public static final int BUTTON_RUN_SHOOTER_INIT_LINE = 7;
        public static final int BUTTON_RUN_INTAKE = 5;
        public static final int BUTTON_RUN_INTAKE_BACKWARDS = 3;
        public static final int BUTTON_HOPPER_CONTINUOUS = 1;
        public static final int BUTTON_CLIMBER = 9;
        public static final int BUTTON_CLIMBER_REVERSE = 10;
        public static final int BUTTON_HOPPER_CONTINUOUS_REVERSE = 11;
        public static final int BUTTON_CLIMBER_ACTUATE = 12;
        public static final int BUTTON_SQUEEZE_BACKWARDS = 2;
    }

    public static final class RobotMap {

        public static final class Motors {
            // Talon FX
            public static final int DRIVE_FRONT_RIGHT = 1;
            public static final int DRIVE_FRONT_LEFT = 3;
            public static final int DRIVE_BACK_RIGHT = 2;
            public static final int DRIVE_BACK_LEFT = 4;

            // Talon SRX
            public static final int CLIMBER = 5;

            // SparkMax
            public static final int SHOOTER_LEFT = 12;
            public static final int SHOOTER_RIGHT = 2;

            public static final int HOPPER_SIDE_BELTS_LEFT = 3;
            public static final int HOPPER_SIDE_BELTS_RIGHT = 4;
            public static final int HOPPER_BOTTOM_BELT = 0000000;
            public static final int HOPPER_EXIT_WHEEL = 6;

            public static final int SQUEEZE_MOTOR = 24;
            public static final int INTAKE_MOTOR = 8;
        }

        public static final class Solenoid {

            public static final int DRIVETRAIN = 0;
            public static final int CLIMBER = 1;
        }
    }

    public static final class Field {

        public static final double TARGET_HEIGHT = 83;
    }

    public static final class Robot {

        public static final class Vision {

            public static final double LIMELIGHT_HEIGHT = 18.34;
            public static final double LIMELIGHT_MOUNTING_ANGLE = 13.56;

            public static final double TURN_TOLERANCE_DEG = 1;
            public static final double TURN_RATE_TOLERANCE_DEG_PER_SEC = 5;

            public static final double FRICTION = .3;
            public static final double P = 0.006;

            public static final String LIMELIGHT_NETWORK_TABLE = "limelight-acid";
        }

        public static final class MotorSpeeds {

            public static final double INTAKE_SPEED_FORWARD = 0.5;
            public static final double INTAKE_SPEED_BACKWARDS = -0.5;
            public static final double INTAKE_SQUEEZE_SPEED_FORWARDS = 0.6;
            public static final double INTAKE_SQUEEZE_SPEED_BACKWARDS = -1;

            public static final double HOPPER_LEFT_BELT = 1;
            public static final double HOPPER_RIGHT_BELT = 0.25;
            public static final double HOPPWE_BOTTOM_BELT = 1;
            public static final double HOPPER_EXIT_WHEEL = 1;
            public static final double HOPPER_LEFT_BELT_REVERSE = -1;
            public static final double HOPPER_RIGHT_BELT_REVERSE = -0.25;
            public static final double HOPPER_BOTTOM_BELT_REVERSE = -1;
            public static final double HOPPER_EXIT_WHEEL_REVERSE = -1;

            public static final double CLIMBER_FORWARD = 1;
            public static final double CLIMBER_BACKWARD = -1;

            public static final double SHOOTER_FULL = 1;
            public static final double SHOOTER_TRENCH = 0.75;
            public static final double SHOOTER_INIT_LINE = 0.75;
        }
    }
}
