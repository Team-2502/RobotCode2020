/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team2502.robot2020;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    // MOST VALUES ARE FILLER VALUES AT THE CURRENT STATE

    public static final class RobotMap {
        private RobotMap() { }

        public static final class Drive {
            private Drive() { }

            public static final int DRIVE_FRONT_RIGHT = 1;
            public static final int DRIVE_FRONT_LEFT = 6;
            public static final int DRIVE_BACK_RIGHT = 2;
            public static final int DRIVE_BACK_LEFT = 5;

            // TODO set actual encoder ports
            public static final int[] kLeftEncoderPorts = new int[]{0, 1};
            public static final int[] kRightEncoderPorts = new int[]{2, 3};

            public static final boolean kLeftEncoderReversed = false;
            public static final boolean kRightEncoderReversed = true;

            // TODO set to correct cpr
            public static final int kEncoderCPR = 1024;
            // TODO set to actual wheel diameter
            public static final double kWheelDiameterMeters = 0.15;
            public static final double kEncoderDistancePerPulse =
                    // Assumes the encoders are directly mounted on the wheel shafts
                    (kWheelDiameterMeters * Math.PI) / (double) kEncoderCPR;

            // TODO set correct port
            public static final SPI.Port kNavXPort = SPI.Port.kMXP;
            public static final boolean kGyroReversed = false;

            // These are example values only - DO NOT USE THESE FOR YOUR OWN ROBOT!
            // These characterization values MUST be determined either experimentally or theoretically
            // for *your* robot's drive.
            // The Robot Characterization Toolsuite provides a convenient tool for obtaining these
            // values for your robot.
            public static final double ksVolts = 0.22;
            public static final double kvVoltSecondsPerMeter = 1.98;
            public static final double kaVoltSecondsSquaredPerMeter = 0.2;

            public static final double kTrackwidthMeters = 0.69;
            public static final DifferentialDriveKinematics kDriveKinematics =
                    new DifferentialDriveKinematics(kTrackwidthMeters);

            // Example value only - as above, this must be tuned for your drive!
            public static final double kPDriveVel = 8.5;
        }

        public static final class Auto {

            public static final double kMaxSpeedMetersPerSecond = 3;
            public static final double kMaxAccelerationMetersPerSecondSquared = 3;

            // Reasonable baseline values for a RAMSETE follower in units of meters and seconds
            public static final double kRamseteB = 2;
            public static final double kRamseteZeta = 0.7;
        }
    }
}
