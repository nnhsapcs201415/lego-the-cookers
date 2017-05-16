import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.nxt.*;
import lejos.robotics.objectdetection.*;


public class ObjectDetection implements FeatureListener
{
    private DifferentialPilot hook;
    private UltrasonicSensor us;
    private FeatureDetector fd;
    
    public ObjectDetection()
    {
        this.hook = new DifferentialPilot(56, 56, 162, Motor.B, Motor.C, false);
        this.us = new UltrasonicSensor(SensorPort.S3);
        this.fd = new RangeFeatureDetector(us, 80, 100);
        
        System.out.println("Program 2");
        Button.waitForAnyPress();
        this.fd.addListener(this);
        this.hook.forward();
    }
    
    public static void main(String[] args)
    {
        FeatureListener listener = new ObjectDetection();       
        Button.waitForAnyPress();
    }
    
    public void featureDetected(Feature feature, FeatureDetector detector)
    {
        System.out.println("Feature Detected");
        int range = (int)feature.getRangeReading().getRange();
        if (range < 10)
        {
            hook.stop();
        }
        else if (range > 10)
        {
            hook.forward();
        }
    }
}
