package com.team2502.robot2020.command.autonomous.ingredients;

import com.team2502.robot2020.subsystem.DrivetrainSubsystem;
import com.team2502.robot2020.subsystem.VisionSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

import static com.team2502.robot2020.Constants.Robot.Vision.*;

public class VisionAlignCommand extends CommandBase {
    private final VisionSubsystem vision;
    private final DrivetrainSubsystem drive;
    double leftPower;
    double rightPower;

    private double tx;
    private boolean seesTarget;

    private double p;
    private double frictionConstant;

    public VisionAlignCommand(VisionSubsystem vision_subsystem, DrivetrainSubsystem drive_subsystem){
        vision = vision_subsystem;
        drive = drive_subsystem;
        seesTarget = false;
        addRequirements(drive);
    }

    @Override
    public void initialize() {
        if(drive.isHighGear()){
            frictionConstant = FRICTION_HIGH;
            p = VISION_TURNING_P_HIGH;
        }
        else{
            p = VISION_TURNING_P_LOW;
            frictionConstant = FRICTION_LOW;
        }

    }

    @Override
    public void execute() {
        tx = vision.getTx();
        double heading_error = tx;
        double steering_adjust = 0.0f;

        seesTarget = vision.getArea() != 0.0;

        if (tx > 1.0) {
            steering_adjust = p *heading_error + frictionConstant;
        }
        else if (tx < 1.0) {    //robot needs to turn left
            steering_adjust = p *heading_error - frictionConstant;
        }
        leftPower = steering_adjust;
        rightPower = -steering_adjust;
        drive.getDrive().tankDrive(leftPower, rightPower);
    }

    @Override
    public boolean isFinished() {
        return !seesTarget;
    }
}
