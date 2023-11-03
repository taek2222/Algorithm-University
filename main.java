import java.io.IOException;
import java.util.HashMap;
import java.util.*;


public class main {
    public static void main(String[] args) throws IOException {
        Graph graph = new Graph();
        // 정점 0의 연결 정보 리스트 등록
        graph.list_add(0, 1, 4);
        graph.list_add(0, 7, 8);
        // 정점 1의 연결 정보 리스트 등록
        graph.list_add(1, 0, 4);
        graph.list_add(1, 2, 8);
        graph.list_add(1, 7, 11);
        // 정점 2의 연결 정보 리스트 등록
        graph.list_add(2, 1, 8);
        graph.list_add(2, 3, 7);
        graph.list_add(2, 5, 4);
        graph.list_add(2, 8, 2);
        // 정점 3의 연결 정보 리스트 등록
        graph.list_add(3, 2, 7);
        graph.list_add(3, 4, 9);
        graph.list_add(3, 5, 14);
        // 정점 4의 연결 정보 리스트 등록
        graph.list_add(4, 3, 9);
        graph.list_add(4, 5, 10);
        // 정점 5의 연결 정보 리스트 등록
        graph.list_add(5, 2, 4);
        graph.list_add(5, 3, 14);
        graph.list_add(5, 4, 10);
        graph.list_add(5, 6, 2);
        // 정점 6의 연결 정보 리스트 등록
        graph.list_add(6, 5, 2);
        graph.list_add(6, 7, 1);
        graph.list_add(6, 8, 6);
        // 정점 7의 연결 정보 리스트 등록
        graph.list_add(7, 0, 8);
        graph.list_add(7, 1, 11);
        graph.list_add(7, 6, 1);
        graph.list_add(7, 8, 7);
        // 그래프 출력
        //graph.printGraph();


    }
}

//    public void prim_MST_2(int graph[][MAX], int n, int s) {
//        boolean MST_V[MAX];
//        int distance[MAX], nearest[MAX], i, u, w;
//        for (i = 0; i < n; i++) {
//            MST_V[i] = false;
//            distance[i] = graph[s][i];
//            nearest[i] = s;
//        }
//
//        loop (n - 2) {
//            u = delete_min_heap(heap); // distance가 최소인 정점 삭제
//            MST_V[u] = true;
//            for each(u에 인접한 정점 w)
//            if (MST_V[w] == false)
//                if (weight(u, w) < distance[w]) {
//                        decrease_key(heap, distance, w);
//                        nearest[w] = u;
//                }
//        }
//    }
