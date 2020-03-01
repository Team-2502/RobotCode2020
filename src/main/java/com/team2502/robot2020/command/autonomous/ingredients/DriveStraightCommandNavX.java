package com.team2502.robot2020.command.autonomous.ingredients;

import com.team2502.robot2020.Constants;
import com.team2502.robot2020.subsystem.DrivetrainSubsystem;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveStraightCommandNavX extends CommandBase {
    protected static final double defaultKPgain = 0.01;

    private final DrivetrainSubsystem drivetrain;
    private final double speed;

    private double targetAngle;

    private PIDController pidController;
    private double kPgain;

    /**
     * Construct a Drive Straight command
     *
     * @param speed How fast to go (ft/s)
     */
    public DriveStraightCommandNavX(DrivetrainSubsystem drivetrain, double speed) {
        addRequirements(drivetrain);
        this.drivetrain = drivetrain;
        this.speed = speed;
        this.pidController = new PIDController(0.01, 0, 0);

        pidController.enableContinuousInput(180.0f, -180.0f);

        SmartDashboard.putNumber("drivestraight_kP", defaultKPgain);
    }

    @Override
    public void initialize()
    {
        this.targetAngle = drivetrain.getHeading();
        pidController.setSetpoint(targetAngle);

        // Allow for tuning of PID without redeployment.
        kPgain = SmartDashboard.getNumber("drivestraight_kP", defaultKPgain);
    }

    @Override
    public void execute()
    {
        // TODO: Fix this garbage
        double currentAngle = (drivetrain.getHeading());

        // Angluar velocity is the change in error and also the change in absolute angle because taking the derivative eliminates constants
        // and the initial angle is a constant
        // Learn calculus for more information

        double desiredWheelDifferential = pidController.calculate(currentAngle);
        if(desiredWheelDifferential > 0) {
            desiredWheelDifferential += Constants.Robot.Vision.FRICTION_LOW;
        }
        else if(desiredWheelDifferential < 0) {
            desiredWheelDifferential -= Constants.Robot.Vision.FRICTION_LOW;

        }
        SmartDashboard.putNumber("drivestraight_desiredWheelDifferential", desiredWheelDifferential);

        drivetrain.getDrive().tankDrive(speed - desiredWheelDifferential, speed + desiredWheelDifferential);
    }

    @Override
    public boolean isFinished()
    {
        return false;
    }
}
