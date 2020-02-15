package com.team2502.robot2020.command;

import com.team2502.robot2020.subsystem.ShooterSubsystem;
import com.team2502.robot2020.subsystem.VisionSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;

public class ShootCommand extends CommandBase {
    public VisionSubsystem VISION;
    public ShooterSubsystem SHOOTER;

    //Add .follower failsafe
    public ShootCommand(VisionSubsystem vision, ShooterSubsystem shooter) {
        SHOOTER = shooter;
        VISION = vision;
        addRequirements(shooter);
    }

    @Override
    public void execute(){
        //intake distance from limelight and set speed accordingly
        VISION.getDistance();
        SHOOTER.setShooterSpeed(1.0);
    }

    @Override
    public boolean isFinished(){
        return false;
    }

    @Override
    public void end(boolean interrupted){SHOOTER.stopShooter();}

}
