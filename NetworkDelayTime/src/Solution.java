import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yihuan on 2018/4/23.
 */
public class Solution {
    private Graph graph = new Graph();
    private Map<Integer , Integer> S = new HashMap<>();
    private Map<Integer , Integer> U = new HashMap<>();

    private class Graph{
        int[] node;
        int node_num;
        int edge_num;
        int[][] adjacent_matrix;
    }

    public int networkDelayTime(int[][] times, int N, int K) {
        //初始化集合

        S.put(K , 0);
        for(int i = 1 ; i <= N ; i++) {
            if(i == K)
                continue;
            U.put(i , Integer.MAX_VALUE);
        }

        //储存图信息
        graph.adjacent_matrix = new int[N][N];
        graph.node_num = N;
        graph.edge_num = times.length;

        for(int i = 0 ; i < graph.adjacent_matrix.length ; i++)
            for(int j = 0 ; j < graph.adjacent_matrix[i].length ; j++)
                graph.adjacent_matrix[i][j] = -1;

        for(int i = 0 ; i < graph.edge_num ; i++)
            graph.adjacent_matrix[times[i][0]-1][times[i][1]-1] = times[i][2];

        update(K);

        while(S.size() != N){
            int node = FindMin();
            if(node == 0)
                return -1;
            S.put(node , U.get(node));
            U.remove(node);
            if(S.size() == N)
                break;
            update(node);
        }

        int max = 0;
        for(int key : S.keySet())
            if(S.get(key) > max)
                max = S.get(key);

        return max;
    }

    private void update(int node){
        for(int i = 0 ; i < graph.node_num ; i++){
            if(!U.containsKey(i+1))
                continue;
            int weight = graph.adjacent_matrix[node-1][i];
            if(weight == -1)
                continue;
            if((weight + S.get(node)) < U.get(i+1))
                U.put(i+1,weight + S.get(node));
        }
    }

    private int FindMin(){
        int min = Integer.MAX_VALUE;
        int retNode = 0;
        for(int key : U.keySet()){
            if(U.get(key) < min){
                min = U.get(key);
                retNode = key;
            }
        }
        return retNode;
    }

    public static void main(String[] args){
        int[][] input = {{3,5,78},{2,1,1},{1,3,0},{4,3,59},{5,3,85},{5,2,22},{2,4,23},{1,4,43},{4,5,75},{5,1,15},{1,5,91},{4,1,16},{3,2,98},{3,4,22},{5,4,31},{1,2,0},{2,5,4},{4,2,51},{3,1,36},{2,3,59}};
        Solution solution = new Solution();
        int result = solution.networkDelayTime(input,5,5);
        System.out.println(result);
    }
}
