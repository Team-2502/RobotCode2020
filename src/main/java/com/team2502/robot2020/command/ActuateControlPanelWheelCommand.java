package com.team2502.robot2020.command;

import com.team2502.robot2020.subsystem.ControlPanelSubsystem;
import edu.wpi.first.wpilibj2.command.InstantCommand;


public class ActuateControlPanelWheelCommand extends InstantCommand {
    private final ControlPanelSubsystem control;

    public ActuateControlPanelWheelCommand(ControlPanelSubsystem control){
        this.control = control;
        addRequirements(control);
    }

    @Override
    public void initialize() {
        if(control.isUp()){
            control.retractSolenoid();
        }
        else{
            control.deploySolenoid();
        }
    }
}
