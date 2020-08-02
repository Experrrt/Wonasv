
import java.awt.*;
import java.awt.image.BufferedImage;

public class Animacie {
    Player player = new Player();
    Map map = new Map();
    private BufferedImage sanoDolePrava;
    private BufferedImage sanoDoleLava;
    private BufferedImage sanoSkokPrava;
    private BufferedImage sanoSkokLava;
    private static Boolean vpravo=true;
    private static Boolean vlavo=false;
    static boolean dole=false;
    static int mapa=0;
    float a=0;
    float b=0;
    double al = 0;
    Font font =new Font("Courier",Font.BOLD,50);
    public void getImg(BufferedImage sanoDoleLava,BufferedImage sanoDolePrava,BufferedImage sanoSkokLava,BufferedImage sanoSkokPrava){
        this.sanoDoleLava =sanoDoleLava;
        this.sanoDolePrava=sanoDolePrava;
        this.sanoSkokLava=sanoSkokLava;
        this.sanoSkokPrava=sanoSkokPrava;
    }

    public void tick(){
        if(player.getVelX()<0){
            vlavo=true;
            vpravo=false;
        }
        else if(player.getVelX()>0){
            vlavo=false;
            vpravo=true;
        }
    }

    public void renderPlayer(Graphics g){
        int daco=player.getVelY();
        Graphics2D g2d = (Graphics2D)g;
        if(player.getHorCol()&&daco==0){
            daco+=1;
        }
        if(daco==0) {
            if (vpravo) {
                g2d.drawImage(sanoDolePrava, null, player.getX(), player.getY());
            } else if (vlavo) {
                g2d.drawImage(sanoDoleLava, null, player.getX(), player.getY());
            }
        }

        if(daco!=0|player.getHorCol()){
            if (vpravo) {
                g2d.drawImage(sanoSkokPrava, null, player.getX(), player.getY());
            } else if (vlavo) {
                g2d.drawImage(sanoSkokLava, null, player.getX(), player.getY());
            }
        }

    }
    public void renderMapChange(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.setFont(new Font("Courier",Font.BOLD,15));
        if(player.jeMapka()==3){g2d.drawString("The end",15,25);}
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        if(player.jeMapka()>mapa) {a=0;dole=false;mapa=player.jeMapka();}
        g2d.setColor(new Color(255, 255, 255, (int) animZaciatok()));
        g2d.setFont(font);
        g2d.drawString("Level "+player.jeMapka(), 700, 425);
        g2d.setColor(Color.BLACK);
        if(player.jeMapka()==4){g2d.fillRect(0,0,1600,900);if(b<200)b+=1; g2d.setColor(new Color(255, 5, 0,(int)b));g2d.drawString("Konecna",700,425);if(b==200){if(al<1){al+=0.008;}AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,(float)al);g2d.setComposite(ac);g2d.drawImage(sanoDoleLava,null,790,250);}}
        }
    public float animZaciatok(){if(a<255&&!dole){a+=2.5;}if(a==255){dole=true;}if(a>0&&dole){a-=2.5;} return a;}
}
