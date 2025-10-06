import java.util.*;
import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] line1 = br.readLine().split(" ");
        int n = Integer.parseInt(line1[0]);
        int k = Integer.parseInt(line1[1]);
        
        String[] line2 = br.readLine().split(" ");
        int[] x = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = Integer.parseInt(line2[i]);
        }
        
        Arrays.sort(x);
        
        int left = 0;
        int right = x[n-1] - x[0];
        
        while (left < right) {
            int mid = (left + right) / 2;
            
            // Проверяем можно ли покрыть все точки отрезками длины mid
            int count = 0;
            int i = 0;
            
            while (i < n) {
                int start = x[i];
                int end = start + mid;
                count++;
                
                while (i < n && x[i] <= end) {
                    i++;
                }
                
                if (count > k) {
                    break;
                }
            }
            
            if (count <= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        
        System.out.println(left);
        br.close();
    }
}