/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team2502.robot2020;

import com.sun.tools.javac.util.List;
import com.team2502.robot2020.Constants.RobotMap.Auto;
import com.team2502.robot2020.Constants.RobotMap.Drive;
import com.team2502.robot2020.subsystem.DrivetrainSubsytem;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  private final DrivetrainSubsytem drivetrain = new DrivetrainSubsytem();

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // Voltage constraint so we don't accelerate too fast
    var autoVoltageConstraint = new DifferentialDriveVoltageConstraint(
            new SimpleMotorFeedforward(Drive.ksVolts, Drive.kvVoltSecondsPerMeter, Drive.kaVoltSecondsSquaredPerMeter),
            Drive.kDriveKinematics, 10
    );

    // Trajectory config
    TrajectoryConfig config = new TrajectoryConfig(Auto.kMaxSpeedMetersPerSecond, Auto.kMaxAccelerationMetersPerSecondSquared)
            .setKinematics(Drive.kDriveKinematics)
            .addConstraint(autoVoltageConstraint);

    // Generate trajectory from list pf predetermined way points
    // Set the waypoints for auto here
    Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
            // Start at origin facing forwards
            new Pose2d(0, 0, new Rotation2d(0)),

            // List of waypoints to follow
            List.of(new Translation2d(1, 1),
                    new Translation2d(2, -1)),

            // Ending location + rotation
            new Pose2d(3, 0, new Rotation2d(0)),

            // Pass the config
            config
    );

    // Construct the actual ramsete command for auto
    RamseteCommand autoCommand = new RamseteCommand(
            trajectory,
            drivetrain::getPose,
            new RamseteController(Auto.kRamseteB, Auto.kRamseteZeta),
            new SimpleMotorFeedforward(Drive.ksVolts, Drive.kvVoltSecondsPerMeter, Drive.kaVoltSecondsSquaredPerMeter),
            Drive.kDriveKinematics,
            drivetrain::getWheelSpeeds,
            new PIDController(Drive.kPDriveVel, 0, 0),
            new PIDController(Drive.kPDriveVel, 0, 0),

            // Command passes volts to callback
            drivetrain::tankDriveVolts,
            drivetrain
    );

    // Run the command and then stop
    return autoCommand.andThen(() -> drivetrain.tankDriveVolts(0, 0));
  }
}