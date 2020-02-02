/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team2502.robot2020;

import com.team2502.robot2020.command.RunHopperContinuously;
import com.team2502.robot2020.subsystem.HopperSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final HopperSubsystem HOPPER = new HopperSubsystem();

  private final Joystick JOYSTICK_OPERATOR = new Joystick(Constants.OI.JOYSTICK_OPERATOR);
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }


  private void configureButtonBindings() {
    JoystickButton HopperContinuousButton = new JoystickButton(JOYSTICK_OPERATOR, Constants.OI.BUTTON_HOPPER_CONTINUOUS);
    HopperContinuousButton.whenPressed(new RunHopperContinuously(HOPPER));
  }

}
