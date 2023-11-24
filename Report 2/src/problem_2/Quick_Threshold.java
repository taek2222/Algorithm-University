package problem_2;

import java.util.*;

public class Quick_Threshold {
    public static void Random_array(int[] array) {
        for(int i = 0; i < array.length; i++)
            array[i] = (int)(Math.random()*100); // Math 메소드를 활용해 100 미만 랜덤 수 생성.
    }

    //퀵 정렬(분할 정복) 분할
    public static void quicksort_DC(int[] list, int low, int high, int threshold) {
        if (low < high) {
            if (high - low < threshold) {
                // 임계값 이하일 경우 삽입 정렬 사용
                insertionSort(list, low, high);
            } else {
                // 임계값 이상일 경우 퀵 정렬 계속 사용
                int pivot_pos = partition(list, low, high);
                quicksort_DC(list, low, pivot_pos - 1, threshold);
                quicksort_DC(list, pivot_pos + 1, high, threshold);
            }
        }
    }

    // 퀵 정렬(분할 정복)
    public static int partition(int[] list, int low, int high) {
        int pivot = list[low]; // 피벗을 배열의 첫 번째 요소로 선택
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

    // 삽입 정렬
    public static void insertionSort(int[] list, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            int next = list[i]; // 삽입할 요소
            int j;
            // 왼쪽으로 이동하며 삽입할 위치 찾
            for (j = i - 1; j >= low && list[j] > next; j--)
                list[j + 1] = list[j];
            // 삽입 위치에 요소 삽입
            list[j + 1] = next;
        }
    }

    public static void main(String[] args) {
        int Data_Size = 1000000; // n 데이터 사이즈
        int[][] Data_Array = new int[10][Data_Size]; // 10개 배열의 각각 무작위 1000000개의 수 저장 배열
        List<Map.Entry<Integer, Double>> averageTimesList = new ArrayList<>();

        for(int i = 0; i < 10; i++) // 10개 배열 무작위 수 생성
            Random_array(Data_Array[i]);

        System.out.println("\n*** 총 데이터 수 "+ Data_Size +"개의 임계값의 따른 퀵정렬 처리 속도 비교 *** ");

        // 임계값에 따른 퀵정렬 시간 측정 반복문
        for(int Threshold = 0; Threshold <= Data_Size; Threshold = (Threshold == 0 ? 1 : Threshold * 10)) {
            System.out.println("\n** 랜덤 배열 각각의 실행 시간 비교 및 평균 시간 [임계값 : "+Threshold+"의 퀵정렬] **\n");
            double Time_Total = 0; // 총 시간 초기화

            for(int i = 0; i < 10; i++) {
                int[] Quick_Threshold = Data_Array[i].clone(); // 기존 데이터 보존을 위한 복사
                long Quick_Threshold_beforeTime = System.currentTimeMillis(); // 일반 퀵정렬 시간 측정 시작

                quicksort_DC(Quick_Threshold, 0, Data_Size-1, Threshold); // 퀵 정렬 실행 (임계값 1 ~ n 까지)

                long Quick_Threshold_afterTime = System.currentTimeMillis(); // 시간 측정 완료
                long Quick_Threshold_result = Quick_Threshold_afterTime - Quick_Threshold_beforeTime; // 최종 시간 계산

                System.out.println( (i+1) + "번째 데이터 실행 시간 : " + Quick_Threshold_result); // 각각의 실행 시간 출력
                Time_Total += Quick_Threshold_result; // 총 실행 시간 합산
            }

            double averageTime = Time_Total / 10;
            averageTimesList.add(new AbstractMap.SimpleEntry<>(Threshold, averageTime)); // 평균 시간을 리스트에 저장
            System.out.println("[임계값 : "+Threshold+"의 퀵정렬]의 평균 실행 시간 : " + averageTime +" ms"); // 평균 실행 시간 계산
        }

        // 평균 실행 시간을 기준으로 리스트 오름차순 정렬 및 출력
        averageTimesList.sort(Map.Entry.comparingByValue());

        System.out.println("\n평균 실행 시간에 따른 임계값 (오름차순):");
        for(Map.Entry<Integer, Double> entry : averageTimesList) {
            System.out.println("임계값: " + entry.getKey() + ", 평균 실행 시간: " + entry.getValue() + " ms");
        }
    }
}
