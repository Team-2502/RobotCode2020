package com.team2502.robot2020.subsystem;

import com.revrobotics.*;
import com.team2502.robot2020.Constants;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static com.team2502.robot2020.Constants.Robot.MotorSpeeds.SHOOTER_HALF_RPM;

public class ShooterSubsystem extends SubsystemBase {

    private final CANSparkMax shooterRight;
    private final CANSparkMax shooterLeft;

    public final CANPIDController rightPID;

    private final CANEncoder rightEncoder;
    private final CANEncoder leftEncoder;
    private double kP, kI, kD, kIz, kFF, kMaxOutputRight, kMinOutputRight, maxRPM;

    private final ShuffleboardTab shuffleboard = Shuffleboard.getTab("Shooting");
    //private final NetworkTableEntry PShootEntry = shuffleboard.add("kP", 1).getEntry();

    public ShooterSubsystem() {
        shooterLeft = new CANSparkMax(Constants.RobotMap.Motors.SHOOTER_LEFT, CANSparkMaxLowLevel.MotorType.kBrushless);
        shooterRight = new CANSparkMax(Constants.RobotMap.Motors.SHOOTER_RIGHT, CANSparkMaxLowLevel.MotorType.kBrushless);

        shooterLeft.follow(shooterRight, true);

        rightPID = shooterRight.getPIDController();

        rightEncoder = shooterRight.getEncoder();
        leftEncoder = shooterLeft.getEncoder();

        kP = 0.0008;
        kI = 0.0;
        kD = 0.0;
        kIz = 0; //1000
        kFF = getFeedforward();
        kMaxOutputRight = 1;
        kMinOutputRight = -0.1;
        maxRPM = 5850;

//        setupPID();

    }

    public void setShooterSpeed(double speed) {
        rightPID.setReference(speed, ControlType.kVelocity);
        //shooterRight.set(speed);
        SmartDashboard.putNumber("SetPoint", speed);
    }

    public void stopShooter() {
        shooterRight.set(0);
    }

    public boolean isShooterRunning() {
        return shooterLeft.get() != 0 || shooterRight.get() != 0;
    }

    @Override
    public void periodic() {
        SmartDashboard.putBoolean("Shooters equal", (shooterLeft.get() == shooterRight.get()));
        SmartDashboard.putNumber("Shooter Velocity", rightEncoder.getVelocity());
        SmartDashboard.putNumber("Shooter Velocity left", leftEncoder.getVelocity());
        SmartDashboard.putNumber("Shooter Min", rightPID.getOutputMin());
        SmartDashboard.putNumber("Shooter Voltage", shooterRight.getBusVoltage());

        SmartDashboard.putNumber("Shooter kP", rightPID.getP());
        SmartDashboard.putNumber("Shooter kI", rightPID.getI());
        SmartDashboard.putNumber("Shooter kD", rightPID.getD());
        SmartDashboard.putNumber("Shooter kIZone", rightPID.getIZone());
        SmartDashboard.putNumber("Shooter kF", rightPID.getFF());
        SmartDashboard.putNumber("Shooter Integral Accum", rightPID.getIAccum());

        SmartDashboard.putNumber("SetPoint", SHOOTER_HALF_RPM);
    }

    public void setupPID(){
        rightPID.setP(kP);
        rightPID.setI(kI);
        rightPID.setD(kD);
        rightPID.setIZone(kIz);
        rightPID.setFF(kFF);
        rightPID.setOutputRange(kMinOutputRight, kMaxOutputRight);
        shooterRight.burnFlash();
    }

    public double getFeedforward()
    {
        double gearReduction = 2f/3f;
        double speedConstant = 12f / maxRPM;

        return speedConstant;
    }

}
