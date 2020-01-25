package com.team2502.robot2020.command;

import com.team2502.robot2020.Constants;
import com.team2502.robot2020.subsystem.DrivetrainSubsystem;
import com.team2502.robot2020.subsystem.VisionSubsystem;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;

import static com.team2502.robot2020.Constants.Robot.Vision;

public class VisionTurningCommandPID extends PIDCommand {

    public VisionTurningCommandPID(VisionSubsystem vision, DrivetrainSubsystem drivetrain) {
        super(
                new PIDController(Vision.TURN_P, Vision.TURN_I, Vision.TURN_D),
                // Close loop on heading
                vision::getTX,
                // Set reference to target
                0,
                // Pipe output to turn robot
                output -> drivetrain.drive.tankDrive(0, output),
                // Require the drive
                drivetrain);

        // Set the controller to be continuous (because it is an angle controller)
        getController().enableContinuousInput(-40, 40);
        // Set the controller tolerance - the delta tolerance ensures the robot is stationary at the
        // setpoint before it is considered as having reached the reference
        getController()
                .setTolerance(Vision.kTurnToleranceDeg, Vision.kTurnRateToleranceDegPerS);
    }

    @Override
    public boolean isFinished() {
        // End when the controller is at the reference.
        return getController().atSetpoint();
    }

}
