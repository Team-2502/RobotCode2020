package com.team2502.robot2020.command;

import com.team2502.robot2020.subsystem.ClimberSubsystem;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class ActuateClimberCommand extends InstantCommand {
    public final ClimberSubsystem climber;

    public ActuateClimberCommand(ClimberSubsystem climberSubsystem){
        climber = climberSubsystem;
        addRequirements(climberSubsystem);
    }

    @Override
    public void initialize() {
        System.out.println("Climber status: " + climber.isDeployed());
        if(!climber.isDeployed()){
            climber.retractSolenoid();
            System.out.println("Climber lock: retracting");
        }
        else{
            climber.deploySolenoid();
            System.out.println("Climber lock: deploying");
        }
    }
}

