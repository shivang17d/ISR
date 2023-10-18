import java.io.BufferedReader;
import java.util.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Document_Retrieval {

	public static void main(String args[]) {
		try {
			File file = new File("C:\\Users\\admin\\Downloads\\ISR Assignments222\\Group-A\\Assignment-03\\input.txt");
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			StringBuffer sb = new StringBuffer();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
				sb.append("\n");
			}
			fr.close();

			String sentence = sb.toString();
			sentence = sentence.toLowerCase();
			String[] senten = sentence.split(" ");

			int col = 0;
			int[] doc_r = new int[9];
			File filee = new File(
					"C:\\Users\\admin\\Downloads\\ISR Assignments222\\Group-A\\Assignment-03\\representative.txt");
			Scanner sc = new Scanner(filee);
			String str1 = "";

			String[] arr_str = new String[20];

			int c = 0;

			String[] keyword = new String[9];

			while (sc.hasNextLine()) {
				arr_str = sc.nextLine().split("  ");
				for (String a : arr_str) {
					if (c % 2 == 0) {
						keyword[c / 2] = a.strip();
					}

					c++;
				}
			}

			int[][] Key_count = new int[7][6];
			int[] ct = new int[] { 0, 0, 0, 0, 0, 0, 0 };
			for (int i = 0; i < senten.length; i++) {
				for (int j = 0; j < keyword.length; j++) {
					if (stringCompare(senten[i], keyword[j]) == 1) {
						int n = ct[j];
						Key_count[j][n] = i + 1;
						ct[j]++;
					}
				}

			}

			System.out.println("\n\n\n");
			System.out.println("\t\t\t***-- Keyword with their respective positions in document --***\n\n\n");
			for (int i = 0; i < Key_count.length; i++) {
				int y = 0;
				System.out.print(keyword[i] + " : ");
				for (int j = 0; j < Key_count[i].length; j++) {
					if (Key_count[i][j] != 0) {
						int k = i + 1;
						System.out.print(Key_count[i][j] + "\t");
						y++;
					}

				}
				System.out.print("\t\t\t\t----->Total Keywords in document : " + y);
				System.out.println("\n");
			}

			System.out.println("\n--------------------------------------------------------------------------------");

			Scanner scc = new Scanner(System.in);
			System.out.print("\n\t\t\t**Enter a string to search : ");
			String str = scc.nextLine();

			int flag = 0;
			for (int i = 0; i < keyword.length; i++) {
				if (stringCompare(str.strip(), keyword[i]) == 1) {
					flag = 1;
					break;
				}
			}

			if (flag == 1) {
				System.out.print(str + "  found at position : ");
				for (int i = 0; i < senten.length; i++) {
					if (stringCompare(senten[i], str) == 1) {
						int k = i + 1;
						System.out.print("Document-01 --> " + k + "\t\t");
					}
				}
			} else {
				System.out.println("This is not a keyword");
			}
			System.out.println("\n--------------------------------------------------------------------------------\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static int stringCompare(String str1, String str2) {

		int l1 = str1.length();
		int l2 = str2.length();
		int lmin = Math.min(l1, l2);

		int flag = 1;

		if (l1 != l2) {
			return 0;
		}

		for (int i = 0; i < lmin; i++) {
			int str1_ch = (int) str1.charAt(i);
			int str2_ch = (int) str2.charAt(i);

			if (str1_ch != str2_ch) {
				return 0;
			}
		}

		return 1;
	}
}