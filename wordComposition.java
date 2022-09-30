import java.io.*;
import java.util.ArrayList;

class wordComposition  {
    // 26 pointers for each english alphabets
    static int nodesize = 26;

    // Declaration of Trie Node
    static class TrieNode {
        TrieNode children[];
        boolean wordend;

        TrieNode() {
            children = new TrieNode[nodesize];
            for (int i = 0; i < nodesize; i++)
                children[i] = null;

            wordend = false;
        }
    }
    // Inserting the new word in the trie
    static void insert(TrieNode root, String word) {
        TrieNode pCrawl = root;

        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (pCrawl.children[index] == null)
                pCrawl.children[index] = new TrieNode();


            pCrawl = pCrawl.children[index];
        }
        pCrawl.wordend = true;
    }

    // checking/searching the word in the Trie
    static boolean search(TrieNode root, String word) {
        TrieNode pCrawl = root;

        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (pCrawl.children[index] == null)
                return false;

            pCrawl = pCrawl.children[index];
        }
        return (pCrawl != null && pCrawl.wordend);
    }

    static boolean compoundedword(String str, TrieNode root,int length) {
        int size = str.length();

        if (size == 0)
            return true;

        for (int i = 1; i <= size; i++) {

            if (search(root, str.substring(0, i)) && compoundedword(str.substring(i, size), root,length)){
                if(length!=str.length()) return true;
                if(size-i==0) return false;
                return true;
            }
                
        }

        return false;
    }

// For reading the txt data file
    public static ArrayList<String> getdata(String filename) throws IOException {
        File f = new File(filename);
        String path = f.getAbsolutePath();

        File file = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;

        ArrayList<String> arr = new ArrayList<>();

        while ((st = br.readLine()) != null) {
            arr.add(st);
        }

        return arr;
    }

    public static void solve(String filename) throws IOException {
        
        // for calculating the execution time
        long startTime = System.nanoTime();

        // storing each word in an arrayList 
        ArrayList<String> listOfWords = getdata(filename);

        // Trie Node is created
        TrieNode root = new TrieNode();

        for (String s : listOfWords)
            insert(root, s);

        String firstAns = "";
        String secondAns = "";
        for (String str : listOfWords) {
            if (compoundedword(str, root,str.length())) {

                if (str.length() > firstAns.length()) {
                    secondAns = firstAns;
                    firstAns = str;
                } else if (str.length() > secondAns.length()) {
                    secondAns = str;
                }
            }
        }

        System.out.println("Longest Compound Word: " + firstAns);
        System.out.println("Second Longest Compound Word: " + secondAns);
        System.out.println("Execution Time: " + (System.nanoTime() - startTime)/1e6 + "ms");

    }

    public static void main(String[] args) throws IOException {

        String filename1 = "Input1.txt";
        // Executing the Input1
        solve(filename1);

        System.out.println();

        filename1 = "input2.txt";
        // Executing the Input2
        solve(filename1);
    }

}