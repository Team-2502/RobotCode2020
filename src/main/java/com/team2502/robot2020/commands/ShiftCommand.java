package com.team2502.robot2020.commands;

import com.team2502.robot2020.RobotContainer;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class ShiftCommand extends InstantCommand
{
    public ShiftCommand()
    {
        addRequirements(RobotContainer.SHIFTING_SOLENOID);
    }

    @Override
    public void execute()
    {
        RobotContainer.SHIFTING_SOLENOID.toggle();
    }
}
