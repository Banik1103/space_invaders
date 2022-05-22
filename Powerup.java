import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

public class Powerup extends Actor
{
    private int lifes = 1;
    private int type = 0;
    private int direction = 3;
    private int timer = 0;
    GreenfootSound item = new GreenfootSound("sfx17.wav");
    
    Powerup(){
        type = Greenfoot.getRandomNumber(3) + 1;
        
        if(type == 1){
            setImage("rapid.png"); 
        }else if(type == 2){
            setImage("heart.png"); 
        }else if(type == 3){
            setImage("speed.png"); 
        }
    }
    
    public void act() 
    {
        if(lifes > 0){
            move();
        }
        dying();
    }    
    
    private void move(){
        setLocation(getX() + direction, getY() + 1);
        if(isAtEdge()){
            direction = direction * -1;
            setLocation(getX() - direction * 5, getY());
        }
    }
    
    private void dying(){
        if(getY() > 650){
            getWorld().removeObject(this);
        }
        if(lifes > 0){
            try{        
                    if(isTouching(PlayerProjectile.class)){
                        ((Space)getWorld()).getPlayer().setPower(type);
                        lifes--;
                        item.play();
                    }
                }catch(Exception e){}
        } else {
            getImage().setTransparency(0);
            timer ++;
            if(timer == 500){
                try{
                    ((Space)getWorld()).getPlayer().setPower(0);
                    getWorld().removeObject(this);
                    getWorld().removeObject(getOneIntersectingObject(PlayerProjectile.class));
                }catch(Exception e){}
            }
        }
    }
}