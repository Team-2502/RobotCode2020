package com.team2502.robot2020.command;

import com.team2502.robot2020.Constants;
import com.team2502.robot2020.command.autonomous.ingredients.VisionAlign;
import com.team2502.robot2020.subsystem.DrivetrainSubsystem;
import com.team2502.robot2020.subsystem.HopperSubsystem;
import com.team2502.robot2020.subsystem.ShooterSubsystem;
import com.team2502.robot2020.subsystem.VisionSubsystem;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class GodShootCommand extends SequentialCommandGroup {
    double speed;

    public GodShootCommand(DrivetrainSubsystem drivetrain, ShooterSubsystem shooter, HopperSubsystem hopper, VisionSubsystem vision){
         speed = shooter.findRPMFromDistance( vision.getDistance() / 12f);

        addCommands(
            new ParallelRaceGroup(
                new VisionAlign(vision, drivetrain),
                new ShootCommand(shooter, speed),
                new WaitCommand(2.5)),
            new ParallelRaceGroup(
                new ShootCommand(shooter, speed),
                new RunHopperContinuouslyCommand(hopper, shooter, Constants.Robot.MotorSpeeds.HOPPER_LEFT_BELT,
                        Constants.Robot.MotorSpeeds.HOPPER_RIGHT_BELT, Constants.Robot.MotorSpeeds.HOPPER_EXIT_WHEEL,
                        Constants.Robot.MotorSpeeds.HOPPER_BOTTOM_BELT, false),
                new WaitCommand(5))
        );
    }
}
