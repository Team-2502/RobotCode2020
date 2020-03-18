package com.team2502.robot2020.command;

import com.team2502.robot2020.subsystem.ShooterSubsystem;
import com.team2502.robot2020.subsystem.VisionSubsystem;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class ToggleShooterCommand extends InstantCommand {
    private final ShooterSubsystem shooter;
    private final VisionSubsystem vision;
    private final double defaultSpeed;

    public ToggleShooterCommand(ShooterSubsystem shooter, VisionSubsystem vision, double defaultSpeed) {
        this.shooter = shooter;
        this.vision = vision;
        this.defaultSpeed = defaultSpeed;
        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        if(shooter.isShooterRunning()) {
            vision.limeLightOff();
            shooter.setShooterToggleOff();
            shooter.stopShooter();
        }
        else {
            vision.limeLightOn();
            shooter.setShooterToggleOn();
            shooter.setShooterSpeedVisionToggle(defaultSpeed, vision);
        }
    }
}
