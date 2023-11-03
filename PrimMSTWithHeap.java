import java.util.*;

public class PrimMSTWithHeap {
    private static final int INF = Integer.MAX_VALUE;

    class Node implements Comparable<Node> {
        int vertex;
        int distance;

        public Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    public void primMST(int graph[][], int n) {
        PriorityQueue<Node> minHeap = new PriorityQueue<>();
        int[] distance = new int[n];
        int[] parent = new int[n];
        boolean[] MST_V = new boolean[n];

        Arrays.fill(distance, INF);
        distance[0] = 0;
        parent[0] = -1;  // 시작 정점의 부모는 없음을 표시

        for (int i = 0; i < n; i++) {
            minHeap.add(new Node(i, distance[i]));
        }

        while (!minHeap.isEmpty()) {
            Node current = minHeap.poll();
            int u = current.vertex;

            if (MST_V[u]) continue;  // 이미 MST에 포함된 정점은 스킵
            MST_V[u] = true;

            if (parent[u] != -1) {  // 시작 정점은 제외
                System.out.println("Edge: (" + parent[u] + ", " + u + ") with weight: " + graph[parent[u]][u]);
            }

            for (int v = 0; v < n; v++) {
                if (graph[u][v] != 0 && !MST_V[v] && graph[u][v] < distance[v]) {
                    distance[v] = graph[u][v];
                    parent[v] = u;
                    minHeap.offer(new Node(v, distance[v]));
                }
            }
        }

        // ... (primMST 함수의 나머지 부분)

        int totalWeight = 0;
        for (int i = 1; i < n; i++) {
            totalWeight += graph[i][parent[i]];
        }

        System.out.println("Total weight of MST: " + totalWeight);

    }

    public static void main(String[] args) {
        // 그래프를 인접 행렬로 표현
        int graph[][] = {
                {0, 4, 0, 0, 0, 0, 0, 8, 0}, // 0
                {4, 0, 8, 0, 0, 0, 0, 11, 0}, // 1
                {0, 8, 0, 7, 0, 4, 0, 0, 2}, // 2
                {0, 0, 7, 0, 9, 14, 0, 0, 0}, // 3
                {0, 0, 0, 9, 0, 10, 0, 0, 0}, // 4
                {0, 0, 4, 14, 10, 0, 2, 0, 0},// 5
                {0, 0, 0, 0, 0, 2, 0, 1, 6},// 6
                {8, 11, 0, 0, 0, 0, 1, 0, 7},// 7
                {0, 0, 2, 0, 0, 0, 6, 7, 0}// 8
        };

        PrimMSTWithHeap mst = new PrimMSTWithHeap();
        mst.primMST(graph, graph.length);

    }
}
