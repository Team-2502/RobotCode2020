package com.team2502.robot2020.command;

import com.team2502.robot2020.subsystem.DrivetrainSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveCommand extends CommandBase
{
    SendableChooser<DRIVETYPE> typeEntry = new SendableChooser<DRIVETYPE>();

    DrivetrainSubsystem drivetrain;
    Joystick left_joystick;
    Joystick right_joystick;

    public DriveCommand(DrivetrainSubsystem DRIVE_TRAIN, Joystick JOYSTICK_DRIVE_LEFT, Joystick JOYSTICK_DRIVE_RIGHT)
    {
        drivetrain = DRIVE_TRAIN;
        left_joystick = JOYSTICK_DRIVE_LEFT;
        right_joystick = JOYSTICK_DRIVE_RIGHT;

        typeEntry.addOption("Split Arcade", DRIVETYPE.Arcade);
        typeEntry.addOption("Reverse", DRIVETYPE.Reverse);
        typeEntry.setDefaultOption("Tank", DRIVETYPE.Tank);
        SmartDashboard.putData("Drive Type", typeEntry);
        addRequirements(DRIVE_TRAIN);
    }

    @Override
    public void execute()
    {
        switch(typeEntry.getSelected())
        {
            case Tank:
                drivetrain.drive.tankDrive(-left_joystick.getY(), -right_joystick.getY(), true);
                break;
            case Arcade:
                drivetrain.drive.arcadeDrive(-left_joystick.getY(), right_joystick.getX(), true);
                break;
            case Reverse:
                drivetrain.drive.tankDrive(left_joystick.getY(), right_joystick.getY(), true);
        }
    }

    private enum DRIVETYPE
    {
        Tank,
        Arcade,
        Reverse
    }
}
