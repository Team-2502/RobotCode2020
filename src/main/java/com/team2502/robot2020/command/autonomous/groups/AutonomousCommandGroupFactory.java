package com.team2502.robot2020.command.autonomous.groups;

import com.team2502.robot2020.Constants;
import com.team2502.robot2020.command.*;
import com.team2502.robot2020.command.autonomous.ingredients.*;
import com.team2502.robot2020.subsystem.*;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class AutonomousCommandGroupFactory {
    private static SequentialCommandGroup SpoolUpShooterAndShoot(ShooterSubsystem shooter, HopperSubsystem hopper, double speed, double hopperRunTime) {
        ParallelRaceGroup spoolUpShooter = new ParallelRaceGroup(
                new ShootAtRPMCommand(shooter, speed),
                new WaitCommand(2)
        );

        ParallelRaceGroup runHopperAndShootBalls = new ParallelRaceGroup(
                new ShootAtRPMCommand(shooter, speed),
                new RunHopperContinuouslyCommand(hopper, shooter, Constants.Robot.MotorSpeeds.HOPPER_LEFT_BELT,
                        Constants.Robot.MotorSpeeds.HOPPER_RIGHT_BELT, Constants.Robot.MotorSpeeds.HOPPER_EXIT_WHEEL,
                        Constants.Robot.MotorSpeeds.HOPPER_BOTTOM_BELT, false),
                new WaitCommand(hopperRunTime)
        );

        return new SequentialCommandGroup(
                spoolUpShooter,
                runHopperAndShootBalls
        );
    }

    private static SequentialCommandGroup VoltageDriveRace(DrivetrainSubsystem drivetrain, double leftSpeed, double rightSpeed, double timeout) {
        ParallelRaceGroup drive = new ParallelRaceGroup(
                new VoltageDriveCommand(drivetrain, leftSpeed, rightSpeed),
                new WaitCommand(timeout)
        );

        return new SequentialCommandGroup(drive);
    }

    public static SequentialCommandGroup Shoot3BackAndIntakeRightSideRendezvous(DrivetrainSubsystem drivetrain,
                                                                                IntakeSubsystem intake,
                                                                                HopperSubsystem hopper,
                                                                                VisionSubsystem vision,
                                                                                ShooterSubsystem shooter) {
        ParallelRaceGroup spoolUpShooter = new ParallelRaceGroup(
                new ShootAtRPMCommand(shooter, Constants.LookupTables.DIST_TO_RPM_TABLE.get(10D)),
                new WaitCommand(3)
        );

        ParallelRaceGroup runHopperAndShootBalls = new ParallelRaceGroup(
                new ShootAtRPMCommand(shooter, Constants.LookupTables.DIST_TO_RPM_TABLE.get(10D)),
                new RunHopperContinuouslyCommand(hopper, shooter, Constants.Robot.MotorSpeeds.HOPPER_LEFT_BELT,
                        Constants.Robot.MotorSpeeds.HOPPER_RIGHT_BELT, Constants.Robot.MotorSpeeds.HOPPER_EXIT_WHEEL,
                        Constants.Robot.MotorSpeeds.HOPPER_BOTTOM_BELT, false),
                new WaitCommand(5)
        );

        ParallelRaceGroup driveBackFromInitLine = new ParallelRaceGroup(
                new VoltageDriveCommand(drivetrain, -0.8, -0.8),
                new WaitCommand(0.55)
        );

        ParallelRaceGroup stopRobot = new ParallelRaceGroup(
                new VoltageDriveCommand(drivetrain, 0, 0),
                new WaitCommand(0.5)
        );

        ParallelRaceGroup turnRobot = new ParallelRaceGroup(
                new TurnToAngleCommandNavX(drivetrain, 120),
                new WaitCommand(2)
        );

        ParallelRaceGroup intakeBall = new ParallelRaceGroup(
                new RunIntakeCommand(intake, hopper, Constants.Robot.MotorSpeeds.INTAKE_SPEED_FORWARD, Constants.Robot.MotorSpeeds.INTAKE_SQUEEZE_SPEED_FORWARDS, Constants.Robot.MotorSpeeds.HOPPER_BOTTOM_BELT_INTAKE),
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
                                                                             IntakeSubsystem intake, VisionSubsystem vision) {
        ParallelRaceGroup spoolUpShooter = new ParallelRaceGroup(
                new ShootAtRPMCommand(shooter, Constants.LookupTables.DIST_TO_RPM_TABLE.get(10D)),
                new WaitCommand(2)
        );

        ParallelRaceGroup runHopperAndShootBalls = new ParallelRaceGroup(
                new ShootAtRPMCommand(shooter, Constants.LookupTables.DIST_TO_RPM_TABLE.get(10D)),
                new RunHopperContinuouslyCommand(hopper, shooter, Constants.Robot.MotorSpeeds.HOPPER_LEFT_BELT,
                        Constants.Robot.MotorSpeeds.HOPPER_RIGHT_BELT, Constants.Robot.MotorSpeeds.HOPPER_EXIT_WHEEL,
                        Constants.Robot.MotorSpeeds.HOPPER_BOTTOM_BELT, false),
                new WaitCommand(3)
        );

        ParallelRaceGroup driveBackFromInitLine = new ParallelRaceGroup(
                new VoltageDriveCommand(drivetrain, -0.8, -0.8),
                new WaitCommand(0.7)
        );

        ParallelRaceGroup stopRobot = new ParallelRaceGroup(
                new VoltageDriveCommand(drivetrain, 0, 0),
                new WaitCommand(0.3)
        );

        ParallelRaceGroup turnRobot = new ParallelRaceGroup(
                new TurnToAngleCommandNavX(drivetrain, -120),
                new WaitCommand(2)
        );

        ParallelRaceGroup intakeBall = new ParallelRaceGroup(
                new RunIntakeCommand(intake, hopper, Constants.Robot.MotorSpeeds.INTAKE_SPEED_FORWARD, Constants.Robot.MotorSpeeds.INTAKE_SQUEEZE_SPEED_FORWARDS, Constants.Robot.MotorSpeeds.HOPPER_BOTTOM_BELT_INTAKE),
                new VoltageDriveCommand(drivetrain, 0.7, 0.7),
                new WaitCommand(1.5)
        );

        ParallelRaceGroup stopRobotAgain = new ParallelRaceGroup(
                new RunIntakeCommand(intake, hopper, Constants.Robot.MotorSpeeds.INTAKE_SPEED_FORWARD, Constants.Robot.MotorSpeeds.INTAKE_SQUEEZE_SPEED_FORWARDS, Constants.Robot.MotorSpeeds.HOPPER_BOTTOM_BELT_INTAKE),
                new VoltageDriveCommand(drivetrain, -0.4, -0.4),
                new ShootAtRPMCommand(shooter, Constants.LookupTables.DIST_TO_RPM_TABLE.get(25D)),
                new WaitCommand(1.5)
        );

        ParallelRaceGroup turnBack = new ParallelRaceGroup(
                new RunIntakeCommand(intake, hopper, Constants.Robot.MotorSpeeds.INTAKE_SPEED_FORWARD, Constants.Robot.MotorSpeeds.INTAKE_SQUEEZE_SPEED_FORWARDS, Constants.Robot.MotorSpeeds.HOPPER_BOTTOM_BELT_INTAKE),
                new ShootAtRPMCommand(shooter, Constants.LookupTables.DIST_TO_RPM_TABLE.get(25D)),
                new TurnToAngleCommandNavX(drivetrain, 10),
                new WaitCommand(2)
        );

        ParallelRaceGroup visionAllign = new ParallelRaceGroup(
                new VisionAlign(vision, drivetrain),
                new WaitCommand(1)
        );

        ParallelRaceGroup runHopperAndShootBallsAgain = new ParallelRaceGroup(
                new VoltageDriveCommand(drivetrain, 0, 0),
                new ShootAtRPMCommand(shooter, Constants.LookupTables.DIST_TO_RPM_TABLE.get(25D)),
                new RunHopperContinuouslyCommand(hopper, shooter, Constants.Robot.MotorSpeeds.HOPPER_LEFT_BELT,
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
                visionAllign,
                runHopperAndShootBallsAgain
        );
    }

    public static SequentialCommandGroup Shoot3BackAndShootLeftSideRendezvous(DrivetrainSubsystem drivetrain,
                                                                              IntakeSubsystem intake,
                                                                              HopperSubsystem hopper,
                                                                              VisionSubsystem vision,
                                                                              ShooterSubsystem shooter) {
        ParallelRaceGroup spoolUpShooter = new ParallelRaceGroup(
                new ShootAtRPMCommand(shooter, Constants.LookupTables.DIST_TO_RPM_TABLE.get(10D)),
                new WaitCommand(3)
        );

        ParallelRaceGroup runHopperAndShootBalls = new ParallelRaceGroup(
                new ShootAtRPMCommand(shooter, Constants.LookupTables.DIST_TO_RPM_TABLE.get(10D)),
                new RunHopperContinuouslyCommand(hopper, shooter, Constants.Robot.MotorSpeeds.HOPPER_LEFT_BELT,
                        Constants.Robot.MotorSpeeds.HOPPER_RIGHT_BELT, Constants.Robot.MotorSpeeds.HOPPER_EXIT_WHEEL,
                        Constants.Robot.MotorSpeeds.HOPPER_BOTTOM_BELT, false),
                new WaitCommand(5)
        );

        ParallelRaceGroup driveBackFromInitLine = new ParallelRaceGroup(
                new VoltageDriveCommand(drivetrain, -0.8, -0.8),
                new WaitCommand(0.1)
        );

        ParallelRaceGroup stopRobot = new ParallelRaceGroup(
                new VoltageDriveCommand(drivetrain, 0, 0),
                new WaitCommand(0.5)
        );

        ParallelRaceGroup turnRobot = new ParallelRaceGroup(
                new TurnToAngleCommandNavX(drivetrain, 165),
                new WaitCommand(2)
        );

        ParallelRaceGroup intakeBall = new ParallelRaceGroup(
                new RunIntakeCommand(intake, hopper, Constants.Robot.MotorSpeeds.INTAKE_SPEED_FORWARD, Constants.Robot.MotorSpeeds.INTAKE_SQUEEZE_SPEED_FORWARDS, Constants.Robot.MotorSpeeds.HOPPER_BOTTOM_BELT_INTAKE),
                new VoltageDriveCommand(drivetrain, 0.7, 0.7),
                new WaitCommand(1.5)
        );

        ParallelRaceGroup stopRobotAgain = new ParallelRaceGroup(
                new RunIntakeCommand(intake, hopper, Constants.Robot.MotorSpeeds.INTAKE_SPEED_FORWARD, Constants.Robot.MotorSpeeds.INTAKE_SQUEEZE_SPEED_FORWARDS, Constants.Robot.MotorSpeeds.HOPPER_BOTTOM_BELT_INTAKE),
                new VoltageDriveCommand(drivetrain, 0, 0),
                new ShootAtRPMCommand(shooter, Constants.LookupTables.DIST_TO_RPM_TABLE.get(25D)),
                new WaitCommand(1.5)
        );

        ParallelRaceGroup turnBack = new ParallelRaceGroup(
                new RunIntakeCommand(intake, hopper, Constants.Robot.MotorSpeeds.INTAKE_SPEED_FORWARD, Constants.Robot.MotorSpeeds.INTAKE_SQUEEZE_SPEED_FORWARDS, Constants.Robot.MotorSpeeds.HOPPER_BOTTOM_BELT_INTAKE),
                new ShootAtRPMCommand(shooter, Constants.LookupTables.DIST_TO_RPM_TABLE.get(25D)),
                new TurnToAngleCommandNavX(drivetrain, 0),
                new RunHopperContinuouslyCommand(hopper, shooter, Constants.Robot.MotorSpeeds.HOPPER_LEFT_BELT,
                        Constants.Robot.MotorSpeeds.HOPPER_RIGHT_BELT, Constants.Robot.MotorSpeeds.HOPPER_EXIT_WHEEL,
                        Constants.Robot.MotorSpeeds.HOPPER_BOTTOM_BELT, false),
                new WaitCommand(2)
        );

        ParallelRaceGroup runHopperAndShootBallsAgain = new ParallelRaceGroup(
                new VoltageDriveCommand(drivetrain, 0, 0),
                new ShootAtRPMCommand(shooter, Constants.LookupTables.DIST_TO_RPM_TABLE.get(25D)),
                new RunHopperContinuouslyCommand(hopper, shooter, Constants.Robot.MotorSpeeds.HOPPER_LEFT_BELT,
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

    public static SequentialCommandGroup Shoot3RightDriveIntake3Trench(DrivetrainSubsystem drivetrain,
                                                                       IntakeSubsystem intake,
                                                                       HopperSubsystem hopper,
                                                                       VisionSubsystem vision,
                                                                       ShooterSubsystem shooter) {

        ParallelRaceGroup spoolUpShooter = new ParallelRaceGroup(
                new ShootAtRPMCommand(shooter, Constants.LookupTables.DIST_TO_RPM_TABLE.get(10D)),
                new TurnToAngleCommandNavX(drivetrain, 22),
                new WaitCommand(2)
        );

        ParallelRaceGroup runHopperAndShootBalls = new ParallelRaceGroup(
                new ShootAtRPMCommand(shooter, Constants.LookupTables.DIST_TO_RPM_TABLE.get(10D)),
                new RunHopperContinuouslyCommand(hopper, shooter, Constants.Robot.MotorSpeeds.HOPPER_LEFT_BELT,
                        Constants.Robot.MotorSpeeds.HOPPER_RIGHT_BELT, Constants.Robot.MotorSpeeds.HOPPER_EXIT_WHEEL,
                        Constants.Robot.MotorSpeeds.HOPPER_BOTTOM_BELT, false),
                new WaitCommand(2)
        );

        ParallelRaceGroup TurnToBalls = new ParallelRaceGroup(
                new TurnToAngleCommandNavX(drivetrain, 176),
                new WaitCommand(1)
        );

        ParallelRaceGroup driveThroughTrench = new ParallelRaceGroup(
                new DriveStraightCommandNavX(drivetrain, 0.6, 180),
                new RunIntakeCommand(intake, hopper, Constants.Robot.MotorSpeeds.INTAKE_SPEED_FORWARD, Constants.Robot.MotorSpeeds.INTAKE_SQUEEZE_SPEED_FORWARDS, Constants.Robot.MotorSpeeds.HOPPER_BOTTOM_BELT_INTAKE),
                new WaitCommand(3.5)
        );

        ParallelRaceGroup stopandIntake = new ParallelRaceGroup(
                new VoltageDriveCommand(drivetrain, 0, 0),
                new ShootAtRPMCommand(shooter, Constants.LookupTables.DIST_TO_RPM_TABLE.get(30D)),
                new RunIntakeCommand(intake, hopper, Constants.Robot.MotorSpeeds.INTAKE_SPEED_FORWARD, Constants.Robot.MotorSpeeds.INTAKE_SQUEEZE_SPEED_FORWARDS, Constants.Robot.MotorSpeeds.HOPPER_BOTTOM_BELT_INTAKE),
                new WaitCommand(1.5)
        );

        ParallelRaceGroup TurnToTarget = new ParallelRaceGroup(
                new TurnToAngleCommandNavX(drivetrain, 22),
                new RunIntakeCommand(intake, hopper, Constants.Robot.MotorSpeeds.INTAKE_SPEED_FORWARD, Constants.Robot.MotorSpeeds.INTAKE_SQUEEZE_SPEED_FORWARDS, Constants.Robot.MotorSpeeds.HOPPER_BOTTOM_BELT_INTAKE),
                new WaitCommand(1.5)
        );

        ParallelRaceGroup VisionAlignment = new ParallelRaceGroup(
                new VisionMovingWhileAligningAutoCommandP(vision, drivetrain, 0.4),
                new WaitCommand(1)
        );
        ParallelCommandGroup runHopperAndShootBallsAligning = new ParallelCommandGroup(
                new RunHopperContinuouslyCommand(hopper, shooter, Constants.Robot.MotorSpeeds.HOPPER_LEFT_BELT,
                        Constants.Robot.MotorSpeeds.HOPPER_RIGHT_BELT, Constants.Robot.MotorSpeeds.HOPPER_EXIT_WHEEL,
                        Constants.Robot.MotorSpeeds.HOPPER_BOTTOM_BELT, false),
                new WaitCommand(2)
        );


        return new SequentialCommandGroup(
                spoolUpShooter,
                runHopperAndShootBalls,
                TurnToBalls,
                driveThroughTrench,
                stopandIntake,
                new RunShooterCommand(shooter, vision, Constants.LookupTables.DIST_TO_RPM_TABLE.get(25D)),
                TurnToTarget,
                VisionAlignment,
                runHopperAndShootBallsAligning
        );
    }

    public static SequentialCommandGroup Shoot3CenterMoveBackwards(DrivetrainSubsystem drivetrain, IntakeSubsystem i, HopperSubsystem hopper, VisionSubsystem v, ShooterSubsystem shooter) {

        return new SequentialCommandGroup(
                SpoolUpShooterAndShoot(shooter, hopper, Constants.LookupTables.DIST_TO_RPM_TABLE.get(10D), 3),
                VoltageDriveRace(drivetrain, -0.8, -0.8, 0.55)
        );
    }

    public static SequentialCommandGroup Shoot3CenterMoveForwards(DrivetrainSubsystem drivetrain, IntakeSubsystem i, HopperSubsystem hopper, VisionSubsystem v, ShooterSubsystem shooter) {

        return new SequentialCommandGroup(
                SpoolUpShooterAndShoot(shooter, hopper, Constants.LookupTables.DIST_TO_RPM_TABLE.get(10D), 3),
                VoltageDriveRace(drivetrain, 0.8, 0.8, 0.55)
        );
    }
}
