import java.util.*;
import java.io.*;

public class Solution {
    
    /**
     * Проверяет, можно ли покрыть все точки отрезками длины l, 
     * используя не более k отрезков
     */
    private static boolean canCover(int[] points, long l, int k) {
        int n = points.length;
        int segments = 0; // количество использованных отрезков
        int i = 0; // индекс текущей точки
        
        while (i < n) {
            long start = points[i]; // начало нового отрезка
            long end = start + l;   // конец отрезка
            segments++;
            
            // Пропускаем все точки, покрытые этим отрезком
            while (i < n && points[i] <= end) {
                i++;
            }
            
            // Если использовали больше k отрезков, то невозможно
            if (segments > k) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Находит минимальную длину отрезка l для покрытия всех точек
     */
    private static long solve(int n, int k, int[] points) {
        // Сортируем точки по возрастанию
        Arrays.sort(points);
        
        // Границы бинарного поиска
        long left = 0;
        long right = (long) points[n-1] - points[0]; // максимальное расстояние
        
        // Бинарный поиск по ответу
        while (left < right) {
            long mid = left + (right - left) / 2;
            
            if (canCover(points, mid, k)) {
                right = mid; // можем попробовать меньшую длину
            } else {
                left = mid + 1; // нужна большая длина
            }
        }
        
        return left;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // Читаем n и k
        String[] firstLine = br.readLine().trim().split("\\s+");
        int n = Integer.parseInt(firstLine[0]);
        int k = Integer.parseInt(firstLine[1]);
        
        // Читаем координаты приемников
        String[] secondLine = br.readLine().trim().split("\\s+");
        int[] points = new int[n];
        for (int i = 0; i < n; i++) {
            points[i] = Integer.parseInt(secondLine[i]);
        }
        
        // Находим и выводим ответ
        long result = solve(n, k, points);
        System.out.println(result);
        
        br.close();
    }
}