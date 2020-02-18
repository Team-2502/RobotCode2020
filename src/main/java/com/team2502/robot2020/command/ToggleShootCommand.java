package com.team2502.robot2020.command;

import com.team2502.robot2020.subsystem.ShooterSubsystem;
import com.team2502.robot2020.subsystem.VisionSubsystem;

import edu.wpi.first.wpilibj2.command.InstantCommand;

public class ToggleShootCommand extends InstantCommand {
    public VisionSubsystem vision;
    public ShooterSubsystem shooter;
    public double speed;

    public ToggleShootCommand(VisionSubsystem vision, ShooterSubsystem shooter, double speed) {
        this.shooter = shooter;
        this.vision = vision;
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
