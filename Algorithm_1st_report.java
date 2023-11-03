import java.util.*;

public class Algorithm_1st_report {
    private static final int INF = Integer.MAX_VALUE; // INF

    static class Node implements Comparable<Node> { // 그래프의 각 정점의 (정점의 번호, 가중치) 정보를 설정.
        int vertex; // 정점의 번호
        int key; // 가중치

        public Node(int vertex, int key) {
            this.vertex = vertex;
            this.key = key;
        }

        @Override // Comparable 인터페이스의 compareTo 메소드를 오버라이드
        public int compareTo(Node other) {
            return Integer.compare(this.key, other.key); // 현재 노드의 key와 다른 노드의 key를 비교
        }
    }

    static class MinHeap {
        Node[] heap;
        int size; // 배열 내에 사용 중인 요소의 수 표현
        int[] vertexPosition;

        public MinHeap(int maxsize) { // 히프의 생성자 [ 생성과 동시에 초기화 진행 ]
            this.size = 0; // 크기를 0으로 설정
            heap = new Node[maxsize + 1]; //
            vertexPosition = new int[maxsize];
            Arrays.fill(vertexPosition, -1);
        }

        public void add(Node node) {
            heap[++size] = node;
            vertexPosition[node.vertex] = size;
            decreaseKey(node.vertex, node.key);
        }

        public void decreaseKey(int vertex, int newKey) {
            int index = vertexPosition[vertex];
            Node node = heap[index];
            node.key = newKey;

            while (index > 1 && heap[index / 2].compareTo(node) > 0) {
                swap(index, index / 2);
                index = index / 2;
            }
        }

        public Node delete_min_heap() {
            Node min = heap[1];
            Node lastNode = heap[size];
            heap[1] = lastNode;
            vertexPosition[lastNode.vertex] = 1;
            heap[size--] = null;
            minHeapify(1);
            return min;
        }

        private void minHeapify(int index) {
            int smallest = index;
            int left = 2 * index;
            int right = 2 * index + 1;

            if (left <= size && heap[left].compareTo(heap[smallest]) < 0) {
                smallest = left;
            }

            if (right <= size && heap[right].compareTo(heap[smallest]) < 0) {
                smallest = right;
            }

            if (smallest != index) {
                swap(index, smallest);
                minHeapify(smallest);
            }
        }

        private void swap(int i, int j) {
            Node temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;

            vertexPosition[heap[i].vertex] = i;
            vertexPosition[heap[j].vertex] = j;
        }
    }

    public void primMST(int[][] graph, int n) {
        int[] distance = new int[n];
        int[] nearest = new int[n];
        boolean[] MST_V = new boolean[n];
        MinHeap heap = new MinHeap(n);

        for (int i = 0; i < n; i++) {
            distance[i] = INF;
            nearest[i] = -1;
            MST_V[i] = false;
            heap.add(new Node(i, distance[i]));
        }

        distance[0] = 0;
        heap.decreaseKey(0, distance[0]);

        for (int i = 0; i < n - 1; i++) { // loop(n-2)에 해당하는 부분
            Node u = heap.delete_min_heap();
            MST_V[u.vertex] = true;

            // for each (u에 인접한 정점 w)에 해당하는 부분
            for (int w = 0; w < n; w++) {
                if (graph[u.vertex][w] != 0 && !MST_V[w]) {
                    if (graph[u.vertex][w] < distance[w]) {
                        heap.decreaseKey(w, graph[u.vertex][w]);
                        nearest[w] = u.vertex;
                        distance[w] = graph[u.vertex][w];
                    }
                }
            }
        }

        // Print the constructed MST
        printMST(nearest, graph, n);
    }

    private void printMST(int[] parent, int[][] graph, int n) {
        int totalWeight = 0;
        System.out.println("Edge \tWeight");
        for (int i = 1; i < n; i++) {
            System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
            totalWeight += graph[i][parent[i]];
        }
        System.out.println("Total weight of MST: " + totalWeight);
    }

    public static void main(String[] args) {
        // input_graph 파일의 그래프를 인접행렬로 표현.
        int[][] graph = {
                {0, 4, 0, 0, 0, 0, 0, 8, 0}, // [0]의 정점과 정점 사이의 가중치
                {4, 0, 8, 0, 0, 0, 0, 11, 0}, // [1]
                {0, 8, 0, 7, 0, 4, 0, 0, 2}, // [2]
                {0, 0, 7, 0, 9, 14, 0, 0, 0}, // [3]
                {0, 0, 0, 9, 0, 10, 0, 0, 0}, // [4]
                {0, 0, 4, 14, 10, 0, 2, 0, 0},// [5]
                {0, 0, 0, 0, 0, 2, 0, 1, 6},// [6]
                {8, 11, 0, 0, 0, 0, 1, 0, 7},// [7]
                {0, 0, 2, 0, 0, 0, 6, 7, 0}// [8]
        };

        Algorithm_1st_report A_1_r = new Algorithm_1st_report(); // 클래스의 인스턴스 A_i_r 생성
        A_1_r.primMST(graph, graph.length); // 생성된 인스턴스를 통해 'primMST' 메소드 호출로 최소 히프를 사용한 프림 알고리즘 실행.
    }
}
