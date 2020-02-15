package com.team2502.robot2020.subsystem;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static com.team2502.robot2020.Constants.RobotMap.Motors;

public class ClimberSubsystem extends SubsystemBase {
    public final WPI_TalonSRX CLIMBER_CIM;

    public ClimberSubsystem() {
        CLIMBER_CIM = new WPI_TalonSRX(Motors.CLIMBER_MOTOR);

    }

    @Override
    public void periodic() {

    }

    public void runMotor(double speed) {
        CLIMBER_CIM.set(speed);
    }

}


