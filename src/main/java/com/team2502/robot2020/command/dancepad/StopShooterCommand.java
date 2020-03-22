package com.team2502.robot2020.command.dancepad;

import com.team2502.robot2020.subsystem.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class StopShooterCommand extends InstantCommand {
    private final ShooterSubsystem shooter;

    public StopShooterCommand(ShooterSubsystem shooter) {
        this.shooter = shooter;
        addRequirements(shooter);
    }

    @Override
    public void execute(){ shooter.stopShooter(); }

}