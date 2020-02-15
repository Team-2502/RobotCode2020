package com.team2502.robot2020.command;

import com.team2502.robot2020.Constants;
import com.team2502.robot2020.subsystem.ShooterSubsystem;
import com.team2502.robot2020.subsystem.VisionSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;

public class ShootCommand extends CommandBase {
    public VisionSubsystem VISION;
    public ShooterSubsystem SHOOTER;
    public double SPEED;

    public ShootCommand(VisionSubsystem vision, ShooterSubsystem shooter, double speed) {
        SHOOTER = shooter;
        VISION = vision;
        SPEED = speed;
        addRequirements(shooter);
    }

    @Override
    public void execute(){
        //intake distance from limelight and set speed accordingly
        VISION.getDistance();
        SHOOTER.setShooterSpeed(SPEED);
    }

    @Override
    public boolean isFinished(){
        return false;
    }

    @Override
    public void end(boolean interrupted){SHOOTER.stopShooter();}

}
