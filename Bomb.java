import greenfoot.*; 


public class Bomb extends Actor
{
    int a =0;
    public void act()
    {
        if(getY()<785){   
        setLocation(getX(), getY() +2);
       }
       a = a +1;
       if(a>=300){
           getWorld().removeObject(this);
        }
       }
       }

