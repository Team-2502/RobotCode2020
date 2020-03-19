package com.team2502.robot2020.command.autonomous;

import com.team2502.robot2020.subsystem.*;
import edu.wpi.first.wpilibj2.command.CommandBase;

@FunctionalInterface
public interface CommandFactory
{
    CommandBase getInstance(
            DrivetrainSubsystem drivetrainSubsystem,
            IntakeSubsystem intakeSubsystem,
            HopperSubsystem hopperSubsystem,
            VisionSubsystem visionSubsystem,
            ShooterSubsystem shooterSubsystem
    );
}
