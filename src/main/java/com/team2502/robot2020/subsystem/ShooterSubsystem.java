package com.team2502.robot2020.subsystem;

import com.revrobotics.*;
import com.team2502.robot2020.Constants;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {

    private final CANSparkMax shooterRight;
    private final CANSparkMax shooterLeft;

    public final CANPIDController rightPID;

    private final CANEncoder rightEncoder;
    private double kP, kI, kD, kIz, kFF, kMaxOutputRight, kMinOutputRight;

    private final ShuffleboardTab shuffleboard = Shuffleboard.getTab("Shooting");

    private final ShooterStateBean shooterStateBean = new ShooterStateBean(false, 0);

    public ShooterSubsystem() {
        shooterLeft = new CANSparkMax(Constants.RobotMap.Motors.SHOOTER_LEFT, CANSparkMaxLowLevel.MotorType.kBrushless);
        shooterRight = new CANSparkMax(Constants.RobotMap.Motors.SHOOTER_RIGHT, CANSparkMaxLowLevel.MotorType.kBrushless);

        shooterLeft.follow(shooterRight, true);

        rightPID = shooterRight.getPIDController();

        rightEncoder = shooterRight.getEncoder();

        kP = 0.0008;
        kI = 0.0;
        kD = 0.0;
        kIz = 0;
        kFF = 0.00019;
        kMaxOutputRight = 1;
        kMinOutputRight = -0.1;

        setupPID();

    }

    public void setShooterSpeed(double speed) {
        rightPID.setReference(speed, ControlType.kVelocity);
        SmartDashboard.putNumber("SetPoint", speed);
    }

    public void stopShooter() {
        shooterRight.set(0);
        shooterStateBean.setOn(false);
        shooterStateBean.setDesiredRPM(0);
    }

    public boolean isShooterRunning() {
        return shooterLeft.get() != 0 || shooterRight.get() != 0;
    }

    public double findRPMFromDistance(double distanceFeet) {
        return 120.8 + 992.28 * (distanceFeet) + -98.63 * Math.pow(distanceFeet, 2) + 4.589 * Math.pow(distanceFeet, 3) + -0.1018 * Math.pow(distanceFeet, 4) + 0.0008942 * Math.pow(distanceFeet, 5);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Shooter Velocity", rightEncoder.getVelocity());
    }

    public void setupPID() {
        rightPID.setP(kP);
        rightPID.setI(kI);
        rightPID.setD(kD);
        rightPID.setIZone(kIz);
        rightPID.setFF(kFF);
        rightPID.setOutputRange(kMinOutputRight, kMaxOutputRight);
        shooterRight.burnFlash();
    }

    public ShooterStateBean getShooterStateBean() {
        return shooterStateBean;
    }

    public static class ShooterStateBean {
        private boolean on;
        private double desiredRPM;

        public ShooterStateBean(boolean on, double desiredRPM) {
            this.on = on;
            this.desiredRPM = desiredRPM;
        }

        public void setOn(boolean on) {
            this.on = on;
            if(!on) {
                this.desiredRPM = 0;
            }
        }

        public double getDesiredRPM() {
            return desiredRPM;
        }

        public void setDesiredRPM(double desiredRPM) {
            if(on) {
                this.desiredRPM = desiredRPM;
            } else {
                this.desiredRPM = 0;
            }
        }

        public boolean isOn() {
            return on;
        }
    }
}
