package leet_again.graphs;

import java.util.*;

/*
 * This problem can be solved in two ways
 * 1. DFS: Use dfs recursively and maintain a map of old node as key and new node as its val
 * If the old node already exist that means new node is also there right coz we are creating the map and putting this while iterating through the neigbours lisy
 * If the node exist in map simply add it as new nodes neighbour(else part), if not create a new node and do a dfs
 *
 * 2. BFS:
 *
 * */
public class CloneGraph {
    Map<Node, Node> map = new HashMap<>();

    public Node cloneGraph2(Node node) {
        Node startNewNode = new Node();
        if (node == null)
            return null;
        startNewNode.val = node.val;
        map.put(node, startNewNode);
        dfs(node, startNewNode, map);
        return startNewNode;
    }


    public Node cloneGraph(Node node) {
        Queue<Node> queue = new LinkedList<>();
        Node startNewNode = new Node();
        if (node == null)
            return null;
        startNewNode.val = node.val;
        queue.add(node);
        map.put(node, startNewNode);
        bfs(queue, startNewNode);
        return map.get(node);
    }

    private void bfs(Queue<Node> queue, Node startNewNode) {
        Node clonedNode = startNewNode;
        while (!queue.isEmpty()) {
            Node n = queue.remove();
            for (Node neighbour : n.neighbors) {
                if (!map.containsKey(neighbour)) {
                    Node newNode = new Node();
                    newNode.val = neighbour.val;
                    clonedNode.neighbors.add(newNode);
                    map.put(neighbour, newNode);
                    queue.add(neighbour);
                } else {
                    clonedNode.neighbors.add(map.get(neighbour));
                }
            }
        }
    }


    void dfs(Node existing, Node startNode, Map<Node, Node> map) {
        for (Node neighbour : existing.neighbors) {
            if (!map.containsKey(neighbour)) {
                Node newNode = new Node();
                newNode.val = neighbour.val;
                startNode.neighbors.add(newNode);
                map.put(neighbour, newNode);
                dfs(neighbour, newNode, map);
            } else {
                startNode.neighbors.add(map.get(neighbour));
            }
        }
    }

    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);

// Set neighbors
        n1.neighbors.add(n2);
        n1.neighbors.add(n4);

        n2.neighbors.add(n1);
        n2.neighbors.add(n3);

        n3.neighbors.add(n2);
        n3.neighbors.add(n4);

        n4.neighbors.add(n1);
        n4.neighbors.add(n3);

// starting node
        Node start = n1;
        new CloneGraph().cloneGraph(start);
    }
}

class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}