package com.team2502.robot2020.subsystem;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import com.team2502.robot2020.Constants;
import com.team2502.robot2020.Constants.RobotMap.Motors;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static com.team2502.robot2020.Constants.Robot.Auto;

public class DrivetrainSubsystem extends SubsystemBase {

    private final WPI_TalonFX drivetrainFrontLeft;
    private final WPI_TalonFX drivetrainBackLeft;
    private final WPI_TalonFX drivetrainFrontRight;
    private final WPI_TalonFX drivetrainBackRight;
    private final Solenoid drivetrainSolenoid;

    private final AHRS navX;

    private final DifferentialDrive drive;

    private final DifferentialDriveOdometry odometry;

    /**
     * Creates a new instance of the Drivetrain subsystem
     */
    public DrivetrainSubsystem() {
        drivetrainBackLeft = new WPI_TalonFX(Motors.DRIVE_BACK_LEFT);
        drivetrainFrontLeft = new WPI_TalonFX(Motors.DRIVE_FRONT_LEFT);
        drivetrainBackRight = new WPI_TalonFX(Motors.DRIVE_BACK_RIGHT);
        drivetrainFrontRight = new WPI_TalonFX(Motors.DRIVE_FRONT_RIGHT);

        drivetrainBackLeft.follow(drivetrainFrontLeft);
        drivetrainBackRight.follow(drivetrainFrontRight);

        TalonFXConfiguration configs = new TalonFXConfiguration();
        configs.primaryPID.selectedFeedbackSensor = FeedbackDevice.IntegratedSensor;

        drivetrainFrontRight.configAllSettings(configs);
        drivetrainFrontLeft.configAllSettings(configs);

        drivetrainFrontRight.setInverted(TalonFXInvertType.CounterClockwise);
        drivetrainFrontLeft.setInverted(TalonFXInvertType.CounterClockwise);

        drivetrainFrontRight.setNeutralMode(NeutralMode.Coast);
        drivetrainFrontLeft.setNeutralMode(NeutralMode.Coast);

        resetEncoders();

        drive = new DifferentialDrive(drivetrainFrontLeft, drivetrainFrontRight);

        navX = new AHRS(SPI.Port.kMXP);
        zeroHeading();

        odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getHeading()));

        drivetrainSolenoid = new Solenoid(Constants.RobotMap.Solenoid.DRIVETRAIN);
        drivetrainSolenoid.set(false);
    }

    @Override
    public void periodic() {
        odometry.update(Rotation2d.fromDegrees(getHeading()), getLeftEncoderPosition(drivetrainFrontLeft, isHighGear()), getRightEncoderPosition(drivetrainFrontRight, isHighGear()));

        updateSmartDashboard();
    }

    public void tankDriveVoltage(double leftVolts, double rightVolts) {
        drivetrainFrontLeft.set(ControlMode.PercentOutput, leftVolts/12);
        drivetrainFrontLeft.set(ControlMode.PercentOutput, rightVolts/12);
    }

    public DifferentialDrive getDrive() {
        return drive;
    }

    public double getHeading() { return Math.IEEEremainder(navX.getAngle(), 360) * (Constants.Robot.Auto.GYRO_REVERSED ? -1 : 1); }

    public double getTurnRate() { return navX.getRate() * (Constants.Robot.Auto.GYRO_REVERSED ? -1 : 1); }

    public Pose2d getPose() { return odometry.getPoseMeters(); }

    /**
     *
     * @param talon the talon
     * @param highGear if we are in high gear
     * @return meters
     */
    public double getLeftEncoderPosition(WPI_TalonFX talon, boolean highGear){
        if(highGear){
            return talon.getSelectedSensorPosition() * Auto.ENCODER_DPP_HIGH;
        }
        else{
            return talon.getSelectedSensorPosition() * Auto.ENCODER_DPP_LOW;
        }
    }

    public double getRightEncoderPosition(WPI_TalonFX talon, boolean highGear){
        if(highGear){
            return -talon.getSelectedSensorPosition() * Auto.ENCODER_DPP_HIGH;
        }
        else{
            return -talon.getSelectedSensorPosition() * Auto.ENCODER_DPP_LOW;
        }
    }

    public double getLeftEncoderSpeed(WPI_TalonFX talon, boolean highGear){
        if(highGear){
            return talon.getSelectedSensorVelocity() * 10 * Auto.ENCODER_DPP_HIGH;
        }
        else {
            return talon.getSelectedSensorVelocity() * 10 * Auto.ENCODER_DPP_LOW;
        }
    }

    public double getRightEncoderSpeed(WPI_TalonFX talon, boolean highGear){
        if(highGear){
            return -talon.getSelectedSensorVelocity() * 10 * Auto.ENCODER_DPP_HIGH;
        }
        else {
            return -talon.getSelectedSensorVelocity() * 10 * Auto.ENCODER_DPP_LOW;
        }
    }

    public DifferentialDriveWheelSpeeds getWheelSpeeds(){ return new DifferentialDriveWheelSpeeds(getLeftEncoderSpeed(drivetrainFrontLeft, isHighGear()),getRightEncoderSpeed(drivetrainFrontRight, isHighGear())); }

    public void resetEncoders() {
        drivetrainFrontRight.setSelectedSensorPosition(0);
        drivetrainFrontLeft.setSelectedSensorPosition(0);
        drivetrainBackLeft.setSelectedSensorPosition(0);
        drivetrainBackRight.setSelectedSensorPosition(0);
    }

    public void zeroHeading(){ navX.reset(); }

    public void enterHighGear() { drivetrainSolenoid.set(true); }

    public void enterLowGear() { drivetrainSolenoid.set(false); }

    public boolean isHighGear() { return drivetrainSolenoid.get(); }

    public void updateSmartDashboard() {
        SmartDashboard.putNumber("Left Front Position", getLeftEncoderPosition(drivetrainFrontLeft, isHighGear()));
        SmartDashboard.putNumber("Right Front Position", getRightEncoderPosition(drivetrainFrontRight, isHighGear()));
        SmartDashboard.putNumber("Left Back Position", getLeftEncoderPosition(drivetrainBackLeft, isHighGear()));
        SmartDashboard.putNumber("Right Back Position", getRightEncoderPosition(drivetrainBackRight, isHighGear()));

        SmartDashboard.putNumber("Left Front Enc Ticks", drivetrainFrontLeft.getSelectedSensorPosition());
        SmartDashboard.putNumber("Right Front Enc Ticks", drivetrainFrontRight.getSelectedSensorPosition());
        SmartDashboard.putNumber("Pose Angle", getHeading());
        SmartDashboard.putString("Estimated Pose", getPose().toString());

        SmartDashboard.putNumber("Right Front Velocity", getRightEncoderSpeed(drivetrainFrontRight, isHighGear()));
        SmartDashboard.putNumber("Left Front Velocity", getLeftEncoderSpeed(drivetrainFrontLeft, isHighGear()));
        SmartDashboard.putNumber("Right Back Velocity", getRightEncoderSpeed(drivetrainBackRight, isHighGear()));
        SmartDashboard.putNumber("Left Back Velocity", getLeftEncoderSpeed(drivetrainBackLeft, isHighGear()));

        SmartDashboard.putBoolean("High Gear", isHighGear());
    }

    public void resetNavX(){
        navX.reset();
    }

    public void resetLocEst() {
        resetEncoders();
        resetNavX();
        odometry.resetPosition(new Pose2d(new Translation2d(0, 0), new Rotation2d(0)),new Rotation2d(0));
    }
}