
import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.robotics.navigation.DifferentialPilot;

public class Really
{
    public static void main(String[] args)
    {
        System.out.println("Program 2");
        Button.waitForAnyPress();
        
        DifferentialPilot hook = new DifferentialPilot(56, 56, 162, Motor.B, Motor.C, false);
        hook.travelArc(300, 3000);
        hook.rotate(180);
        hook.steer(5, -60);
        hook.travel(100);
        
        
    }
}
