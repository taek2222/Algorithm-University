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
    private static void insertionSort(int[] list, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            int key = list[i];
            int j = i - 1;

            while (j >= low && list[j] > key) {
                list[j + 1] = list[j];
                j--;
            }
            list[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        int[] Random = new int[1000000]; // 데이터 배열
        Random_array(Random); // 랜덤 수 선정

        int[] Quick = Random.clone(); // 일반 퀵정렬 배열 선언 (랜덤 수 배열 복사)
        int[] Quick_Threshold_1 = Random.clone(); // 퀵 정렬 임계값 배열 선언 (랜덤 수 배열 복사)_1
        int[] Quick_Threshold_2 = Random.clone(); // 퀵 정렬 임계값 배열 선언 (랜덤 수 배열 복사)_2
        int[] Quick_Threshold_3 = Random.clone(); // 퀵 정렬 임계값 배열 선언 (랜덤 수 배열 복사)_3

        // 일반 퀵정렬 시간 측정 및 시작
        long Quick_beforeTime = System.currentTimeMillis(); // 일반 퀵정렬 시간 측정 시작

        quicksort_DC(Quick, 0, Quick.length-1, 0);

        long Quick_afterTime = System.currentTimeMillis(); // 시간 측정 완료
        long Quick_result = Quick_afterTime - Quick_beforeTime; // 최종 시간 계산

        // 임계값_1 퀵정렬 시간 측정 및 시작
        long Quick_beforeTime_Threshold_1 = System.currentTimeMillis(); // 임계값 퀵정렬 시간 측정 시작

        quicksort_DC(Quick_Threshold_1, 0, Quick_Threshold_1.length-1, 100);

        long Quick_Threshold_1_afterTime = System.currentTimeMillis(); // 시간 측정 완료
        long Quick_Threshold_1_result = Quick_Threshold_1_afterTime - Quick_beforeTime_Threshold_1; // 최종 시간 계산

        // 임계값_2 퀵정렬 시간 측정 및 시작
        long Quick_beforeTime_Threshold_2 = System.currentTimeMillis(); // 임계값 퀵정렬 시간 측정 시작

        quicksort_DC(Quick_Threshold_2, 0, Quick_Threshold_2.length-1, 1000);

        long Quick_Threshold_2_afterTime = System.currentTimeMillis(); // 시간 측정 완료
        long Quick_Threshold_2_result = Quick_Threshold_2_afterTime - Quick_beforeTime_Threshold_2; // 최종 시간 계산


        // 임계값_3 퀵정렬 시간 측정 및 시작
        long Quick_beforeTime_Threshold_3 = System.currentTimeMillis(); // 임계값 퀵정렬 시간 측정 시작

        quicksort_DC(Quick_Threshold_3, 0, Quick_Threshold_3.length-1, 10000);

        long Quick_Threshold_3_afterTime = System.currentTimeMillis(); // 시간 측정 완료
        long Quick_Threshold_3_result = Quick_Threshold_3_afterTime - Quick_beforeTime_Threshold_3; // 최종 시간 계산



        System.out.println("퀵 정렬 실행 시간(ms) : " + Quick_result);
        System.out.println("퀵 정렬 실행 시간(ms) : " + Quick_Threshold_1_result);
        System.out.println("퀵 정렬 실행 시간(ms) : " + Quick_Threshold_2_result);
        System.out.println("퀵 정렬 실행 시간(ms) : " + Quick_Threshold_3_result);
    }
}
