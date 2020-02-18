package com.team2502.robot2020.command;

import com.team2502.robot2020.Constants;
import com.team2502.robot2020.subsystem.DrivetrainSubsystem;
import com.team2502.robot2020.subsystem.VisionSubsystem;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class VisionTurningCommandP extends CommandBase {
    private final VisionSubsystem vision;
    private final DrivetrainSubsystem drive;
    double leftPower;
    double rightPower;

    double tX;
    boolean sees_target;

    ShuffleboardTab shuffleboard = Shuffleboard.getTab("Vision");
    NetworkTableEntry KpEntry = shuffleboard.add("Kp", Constants.Robot.Vision.KP).getEntry();
    NetworkTableEntry minEntry = shuffleboard.add("min", Constants.Robot.Vision.MIN_POWER).getEntry();

    double Kp;
    double min_command;

    public VisionTurningCommandP(VisionSubsystem vision_subsystem, DrivetrainSubsystem drive_subsystem){
        vision = vision_subsystem;
        drive = drive_subsystem;
        sees_target = false;
        addRequirements(drive);
    }

    @Override
    public void initialize() {
        Kp = KpEntry.getDouble(Constants.Robot.Vision.KP);
        min_command = minEntry.getDouble(Constants.Robot.Vision.MIN_POWER);
    }

    @Override
    public void execute() {
        tX = vision.getTX();
        double heading_error = tX;
        double steering_adjust = 0.0f;

        sees_target = vision.getArea() != 0.0;

        if (tX > 1.0)
        {
            steering_adjust = Kp*heading_error + min_command;
        }
        //robot needs to turn left
        else if (tX < 1.0)
        {
            steering_adjust = Kp*heading_error - min_command;
        }
        leftPower = steering_adjust;
        rightPower = -steering_adjust;
        drive.drive.tankDrive(leftPower, rightPower);
    }

    @Override
    public boolean isFinished() {
        return !sees_target;
    }
}
