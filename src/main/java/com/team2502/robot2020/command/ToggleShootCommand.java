package com.team2502.robot2020.command;

import com.team2502.robot2020.subsystem.ShooterSubsystem;

import com.team2502.robot2020.subsystem.VisionSubsystem;
import edu.wpi.first.wpilibj2.command.InstantCommand;

import static com.team2502.robot2020.RobotContainer.JOYSTICK_OPERATOR;

public class ToggleShootCommand extends InstantCommand {
    private final ShooterSubsystem shooter;
    private final double speed;
    private final VisionSubsystem vision;

    public ToggleShootCommand(ShooterSubsystem shooter, VisionSubsystem vision, double speed) {
        this.shooter = shooter;
        this.speed = speed;
        this.vision = vision;
        addRequirements(shooter);
    }

    @Override
    public void initialize(){

        if(shooter.isShooterRunning()) {
            shooter.stopShooter();
//            vision.limeLightOff();
        }
        else
        {
            shooter.getShooterStateBean().setOn(true);
            shooter.getShooterStateBean().setDesiredRPM(speed);
        }
    }
}
