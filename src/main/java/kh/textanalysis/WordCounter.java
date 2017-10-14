package kh.textanalysis;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class WordCounter {

	private Map<String, Integer> wordCounts = new HashMap<>();
	
	public static void main(String[] args) throws Exception{
		String filepath = args[0];
		WordCounter wc = new WordCounter();
		
		long start = System.currentTimeMillis();
		wc.countWordsInFile(filepath);
		long end = System.currentTimeMillis();
		long elapsedMillis = end - start;
		
		//wc.dumpResults();
		wc.printMapDescendingOrder();
		
		System.out.println("Elapsed time: " + elapsedMillis + " millis");
	}

	
	private void printMap() {
		Set<String> keys = this.wordCounts.keySet();
		
		for(String key : keys){
			System.out.println(key + ":" + this.wordCounts.get(key).intValue() + ", ");
		}
	}
	
	
	private void printMapDescendingOrder(){
		this.wordCounts.entrySet().stream()
		   .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
		   .forEach((entry) -> System.out.println(entry.getKey() + " : " + entry.getValue()));
	}


	private void countWordsInFile(String filename) throws IOException{
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
		String currentLine = null;
		while( ( currentLine = reader.readLine()) != null){
			String[] words = currentLine.split("\\s+");
			for(String word : words){
				//remove non-word characters (punctuation)
				word = word.replaceAll("\\W", "");
				Integer currentCount = wordCounts.get(word);
				if( currentCount == null){
					wordCounts.put(word, new Integer(1));
				}
				else{
					int val = currentCount.intValue();
					val++;
					wordCounts.put(word, new Integer(val));
				}
			}
		}
		reader.close();
	}
}
