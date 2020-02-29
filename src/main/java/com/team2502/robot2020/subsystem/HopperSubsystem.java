package com.team2502.robot2020.subsystem;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.team2502.robot2020.Constants.RobotMap.Motors;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class HopperSubsystem extends SubsystemBase {

    private final CANSparkMax hopperSideBeltsRight;
    private final CANSparkMax hopperSideBeltsLeft;
    //private final CANSparkMax hopperBottomBelt;
    private final CANSparkMax hopperExitWheel;

    public HopperSubsystem() {
        hopperSideBeltsRight = new CANSparkMax(Motors.HOPPER_SIDE_BELTS_RIGHT, MotorType.kBrushless);
        hopperSideBeltsLeft = new CANSparkMax(Motors.HOPPER_SIDE_BELTS_LEFT, MotorType.kBrushless);
        //hopperBottomBelt = new CANSparkMax(Motors.HOPPER_BOTTOM_BELT, MotorType.kBrushless);
        hopperExitWheel = new CANSparkMax(Motors.HOPPER_EXIT_WHEEL, MotorType.kBrushless);

        hopperSideBeltsRight.setInverted(true);
        hopperExitWheel.setInverted(true);
    }

    public void runLeftBelt(double speed) { hopperSideBeltsLeft.set(speed); }

    public void runRightBelt(double speed) { hopperSideBeltsRight.set(speed); }

    public void runExitWheel(double speed){
        hopperExitWheel.set(speed);
    }

    public void runBottomBelt(double speed) {
        //hopperBottomBelt.set(speed);
    }
}

