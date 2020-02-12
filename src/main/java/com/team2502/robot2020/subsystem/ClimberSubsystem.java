package com.team2502.robot2020.subsystem;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static com.team2502.robot2020.Constants.RobotMap.Motors;

public class ClimberSubsystem extends SubsystemBase {
    public final CANSparkMax CLIMBER_NEO;

    public ClimberSubsystem() {
        CLIMBER_NEO = new CANSparkMax(Motors.CLIMBER_MOTOR, CANSparkMaxLowLevel.MotorType.kBrushless);

    }

    @Override
    public void periodic() {

    }

    public void runMotor(double speed) {
        CLIMBER_NEO.set(speed);
    }

}


