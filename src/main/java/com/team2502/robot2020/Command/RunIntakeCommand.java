package com.team2502.robot2020.command;

import com.team2502.robot2020.subsystem.IntakeSubSystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class RunIntakeCommand extends CommandBase {
    private final double speed;
    private final IntakeSubSystem intake;

    protected boolean isDeployed;

    public RunIntakeCommand(IntakeSubSystem subsystem, double speed) {
        intake = subsystem;
        this.speed = speed;
        addRequirements(subsystem);
    }

    @Override
    public void initialize(){
      if(!intake.currentValueDeploy()) {
          end(true);
      }
    }

    @Override
    public void execute(){
        intake.runIntake(speed);
    }

    @Override
    public boolean isFinished(){
        return !intake.currentValueDeploy();
    }

}
