import greenfoot.*;
public class Box extends Actor
{
    int leben = 5;
     
    public void act()
    {
        if (isTouching(Projectile.class)) {
            getWorld().removeObject(getOneIntersectingObject(Projectile.class)); 
            leben--;
            setImage("box"+leben+".png");
        }
        
        if (leben==0) {
            getWorld().removeObject(this);
        }
    }
}