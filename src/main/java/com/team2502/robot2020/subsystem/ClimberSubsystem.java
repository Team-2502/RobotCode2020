package com.team2502.robot2020.subsystem;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.team2502.robot2020.Constants;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static com.team2502.robot2020.Constants.RobotMap.Motors;

public class ClimberSubsystem extends SubsystemBase {
    public final WPI_TalonSRX CLIMBER_CIM;
    public final Solenoid climberSolenoid;

    public ClimberSubsystem() {
        CLIMBER_CIM = new WPI_TalonSRX(Motors.CLIMBER);
        climberSolenoid = new Solenoid(Constants.RobotMap.Solenoid.CLIMBER);
        deploySolenoid();
    }

    @Override
    public void periodic() {

    }

    public void runMotor(double speed) {
        CLIMBER_CIM.set(speed);
    }

    public void retractSolenoid() {
        climberSolenoid.set(true);

    }

    public void deploySolenoid() {
        climberSolenoid.set(false);
    }

    public boolean currentValueDeploy() {
        return climberSolenoid.get();
    }

}


