package com.team2502.robot2020.command;

import com.team2502.robot2020.subsystem.IntakeSubSystem;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class ActuateIntakeCommand extends InstantCommand {

    public final IntakeSubSystem intake;

    public ActuateIntakeCommand(IntakeSubSystem intakeSubsystem){
        intake = intakeSubsystem;
        addRequirements(intakeSubsystem);
    }

    @Override
    public void initialize(){
        if(intake.currentValueDeploy()){
            intake.retractSolenoid();
        }
        else{
            intake.deploySolenoid();
        }
    }
}
