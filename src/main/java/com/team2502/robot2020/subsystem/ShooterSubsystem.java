package com.team2502.robot2020.subsystem;

import com.team2502.robot2020.Constants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {

    private final CANSparkMax shooterRight;
    private final CANSparkMax shooterLeft;

    public ShooterSubsystem() {
        shooterLeft = new CANSparkMax(Constants.RobotMap.Motors.SHOOTER_LEFT, CANSparkMaxLowLevel.MotorType.kBrushless);
        shooterRight = new CANSparkMax(Constants.RobotMap.Motors.SHOOTER_RIGHT, CANSparkMaxLowLevel.MotorType.kBrushless);
    }

    public void setShooterSpeed(double speed) {
        shooterLeft.set(-speed);
        shooterRight.set(speed);
    }

    public void stopShooter() {
        shooterRight.set(0);
        shooterLeft.set(0);
    }

    public boolean isShooterRunning() {
        return shooterLeft.get() != 0 || shooterRight.get() != 0;
    }
}
