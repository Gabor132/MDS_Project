package Visual;

import Console.ConsoleFrame;
import Engine.Bullet;
import Engine.Tank;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JPanel;

/**
 * The separate thread which is responsible
 * with updating game entitites. It calls the 
 * repaint() function from JPanel.
 * 
 * @author Liviu
 * 
 */
public class Animator extends Thread{
    private final JPanel panel;
    private boolean running;
    private final int framerate = Constants.VisualConstants.FRAME_RATE;
    private final List<Tank> tanks;
    private final List<Bullet> bullets;
    private final List<Thread> tankThreads;
    private final Semafoare semafoare;
    
    public Animator(JPanel panel, List<Tank> tanks, List<Bullet> bullets, List<Thread> tankThreads, Semafoare semafoare){
        this.panel = panel;
        this.tanks = tanks;
        this.bullets = bullets;
        this.tankThreads = tankThreads;
        running = true;
        this.semafoare = semafoare;
    }
    
    @Override
    public void run(){
        for(Thread thread:tankThreads){
            thread.start();
        }
        
        Bullet auxBullet = null;
        
        while(running){
            
            //DO ENTITY UPDATES
//            for (Tank tankAux : tanks) {
//                tankAux.rotateCannon(-0.1);
//                tankAux.rotate(0.1);
//            }
            //Entities ar updated automatically
            synchronized(bullets){
                for (Tank tankAux : tanks) {
                    if(tankAux.hasFired()){
                        auxBullet = tankAux.getBullet();
                        bullets.add(auxBullet);
                    }
                }
                List<Bullet> bulletsToBeRemoved = Collections.synchronizedList(new LinkedList<Bullet>());
                for(Bullet bulletAux: bullets){
                    if(bulletAux.getX() > Constants.VisualConstants.ENGINE_WIDTH || bulletAux.getX() < 0 ||
                            bulletAux.getY() > Constants.VisualConstants.ENGINE_HEIGHT || bulletAux.getY() < 0){
                        //if the bullet is no longer on the screen, it's removed
                        bulletsToBeRemoved.add(bulletAux);
                    }
                    else{
                        bulletAux.moveFront();
                    }
                }
                for(Bullet bulletAux:bulletsToBeRemoved){
                    bullets.remove(bulletAux);
                }
            }
             
            panel.repaint();    //done with updates, start painting
            
            try{
                Thread.sleep(1000/framerate);
                synchronized(semafoare){
                    for(int i = 0;i<semafoare.getSize();i++){
                        semafoare.setReady(i, true);
                    }
                    semafoare.notifyAll();
                }
            }
            catch(InterruptedException iex){
                ConsoleFrame.sendMessage("Animator", "Thread interrupted");
            }
        }
    }
    /**
     * Stops the animation in the game engine.
     * If you want to start the animation again, you'll 
     * have to create a new Animator object.
     */
    
    public void stopAnimation(){
        running = false;
        tanks.clear();
        tankThreads.clear();
        semafoare.reset();
        bullets.clear();
    }
}
