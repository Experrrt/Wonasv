

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
    Player player = new Player();
    private boolean[]keyDown = new boolean[4];
    public KeyInput(){
        for (int i=0;i<4;i++){keyDown[i]=false;}
    }
    public void keyTyped(KeyEvent e){
        int key = e.getKeyCode();
    }
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_LEFT){player.velX(-6);keyDown[0]=true;}
        if(key == KeyEvent.VK_RIGHT){player.velX(6);keyDown[1]=true;}
        if(key == KeyEvent.VK_UP){player.jump();keyDown[2]=true;}

    }
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_LEFT){keyDown[0]=false;}
        if(key == KeyEvent.VK_RIGHT){keyDown[1]=false;}
        if(key == KeyEvent.VK_UP){keyDown[2]=false;}
        if(!keyDown[0]&&!keyDown[1])player.velX(0);
    }
}
