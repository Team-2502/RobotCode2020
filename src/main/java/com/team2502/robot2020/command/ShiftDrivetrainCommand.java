package com.team2502.robot2020.command;

import com.team2502.robot2020.subsystem.DrivetrainSubsystem;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class ShiftDrivetrainCommand extends InstantCommand {

    DrivetrainSubsystem drivetrain;

    public ShiftDrivetrainCommand(DrivetrainSubsystem drivetrain)
    {
        this.drivetrain = drivetrain;
        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
        if(drivetrain.isLowGear()){
            drivetrain.enterHighGear();
        }
        else{
            drivetrain.enterLowGear();
        }
    }
}
