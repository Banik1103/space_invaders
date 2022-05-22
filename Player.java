import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

public class Player extends Actor
{
    private int lifes = 3;
    private int speed = 1;
    private int power = 0;
    private int count;
    
    private int score;
    public static int diff = 1;
    
    GreenfootSound shootSound = new GreenfootSound("sfx3.wav");
    GreenfootSound deadP = new GreenfootSound("sfx20.wav");
    
     void Player()
    {
        setImage("plyer_01.png");
    }
    
    public void act() 
    {
        move();
        count++;
        
        if (count % 10 == 0) {
            shoot();
            count = 0;
        }
        
        dying();
        bePower();
    }
    
    private void move()
    {
        if(getX() > 50 && Greenfoot.isKeyDown("left")) {
            setLocation​(getX() - (5 * speed), getY());
        } else if(getX() < 550 && Greenfoot.isKeyDown("right")) {
            setLocation​(getX() + (5 * speed), getY());
        }
    }
    
    private void shoot()
    {
        if(getWorld().getObjects(PlayerProjectile.class).size() == 0 || power == 1) {
            try {
                if(Greenfoot.isKeyDown("space")) {
                shootSound.play();
                getWorld().addObject(new PlayerProjectile(), getX(), getY() - 45);
            }
            } catch (Exception e) {}
        }
    }
    
    private void dying(){
        if(isTouching(EnemyProjectile.class)){
            getWorld().addObject(new HealthAnimation(false), ((Space)getWorld()).getPlayer().getX() + 20, ((Space)getWorld()).getPlayer().getY() - 25);
            lifes -= 1;
            getWorld().removeObject(getOneIntersectingObject(EnemyProjectile.class));
        }
        
        if(isTouching(Bomb.class)){
            getWorld().addObject(new HealthAnimation(false), ((Space)getWorld()).getPlayer().getX() + 20, ((Space)getWorld()).getPlayer().getY() - 25);
            lifes -= 1;
            getWorld().removeObject(getOneIntersectingObject(Bomb.class));
        }
        
        if(lifes <= 0){
            deadP.play();
            Greenfoot.setWorld( new Dead());
            Greenfoot.delay(500);
            Greenfoot.setWorld(new Screen());
        }
    }
    
    private void bePower(){
        if(power == 0){
            speed = 1;
        }else if(power == 2){
            getWorld().addObject(new HealthAnimation(true), ((Space)getWorld()).getPlayer().getX() + 20, ((Space)getWorld()).getPlayer().getY() - 25);
            lifes++;
            setPower(0);
        } else if (power == 3){
            speed = 2;
        }
    }
    
    public int getScore(){
        return score;
    }
    
    public void setScore(int s){
        score += s;
    }
    
    public int getLifes() {
        return lifes;
    }
    
    public void setPower(int i){
        if(power >= 0 && power < 4){
            power = i;
        }
    }
    
    public int getPower(){
        return power;
    }
    
    public int getDiff()
    {
        return diff; 
    }
    
    public void setDiff()
    {
        diff = diff + 1; 
    }
}