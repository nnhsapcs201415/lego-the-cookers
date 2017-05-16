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
        
        while(!touch.isPressed())
        {
            hook.forward();
            //hook.backward();
        }
        hook.stop();
        Button.waitForAnyPress();
        //else
        //{
           // hook.forward();
        //}
    }


}
