package com.team2502.robot2020.subsystem;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import com.team2502.robot2020.Constants.RobotMap.Drive;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DrivetrainSubsytem extends SubsystemBase {

    // The motors on the left side of the drive.
    private final SpeedControllerGroup leftMotors = new SpeedControllerGroup(
            new WPI_TalonSRX(Drive.DRIVE_FRONT_LEFT),
            new WPI_TalonSRX(Drive.DRIVE_BACK_LEFT)
    );

    // The motors on the right side of the drive.
    private final SpeedControllerGroup rightMotors = new SpeedControllerGroup(
            new WPI_TalonSRX(Drive.DRIVE_FRONT_RIGHT),
            new WPI_TalonSRX(Drive.DRIVE_BACK_RIGHT)
    );

    // Robot's drive
    private final DifferentialDrive drive = new DifferentialDrive(leftMotors, rightMotors);

    // Left side encoders
    private final Encoder leftEncoder = new Encoder(
            Drive.kLeftEncoderPorts[0],
            Drive.kLeftEncoderPorts[1],
            Drive.kLeftEncoderReversed
    );

    // Left side encoders
    private final Encoder rightEncoder = new Encoder(
            Drive.kRightEncoderPorts[0],
            Drive.kRightEncoderPorts[1],
            Drive.kRightEncoderReversed
    );

    // Odometry class for tracking robot pose
    private final DifferentialDriveOdometry odometry;

    // Navx
    private final AHRS navX = new AHRS(Drive.kNavXPort);

    /**
     * Creates a new instance of the Drivetrain subsystem
     */
    public DrivetrainSubsytem() {
        // Set the pulse of each encoder
        this.leftEncoder.setDistancePerPulse(Drive.kEncoderDistancePerPulse);
        this.rightEncoder.setDistancePerPulse(Drive.kEncoderDistancePerPulse);

        resetEncoders();
        this.odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getHeading()));
    }

    @Override
    public void periodic() {
        odometry.update(Rotation2d.fromDegrees(getHeading()), leftEncoder.getDistance(), rightEncoder.getDistance());
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
     * Returns the current wheel speeds of the robot.
     *
     * @return The current wheel speeds.
     */
    public DifferentialDriveWheelSpeeds getWheelSpeeds() {
        return new DifferentialDriveWheelSpeeds(leftEncoder.getRate(), rightEncoder.getRate());
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
        leftEncoder.reset();
        rightEncoder.reset();
    }

    /**
     * Gets the average distance of the two encoders.
     *
     * @return the average of the two encoder readings
     */
    public double getAverageEncoderDistance() {
        return (leftEncoder.getDistance() + rightEncoder.getDistance()) / 2.0;
    }

    /**
     * Gets the left drive encoder.
     *
     * @return the left drive encoder
     */
    public Encoder getLeftEncoder() {
        return leftEncoder;
    }

    /**
     * Gets the right drive encoder.
     *
     * @return the right drive encoder
     */
    public Encoder getRightEncoder() {
        return rightEncoder;
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