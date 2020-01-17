package mpeitp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


/**
 *
 * @author manuelgil and João Santos
 */
public class MinH {
    
    private int index;
    private int numHash;
    private final int[] seed;

    
    public MinH(int numHash){
        this.seed = new int[numHash];
        this.index = 0;
        this.numHash = numHash;
        for (int i = 0; i < numHash; i++) {
            seed[i] = (int)(Math.random()*100);
        }
    }
    
    public void getSetOfWords(HashMap<Integer,List<String>> S , String file) throws FileNotFoundException{
        
        List<String> shingles = new ArrayList<>(); 
        
        Scanner f = new Scanner(new File(file));
        List<String> words1 = new ArrayList<>();
        String[] line1;
        
        // reads the file and stores every word in an ArrayList
        while(f.hasNext()){ 
            line1 = f.nextLine().split("\\s+");
            for(int i=0; i<line1.length; i++){
                if(line1[i].contains("."))
                    line1[i] = line1[i].replace(".","");
                else if(line1[i].contains(","))
                    line1[i] = line1[i].replace(",","");
                else if(line1[i].contains("!"))
                    line1[i] = line1[i].replace("!","");
                else if(line1[i].contains("-"))
                    line1[i] = line1[i].replace("-","");
                else if(line1[i].contains(";"))
                    line1[i] = line1[i].replace(";","");
            }
           
            words1.addAll(Arrays.asList(line1));
            String[] words = new String[words1.size()];
            
            words1.toArray(words);
                                          
            for (int i = 0; i < words.length; i++) {
                for (int j = 0; j < words[i].length()-2; j++){ 
                    shingles.add(words[i].substring(j, j + 3));
                }
            }
        }
       
        System.out.println(shingles);
        S.put(index, shingles);
        index++;
    }

    public  void getFileInfo(HashMap<Integer,List<Integer>> S , String file) throws FileNotFoundException{
        Scanner f = new Scanner(new File(file));
        String[] line;
        int count = 0;
        
        while(f.hasNextLine()){
            Scanner nReader = new Scanner(f.nextLine());
            ArrayList<Integer> n = new ArrayList<>();
            while(nReader.hasNextInt())
            {
                n.add(nReader.nextInt());
            }
            S.put(index, n);
            index++;
        }    
    }
    
    public int hashFunction(String word, int length, int seed){
		int h = seed;
		for (int i=0; i < length ; i++){
			h = (int)(31 * h + word.charAt(i));
			h = Math.abs(h);
		}
		return (int)(h);
    }
      
    public void hashSet(int key ,List<String> set ,int numh, int[][] matrix){
        
        //System.out.print(key + " ");
        //System.out.println(set);
        int myval=0;
        for(int j=0; j<numh; j++){  
            matrix[key][j] = Integer.MAX_VALUE;
            for(String shingle: set){
                myval = hashFunction(shingle,shingle.length(),seed[j]);
                if(myval < matrix[key][j])
                    matrix[key][j] = myval;//guarda o hash com menor valor. 
            }
        }
    }
    
    public void hashSets(int key ,List<Integer> set ,int numh, int[][] matrix){
        
        //System.out.print(key + " ");
        //System.out.println(set);
        int myval;
        for(int j=0; j<numh; j++){  
            matrix[key][j] = Integer.MAX_VALUE;
            for(Integer n: set){
                myval = hashFunction((""+n), (""+n).length(), seed[j]);
                if(myval < matrix[key][j])
                    matrix[key][j] = myval;//guarda o hash com menor valor. 
            }
        }
    }
}


