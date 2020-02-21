package com.team2502.robot2020.commands.drive;

import com.team2502.robot2020.Constants;
import com.team2502.robot2020.RobotContainer;
import com.team2502.robot2020.subsystem.DrivetrainSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveCommand extends BaseDriveCommand
{
    SendableChooser<DriveType> typeEntry = new SendableChooser<>();

    DriveType currDriveType;

    public DriveCommand(DrivetrainSubsystem driveTrain, Joystick leftJoystick, Joystick rightJoystick)
    {
        super(driveTrain, leftJoystick, rightJoystick);

        typeEntry.addOption("Split Arcade", DriveType.Arcade);
        typeEntry.addOption("Reverse", DriveType.Reverse);
        typeEntry.setDefaultOption("Tank", DriveType.Tank);
        SmartDashboard.putData("Drive Type", typeEntry);
        addRequirements(driveTrain);
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
                driveTrain.drive.tankDrive(-leftJoystick.getY(), -rightJoystick.getY(), true);
                break;
            case Arcade:
                driveTrain.drive.arcadeDrive(-leftJoystick.getY(), rightJoystick.getX(), true);
                break;
            case Reverse:
                driveTrain.drive.tankDrive(leftJoystick.getY(), rightJoystick.getY(), true);
        }

        if(leftJoystick.getY() - rightJoystick.getY() <= Constants.Control.STRAIGHT_DRIVE_JOYSTICK_THRESH)
        {
            shouldEnd = true;
            nextCommand = NextCommand.NormalDrive;
        }
    }

    private enum DriveType
    {
        Tank,
        Arcade,
        Reverse
    }
}
