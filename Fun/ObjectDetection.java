
import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.nxt.*;
import lejos.robotics.objectdetection.*;


public class ObjectDetection implements FeatureListener
{
    public static void main(String[] args)
    {
        UltrasonicSensor us = new UltrasonicSensor(SensorPort.S3);
        ObjectDetection listener = new ObjectDetection();
        FeatureDetector fd = new RangeFeatureDetector(us, 80, 500);
        fd.addListener(listener);
        DifferentialPilot hook = new DifferentialPilot(56, 56, 162, Motor.B, Motor.C, false);
        
        
        System.out.println("Program 2");
        Button.waitForAnyPress();
        hook.forward();
        listener.featureDetected(fd.scan(), fd);
        
        
        /*
        Feature scan = fd.scan();
        if (scan != null)
        {
            if (scan.getRangeReading().getRange() < 5)
            {
                System.out.println("object detected");
                Button.waitForAnyPress();
                hook.stop();
            }
        }  
        */
        
    }
    
    public void featureDetected(Feature feature, FeatureDetector detector)
    {
        int range = (int)feature.getRangeReading().getRange();
        DifferentialPilot hook = new DifferentialPilot(56, 56, 162, Motor.B, Motor.C, false);
        if (range < 10)
        {
            hook.stop();
        }
    }
}
