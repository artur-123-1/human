import java.io.*;
import java.util.*;

public class Solution {
    
    static class ReceiverGrid {
        private int[] positions;
        private int spellLimit;
        
        ReceiverGrid(int[] coords, int maxSpells) {
            this.positions = coords.clone();
            this.spellLimit = maxSpells;
            Arrays.sort(this.positions);
        }
        
        long calculateOptimalRange() {
            long minRange = 0L;
            long maxRange = (long)positions[positions.length - 1] - positions[0];
            
            while (minRange < maxRange) {
                long testRange = minRange + (maxRange - minRange) / 2;
                
                if (isRangeSufficient(testRange)) {
                    maxRange = testRange;
                } else {
                    minRange = testRange + 1;
                }
            }
            
            return minRange;
        }
        
        private boolean isRangeSufficient(long range) {
            int spellCount = 1;
            long currentCoverage = positions[0] + range;
            
            for (int idx = 1; idx < positions.length; idx++) {
                if (positions[idx] > currentCoverage) {
                    spellCount++;
                    if (spellCount > spellLimit) return false;
                    currentCoverage = positions[idx] + range;
                }
            }
            
            return true;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        
        int receiverCount = Integer.parseInt(tokenizer.nextToken());
        int availableSpells = Integer.parseInt(tokenizer.nextToken());
        
        int[] coords = new int[receiverCount];
        tokenizer = new StringTokenizer(reader.readLine());
        
        for (int i = 0; i < receiverCount; i++) {
            coords[i] = Integer.parseInt(tokenizer.nextToken());
        }
        
        ReceiverGrid grid = new ReceiverGrid(coords, availableSpells);
        System.out.println(grid.calculateOptimalRange());
        
        reader.close();
    }
}
