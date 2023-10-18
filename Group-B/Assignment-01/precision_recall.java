import java.util.*;
import java.io.*;
import java.util.Scanner;
import java.lang.Math;

/*
Precison = Ra / A
Recall = Ra / R

R - set of relevant document   //constant
A - set of retrieved document
Ra - set of relevant retrieved document
*/

public class precision_recall {
    public static void main(String args[]) {
        String relevant_arr[] = { "d3", "d5", "d9", "d25", "d39", "d44", "d56", "d71", "d89", "d123" }; // R
        int relevant_size = relevant_arr.length; // |R|
        String retrieved_arr[] = { "d123", "d84", "d56", "d6", "d8", "d9", "d511", "d129", "d187", "d25", "d38", "d48",
                "d250", "d113", "d3" }; // A
        int retrieved_size = retrieved_arr.length; // |A|
        double precision = 0, recall = 0;
        double count = 0; // |Ra|
        String retrieved_string = "";

        System.out.println("\n\n");
        System.out.println(
                "--------------------------------------------------------------------------------------------------------------------------");
        System.out.println("\t\t   Documents\t\t\t\t\t      ||Ra|   |A|    |Precision|    |Recall|");
        System.out.println(
                "--------------------------------------------------------------------------------------------------------------------------");

        for (int i = 0; i < retrieved_size; ++i) {
            for (int j = 0; j < relevant_size; ++j) {
                if (retrieved_arr[i] == relevant_arr[j]) {
                    count++;
                }
            }

            precision = count / (i + 1);
            recall = count / relevant_size;

            retrieved_string = retrieved_string + " " + retrieved_arr[i];

            System.out.print(retrieved_string);

            for (int k = 1; k <= 70 - retrieved_string.length(); ++k) {
                System.out.print(" ");
            }
            System.out.print("| " + count + "    ");
            System.out.print((i + 1) + "      ");

            System.out.print(String.format("%.2f", precision * 100) + "%        ");
            System.out.print(recall * 100 + "%");
            System.out.println("");

        }
        System.out.println(
                "--------------------------------------------------------------------------------------------------------------------------");

    }
}
