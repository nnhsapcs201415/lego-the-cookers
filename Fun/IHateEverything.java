import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.nxt.Motor;
import lejos.nxt.Button;
import lejos.nxt.SensorPortListener;

public class IHateEverything implements SensorPortListener
{
    private DifferentialPilot hook;

    public IHateEverything()
    {
        this.hook = new DifferentialPilot(56, 56, 162, Motor.B, Motor.C, false);
        SensorPort.S1.addSensorPortListener(this);
        System.out.println("Program 4");
        Button.waitForAnyPress();
        this.hook.forward();
    }

    public static void main(String[] args)
    {
        SensorPortListener listener = new IHateEverything();
        Button.waitForAnyPress();
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
