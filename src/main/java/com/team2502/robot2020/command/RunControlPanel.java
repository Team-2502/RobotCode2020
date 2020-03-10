package com.team2502.robot2020.command;

import com.team2502.robot2020.subsystem.ControlPanelSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class RunControlPanel extends CommandBase {
    private final ControlPanelSubsystem controlPanel;
    private final double speed;

public RunControlPanel(ControlPanelSubsystem control_panel, double speed){
    controlPanel = control_panel;
    this.speed = speed;
    addRequirements(control_panel);
}

@Override
    public void initialize(){
    controlPanel.runMotor(speed);
}
@Override
    public void end(boolean interrupted){
controlPanel.runMotor(0);
}
@Override
    public boolean isFinished(){
    return false;
}
}
