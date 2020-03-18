package com.team2502.robot2020.command;

import com.team2502.robot2020.subsystem.ShooterSubsystem;
import com.team2502.robot2020.subsystem.VisionSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class RunShooterCommand extends CommandBase {
    private final ShooterSubsystem shooter;
    private final VisionSubsystem vision;
    private final double defaultSpeed;

    public RunShooterCommand(ShooterSubsystem shooter, VisionSubsystem vision, double defaultSpeed) {
        this.shooter = shooter;
        this.vision = vision;
        this.defaultSpeed = defaultSpeed;
        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        if(shooter.isShooterRunning()) {
            vision.limeLightOff();
            shooter.stopShooter();
            end(false);
        }
        else {
            vision.limeLightOn();
            shooter.setShooterSpeedRPM(defaultSpeed);
        }
    }

    @Override
    public void execute() { shooter.setShooterSpeedVision(defaultSpeed, vision); }

    @Override
    public void end(boolean interrupted) { }

    @Override
    public boolean isFinished() {
        return false;
    }
}
