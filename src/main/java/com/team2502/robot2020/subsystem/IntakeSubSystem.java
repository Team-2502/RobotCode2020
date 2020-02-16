package com.team2502.robot2020.subsystem;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.team2502.robot2020.Constants;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubSystem extends SubsystemBase implements Subsystem {

    private final CANSparkMax intakeMotor;
    private final CANSparkMax squeezeMotor;
    private final Solenoid intakeSolenoid;

    private boolean intakeDeployed = true;

    public IntakeSubSystem() {
        // fix motor ID
        intakeMotor = new CANSparkMax(Constants.RobotMap.Motors.INTAKE_MOTOR, CANSparkMaxLowLevel.MotorType.kBrushless);
        squeezeMotor = new CANSparkMax(Constants.RobotMap.Motors.SQUEEZE_MOTOR, CANSparkMaxLowLevel.MotorType.kBrushless);
        intakeSolenoid = new Solenoid(Constants.RobotMap.Solenoid.INTAKE);
    }

    public void retractSolenoid() {
        intakeSolenoid.set(false);

    }

    public void deploySolenoid() {
        intakeSolenoid.set(true);
    }

    public void runIntake(double speed) {
        intakeMotor.set(speed);
        squeezeMotor.set(1);
    }

    public void stopIntake() {
        squeezeMotor.set(0);
        intakeMotor.set(0);
    }

    public boolean currentValueDeploy() {
       return intakeSolenoid.get();
    }
    
}
