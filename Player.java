

import java.awt.*;

public class Player {
    Map map = new Map();
    static boolean skok =true;
    static int pocetSkok =0;
    static boolean pada = false;
    static boolean doubleJ = false;
    static boolean mapaDalsia =false;
    static int mapaka=0;
    private static boolean horizontalCollision =true;
    private static boolean verticaCollision =true;
    private static int spawnX=800,spawY=625,x=spawnX,y=spawY,velX,velY, gravity=1,W=30,H=55;
    public void velX(int velX){this.velX=velX;}
    public void velY(int velY){this.velY=velY;}
    public void y(int y){this.y=y;}
    public int getY(){return y;}
    public int getX(){return x;}
    public int getW(){return W;}
    public int getH(){return H;}
    public int getVelX(){return velX;}
    public int getVelY(){return velY;}
    public boolean getHorCol(){return pada;}
    public void tick() {
        falling();
        move();
        if(y<=0){y=0;velY=0;}
        if(y>=(820)){x=map.getSpawn().get(0);y=map.getSpawn().get(1);}
        if(x<=0)x=0;
        if(x>=(1562))x=(1562);
        if(x==810&&y==625){
            System.out.println("kokot");
        }
        endCollision();

    }
    public void move(){
        //System.out.println(velX);

        if(hasHorizontalCollision() && hasVerticalCollision()){horizontalCollision=false; }
        else if(hasHorizontalCollision()){velY=0;}
        //System.out.println(hasHorizontalCollision());
        //System.out.println(horizontalCollision+"***");
        //System.out.println(hasVerticalCollision()+"*");
        if(!hasHorizontalCollision() || !horizontalCollision)y+=velY;horizontalCollision=true;
        if(!hasVerticalCollision())x+=velX;
        if(spikeCollision()){x=map.getSpawn().get(0);y=map.getSpawn().get(1);velX=0;velY=0;}
        //System.out.println(hasHorizontalCollision());
        //System.out.println(horizontalCollision);
        //System.out.println(pada);
    }
    protected void falling(){
        velY += gravity;
        if(velY>5&&!skok)doubleJ=true;
        if(velY>12){velY=15;}
    }
    protected void jump(){
        if(skok&&pocetSkok==0){
            velY-=17;skok=false;pocetSkok++; }
        if(doubleJ&&pocetSkok==1){velY-=(20+velY/2);doubleJ=false;pocetSkok++;}
        pada=true;
    }
    protected boolean hasHorizontalCollision(){
        for (int i=0;i<map.getSizeRect();i++){
        if(map.getBounds(x,y,W,H).intersects(map.getTop(i))&&velY>0){skok =true;doubleJ=false;pocetSkok=0;pada=false;return true;}
        }
        for (int i=0;i<map.getSizeRect();i++){
        if(map.getBounds(x,y,W,H).intersects(map.getBottom(i))&&velY<0){velY=0;pada=false;return true;}
        }
        horizontalCollision=false;
        return false;
    }
    protected boolean hasVerticalCollision(){
        for (int i=0;i<map.getSizeRect();i++){
        if(map.getBounds(x,y,W,H).intersects(map.getRight(i))&&velX<=0){velX=0;verticaCollision =true;return true;}
        }
        for (int i=0;i<map.getSizeRect();i++){
        if(map.getBounds(x,y,W,H).intersects(map.getLeft(i))&&velX>=0){velX=0;verticaCollision =true;return true;}
        }
        verticaCollision =false;
        return false;
    }
    protected boolean spikeCollision(){
        for (int i=0;i<map.getSizePoly();i++){
            if(map.spike(i).intersects(map.getBounds(x,y,W,H))){return true;}
        }
        return false;
    }
    public void endCollision(){
        if(map.getBounds(x,y,W,H).intersects(new Rectangle(map.getEnd().get(0),map.getEnd().get(1),20,20))&&mapaDalsia){mapChange();mapaDalsia=false;x=map.getSpawn().get(0);y=map.getSpawn().get(1);}
        if(!(map.getBounds(x,y,W,H).intersects(new Rectangle(map.getEnd().get(0),map.getEnd().get(1),20,20)))){mapaDalsia=true;}
    }
    public void mapChange(){
           koniec("mapa");}


    public void koniec(String mapa){
        if(mapaka<4) {
            mapaka++;
            map.mapLoader(mapa + mapaka);

        }
        }
    public int jeMapka(){
        return mapaka;
    }
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.RED);
        g2d.setColor(Color.BLUE);
        g2d.drawRect(map.getEnd().get(0),map.getEnd().get(1),20,20);
        for (int i=0;i<5;i++){g2d.draw(map.getTop(i));g2d.draw(map.getBottom(i));g2d.draw(map.getLeft(i));g2d.draw(map.getRight(i));}
    }

}