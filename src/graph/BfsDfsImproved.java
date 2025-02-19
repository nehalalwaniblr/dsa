package graph;

import java.util.*;
import java.util.stream.Collectors;

class Vertex<T> {
    T data;
    List<Vertex<T>> neighbours = new ArrayList<>();
    boolean isVisited;

    Vertex(T data) {
        this.data = data;
    }

}

public class BfsDfsImproved {
    <T> void bfs(Vertex<T> s) {
        Queue<Vertex<T>> queue = new LinkedList<>();
        queue.add(s);
        while (!queue.isEmpty()) {
            Vertex<T> vertex = queue.poll();
            if (!vertex.isVisited) {
                System.out.println(vertex.data);
                vertex.isVisited = true;
                queue.addAll(vertex.neighbours);
            }
        }
    }

    <T> void dfs(Vertex<T> s) {
        Stack<Vertex<T>> stack = new Stack<>();
        stack.push(s);
        while (!stack.isEmpty()) {
            Vertex<T> vertex = stack.pop();
            if (!vertex.isVisited) {
                System.out.println(vertex.data);
                vertex.isVisited = true;
                Collections.reverse(vertex.neighbours);
                vertex.neighbours.forEach(stack::push);
            }
        }
    }

    <T> void dfsRecursive(Vertex<T> vertex) {
        vertex.isVisited=true;
        System.out.println(vertex.data);
        for (Vertex<T> v : vertex.neighbours
             ) {
            if(!v.isVisited)
                dfsRecursive(v);
        }
    }


    public static void main(String[] args) {
        BfsDfsImproved bfsDfsImproved = new BfsDfsImproved();

        Vertex<Integer> v0 = new Vertex<>(0);
        Vertex<Integer> v1 = new Vertex<>(1);
        Vertex<Integer> v2 = new Vertex<>(2);
        Vertex<Integer> v3 = new Vertex<>(3);
        Vertex<Integer> v4 = new Vertex<>(4);
        Vertex<Integer> v5 = new Vertex<>(5);
        Vertex<Integer> v6 = new Vertex<>(6);

        v0.neighbours = Arrays.asList(v1, v5, v6);
        v1.neighbours = Arrays.asList(v3, v4, v5);
        v4.neighbours = Arrays.asList(v2, v6);
        v6.neighbours = Arrays.asList(v0);
        bfsDfsImproved.bfs(v0);

        System.out.println("**********");

        Vertex<Integer> v00 = new Vertex<>(0);
        Vertex<Integer> v10 = new Vertex<>(1);
        Vertex<Integer> v20 = new Vertex<>(2);
        Vertex<Integer> v30 = new Vertex<>(3);
        Vertex<Integer> v40 = new Vertex<>(4);
        Vertex<Integer> v50 = new Vertex<>(5);
        Vertex<Integer> v60 = new Vertex<>(6);

        v00.neighbours = Arrays.asList(v10, v50, v60);
        v10.neighbours = Arrays.asList(v30, v40, v50);
        v40.neighbours = Arrays.asList(v20, v60);
        v60.neighbours = Arrays.asList(v00);
        bfsDfsImproved.dfs(v00);

        System.out.println("**********");

        Vertex<Integer> v01 = new Vertex<>(0);
        Vertex<Integer> v11 = new Vertex<>(1);
        Vertex<Integer> v21 = new Vertex<>(2);
        Vertex<Integer> v31 = new Vertex<>(3);
        Vertex<Integer> v41 = new Vertex<>(4);
        Vertex<Integer> v51 = new Vertex<>(5);
        Vertex<Integer> v61 = new Vertex<>(6);

        v01.neighbours = Arrays.asList(v11, v51, v61);
        v11.neighbours = Arrays.asList(v31, v41, v51);
        v41.neighbours = Arrays.asList(v21, v61);
        v61.neighbours = Arrays.asList(v01);
        bfsDfsImproved.dfs(v01);

    }
}
