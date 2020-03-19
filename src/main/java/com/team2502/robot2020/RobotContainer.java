/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team2502.robot2020;

import com.team2502.robot2020.command.*;
import com.team2502.robot2020.command.autonomous.ingredients.ShootAtRPMCommand;
import com.team2502.robot2020.command.dancepad.DancePadDriveCommand;
import com.team2502.robot2020.subsystem.*;
import com.team2502.robot2020.Constants.OI;

import com.team2502.robot2020.util.DancePad;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the Robot periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  protected final ControlPanelWheelSubsystem CONTROL_PANEL = new ControlPanelWheelSubsystem();
  protected final DrivetrainSubsystem DRIVE_TRAIN = new DrivetrainSubsystem();
  protected final ClimberSubsystem CLIMBER = new ClimberSubsystem();
  protected final IntakeSubsystem INTAKE = new IntakeSubsystem();
  protected final HopperSubsystem HOPPER = new HopperSubsystem();
  protected final VisionSubsystem VISION = new VisionSubsystem();
  protected final ShooterSubsystem SHOOTER = new ShooterSubsystem();

  protected final SendableChooser<ControlScheme> controlType = new SendableChooser<>();

    /**
     * The container for the robot.  Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
      controlType.addOption("Dance Pad", ControlScheme.DancePad);
      controlType.addOption("Two Joysticks", ControlScheme.TwoJoysticks);
      controlType.setDefaultOption("Three Joysticks", ControlScheme.ThreeJoysticks);

      SmartDashboard.putData("Control Scheme", controlType);
      AutoSwitcher.putToSmartDashboard();

      CameraServer.getInstance().startAutomaticCapture();
  }

  protected void configureButtonBindingsThreeJoysticks() {
      Joystick JOYSTICK_DRIVE_RIGHT = new Joystick(Constants.OI.JOYSTICK_DRIVE_RIGHT);
      Joystick JOYSTICK_DRIVE_LEFT = new Joystick(Constants.OI.JOYSTICK_DRIVE_LEFT);
      Joystick JOYSTICK_OPERATOR = new Joystick(Constants.OI.JOYSTICK_OPERATOR);

      JoystickButton RunControlPanelButton = new JoystickButton(JOYSTICK_OPERATOR, Constants.OI.BUTTON_CONTROL_PANEL);
      RunControlPanelButton.whileHeld(new RunControlPanelWheelCommand(CONTROL_PANEL, Constants.Robot.MotorSpeeds.CONTROL_PANEL));

      JoystickButton ActuateControlPanel = new JoystickButton(JOYSTICK_OPERATOR, Constants.OI.BUTTON_ACTUATE_CONTROL_PANEL);
      ActuateControlPanel.whenPressed(new ToggleControlPanelWheelSolenoidCommand(CONTROL_PANEL));

      JoystickButton RunIntakeButton = new JoystickButton(JOYSTICK_OPERATOR,Constants.OI.BUTTON_RUN_INTAKE);
      JoystickButton RunIntakeBackwardsButton = new JoystickButton(JOYSTICK_OPERATOR,Constants.OI.BUTTON_RUN_INTAKE_BACKWARDS);

      RunIntakeButton.whileHeld(new RunIntakeCommand(INTAKE, HOPPER, Constants.Robot.MotorSpeeds.INTAKE_SPEED_FORWARD, Constants.Robot.MotorSpeeds.INTAKE_SQUEEZE_SPEED_FORWARDS, Constants.Robot.MotorSpeeds.HOPPER_BOTTOM_BELT_INTAKE));
      RunIntakeBackwardsButton.whileHeld(new RunIntakeCommand(INTAKE, HOPPER, Constants.Robot.MotorSpeeds.INTAKE_SPEED_BACKWARDS, Constants.Robot.MotorSpeeds.INTAKE_SQUEEZE_SPEED_BACKWARDS, 0));

      JoystickButton ShiftButton = new JoystickButton(JOYSTICK_DRIVE_RIGHT, Constants.OI.BUTTON_SHIFT);
      ShiftButton.whenPressed(new ToggleDrivetrainGearCommand(DRIVE_TRAIN));

      JoystickButton HopperContinuousButton = new JoystickButton(JOYSTICK_OPERATOR, Constants.OI.BUTTON_HOPPER_CONTINUOUS);
      HopperContinuousButton.whileHeld(new RunHopperCommand(HOPPER, SHOOTER, Constants.Robot.MotorSpeeds.HOPPER_LEFT_BELT,
              Constants.Robot.MotorSpeeds.HOPPER_RIGHT_BELT, Constants.Robot.MotorSpeeds.HOPPER_EXIT_WHEEL, Constants.Robot.MotorSpeeds.HOPPER_BOTTOM_BELT, true));

      JoystickButton HopperContinuousButtonReverse = new JoystickButton(JOYSTICK_OPERATOR, Constants.OI.BUTTON_HOPPER_CONTINUOUS_REVERSE);
      HopperContinuousButtonReverse.whileHeld(new RunHopperCommand(HOPPER, SHOOTER, Constants.Robot.MotorSpeeds.HOPPER_LEFT_BELT_REVERSE,
              Constants.Robot.MotorSpeeds.HOPPER_RIGHT_BELT_REVERSE, Constants.Robot.MotorSpeeds.HOPPER_EXIT_WHEEL_REVERSE, Constants.Robot.MotorSpeeds.HOPPER_BOTTOM_BELT_REVERSE, false));

      JoystickButton RunShooterCloseButton = new JoystickButton(JOYSTICK_OPERATOR, Constants.OI.BUTTON_RUN_SHOOTER_INIT_LINE);
      RunShooterCloseButton.toggleWhenPressed(new ShootAtRPMCommand(SHOOTER, Constants.LookupTables.DIST_TO_RPM_TABLE.get(10D)));

      JoystickButton RunShooterTrenchButton = new JoystickButton(JOYSTICK_OPERATOR, Constants.OI.BUTTON_RUN_SHOOTER_TRENCH);
      RunShooterTrenchButton.toggleWhenPressed(new ShootAtRPMCommand(SHOOTER, Constants.LookupTables.DIST_TO_RPM_TABLE.get(25D)));

      JoystickButton RunSqueezeBackwards = new JoystickButton(JOYSTICK_OPERATOR, OI.BUTTON_BOTTOM_ROLLER_BACKWARDS);
      RunSqueezeBackwards.whileHeld(new RunIntakeCommand(INTAKE, HOPPER, 0, Constants.Robot.MotorSpeeds.INTAKE_SQUEEZE_SPEED_BACKWARDS, 0));

      DRIVE_TRAIN.setDefaultCommand(new DriveCommand(DRIVE_TRAIN, JOYSTICK_DRIVE_LEFT, JOYSTICK_DRIVE_RIGHT));
  }

  protected void configureButtonBindingsDancePad(){
      DancePad DANCEPAD = new DancePad(OI.JOYSTICK_OPERATOR);
      DRIVE_TRAIN.setDefaultCommand(new DancePadDriveCommand(DRIVE_TRAIN, DANCEPAD));
  }

  protected void configureButtonBindingsTwoJoysticks(){

  }

  public Command getAutonomousRoutine() {
      return AutoSwitcher.getAutoInstance().getInstance(
              DRIVE_TRAIN,
              INTAKE,
              HOPPER,
              VISION,
              SHOOTER
      );
  }

  public enum ControlScheme {
    ThreeJoysticks,
    TwoJoysticks,
    DancePad
  }
}