package com.team2502.robot2020.command;

import com.team2502.robot2020.subsystem.IntakeSubSystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ReverseJuicerCommand extends CommandBase {
    IntakeSubSystem intake;

    public ReverseJuicerCommand(IntakeSubSystem intake){
        this.intake = intake;
    }

    @Override
    public void initialize(){
        intake.runJuicer(-1);
    }

    @Override
    public void execute(){
        intake.runJuicer(-1);
    }

    @Override
    public void end(boolean kInterrupted){
        intake.runJuicer(0);
    }

    @Override
    public boolean isFinished(){
        return false;

    }
}
