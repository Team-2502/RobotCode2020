package com.team2502.robot2020.subsystem.solenoid;

import com.team2502.robot2020.Constants;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShiftingSolenoid extends SubsystemBase
{
    private Solenoid transmission;
    private boolean lowGear;

    public ShiftingSolenoid()
    {
        transmission = new Solenoid(Constants.RobotMap.Solenoid.TRANSMISSION);
        transmission.set(false);
    }

    public void toggle()
    {
        transmission.set(lowGear = !lowGear);
    }

    public boolean isLowGear()
    {
        return lowGear;
    }

    public void setLowGear(boolean lowGear)
    {
        this.lowGear = lowGear;
    }
}
