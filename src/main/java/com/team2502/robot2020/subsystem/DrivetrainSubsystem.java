package com.team2502.robot2020.subsystem;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.kauailabs.navx.frc.AHRS;
import com.team2502.robot2020.Constants;
import com.team2502.robot2020.Constants.RobotMap.Motors;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
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

    private final DifferentialDrive drive;

    private final DifferentialDriveOdometry odometry;

    private final AHRS navX;

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
        drivetrainBackLeft.configAllSettings(configs);
        drivetrainBackRight.configAllSettings(configs);

        drivetrainFrontRight.setInverted(TalonFXInvertType.CounterClockwise);
        drivetrainBackRight.setInverted(TalonFXInvertType.CounterClockwise);
        drivetrainFrontLeft.setInverted(TalonFXInvertType.CounterClockwise);
        drivetrainBackLeft.setInverted(TalonFXInvertType.CounterClockwise);

        drivetrainFrontRight.setNeutralMode(NeutralMode.Coast);
        drivetrainFrontLeft.setNeutralMode(NeutralMode.Coast);
        drivetrainBackLeft.setNeutralMode(NeutralMode.Coast);
        drivetrainBackRight.setNeutralMode(NeutralMode.Coast);

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
        odometry.update(Rotation2d.fromDegrees(getHeading()), getEncoderPosition(drivetrainFrontLeft, isHighGear()), getEncoderPosition(drivetrainFrontRight, isHighGear()));

        updateSmartDashboard();
    }

    public void tankDriveVoltage(double leftVolts, double rightVolts) {
        drivetrainFrontLeft.setVoltage(leftVolts);
        drivetrainFrontLeft.setVoltage(rightVolts);
    }

    public DifferentialDrive getDrive() {
        return drive;
    }

    public double getHeading() { return Math.IEEEremainder(navX.getAngle(), 360) * (Constants.Robot.Auto.GYRO_REVERSED ? -1 : 1); }

    public double getTurnRate() { return navX.getRate() * (Constants.Robot.Auto.GYRO_REVERSED ? -1 : 1); }

    public Pose2d getPose() { return odometry.getPoseMeters(); }

    public double getEncoderPosition(WPI_TalonFX talon, boolean highGear){
        if(highGear){
            return talon.getSelectedSensorPosition() * Auto.ENCODER_DPP_HIGH;
        }
        else{
            return talon.getSelectedSensorPosition() * Auto.ENCODER_DPP_LOW;
        }

    }

    public double getEncoderSpeed(WPI_TalonFX talon, boolean highGear){
        if(highGear){
            return talon.getSelectedSensorVelocity() * 10 * Auto.ENCODER_DPP_HIGH;
        }
        else {
            return talon.getSelectedSensorVelocity() * 10 * Auto.ENCODER_DPP_LOW;
        }
    }

    public DifferentialDriveWheelSpeeds getWheelSpeeds(){ return new DifferentialDriveWheelSpeeds(getEncoderSpeed(drivetrainFrontLeft, isHighGear()),getEncoderSpeed(drivetrainFrontRight, isHighGear())); }

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
        SmartDashboard.putNumber("Left Front Position", getEncoderPosition(drivetrainFrontLeft, isHighGear()));
        SmartDashboard.putNumber("Right Front Position", getEncoderPosition(drivetrainFrontRight, isHighGear()));
        SmartDashboard.putNumber("Left Back Position", getEncoderPosition(drivetrainBackLeft, isHighGear()));
        SmartDashboard.putNumber("Right Back Position", getEncoderPosition(drivetrainBackRight, isHighGear()));

        SmartDashboard.putNumber("Pose Angle", getHeading());

        SmartDashboard.putNumber("Right Front Velocity", getEncoderSpeed(drivetrainFrontRight, isHighGear()));
        SmartDashboard.putNumber("Left Front Velocity", getEncoderSpeed(drivetrainFrontLeft, isHighGear()));
        SmartDashboard.putNumber("Right Back Velocity", getEncoderSpeed(drivetrainBackRight, isHighGear()));
        SmartDashboard.putNumber("Left Back Velocity", getEncoderSpeed(drivetrainBackLeft, isHighGear()));

        SmartDashboard.putBoolean("High Gear", isHighGear());
    }
}