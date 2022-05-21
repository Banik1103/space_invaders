import greenfoot.*;

public class Boss extends Actor
{
    int a = 0;
    int b = Player.diff;
    int livesBoss = 3;
    GreenfootSound dead = new GreenfootSound("sfx22.wav");
    GreenfootSound shootSound = new GreenfootSound("sfx3.wav");
    
    public void act()
    {
        move();
        shoot();
        dying();
    }

    void move()
    {
        a += b;
        setLocation(getX() + b, a * a / 100 + 30);
  
        if (a >= 200) {
            b *= -1;
        }
        
        if (a<=-200) {
            b *= -1;
        } 
    }
        
    void shoot()
    {
        if (getWorld().getObjects(BossProjectile.class).size() == 0) {
            shootSound.play();
            getWorld().addObject(new BossProjectile(), getX(), getY() + 4);
            
            if (Greenfoot.getRandomNumber(5) == 1) {
                getWorld().addObject(new BossProjectile(), getX() - 10, getY() + 4);
                getWorld().addObject(new BossProjectile(), getX() + 10, getY() + 4);
                    
                if (Greenfoot.getRandomNumber(5) == 1) {
                    getWorld().addObject(new BossProjectile(), getX() - 20, getY() + 4);
                    getWorld().addObject(new BossProjectile(), getX() + 20, getY() + 4);
                }
            }
        }
    }
    
    void dying()
    {
        if (isTouching(PlayerProjectile.class)) {
            livesBoss -= 1;
            getWorld();
            getWorld().removeObject(getOneIntersectingObject(PlayerProjectile.class));
        }
        
        if (livesBoss == 0) {
            dead.play();
            getWorld().removeObject(this);
            Player.diff++;
            Greenfoot.delay(150);
            Greenfoot.setWorld(new Space());
        }
    }   
}