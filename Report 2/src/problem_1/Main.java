package problem_1;

public class Main {

    // 무작위 수 생성
    public static void Random_array(int[] array) {
        for(int i = 0; i < array.length; i++)
            array[i] = (int)(Math.random()*100); // Math 메소드를 활용해 100 미만 랜덤 수 생성.
    }

    // 합병정렬(분할정복) 분할
    public static void merge_sort_DC(int[] list, int low, int high) {
        int middle;
        if(low < high) { // 재귀적으로 계속 호출하여 분할 과정 진행
            middle = (low + high) / 2;
            merge_sort_DC(list, low, middle); // 최소 ~ 중간 값
            merge_sort_DC(list, middle+1, high); // 중간 값 ~ 최대 값
            merge(list, low, middle, high); // 병항 과정
        }
    }

    // 합병정렬(분할정복) 병합
    private static void merge(int[] list, int low, int mid, int high) {
        int n1 = low, n2 = mid + 1, s = low, i; // 첫 번쩨, 두 번째 부분 배열, 저장할 인덱스 배열 번호
        int[] sorted = new int[list.length]; // 정렬된 내용 임시 배열 선언
        while (n1 <= mid && n2 <= high) { // 비교 시작 과정
            if(list[n1] <= list[n2]) sorted[s++] = list[n1++]; // 더 작은 요소를 sorted 배열에 추가
            else sorted[s++] = list[n2++];
        }
        if(n1 > mid) while (n2 <= high) sorted[s++] = list[n2++]; // 남은 부분 처리 과정
        else while (n1 <= mid) sorted[s++] = list[n1++];
        for(i = low; i <= high; i++) list[i] = sorted[i]; // 임시 배열 내용을 원래 배열에 저장
    }

    public static void main(String[] args) {
        int[] Random = new int[10000]; // 데이터 배열
        Random_array(Random); // 랜덤 수 선정
        int[] Merge = Random.clone(); // 합병정렬 배열 선언 (랜덤 수 배열 복사)
        int[] Quick = Random.clone(); // 퀵정렬 배열 선언 (랜덤 수 배열 복사)

        merge_sort_DC(Merge, 0, Random.length-1);
        for(int i = 0; i < Random.length; i++)
            System.out.println(i+ " : " + Random[i]);
    }
}


