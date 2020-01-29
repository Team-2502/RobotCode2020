package com.team2502.robot2020.subsystem;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.team2502.robot2020.Constants.RobotMap.Motors;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DrivetrainSubsystem extends SubsystemBase {

    public final WPI_TalonFX DT_FRONT_LEFT;
    public final WPI_TalonFX DT_BACK_LEFT;
    public final WPI_TalonFX DT_FRONT_RIGHT;
    public final WPI_TalonFX DT_BACK_RIGHT;

    // The motors on the left side of the drive.
    public final SpeedControllerGroup leftMotors;

    // The motors on the right side of the drive.
    public final SpeedControllerGroup rightMotors;

    // Robot's drive
    public final DifferentialDrive drive;

    /**
     * Creates a new instance of the Drivetrain subsystem
     */
    public DrivetrainSubsystem() {
        DT_BACK_LEFT = new WPI_TalonFX(Motors.DRIVE_BACK_LEFT);
        DT_FRONT_LEFT = new WPI_TalonFX(Motors.DRIVE_FRONT_LEFT);
        DT_BACK_RIGHT = new WPI_TalonFX(Motors.DRIVE_BACK_RIGHT);
        DT_FRONT_RIGHT = new WPI_TalonFX(Motors.DRIVE_FRONT_RIGHT);

        leftMotors = new SpeedControllerGroup(DT_FRONT_LEFT, DT_BACK_LEFT);
        rightMotors = new SpeedControllerGroup(DT_BACK_RIGHT, DT_FRONT_RIGHT);

        rightMotors.setInverted(false);
        leftMotors.setInverted(false);

        drive = new DifferentialDrive(leftMotors, rightMotors);
    }

    @Override
    public void periodic() {
    }
}