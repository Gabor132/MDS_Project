package Inteligence;

import Engine.Inteligence;
import Engine.Tank; 

public class InteligenceTemplate implements Inteligence{ 

    @Override
    public void mainMoves(){
        
        while(true){
            
        }
    }

    @Override 
    public void gotHitByBullet() {} 

    @Override 
    public void hitArenaWall() {} 

    @Override 
    public void hitEnemyTank() {} 

    @Override 
    public void detectedEnemyTank(Tank enemy) {} 

}