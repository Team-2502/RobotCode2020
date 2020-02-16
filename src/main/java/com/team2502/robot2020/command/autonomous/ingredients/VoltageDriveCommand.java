package com.team2502.robot2020.command.autonomous.ingredients;

import com.team2502.robot2020.subsystem.DrivetrainSubsystem;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class VoltageDriveCommand extends CommandBase {

    private final DrivetrainSubsystem DT;
    private final double left_volts;
    private final double right_volts;
    private final double timeout;
    private final Timer timer;

    public VoltageDriveCommand(DrivetrainSubsystem DT, double left_volts, double right_volts, double timeout) {
        this.DT = DT;
        this.left_volts = left_volts;
        this.right_volts = right_volts;
        this.timeout = timeout;

        timer = new Timer();
    }

    @Override
    public void initialize() {
        timer.reset();
        System.out.println("init");
        DT.drive.tankDrive(left_volts, right_volts);
    }

    @Override
    public void execute() {
        System.out.println("execute");
        DT.drive.tankDrive(left_volts, right_volts);
    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("End");
        DT.drive.tankDrive(0, 0);
        DT.rightMotors.set(0);
        DT.leftMotors.set(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
