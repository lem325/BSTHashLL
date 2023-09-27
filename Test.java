import java.util.Iterator;
import java.util.NoSuchElementException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Lillian Mauger
 * Date modified: 11/15/2022
 * Tester class for ALA9
 */
public class Test{
    public static void main (String[]args){
//things
        HashMap<String, String> dictionaryHM = new HashMap<>(50000);
        BST<String> dictionaryBST = new BST<>();
        LinkedList<String> dictionaryLL = new LinkedList<>();
        ArrayList<HashMapEntry<String, String>> words = new ArrayList<HashMapEntry<String, String>>();


//Reads file and adds each line into arrayList words
        readFile(words, "dictionary.txt");
        
//electric shuffle
        Collections.shuffle(words);

//Adding to HashMap, BST, and LinkedList
        addWords(words, dictionaryHM, dictionaryBST, dictionaryLL);
                
//1000 search ops on hmap, bst, and ll for 1000 randomly selected words
//then display iterations for each
        testSearch(words, dictionaryHM, dictionaryBST, dictionaryLL);
      
        System.out.println("\n\n Maximum Number of Collisions: " + dictionaryHM.collisions());
    }

    public static void readFile(ArrayList<HashMapEntry<String,String>> list, String filename){
        File file = new File("dictionary.txt");
        try{
            Scanner read = new Scanner(file);
            while(read.hasNextLine()){
                String line = read.nextLine();
                String [] items = line.split("\\|");
                list.add(new HashMapEntry<>(items[0], items[1]));
            }
            read.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File not found.");
            System.exit(0);
        }
    }

    public static void addWords(ArrayList<HashMapEntry<String,String>> al, HashMap<String,String> hm, BST<String> bst, LinkedList<String> ll){

        for(int i = 0; i<al.size(); i++){
            HashMapEntry<String, String> entry = al.get(i);
            hm.put(entry.getKey(), entry.getValue());
            bst.add(entry.getKey());
            ll.add(entry.getKey());
        }
    }

    public static void  testSearch(ArrayList<HashMapEntry<String,String>> al, HashMap<String,String> hm, BST<String> bst, LinkedList<String> ll ){
        int totalHM = 0, totalBST = 0, totalLL = 0;
        int hmIter=0, bstIter=0, llIter=0;
        System.out.println("Testing contains");
        System.out.printf("%-30s\t%-10s\t%-10s\t%-10s\n", "Word", "LinkedList", "BST", "Hash Table");
        for(int i=0; i<=1000; i++){
            int rand = (int)(Math.random() * al.size());
            HashMapEntry<String, String> entry = al.get(rand);
            HashMap.getIterations = 0;

            hm.get(entry.getKey());
            hmIter = HashMap.getIterations;
             bstIter = bst.contains(entry.getKey());
             llIter = ll.contains(entry.getKey());

            totalHM += hmIter;
            totalBST += bstIter;
            totalLL += llIter;

            if(i%50 == 0){
                System.out.printf("%-30s\t%-10d\t%-10d\t%-10d\n", entry.getKey(), llIter, bstIter, hmIter);
            }
        }
        System.out.printf("%-30s\t%-10d\t%-10d\t%-10d\n", "Average", (totalLL/1000), (totalBST/1000),(totalHM/1000) );
    }
/**
 * HashMap is incredibly quicker here with an average of ~1 iterations per search.
 * BST tends to be pretty decent with the iterations around an average of 15-20, so it gets to the needed term but not without some
 * searching.
 * LinkedList is not great here. With average iterations being at tens of thousands, it shows that a weakness in efficiency
 * for LinkedList is searching, it takes forever.
 */
    

}