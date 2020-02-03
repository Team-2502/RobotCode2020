package com.team2502.robot2020.subsystem;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubSystem extends SubsystemBase {

    private final CANSparkMax intakeMotor;
    private final Solenoid intakeSolenoid;

    public IntakeSubSystem() {
        // fix motor ID
        intakeMotor = new CANSparkMax(0, CANSparkMaxLowLevel.MotorType.kBrushless);
        intakeSolenoid = new Solenoid(0);
    }

    public void retractSolenoid(){
        intakeSolenoid.set(false);
    }
    public void deploySolenoid(){
        intakeSolenoid.set(true);
    }

    //Sets intake to run at speed between -1.0 and 1.0
    public void runIntake(){
        //Change speed setting after setting, 0.5 for placeholder
        intakeMotor.set(0.5);
    }

    public void stopIntake(){
        intakeMotor.set(0);
    }
    
}
