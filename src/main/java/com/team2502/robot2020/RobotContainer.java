/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team2502.robot2020;

import com.team2502.robot2020.command.*;
import com.team2502.robot2020.command.autonomous.groups.AutonCommandGroupFactory;
import com.team2502.robot2020.subsystem.ClimberSubsystem;
import com.team2502.robot2020.subsystem.DrivetrainSubsystem;
import com.team2502.robot2020.subsystem.ShooterSubsystem;
import com.team2502.robot2020.subsystem.solenoid.ShiftingSolenoid;
import com.team2502.robot2020.subsystem.IntakeSubSystem;
import com.team2502.robot2020.subsystem.HopperSubsystem;
import com.team2502.robot2020.subsystem.VisionSubsystem;
import com.team2502.robot2020.Constants.OI;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
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
  private static final IntakeSubSystem INTAKE = new IntakeSubSystem();
  private static final HopperSubsystem HOPPER = new HopperSubsystem();
  private final VisionSubsystem VISION = new VisionSubsystem();
  private static final ShooterSubsystem SHOOTER = new ShooterSubsystem();

  public static final ShiftingSolenoid SHIFTING_SOLENOID = new ShiftingSolenoid();

  public static final Joystick JOYSTICK_DRIVE_RIGHT = new Joystick(Constants.OI.JOYSTICK_DRIVE_RIGHT);
  public static final Joystick JOYSTICK_DRIVE_LEFT = new Joystick(Constants.OI.JOYSTICK_DRIVE_LEFT);
  public static final Joystick JOYSTICK_OPERATOR = new Joystick(Constants.OI.JOYSTICK_OPERATOR);


    /**
     * The container for the robot.  Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        configureButtonBindings();

    DRIVE_TRAIN.setDefaultCommand(
            new DriveCommand(DRIVE_TRAIN, JOYSTICK_DRIVE_LEFT, JOYSTICK_DRIVE_RIGHT));

      Compressor COMPRESSOR = new Compressor();
      COMPRESSOR.start();
  }

  private void configureButtonBindings() {
    JoystickButton ActuateButton = new JoystickButton(JOYSTICK_OPERATOR,Constants.OI.BUTTON_ACTUATE_INTAKE);
    JoystickButton RunIntakeButton = new JoystickButton(JOYSTICK_OPERATOR,Constants.OI.BUTTON_RUN_INTAKE);
    JoystickButton RunIntakeBackwardsButton = new JoystickButton(JOYSTICK_OPERATOR,Constants.OI.BUTTON_RUN_INTAKE_BACKWARDS);

    ActuateButton.whenPressed(new ActuateIntakeCommand(INTAKE));
    RunIntakeButton.whileHeld(new RunIntakeCommand(INTAKE, Constants.Robot.MotorSpeeds.INTAKE_SPEED_FORWARD));
    RunIntakeBackwardsButton.whileHeld(new RunIntakeCommand(INTAKE, Constants.Robot.MotorSpeeds.INTAKE_SPEED_BACKWARDS));

    JoystickButton ShiftButton = new JoystickButton(JOYSTICK_DRIVE_RIGHT, Constants.OI.BUTTON_SHIFT);
    ShiftButton.whenPressed(new ShiftDrivetrainCommand(SHIFTING_SOLENOID));

    JoystickButton VisionButton = new JoystickButton(JOYSTICK_DRIVE_LEFT, OI.BUTTON_VISION_ALIGN);
    VisionButton.whileHeld(new VisionTurningCommandP(VISION, DRIVE_TRAIN));

    JoystickButton HopperContinuousButton = new JoystickButton(JOYSTICK_OPERATOR, Constants.OI.BUTTON_HOPPER_CONTINUOUS);
    HopperContinuousButton.whileHeld(new RunHopperContinuouslyCommand(HOPPER, SHOOTER, false));

    JoystickButton HopperContinuousButtonReverse = new JoystickButton(JOYSTICK_OPERATOR, Constants.OI.BUTTON_HOPPER_CONTINUOUS_REVERSE);
    HopperContinuousButtonReverse.whileHeld(new RunHopperContinuouslyCommand(HOPPER, SHOOTER, true));

    JoystickButton RunShooterFullButton = new JoystickButton(JOYSTICK_OPERATOR, Constants.OI.BUTTON_RUN_SHOOTER_FULL);
    RunShooterFullButton.whileHeld(new ShootCommand(VISION, SHOOTER, Constants.Robot.MotorSpeeds.SHOOTER_FULL));

    JoystickButton RunShooterTrenchButton = new JoystickButton(JOYSTICK_OPERATOR, Constants.OI.BUTTON_RUN_SHOOTER_TRENCH);
    RunShooterTrenchButton.whileHeld(new ShootCommand(VISION, SHOOTER, Constants.Robot.MotorSpeeds.SHOOTER_TRENCH));

    JoystickButton RunShooterInitLineButton = new JoystickButton(JOYSTICK_OPERATOR, Constants.OI.BUTTON_RUN_SHOOTER_INIT_LINE);
    RunShooterInitLineButton.whileHeld(new ShootCommand(VISION, SHOOTER, Constants.Robot.MotorSpeeds.SHOOTER_INIT_LINE));

    JoystickButton RunClimberButton = new JoystickButton(JOYSTICK_OPERATOR, Constants.OI.BUTTON_CLIMBER);
    RunClimberButton.whileHeld(new RunClimberCommand(CLIMBER, false));

    JoystickButton RunClimberReverseButton = new JoystickButton(JOYSTICK_OPERATOR, Constants.OI.BUTTON_CLIMBER_REVERSE);
    RunClimberReverseButton.whileHeld(new RunClimberCommand(CLIMBER, true));

    JoystickButton ActuateCLimberButton = new JoystickButton(JOYSTICK_OPERATOR, OI.BUTTON_CLIMBER_ACTUATE);
    ActuateCLimberButton.whenPressed(new ActuateClimberCommand(CLIMBER));
  }

  public Command getAutonomousRoutine() {
      return AutonCommandGroupFactory.SimpleShoot3Balls(SHOOTER, VISION, HOPPER, DRIVE_TRAIN);
  }
}