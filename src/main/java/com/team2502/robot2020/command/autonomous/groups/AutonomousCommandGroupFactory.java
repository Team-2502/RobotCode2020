package com.team2502.robot2020.command.autonomous.groups;

import com.team2502.robot2020.Constants;
import com.team2502.robot2020.command.RunHopperContinuouslyCommand;
import com.team2502.robot2020.command.ShootCommand;
import com.team2502.robot2020.command.autonomous.ingredients.VoltageDriveCommand;
import com.team2502.robot2020.subsystem.DrivetrainSubsystem;
import com.team2502.robot2020.subsystem.HopperSubsystem;
import com.team2502.robot2020.subsystem.ShooterSubsystem;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class AutonomousCommandGroupFactory {
    public static SequentialCommandGroup SimpleShoot3Balls(ShooterSubsystem shooter,
                                                           HopperSubsystem hopper,
                                                           DrivetrainSubsystem drivetrain) {
        ParallelRaceGroup spoolUpShooter = new ParallelRaceGroup(
                new ShootCommand(shooter, Constants.OI.BUTTON_RUN_SHOOTER_INIT_LINE),
                new WaitCommand(3)
        );

        ParallelRaceGroup runHopperAndShootBalls = new ParallelRaceGroup(
                new ShootCommand(shooter, Constants.OI.BUTTON_RUN_SHOOTER_INIT_LINE),
                new RunHopperContinuouslyCommand(hopper,shooter, Constants.Robot.MotorSpeeds.HOPPER_LEFT_BELT,
                        Constants.Robot.MotorSpeeds.HOPPER_RIGHT_BELT, Constants.Robot.MotorSpeeds.HOPPER_EXIT_WHEEL, true),
                new WaitCommand(5)
        );

        ParallelRaceGroup driveBackFromInitLine = new ParallelRaceGroup(
                new VoltageDriveCommand(drivetrain, -0.5, -0.5),
                new WaitCommand(1)
        );

        return new SequentialCommandGroup(
                spoolUpShooter,
                runHopperAndShootBalls,
                driveBackFromInitLine
        );
    }
}
