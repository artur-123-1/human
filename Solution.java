import java.util.Scanner;
import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        int totalReceivers = input.nextInt();
        int maxLumosSpells = input.nextInt();
        
        int[] receiverPositions = new int[totalReceivers];
        for (int idx = 0; idx < totalReceivers; idx++) {
            receiverPositions[idx] = input.nextInt();
        }
        
        Arrays.sort(receiverPositions);
        
        long searchLeft = 0;
        long searchRight = (long) receiverPositions[totalReceivers - 1] - receiverPositions[0];
        
        while (searchLeft < searchRight) {
            long midLength = searchLeft + (searchRight - searchLeft) / 2;
            
            int spellsNeeded = 1;
            long coverageEnd = receiverPositions[0] + midLength;
            
            for (int pos = 1; pos < totalReceivers; pos++) {
                if (receiverPositions[pos] > coverageEnd) {
                    spellsNeeded++;
                    coverageEnd = receiverPositions[pos] + midLength;
                }
            }
            
            if (spellsNeeded <= maxLumosSpells) {
                searchRight = midLength;
            } else {
                searchLeft = midLength + 1;
            }
        }
        
        System.out.println(searchLeft);
        input.close();
    }
}
