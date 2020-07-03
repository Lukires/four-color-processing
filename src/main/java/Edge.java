public class Edge {

    private Node to, from;

    public Node getTo() {
        return to;
    }

    public Node getFrom() {
        return from;
    }

    public Edge(Node to, Node from) {
        this.to=to;
        this.from=from;
    }
}
