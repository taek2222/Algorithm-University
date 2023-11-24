package problem_1;

public class Merge_Quick {

    // 무작위 수 생성
    public static void Random_array(int[] array) {
        for(int i = 0; i < array.length; i++)
            array[i] = (int)(Math.random()*100); // Math 메소드를 활용해 100 미만 랜덤 수 생성.
    }

    // 정렬 전, 후 출력
    public static void printf(int[] array, int[] sort_array, String str) {
        System.out.println("[" + str + " 정렬 전] 가장 앞 5개 배열");
        for(int i = 0; i < 5; i++)
            System.out.println((i + 1) + " 번째 숫자 : [" + array[i] + "] ");

        System.out.println();

        System.out.println("[" + str + " 정렬 전] 가장 뒤 5개 배열");
        for(int i = array.length - 1; i > array.length-6; i--)
            System.out.println((i + 1) + " 번째 숫자 : [" + array[i] + "] ");

        System.out.println();

        System.out.println("[" + str + " 정렬 후] 가장 앞 5개 배열");
        for(int i = 0; i < 5; i++)
            System.out.println((i + 1) + " 번째 숫자 : [" + sort_array[i] + "] ");

        System.out.println();

        System.out.println("[" + str + " 정렬 후] 가장 뒤 5개 배열");
        for(int i = array.length - 1; i > array.length-6; i--)
            System.out.println((i + 1) + " 번째 숫자 : [" + sort_array[i] + "] ");

        System.out.println();
    }

    // 합병 정렬(분할 정복) 분할
    public static void merge_sort_DC(int[] list, int low, int high) {
        int middle;
        if(low < high) { // 재귀적으로 계속 호출하여 분할 과정 진행
            middle = (low + high) / 2;
            merge_sort_DC(list, low, middle); // 최소 ~ 중간 값
            merge_sort_DC(list, middle+1, high); // 중간 값 ~ 최대 값
            merge(list, low, middle, high); // 병항 과정
        }
    }

    // 합병 정렬(분할 정복) 병합
    private static void merge(int[] list, int low, int mid, int high) {
        int n1 = low, n2 = mid + 1, s = low, i; // 첫 번쩨, 두 번째 부분 배열, 저장할 인덱스 배열 번호
        int[] sorted = new int[list.length]; // 정렬된 내용 임시 배열 선언
        while (n1 <= mid && n2 <= high) { // 비교 시작 과정
            if(list[n1] <= list[n2]) sorted[s++] = list[n1++]; // 더 작은 요소를 sorted 배열에 추가
            else sorted[s++] = list[n2++];
        }
        if(n1 > mid)
            while (n2 <= high) sorted[s++] = list[n2++]; // 남은 부분 처리 과정
        else
            while (n1 <= mid) sorted[s++] = list[n1++];
        for(i = low; i <= high; i++) list[i] = sorted[i]; // 임시 배열 내용을 원래 배열에 저장
    }

    //퀵 정렬(분할 정복) 분할
    public static void quicksort_DC(int[] list, int low, int high) {
        int pivot_pos;
        if(low < high){ // 재귀적으로 계속 호출하여 분할 과정 진행
            pivot_pos = partition(list, low, high); // 피벗 고르기
            quicksort_DC(list, low, pivot_pos-1); // 피벗을 기반 나누기
            quicksort_DC(list, pivot_pos+1, high); // 피벗을 기반 나누기
        }
    }
    // 퀵 정렬(분할 정복) 교환
    private static int partition(int[] list, int low, int high) {
        int pivot = list[low];
        int i, j = low;
        for(i = low+1; i <= high; i++)
            if(list[i] < pivot) {
                j++;
                int temp = list[i];
                list[i] = list[j];
                list[j] = temp;
            }
        list[low] = list[j];
        list[j] = pivot;
        return j;
    }


    public static void main(String[] args) {
        int[] Random = new int[100000]; // 데이터 배열
        Random_array(Random); // 랜덤 수 선정
        int[] Merge = Random.clone(); // 합병 정렬 배열 선언 (랜덤 수 배열 복사)
        int[] Quick = Random.clone(); // 퀵정렬 배열 선언 (랜덤 수 배열 복사)


        // 합병 정렬 시간 측정 및 시작
        long Merge_beforeTime = System.currentTimeMillis(); // 시간 측정 시작

        merge_sort_DC(Merge, 0, Merge.length-1); // 합병 정렬 START
        printf(Random, Merge, "합병");


        long Merge_afterTime = System.currentTimeMillis(); // 시간 측정 완료
        long Merge_result = Merge_afterTime - Merge_beforeTime; // 최종 시간 계산

        //퀵정렬 시간 측정 및 시작
        long Quick_beforeTime = System.currentTimeMillis(); // 시간 측정 시작

        quicksort_DC(Quick, 0, Quick.length-1);
        printf(Random, Quick, "퀵");

        long Quick_afterTime = System.currentTimeMillis(); // 시간 측정 완료
        long Quick_result = Quick_afterTime - Quick_beforeTime; // 최종 시간 계산
        System.out.println("합병 정렬 실행 시간(ms) : " + Merge_result);
        System.out.println("퀵 정렬 실행 시간(ms) : " + Quick_result);

    }
}


