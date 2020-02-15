package com.team2502.robot2020.command;

import com.team2502.robot2020.subsystem.HopperSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class RunHopperContinuously extends CommandBase {

    private final HopperSubsystem hopper;

    public RunHopperContinuously(HopperSubsystem hopper_sub){
        hopper = hopper_sub;
        addRequirements(hopper_sub);
    }

    @Override
    public void initialize(){
        hopper.RunSideBelts(1);
        hopper.RunBottomBelts(1);
        hopper.RunExitWheel(1);
    }

    @Override
    public void execute(){

    }

    @Override
    public void end(boolean interrupted){
        hopper.RunSideBelts(0);
        hopper.RunBottomBelts(0);
        hopper.RunExitWheel(0);
    }

    @Override
    public boolean isFinished(){
        return false;
    }
}
