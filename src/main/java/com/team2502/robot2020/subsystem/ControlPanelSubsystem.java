package com.team2502.robot2020.subsystem;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.team2502.robot2020.Constants.RobotMap.Motors;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ControlPanelSubsystem extends SubsystemBase {
 public final CANSparkMax CONTROL_PANEL;

 public ControlPanelSubsystem(){
  CONTROL_PANEL = new CANSparkMax(Motors.CONTROL_PANEL, CANSparkMaxLowLevel.MotorType.kBrushless);
 }
 @Override
 public void periodic(){

 }
 public void runMotor(double speed){
  CONTROL_PANEL.set(speed);
 }
}
