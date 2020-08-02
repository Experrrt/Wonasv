
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Map {
    private static ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();
    private static ArrayList<Integer> spikes = new ArrayList<Integer>();
    private static ArrayList<Polygon> polygons = new ArrayList<Polygon>();
    static String mapka ="";
    private BufferedImage spike;
    private BufferedImage blockHore;
    private BufferedImage blockDole;
    private String load;
    private String load2;
    private String load3;
    private String load4;
    private  String []udaje;
    private  String []udaje2;
    private  String []udaje3;
    private  String []udaje4;
    private ArrayList<Integer> rec = new ArrayList<Integer>();
    private ArrayList<Integer> end = new ArrayList<Integer>();
    private ArrayList<Integer> trian = new ArrayList<Integer>();
    private ArrayList<Integer> spawn = new ArrayList<Integer>();

    public void Mapa(){
        rectangles.clear();
        //spikes.clear();
        //polygons.clear();
        for (int i = 3; i< rec.size(); i+=4){rectangles.add(new Rectangle(rec.get(i-3), rec.get(i-2), rec.get(i-1), rec.get(i)));}
        spikes=trian;
        loadSpike();
    }

    public void mapLoader(String mapa){
        mapka=mapa;
        rec.clear();
        end.clear();
        trian.clear();
        spikes.clear();
        polygons.clear();
        spawn.clear();
        BufferedReader txtReader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("mapy/"+mapa)));
        try {
            load=txtReader.readLine();
            load2=txtReader.readLine();
            load3=txtReader.readLine();
            load4=txtReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        udaje =load.split(",");
        udaje2=load2.split(",");
        udaje3=load3.split(",");
        udaje4=load4.split(",");
        for (int i = 0; i < udaje.length; i++) { rec.add(Integer.parseInt(udaje[i])); }
        for (int i = 0; i < udaje2.length; i++) { end.add(Integer.parseInt(udaje2[i])); }
        for (int i = 0; i < udaje3.length; i++) { trian.add(Integer.parseInt(udaje3[i])); }
        for (int i = 0; i < udaje4.length; i++) { spawn.add(Integer.parseInt(udaje4[i])); }
        Mapa();

    }
    public ArrayList<Integer> getEnd(){ return end; }
    public ArrayList<Integer> getSpawn(){ return spawn; }

    public void getImg(BufferedImage spikes,BufferedImage blockHore,BufferedImage blockDole){
        this.spike=spikes;
        this.blockHore=blockHore;
        this.blockDole=blockDole;
    }
    public void render(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.RED);
        //for(int i=0;i<rectangles.size();i++) { g2d.draw(rectangles.get(i)); }
        //for(int i=0;i<polygons.size();i++){g2d.draw(polygons.get(i));}
        //for(int i=0;i<)
        //g2d.drawRect(50,50,100,100);
        for(int i=1;i<spikes.size();i+=2){g2d.drawImage(spike,null,spikes.get(i-1)-2,spikes.get(i)-31);}
        for(int i=0;i<rectangles.size();i++) {
            for (int x = 0; x < rectangles.get(i).getHeight(); x += 25) {
                for (int y = 0; y < rectangles.get(i).getWidth(); y += 25) {
                   if(x==0){g2d.drawImage(blockHore, null, (int) rectangles.get(i).getX() + y, (int) rectangles.get(i).getY()+x);}
                    else{g2d.drawImage(blockDole, null, (int) rectangles.get(i).getX() + y, (int) rectangles.get(i).getY()+x);}
                }

                //rectangles.get(i);
            }
        }
        }


    public Rectangle getBounds(int x,int y,int width,int height){
        return new Rectangle(x,y,width,height);

    }
    public Rectangle getTop(int i){
        //mapa();
        return new Rectangle((int)rectangles.get(i).getX()-2,(int)rectangles.get(i).getY()-5,(int)rectangles.get(i).getWidth()+4,(int)rectangles.get(i).getHeight()/5);
    }
    public Rectangle getBottom(int i){
        //mapa();
        return new Rectangle((int)rectangles.get(i).getX()-2,(int)rectangles.get(i).getY()+(int)rectangles.get(i).getHeight()-(int)rectangles.get(i).getHeight()/5,(int)rectangles.get(i).getWidth()+4,(int)rectangles.get(i).getHeight()/5+5);
    }
    public Rectangle getRight(int i){
        //mapa();
        return new Rectangle((int)rectangles.get(i).getX()+(int)rectangles.get(i).getWidth()-(int)rectangles.get(i).getWidth()/20-4,(int)rectangles.get(i).getY()+10,(int)rectangles.get(i).getWidth()/10+4,(int)rectangles.get(i).getHeight()-14);
    }
    public Rectangle getLeft(int i){
        //mapa();
        return new Rectangle((int)rectangles.get(i).getX()-6,(int)rectangles.get(i).getY()+10,(int)rectangles.get(i).getWidth()/10+4,(int)rectangles.get(i).getHeight()-14);
    }
    public Polygon spike(int i){
        //mapa();
        return polygons.get(i);
    }
    public int getSizeRect(){
        return rectangles.size();
    }
    public int getSizePoly(){
        return polygons.size();
    }

    public void loadSpike(){
        for(int i=0;i<spikes.size();i+=2){
               polygons.add(new Polygon(new int[]{spikes.get(i), spikes.get(i)+10, spikes.get(i)+20}, new int[]{spikes.get(i+1), spikes.get(i+1)-30, spikes.get(i+1)}, 3));
            }
    }
}