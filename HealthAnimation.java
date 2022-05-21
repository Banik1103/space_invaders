import greenfoot.*;

public class HealthAnimation extends Actor
{
    public boolean state;
    private int count = 8;
    
    public HealthAnimation(boolean s)
    {
       state = s;
       
       if (state) {
           setImage("+1.png");
       } else {
           setImage("-1.png");
       }
    }
    
    public void act() 
    {
       if (count > 0) {
            setLocation(getX(), getY() - count);
            count--;
       } else {
           getWorld().removeObject(this);
       }
    }    
}