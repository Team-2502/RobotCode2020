package com.team2502.robot2020.command;

import com.team2502.robot2020.subsystem.DrivetrainSubsystem;

import edu.wpi.first.wpilibj2.command.InstantCommand;

public class ActuateDrivetrainCommand extends InstantCommand {

    private final DrivetrainSubsystem drivetrain;

    public ActuateDrivetrainCommand(DrivetrainSubsystem drivetrain)
    {
        this.drivetrain = drivetrain;
        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
        if(drivetrain.isHighGear()){
            drivetrain.enterLowGear();
        }
        else{
            drivetrain.enterHighGear();
        }
    }
}
