
import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.nxt.*;
import lejos.robotics.objectdetection.*;


public class ObjectDetection 
{
    public static void main(String[] args)
    {
        System.out.println("Program 2");
        Button.waitForAnyPress();
       
        DifferentialPilot hook = new DifferentialPilot(56, 56, 162, Motor.B, Motor.C, false);
        hook.forward();
        Button.waitForAnyPress();
        hook.stop();
        /*
        
        UltrasonicSensor us = new UltrasonicSensor(SensorPort.S3);
        //ObjectDetection listener = new ObjectDetection();
        FeatureDetector fd = new RangeFeatureDetector(us, 80, 500);
        //fd.addListener(listener);
        
        if (fd.scan() != null)
        {
            if (fd.scan().getRangeReading().getRange() < 5)
            {
                hook.stop();
                System.out.println("object detected");
            }
        }  
        
        */
    }
    
}
