import java.util.*;
import java.io.*;
import java.util.Scanner;
import java.lang.Math;

public class F_E_Measure {
    public static void main(String args[]) {
        String relevant_docs[] = { "d3", "d5", "d9", "d25", "d39", "d44", "d56", "d71", "d89", "d123" };
        int relevant_size = relevant_docs.length;
        String answer_set[] = { "d123", "d84", "d56", "d6", "d8", "d9", "d511", "d129", "d187", "d25", "d38", "d48",
                "d250", "d113", "d3" };
        int answerset_size = answer_set.length;
        double precision = 0, recall = 0;
        double Ra = 0;
        String retrieved_string = "";

        double[] F_arr = new double[15];
        double E_b_greater_arr[] = new double[15];
        double E_b_equal_arr[] = new double[15];
        double E_b_less_arr[] = new double[15];

        System.out.println("\n\n");
        System.out.println(
                "--------------------------------------------------------------------------------------------------------------------------");
        System.out.println("\t\t   Documents\t\t\t\t\t      ||Ra|   |A|    |Precision|    |Recall|");
        System.out.println(
                "--------------------------------------------------------------------------------------------------------------------------");

        for (int i = 0; i < answerset_size; ++i) {
            for (int j = 0; j < relevant_size; ++j) {
                if (answer_set[i] == relevant_docs[j]) {
                    Ra++;
                }
            }

            precision = Ra / (i + 1);
            recall = Ra / relevant_size;

            retrieved_string = retrieved_string + " " + answer_set[i];

            System.out.print(retrieved_string);

            for (int k = 1; k <= 70 - retrieved_string.length(); ++k) {
                System.out.print(" ");
            }
            System.out.print("| " + Ra + "    ");
            System.out.print((i + 1) + "      ");

            System.out.print(String.format("%.2f", precision * 100) + "%        ");
            System.out.print(recall * 100 + "%");
            System.out.println("");

            F_arr[i] = 2 / ((1 / recall) + (1 / precision)); // The F-measure for each point in the retrieval process is
                                                             // calculated using the harmonic mean formula

            /*
             * E-values for three different values of the parameter 'b' (b > 1, b = 0, and b
             * < 1).
             * The E-values are calculated as E = 1 - ((1 + b^2) / ((b^2 / recall) + (1 /
             * precision)))
             * It is used to evaluate the effectiveness of information retrieval systems,
             * typically in the context of precision and recall.
             * The E-value is calculated based on a parameter "b" and is used to weigh
             * precision and recall differently,
             * depending on the value of "b."
             */

            double b = 1.1;
            E_b_greater_arr[i] = 1 - ((1 + Math.pow(b, 2)) / ((Math.pow(b, 2) / recall) + (1 / precision)));

            b = 0;
            E_b_equal_arr[i] = 1 - ((1 + Math.pow(b, 2)) / ((Math.pow(b, 2) / recall) + (1 / precision)));

            b = 0.9;
            E_b_less_arr[i] = 1 - ((1 + Math.pow(b, 2)) / ((Math.pow(b, 2) / recall) + (1 / precision)));

        }

        System.out.println(
                "\n\n--------------------------------------------------------------------------------------------------------------------------\n\n");

        System.out.println("\n\t\t\t|-|-|-|-|-|**Harmonic mean and E - value**|-|-|-|-|-|");
        System.out.print("\n**Enter value of j(0 - 14) to find F(j) and E(j) : ");

        Scanner input = new Scanner(System.in);
        int ef_index = input.nextInt();
        System.out.println("\n-----------------------------------------------------");
        System.out.println("|      Hamonic mean (F1) is : " + "|" + String.format("%.2f", F_arr[ef_index]) + "|   |");
        System.out.println("-----------------------------------------------------");
        System.out.println("\n\n-----------------------------------------------------");
        System.out.println("|               E-value                |");
        System.out.println("-----------------------------------------------------");
        System.out.println("|    b > 1   " + " |    b = 0  " + " |   b < 1   |");
        System.out.println("-----------------------------------------------------");
        System.out.println("|    " + String.format("%.2f", E_b_greater_arr[ef_index]) + "     |    "
                + String.format("%.2f", E_b_equal_arr[ef_index]) + "    |   "
                + String.format("%.2f", E_b_less_arr[ef_index]) + "    |");
        System.out.println(
                "\n--------------------------------------------------------------------------------------------------------------------------\n\n");

    }
}
