import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.nxt.Motor;
import lejos.nxt.Button;


public class TouchTest
{
    public static void main(String[] args)
    {
        DifferentialPilot hook = new DifferentialPilot(56, 56, 162, Motor.B, Motor.C, false);
        TouchSensor touch = new TouchSensor(SensorPort.S1);
        System.out.println("Program 3");
        Button.waitForAnyPress();
        hook.forward();
        if(touch.isPressed())
        {
            hook.stop();
            //hook.backward();
        }
        //else
        //{
           // hook.forward();
        //}
    }


}
