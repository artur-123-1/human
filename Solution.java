import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Чтение входных данных
        int n = sc.nextInt();
        int k = sc.nextInt();
        long[] x = new long[n];
        
        for (int i = 0; i < n; i++) {
            x[i] = sc.nextLong();
        }
        
        // Сортировка координат
        Arrays.sort(x);
        
        // Бинарный поиск минимального ℓ
        long left = 0;
        long right = x[n-1] - x[0];
        long result = right;
        
        while (left <= right) {
            long mid = left + (right - left) / 2;
            
            if (canCover(x, k, mid)) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        System.out.println(result);
    }
    
    // Функция проверки возможности покрытия всех точек k интервалами длины ℓ
    private static boolean canCover(long[] x, int k, long length) {
        int count = 1; // Количество интервалов
        long currentEnd = x[0] + length;
        
        for (int i = 1; i < x.length; i++) {
            if (x[i] > currentEnd) {
                // Нужно новый интервал
                count++;
                currentEnd = x[i] + length;
                
                // Если мы использовали больше k интервалов, то невозможно
                if (count > k) {
                    return false;
                }
            }
        }
        
        return true;
    }
}