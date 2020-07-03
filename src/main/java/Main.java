import megamu.mesh.Delaunay;
import megamu.mesh.MPolygon;
import megamu.mesh.Voronoi;
import processing.core.PApplet;

public class Main extends PApplet {
    public static void main(String[] args) {
        main("Main");
    }


    public void settings() {
        size(1400,860);
    }


    boolean loop = true;
    Plane plane;
    Graph graph;
    float[][] points;
    public void setup() {

        int amount = (int)random(10,50);
        points = new float[amount][2];

        for(int i = 0; i < amount; i++) {
            int x = (int)random(0,width);
            int y = (int)random(0,height);
            points[i][0] = x;
            points[i][1] = y;
        }
        plane = new Plane( points );
        //Delaunay triangulation is the dual graph of voronoi
        graph = new Graph( plane );
        graph.colorize();
    }

    public void draw() {

        for(int i=0; i<graph.nodes.length; i++) {
            Node node = graph.nodes[i];
            Colors color = node.color;
            if (color == null) {
                setup();
                return;
            }
        }

        clear();
        background(255,255,255);


        float[][] myEdges = plane.getEdges();

        for(int i=0; i<myEdges.length; i++)
        {
            float startX = myEdges[i][0];
            float startY = myEdges[i][1];
            float endX = myEdges[i][2];
            float endY = myEdges[i][3];
            line( startX, startY, endX, endY );
        }

        MPolygon[] regions = plane.getRegions();


        int[][] myLinks = graph.getLinks();
        //stroke(255,0,0);
        for(int i=0; i<myLinks.length; i++)
        {
            int startIndex = myLinks[i][0];
            int endIndex = myLinks[i][1];

            float startX = points[startIndex][0];
            float startY = points[startIndex][1];
            float endX = points[endIndex][0];
            float endY = points[endIndex][1];
            line( startX, startY, endX, endY );
        }

        for(int i=0; i<graph.nodes.length; i++) {
            Node node = graph.nodes[i];
            Colors color = node.color;
            int[] RGB = color==null?new int[]{0,0,0}:color.getRGB();

            float[][] regionCoordinates = regions[i].getCoords();

            fill(RGB[0], RGB[1], RGB[2]);
            regions[i].draw(this); // draw this shape

        }

        if(loop) {
            setup();
        }else{
            noLoop();
        }
    }
}
