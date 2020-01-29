package com.team2502.robot2020.subsystem;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {

    private final CANSparkMax shootRight;
    private final CANSparkMax shootLeft;

    public ShooterSubsystem() {
        //Fix int for Motor ID
        shootLeft = new CANSparkMax(0, CANSparkMaxLowLevel.MotorType.kBrushless);
        shootRight = new CANSparkMax(1, CANSparkMaxLowLevel.MotorType.kBrushless);

        // Add copy motor, motor follows other.


    }

    public void setShooterPower(double power){

    }

    public void setShooterRPM(){

    }

    public void stopShooter(){
        
    }


}
