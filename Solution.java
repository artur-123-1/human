import java.util.Scanner;
import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int k = sc.nextInt();
        
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        
        Arrays.sort(arr);
        
        int low = 0;
        int high = arr[n - 1] - arr[0];
        
        while (low < high) {
            int middle = low + (high - low) / 2;
            
            int count = 1;
            int border = arr[0] + middle;
            
            for (int j = 1; j < n; j++) {
                if (arr[j] > border) {
                    count++;
                    border = arr[j] + middle;
                }
            }
            
            if (count <= k) {
                high = middle;
            } else {
                low = middle + 1;
            }
        }
        
        System.out.println(low);
        sc.close();
    }
}
