package graph;

import java.util.*;

public class CloneGraph {
//    Fails few cases
    public Node cloneGraph2(Node node) {
        Map<Integer, Node> map = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        if (node == null)
            return null;
        queue.add(node);
        boolean first = true;
        Node head = null;
        while (!queue.isEmpty()) {
            Node n = queue.remove();
            if (map.containsKey(n.val)) {
                Node temp = map.get(n.val);
                for (Node neighbour : n.neighbors) {
                    if (map.containsKey(neighbour.val)) {
//                        TODO: check if the neighbour is not already added then only add
                        //add neighbours to the current node and add current node to the neighbours
                        Node neighbour2 = map.get(neighbour.val);
//                        temp.neighbors.add(neighbour2);
                        boolean shouldAdd = true;
                        for (int i = 0; i < neighbour2.neighbors.size(); i++) {
                            if (temp.val == neighbour2.neighbors.get(i).val) {
                                shouldAdd = false;
                                break;
                            }
                        }
                        if (shouldAdd)
                            neighbour2.neighbors.add(temp);

                    } else {
                        queue.add(n);
                    }
                }
//                temp.neighbors = n.neighbors;
            } else {
                Node newNode = new Node();
                if (first) {
                    head = newNode;
                    first = false;
                }
                newNode.val = n.val;
                if (n.neighbors != null) {
                    queue.addAll(n.neighbors);
                }
                map.put(n.val, newNode);
            }
        }
        return head;
    }

    public Node cloneGraph(Node node){
//        map storing old node and new node
        Map<Node,Node> map = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        if(node==null)
            return null;
        if(node.neighbors.isEmpty()){
            return new Node(node.val);
        }
        queue.add(node);
        map.put(node,new Node(node.val));
        while(!queue.isEmpty()){
            Node n = queue.remove();
            for (Node neighbour: n.neighbors
                 ) {
                if(!map.containsKey(neighbour)){
                    map.put(neighbour, new Node(neighbour.val));
                    queue.add(neighbour);
                }
                map.get(n).neighbors.add( new Node(neighbour.val));
            }
        }
        return map.get(node);
    }

        public static void main(String[] args) {
//        Node node4 = new Node();
//        node4.val = 4;
//
//        Node node3 = new Node();
//        node3.val = 3;
//        node3.neighbors.add(node4);

        Node node2 = new Node();
        node2.val = 2;
//        node2.neighbors.add(node4);

        Node node1 = new Node();
        node1.val = 1;
//        node1.neighbors.addAll(Arrays.asList(node2, node3));
        node1.neighbors.addAll(Arrays.asList(node2));


        node2.neighbors.add(node1);
//        node3.neighbors.add(node1);
//        node4.neighbors.add(node2);
//        node4.neighbors.add(node3);
        new CloneGraph().cloneGraph(node1);

    }
}
