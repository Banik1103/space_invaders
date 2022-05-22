import greenfoot.*; 


public class Moon extends Actor
{
    int a = 3;
    public void act()
    {
        setLocation(getX() + a, getY());
        
        if(isAtEdge()){
            a = a * -1;
            setLocation(getX() - a * 5, getY());
        }
        
        if(isTouching(PlayerProjectile.class)){
            getWorld().removeObject(getOneIntersectingObject(PlayerProjectile.class));
            getWorld().addObject(new Bomb(), getX(), getY() + 4);
        }
        
        if(Greenfoot.getRandomNumber(500)==1){
            getWorld().addObject(new Bomb(), getX(), getY() + 4);
        
        }
    }
}
