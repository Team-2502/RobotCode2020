package com.team2502.robot2020.command;

import com.team2502.robot2020.subsystem.IntakeSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class RunIntakeCommand extends CommandBase {
    private final double speedIntake;
    private final double speedSqueeze;
    private final IntakeSubsystem intake;

    public RunIntakeCommand(IntakeSubsystem subsystem, double speedIntake, double speedSqueeze) {
        intake = subsystem;
        this.speedIntake = speedIntake;
        this.speedSqueeze = speedSqueeze;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        intake.runIntake(speedIntake);
        intake.runSqueeze(speedSqueeze);
    }

    @Override
    public void execute() {
        intake.runIntake(speedIntake);
        intake.runSqueeze(speedSqueeze);
    }

    @Override
    public void end(boolean kInterrupted){
        intake.stopIntake();
        intake.stopSqueeze();
    }

    @Override
    public boolean isFinished() {
        return false;

    }

}
