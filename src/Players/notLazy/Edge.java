package Players.notLazy;


/**
 * Created by Zung on 3/29/17.
 */
public class Edge {

    private Node fromNode;
    private Node toNode;
    private int weigh;

    /**
     * Construct an edge
     *
     * @param from from node
     * @param to to node
     * @param weigh weight of the edge
     */
    public Edge(Node from, Node to, int weigh) {
        this.fromNode = from;
        this.toNode = to;
        this.weigh = weigh;
    }

    /**
     * Get the destination node
     *
     * @return the destination node
     */
    public Node getFromNode() {
        return this.fromNode;
    }

    /**
     * Get the source node
     *
     * @return the source node
     */
    public Node getToNode() {
        return this.toNode;
    }

    /**
     * Get the weight of the edge
     *
     * @return the weight of the edge
     */
    public int getWeigh() {
        return this.weigh;
    }

    /**
     * Set a new weight for the edge
     *
     * @param new_weigh new weight
     */
    public void setWeigh(int new_weigh) {
        this.weigh = new_weigh;
    }
}
