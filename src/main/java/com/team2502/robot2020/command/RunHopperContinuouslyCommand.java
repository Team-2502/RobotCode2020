package com.team2502.robot2020.command;

import com.team2502.robot2020.subsystem.HopperSubsystem;
import com.team2502.robot2020.subsystem.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class RunHopperContinuouslyCommand extends CommandBase {

    private final HopperSubsystem hopper;
    private final ShooterSubsystem shooter;
    private final double speedLeft;
    private final double speedRight;
    private final double speedWheel;
    private final boolean waitForFlywheel;

    public RunHopperContinuouslyCommand(HopperSubsystem hopper, ShooterSubsystem shooter, double speedLeft, double speedRight, double speedWheel, boolean waitForFlywheel){
        this.speedLeft = speedLeft;
        this.speedRight = speedRight;
        this.speedWheel = speedWheel;
        this.hopper = hopper;
        this.shooter = shooter;
        this.waitForFlywheel = waitForFlywheel;
        addRequirements(hopper);
    }

    @Override
    public void initialize() {
        if(waitForFlywheel && !shooter.isShooterRunning()){
            end(false);
        }
    }

    @Override
    public void execute() {
        hopper.runLeftBelt(speedLeft);
        hopper.runRightBelt(speedRight);
        hopper.runExitWheel(speedWheel);

    }

    @Override
    public void end(boolean interrupted) {
        hopper.runLeftBelt(0);
        hopper.runRightBelt(0);
        hopper.runExitWheel(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
