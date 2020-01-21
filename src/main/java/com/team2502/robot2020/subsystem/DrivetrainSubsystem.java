package com.team2502.robot2020.subsystem;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import com.team2502.robot2020.Constants;
import com.team2502.robot2020.Constants.RobotMap.Drive;
import com.team2502.robot2020.RobotContainer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DrivetrainSubsystem extends SubsystemBase {

    public final WPI_TalonSRX DT_FRONT_LEFT;
    public final WPI_TalonSRX DT_BACK_LEFT_ENC;
    public final WPI_TalonSRX DT_FRONT_RIGHT_ENC;
    public final WPI_TalonSRX DT_BACK_RIGHT;

    // The motors on the left side of the drive.
    public final SpeedControllerGroup leftMotors;

    // The motors on the right side of the drive.
    public final SpeedControllerGroup rightMotors;

    // Robot's drive
    public final DifferentialDrive drive;



    // Left side encoders
//    private final Encoder leftEncoder = new Encoder(
//            Drive.kLeftEncoderPorts[0],
//            Drive.kLeftEncoderPorts[1],
//            Drive.kLeftEncoderReversed
//    );
//
//    // Left side encoders
//    private final Encoder rightEncoder = new Encoder(
//            Drive.kRightEncoderPorts[0],
//            Drive.kRightEncoderPorts[1],
//            Drive.kRightEncoderReversed
//    );

    // Odometry class for tracking robot pose
    private final DifferentialDriveOdometry odometry;

    // Navx
    private final AHRS navX = new AHRS(Drive.kNavXPort);

    /**
     * Creates a new instance of the Drivetrain subsystem
     */
    public DrivetrainSubsystem() {
        // Set the pulse of each encoder
        //this.leftEncoder.setDistancePerPulse(Drive.kEncoderDistancePerPulse);
        //this.rightEncoder.setDistancePerPulse(Drive.kEncoderDistancePerPulse);

        resetEncoders();
        this.odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getHeading()));

        DT_BACK_LEFT_ENC = new WPI_TalonSRX(Drive.DRIVE_BACK_LEFT);
        DT_FRONT_LEFT = new WPI_TalonSRX(Drive.DRIVE_FRONT_LEFT);
        DT_BACK_RIGHT = new WPI_TalonSRX(Drive.DRIVE_BACK_RIGHT);
        DT_FRONT_RIGHT_ENC = new WPI_TalonSRX(Drive.DRIVE_FRONT_RIGHT);

        leftMotors = new SpeedControllerGroup(DT_FRONT_LEFT, DT_BACK_LEFT_ENC);
        rightMotors = new SpeedControllerGroup(DT_BACK_RIGHT, DT_FRONT_RIGHT_ENC);

        drive = new DifferentialDrive(leftMotors, rightMotors);

        DT_FRONT_RIGHT_ENC.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
        DT_BACK_LEFT_ENC.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);



    }

    @Override
    public void periodic() {
        //odometry.update(Rotation2d.fromDegrees(getHeading()), leftEncoder.getDistance(), rightEncoder.getDistance());
    }

    /**
     * Returns the currently-estimated pose of the robot.
     *
     * @return The pose.
     */
    public Pose2d getPose() {
        return odometry.getPoseMeters();
    }



    /**
     * Resets the odometry to the specified pose.
     *
     * @param pose The pose to which to set the odometry.
     */
    public void resetOdometry(Pose2d pose) {
        resetEncoders();
        odometry.resetPosition(pose, Rotation2d.fromDegrees(getHeading()));
    }

    /**
     * Controls the left and right sides of the drive directly with voltages.
     *
     * @param leftVolts  the commanded left output
     * @param rightVolts the commanded right output
     */
    public void tankDriveVolts(double leftVolts, double rightVolts) {
        leftMotors.setVoltage(leftVolts);
        rightMotors.setVoltage(-rightVolts);
    }

    /**
     * Resets encoders
     */
    public void resetEncoders() {
        //leftEncoder.reset();
        //rightEncoder.reset();
    }



    /**
     * Sets the max output of the drive.  Useful for scaling the drive to drive more slowly.
     *
     * @param maxOutput the maximum output to which the drive will be constrained
     */
    public void setMaxOutput(double maxOutput) {
        drive.setMaxOutput(maxOutput);
    }

    /**
     * Returns the heading of the robot.
     *
     * @return the robot's heading in degrees, from 180 to 180
     */
    public double getHeading() {
        return Math.IEEEremainder(navX.getAngle(), 360) * (Drive.kGyroReversed ? -1 : 1);
    }

    /**
     * Returns the turn rate of the robot.
     *
     * @return The turn rate of the robot, in degrees per second
     */
    public double getTurnRate() {
        return navX.getRate() * (Drive.kGyroReversed ? -1 : 1);
    }
}