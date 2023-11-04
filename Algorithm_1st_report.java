import java.util.*;

public class Algorithm_1st_report {
    private static final int INF = Integer.MAX_VALUE; // INF를 int 최대 값으로 설정하여 무한대를 의미

    static class Node implements Comparable<Node> { // 그래프의 각 정점의 (정점의 번호, 가중치) 정보를 설정
        int vertex; // 정점의 번호
        int key; // 가중치

        // Node 클래스의 생성자
        // 생성자를 통해서 객체 생성 시 정점의 번호와 가중치를 초기화
        public Node(int vertex, int key) {
            this.vertex = vertex;
            this.key = key;
        }

        @Override // Comparable 인터페이스의 compareTo 메소드를 오버라이드
        public int compareTo(Node other) {
            return Integer.compare(this.key, other.key); // 현재 노드의 key와 다른 노드의 key를 비교
            // 반환 값을 현재 객체의 key값이 더 작으면 음수, 같으면 0, 더 크면 양수 반환
        }
    }

    // MinHeap (최소 히프) 클래스 선언
    static class MinHeap {
        Node[] heap; // Node 타입의 배열을 선언
        int size; // 배열 내에 사용 중인 요소의 수 표현하는 변수 선언
        int[] vertexPosition; // 정점 번호의 위치를 저장하는 배열 선언

        public MinHeap(int maxsize) { // 히프의 생성자 [ 생성과 동시에 초기화 진행 ]
            this.size = 0; // 현재 히프의 크기를 0으로 설정
            heap = new Node[maxsize + 1]; // 히프 배열을 초기화 [ 분석에 이해를 돕기 위해 인덱스 1부터 시작해 크기를 더 크게 선언 ]
            vertexPosition = new int[maxsize]; // 정점의 위치를 저장할 배열 초기화
            Arrays.fill(vertexPosition, -1); // 모든 정점의 위치를 -1로 채움으로 초기화
        }

        public void add(Node node) { // 새로운 Node 객체를 히프에 추가하는 메소드
            heap[++size] = node; // size 1증가와 함께 히프의 다음 위치에 노드를 추가
            vertexPosition[node.vertex] = size; // 추가된 노드의 정점 번호에 해당 위치 정보 저장
            decreaseKey(node.vertex, node.key); // 히프의 속성을 유지하기 위해 key 값을 감소
        }

        public void decreaseKey(int vertex, int newKey) { // 히프 내에 있는 특정 노드의 키 값을 감소 [ 히프 속성 유지 ]
            int index = vertexPosition[vertex]; // 정점의 위치를 새로운 index 변수에 저장
            Node node = heap[index]; // 해당 index에 위치한 히프 배열을 추출해 node에 저장
            node.key = newKey; // // 노드의 키 값을 새로운 키 값으로 저장

            while (index > 1 && heap[index / 2].compareTo(node) > 0) { // 노드가 루트가 아니고, 부모 노드의 값이 현재 노드 키 값보다 크면 계속 탐색
                swap(index, index / 2); // 현 노드와 부모 노드 위치 교환
                index = index / 2; // 교환 후 index를 부모 노드의 index로 교환
            }
        }

        public Node delete_min_heap() { // 히프에서 가장 작은 키 값을 가진 노드 제거 후 값 반환
            Node min = heap[1]; // 히프의 첫 번째 원소를 최솟값으로 저장
            Node lastNode = heap[size]; // 히프의 마지막 원소를 저장
            heap[1] = lastNode; // 히프의 마지막 원소를 히프의 첫 번째 위치로 이동
            vertexPosition[lastNode.vertex] = 1; // 이동된 노드의 정점 위치 배열 업데이트
            heap[size--] = null; // 히프의 크기를 줄이고, 마지막 원소의 위치를 NULL 설정으로 삭제
            minHeapify(1); // 히프의 구조를 재조정을 위해 메소드 호출
            return min; // 삭제된 최솟값 반환
        }

        private void minHeapify(int index) { // 히프의 속성을 재구성
            int smallest = index; // 현 index 가 가장 작다고 가정 설정
            int left = 2 * index; // 왼쪽 자식 노드 index 계산
            int right = 2 * index + 1; // 오른쪽 자식 노드 index 계산

            // [왼쪽] 히프 내에 있고, 현 노드보다 작다면 index 수정
            if (left <= size && heap[left].compareTo(heap[smallest]) < 0) {
                smallest = left;
            }

            // [오른쪽] 히프 내에 있고, 현 노드보다 작다면 index 수정
            if (right <= size && heap[right].compareTo(heap[smallest]) < 0) {
                smallest = right;
            }

            // 가장 작은 값이 현 index 값이 아니라면 위치 바꾸기
            if (smallest != index) {
                swap(index, smallest); // 노드 위치 바꾸기
                minHeapify(smallest); // 히프의 속성 유지를 위해 변경된 트리에서도 재호출
            }
        }

        private void swap(int i, int j) { // 히프 배열에서 두 개 노드 위치 변경
            Node temp = heap[i]; // 히프 노드 변경 구간
            heap[i] = heap[j];
            heap[j] = temp;

            vertexPosition[heap[i].vertex] = i; // 위치가 바뀐 노드도 vertexPosition 배열 업데이트
            vertexPosition[heap[j].vertex] = j;
        }
    }

    public void primMST(int[][] graph, int n) { // 인접 행렬 표현으로 생성된 이차원 배열 graph, 그래프의 정점 수 n
        int[] distance = new int[n]; // 각 정점까지의 최소 가중치 저장
        int[] nearest = new int[n]; // 최소 신장 트리에 가장 가까운 정점 저장
        boolean[] MST_V = new boolean[n]; // 이미 포함된 정점인지 여부를 표시하는 배열
        MinHeap heap = new MinHeap(n); // 정점들 관리하기 위한 최소 히프 생성

        for (int i = 0; i < n; i++) {
            distance[i] = INF; // 무한대로 초기화
            nearest[i] = -1; // -1(없음)으로 초기화
            MST_V[i] = false; // 모든 정점이 아직 포함되지 않음을 표시를 위한 초기화
            heap.add(new Node(i, distance[i])); // 모든 정점을 히프에 추가.
        }

        distance[0] = 0; // 최소 신장 트리의 프림 알고리즘 첫 시작점 0 설정
        heap.decreaseKey(0, distance[0]);

        for (int i = 0; i < n - 1; i++) { // n-1개의 간선이 추가될 때까지 반복
            Node u = heap.delete_min_heap(); // 히프에서 최소 가중치 노드를 삭제함과 동시에 반한해서 저장
            MST_V[u.vertex] = true;  // 추출한 최소 가중치 노드는 이제 입력 되므로 정점에 등록을 알림

            // for each (u에 인접한 정점 w)에 해당하는 부분
            for (int w = 0; w < n; w++) {
                if (graph[u.vertex][w] != 0 && !MST_V[w]) { // MST_V에 ture(등록)된 정점이 아니며 인접 행렬에서 0이 아닌 경우
                    if (graph[u.vertex][w] < distance[w]) { // 만약 간선의 가중치가 현재 w까지의 거리보다 작으면
                        heap.decreaseKey(w, graph[u.vertex][w]); // 히프에서 w 정점 거리를 기반으로 호출
                        nearest[w] = u.vertex; // nearest 배열 업데이트
                        distance[w] = graph[u.vertex][w]; // distance 배열 업데이트
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
        A_1_r.primMST(graph, graph.length); // 생성된 인스턴스를 통해 'primMST' 메소드 호출로 최소 히프를 사용한 프림 알고리즘 실행
    }
}
