package com.team2502.robot2020.command.autonomous.ingredients;

import com.team2502.robot2020.Constants;
import com.team2502.robot2020.subsystem.DrivetrainSubsystem;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;


public class TurnToAngleCommandNavX extends PIDCommand
{

    DrivetrainSubsystem drive;

    public TurnToAngleCommandNavX(DrivetrainSubsystem driveTrain, double targetDegrees){
        super(
                new PIDController(0.02,0,0),
                // Close loop on heading
                driveTrain::getHeading,
                // Set reference to target
                targetDegrees,
                // Pipe output to turn robot
                output -> {
                    double frictionConstant = Constants.Robot.Vision.FRICTION_LOW;
                    double steering_power = 0;
                    if (output > 0) {
                        steering_power = output + frictionConstant;
                    }
                    else if (output < 0) {    //robot needs to turn left
                        steering_power = output - frictionConstant;
                    }

                    double leftPower = -steering_power;
                    double rightPower = steering_power;
                    driveTrain.getDrive().tankDrive(leftPower, rightPower);
                },
                // Require the drive
                driveTrain);

        //driveTrain.resetNavX();
        // Set the controller to be continuous (because it is an angle controller)
        getController().enableContinuousInput(-180.0f, 180.0f);
        // Set the controller tolerance - the delta tolerance ensures the robot is stationary at the
        // set point before it is considered as having reached the reference
        getController().setTolerance(Constants.Robot.Auto.TURN_TOLERANCE_DEG, Constants.Robot.Auto.TURN_RATE_TOLERANCE_DEG_PER_SEC);

        drive = driveTrain;
    }

    @Override
    public boolean isFinished() {
        // End when the controller is at the reference.
        return getController().atSetpoint();
    }

    @Override
    public void end(boolean interrupted) {
        //drive.getDrive().tankDrive(0,0);
    }
}
