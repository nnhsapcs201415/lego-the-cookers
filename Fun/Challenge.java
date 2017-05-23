import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.nxt.*;
import lejos.robotics.objectdetection.*;
import lejos.nxt.SensorPortListener;
import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.util.Delay;

public class Challenge implements FeatureListener, SensorPortListener
{
    private DifferentialPilot hook;
    private UltrasonicSensor us;
    private FeatureDetector fd;
    private LightSensor ls;
    private int count = 0; //0
    private int state = 0; //0
    // 0: In box
    // 1: Searching for cans
    // 2: Can seen
    // 3: Line hit    

    public Challenge()
    {
        this.hook = new DifferentialPilot(56, 56, 162, Motor.B, Motor.C, false);
        this.hook.setTravelSpeed(200);
        this.hook.setRotateSpeed(100);
        this.us = new UltrasonicSensor(SensorPort.S3);
        this.ls = new LightSensor(SensorPort.S1);
        this.ls.setFloodlight(true);
        SensorPort.S1.addSensorPortListener(this);
        this.fd = new RangeFeatureDetector(us, 150, 30);
        this.fd.addListener(this);
    }

    public void scanForNothing()
    {
        while (this.count != 4)
        {
            if (fd.scan() == null)
            {
                this.count = 4;
                this.hook.travel(300);
                this.state = 1;
            }
        }
    }

    public void state()
    {
        while (true)
        {
            if (this.state == 0) // In box
            {
                System.out.println(this.state);
                this.scanForNothing();

            }
            else if (this.state == 1) // Searching for cans
            {
                System.out.println(this.state);
                this.hook.rotateLeft();
                
            }
            else if (this.state == 2) // Can seen
            {
                System.out.println(this.state);
                this.hook.forward();
            }
            else if (this.state == 3) // Line hit
            {
                System.out.println(this.state);
                this.hook.travel(-100);
                this.hook.rotate(180);
                this.state = 1;
            }
            Delay.msDelay(100);
        }

    }

    public static void main(String[] args)
    {
        System.out.println("Challenge");
        Button.waitForAnyPress();
        Challenge listener = new Challenge();
        listener.state();
        //listener.scanForNothing();
        Button.waitForAnyPress();
    }

    public void featureDetected(Feature feature, FeatureDetector detector)
    {
        int range = (int)feature.getRangeReading().getRange();     
        System.out.println("Feature Detected");
        if (this.count < 4)
        {
            if (range < 15)
            {
                this.hook.rotate(90);
                this.count++;
                System.out.println("Wall Detected");
            }
        }
        else if (this.count >= 4)
        {
            System.out.println("FEATURE DETECTED");
            //this.hook.stop();
            this.state = 2;
        }
    }
    
    public void stateChanged(SensorPort aSource, int aOldValue, int aNewValue)
    {
        //System.out.println(aNewValue);
        if (this.count >= 4 && aNewValue > 700)
        {
            this.state = 3;
        }
    }
    
}
