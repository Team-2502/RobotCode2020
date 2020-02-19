package com.team2502.robot2020.subsystem;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.team2502.robot2020.Constants;
import com.team2502.robot2020.Constants.RobotMap.Motors;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DrivetrainSubsystem extends SubsystemBase {

    private final WPI_TalonSRX drivetrainFrontLeft;
    private final WPI_TalonSRX drivetrainBackLeft;
    private final WPI_TalonSRX drivetrainFrontRight;
    private final WPI_TalonSRX drivetrainBackRight;
    private final Solenoid drivetrainSolenoid;

    private final DifferentialDrive drive;

    /**
     * Creates a new instance of the Drivetrain subsystem
     */
    public DrivetrainSubsystem() {
        drivetrainBackLeft = new WPI_TalonSRX(Motors.DRIVE_BACK_LEFT);
        drivetrainFrontLeft = new WPI_TalonSRX(Motors.DRIVE_FRONT_LEFT);
        drivetrainBackRight = new WPI_TalonSRX(Motors.DRIVE_BACK_RIGHT);
        drivetrainFrontRight = new WPI_TalonSRX(Motors.DRIVE_FRONT_RIGHT);

        drivetrainBackLeft.follow(drivetrainFrontLeft);
        drivetrainBackRight.follow(drivetrainFrontRight);

        drive = new DifferentialDrive(drivetrainFrontLeft, drivetrainFrontRight);

        drivetrainSolenoid = new Solenoid(Constants.RobotMap.Solenoid.DRIVETRAIN);
        drivetrainSolenoid.set(false);
    }

    public DifferentialDrive getDrive() {
        return drive;
    }

    public void enterHighGear()
    {
        drivetrainSolenoid.set(true);
    }

    public void enterLowGear() { drivetrainSolenoid.set(false); }

    public boolean isLowGear() { return drivetrainSolenoid.get(); }
}