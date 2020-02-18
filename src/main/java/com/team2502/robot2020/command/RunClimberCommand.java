package com.team2502.robot2020.command;

import com.team2502.robot2020.subsystem.ClimberSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class RunClimberCommand extends CommandBase {
    public final ClimberSubsystem climber;
    public final double speed;

    public RunClimberCommand(ClimberSubsystem climber, double speed) {
        this.climber = climber;
        this.speed = speed;
        addRequirements(climber);
    }

    @Override
    public void initialize() { climber.runClimber(speed); }

    @Override
    public void execute(){ climber.runClimber(speed); }

    @Override
    public void end(boolean interrupted) {
        climber.stopClimber();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}
