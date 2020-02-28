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

    private double tx;
    private boolean seesTarget;

    private final ShuffleboardTab shuffleboard = Shuffleboard.getTab("Vision");
    private final NetworkTableEntry PEntry = shuffleboard.add("P", Constants.Robot.Vision.P_LOW).getEntry();
    private final NetworkTableEntry PHighEntry = shuffleboard.add("P High", Constants.Robot.Vision.P_HIGH).getEntry();
    private final NetworkTableEntry frictionEntry = shuffleboard.add("Friction", Constants.Robot.Vision.FRICTION_LOW).getEntry();

    private double p;
    private double frictionConstant;

    public VisionTurningCommandP(VisionSubsystem vision_subsystem, DrivetrainSubsystem drive_subsystem){
        vision = vision_subsystem;
        drive = drive_subsystem;
        seesTarget = false;
        addRequirements(drive);
    }

    @Override
    public void initialize() {
        if(drive.isHighGear()){
            frictionConstant = frictionEntry.getDouble(Constants.Robot.Vision.FRICTION_HIGH);
            p = PHighEntry.getDouble(Constants.Robot.Vision.P_HIGH);
        }
        else{
            p = PEntry.getDouble(Constants.Robot.Vision.P_LOW);
            frictionConstant = frictionEntry.getDouble(Constants.Robot.Vision.FRICTION_LOW);
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
