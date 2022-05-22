import greenfoot.*; 

public class BossProjectile extends EnemyProjectile
{

    public void act()
    {
        if(isAtEdge()) {
            getWorld().removeObject(this);
        } else {
            move(10);
        }
    }
}
