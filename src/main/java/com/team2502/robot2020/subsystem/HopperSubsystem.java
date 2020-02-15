package com.team2502.robot2020.subsystem;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.team2502.robot2020.Constants.RobotMap.Motors;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class HopperSubsystem extends SubsystemBase {

    public final CANSparkMax HopperSideBelts;
    public final CANSparkMax HopperBottomBelt;
    public final CANSparkMax HopperExitWheel;

    public HopperSubsystem(){
        HopperSideBelts = new CANSparkMax(Motors.HOPPER_SIDE_BELTS, MotorType.kBrushless);
        HopperBottomBelt = new CANSparkMax(Motors.HOPPER_BOTTOM_BELTS, MotorType.kBrushless);
        HopperExitWheel = new CANSparkMax(Motors.HOPPER_EXIT_WHEEL, MotorType.kBrushless);
    }

    @Override
    public void periodic(){

    }
    public void RunSideBelts(double speed){
        HopperSideBelts.set(speed);
    }

    public void RunBottomBelts(double speed){
        HopperBottomBelt.set(speed);
    }

    public void RunExitWheel(double speed){
        HopperExitWheel.set(speed);
    }
}

