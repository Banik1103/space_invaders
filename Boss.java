import greenfoot.*;

public class Boss extends Actor
{
    Space s = (Space)getWorld();
    int a = 0;
    int b = Player.diff;
    int lifesBoss = 3;
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
        a= a + b;
        setLocation(getX() +b, a*a/100 + 30);
  
        if(a>=200){
            b = b * -1;
        }
        
        if(a<=-200){
            b = b * -1;
        } 
    }
        
    void shoot()
    {
            if (getWorld().getObjects(BossProjectile.class).size() == 0) {
                shootSound.play();
                getWorld().addObject(new BossProjectile(), getX(), getY() + 4);
                if (Greenfoot.getRandomNumber(4)== 1){
                    getWorld().addObject(new BossProjectile(), getX() -10, getY() + 4);
                    getWorld().addObject(new BossProjectile(), getX() +10, getY() + 4);
                    
                    if (Greenfoot.getRandomNumber(4)== 1){
                    getWorld().addObject(new BossProjectile(), getX() -20, getY() + 4);
                    getWorld().addObject(new BossProjectile(), getX() +20, getY() + 4);
                   }
                }
        }
    }
    
    void dying()
    {
        if(isTouching(PlayerProjectile.class)){
            lifesBoss -= 1;
            getWorld();
            getWorld().removeObject(getOneIntersectingObject(PlayerProjectile.class));
        }
        
        if(lifesBoss == 0){
            dead.play();
            getWorld().removeObject(this);
            ((Space)getWorld()).getPlayer().setDiff();
            Greenfoot.delay(150);
            Greenfoot.setWorld(new Space());
            }
        }   
    }