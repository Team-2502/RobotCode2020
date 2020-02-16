package com.team2502.robot2020.command;

import com.team2502.robot2020.subsystem.ShooterSubsystem;
import com.team2502.robot2020.subsystem.VisionSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class ToggleShootCommand extends InstantCommand {
    public VisionSubsystem VISION;
    public ShooterSubsystem SHOOTER;
    public double SPEED;

    public ToggleShootCommand(VisionSubsystem vision, ShooterSubsystem shooter, double speed) {
        SHOOTER = shooter;
        VISION = vision;
        SPEED = speed;
        addRequirements(shooter);
    }

    @Override
    public void initialize(){
        if(SHOOTER.isShooterRunning())
        {
            SHOOTER.stopShooter();
            SHOOTER.shooterRunning = false;
        }

        else
        {
            SHOOTER.setShooterSpeed(SPEED);
            SHOOTER.shooterRunning = true;
        }
    }

}
