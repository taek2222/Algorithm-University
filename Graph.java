import java.util.*;

public class Graph {
    public static class list {
        int id;
        int id_weight;

        list(int id, int id_weight) {
            this.id = id;
            this.id_weight = id_weight;
        }
    }

    private Map<Integer, List<list>> vertex_list;

    public Graph() {
        this.vertex_list = new HashMap<>();
    }

    public void list_add(int num, int id, int id_weight) {
        vertex_list.putIfAbsent(num, new ArrayList<>());
        vertex_list.get(num).add(new list(id, id_weight));
    }

    public void printGraph() {
        for (Map.Entry<Integer, List<list>> entry : vertex_list.entrySet()) {
            System.out.print(entry.getKey() + ": ");
            for (list v : entry.getValue()) {
                System.out.print("(" + v.id + ", " + v.id_weight + ") ");
            }
            System.out.println();
        }
    }

}