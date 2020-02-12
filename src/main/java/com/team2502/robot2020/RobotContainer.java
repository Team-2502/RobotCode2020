/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team2502.robot2020;

import com.team2502.robot2020.command.ShootCommand;
import com.team2502.robot2020.subsystem.ShooterSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import com.team2502.robot2020.command.VisionTurningCommandP;
import com.team2502.robot2020.command.VisionTurningCommandPID;
import com.team2502.robot2020.subsystem.DrivetrainSubsystem;
import com.team2502.robot2020.subsystem.VisionSubsystem;
import com.team2502.robot2020.Constants.OI;
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
    private final VisionSubsystem VISION = new VisionSubsystem();

    public final Joystick JOYSTICK_DRIVE_RIGHT = new Joystick(OI.JOYSTICK_DRIVE_RIGHT);
    private final Joystick JOYSTICK_DRIVE_LEFT = new Joystick(OI.JOYSTICK_DRIVE_LEFT);

    public static final ShooterSubsystem SHOOTER = new ShooterSubsystem();

    public static final Joystick JOYSTICK_FUNCTION = new Joystick(Constants.OI.JOYSTICK_FUNCTION);


    /**
     * The container for the robot.  Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        configureButtonBindings();


        // Configure default commands
        DRIVE_TRAIN.setDefaultCommand(
                new RunCommand(() -> DRIVE_TRAIN.drive.tankDrive(-JOYSTICK_DRIVE_LEFT.getY(), -JOYSTICK_DRIVE_RIGHT.getY()), DRIVE_TRAIN));

    }

    /**
     * Use this method to define your button->command mappings.  Buttons can be created by
     * instantiating a {GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {XboxController}), and then passing it to a
     * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        final JoystickButton RUN_SHOOTER = new JoystickButton(JOYSTICK_FUNCTION, Constants.OI.RUN_SHOOTER);

        JoystickButton pidVisionButton = new JoystickButton(JOYSTICK_DRIVE_RIGHT, OI.BUTTON_VISION_ALIGN);
        JoystickButton visionButton = new JoystickButton(JOYSTICK_DRIVE_LEFT, OI.BUTTON_VISION_ALIGN);
        RUN_SHOOTER.whileHeld(new ShootCommand());

        visionButton.whileHeld(new VisionTurningCommandP(VISION, DRIVE_TRAIN));
        pidVisionButton.whileHeld(new VisionTurningCommandPID(VISION, DRIVE_TRAIN));
    }


}


