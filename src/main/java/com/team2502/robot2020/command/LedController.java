package com.team2502.robot2020.command;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.util.Color;

public class LedController extends AddressableLED
{
    AddressableLEDBuffer ledBuffer;

    /**
     * Constructs a new driver for a specific port.
     *
     * @param port the output port to use (Must be a PWM header, not on MXP)
     * @param length the amount of LEDs on the strip
     */
    public LedController(int port, int length) {
        super(port);

        ledBuffer = new AddressableLEDBuffer(length);
        setLength(ledBuffer.getLength());
        setData(ledBuffer);
        allRed();
    }

    //Red normally chasing
    //Vision on but no target Orange
    //Vision when on target Green
    public void allRed()
    {
        stop();
        for (int i = 0; i < ledBuffer.getLength(); i++) {
            ledBuffer.setLED(i, Color.kRed);
        }
        start();
    }

    public void allGreen()
    {
        stop();
        for (int i = 0; i < ledBuffer.getLength(); i++) {
            ledBuffer.setLED(i, Color.kGreen);
        }
        start();
    }

    public void allOrange()
    {
        stop();
        for (int i = 0; i < ledBuffer.getLength(); i++) {
            ledBuffer.setLED(i, Color.kOrange);
        }
        start();
    }

    int i = 0;
    public void chaseRed()
    {
        if(i == 0)
        {
            ledBuffer.setRGB(ledBuffer.getLength()-1, 0,0, 0);
            ledBuffer.setLED(i, Color.kRed);
        }
    }
}
