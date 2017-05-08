

import lejos.nxt.Button;
import lejos.nxt.Motor;
public class MoveForward
{
    public static void main(String[] args)
    {
        System.out.println("Program 1");
        Button.waitForAnyPress();
        Motor.B.forward();
        Motor.C.forward();
        Button.waitForAnyPress();
        Motor.B.stop();
        Motor.C.stop();
    }
}
