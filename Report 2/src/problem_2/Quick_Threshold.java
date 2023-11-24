package problem_2;

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

    // 삽입 정렬
    public static void insertionSort(int[] list, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            int next = list[i];
            int j;
            for (j = i - 1; j >= low && list[j] > next; j--)
                list[j + 1] = list[j];
            list[j + 1] = next;
        }
    }

    // 임계값에 따른 퀵정렬


    public static void main(String[] args) {
        int Data_Size = 1000000; // n 데이터 사이즈
        int[][] Data_Array = new int[10][Data_Size]; // 10개 배열의 각각 무작위 1000000개의 수 저장 배열
        double Time_Total;

        for(int i = 0; i < 10; i++) // 10개 배열 무작위 수 생성
            Random_array(Data_Array[i]);

        System.out.println("\n*** 총 데이터 수 "+ Data_Size +"개의 임계값의 따른 퀵정렬 처리 속도 비교 *** \n");

        // 퀵정렬 시간 측정 및 시작
        System.out.println("** 랜덤 배열 각각의 실행 시간 비교 및 평균 시간 [일반 퀵정렬] **");
        Time_Total = 0; // 총 시간 초기화
        for(int i = 0; i < 10; i++) {
            int[] Quick_Threshold = Data_Array[i].clone(); // 기존 데이터 보존을 위한 복사
            long Quick_Threshold_beforeTime = System.currentTimeMillis(); // 일반 퀵정렬 시간 측정 시작

            quicksort_DC(Quick_Threshold, 0, Data_Size-1, 0); // 퀵 정렬 실행 (임계값 0 = 퀵정렬)

            long Quick_Threshold_afterTime = System.currentTimeMillis(); // 시간 측정 완료
            long Quick_Threshold_result = Quick_Threshold_afterTime - Quick_Threshold_beforeTime; // 최종 시간 계산
            System.out.println( (i+1) + "번째 데이터 실행 시간 : " + Quick_Threshold_result); // 각각의 실행 시간 출력
            Time_Total += Quick_Threshold_result; // 총 실행 시간 합산
        }
        System.out.println("[일반 퀵정렬]의 평균 실행 시간 : " + Time_Total/Data_Array.length); // 평균 실행 시간 계산

        // 임계값에 따른 퀵정렬 시간 측정 반복문
        for(int Threshold = 1; Threshold <= Data_Size; Threshold *= 10) {
            System.out.println("\n** 랜덤 배열 각각의 실행 시간 비교 및 평균 시간 [임계값 : "+Threshold+"의 퀵정렬] **\n");
            Time_Total = 0; // 총 시간 초기화
            for(int i = 0; i < 10; i++) {
                int[] Quick_Threshold = Data_Array[i].clone(); // 기존 데이터 보존을 위한 복사
                long Quick_Threshold_beforeTime = System.currentTimeMillis(); // 일반 퀵정렬 시간 측정 시작

                quicksort_DC(Quick_Threshold, 0, Data_Size-1, Threshold); // 퀵 정렬 실행 (임계값 1 ~ n 까지)

                long Quick_Threshold_afterTime = System.currentTimeMillis(); // 시간 측정 완료
                long Quick_Threshold_result = Quick_Threshold_afterTime - Quick_Threshold_beforeTime; // 최종 시간 계산
                System.out.println( (i+1) + "번째 데이터 실행 시간 : " + Quick_Threshold_result); // 각각의 실행 시간 출력
                Time_Total += Quick_Threshold_result; // 총 실행 시간 합산
            }
            System.out.println("[임계값 : "+Threshold+"의 퀵정렬]의 평균 실행 시간 : " + Time_Total/Data_Array.length); // 평균 실행 시간 계산
        }
    }
}
