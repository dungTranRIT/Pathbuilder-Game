package Players.notLazy;


/**
 * Created by Zung on 3/29/17.
 */
public class Edge {

    private Node fromNode;
    private Node toNode;
    private int weigh;

    public Edge(Node from, Node to, int weigh) {
        this.fromNode = from;
        this.toNode = to;
        this.weigh = weigh;
    }

    public Node getFromNode() {
        return this.fromNode;
    }

    public Node getToNode() {
        return this.toNode;
    }

    public int getWeigh() {
        return this.weigh;
    }

    public void setWeigh(int new_weigh) {
        this.weigh = new_weigh;
    }
}
