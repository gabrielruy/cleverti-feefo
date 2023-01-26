package com.cleverti.feefo.calculator;

import org.springframework.stereotype.Component;

@Component
public class LevenshteinDistance {

    private static int findMin(int a, int b, int c) {
        int min = Math.min(a, b);
        return Math.min(min, c);
    }

    private static int substitutionCost(char a, char b) {
        return a == b ? 0 : 1;
    }

    public static int calculate(String x, String y) {
        // A matrix to store calculated answers
        int[][] distance = new int[x.length() + 1][y.length() + 1];

        for (int i = 0; i <= x.length(); i++) {
            for (int j=0; j <= y.length(); j++) {

                // If "x" is empty, all characters of "y" will be inserted into "x"
                if (i == 0) {
                    distance[i][j] = j;
                }
                // If "y" is empty, all characters of "x" will be removed
                else if (j == 0) {
                    distance[i][j] = i;
                }
                // Find the minimum number of operations between replacement, insert and delete
                else {
                    distance[i][j] = findMin(distance[i - 1][j - 1]
                                    + substitutionCost(x.charAt(i - 1),y.charAt(j - 1)), // Replacement
                            distance[i - 1][j] + 1, // Delete
                            distance[i][j - 1] + 1); // Insert
                }
            }
        }
        return distance[x.length()][y.length()];
    }
}
