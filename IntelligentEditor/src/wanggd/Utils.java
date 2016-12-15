package wanggd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;


public final class Utils {

	public static String[][] getWordList(){
		String[][] wordList = new String[26][3000];
		int initial_index;
		int[] index = new int[26];
		for(int i=0;i<26;i++)
			index[i]=0;
 		File file = new File("word_list.txt");
		BufferedReader br = null;
		String str = null;
		try{
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			while ((str=br.readLine())!=null) {
				str = str.toLowerCase();
				initial_index = str.charAt(0)-'a';
				wordList[initial_index][index[initial_index]++] = new String(str);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wordList;
	}
	
}
