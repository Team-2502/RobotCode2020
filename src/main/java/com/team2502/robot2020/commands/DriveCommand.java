package com.team2502.robot2020.commands;

import com.team2502.robot2020.RobotContainer;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveCommand extends CommandBase
{
    ShuffleboardTab sbTab = Shuffleboard.getTab("Drive Type");

    SendableChooser<DriveType> typeEntry = new SendableChooser<>();

    public DriveCommand()
    {
        typeEntry.addOption("Tank", DriveType.Tank);
        typeEntry.addOption("Split Arcade", DriveType.Arcade);
        SmartDashboard.putData("Drive Type", typeEntry);
        addRequirements(RobotContainer.DRIVE_TRAIN);
    }

    @Override
    public void execute()
    {
        switch(typeEntry.getSelected())
        {
            case Tank:
                RobotContainer.DRIVE_TRAIN.drive.tankDrive(-RobotContainer.JOYSTICK_DRIVE_LEFT.getY(), -RobotContainer.JOYSTICK_DRIVE_RIGHT.getY(), true);
                break;
            case Arcade:
                RobotContainer.DRIVE_TRAIN.drive.arcadeDrive(-RobotContainer.JOYSTICK_DRIVE_LEFT.getY(), RobotContainer.JOYSTICK_DRIVE_RIGHT.getX(), true);
                break;
        }
    }

    private enum DriveType
    {
        Tank,
        Arcade
    }
}
