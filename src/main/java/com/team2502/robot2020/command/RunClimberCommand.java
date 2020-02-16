package com.team2502.robot2020.command;

import com.team2502.robot2020.Constants;
import com.team2502.robot2020.subsystem.ClimberSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class RunClimberCommand extends CommandBase {
    public final ClimberSubsystem CLIMBER;
    public final boolean reversed;

    public RunClimberCommand(ClimberSubsystem climber, boolean reverse) {
        CLIMBER = climber;
        reversed = reverse;
        addRequirements(climber);
    }

    @Override
    public void initialize() {
        if(reversed){
            CLIMBER.runMotor(-Constants.Robot.MotorSpeeds.CLIMBER);
        }
        else {
            CLIMBER.runMotor(Constants.Robot.MotorSpeeds.CLIMBER);
        }
    }

    @Override
    public void execute(){
        if(reversed){
            CLIMBER.runMotor(-Constants.Robot.MotorSpeeds.CLIMBER);
        }
        else {
            CLIMBER.runMotor(Constants.Robot.MotorSpeeds.CLIMBER);
        }
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
