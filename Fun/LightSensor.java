import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.nxt.Motor;
import lejos.nxt.Button;
import lejos.nxt.SensorPortListener;

public class LightSensor implements SensorPortListener
{
    private DifferentialPilot hook;

    public LightSensor()
    {
        this.hook = new DifferentialPilot(56, 56, 162, Motor.B, Motor.C, false);
        SensorPort.S1.addSensorPortListener(this);
        System.out.println("Program 4");
        Button.waitForAnyPress();
        this.hook.forward();
    }

    public static void main(String[] args)
    {
        SensorPortListener listener = new LightSensor();
        Button.waitForAnyPress();
    }

    public void stateChanged(SensorPort aSource, int aOldValue, int aNewValue)
    {
        System.out.println("Color Change");
        if (Math.abs(aOldValue - aNewValue) > 100)
        {
            hook.stop();
        }
    }
}
