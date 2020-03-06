/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team2502.robot2020;

import com.team2502.robot2020.command.*;
import com.team2502.robot2020.subsystem.ClimberSubsystem;
import com.team2502.robot2020.subsystem.DrivetrainSubsystem;
import com.team2502.robot2020.subsystem.ShooterSubsystem;
import com.team2502.robot2020.subsystem.IntakeSubsystem;
import com.team2502.robot2020.subsystem.HopperSubsystem;
import com.team2502.robot2020.subsystem.VisionSubsystem;
import com.team2502.robot2020.command.RunControlPanel;
import com.team2502.robot2020.subsystem.ControlPanelSubsystem;
import com.team2502.robot2020.Constants.OI;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
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
  protected final ControlPanelSubsystem CONTROL_PANEL = new ControlPanelSubsystem();
  protected final DrivetrainSubsystem DRIVE_TRAIN = new DrivetrainSubsystem();
  protected final ClimberSubsystem CLIMBER = new ClimberSubsystem();
  protected final IntakeSubsystem INTAKE = new IntakeSubsystem();
  protected final HopperSubsystem HOPPER = new HopperSubsystem();
  protected final VisionSubsystem VISION = new VisionSubsystem();
  protected final ShooterSubsystem SHOOTER = new ShooterSubsystem();

  private static final Joystick JOYSTICK_DRIVE_RIGHT = new Joystick(Constants.OI.JOYSTICK_DRIVE_RIGHT);
  private static final Joystick JOYSTICK_DRIVE_LEFT = new Joystick(Constants.OI.JOYSTICK_DRIVE_LEFT);
  private static final Joystick JOYSTICK_OPERATOR = new Joystick(Constants.OI.JOYSTICK_OPERATOR);


    /**
     * The container for the robot.  Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        configureButtonBindings();

    DRIVE_TRAIN.setDefaultCommand(
            new DriveCommand(DRIVE_TRAIN, JOYSTICK_DRIVE_LEFT, JOYSTICK_DRIVE_RIGHT));

    AutoSwitcher.putToSmartDashboard();
    CameraServer.getInstance().startAutomaticCapture();
  }

  private void configureButtonBindings() {
    JoystickButton RunControlPanelButton = new JoystickButton(JOYSTICK_OPERATOR, Constants.OI.BUTTON_CONTROL_PANEL);
    RunControlPanelButton.whileHeld(new RunControlPanel(CONTROL_PANEL, Constants.Robot.MotorSpeeds.CONTROL_PANEL));

    JoystickButton ActuateControlPanel = new JoystickButton(JOYSTICK_OPERATOR, Constants.OI.BUTTON_ACTUATE_CONTROL_PANEL);
    ActuateControlPanel.whenPressed(new ActuateControlPanelWheelCommand(CONTROL_PANEL));

    JoystickButton RunIntakeButton = new JoystickButton(JOYSTICK_OPERATOR,Constants.OI.BUTTON_RUN_INTAKE);
    JoystickButton RunIntakeBackwardsButton = new JoystickButton(JOYSTICK_OPERATOR,Constants.OI.BUTTON_RUN_INTAKE_BACKWARDS);

    RunIntakeButton.whileHeld(new RunIntakeCommand(INTAKE, HOPPER, Constants.Robot.MotorSpeeds.INTAKE_SPEED_FORWARD, Constants.Robot.MotorSpeeds.INTAKE_SQUEEZE_SPEED_FORWARDS, Constants.Robot.MotorSpeeds.HOPPER_BOTTOM_BELT_INTAKE));
    RunIntakeBackwardsButton.whileHeld(new RunIntakeCommand(INTAKE, HOPPER, Constants.Robot.MotorSpeeds.INTAKE_SPEED_BACKWARDS, Constants.Robot.MotorSpeeds.INTAKE_SQUEEZE_SPEED_BACKWARDS, 0));

    JoystickButton ShiftButton = new JoystickButton(JOYSTICK_DRIVE_RIGHT, Constants.OI.BUTTON_SHIFT);
    ShiftButton.whenPressed(new ShiftDrivetrainCommand(DRIVE_TRAIN));

    JoystickButton VisionButton = new JoystickButton(JOYSTICK_DRIVE_LEFT, OI.BUTTON_VISION_ALIGN);
    VisionButton.whileHeld(new VisionTurningCommandP(VISION, DRIVE_TRAIN));

    JoystickButton HopperContinuousButton = new JoystickButton(JOYSTICK_OPERATOR, Constants.OI.BUTTON_HOPPER_CONTINUOUS);
    HopperContinuousButton.whileHeld(new RunHopperContinuouslyCommand(HOPPER, SHOOTER, Constants.Robot.MotorSpeeds.HOPPER_LEFT_BELT,
            Constants.Robot.MotorSpeeds.HOPPER_RIGHT_BELT, Constants.Robot.MotorSpeeds.HOPPER_EXIT_WHEEL, Constants.Robot.MotorSpeeds.HOPPER_BOTTOM_BELT, true));

    JoystickButton HopperContinuousButtonReverse = new JoystickButton(JOYSTICK_OPERATOR, Constants.OI.BUTTON_HOPPER_CONTINUOUS_REVERSE);
    HopperContinuousButtonReverse.whileHeld(new RunHopperContinuouslyCommand(HOPPER, SHOOTER, Constants.Robot.MotorSpeeds.HOPPER_LEFT_BELT_REVERSE,
            Constants.Robot.MotorSpeeds.HOPPER_RIGHT_BELT_REVERSE, Constants.Robot.MotorSpeeds.HOPPER_EXIT_WHEEL_REVERSE, Constants.Robot.MotorSpeeds.HOPPER_BOTTOM_BELT_REVERSE, false));

    JoystickButton RunShooterCloseButton = new JoystickButton(JOYSTICK_OPERATOR, Constants.OI.BUTTON_RUN_SHOOTER_FULL);
    RunShooterCloseButton.whenPressed(new ToggleShootCommand(SHOOTER, VISION, Constants.Robot.MotorSpeeds.SHOOTER_RPM_GENERIC_CLOSE));

    JoystickButton RunShooterTrenchButton = new JoystickButton(JOYSTICK_OPERATOR, Constants.OI.BUTTON_RUN_SHOOTER_TRENCH);
    RunShooterTrenchButton.whenPressed(new ToggleShootCommand(SHOOTER,VISION ,Constants.Robot.MotorSpeeds.SHOOTER_RPM_FAR_TRENCH));

    JoystickButton RunClimberForwardsButton = new JoystickButton(JOYSTICK_OPERATOR, Constants.OI.BUTTON_CLIMBER);
    RunClimberForwardsButton.whileHeld(new RunClimberCommand(CLIMBER, Constants.Robot.MotorSpeeds.CLIMBER_FORWARD));

    JoystickButton RunClimberReverseButton = new JoystickButton(JOYSTICK_OPERATOR, Constants.OI.BUTTON_CLIMBER_REVERSE);
    RunClimberReverseButton.whileHeld(new RunClimberCommand(CLIMBER, Constants.Robot.MotorSpeeds.CLIMBER_BACKWARD));

    JoystickButton ActuateCLimberButton = new JoystickButton(JOYSTICK_OPERATOR, OI.BUTTON_CLIMBER_ACTUATE);
    ActuateCLimberButton.whenPressed(new ActuateClimberLockCommand(CLIMBER));

    JoystickButton RunSqueezeBackwards = new JoystickButton(JOYSTICK_OPERATOR, OI.BUTTON_SQUEEZE_BACKWARDS);
    RunSqueezeBackwards.whileHeld(new RunIntakeCommand(INTAKE, HOPPER, 0, Constants.Robot.MotorSpeeds.INTAKE_SQUEEZE_SPEED_BACKWARDS, 0));
  }

  public Command getAutonomousRoutine() {
      return AutoSwitcher.getAutoInstance().getInstance(
              DRIVE_TRAIN,
              INTAKE,
              HOPPER,
              VISION,
              SHOOTER
      );

//      return AutonomousCommandGroupFactory.Shoot3RightDriveIntake3Trench(SHOOTER, HOPPER, DRIVE_TRAIN, INTAKE, VISION);
//    return new DriveStraightCommandNavX(DRIVE_TRAIN, 0.25);
    //return new VoltageDriveCommand(DRIVE_TRAIN, -0.255, -0.255);
    //return new VisionAlign(VISION, DRIVE_TRAIN);
    //return new TurnToAngleCommandNavX(DRIVE_TRAIN, 180);
  }
}