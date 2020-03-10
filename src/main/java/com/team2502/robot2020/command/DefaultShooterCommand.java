package com.team2502.robot2020.command;

import com.team2502.robot2020.subsystem.ShooterSubsystem;
import com.team2502.robot2020.subsystem.VisionSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DefaultShooterCommand extends CommandBase {

    private final ShooterSubsystem shooter;
    private final VisionSubsystem vision;
    private final Joystick operatorJoy;

    public DefaultShooterCommand(ShooterSubsystem shooter, VisionSubsystem vision, Joystick operatorJoy) {

        this.shooter = shooter;
        this.vision = vision;
        this.operatorJoy = operatorJoy;
        addRequirements(shooter);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        ShooterSubsystem.ShooterStateBean shooterState = shooter.getShooterStateBean();

        if(shooterState.isOn()) {
            double desiredRPM = shooterState.getDesiredRPM();
            shooter.setShooterSpeed(desiredRPM + ((operatorJoy.getThrottle()-1) * -200));
            vision.limeLightOn();
        } else {
            shooter.stopShooter();
            vision.limeLightOff();
        }

    }

    @Override
    public void end(boolean interrupted) {
        shooter.setShooterSpeed(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
