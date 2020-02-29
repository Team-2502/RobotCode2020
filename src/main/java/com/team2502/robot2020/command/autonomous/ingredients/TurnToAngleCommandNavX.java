package com.team2502.robot2020.command.autonomous.ingredients;

import com.team2502.robot2020.Constants;
import com.team2502.robot2020.subsystem.DrivetrainSubsystem;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;


public class TurnToAngleCommandNavX extends PIDCommand
{

    public TurnToAngleCommandNavX(DrivetrainSubsystem driveTrain, double targetDegrees){
        super(
                new PIDController(0.02,0.0015,0),
                // Close loop on heading
                driveTrain::getHeading,
                // Set reference to target
                targetDegrees,
                // Pipe output to turn robot
                output -> driveTrain.getDrive().arcadeDrive(0, -output),
                // Require the drive
                driveTrain);

        //driveTrain.resetNavX();
        // Set the controller to be continuous (because it is an angle controller)
        getController().enableContinuousInput(-180.0f, 180.0f);
        // Set the controller tolerance - the delta tolerance ensures the robot is stationary at the
        // set point before it is considered as having reached the reference
        getController().setTolerance(Constants.Robot.Auto.TURN_TOLERANCE_DEG, Constants.Robot.Auto.TURN_RATE_TOLERANCE_DEG_PER_SEC);
    }

    @Override
    public boolean isFinished() {
        // End when the controller is at the reference.
        return getController().atSetpoint();
    }
}
