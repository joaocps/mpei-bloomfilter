package mpeitp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author manuelgil and João Santos
 */
public class Teste {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        
        int l;
        int k;
        boolean[] b = null;
        boolean verify=false;
        String file = "Michelle.txt";
        String file1 = "Melania.txt";
        //file = "Michelle.txt";
        //file1 = "Melania.txt";
        List<String> words = new ArrayList<>();
        List<String> words1 = new ArrayList<>();
        
        BloomF bloom = new BloomF();
        
   
        System.out.print("Introduza o valor que pretende para o tamanho do Bloom Filter: \n");
        l = sc.nextInt();
        
        System.out.print("Introduza o numero de funcoes de dispersao: \n");
        k = sc.nextInt();
        
        //SET ALL BOOLEANS TO FALSE
        b = bloom.initb(l, b);
        
        //INSERTS THE WORDS IN THE BLOOM
        words = readFile(file);
        for (int i = 0; i < words.size(); i++) {
            bloom.insertString(words.get(i), k, l, b);
        }
        
        words1 = readFile1(file1);
        
        //bloom.insertString("Manuel", k, l, b);
        //bloom.insertString("Joao", k, l, b);
        //bloom.insertString("Gil", k, l, b);
        //bloom.insertString("Santos", k, l, b);
        //bloom.insertString("UA", k, l, b);
        //bloom.insertString("DETI", k, l, b);
        //bloom.insertString("LEI", k, l, b);
        //int num=7;
        
        //VERIFIES IF THE WORDS ARE PROBABLY IN THE BLOOM OR NOT
        int count = 0;
        for (int i = 0; i < words1.size(); i++) {
            verify = bloom.isMember(words1.get(i), l, k, b);
            //verify = bloom.isMember("DETI", l, k, b);            
        }

        //BLOOM FILTER REPRESENTATION
        int col=0;
        System.out.println();
        System.out.println("BloomFilter Representation: ");
        System.out.println();

        System.out.println(b.length);
        for (int i = 0; i < b.length; i++) {
            if(col==k){
                System.out.printf("%d --> %b  |\n",i , b[i]);
                col=0;
            }
            System.out.printf("%d --> %b  |",i , b[i]);
            col++;

        }
        
        System.out.println();  
        System.out.println();       
        System.out.println("False Positives probability: ");
        
        //REPRESENTS THE PROBABILITY OF FALSE POSITIVES
        double fk = (double)k;
        double fw = (double)words.size();
        //double fw = (double)num;
        double fl = (double)l;
        double ak = Math.pow((1-(Math.pow(Math.E,(((-fk)*fw)/(fl))))),fk);
        System.out.println(ak);
      
        //REPRESENTS THE OPTIMAL NUMBER OF HASHFUNCTIONS
        double optk =(int) (fl/fw)*Math.log(2);
        System.out.println();
        System.out.print("Optimal value of k: \n");
        System.out.println(Math.round(optk));
        System.out.println();

        
        //COUNTS THE NUMBER OF BITS SET TO 0 AND 1
        int countt=0;
        int countf=0;
        for (int i = 0; i < b.length; i++) {
            if(b[i]==true){
                countt++;
            }
            else
                countf++;
        }
        System.out.println("Number of bits set to 'zero': ");
        System.out.println(countf);
        
        System.out.println("Number of bits set to 'one': ");
        System.out.println(countt);
        System.out.println();
    }
    
    public static List<String> readFile(String file) throws FileNotFoundException{
        //file = "Michelle.txt";
        Scanner f = new Scanner(new File(file));
        List<String> words = new ArrayList<>();  
        String[] line;
        // reads the file and stores every word in an ArrayList
        while(f.hasNext()){ 
            line = f.nextLine().split("\\s+");
            for(int i=0; i<line.length; i++){
                if(line[i].contains("."))
                    line[i] = line[i].replace(".","");
                else if(line[i].contains(","))
                    line[i] = line[i].replace(",","");
                else if(line[i].contains("!"))
                    line[i] = line[i].replace("!","");
                else if(line[i].contains("-"))
                    line[i] = line[i].replace("-","");
                else if(line[i].contains(";"))
                    line[i] = line[i].replace(";","");
            }
            words.addAll(Arrays.asList(line));
        }
        
        //Text Representation
        System.out.println("-----------------------------------------------------");
        System.out.println("Michelle Obama's Speech");
        System.out.println();
        
        int index = 0;
        for (String wo : words) {
            System.out.print(" " + wo);
            index++;
            
            if(index==9){
                System.out.println();
                index=0;
            }
        }
        System.out.println();
        System.out.println("-----------------------------------------------------");
        System.out.println();
        
        Set<String> hs = new HashSet<>();
        hs.addAll(words);
        words.clear();
        words.addAll(hs);
        
        return words;
    }
    
    public static List<String> readFile1(String file) throws FileNotFoundException{
        //file = "Melania.txt";
        Scanner f = new Scanner(new File(file));
        List<String> words = new ArrayList<>();  
        String[] line;
        // reads the file and stores every word in an ArrayList
        while(f.hasNext()){ 
            line = f.nextLine().split("\\s+");
            for(int i=0; i<line.length; i++){
                if(line[i].contains("."))
                    line[i] = line[i].replace(".","");
                else if(line[i].contains(","))
                    line[i] = line[i].replace(",","");
                else if(line[i].contains("!"))
                    line[i] = line[i].replace("!","");
                else if(line[i].contains("-"))
                    line[i] = line[i].replace("-","");
                else if(line[i].contains(";"))
                    line[i] = line[i].replace(";","");
            }
            words.addAll(Arrays.asList(line));
        }
   
        Set<String> hs = new HashSet<>();
        hs.addAll(words);
        words.clear();
        words.addAll(hs);

        return words;
    }
}
