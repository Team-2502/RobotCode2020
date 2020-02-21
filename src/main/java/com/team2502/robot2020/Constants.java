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

        public static final int BUTTON_SHIFT = 1;
    }

    public static final class RobotMap{

        public static final class Motors {

            public static final int DRIVE_FRONT_RIGHT = 1;
            public static final int DRIVE_FRONT_LEFT = 3;
            public static final int DRIVE_BACK_RIGHT = 2;
            public static final int DRIVE_BACK_LEFT = 4;
        }

        public static final class Solenoid
        {
            public static final int TRANSMISSION = 0;
        }
    }

    public static final class Physical
    {
        public static final double COUNTS_PER_FALCON_REV = 2048.0;
        public static final double MOTOR_TO_OUTPUT_RATIO_HIGH = (11.0/42.0)*(24.0/50.0); // High gear ratio in dt gearbox
        public static final double MOTOR_TO_OUTPUT_RATIO_LOW = (11.0/42.0)*(14.0/60.0); // Low gear ratio in dt gearbox

        public static final double WHEEL_DIAMETER_INCHES = 6.0;

        /**
         * Multiply your native RPM from the motor by this conversion factor to get it into <b>inches per second</b>.
         */
        public static final double MOTOR_RPM_TO_INCHES_PER_SEC_HIGH =
                (MOTOR_TO_OUTPUT_RATIO_HIGH * Math.PI * WHEEL_DIAMETER_INCHES) / 60.0; // in/min to in/s

        /**
         * Multiply your native RPM from the motor by this conversion factor to get it into <b>inches per second</b>.
         */
        public static final double MOTOR_RPM_TO_INCHES_PER_SEC_LOW =
                (MOTOR_TO_OUTPUT_RATIO_LOW * Math.PI * WHEEL_DIAMETER_INCHES) / 60.0; // in/min to in/s

        //TODO Find values
        public static final double MAX_SPEED_HIGH = 0.0D;
        public static final double MAX_SPEED_LOW = 0.0D;
    }

    public static final class Control
    {
        public static final double STRAIGHT_DRIVE_JOYSTICK_THRESH = 0.05;
    }
}
