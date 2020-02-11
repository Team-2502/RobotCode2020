package com.team2502.robot2020.commands.drive;

import com.team2502.robot2020.subsystem.DrivetrainSubsystem;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class StraightDriveCommand extends CommandBase
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

    DrivetrainSubsystem drive_train;
    Joystick left_joystick;
    Joystick right_joystick;

    public StraightDriveCommand(DrivetrainSubsystem DRIVE_TRAIN, Joystick JOYSTICK_DRIVE_LEFT, Joystick JOYSTICK_DRIVE_RIGHT)
    {
        drive_train = DRIVE_TRAIN;
        left_joystick = JOYSTICK_DRIVE_LEFT;
        right_joystick = JOYSTICK_DRIVE_RIGHT;

        pidCRight.disableContinuousInput();
        pidCLeft.disableContinuousInput();
        addRequirements(drive_train);
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
        drive_train.drive.tankDrive(pidCLeft.calculate(drive_train.DT_FRONT_LEFT));
    }
}
