import java.util.*;

public class Solution {
    
    /**
     * Проверяет, можно ли покрыть все точки k отрезками длины segmentLength
     * Использует жадный алгоритм: всегда покрываем самую левую непокрытую точку
     * 
     * @param coordinates отсортированный массив координат приемников
     * @param k максимальное количество отрезков
     * @param segmentLength длина каждого отрезка
     * @return true, если можно покрыть все точки, иначе false
     */
    private static boolean canCoverAllPoints(int[] coordinates, int k, long segmentLength) {
        int n = coordinates.length;
        int segmentsUsed = 0;  // количество использованных отрезков
        int i = 0;  // индекс текущей непокрытой точки
        
        while (i < n) {
            // Начинаем новый отрезок с текущей непокрытой точки
            segmentsUsed++;
            
            // Если использовали больше k отрезков, то длина недостаточна
            if (segmentsUsed > k) {
                return false;
            }
            
            // Отрезок покрывает диапазон [coordinates[i], coordinates[i] + segmentLength]
            long rightBoundary = coordinates[i] + segmentLength;
            
            // Пропускаем все точки, которые попадают в этот отрезок
            while (i < n && coordinates[i] <= rightBoundary) {
                i++;
            }
        }
        
        return true;  // все точки покрыты, использовано ≤ k отрезков
    }
    
    /**
     * Находит минимальную длину отрезка для покрытия всех точек
     * 
     * @param n количество приемников
     * @param k максимальное количество активных заклинаний
     * @param coordinates массив координат приемников
     * @return минимальная длина отрезка
     */
    private static long findMinimumSegmentLength(int n, int k, int[] coordinates) {
        // Сортируем координаты для жадного алгоритма
        Arrays.sort(coordinates);
        
        // Границы бинарного поиска
        long left = 0;  // минимальная возможная длина
        long right = (long) coordinates[n - 1] - coordinates[0];  // расстояние от min до max
        
        long answer = right;  // изначально ответ = максимальная длина
        
        // Бинарный поиск по длине отрезка
        while (left <= right) {
            long mid = left + (right - left) / 2;
            
            // Проверяем, можно ли покрыть все точки k отрезками длины mid
            if (canCoverAllPoints(coordinates, k, mid)) {
                // Если можно, пробуем уменьшить длину
                answer = mid;
                right = mid - 1;
            } else {
                // Если нельзя, нужна большая длина
                left = mid + 1;
            }
        }
        
        return answer;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Читаем входные данные
        int n = scanner.nextInt();  // количество приемников
        int k = scanner.nextInt();  // максимальное количество заклинаний
        
        int[] coordinates = new int[n];
        for (int i = 0; i < n; i++) {
            coordinates[i] = scanner.nextInt();
        }
        
        // Находим и выводим ответ
        long result = findMinimumSegmentLength(n, k, coordinates);
        System.out.println(result);
        
        scanner.close();
    }
}
