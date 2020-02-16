package com.team2502.robot2020.command;

import com.team2502.robot2020.Constants;
import com.team2502.robot2020.subsystem.HopperSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class RunHopperContinuouslyCommand extends CommandBase {

    private final HopperSubsystem hopper;
    private final boolean reverse;

    public RunHopperContinuouslyCommand(HopperSubsystem hopper_sub, boolean reversed){
        reverse = reversed;
        hopper = hopper_sub;
        addRequirements(hopper_sub);
    }

    @Override
    public void initialize(){

    }

    @Override
    public void execute(){
        if(!reverse) {
            hopper.RunSideBelts(Constants.Robot.MotorSpeeds.HOPPER_SIDE_BELTS);
            hopper.RunBottomBelt(Constants.Robot.MotorSpeeds.HOPPER_BOTTOM_BELT);
            hopper.RunExitWheel(Constants.Robot.MotorSpeeds.HOPPER_EXIT_WHEEL);
        }
        else{
            hopper.RunSideBelts(-Constants.Robot.MotorSpeeds.HOPPER_SIDE_BELTS);
            hopper.RunBottomBelt(-Constants.Robot.MotorSpeeds.HOPPER_BOTTOM_BELT);
            hopper.RunExitWheel(-Constants.Robot.MotorSpeeds.HOPPER_EXIT_WHEEL);
        }
    }

    @Override
    public void end(boolean interrupted){
        hopper.RunSideBelts(0);
        hopper.RunBottomBelt(0);
        hopper.RunExitWheel(0);
    }

    @Override
    public boolean isFinished(){
        return false;
    }
}
