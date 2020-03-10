package com.team2502.robot2020.command;

import com.team2502.robot2020.subsystem.ShooterSubsystem;
import com.team2502.robot2020.subsystem.VisionSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ToggleShootCalculateRPMCommand extends CommandBase {
    private final ShooterSubsystem shooter;
    private final VisionSubsystem vision;

    public ToggleShootCalculateRPMCommand(ShooterSubsystem shooter, VisionSubsystem vision) {
        this.shooter = shooter;
        this.vision = vision;
        addRequirements(shooter);
    }

    @Override
    public void initialize(){
        if(shooter.isShooterRunning()) {
            shooter.stopShooter();
            vision.limeLightOff();
        }
        else {
            vision.limeLightOn();
            double distanceFeet = vision.getDistance() / 12f;
            double speed = shooter.findRPMFromDistance(distanceFeet);
            shooter.setShooterSpeed(speed);
        }
    }
}
