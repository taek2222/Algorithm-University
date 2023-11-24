package problem_1;

public class Merge_Quick {

    // 무작위 수 생성
    public static void Random_array(int[] array) {
        for(int i = 0; i < array.length; i++)
            array[i] = (int)(Math.random()*100); // Math 메소드를 활용해 100 미만 랜덤 수 생성.
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
    public static int partition(int[] list, int low, int high) {
        int pivot = list[low]; // 피벗을 첫 번째 요소로 선택
        int i, j = low;
        for(i = low+1; i <= high; i++) // 피벗보다 작은 요소 왼쪽, 큰 요소 오른쪽 이동
            if(list[i] < pivot) {
                j++;
                int temp = list[i];
                list[i] = list[j];
                list[j] = temp;
            }
        // 피벗을 올바른 위치로 이동
        list[low] = list[j];
        list[j] = pivot;
        return j; // 피벗 최종 위치 반환
    }


    public static void main(String[] args) {
        int Data_Size = 100000; // n 데이터 사이즈
        int[][] Data_Array = new int[10][Data_Size]; // 10개 배열의 각각 무작위 1000000개의 수 저장 배열

        for(int i = 0; i < 10; i++) // 10개 배열 무작위 수 생성
            Random_array(Data_Array[i]);

        System.out.println("\n*** 총 데이터 수 "+ Data_Size +"개의 합병정렬, 퀵정렬 처리 속도 비교 *** ");

        // 합병 정렬 시간 측정 및 시작
        System.out.println("\n** 랜덤 배열 각각의 실행 시간 비교 및 평균 시간 [합병 정렬] **\n");
        double Merge_Time_Total = 0; // 합병 정렬 총 걸린 시간
        for(int i = 0; i < 10; i++) {
            int[] Merge_Array = Data_Array[i].clone(); // 기존 데이터 보존을 위한 복사
            long Merge_beforeTime = System.currentTimeMillis(); // 시간 측정 시작

            merge_sort_DC(Merge_Array, 0, Merge_Array.length-1); // 합병 정렬 시작

            long Merge_afterTime = System.currentTimeMillis(); // 시간 측정 완료
            long Merge_result = Merge_afterTime - Merge_beforeTime; // 최종 시간 계산

            System.out.println( (i+1) + "번째 데이터 실행 시간 : " + Merge_result); // 각각의 실행 시간 출력
            Merge_Time_Total += Merge_result; // 실행 시간 합산
        }


        // 퀵정렬 시간 측정 및 시작
        System.out.println("\n** 랜덤 배열 각각의 실행 시간 비교 및 평균 시간 [퀵 정렬] **\n");
        double Quick_Time_Total = 0; // 퀵 정렬 총 걸린 시간
        for(int i = 0; i < 10; i++) {
            int[] Quick_Array = Data_Array[i].clone(); // 기존 데이터 보존을 위한 복사
            long Quick_beforeTime = System.currentTimeMillis(); // 시간 측정 시작

            quicksort_DC(Quick_Array, 0, Quick_Array.length-1); // 퀵 정렬 시작

            long Quick_afterTime = System.currentTimeMillis(); // 시간 측정 완료
            long Quick_result = Quick_afterTime - Quick_beforeTime; // 최종 시간 계산

            System.out.println( (i+1) + "번째 데이터 실행 시간 : " + Quick_result); // 각각의 실행 시간 출력
            Quick_Time_Total += Quick_result; // 실행 시간 합산
        }

        System.out.println("\n[합병 정렬] 평균 실행 시간 : " + Merge_Time_Total/10 + " ms"); // 합병 정렬 총 시간 출력
        System.out.println("[퀵 정렬] 평균 실행 시간 : " + Quick_Time_Total/10 + " ms"); // 퀵 정렬 총 시간 출력
    }
}