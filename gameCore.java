
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class gameCore extends Canvas implements Runnable {
///////////////////////////////////////////////////////////////
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public static final int width = 800, height = 450, scale = 2;
    public static boolean running = false;
    public Thread gameThread;
    private Player player;
    private Map map;
    private Animacie animacie;
    private BufferedImage sanoDoleLava;
    private BufferedImage sanoDolePrava;
    private BufferedImage sanoSkokPrava;
    private BufferedImage sanoSkokLava;
    private BufferedImage blockHore;
    private BufferedImage blockDole;
    private BufferedImage spike;
    private BufferedImage pozadie;

    public void init(){
        ImageLoader img = new ImageLoader();
        sanoDoleLava = img.load("/obrazky/sanoZemLava.png");
        sanoSkokLava=img.load("/obrazky/sanoSkokLava.png");
        sanoDolePrava =img.load("/obrazky/sanoZemPrava.png");
        sanoSkokPrava=img.load("/obrazky/sanoSkokPrava.png");
        pozadie =img.load("/obrazky/pozadie.png");
        spike =img.load("/obrazky/spike.png");
        blockHore=img.load("/obrazky/blockHore.png");
        blockDole=img.load("/obrazky/blockDole.png");
        player = new Player();
        map = new Map();
        this.addKeyListener(new KeyInput());
        animacie = new Animacie();
        animacie.getImg(sanoDoleLava,sanoDolePrava,sanoSkokLava,sanoSkokPrava);
        map.getImg(spike,blockHore,blockDole);
        player.koniec("mapa");
    }

    public synchronized void start(){
        if(running)return;
        running=true;
        gameThread =new Thread(this);
        gameThread.start();
    }

    public synchronized void stop(){
        if(!running)return;
        running=false;
        try { gameThread.join(); } catch (InterruptedException e) { e.printStackTrace(); }
    }

    @Override
    public void run() {
        requestFocus();
        init();
        long lastTime = System.nanoTime();
        final double amoutOfTicks =60D;
        double ns = 1000000000/amoutOfTicks;
        double delta =0;
        while(running){
            long now = System.nanoTime();
            delta+=(now-lastTime)/ns;
            lastTime=now;
            if(delta>=1){
                tick();
                render();
                delta--;
            }
        }
        stop();
    }
    public void tick(){
        player.tick();
        animacie.tick();
    }
    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs==null){
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        AffineTransform a = AffineTransform.getTranslateInstance(0,0);
        a.scale(2,2);
        //
        ((Graphics2D) g).drawImage(pozadie,a,null);
        //g.fillRect(0,0,width*scale,height*scale);
        g.setColor(Color.BLACK);
        //if(player.jeMapka()==4){g.drawRect(0,0,width*scale,height*scale);}
        player.render(g);
        map.render(g);
        animacie.renderPlayer(g);
        animacie.renderMapChange(g);
        //
        g.dispose();
        bs.show();
    }
    public static void main(String[]args){
        gameCore game = new gameCore();
        game.setPreferredSize(new Dimension(width*scale,height*scale));
        new Window(width,height,scale,game);
        game.start();
    }
}
