/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team2502.robot2020;

import com.team2502.robot2020.command.RunClimberCommand;
import com.team2502.robot2020.subsystem.ClimberSubsystem;
import com.team2502.robot2020.subsystem.DrivetrainSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DrivetrainSubsystem DRIVE_TRAIN = new DrivetrainSubsystem();
  private final ClimberSubsystem CLIMBER = new ClimberSubsystem();

  public final Joystick JOYSTICK_DRIVE_RIGHT = new Joystick(Constants.OI.JOYSTICK_DRIVE_RIGHT);
  private final Joystick JOYSTICK_DRIVE_LEFT = new Joystick(Constants.OI.JOYSTICK_DRIVE_LEFT);

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
    JoystickButton RunClimberButton = new JoystickButton(JOYSTICK_DRIVE_LEFT 0);
    RunClimberButton.whileHeld(new RunClimberCommand(CLIMBER));
  }
}
