package com.team2502.robot2020.command.autonomous.ingredients;

import com.team2502.robot2020.subsystem.DrivetrainSubsystem;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class VoltageDriveCommand extends CommandBase {

    private final DrivetrainSubsystem drivetrain;
    private final double leftVolts;
    private final double rightVolts;
    private final double timeout;
    private final Timer timer;

    public VoltageDriveCommand(DrivetrainSubsystem DT, double left_volts, double right_volts, double timeout) {
        this.drivetrain = DT;
        this.leftVolts = left_volts;
        this.rightVolts = right_volts;
        this.timeout = timeout;

        timer = new Timer();
    }

    @Override
    public void initialize() {
        timer.reset();
        System.out.println("init");
        drivetrain.drive.tankDrive(leftVolts, rightVolts);
    }

    @Override
    public void execute() {
        System.out.println("execute");
        drivetrain.drive.tankDrive(leftVolts, rightVolts);
    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("End");
        drivetrain.drive.tankDrive(0, 0);
        drivetrain.rightMotors.set(0);
        drivetrain.leftMotors.set(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
