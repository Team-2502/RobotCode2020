package com.team2502.robot2020.command;

import com.team2502.robot2020.Constants;
import com.team2502.robot2020.subsystem.DrivetrainSubsystem;
import com.team2502.robot2020.subsystem.VisionSubsystem;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class VisionMovingWhileAligningAutoCommandP extends CommandBase {
    private final VisionSubsystem vision;
    private final DrivetrainSubsystem drive;
    private final double userDesiredValue;
    double leftPower;
    double rightPower;

    private double tx;
    private boolean seesTarget;

    private double p;
    private double frictionConstant;

    public VisionMovingWhileAligningAutoCommandP(VisionSubsystem vision_subsystem, DrivetrainSubsystem drive_subsystem, double power){
        vision = vision_subsystem;
        drive = drive_subsystem;
        this.userDesiredValue = power;
        seesTarget = false;
        addRequirements(drive);
    }

    @Override
    public void initialize() {
        if(drive.isHighGear()){
            frictionConstant = Constants.Robot.Vision.FRICTION_HIGH;
            p = Constants.Robot.Vision.P_HIGH;
        }
        else{
            p = Constants.Robot.Vision.P_LOW;
            frictionConstant = Constants.Robot.Vision.FRICTION_LOW;
        }

    }

    @Override
    public void execute() {

        tx = vision.getTx();
        double heading_error = tx;
        double steering_adjust = 0.0f;

        seesTarget = vision.getArea() != 0.0;

        if(seesTarget) {
            double power = 0;

            if (vision.getDistance() < 10) {
                power = Math.min(-0.2 - frictionConstant, userDesiredValue);
            } else if (10 <= vision.getDistance() && vision.getDistance() <= 12) {
                power = Math.min(0, userDesiredValue);
            } else {
                power = userDesiredValue;
            }
            boolean useFriction = power < frictionConstant;
            double frictionVal = useFriction ? frictionConstant : 0;

            if (tx > 1.0) {
                steering_adjust = p * heading_error + frictionVal;
            } else if (tx < 1.0) {    //robot needs to turn left
                steering_adjust = p * heading_error - frictionVal;
            }
            leftPower = steering_adjust;
            rightPower = -steering_adjust;
            drive.getDrive().tankDrive(leftPower + power, rightPower + power);
        } else {
            drive.getDrive().tankDrive(userDesiredValue, userDesiredValue, false);
        }
    }

    @Override
    public boolean isFinished() {
        return !seesTarget;
    }
}