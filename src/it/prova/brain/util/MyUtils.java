package it.prova.brain.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyUtils {
	public static boolean compareArray(Integer[] array1, Integer[] array2) {
		if (array1.length != array2.length) 
			return false;
			
		for (int i=0;i<array1.length;i++) {
			if (array1[i] < array2[i] - 1 || array1[i] > array2[i] + 1)
				return false;
		}
		return true;
	}
	
	public static List<String> token(String phrase) {
		phrase += '\0';
		int phrase_length = phrase.length();
		List<String> tokens = new ArrayList<>(); 
		String temp = "";
		char c;
		int count = 0;
		
		for (int i=0;i<phrase_length;i++) {
			c = phrase.charAt(i);
			if (c != ' ' && c != '\0' && c != '\n') {
				count = 0;
				temp += c;
			} else {
				count++;
				if (count<2) 
					tokens.add(temp);
				temp = "";
			}
		}
		
		return tokens;
	}
	
	@Deprecated
	public static String fromBreakToSpace(String elenco) {
		String result = "";
		for (int i=0;i<elenco.length();i++) {
			if (elenco.charAt(i) == '\n') {
				result += ' ';
				continue;
			} else if (elenco.charAt(i) == ' ') {
				continue;
			}
			result += elenco.charAt(i);
		}
		return result;
	}
	
	public static String readFromFile(String path) {
		String data = "";
		try {
			File myObj = new File(path);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				data += myReader.nextLine() + " ";
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return data;
	}
	
	
	public static String removeTrashFromFile(String path) {
		String data = "";
		String dataTemp = "";
		String dataTemp1 = "";
		int lengthDataTemp;
		int i;
		char c;
		
		try {
			File myObj = new File(path);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				dataTemp = myReader.nextLine();
				dataTemp1 = dataTemp;
				
				lengthDataTemp = dataTemp1.length();
				i = 0;
				while (i < lengthDataTemp) {
					c = dataTemp1.charAt(i);
					if (MyUtils.isNumber(c) || MyUtils.isPoint(c) || c == ' ' || c == '\t') {
						dataTemp = dataTemp1.substring(0, i);
						if (i+1 <= lengthDataTemp)
							dataTemp += dataTemp1.substring(i+1, lengthDataTemp);
						i--;
					}
					dataTemp1 = dataTemp;
					lengthDataTemp = dataTemp1.length();
					i++;
				}
				data += dataTemp + " ";
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return data;
	}
	
	public static boolean isNumber(char c) {
		return c == '0' || 
				c == '1' ||
				c == '2' ||
				c == '3' ||
				c == '4' ||
				c == '5' ||
				c == '6' ||
				c == '7' ||
				c == '8' ||
				c == '9';
	}
	
	public static boolean isPoint(char c) {
		return c == '.';
	}
	
	public static boolean isAffermazione(String string) {
		return string.toLowerCase().equals("si") ||  string.toLowerCase().equals("s");
	}
}
