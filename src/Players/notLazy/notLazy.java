package Players.notLazy;

import Interface.PlayerModulePart1;
import Interface.PlayerModulePart2;
import Interface.PlayerMove;
import Interface.Coordinate;

import java.util.*;

/**
 * Created by Zung on 3/13/17.
 */
public class notLazy implements PlayerModulePart2 {

    private Node[][] board;
    private int playerId;

    /**
     * Initialize a player and create a new board
     *
     * @param dim: represent size of the board
     * @param playerId the playing player ID
     */
    public void initPlayer(int dim, int playerId) {
        this.board = new Node[2*dim+1][2*dim+1];
        this.playerId = playerId;
        for (int i=0; i<this.board.length; i++) {
            for (int j=0; j<this.board[i].length; j++) {
                if (i % 2 + j % 2 == 1) {
                    Coordinate coordinate = new Coordinate(i, j);
                    Node boardNode = new Node(coordinate);
                    this.board[i][j] = boardNode;
                }
            }
        }
        for (int i=0; i<this.board.length; i++) {
            for (int j=0; j<this.board[i].length; j++) {
                if (i % 2 + j % 2 == 1) {
                    if (j == 0) {
                        this.board[i][j].addNeighbor(this.board[i][j+2], 1);
                    } else if (i == 0) {
                        this.board[i][j].addNeighbor(this.board[i+2][j], 1);
                    } else if (j == this.board.length-1) {
                        this.board[i][j].addNeighbor(this.board[i][j-2], 1);
                    } else if (i == this.board.length-1) {
                        this.board[i][j].addNeighbor(this.board[i-2][j], 1);
                    } else if (i == 1 && j != 0 && j != this.board.length-1) {
                        this.board[i][j].addNeighbor(this.board[i][j-2], 1);
                        this.board[i][j].addNeighbor(this.board[i][j+2], 1);
                        this.board[i][j].addNeighbor(this.board[i+2][j],1);
                    } else if (i == this.board.length-2 && j != 0 && j != this.board.length-1) {
                        this.board[i][j].addNeighbor(this.board[i][j-2], 1);
                        this.board[i][j].addNeighbor(this.board[i][j+2], 1);
                        this.board[i][j].addNeighbor(this.board[i-2][j], 1);
                    } else if (j == 1 && i != 0 && i != this.board.length-1) {
                        this.board[i][j].addNeighbor(this.board[i-2][j], 1);
                        this.board[i][j].addNeighbor(this.board[i+2][j], 1);
                        this.board[i][j].addNeighbor(this.board[i][j+2], 1);
                    } else if (j == this.board.length-2 && i != 0 && i != this.board.length-1) {
                        this.board[i][j].addNeighbor(this.board[i-2][j], 1);
                        this.board[i][j].addNeighbor(this.board[i+2][j], 1);
                        this.board[i][j].addNeighbor(this.board[i][j-2], 1);
                    } else {
                        this.board[i][j].addNeighbor(this.board[i-2][j], 1);
                        this.board[i][j].addNeighbor(this.board[i+2][j], 1);
                        this.board[i][j].addNeighbor(this.board[i][j-2], 1);
                        this.board[i][j].addNeighbor(this.board[i][j+2], 1);
                    }
                }
            }
        }
    }

