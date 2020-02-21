package com.team2502.robot2020.commands.drive;

import com.team2502.robot2020.Constants;
import com.team2502.robot2020.RobotContainer;
import com.team2502.robot2020.subsystem.DrivetrainSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveCommand extends CommandBase
{
    SendableChooser<DRIVETYPE> typeEntry = new SendableChooser<>();

    DrivetrainSubsystem drive_train;
    Joystick left_joystick;
    Joystick right_joystick;

    DRIVETYPE currDriveType;

    public DriveCommand(DrivetrainSubsystem DRIVE_TRAIN, Joystick JOYSTICK_DRIVE_LEFT, Joystick JOYSTICK_DRIVE_RIGHT)
    {
        drive_train = DRIVE_TRAIN;
        left_joystick = JOYSTICK_DRIVE_LEFT;
        right_joystick = JOYSTICK_DRIVE_RIGHT;

        typeEntry.addOption("Split Arcade", DRIVETYPE.Arcade);
        typeEntry.addOption("Reverse", DRIVETYPE.Reverse);
        typeEntry.setDefaultOption("Tank", DRIVETYPE.Tank);
        SmartDashboard.putData("Drive Type", typeEntry);
        addRequirements(DRIVE_TRAIN);
    }

    @Override
    public void initialize()
    {
        currDriveType = typeEntry.getSelected();
    }

    @Override
    public void execute()
    {
        switch(currDriveType)
        {
            case Tank:
                drive_train.drive.tankDrive(-left_joystick.getY(), -right_joystick.getY(), true);
                break;
            case Arcade:
                drive_train.drive.arcadeDrive(-left_joystick.getY(), right_joystick.getX(), true);
                break;
            case Reverse:
                drive_train.drive.tankDrive(left_joystick.getY(), right_joystick.getY(), true);
        }
    }

    @Override
    public void end(boolean interrupted)
    {
        new StraightDriveCommand(RobotContainer.DRIVE_TRAIN, RobotContainer.JOYSTICK_DRIVE_LEFT, RobotContainer.JOYSTICK_DRIVE_RIGHT);
    }

    @Override
    public boolean isFinished()
    {
        return left_joystick.getY() - right_joystick.getY() <= Constants.Control.STRAIGHT_DRIVE_JOYSTICK_THRESH;
    }

    private enum DRIVETYPE
    {
        Tank,
        Arcade,
        Reverse
    }
}
