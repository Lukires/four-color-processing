import megamu.mesh.Voronoi;

public class Plane extends Voronoi {


    public float[][] points;
    public Plane(float[][] points) {
        super(points);
        this.points=points;
    }
}
