package com.team2502.robot2020.commands.drive;

import com.team2502.robot2020.RobotContainer;
import com.team2502.robot2020.subsystem.DrivetrainSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class BaseDriveCommand extends CommandBase
{
    protected DrivetrainSubsystem driveTrain;
    protected Joystick leftJoystick;
    protected Joystick rightJoystick;

    protected NextCommand nextCommand = NextCommand.NormalDrive;

    protected boolean shouldEnd = false;

    public BaseDriveCommand(DrivetrainSubsystem driveTrain, Joystick leftJoystick, Joystick rightJoystick)
    {
        this.driveTrain = driveTrain;
        this.leftJoystick = leftJoystick;
        this.rightJoystick = rightJoystick;

        addRequirements(driveTrain);
    }

    @Override
    public void end(boolean interrupted)
    {
        switch(nextCommand)
        {
            case NormalDrive:
                new DriveCommand(RobotContainer.DRIVE_TRAIN, RobotContainer.JOYSTICK_DRIVE_LEFT, RobotContainer.JOYSTICK_DRIVE_RIGHT);
            case StraightDrive:
                new StraightDriveCommand(RobotContainer.DRIVE_TRAIN, RobotContainer.JOYSTICK_DRIVE_LEFT, RobotContainer.JOYSTICK_DRIVE_RIGHT);
        }

    }

    @Override
    public boolean isFinished()
    {
        return shouldEnd;
    }

    protected enum NextCommand
    {
        NormalDrive,
        StraightDrive
    }
}
