import greenfoot.*;

public class Projectile extends Actor
{
    Projectile()
    {
        turn(270);
    }
    
    public void act() 
    {
        if (isAtEdge()) {
            getWorld().removeObject(this);
        } else {
            move(5);
        }

    }    
}