package com.team2502.robot2020.command;

import com.team2502.robot2020.RobotContainer;
import com.team2502.robot2020.subsystem.solenoid.ShiftingSolenoid;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class ShiftDrivetrainCommand extends InstantCommand
{
    ShiftingSolenoid shiftingSolenoid;
    public ShiftDrivetrainCommand(ShiftingSolenoid solenoid)
    {
        shiftingSolenoid = solenoid;
        addRequirements(solenoid);
    }

    @Override
    public void execute()
    {
        shiftingSolenoid.toggle();
    }
}