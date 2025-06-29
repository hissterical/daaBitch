import java.util.*;

public class GaleShapley {
    private int n;
    private int[][] menPref;
    private int[][] womenPref;
    private int[] womenPartner;
    private boolean[] menEngaged;
    private int[] menNext;
    
    public GaleShapley(int[][] mp, int[][] wp) {
        n = mp.length;
        menPref = mp;
        womenPref = wp;
        womenPartner = new int[n];
        menEngaged = new boolean[n];
        menNext = new int[n];
        
        Arrays.fill(womenPartner, -1);
        Arrays.fill(menEngaged, false);
        Arrays.fill(menNext, 0);
    }
    
    public void solve() {
        long startTime = System.nanoTime();
        
        int freeMen = n;
        while (freeMen > 0) {
            int man = -1;
            for (int i = 0; i < n; i++) {
                if (!menEngaged[i]) {
                    man = i;
                    break;
                }
            }
            
            int woman = menPref[man][menNext[man]];
            menNext[man]++;
            
            if (womenPartner[woman] == -1) {
                womenPartner[woman] = man;
                menEngaged[man] = true;
                freeMen--;
            } else {
                int currentPartner = womenPartner[woman];
                if (womenPrefers(woman, man, currentPartner)) {
                    womenPartner[woman] = man;
                    menEngaged[man] = true;
                    menEngaged[currentPartner] = false;
                } 
            }
        }
        
        long endTime = System.nanoTime();
        System.out.println("Execution time: " + (endTime - startTime) + " nanoseconds");
        
        printMatching();
    }
    
    private boolean womenPrefers(int woman, int man1, int man2) {
        for (int i = 0; i < n; i++) {
            if (womenPref[woman][i] == man1) return true;
            if (womenPref[woman][i] == man2) return false;
        }
        return false;
    }
    
    private void printMatching() {
        System.out.println("Stable Matching:");
        for (int i = 0; i < n; i++) {
            System.out.println("Woman " + i + " matched with Man " + womenPartner[i]);
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter number of men/women: ");
        int n = sc.nextInt();
        
        int[][] menPref = new int[n][n];
        int[][] womenPref = new int[n][n];
        
        System.out.println("Enter men's preferences (0-indexed):");
        for (int i = 0; i < n; i++) {
            System.out.print("Man " + i + ": ");
            for (int j = 0; j < n; j++) {
                menPref[i][j] = sc.nextInt();
            }
        }
        
        System.out.println("Enter women's preferences (0-indexed):");
        for (int i = 0; i < n; i++) {
            System.out.print("Woman " + i + ": ");
            for (int j = 0; j < n; j++) {
                womenPref[i][j] = sc.nextInt();
            }
        }
        
        GaleShapley gs = new GaleShapley(menPref, womenPref);
        gs.solve();
        
        sc.close();
    }
}
