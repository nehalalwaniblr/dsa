package leet_again.graphs;

import java.util.*;

/*
*Consider this given a/b=2 ; b/c = 3
* so a---2-->b--3-->c
* i.e. a to b has weight 2 and bto c has weight 3; and a/c is 6 so a--->c = 6
* so src=a and destination =c so seems like a graph question
*
* maintain a map storing a->b and also b->a  if a/b is 2 so b/a will be 1/2
* hence graph will contain a-->(b,2) and b-->(a,1/2)
* */
public class EvaluateDivision {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        double[] result = new double[queries.size()];
        Map<String,Map<String,Double>> adjMap = new HashMap<>();
        //construct a map
        for(int i=0;i<equations.size();i++){
            if(adjMap.containsKey(equations.get(i).getFirst())){
                Map<String,Double> map = adjMap.get(equations.get(i).getFirst());
                map.put(equations.get(i).getLast(), values[i]);
            }else{
                Map<String,Double> map = new HashMap<>();
                map.put(equations.get(i).getLast(),values[i]);
                adjMap.put(equations.get(i).getFirst(), map);
            }

            //reverse mapping also we need to put
            if(adjMap.containsKey(equations.get(i).getLast())){
                Map<String,Double> map = adjMap.get(equations.get(i).getLast());
                map.put(equations.get(i).getFirst(), 1.0/values[i]);
            }else{
                Map<String,Double> map = new HashMap<>();
                map.put(equations.get(i).getFirst(),1.0/values[i]);
                adjMap.put(equations.get(i).getLast(), map);
            }
        }

        for (int i=0;i<queries.size();i++){
            double product = 1.0;
            double res = -1.0;
            Set<String> visited = new HashSet<>(); //new set for every query
            String u = queries.get(i).getFirst();
            String v = queries.get(i).getLast();
            if(adjMap.containsKey(u)){
                //source node u exist in map; check if destination exist apply dfs
                res =dfs(adjMap, u, v, product, res, visited);
            }
            result[i] = res;
        }
        return result;
    }

    private double dfs(Map<String, Map<String, Double>> adjMap, String u, String v, double product, double res, Set<String> visited) {
        if(visited.contains(u))
            return res;
       visited.add(u);
       if(Objects.equals(u, v)){
           res = product;
           return res;
       }
       for (Map.Entry<String, Double> doubleMap: adjMap.get(u).entrySet()){
           String node = doubleMap.getKey();
           Double value = doubleMap.getValue();
           double result =  dfs(adjMap, node, v, product*value , res, visited);
           if (result != -1.0)
               return result;

       }
       return -1.0;
    }

    public static void main(String[] args) {
        List<List<String>> equations = Arrays.asList(
                Arrays.asList("a", "b"),
                Arrays.asList("b", "c")
        );

        double[] values = {2.0, 3.0};

        List<List<String>> queries = Arrays.asList(
                Arrays.asList("a", "c"),
                Arrays.asList("b", "a"),
                Arrays.asList("a", "e"),
                Arrays.asList("a", "a"),
                Arrays.asList("x", "x")
        );
        System.out.println(Arrays.toString(new EvaluateDivision().calcEquation(equations,values,queries)));

        List<List<String>> equations2 = Arrays.asList(
                Arrays.asList("a", "b"),
                Arrays.asList("b", "c"),
                Arrays.asList("bc", "cd")
        );

        double[] values2 = {1.5, 2.5, 5.0};

        List<List<String>> queries2 = Arrays.asList(
                Arrays.asList("a", "c"),
                Arrays.asList("c", "b"),
                Arrays.asList("bc", "cd"),
                Arrays.asList("cd", "bc")
        );

        System.out.println(Arrays.toString(new EvaluateDivision().calcEquation(equations2,values2,queries2)));

        List<List<String>> equations3 = Arrays.asList(
                Arrays.asList("a", "b")
        );

        double[] values3 = {0.5};

        List<List<String>> queries3 = Arrays.asList(
                Arrays.asList("a", "b"),
                Arrays.asList("b", "a"),
                Arrays.asList("a", "c"),
                Arrays.asList("x", "y")
        );

        System.out.println(Arrays.toString(new EvaluateDivision().calcEquation(equations3,values3,queries3)));


    }

}

record Pair3(String x, Double y) {
}