package com.team2502.robot2020.command.autonomous.groups;

import com.team2502.robot2020.Constants;
import com.team2502.robot2020.command.RunHopperContinuouslyCommand;
import com.team2502.robot2020.command.RunIntakeCommand;
import com.team2502.robot2020.command.ShootCommand;
import com.team2502.robot2020.command.autonomous.ingredients.TurnToAngleCommandNavX;
import com.team2502.robot2020.command.autonomous.ingredients.VoltageDriveCommand;
import com.team2502.robot2020.subsystem.DrivetrainSubsystem;
import com.team2502.robot2020.subsystem.HopperSubsystem;
import com.team2502.robot2020.subsystem.IntakeSubsystem;
import com.team2502.robot2020.subsystem.ShooterSubsystem;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class AutonomousCommandGroupFactory {
    public static SequentialCommandGroup Shoot3BackAndLeftIntake(ShooterSubsystem shooter,
                                                                 HopperSubsystem hopper,
                                                                 DrivetrainSubsystem drivetrain,
                                                                 IntakeSubsystem intake) {
        ParallelRaceGroup spoolUpShooter = new ParallelRaceGroup(
                new ShootCommand(shooter, Constants.Robot.MotorSpeeds.SHOOTER_HALF_RPM),
                new WaitCommand(3)
        );

        ParallelRaceGroup runHopperAndShootBalls = new ParallelRaceGroup(
                new ShootCommand(shooter, Constants.Robot.MotorSpeeds.SHOOTER_HALF_RPM),
                new RunHopperContinuouslyCommand(hopper,shooter, Constants.Robot.MotorSpeeds.HOPPER_LEFT_BELT,
                        Constants.Robot.MotorSpeeds.HOPPER_RIGHT_BELT, Constants.Robot.MotorSpeeds.HOPPER_EXIT_WHEEL,
                        Constants.Robot.MotorSpeeds.HOPPER_BOTTOM_BELT, false),
                new WaitCommand(5)
        );

        ParallelRaceGroup driveBackFromInitLine = new ParallelRaceGroup(
                new VoltageDriveCommand(drivetrain, -0.8, -0.8),
                new WaitCommand(0.55)
        );

        ParallelRaceGroup stopRobot = new ParallelRaceGroup(
                new VoltageDriveCommand(drivetrain, 0,0),
                new WaitCommand(0.5)
        );

        ParallelRaceGroup turnRobot = new ParallelRaceGroup(
                new TurnToAngleCommandNavX(drivetrain, 120),
            new WaitCommand(2)
        );

        ParallelRaceGroup intakeBall = new ParallelRaceGroup(
                new RunIntakeCommand(intake, Constants.Robot.MotorSpeeds.INTAKE_SPEED_FORWARD, Constants.Robot.MotorSpeeds.INTAKE_SQUEEZE_SPEED_FORWARDS),
                new VoltageDriveCommand(drivetrain, 0.5, 0.5),
                new WaitCommand(2)
        );

        return new SequentialCommandGroup(
                spoolUpShooter,
                runHopperAndShootBalls,
                driveBackFromInitLine,
                stopRobot,
                turnRobot,
                intakeBall
        );
    }

    public static SequentialCommandGroup Shoot3BackAndRightIntakeAndShootOne(ShooterSubsystem shooter,
                                                                 HopperSubsystem hopper,
                                                                 DrivetrainSubsystem drivetrain,
                                                                 IntakeSubsystem intake) {
        ParallelRaceGroup spoolUpShooter = new ParallelRaceGroup(
                new ShootCommand(shooter, Constants.Robot.MotorSpeeds.SHOOTER_HALF_RPM),
                new WaitCommand(2)
        );

        ParallelRaceGroup runHopperAndShootBalls = new ParallelRaceGroup(
                new ShootCommand(shooter, Constants.Robot.MotorSpeeds.SHOOTER_HALF_RPM),
                new RunHopperContinuouslyCommand(hopper,shooter, Constants.Robot.MotorSpeeds.HOPPER_LEFT_BELT,
                        Constants.Robot.MotorSpeeds.HOPPER_RIGHT_BELT, Constants.Robot.MotorSpeeds.HOPPER_EXIT_WHEEL,
                        Constants.Robot.MotorSpeeds.HOPPER_BOTTOM_BELT, false),
                new WaitCommand(3)
        );

        ParallelRaceGroup driveBackFromInitLine = new ParallelRaceGroup(
                new VoltageDriveCommand(drivetrain, -0.8, -0.8),
                new WaitCommand(0.7)
        );

        ParallelRaceGroup stopRobot = new ParallelRaceGroup(
                new VoltageDriveCommand(drivetrain, 0,0),
                new WaitCommand(0.3)
        );

        ParallelRaceGroup turnRobot = new ParallelRaceGroup(
                new TurnToAngleCommandNavX(drivetrain, -120),
                new WaitCommand(2)
        );

        ParallelRaceGroup intakeBall = new ParallelRaceGroup(
                new RunIntakeCommand(intake, Constants.Robot.MotorSpeeds.INTAKE_SPEED_FORWARD, Constants.Robot.MotorSpeeds.INTAKE_SQUEEZE_SPEED_FORWARDS),
                new VoltageDriveCommand(drivetrain, 0.7, 0.7),
                new WaitCommand(1.5)
        );

        ParallelRaceGroup stopRobotAgain = new ParallelRaceGroup(
                new RunIntakeCommand(intake, Constants.Robot.MotorSpeeds.INTAKE_SPEED_FORWARD, Constants.Robot.MotorSpeeds.INTAKE_SQUEEZE_SPEED_FORWARDS),
                new VoltageDriveCommand(drivetrain, -0.4,-0.4),
                new ShootCommand(shooter, 3950),
                new WaitCommand(1.5)
        );

        ParallelRaceGroup turnBack = new ParallelRaceGroup(
                new RunIntakeCommand(intake, Constants.Robot.MotorSpeeds.INTAKE_SPEED_FORWARD, Constants.Robot.MotorSpeeds.INTAKE_SQUEEZE_SPEED_FORWARDS),
                new ShootCommand(shooter, 3950),
                new TurnToAngleCommandNavX(drivetrain, 5),
                new RunHopperContinuouslyCommand(hopper,shooter, Constants.Robot.MotorSpeeds.HOPPER_LEFT_BELT,
                        Constants.Robot.MotorSpeeds.HOPPER_RIGHT_BELT, Constants.Robot.MotorSpeeds.HOPPER_EXIT_WHEEL,
                        Constants.Robot.MotorSpeeds.HOPPER_BOTTOM_BELT, false),
                new WaitCommand(2)
        );

        ParallelRaceGroup runHopperAndShootBallsAgain = new ParallelRaceGroup(
                new VoltageDriveCommand(drivetrain, 0, 0),
                new ShootCommand(shooter, 3950),
                new RunHopperContinuouslyCommand(hopper,shooter, Constants.Robot.MotorSpeeds.HOPPER_LEFT_BELT,
                        Constants.Robot.MotorSpeeds.HOPPER_RIGHT_BELT, Constants.Robot.MotorSpeeds.HOPPER_EXIT_WHEEL,
                        Constants.Robot.MotorSpeeds.HOPPER_BOTTOM_BELT, false),
                new WaitCommand(2.5)
        );

        return new SequentialCommandGroup(
                spoolUpShooter,
                runHopperAndShootBalls,
                driveBackFromInitLine,
                stopRobot,
                turnRobot,
                intakeBall,
                stopRobotAgain,
                turnBack,
                runHopperAndShootBallsAgain
        );
    }

    public static SequentialCommandGroup Shoot3FromLeftBack(ShooterSubsystem shooter,
                                                                 HopperSubsystem hopper,
                                                                 DrivetrainSubsystem drivetrain,
                                                                 IntakeSubsystem intake) {
        ParallelRaceGroup spoolUpShooter = new ParallelRaceGroup(
                new ShootCommand(shooter, Constants.Robot.MotorSpeeds.SHOOTER_HALF_RPM),
                new WaitCommand(3)
        );

        ParallelRaceGroup runHopperAndShootBalls = new ParallelRaceGroup(
                new ShootCommand(shooter, Constants.Robot.MotorSpeeds.SHOOTER_HALF_RPM),
                new RunHopperContinuouslyCommand(hopper,shooter, Constants.Robot.MotorSpeeds.HOPPER_LEFT_BELT,
                        Constants.Robot.MotorSpeeds.HOPPER_RIGHT_BELT, Constants.Robot.MotorSpeeds.HOPPER_EXIT_WHEEL,
                        Constants.Robot.MotorSpeeds.HOPPER_BOTTOM_BELT, false),
                new WaitCommand(5)
        );

        ParallelRaceGroup driveBackFromInitLine = new ParallelRaceGroup(
                new VoltageDriveCommand(drivetrain, -0.8, -0.8),
                new WaitCommand(0.1)
        );

        ParallelRaceGroup stopRobot = new ParallelRaceGroup(
                new VoltageDriveCommand(drivetrain, 0,0),
                new WaitCommand(0.5)
        );

        ParallelRaceGroup turnRobot = new ParallelRaceGroup(
                new TurnToAngleCommandNavX(drivetrain, 165),
                new WaitCommand(2)
        );

        ParallelRaceGroup intakeBall = new ParallelRaceGroup(
                new RunIntakeCommand(intake, Constants.Robot.MotorSpeeds.INTAKE_SPEED_FORWARD, Constants.Robot.MotorSpeeds.INTAKE_SQUEEZE_SPEED_FORWARDS),
                new VoltageDriveCommand(drivetrain, 0.7, 0.7),
                new WaitCommand(1.5)
        );

        ParallelRaceGroup stopRobotAgain = new ParallelRaceGroup(
                new RunIntakeCommand(intake, Constants.Robot.MotorSpeeds.INTAKE_SPEED_FORWARD, Constants.Robot.MotorSpeeds.INTAKE_SQUEEZE_SPEED_FORWARDS),
                new VoltageDriveCommand(drivetrain, 0,0),
                new ShootCommand(shooter, 3950),
                new WaitCommand(1.5)
        );

        ParallelRaceGroup turnBack = new ParallelRaceGroup(
                new RunIntakeCommand(intake, Constants.Robot.MotorSpeeds.INTAKE_SPEED_FORWARD, Constants.Robot.MotorSpeeds.INTAKE_SQUEEZE_SPEED_FORWARDS),
                new ShootCommand(shooter, 3950),
                new TurnToAngleCommandNavX(drivetrain, 0),
                new RunHopperContinuouslyCommand(hopper,shooter, Constants.Robot.MotorSpeeds.HOPPER_LEFT_BELT,
                        Constants.Robot.MotorSpeeds.HOPPER_RIGHT_BELT, Constants.Robot.MotorSpeeds.HOPPER_EXIT_WHEEL,
                        Constants.Robot.MotorSpeeds.HOPPER_BOTTOM_BELT, false),
                new WaitCommand(2)
        );

        ParallelRaceGroup runHopperAndShootBallsAgain = new ParallelRaceGroup(
                new VoltageDriveCommand(drivetrain, 0, 0),
                new ShootCommand(shooter, 3950),
                new RunHopperContinuouslyCommand(hopper,shooter, Constants.Robot.MotorSpeeds.HOPPER_LEFT_BELT,
                        Constants.Robot.MotorSpeeds.HOPPER_RIGHT_BELT, Constants.Robot.MotorSpeeds.HOPPER_EXIT_WHEEL,
                        Constants.Robot.MotorSpeeds.HOPPER_BOTTOM_BELT, false),
                new WaitCommand(2.5)
        );

        return new SequentialCommandGroup(
                spoolUpShooter,
                runHopperAndShootBalls,
                driveBackFromInitLine,
                stopRobot,
                turnRobot,
                intakeBall,
                stopRobotAgain,
                runHopperAndShootBallsAgain
        );
    }

}
