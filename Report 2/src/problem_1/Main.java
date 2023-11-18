package problem_1;

public class Main {

    // 무작위 수 생성
    public static void Random_array(int[] array) {
        for(int i = 0; i < array.length; i++)
            array[i] = (int)(Math.random()*100);
    }

    public static void main(String[] args) {
        int[] Random = new int[100];
        Random_array(Random);

        for(int i = 0; i < Random.length; i++)
            System.out.println(i+ " : " + Random[i]);
    }

}


