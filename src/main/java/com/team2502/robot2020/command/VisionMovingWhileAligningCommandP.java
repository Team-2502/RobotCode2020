package com.team2502.robot2020.command;

import com.team2502.robot2020.Constants;
import com.team2502.robot2020.subsystem.DrivetrainSubsystem;
import com.team2502.robot2020.subsystem.VisionSubsystem;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class VisionMovingWhileAligningCommandP extends CommandBase {
    private final VisionSubsystem vision;
    private final DrivetrainSubsystem drive;
    private final Joystick joy_left;
    private final Joystick joy_right;
    double leftPower;
    double rightPower;

    private double tx;
    private boolean seesTarget;

    private static final ShuffleboardTab shuffleboard = Shuffleboard.getTab("Vision");
    private static final NetworkTableEntry PEntry = shuffleboard.add("g", Constants.Robot.Vision.VISION_TURNING_P_LOW).getEntry();
    private static final NetworkTableEntry PHighEntry = shuffleboard.add("g High", Constants.Robot.Vision.VISION_TURNING_P_HIGH).getEntry();
    private static final NetworkTableEntry frictionEntry = shuffleboard.add("griction", Constants.Robot.Vision.FRICTION_LOW).getEntry();

    private double p;
    private double frictionConstant;

    public VisionMovingWhileAligningCommandP(VisionSubsystem vision_subsystem, DrivetrainSubsystem drive_subsystem, Joystick joy_left, Joystick joy_right){
        vision = vision_subsystem;
        drive = drive_subsystem;
        this.joy_left = joy_left;
        this.joy_right = joy_right;
        seesTarget = false;
        addRequirements(drive);
    }

    @Override
    public void initialize() {
        if(drive.isHighGear()){
            frictionConstant = frictionEntry.getDouble(Constants.Robot.Vision.FRICTION_HIGH);
            p = PHighEntry.getDouble(Constants.Robot.Vision.VISION_TURNING_P_HIGH);
        }
        else{
            p = PEntry.getDouble(Constants.Robot.Vision.VISION_TURNING_P_LOW);
            frictionConstant = frictionEntry.getDouble(Constants.Robot.Vision.FRICTION_LOW);
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
            double userDesiredValue = -(joy_left.getY() + joy_right.getY()) / 2;


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
            drive.getDrive().tankDrive(-joy_left.getY(), -joy_right.getY(), true);
        }
    }

    @Override
    public boolean isFinished() {
        return !seesTarget;
    }
}
