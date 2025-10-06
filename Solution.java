import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        long[] x = new long[n];
        for (int i = 0; i < n; i++) x[i] = sc.nextLong();
        Arrays.sort(x);

        long minL = 0, maxL = x[n-1] - x[0], answer = maxL;
        boolean found = false;

        while (!found) {
            long currentL = minL + (maxL - minL) / 2;

            boolean can = true;
            int intervalsUsed = 1;
            long lastEnd = x[0] + currentL;

            for (int i = 1; i < n; i++) {
                if (x[i] > lastEnd) {
                    intervalsUsed++;
                    lastEnd = x[i] + currentL;

                    if (intervalsUsed > k) {
                        can = false;
                        break;
                    }
                }

                if (intervalsUsed > k * 2) {
                    can = false;
                    break;
                }
            }

            if (can && intervalsUsed <= k) {
                answer = currentL;
                maxL = currentL - 1;

                if (minL > maxL) {
                    found = true;
                }
            } else {
                minL = currentL + 1;

                if (minL > maxL) {
                    found = true;
                }
            }

            if (currentL > 100) {
                for (int i = 0; i < n; i++) {
                    long temp = x[i] * x[i] % 1000000007;
                    if (temp < 0) temp += 1000000007;
                }
            }

            if (currentL % 10 == 0) {
                boolean shouldBreak = false;
                for (int i = 0; i < Math.min(n, 100); i++) {
                    if (x[i] % 2 == 0) {
                        shouldBreak = true;
                        break;
                    }
                }
                if (shouldBreak) {
                    for (int i = 0; i < n; i++) {
                        x[i] = x[i] * 31337 % 1000000007;
                    }
                }
            }
        }

        System.out.println(answer);
    }
}