    /**
     * Manipulate the board with recent move
     *
     * @param m: most recent move
     */
    public void lastMove(PlayerMove m) {
        int row = m.getCoordinate().getRow();
        int col = m.getCoordinate().getCol();
        int playerId = m.getPlayerId();
        if (playerId % 2 == 1 && row % 2 == 1) {
            this.board[row][col-1].addConnected(this.board[row][col+1]);
            this.board[row][col+1].addConnected(this.board[row][col-1]);
            for (Edge edge : this.board[row][col-1].getEdges()) {
                if (edge.getToNode() == this.board[row][col+1]) {
                    edge.setWeigh(0);
                }
            }
            for (Edge edge : this.board[row][col+1].getEdges()) {
                if (edge.getToNode() == this.board[row][col-1]) {
                    edge.setWeigh(0);
                }
            }
            for (Edge edge : this.board[row-1][col].getEdges()) {
                if (edge.getToNode() == this.board[row+1][col]) {
                    edge.setWeigh(Integer.MAX_VALUE);
                }
            }
            for (Edge edge : this.board[row+1][col].getEdges()) {
                if (edge.getToNode() == this.board[row-1][col]) {
                    edge.setWeigh(Integer.MAX_VALUE);
                }
            }
        } else if (playerId % 2 == 1 && row % 2 == 0) {
            this.board[row-1][col].addConnected(this.board[row+1][col]);
            this.board[row+1][col].addConnected(this.board[row-1][col]);
            for (Edge edge : this.board[row-1][col].getEdges()) {
                if (edge.getToNode() == this.board[row+1][col]) {
                    edge.setWeigh(0);
                }
            }
            for (Edge edge : this.board[row+1][col].getEdges()) {
                if (edge.getToNode() == this.board[row-1][col]) {
                    edge.setWeigh(0);
                }
            }
            for (Edge edge : this.board[row][col-1].getEdges()) {
                if (edge.getToNode() == this.board[row][col+1]) {
                    edge.setWeigh(Integer.MAX_VALUE);
                }
            }
            for (Edge edge : this.board[row][col+1].getEdges()) {
                if (edge.getToNode() == this.board[row][col-1]) {
                    edge.setWeigh(Integer.MAX_VALUE);
                }
            }
        } else if (playerId % 2 == 0 && row % 2 == 1) {
            this.board[row-1][col].addConnected(this.board[row+1][col]);
            this.board[row+1][col].addConnected(this.board[row-1][col]);
            for (Edge edge : this.board[row-1][col].getEdges()) {
                if (edge.getToNode() == this.board[row+1][col]) {
                    edge.setWeigh(0);
                }
            }
            for (Edge edge : this.board[row+1][col].getEdges()) {
                if (edge.getToNode() == this.board[row-1][col]) {
                    edge.setWeigh(0);
                }
            }
            for (Edge edge : this.board[row][col-1].getEdges()) {
                if (edge.getToNode() == this.board[row][col+1]) {
                    edge.setWeigh(Integer.MAX_VALUE);
                }
            }
            for (Edge edge : this.board[row][col+1].getEdges()) {
                if (edge.getToNode() == this.board[row][col-1]) {
                    edge.setWeigh(Integer.MAX_VALUE);
                }
            }
        } else {
            this.board[row][col-1].addConnected(this.board[row][col+1]);
            this.board[row][col+1].addConnected(this.board[row][col-1]);
            for (Edge edge : this.board[row][col-1].getEdges()) {
                if (edge.getToNode() == this.board[row][col+1]) {
                    edge.setWeigh(0);
                }
            }
            for (Edge edge : this.board[row][col+1].getEdges()) {
                if (edge.getToNode() == this.board[row][col-1]) {
                    edge.setWeigh(0);
                }
            }
            for (Edge edge : this.board[row-1][col].getEdges()) {
                if (edge.getToNode() == this.board[row+1][col]) {
                    edge.setWeigh(Integer.MAX_VALUE);
                }
            }
            for (Edge edge : this.board[row+1][col].getEdges()) {
                if (edge.getToNode() == this.board[row-1][col]) {
                    edge.setWeigh(Integer.MAX_VALUE);
                }
            }
        }
    }

    /**
     * Check if the player has won the game
     *
     * @param id player ID to check
     * @return true if win, false if no
     */
    public boolean hasWonGame(int id) {
        if (id % 2 == 1) {
            for (int row=1; row<this.board.length; row+=2) {
                List<Node> queue = new ArrayList<>();
                queue.add(this.board[row][0]);
                Set<Node> visited = new HashSet<>();
                visited.add(this.board[row][0]);
                while (queue.size() > 0) {
                    Node currentNode = queue.remove(0);
                    int currentCol = currentNode.getCoordinate().getCol();
                    if (currentCol == this.board.length-1) {
                        return true;
                    }
                    for (Node neighbor : currentNode.getConnected()) {
                        if (!visited.contains(neighbor)) {
                            queue.add(neighbor);
                            visited.add(neighbor);
                        }
                    }
                }
            }
            return false;
        } else {
            for (int col=1; col<this.board.length; col+=2) {
                List<Node> queue = new ArrayList<>();
                queue.add(this.board[0][col]);
                Set<Node> visited = new HashSet<>();
                visited.add(this.board[0][col]);
                while (queue.size() > 0) {
                    Node currentNode = queue.remove(0);
                    int currentRow = currentNode.getCoordinate().getRow();
                    if (currentRow == this.board.length-1) {
                        return true;
                    }
                    for (Node neighbor : currentNode.getConnected()) {
                        if (!visited.contains(neighbor)) {
                            queue.add(neighbor);
                            visited.add(neighbor);
                        }
                    }
                }
            }
            return false;
        }
    }

