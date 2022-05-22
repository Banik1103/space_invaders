import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)
public class Box extends Actor
{
 int leben = 5;
     
    public void act(){
    
        if(isTouching(Projectile.class)){
            getWorld().removeObject(getOneIntersectingObject(Projectile.class)); 
            leben = leben -1;
            setImage("box"+leben+".png");
        }
        
        if(leben==0){
            getWorld().removeObject(this);
        }
    }
    
}