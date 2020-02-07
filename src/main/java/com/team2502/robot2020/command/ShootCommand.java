package com.team2502.robot2020.command;

import com.team2502.robot2020.RobotContainer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;

public class ShootCommand extends CommandBase {
    //Add .follower failsafe
    public ShootCommand() {
        addRequirements(RobotContainer.SHOOTER);
    }

    @Override
    public void execute(){
        RobotContainer.SHOOTER.setShooterSpeed(0.8);
    }

    @Override
    public boolean isFinished(){
        return false;
    }

    @Override
    public void end(boolean interrupted){RobotContainer.SHOOTER.stopShooter();}

}
