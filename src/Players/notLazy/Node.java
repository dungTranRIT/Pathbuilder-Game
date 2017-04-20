package Players.notLazy;

import Interface.Coordinate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Zung on 3/14/17.
 */
public class Node {

    private Coordinate coordinate;
    private List<Edge> neighbors;
    private List<Node> connected;

    /**
     * Construct a board node for the game
     *
     * @param coordinate coordinate of the node
     */
    public Node(Coordinate coordinate) {
        this.coordinate = coordinate;
        this.neighbors = new ArrayList<Edge>();
        this.connected = new ArrayList<Node>();
    }

    /**
     * Copy constructor of a new node
     *
     * @param other other node
     */
    protected Node(Node other) {
        this.coordinate = other.coordinate;
        this.neighbors = new ArrayList<Edge>();
        this.connected = new ArrayList<Node>();
    }

    /**
     * Get the coordinate of the node
     *
     * @return coordinate of the node
     */
    public Coordinate getCoordinate(){
        return this.coordinate;
    }

    /**
     * Add a neighbor to a node
     *
     * @param node node ready to get added
     * @param weigh cost to get to that node
     */
    public void addNeighbor(Node node, int weigh) {
        Edge edge = new Edge(this, node, weigh);
        if (!this.neighbors.contains(edge)) {
            this.neighbors.add(edge);
        }
    }

    /**
     * Add a connected node to the node
     *
     * @param node
     */
    public void addConnected(Node node) {
        if (!this.connected.contains(node)) {
            this.connected.add(node);
        }
    }

    /**
     * Get all the neighbors of a node
     *
     * @return collection of node neighbors
     */
    public Collection<Node> getNeighbors() {
        ArrayList<Node> nodeList = new ArrayList<Node>();
        for (Edge edge : this.neighbors) {
            nodeList.add(edge.getToNode());
        }
        return nodeList;
    }

    /**
     * Get all the edges of the node
     *
     * @return
     */
    public Collection<Edge> getEdges() {
        return this.neighbors;
    }

    /**
     * Get all the connected nodes to the node
     *
     * @return
     */
    public Collection<Node> getConnected() {
        return this.connected;
    }

}
