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
                        this.board[i][j].setDistance(0);
                        this.board[i][j].addNeighbor(this.board[i][j+2], 1);
                    } else if (i == 0) {
                        this.board[i][j].setDistance(0);
                        this.board[i][j].addNeighbor(this.board[i+2][j], 1);
                    } else if (j == this.board.length-1) {
                        this.board[i][j].setDistance(Integer.MAX_VALUE);
                        this.board[i][j].addNeighbor(this.board[i][j-2], 1);
                    } else if (i == this.board.length-1) {
                        this.board[i][j].setDistance(Integer.MAX_VALUE);
                        this.board[i][j].addNeighbor(this.board[i-2][j], 1);
                    } else if (i == 1 && j != 0 && j != this.board.length-1) {
                        this.board[i][j].setDistance(Integer.MAX_VALUE);
                        this.board[i][j].addNeighbor(this.board[i][j-2], 1);
                        this.board[i][j].addNeighbor(this.board[i][j+2], 1);
                        this.board[i][j].addNeighbor(this.board[i+2][j],1);
                    } else if (i == this.board.length-2 && j != 0 && j != this.board.length-1) {
                        this.board[i][j].setDistance(Integer.MAX_VALUE);
                        this.board[i][j].addNeighbor(this.board[i][j-2], 1);
                        this.board[i][j].addNeighbor(this.board[i][j+2], 1);
                        this.board[i][j].addNeighbor(this.board[i-2][j], 1);
                    } else if (j == 1 && i != 0 && i != this.board.length-1) {
                        this.board[i][j].setDistance(Integer.MAX_VALUE);
                        this.board[i][j].addNeighbor(this.board[i-2][j], 1);
                        this.board[i][j].addNeighbor(this.board[i+2][j], 1);
                        this.board[i][j].addNeighbor(this.board[i][j+2], 1);
                    } else if (j == this.board.length-2 && i != 0 && i != this.board.length-1) {
                        this.board[i][j].setDistance(Integer.MAX_VALUE);
                        this.board[i][j].addNeighbor(this.board[i-2][j], 1);
                        this.board[i][j].addNeighbor(this.board[i+2][j], 1);
                        this.board[i][j].addNeighbor(this.board[i][j-2], 1);
                    } else {
                        this.board[i][j].setDistance(Integer.MAX_VALUE);
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
    public PlayerMove move() {
        return null;
    }

    @Override
    public List<PlayerMove> allLegalMoves() {
        return new LinkedList<>();
    }

    @Override
    public int fewestSegmentsToVictory(int i) {
        return 0;
    }
    }
