package mpeitp;

import java.io.FileNotFoundException;
import static java.lang.System.out;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author manuelgil and João Santos
 */
public class TesteMinH {
    public static void main(String[] args) throws FileNotFoundException{
        
        int num = 100;
        MinH m = new MinH(num);
        int nUsers = 1001;
        int[][] matrix = new int[nUsers][num];
        HashMap<Integer,List<Integer>> notas = new HashMap<>();
        
        //int ntexts = 14;
        //int[][] matrix = new int[ntexts][num];
        //HashMap<Integer,List<String>> text = new HashMap<>();
        
        
        m.getFileInfo(notas,"notas.txt");
        
//        m.getSetOfWords(text,"speeches/obama1.txt");
//        m.getSetOfWords(text,"speeches/obama.txt");
//        m.getSetOfWords(text,"speeches/bush1.txt");
//        m.getSetOfWords(text,"speeches/bush.txt");
//        m.getSetOfWords(text,"speeches/clinton1.txt");
//        m.getSetOfWords(text,"speeches/clinton.txt");
//        m.getSetOfWords(text,"speeches/gbush.txt");
//        m.getSetOfWords(text,"speeches/reagan1.txt");
//        m.getSetOfWords(text,"speeches/reagan.txt");
//        m.getSetOfWords(text,"speeches/carter.txt");
//        m.getSetOfWords(text,"speeches/nixon1.txt");
//        m.getSetOfWords(text,"speeches/nixon.txt");
//        m.getSetOfWords(text,"speeches/johnson.txt");
//        m.getSetOfWords(text,"speeches/kennedy.txt");
        
//        for (Map.Entry<Integer, List<String>> entry : text.entrySet()) {
//            int key = entry.getKey();
//            List<String> value = entry.getValue();
//            
//            m.hashSet(key,text.get(key), num, matrix);
//        }        

        for(Map.Entry<Integer, List<Integer>> entry : notas.entrySet()){
            int key = entry.getKey();
            List<Integer> value = entry.getValue();
            
            m.hashSets(key,notas.get(key), num, matrix);
            System.out.print("out:"+ key+" ");
        }
        
        System.out.println();
        
        //PRINTS THE MATRIX 
        System.out.println("-----------------------------------------------------");
        System.out.println("MATRIX");
        System.out.println();
        for(int r=0; r<matrix.length; r++) {
            for(int c=0; c<matrix[r].length; c++)
                System.out.print(matrix[r][c] + " ");
	    System.out.println();
	}
        
        //JACCARD SIMILARITY AND JACCARD DISTANCE
        for (int r = 0; r < matrix.length; r++) {
            for (int r1 = 0; r1 < matrix.length; r1++) {
                if(r == r1){
                }
                else{
                    int count = 0;
                    for (int j = 0; j < num; j++) {
                        if(matrix[r][j] == matrix[r1][j])
                            count++; 
                    }
                    double jacS = (double) count/num;
                    double jacD = (double) 1-jacS;
                    
                    if(jacS>0.20){//threshold
                        System.out.println("----------------------------");
                        out.printf("Jaccard Similarity (%d,%d) : %.2f \n",r,r1,jacS);
                        out.printf("Jaccard Distance (%d,%d) : %.2f \n",r,r1,jacD);
                        System.out.println("----------------------------");
                    }
                }    
            }    
        }   
    }
}
