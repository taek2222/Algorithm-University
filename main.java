import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException {
    }

    public void prim_MST_2(int graph[][MAX], int n, int s) {
        boolean MST_V[ MAX];
        int ditance[MAX],nearest[MAX], i, u, w;
        for (i = 0; i < n; i++) {
            MST_V[i] = false;
            distance[i] = graph[s][i];
            nearest[i] = s;
        }

        while (n - 2) {
            u = delete_min_heap(heap); // distance가 최소인 정점 삭제
            MST_V[u] = true;
            for each(u에 인접한 정점 w)
            if (MST_V[w] == false)
                if (weight(u, w) < distance[w]) {
                        decrease_key(heap, distance, w);
                        nearest[w] = u;
                }
        }
    }
}
