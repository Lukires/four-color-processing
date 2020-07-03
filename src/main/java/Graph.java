import megamu.mesh.Delaunay;
import megamu.mesh.Voronoi;
import processing.core.PApplet;

import java.util.*;

public class Graph extends Delaunay {

    private final Plane plane;
    public Node[] nodes;

    public Graph(Plane plane) {
        super(plane.points);
        this.plane=plane;

        //Node wrapper around points
        nodes = new Node[plane.points.length];
        int[][] links = this.getLinks();
        for(int i = 0; i < links.length; i++) {
            int fromIndex = links[i][0];
            int toIndex = links[i][1];

            Node from = nodes[fromIndex]==null?new Node():nodes[fromIndex];
            Node to = nodes[toIndex]==null?new Node():nodes[toIndex];

            Edge edge = new Edge(from, to);
            from.edges.add(edge);
            to.edges.add(edge);

            nodes[fromIndex] = from;
            nodes[toIndex] = to;

        }
    }


    //We use the Welsh Powell Graph colouring Algorithm which is a type of greedy coloring ordering
    public void colorize() {

        ArrayList<Node> sortedNodes = new ArrayList<Node>();
        sortedNodes.addAll(Arrays.asList(nodes));
        sortedNodes.sort(new NodeComparator());

        ArrayList<Node> tempNodes = new ArrayList<Node>();


        for(int i = 0; i < 4; i++) {

            for(int j = 0; j < sortedNodes.size(); j++) {
                Node node = sortedNodes.get(j);

                boolean colorUsed = false;
                for(Edge edges : node.edges) {
                    Node from = edges.getFrom();
                    Node to = edges.getTo();

                    if(to.color == (Colors.values()[i]) || from.color == (Colors.values()[i])) {
                        colorUsed = true;
                        break;
                    }
                }

                if(!colorUsed) {
                    node.color = Colors.values()[i];
                }else{
                    tempNodes.add(node);
                }

            }
            sortedNodes.clear();
            sortedNodes.addAll(tempNodes);
            tempNodes.clear();
        }
    }


    private class NodeComparator implements Comparator<Node> {

        //We want the elements with the most edges to be the first in the list
        @Override
        public int compare(Node n1, Node n2) {
            return (int)Math.signum(n2.edges.size() - n1.edges.size());
        }
    }

}
