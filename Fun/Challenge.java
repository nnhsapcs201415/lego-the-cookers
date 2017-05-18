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
        this.fd = new RangeFeatureDetector(us, 80, 100);
        this.fd.addListener(this);
        System.out.println("Challenge");
    }
    
    public static void main(String[] args)
    {
        FeatureListener listener = new Challenge();       
        Button.waitForAnyPress();
    }
    
    public void featureDetected(Feature feature, FeatureDetector detector)
    {
        System.out.println("Feature Detected");
        int range = (int)feature.getRangeReading().getRange();
        if (count < 4)
        {
            if (range < 10)
            {
                hook.rotate(90);
                this.count++;
            }
            else if (range > 10)
            {
                hook.forward();
                this.count++;
            }
        }
    }
    
    public void stateChanged(SensorPort aSource, int aOldValue, int aNewValue)
    {
        System.out.println(aNewValue);
        if (aNewValue > 850)
        {
            System.out.println("Stopping");
            hook.stop();
        }
    }
}
