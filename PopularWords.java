/**
 * Tim Pornyuenyong
 * Project 5
 * 5/10/18
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.HashMap;

public class PopularWords {

    public static void main(String[] args) {

        HashMap<String, Integer> words = new HashMap<>();
        ArrayList<String> arrayList = new ArrayList<>();
        ArrayList<Word> wordList = new ArrayList<>();
        String fileLine;
        String fileLine2;
        String sortType = "";
        int numWords = 0;
        boolean flag = false;

        try {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter the text file path: ");
            String userInput;
            userInput = input.next();
            System.out.println("Enter the parameters file path: ");
            String userInput2;
            userInput2 = input.next();
            input.close();

            //Accessing the files
            File inputFile = new File(userInput);
            File paramFile = new File(userInput2);
            //Going through the parameters file
            Scanner paramData = new Scanner(paramFile);
            while(paramData.hasNext()) {
                fileLine2 = paramData.nextLine();
                String buffarray2[];
                buffarray2 = (fileLine2.split(" "));
                sortType = buffarray2[1];
                sortType = sortType.toLowerCase();
                if(sortType.equals("name") || sortType.equals("scarcity") || sortType.equals("frequency")) {
                    flag = true;
                }
                if(!flag) {
                    System.out.println("Please enter name, frequency, or scarcity");
                    System.exit(0);
                }
                if(buffarray2.length == 3) {
                    try {
                        numWords = Integer.parseInt(buffarray2[2]);
                    }
                    catch (InputMismatchException e) {
                        System.out.println("Integer not detected in third line of parameter file.");
                    }
                }
            }

            //Going through input file and storing the words in arrayList
            Scanner fileData = new Scanner(inputFile);
            while(fileData.hasNext()) {
                fileLine = fileData.nextLine();
                String buffarray[];
                //Split the text with whitespace and "--"
                buffarray = (fileLine.split("-\\-| "));
                for (int i = 0; i < buffarray.length; i++) {
                    arrayList.add(buffarray[i]);
                }
            }


            //Getting rid of word delimiters and empty entries in arrayList + converting to lowercase
            for(int i = 0; i < arrayList.size(); i++) {
                if(arrayList.get(i).equals("")) {
                    arrayList.remove(i);
                }
                arrayList.set(i, arrayList.get(i).replaceAll("[\\[\\](),.!?;:\"]", ""));
                if(arrayList.get(i).equals("")) {
                    arrayList.remove(i);
                }
                if (arrayList.get(i).charAt(0) == '_' || arrayList.get(i).charAt(0) == '-') {
                    arrayList.set(i, arrayList.get(i).substring(1));
                }
                arrayList.set(i, arrayList.get(i).toLowerCase());
            }

            //Storing values of arrayList in hashmap
            for(int j = 0; j < arrayList.size(); j++) {
                if (words.containsKey(arrayList.get(j))) {
                    words.put(arrayList.get(j), words.get(arrayList.get(j)) + 1);
                }
                else {
                    words.put(arrayList.get(j), 1);
                }
            }

            //Creating Word objects from hashmap, storing Word objects into arrayList
            for(HashMap.Entry<String, Integer> entry : words.entrySet()) {
                Word hashEntry = new Word(entry.getKey(), entry.getValue());
                wordList.add(hashEntry);
            }


        } catch (FileNotFoundException e) {
            System.out.println("Invalid file path.");
        }

        //Doing mergeSort
        MergeSort theList = new MergeSort(wordList);
        theList.sort(sortType);
        if(numWords == 0) {
            theList.print();
        }
        theList.print(numWords);
    }
}
