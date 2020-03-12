package com.team2502.robot2020.command.autonomous.groups;

import com.team2502.robot2020.Constants;
import com.team2502.robot2020.command.autonomous.CommandFactory;
import com.team2502.robot2020.subsystem.*;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import java.util.List;

public class RamseteTestCommandGroupFactory implements CommandFactory  {
    @Override
    public CommandBase getInstance(DrivetrainSubsystem drivetrain, IntakeSubsystem intake, HopperSubsystem hopper, VisionSubsystem vision, ShooterSubsystem shooter) {

        // Create a voltage constraint to ensure we don't accelerate too fast
        var autoVoltageConstraint =
                new DifferentialDriveVoltageConstraint(
                        new SimpleMotorFeedforward(Constants.Robot.Auto.KS_VOLTAGE,
                                Constants.Robot.Auto.KV_VOLTAGE,
                                Constants.Robot.Auto.KA_VOLTAGE),
                        Constants.Robot.Auto.DRIVE_KINEMATICS,
                        10);

        // Create config for trajectory
        TrajectoryConfig config =
                new TrajectoryConfig(Constants.Robot.Auto.MAX_METERS_PER_SECOND,
                        Constants.Robot.Auto.MAX_ACCEL_MPS_SQUARED)
                        // Add kinematics to ensure max speed is actually obeyed
                        .setKinematics(Constants.Robot.Auto.DRIVE_KINEMATICS)
                        // Apply the voltage constraint
                        .addConstraint(autoVoltageConstraint);

        // An example trajectory to follow.  All units in meters.
        Trajectory exampleTrajectory = TrajectoryGenerator.generateTrajectory(
                // Start at the origin facing the +X direction
                new Pose2d(0, 0, new Rotation2d(0)),
                // Pass through these two interior waypoints, making an 's' curve path
                List.of(
                        new Translation2d(1, 1),
                        new Translation2d(2, -1)
                ),
                // End 3 meters straight ahead of where we started, facing forward
                new Pose2d(3, 0, new Rotation2d(0)),
                // Pass config
                config
        );

        RamseteCommand ramseteCommand = new RamseteCommand(
                exampleTrajectory,
                drivetrain::getPose,
                new RamseteController(Constants.Robot.Auto.RAMSETE_B, Constants.Robot.Auto.RAMSETE_ZETA),
                new SimpleMotorFeedforward(Constants.Robot.Auto.KS_VOLTAGE,
                        Constants.Robot.Auto.KV_VOLTAGE,
                        Constants.Robot.Auto.KA_VOLTAGE),
                Constants.Robot.Auto.DRIVE_KINEMATICS,
                drivetrain::getWheelSpeeds,
                new PIDController(Constants.Robot.Auto.KP_VELOCITY, 0, 0),
                new PIDController(Constants.Robot.Auto.KP_VELOCITY, 0, 0),
                // RamseteCommand passes volts to the callback
                drivetrain::tankDriveVoltage,
                drivetrain
        );

        // Run path following command, then stop at the end.
        return new SequentialCommandGroup(
                new InstantCommand(() -> SmartDashboard.putBoolean("done", false)),
                ramseteCommand,
                new InstantCommand( () -> drivetrain.tankDriveVoltage(0, 0)),
                new InstantCommand(() -> SmartDashboard.putBoolean("done", true)));

    }
}
