package com.team2502.robot2020.command;

import com.team2502.robot2020.subsystem.ShooterSubsystem;

import edu.wpi.first.wpilibj2.command.InstantCommand;

public class ToggleShootCommand extends InstantCommand {
    private final ShooterSubsystem shooter;
    private final double speed;

    public ToggleShootCommand(ShooterSubsystem shooter, double speed) {
        this.shooter = shooter;
        this.speed = speed;
        addRequirements(shooter);
    }

    @Override
    public void initialize(){
        if(shooter.isShooterRunning()) {
            shooter.stopShooter();
        }
        else {
            shooter.setShooterSpeed(speed);
        }
    }

}
