package com.team2502.robot2020.command;

import com.team2502.robot2020.subsystem.DrivetrainSubsystem;
import com.team2502.robot2020.subsystem.VisionSubsystem;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.PIDCommand;

import static com.team2502.robot2020.Constants.Robot.Vision;

public class VisionTurningCommandPID extends PIDCommand {

    ShuffleboardTab shuffleboard = Shuffleboard.getTab("Vision");
    NetworkTableEntry KpEntry = shuffleboard.add("PID: Kp", 0D).getEntry();
    NetworkTableEntry KiEntry = shuffleboard.add("PID: Ki", 0D).getEntry();
    NetworkTableEntry KdEntry = shuffleboard.add("PID: Kd", 0D).getEntry();

    public VisionTurningCommandPID(VisionSubsystem vision, DrivetrainSubsystem drivetrain) {
        super(
                new PIDController(0,0,0),
                // Close loop on heading
                vision::getTX,
                // Set reference to target
                0,
                // Pipe output to turn robot
                output -> drivetrain.getDrive().tankDrive(-output, output),
                // Require the drive
                drivetrain);

        // Set the controller to be continuous (because it is an angle controller)
        getController().enableContinuousInput(-40, 40);
        // Set the controller tolerance - the delta tolerance ensures the robot is stationary at the
        // setpoint before it is considered as having reached the reference
        getController()
                .setTolerance(Vision.TURN_TOLERANCE_DEG, Vision.TURN_RATE_TOLERANCE_DEG_PER_SEC);
    }

    @Override
    public void initialize() {
        getController().setPID(
                KpEntry.getDouble(0.008),
                KiEntry.getDouble(0.0),
                KdEntry.getDouble(0.0));
    }

    @Override
    public boolean isFinished() {
        // End when the controller is at the reference.
        return getController().atSetpoint();
    }

}
