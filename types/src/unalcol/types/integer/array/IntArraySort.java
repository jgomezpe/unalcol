package unalcol.types.integer.array;

/**
 * <p>Sorting method for arrays of ints</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 * 
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 *
 */


public class IntArraySort{
    /**
     * Sorts (low to high) an array of integers using bubble sort
     * @param a Integer array to be sorted
     */
    public static void bubble(int[] a) {
        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (a[j] < a[i]) {
                    int x = a[i];
                    a[i] = a[j];
                    a[j] = x;
                }
            }
        }
    }

    /**
     * Sorts (low to high) an array of integers using merge sort
     * @param a Integer array to be sorted
     */
    public static void merge(int[] a) {
        int n = a.length;
        if (n >= 4) {
            int nizq = n / 2;
            int nder = n - nizq;
            int[] aIzq = new int[nizq];
            int[] aDer = new int[nder];
            for (int i = 0; i < nizq; i++) {
                aIzq[i] = a[i];
            }
            for (int i = 0; i < nder; i++) {
                aDer[i] = a[nizq + i];
            }
            merge(aIzq);
            merge(aDer);
            int k = 0;
            int izq = 0;
            int der = 0;
            while (izq < nizq && der < nder) {
                if (aIzq[izq] < aDer[der]) {
                    a[k] = aIzq[izq];
                    izq++;
                } else {
                    a[k] = aDer[der];
                    der++;
                }
                k++;
            } while (izq < nizq) {
                a[k] = aIzq[izq];
                izq++;
                k++;
            } while (der < nder) {
                a[k] = aDer[der];
                der++;
                k++;
            }
        } else {
            bubble(a);
        }
    }    
}
