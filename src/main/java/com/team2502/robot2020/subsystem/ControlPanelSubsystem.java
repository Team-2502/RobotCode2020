package com.team2502.robot2020.subsystem;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.team2502.robot2020.Constants.RobotMap.Motors;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ControlPanelSubsystem extends SubsystemBase {
 public final CANSparkMax controlPanel;
 private final Solenoid controlPanelSolenoid;

 public ControlPanelSubsystem(){
  controlPanel = new CANSparkMax(Motors.CONTROL_PANEL, CANSparkMaxLowLevel.MotorType.kBrushless);
  controlPanel.setSmartCurrentLimit(20);
  controlPanel.burnFlash();
  controlPanelSolenoid = new Solenoid(2);
 }
 @Override
 public void periodic(){
 }
 public void runMotor(double speed){
  controlPanel.set(speed);
 }

 public void retractSolenoid() { controlPanelSolenoid.set(true); }

 public void deploySolenoid() {
  controlPanelSolenoid.set(false);
 }

 public boolean isDown() { return controlPanelSolenoid.get(); }
}
