package com.team2502.robot2020.command;

import com.team2502.robot2020.subsystem.ShooterSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ShootCommand extends CommandBase {
    private final ShooterSubsystem shooter;
    private final double speed;

    public ShootCommand(ShooterSubsystem shooter, double speed) {
        this.shooter = shooter;
        this.speed = speed;
        addRequirements(shooter);
    }

    @Override
    public void execute(){ shooter.setShooterSpeed(speed); }

    @Override
    public boolean isFinished(){
        return false;
    }

    @Override
    public void end(boolean interrupted){ shooter.stopShooter(); }

}
