package com.team2502.robot2020.command;

import com.team2502.robot2020.subsystem.ClimberSubsystem;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class ActuateClimberLockCommand extends InstantCommand {
    private final ClimberSubsystem climber;

    public ActuateClimberLockCommand(ClimberSubsystem climberSubsystem){
        climber = climberSubsystem;
        addRequirements(climberSubsystem);
    }

    @Override
    public void initialize() {
        if(climber.isRetracted() && DriverStation.getInstance().getMatchTime() < 35){
            climber.deploySolenoid();
        }
        else{
            climber.retractSolenoid();
        }
    }
}

