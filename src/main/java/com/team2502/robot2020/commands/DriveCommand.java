package com.team2502.robot2020.commands;

import com.team2502.robot2020.RobotContainer;
import com.team2502.robot2020.subsystem.DrivetrainSubsystem;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveCommand extends CommandBase
{
    ShuffleboardTab sbTab = Shuffleboard.getTab("Drive Type");

    SendableChooser<DriveType> typeEntry = new SendableChooser<DriveType>();

    DrivetrainSubsystem dt;
    Joystick left_joystick;
    Joystick right_joystick;

    public DriveCommand(DrivetrainSubsystem DRIVE_TRAIN, Joystick left, Joystick right)
    {
        dt = DRIVE_TRAIN;
        left_joystick = left;
        right_joystick = right;

        typeEntry.addOption("Split Arcade", DriveType.Arcade);
        typeEntry.setDefaultOption("Tank", DriveType.Tank);
        SmartDashboard.putData("Drive Type", typeEntry);
        addRequirements(DRIVE_TRAIN);
    }

    @Override
    public void execute()
    {
        switch(typeEntry.getSelected())
        {
            case Tank:
                dt.drive.tankDrive(-left_joystick.getY(), -right_joystick.getY(), true);
                break;
            case Arcade:
                dt.drive.arcadeDrive(-left_joystick.getY(), right_joystick.getX(), true);
                break;
        }
    }

    private enum DriveType
    {
        Tank,
        Arcade
    }
}
