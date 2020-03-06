package collections.jsons.lesson1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReadFromCSV_OK {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		String tempo3Lines = "basas/tempo3Lines.csv";
		String tempo = "basas/tempo.csv";
		String searchWord = "муниципальный";
		List<String> readFromCSV2 = readFile(tempo);

		List<String> matchesList = isNotContains(searchWord, readFromCSV2);

		for (String stroka : matchesList) {
			System.out.println(stroka);
		}

	}

	/** print only if contains */
	@SuppressWarnings("unused")
	private static List<String> isContains(String searchWord, List<String> readFromCSV2) {
		return readFromCSV2.stream().filter(it -> (it.contains(searchWord))).collect(Collectors.toList());
	}

	/** print only if NOT contains */
	private static List<String> isNotContains(String searchWord, List<String> readFromCSV2) {
		return readFromCSV2.stream().filter(it -> (!it.contains(searchWord))).collect(Collectors.toList());
	}

	/** read from File */
	private static List<String> readFile(String filePath) {
		List<String> readList = new ArrayList<>();
		String line = null;

		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
			while ((line = br.readLine()) != null) {
				if (line.trim().equals("")) {
					// empty line
				} else {
					readList.add(line);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return readList;
	}

}
