import greenfoot.*;
import java.util.ArrayList;

public class Enemy extends Actor
{
    private int lives = 1;
    private Space space;
    GreenfootSound deadE = new GreenfootSound("sfx3.wav");
    GreenfootSound shootSound = new GreenfootSound("sfx3.wav");
    
    public void act() 
    {
        if (isTouching(PlayerProjectile.class)) {
            lives--;
            getWorld().removeObject(getOneIntersectingObject(PlayerProjectile.class));
        }
        
        if (lives == 0) {
            ((Space)getWorld()).getPlayer().setScore(10);
            deadE.play();
        }
    }
    
    public void shoot()
    {
        try {
            if (getWorld().getObjects(EnemyProjectile.class).size() == 0) {
                getWorld().addObject(new EnemyProjectile(), getX(), getY() + 4);
                shootSound.play();
            } 
        } catch (Exception e) {}
    }
    
    public int getLives()
    {
        return lives;
    }
}