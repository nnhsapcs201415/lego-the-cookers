import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.nxt.*;
import lejos.robotics.objectdetection.*;
import lejos.nxt.SensorPortListener;
import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;

public class Challenge implements FeatureListener, SensorPortListener
{
    private DifferentialPilot hook;
    private UltrasonicSensor us;
    private FeatureDetector fd;
    private int count = 0;
    
    public Challenge()
    {
        this.hook = new DifferentialPilot(56, 56, 162, Motor.B, Motor.C, false);
        this.us = new UltrasonicSensor(SensorPort.S3);
        SensorPort.S1.addSensorPortListener(this);
        this.fd = new RangeFeatureDetector(us, 400, 300);
        this.fd.addListener(this);
    }
    
    public void scanForNothing()
    {
        while (this.count != 4)
        {
            if (fd.scan() == null)
            {
                this.count = 4;
                hook.forward();
            }
        }
    }
    
    public static void main(String[] args)
    {
        System.out.println("Challenge");
        Button.waitForAnyPress();
        Challenge listener = new Challenge();
        listener.scanForNothing();
        Button.waitForAnyPress();
    }
    
    
    
    public void featureDetected(Feature feature, FeatureDetector detector)
    {
        int range = (int)feature.getRangeReading().getRange();
        if (count < 4)
        {
            if (range < 15)
            {
                hook.rotate(90);
                this.count++;
                System.out.println("Wall Detected");
            }
            /*
            else if (range > 15)
            {
                hook.forward();
                this.count = 4;
            }
            */
        }
    }
    
    public void stateChanged(SensorPort aSource, int aOldValue, int aNewValue)
    {
        System.out.println(aNewValue);
        if (aNewValue > 850)
        {
            System.out.println("Seeing dark");
            //hook.stop();
        }
    }
}
