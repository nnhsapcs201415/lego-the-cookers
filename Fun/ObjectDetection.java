
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
        UltrasonicSensor us = new UltrasonicSensor(SensorPort.S1);
        ObjectDetect listener = new ObjectDetect();
        FeatureDetector fd = new RangeFeatureDetector(us, 50, 500);
        fd.addListener(listener);
        hook.forward();
        if (fd.scan() != null)
        {
            hook.stop();
        }
        
        
        
        
    }
}
