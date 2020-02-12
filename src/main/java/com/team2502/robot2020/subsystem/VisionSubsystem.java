package com.team2502.robot2020.subsystem;


import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.lang.Math;

import com.team2502.robot2020.Constants.Field;
import com.team2502.robot2020.Constants.Robot.Vision;

public class VisionSubsystem extends SubsystemBase {
    private NetworkTable LIMELIGHT;

    private double TX;
    private double TY;
    private double AREA;

    public VisionSubsystem() {
        LIMELIGHT = NetworkTableInstance.getDefault().getTable("limelight-sammy");
    }

    @Override
    public void periodic() {
        NetworkTableEntry TX_ENTRY = LIMELIGHT.getEntry("tx");
        NetworkTableEntry TY_ENTRY = LIMELIGHT.getEntry("ty");
        NetworkTableEntry AREA_ENTRY = LIMELIGHT.getEntry("ta");

        TX = TX_ENTRY.getDouble(0.0);
        TY = TY_ENTRY.getDouble(0.0);
        AREA = AREA_ENTRY.getDouble(0.0);

        SmartDashboard.putNumber("Limelight X", TX);
        SmartDashboard.putNumber("Limelight Y", TY);
        SmartDashboard.putNumber("Limelight Area", AREA);
    }

    public double getTX(){
        return TX;
    }

    public double getTY(){
        return TY;
    }

    public double getAREA(){
        return AREA;
    }

    /**
     * https://docs.limelightvision.io/en/latest/cs_estimating_distance.html#using-area-to-estimate-distance
     * h2 = height of target from ground(98.25
     * h1 = height of camera from ground=18.34in
     * a1 = camera mounting angle=13.56
     * a2 = y angle to top of target
     * formula
     * d = (h2-h1) / tan(a1+a2)
     *
     * @return
     */

    public double getDistance(){
        return (98.25-18.34)/(Math.tan(13.56+getTY()));

    }

}
