package com.team2502.robot2020.subsystem;

import com.team2502.robot2020.Constants.Field;
import com.team2502.robot2020.Constants.Robot.Vision;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.lang.Math;

import static com.team2502.robot2020.Constants.Robot.Vision.LIMELIGHT_NETWORK_TABLE;

public class VisionSubsystem extends SubsystemBase {
    private final NetworkTable limelight;

    private double tX;
    private double tY;
    private double area;

    public VisionSubsystem() {
        limelight = NetworkTableInstance.getDefault().getTable(LIMELIGHT_NETWORK_TABLE);
    }

    @Override
    public void periodic() {
        NetworkTableEntry TX_ENTRY = limelight.getEntry("tx");
        NetworkTableEntry TY_ENTRY = limelight.getEntry("ty");
        NetworkTableEntry AREA_ENTRY = limelight.getEntry("ta");

        tX = TX_ENTRY.getDouble(0.0);
        tY = TY_ENTRY.getDouble(0.0);
        area = AREA_ENTRY.getDouble(0.0);

        SmartDashboard.putNumber("Limelight X", tX);
        SmartDashboard.putNumber("Limelight Y", tY);
        SmartDashboard.putNumber("Limelight Area", area);
    }

    public double getTx(){
        return tX;
    }

    public double getTy(){
        return tY;
    }

    public double getArea(){
        return area;
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
     * @return double
     */
    public double getDistance(){
        return (Field.TARGET_HEIGHT - Vision.LIMELIGHT_HEIGHT)/(Math.tan(Vision.LIMELIGHT_MOUNTING_ANGLE + getTy()));

    }

}
