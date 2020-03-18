package com.team2502.robot2020.command;

import com.team2502.robot2020.subsystem.ControlPanelWheelSubsystem;
import edu.wpi.first.wpilibj2.command.InstantCommand;


public class ToggleControlPanelWheelSolenoidCommand extends InstantCommand {
    private final ControlPanelWheelSubsystem control;

    public ToggleControlPanelWheelSolenoidCommand(ControlPanelWheelSubsystem control){
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
