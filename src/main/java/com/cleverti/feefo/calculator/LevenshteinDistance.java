package com.cleverti.feefo.calculator;

import org.springframework.stereotype.Component;

@Component
public class LevenshteinDistance {

    // Method to find the minimum cost between replace, delete and insert operations
    private static int findMin(int a, int b, int c) {
        int min = Math.min(a, b);
        return Math.min(min, c);
    }

    // This method returns the substitution cost of a character replacement
    // To better understand, please read calculate method commentary and focus on steps 6 and 7
    private static int substitutionCost(char a, char b) {
        return a == b ? 0 : 1;
    }

    /**
     *  This method calculates the Levenshtein Distance (a.k.a. Edit Distance) between two strings.
     *  To do so, we'll have to calculate the distance between every substring of the two words.
     *  For that, we're using a dynamic programming approach and populating a matrix based on the words.
     *  The y axis will represent the word that is being transformed,
     *  and x axis will represent the word that we want to transform into.
     *
     *  The operations are written in the matrix as you can see below, (i,j) represents the cell that is being calculated:
     *  | REPLACE | DELETE |
     *  | INSERT  |  (i,j) |
     *  To understand this, imagine a string DP being transformed into PP. The final result should be 1,
     *  since we only need to replace D with P:
     *  1- To convert " " into " " we need zero operations, it's a match.
     *  |         |   " "   |    D   |    P   |
     *  |   " "   |    0    |        |        |
     *  |    P    |         |        |        |
     *  |    P    |         |        |        |
     *  2- To convert D into " " we need 1 operation, the deletion of D.
     *  |         |   " "   |    D   |    P   |
     *  |   " "   |    0    |    1   |        |
     *  |    P    |         |        |        |
     *  |    P    |         |        |        |
     *  3- To convert DP into " " we need 2 operations, the deletions of D and P.
     *     This would go on for a bigger string. 3, 4, 5 or more deletions.
     *  |         |   " "   |    D   |    P   |
     *  |   " "   |    0    |    1   |    2   |
     *  |    P    |         |        |        |
     *  |    P    |         |        |        |
     *  4- To convert " " into P we need 1 operation, the insertion of P.
     *  |         |   " "   |    D   |    P   |
     *  |   " "   |    0    |    1   |    2   |
     *  |    P    |    1    |        |        |
     *  |    P    |         |        |        |
     *  5- To convert " " into PP we need 2 operations, the insertions of P and P.
     *  |         |   " "   |    D   |    P   |
     *  |   " "   |    0    |    1   |    2   |
     *  |    P    |    1    |        |        |
     *  |    P    |    2    |        |        |
     *  6- Now we have to convert D into P and 3 options are possible: Replace, delete or insert.
     *     We want to choose the option with minimum cost, seeing that replace has a cost of 0,
     *     delete has a cost of 1 and insert is also 1 (check the matrix before the steps if in doubt),
     *     we choose to replace and, because D and P are a mismatch, we add 1 to the count,
     *     as we are doing one more operation.
     *  |         |   " "   |    D   |    P   |
     *  |   " "   |    0    |    1   |    2   |
     *  |    P    |    1    |    1   |        |
     *  |    P    |    2    |        |        |
     *  7- Moving on, we'll fill the rest of the cells in a similar manner. For dpTable[2][1], letters P and P,
     *     replace has a cost of 1, delete is 2 and insert is 1. The minimum cost is 1 and since the letter is the same,
     *     we don't need to add the substitution cost.
     *  |         |   " "   |    D   |    P   |
     *  |   " "   |    0    |    1   |    2   |
     *  |    P    |    1    |    1   |    1   |
     *  |    P    |    2    |        |        |
     *  8- For position dpTable[1][2], letters D and P, the costs are: Replace 1, delete 1 and insert 2.
     *     The minimum cost is 1 and, since it's a mismatch, we need to add 1 as substitution cost.
     *  |         |   " "   |    D   |    P   |
     *  |   " "   |    0    |    1   |    2   |
     *  |    P    |    1    |    1   |    1   |
     *  |    P    |    2    |    2   |        |
     *  9- For the last position, that is the result that matters, since we are now comparing the two complete strings,
     *     the costs are: Replace 1, delete 1 and insert 2, resulting in a minimum cost of 1.
     *     Since it's a match of P letters, the substitution cost will be zero.
     *  |         |   " "   |    D   |    P   |
     *  |   " "   |    0    |    1   |    2   |
     *  |    P    |    1    |    1   |    1   |
     *  |    P    |    2    |    2   |    1   |
     *  Finally, the Levenshtein distance between DP and PP is 1, the result is on dpTable[2][2].
     *
     */
    public static int calculate(String x, String y) {
        // A matrix to store calculated subproblems answers.
        // It is a dynamic programming table, used to find an optimal solution to a complex problem by breaking it down
        // into simpler subproblems.
        int[][] dpTable = new int[x.length() + 1][y.length() + 1];

        for (int i = 0; i <= x.length(); i++) {
            for (int j=0; j <= y.length(); j++) {

                // If "x" is an empty string, all characters of "y" will be inserted into "x"
                // E.g.: To transform " " into "Java", we'll have 4 insertions, thus using the line number (j) to populate
                // This is explained on steps 4 and 5 in the commentary before the method
                if (i == 0) {
                    dpTable[i][j] = j;
                }
                // If "y" is an empty string, all characters of "x" will be removed
                // E.g.: To transform "Java" into " ", we'll have 4 deletions, thus using the column number (i) to populate the table
                // This is explained on steps 2 and 3 in the commentary before the method
                else if (j == 0) {
                    dpTable[i][j] = i;
                }
                // Find the minimum number of operations between replacement, insert and delete
                // To better understand the indexes, check the matrix before the steps on method's commentary
                else {
                    dpTable[i][j] = findMin(
                            // Replacement: we need to call substitution cost to check if it's necessary to add the operation's
                            // cost, since it's possible to have a match
                            dpTable[i - 1][j - 1] + substitutionCost(x.charAt(i - 1),y.charAt(j - 1)),
                            // Delete: we simply add 1 as cost, because a delete operation will be performed
                            dpTable[i - 1][j] + 1,
                            // Insert: we simply add 1 as cost, because an insert operation will be performed
                            dpTable[i][j - 1] + 1
                    );
                }
            }
        }
        return dpTable[x.length()][y.length()];
    }
}
