package com.team2502.robot2020.command.autonomous.groups;

import com.team2502.robot2020.Constants;
import com.team2502.robot2020.command.RunHopperContinuouslyCommand;
import com.team2502.robot2020.command.ShootCommand;
import com.team2502.robot2020.command.autonomous.ingredients.VoltageDriveCommand;
import com.team2502.robot2020.subsystem.DrivetrainSubsystem;
import com.team2502.robot2020.subsystem.HopperSubsystem;
import com.team2502.robot2020.subsystem.ShooterSubsystem;
import com.team2502.robot2020.subsystem.VisionSubsystem;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class AutonCommandGroupFactory {
    public static SequentialCommandGroup SimpleShoot3Balls(ShooterSubsystem SHOOTER,
                                                           VisionSubsystem VISION,
                                                           HopperSubsystem HOPPER,
                                                           DrivetrainSubsystem DT) {
        ParallelRaceGroup spoolUpShooter = new ParallelRaceGroup(
                new ShootCommand(VISION, SHOOTER, Constants.OI.BUTTON_RUN_SHOOTER_INIT_LINE),
                new WaitCommand(3)
        );

        ParallelRaceGroup runHopperAndShootBalls = new ParallelRaceGroup(
                new ShootCommand(VISION, SHOOTER, Constants.OI.BUTTON_RUN_SHOOTER_INIT_LINE),
                new RunHopperContinuouslyCommand(HOPPER,SHOOTER, false),
                new WaitCommand(5)
        );

        VoltageDriveCommand driveBackFromInitLine = new VoltageDriveCommand(DT, -1, -1, 2);

        return new SequentialCommandGroup(
                spoolUpShooter,
                runHopperAndShootBalls,
                driveBackFromInitLine
        );
    }
}