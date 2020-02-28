package com.team2502.robot2020.command;

import com.team2502.robot2020.subsystem.ControlPanelSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class RunControlPanel extends CommandBase {
private final ControlPanelSubsystem CONTROL_PANEL;

public RunControlPanel(ControlPanelSubsystem control_panel){
    CONTROL_PANEL = control_panel;
    addRequirements(control_panel);
}

@Override
    public void initialize(){
    CONTROL_PANEL.runMotor(1);
}
@Override
    public void end(boolean interrupted){
CONTROL_PANEL.runMotor(0);
}
@Override
    public boolean isFinished(){
    return false;
}
}
