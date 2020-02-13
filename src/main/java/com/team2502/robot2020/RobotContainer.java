/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team2502.robot2020;

import com.team2502.robot2020.Command.RunIntakeCommand;
import com.team2502.robot2020.subsystem.DrivetrainSubsystem;
import com.team2502.robot2020.subsystem.IntakeSubSystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import java.util.jar.JarOutputStream;

/**
 * This class is where the bulk of the robot should be declared. Since
 * com.team2502.robot2020.Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DrivetrainSubsystem DRIVE_TRAIN = new DrivetrainSubsystem();
  private IntakeSubSystem INTAKE = new IntakeSubSystem();
  private final Joystick JOYSTICK_DRIVE_RIGHT = new Joystick(Constants.OI.JOYSTICK_DRIVE_RIGHT);
  private final Joystick JOYSTICK_DRIVE_LEFT = new Joystick(Constants.OI.JOYSTICK_DRIVE_LEFT);
  private final Joystick JOYSTICK_OPERATOR = new Joystick((Constants.OI.JOYSTICK_OPERATOR));

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    DRIVE_TRAIN.setDefaultCommand(
            new RunCommand(() -> DRIVE_TRAIN.drive.tankDrive(-JOYSTICK_DRIVE_LEFT.getY(), -JOYSTICK_DRIVE_RIGHT.getY()), DRIVE_TRAIN));
  }


  private void configureButtonBindings() {
    JoystickButton DeployIntakeButton = new JoystickButton(JOYSTICK_OPERATOR,3);
    DeployIntakeButton.whenPressed(new InstantCommand(INTAKE::deploySolenoid,INTAKE));
    JoystickButton RetractIntakeButton = new JoystickButton(JOYSTICK_OPERATOR,4);
    RetractIntakeButton.whenPressed(new InstantCommand(INTAKE::retractSolenoid,INTAKE));
    JoystickButton RunIntakeButton = new JoystickButton(JOYSTICK_OPERATOR,5);
    RunIntakeButton.whileHeld(new RunIntakeCommand(INTAKE, 0.5));
    JoystickButton RunIntakeBackwardsButton = new JoystickButton(JOYSTICK_OPERATOR,6);
    RunIntakeBackwardsButton.whileHeld(new RunIntakeCommand(INTAKE, -0.5));
  }
}
