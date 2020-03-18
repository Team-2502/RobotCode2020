package com.team2502.robot2020.command.autonomous.ingredients;

import com.team2502.robot2020.subsystem.DrivetrainSubsystem;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveStraightCommand extends CommandBase {
    protected static final double defaultKPgain = 0.01;

    private final DrivetrainSubsystem drivetrain;
    private final double speed;

    private double targetAngle;

    private PIDController pidControllerError;
    private double kPgain;

    private final boolean absoluteMode;

    /**
     * @param speed How fast to go (ft/s)
     */
    public DriveStraightCommand(DrivetrainSubsystem drivetrain, double speed) {
        addRequirements(drivetrain);
        this.drivetrain = drivetrain;
        this.speed = speed;
        this.pidControllerError = new PIDController(0.01, 0, 0);
        SmartDashboard.putNumber("drivestraight_kP", defaultKPgain);

        absoluteMode = false;
    }

    public DriveStraightCommand(DrivetrainSubsystem drivetrain, double speed, double targetAngle) {
        addRequirements(drivetrain);
        this.drivetrain = drivetrain;
        this.speed = speed;
        this.pidControllerError = new PIDController(0.01, 0, 0);
        this.targetAngle = targetAngle;
        SmartDashboard.putNumber("drivestraight_kP", defaultKPgain);

        absoluteMode = true;
    }



    @Override
    public void initialize()
    {
        if(!absoluteMode) {
            this.targetAngle = drivetrain.getHeading();
        }

        pidControllerError.setSetpoint(0); // we want 0 error
    }

    @Override
    public void execute()
    {
        double currentAngle = drivetrain.getHeading();

        double error = angleDiff(targetAngle, currentAngle);

        // Angluar velocity is the change in error and also the change in absolute angle because taking the derivative eliminates constants
        // and the initial angle is a constant
        // Learn calculus for more information

        double desiredWheelDifferential = pidControllerError.calculate(error);
        System.out.println("desiredWheelDifferential = " + desiredWheelDifferential);
        System.out.println("speed = " + speed);

        SmartDashboard.putNumber("drivestraight_desiredWheelDifferential", desiredWheelDifferential);

        drivetrain.getDrive().tankDrive(speed + desiredWheelDifferential, speed - desiredWheelDifferential);
    }

    /**
     * Difference between 2 angles, accounting for -180 = 180
     *
     * @param targetAngle angle you want to be at
     * @param currentAngle angle you are actually at
     * @return the number of degrees you must change current angle by to reach target angle.
     */
    private double angleDiff(double targetAngle, double currentAngle) {
        double diff = targetAngle - currentAngle;

        if(diff > 180) {
            diff -= 360;
        } else if (diff < -180) {
            diff += 360;
        }

        return diff;
    }

    @Override
    public boolean isFinished()
    {
        return false;
    }
}
