import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)


public class Screen extends World
{
    int i;
    GreenfootSound screenSound = new GreenfootSound("screen.mp3");
    public Screen()
    {    
        super(600, 800, 1);
        setBackground("logo.png");
        //screenSound.play();
        
    }
    
    public void act(){
        i++;
        Greenfoot.delay(12);
        
        if(Greenfoot.isKeyDown("space")){
            Greenfoot.setWorld(new Space());
        }
        if(i%2==1){
            setBackground("logo2.png");
        }
        if(i%2==0){
            setBackground("logo.png");
        }
    }
}