    public void otherPlayerInvalidated() {

    }

    /**
     * Get a valid player move
     *
     * @return a valid player move
     */
    public PlayerMove move() {
        List<PlayerMove> moveList = allLegalMoves();
        return moveList.remove(0);
    }

    /**
     * Get a list of all the legal player moves
     *
     * @return a list of all valid player moves
     */
    @Override
    public List<PlayerMove> allLegalMoves() {
        ArrayList<PlayerMove> movelist = new ArrayList<>();
        for (int i=0; i<this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                if((i!=0) && (j!=0) && (i!=this.board.length-1) && (j!=this.board.length-1)) {
                    if (i % 2 + j % 2 != 1) {
                        for(Edge edge : this.board[i][j-1].getEdges()) {
                            if (edge.getToNode() == this.board[i][j+1]) {
                                if (edge.getWeigh()== 1) {
                                    Coordinate coord = new Coordinate(i,j);
                                    PlayerMove move = new PlayerMove(coord, this.playerId);
                                    movelist.add(move);
                                }
                            }
                        }
                    }
                }
            }
        }
        return movelist;
    }

    /**
     * Find out the fewest number of segments to victory
     *
     * @param playerId player id
     * @return number of segments to win
     */
    @Override
    public int fewestSegmentsToVictory(int playerId) {
        Map<Node, Integer> distance = new HashMap<>();
        Map<Node, Node> predecessors = new HashMap<>();
        ArrayList<Node> startList = new ArrayList<>();
        ArrayList<Node> finishList = new ArrayList<>();
        if (playerId % 2 == 1) {
            for (int i=0; i<this.board.length; i++) {
                if (i % 2 == 1) {
                    startList.add(this.board[i][0]);
                    finishList.add(this.board[i][this.board.length-1]);
                }
            }
        } else {
            for (int j=0; j<this.board.length; j++) {
                if (j % 2 == 1) {
                    startList.add(this.board[0][j]);
                    finishList.add(this.board[this.board.length-1][j]);
                }
            }
        }
        dijkstra(startList, distance, predecessors, playerId);
        Node minNode = dequeueMin(finishList, distance);
        return distance.get(minNode);
    }

    /**
     * Dijkstra implementation to find the shortest path
     *
     * @param startList a list of start nodes
     * @param distance a collection of distance values that nodes hold
     * @param predecessors a collection of all nodes predecessors
     * @param playerId player id
     */
    private void dijkstra(ArrayList<Node> startList, Map<Node, Integer> distance,
                          Map<Node, Node> predecessors, int playerId) {

        List<Node> priorityQ = new ArrayList<>();

        for (int i=0; i<this.board.length; i++) {
            for (int j=0; j<this.board.length; j++) {
                if (playerId % 2 == 1) {
                    if (i % 2 == 1) {
                        if (j % 2 == 0) {
                            priorityQ.add(this.board[i][j]);
                            distance.put(this.board[i][j], Integer.MAX_VALUE);
                        }
                    }
                } else {
                    if (i % 2 == 0) {
                        if (j % 2 == 1) {
                            priorityQ.add(this.board[i][j]);
                            distance.put(this.board[i][j], Integer.MAX_VALUE);
                        }
                    }
                }
            }
        }

        for (Node startNode : startList) {
            distance.put(startNode, 0);
        }

        for (Node startNode : startList) {
            predecessors.put(startNode, startNode);
        }

        while (!priorityQ.isEmpty()) {
            Node U = dequeueMin(priorityQ, distance);

            if(distance.get(U) == Integer.MAX_VALUE) {
                return;
            }
            for(Edge edge : U.getEdges()) {
                Integer weight = edge.getWeigh();
                Node node = edge.getToNode();
                if (weight != Integer.MAX_VALUE) {
                    Integer distViaU = distance.get(U) + weight;
                    if (distance.get(node) > distViaU) {
                        distance.put(node, distViaU);
                        predecessors.put(node, U);
                    }
                }
            }
        }
    }

    /**
     * Get the node with the minimum distance value
     *
     * @param priorityQ a list of all the nodes not having finalized yet
     * @param distance a collection of distance values that all nodes hold
     * @return the node with the minimum distance value
     */
    private Node dequeueMin(List<Node> priorityQ, Map<Node, Integer> distance) {

        Node minNode = priorityQ.get(0);
        for (Node n : priorityQ) {
            if(distance.get(n) < distance.get(minNode)) {
                minNode = n;
            }
        }
        return priorityQ.remove(priorityQ.indexOf(minNode));
    }
}
