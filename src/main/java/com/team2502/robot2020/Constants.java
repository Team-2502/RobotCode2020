/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team2502.robot2020;

import com.team2502.robot2020.util.LookupTable;

import java.util.HashMap;

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
        public static final int BUTTON_RUN_INTAKE = 5;
        public static final int BUTTON_RUN_INTAKE_BACKWARDS = 3;

        public static final int BUTTON_HOPPER_CONTINUOUS = 1;
        public static final int BUTTON_CLIMBER = 10;
        public static final int BUTTON_CLIMBER_REVERSE = 12;
        public static final int BUTTON_HOPPER_CONTINUOUS_REVERSE = 2;
        public static final int BUTTON_CLIMBER_ACTUATE = 7;
        public static final int BUTTON_SQUEEZE_BACKWARDS = 8;

        public static final int BUTTON_CONTROL_PANEL = 9;
        public static final int BUTTON_ACTUATE_CONTROL_PANEL = 11;
    }

    public static final class RobotMap {

        public static final class Motors {
            // Talon FX
            public static final int DRIVE_FRONT_RIGHT = 1;
            public static final int DRIVE_FRONT_LEFT = 3;
            public static final int DRIVE_BACK_RIGHT = 2;
            public static final int DRIVE_BACK_LEFT = 4;

            // Talon SRX
            public static final int CLIMBER = 15;

            // SparkMax
            public static final int SHOOTER_LEFT = 12;
            public static final int SHOOTER_RIGHT = 2;

            public static final int HOPPER_SIDE_BELTS_LEFT = 3;
            public static final int HOPPER_SIDE_BELTS_RIGHT = 4;
            public static final int HOPPER_BOTTOM_BELT = 5;
            public static final int HOPPER_EXIT_WHEEL = 6;

            public static final int SQUEEZE_MOTOR = 24;
            public static final int INTAKE_MOTOR = 8;
              
            public static final int CONTROL_PANEL = 57;
             
        }

        public static final class Solenoid {

            public static final int DRIVETRAIN = 0;
            public static final int CLIMBER = 1;
            public static final int CONTROL_PANEL = 2;
        }
    }

    public static final class Field {

        public static final double TARGET_HEIGHT = 98;
    }

    public static final class Robot {

        public static final class Vision {

            public static final float LIMELIGHT_HEIGHT = 19;
            public static final float HEIGHT_OFFSET = (float)(Field.TARGET_HEIGHT - LIMELIGHT_HEIGHT);
            public static final float LIMELIGHT_MOUNTING_ANGLE = 26.5f;

            public static final double TURN_TOLERANCE_DEG = 1;
            public static final double TURN_RATE_TOLERANCE_DEG_PER_SEC = 5;

//            public static final double FRICTION_LOW = .255; // Practice bot
            public static final double FRICTION_LOW = 0.348; // Comp bot

            public static final double P_LOW = 0.015;

//            public static final double FRICTION_HIGH = .27; // Practice Bot
            public static final double FRICTION_HIGH = 0.452; // Comp Bot
            public static final double P_HIGH = 0.02;

            public static final String LIMELIGHT_NETWORK_TABLE = "limelight-orion";
        }

        public static final class MotorSpeeds {

            public static final double INTAKE_SPEED_FORWARD = .85;
            public static final double INTAKE_SPEED_BACKWARDS = -1;
            public static final double INTAKE_SQUEEZE_SPEED_FORWARDS = 0.6;
            public static final double INTAKE_SQUEEZE_SPEED_BACKWARDS = -1;

            public static final double HOPPER_LEFT_BELT = 1;
            public static final double HOPPER_RIGHT_BELT = .25;
            public static final double HOPPER_BOTTOM_BELT = 1;
            public static final double HOPPER_BOTTOM_BELT_INTAKE = 0.25;

            public static final double HOPPER_EXIT_WHEEL = 1;
            public static final double HOPPER_LEFT_BELT_REVERSE = -1;
            public static final double HOPPER_RIGHT_BELT_REVERSE = -1;
            public static final double HOPPER_BOTTOM_BELT_REVERSE = -1;
            public static final double HOPPER_EXIT_WHEEL_REVERSE = -1;

            public static final double CLIMBER_FORWARD = 1;
            public static final double CLIMBER_BACKWARD = -1;

            public static final double SHOOTER_RPM_GENERIC_CLOSE = 3840;
            public static final double SHOOTER_RPM_GENERIC_TRENCH = 3900;

            /**Nearer to the target than to the control panel**/
            public static final double SHOOTER_RPM_NEAR_TRENCH = 3850;

            /**Nearer to the control panel than to the target**/
            public static final double SHOOTER_RPM_FAR_TRENCH = 4200; // kinda high lol

            public static final double SHOOTER_RPM_FULL = 5300;

            public static final double SHOOTER_RPM_10FT = 3840;
            public static final double SHOOTER_RPM_15FT = 3828;
            public static final double SHOOTER_RPM_20FT = 3765;
            public static final double SHOOTER_RPM_21FT = 3857;
            public static final double SHOOTER_RPM_23FT = 3862;
            public static final double SHOOTER_RPM_25FT = 3950;
            public static final double SHOOTER_RPM_30FT = 4300;
            public static final double SHOOTER_RPM_35FT = 4900;

            public static final double CONTROL_PANEL = 0.6;
        }

        public static final class Auto {
            public static final boolean GYRO_REVERSED = true;
            public static final double TURN_TOLERANCE_DEG = 1;
            public static final double TURN_RATE_TOLERANCE_DEG_PER_SEC = 1;

        }
    }

    public static final class LookupTables {
        public static final LookupTable TY_TO_DIST_TABLE;

        public static final LookupTable DIST_TO_RPM_TABLE;

        static {
            HashMap<Double, Double> tyToDistMap = new HashMap<>();
            tyToDistMap.put(-5D, 15D);
            tyToDistMap.put(0D, 10D);
            tyToDistMap.put(5D, 5D);

            TY_TO_DIST_TABLE = new LookupTable(tyToDistMap);


            HashMap<Double, Double> distToRpmMap = new HashMap<>();
            distToRpmMap.put(10D, 3840D);
            distToRpmMap.put(15D, 3828D);
            distToRpmMap.put(20D, 3765D);
            distToRpmMap.put(21D, 3857D);
            distToRpmMap.put(23D, 3862D);
            distToRpmMap.put(25D, 3950D);
            distToRpmMap.put(30D, 4300D);
            distToRpmMap.put(35D, 4900D);

            DIST_TO_RPM_TABLE = new LookupTable(distToRpmMap);
        }
    }

}
