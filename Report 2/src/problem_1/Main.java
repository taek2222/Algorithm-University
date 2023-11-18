package problem_1;

public class Main {

    // 무작위 수 생성
    public static void Random_array(int[] array) {
        for(int i = 0; i < array.length; i++)
            array[i] = (int)(Math.random()*100);
    }

    // 합병정렬(분할정복)
    public static void merge_sort_DC(int[] list, int low, int high) {
        int middle;
        if(low < high) {
            middle = (low + high) / 2;
            merge_sort_DC(list, low, middle);
            merge_sort_DC(list, middle+1, high);
            merge(list, low, middle, high);
        }
    }

    private static void merge(int[] list, int low, int mid, int high) {
        int n1 = low, n2 = mid + 1, s = low, i;
        int[] sorted = new int[list.length];
        while (n1 <= mid && n2 <= high) {
            if(list[n1] <= list[n2]) sorted[s++] = list[n1++];
            else sorted[s++] = list[n2++];
        }
        if(n1 > mid) while (n2 <= high) sorted[s++] = list[n2++];
        else while (n1 <= mid) sorted[s++] = list[n1++];
        for(i = low; i <= high; i++) list[i] = sorted[i];
    }

    public static void main(String[] args) {
        int[] Random = new int[100];
        Random_array(Random);

        for(int i = 0; i < Random.length; i++)
            System.out.print(i+ " : " + Random[i] + " ");
        merge_sort_DC(Random, 0, Random.length-1);
        for(int i = 0; i < Random.length; i++)
            System.out.println(i+ " : " + Random[i]);
    }
}


