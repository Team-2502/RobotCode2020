/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team2502.robot2020;

import com.team2502.robot2020.command.DriveCommand;
import com.team2502.robot2020.command.ShiftCommand;
import com.team2502.robot2020.command.RunIntakeCommand;
import com.team2502.robot2020.subsystem.DrivetrainSubsystem;
import com.team2502.robot2020.subsystem.solenoid.ShiftingSolenoid;
import com.team2502.robot2020.subsystem.IntakeSubSystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.InstantCommand;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  public static final DrivetrainSubsystem DRIVE_TRAIN = new DrivetrainSubsystem();

  public static final ShiftingSolenoid SHIFTING_SOLENOID = new ShiftingSolenoid();

  public static final Joystick JOYSTICK_DRIVE_RIGHT = new Joystick(Constants.OI.JOYSTICK_DRIVE_RIGHT);
  public static final Joystick JOYSTICK_DRIVE_LEFT = new Joystick(Constants.OI.JOYSTICK_DRIVE_LEFT);
  public static final Joystick JOYSTICK_OPERATOR = new Joystick(Constants.OI.JOYSTICK_OPERATOR);
  private final IntakeSubSystem INTAKE = new IntakeSubSystem();

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    DRIVE_TRAIN.setDefaultCommand(
            new DriveCommand(DRIVE_TRAIN, JOYSTICK_DRIVE_LEFT, JOYSTICK_DRIVE_RIGHT));
  }


  private void configureButtonBindings() {
    JoystickButton DeployIntakeButton = new JoystickButton(JOYSTICK_OPERATOR,Constants.OI.BUTTON_DEPLOY_INTAKE);
    JoystickButton RetractIntakeButton = new JoystickButton(JOYSTICK_OPERATOR,Constants.OI.BUTTON_RETRACT_INTAKE);
    JoystickButton RunIntakeButton = new JoystickButton(JOYSTICK_OPERATOR,Constants.OI.BUTTON_RUN_INTAKE);
    JoystickButton RunIntakeBackwardsButton = new JoystickButton(JOYSTICK_OPERATOR,Constants.OI.BUTTON_RUN_INTAKE_BACKWARDS);

    DeployIntakeButton.whenPressed(new InstantCommand(INTAKE::deploySolenoid,INTAKE));
    RetractIntakeButton.whenPressed(new InstantCommand(INTAKE::retractSolenoid,INTAKE));
    RunIntakeButton.whileHeld(new RunIntakeCommand(INTAKE, 0.5));
    RunIntakeBackwardsButton.whileHeld(new RunIntakeCommand(INTAKE, -0.5));

    JoystickButton shiftButton = new JoystickButton(JOYSTICK_DRIVE_RIGHT, Constants.OI.BUTTON_SHIFT);
    shiftButton.whenPressed(new ShiftCommand(SHIFTING_SOLENOID));
  }
}
