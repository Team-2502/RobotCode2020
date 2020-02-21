package com.team2502.robot2020.commands.drive;

import com.team2502.robot2020.Constants;
import com.team2502.robot2020.RobotContainer;
import com.team2502.robot2020.subsystem.DrivetrainSubsystem;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class StraightDriveCommand extends BaseDriveCommand
{
    PIDController pidCRight = new PIDController(0,0,0);
    PIDController pidCLeft = new PIDController(0,0,0);

    ShuffleboardTab shuffleboard = Shuffleboard.getTab("PID Drive");
    NetworkTableEntry KpEntryRight = shuffleboard.add("PID Drive Right: Kp", 0D).getEntry();
    NetworkTableEntry KiEntryRight = shuffleboard.add("PID Drive Right: Ki", 0D).getEntry();
    NetworkTableEntry KdEntryRight = shuffleboard.add("PID Drive Right: Kd", 0D).getEntry();
    NetworkTableEntry KpEntryLeft = shuffleboard.add("PID Drive Left: Kp", 0D).getEntry();
    NetworkTableEntry KiEntryLeft = shuffleboard.add("PID Drive Left: Ki", 0D).getEntry();
    NetworkTableEntry KdEntryLeft = shuffleboard.add("PID Drive Left: Kd", 0D).getEntry();

    public StraightDriveCommand(DrivetrainSubsystem driveTrain, Joystick leftJoystick, Joystick rightJoystick)
    {
        super(driveTrain, leftJoystick, rightJoystick);

        pidCRight.disableContinuousInput();
        pidCLeft.disableContinuousInput();
        addRequirements(driveTrain);
    }

    @Override
    public void initialize()
    {
        pidCRight.setPID(KpEntryRight.getDouble(0D),
                KiEntryRight.getDouble(0D),
                KdEntryRight.getDouble(0D));
        pidCLeft.setPID(KpEntryLeft.getDouble(0D),
                KiEntryLeft.getDouble(0D),
                KdEntryLeft.getDouble(0D));
    }

    @Override
    public void execute()
    {
        double outputAvg = (driveTrain.DT_FRONT_LEFT.get() + driveTrain.DT_FRONT_RIGHT.get()) / 2D;

        if(RobotContainer.SHIFTING_SOLENOID.isLowGear())
            driveTrain.drive.tankDrive(
                    pidCLeft.calculate(driveTrain.getLeftVelocity() * Constants.Physical.MOTOR_RPM_TO_INCHES_PER_SEC_LOW,outputAvg * Constants.Physical.MAX_SPEED_LOW),
                    pidCRight.calculate(driveTrain.getRightVelocity() * Constants.Physical.MOTOR_RPM_TO_INCHES_PER_SEC_LOW,outputAvg * Constants.Physical.MAX_SPEED_LOW));
        else
            driveTrain.drive.tankDrive(
                    pidCLeft.calculate(driveTrain.getLeftVelocity() * Constants.Physical.MOTOR_RPM_TO_INCHES_PER_SEC_HIGH,outputAvg * Constants.Physical.MAX_SPEED_HIGH),
                    pidCRight.calculate(driveTrain.getRightVelocity() * Constants.Physical.MOTOR_RPM_TO_INCHES_PER_SEC_HIGH,outputAvg * Constants.Physical.MAX_SPEED_HIGH));

        if(leftJoystick.getY() - rightJoystick.getY() > Constants.Control.STRAIGHT_DRIVE_JOYSTICK_THRESH)
        {
            shouldEnd = true;
            nextCommand = NextCommand.NormalDrive;
        }
    }
}
