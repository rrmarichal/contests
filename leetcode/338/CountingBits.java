public class CountingBits {
    
    public int[] countBits(int num) {
        int[] result = new int[num + 1];
        for (int j = 1; j <= num; j++)
            result[j] = (j % 2 == 1)
                ? result[j - 1] + 1
                : result[j / 2];
        return result;
    }

}
