package com.team2502.robot2020.command;

import com.team2502.robot2020.Constants;
import com.team2502.robot2020.subsystem.ClimberSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class RunClimberCommand extends CommandBase {
    public final ClimberSubsystem CLIMBER;

    public RunClimberCommand(ClimberSubsystem climber) {
        CLIMBER = climber;
        addRequirements(climber);
    }

    @Override
    public void initialize() {
        CLIMBER.runMotor(Constants.Robot.MotorSpeeds.CLIMBER);
    }

    @Override
    public void execute(){
        CLIMBER.runMotor(Constants.Robot.MotorSpeeds.CLIMBER);
    }

    @Override
    public void end(boolean interrupted) {
        CLIMBER.runMotor(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}
