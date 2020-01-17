package mpeitp;

/**
 *
 * @author manuelgil and João Santos
 */
public class BloomF {
    public boolean[] initb(int l, boolean[] b) {
        b = new boolean[l];
        for (boolean bloom : b) {
            bloom = false;
        }
        
        return b;
    }
    
    //Dan Bernstein's 'djb2' hash function
    public void insertString(String elem, int k, int l, boolean[] b){
        char ch[];
        ch = elem.toCharArray();
        long hashl;
        hashl = 5381;
        int ind;
         
        System.out.print("bits set to one: ");
        for (int i = 0; i < k; i++) { 
            for (char c : ch) {
                hashl = c + ((hashl<<5)-hashl);
            }

            hashl = Math.abs((hashl % l));
               
            ind = (int)hashl;
            System.out.print(" " + ind);
            
            b[ind] = true;
        }

        System.out.print(" -> " + elem);
        
        System.out.println();
        String repeated = new String(new char[k]).replace("\0", "-----");
        System.out.println(repeated);
    }
    
    public boolean isMember(String e, int l, int k, boolean[] b){
        long hashm; // inicializar h
        hashm = 5381;
        boolean ismember = true; // boolean que verifica se o bit esta a 1
        char ch[];
        ch = e.toCharArray();
        int ind;

        int i=0;
        do{
            for (i = 0; i < k; i++) { 
                for (char c : ch) {
                    hashm = c + ((hashm<<5)-hashm);
                }

                hashm = Math.abs((hashm % l));

                ind= (int)hashm;
                System.out.print(" "+ind);
                if(b[ind] != true){
                    System.out.printf(" -> '%s' Definitely not in the bloom! \n",e);
                    return false;
                }
            }
        }while(i<k);
       

        if (i==k){
            System.out.printf(" -> '%s' Probably in here...\n",e);
            return true;
        }
        return false;
    }
}
