import greenfoot.*;
import java.util.*;

public class Space extends World
{
    private Player player = new Player();
    private int count; 
    private int direction = 1;
    private int highestY;
    public ArrayList<LinkedList> enemies = new ArrayList<LinkedList>();
    private int columns = 10;
    GreenfootSound deadP = new GreenfootSound("sfx20.wav");
    
    public Space()
    {
        super(600, 800, 1);
        setBackground("background.png");
        addObject(player, 300, 770);
        //createEnemies();
        createBoxes();
    }
    
    public void act()
    {
        if (count == 0) {
            showText("Level: " + player.getDiff(), 300, 400);
            Greenfoot.delay(150);
            showText(null, 300, 400);
        }   
        showText("Score: " + player.getScore(), 50, 10);
        showText("Lives: " + player.getLifes(), 550, 10);
        count++;
        if (count % 3 == 0) {
            moveEnemy();
        }
        checkLists();
        shootEnemy();
        checkEnemies();
        createBoss();
        createItem();
    }
    
    private void createEnemies()
    {
        for (int i = 0; i < 11; i++) {
            LinkedList<Enemy> ll = new LinkedList<Enemy>();
            for(int j = 0; j < 5; j++) {
                Enemy enemy = new Enemy();
                addObject(enemy, 550 - i * 50, 290 - j * 60);
                ll.add(enemy);
            }
            enemies.add(ll);
        }
    }
   
    
    private void createBoss()
    {
        if (getObjects(Enemy.class).size() == 0 && getObjects(Boss.class).size() == 0) {
            Boss boss = new Boss();
            addObject(boss, 300, 30);
       }
    }
    
    private void createBoxes()
    {
        Box box = new Box();
        addObject(box,99,680);
        Box box2 = new Box();
        addObject(box2,231,680);
        Box box3 = new Box();
        addObject(box3,363,680);
        Box box4 = new Box();
        addObject(box4,495,680);
    }

    private void createItem()
    {
      if (Greenfoot.getRandomNumber(700) == 1 && getObjects(Powerup.class).size() == 0) {
            Powerup powerup = new Powerup();
            addObject(powerup,Greenfoot.getRandomNumber(540) + 30,30);
      }
        
      if (getObjects(Enemy.class).size() == 0 && getObjects(Moon.class).size() == 0) {
          Moon moon1 = new Moon();
          addObject(moon1,300,500);
      }
    }
    
    private void moveEnemy()
    {
        for (Enemy e : getObjects(Enemy.class)) {
            if (e.isAtEdge()) {
                direction *= -1;
                for (Enemy f : getObjects(Enemy.class)) {
                    f.move(Player.diff* direction);
                    f.setLocation(f.getX(), f.getY() + 20);
                }
            }
            e.move(Player.diff* direction);
        }
    }
    
    private void shootEnemy()
    {
        try{
           LinkedList<Enemy> currentColumn = enemies.get(Greenfoot.getRandomNumber(columns));
           currentColumn.getFirst().shoot();
        } catch(Exception e) {}
    }
    
    private void checkLists()
    {
        for (Iterator<LinkedList> iterator = enemies.iterator(); iterator.hasNext();) {
            LinkedList ll = iterator.next();
            if (ll.size() <= 0) {
                iterator.remove();
                columns--;
            }
        }
    }
    
    private void checkEnemies()
    {
        for (LinkedList ll: enemies) {
            try {
                for (Object e: ll) {
                    if (((Enemy)e).getY() >= 620) {
                        deadP.play();
                        Greenfoot.setWorld( new Dead());
                        Greenfoot.delay(500);
                        Greenfoot.setWorld(new Screen());
                    }
                    
                    if (((Enemy)e).getLives() <= 0) {
                        removeObject((Actor)e); 
                        try {
                            ll.remove(ll.indexOf(e));
                        } catch(Exception ex) {}
                    }
                }
            } catch(Exception e) {}
        }
    }
    
    public Player getPlayer()
    {
        return player;
    }
}