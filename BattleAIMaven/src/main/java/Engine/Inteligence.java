package Engine;

/**
 *
 * @author Dragos-Alexandru
 */
public interface Inteligence {

    public void mainMoves();
    
    public void gotHitByBullet();

    public void hitArenaWall();

    public void hitEnemyTank();

    public void detectedEnemyTank(Tank enemy);

}
