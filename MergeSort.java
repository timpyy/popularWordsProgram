/**
 * Tim Pornyuenyong
 * Project 5
 * 5/10/18
 */

import java.util.ArrayList;

public class MergeSort {

    private ArrayList<Word> wordList;

    /**
     * Constructor
     * @param input takes in an ArrayList of Word objects
     */
    public MergeSort(ArrayList<Word> input) {
        wordList = input;
    }

    /**
     * Calls the constructor and passes along the type of sort desired
     * @param sortType String that contains either "name", "frequency", or "scarcity"
     */
    public void sort(String sortType) {
        wordList = mergeSort(wordList, sortType);
    }

    /**
     * Recursive method that splits the inputted ArrayList into two, and passes along the desired sort type.
     * It then merges the left and right halves together once the ArrayList is split to its smallest form.
     * @param whole Original whole ArrayList
     * @param sortType sortType string
     * @return Returns a sorted ArrayList
     */
    public ArrayList<Word> mergeSort(ArrayList<Word> whole, String sortType) {
        ArrayList<Word> left = new ArrayList<>();
        ArrayList<Word> right = new ArrayList<>();
        int center;

        if (whole.size() == 1) {
            return whole;
        } else {
            center = whole.size() / 2;
            for (int i = 0; i < center; i++) {
                left.add(whole.get(i));
            }

            for (int i = center; i < whole.size(); i++) {
                right.add(whole.get(i));
            }

            left  = mergeSort(left, sortType);
            right = mergeSort(right, sortType);

            merge(left, right, whole, sortType);
        }
        return whole;
    }

    /**
     * Takes in two halves of an array and merges them according to the desire sort type.
     * The sorting happens in this method.
     * @param left Left half of the ArrayList
     * @param right Right half of the ArrayList
     * @param whole the original complete ArrayList
     * @param sortType sortType string
     */
    private void merge(ArrayList<Word> left, ArrayList<Word> right, ArrayList<Word> whole, String sortType) {
        int leftIndex = 0;
        int rightIndex = 0;
        int wholeIndex = 0;

        if(sortType.equals("name")) {
            while (leftIndex < left.size() && rightIndex < right.size()) {
                if (left.get(leftIndex).getWord().compareTo(right.get(rightIndex).getWord()) < 0) {
                    whole.set(wholeIndex, left.get(leftIndex));
                    leftIndex++;
                }
                else {
                    whole.set(wholeIndex, right.get(rightIndex));
                    rightIndex++;
                }
                wholeIndex++;
            }
        }

        if(sortType.equals("scarcity")) {
            while (leftIndex < left.size() && rightIndex < right.size()) {
                if (left.get(leftIndex).getOccurences() < right.get(rightIndex).getOccurences()) {
                    whole.set(wholeIndex, left.get(leftIndex));
                    leftIndex++;
                }
                else if(left.get(leftIndex).getOccurences() == right.get(rightIndex).getOccurences()) {
                    if(left.get(leftIndex).getWord().compareTo(right.get(rightIndex).getWord()) < 0) {
                        whole.set(wholeIndex, left.get(leftIndex));
                        leftIndex++;
                    }
                    else {
                        whole.set(wholeIndex, right.get(rightIndex));
                        rightIndex++;
                    }
                }
                else {
                    whole.set(wholeIndex, right.get(rightIndex));
                    rightIndex++;
                }
                wholeIndex++;
            }
        }

        if(sortType.equals("frequency")) {
            while (leftIndex < left.size() && rightIndex < right.size()) {
                if (left.get(leftIndex).getOccurences() > right.get(rightIndex).getOccurences()) {
                    whole.set(wholeIndex, left.get(leftIndex));
                    leftIndex++;
                }
                else if(left.get(leftIndex).getOccurences() == right.get(rightIndex).getOccurences()) {
                    if(left.get(leftIndex).getWord().compareTo(right.get(rightIndex).getWord()) < 0) {
                        whole.set(wholeIndex, left.get(leftIndex));
                        leftIndex++;
                    }
                    else {
                        whole.set(wholeIndex, right.get(rightIndex));
                        rightIndex++;
                    }
                }
                else {
                    whole.set(wholeIndex, right.get(rightIndex));
                    rightIndex++;
                }
                wholeIndex++;
            }
        }

        ArrayList<Word> rest;
        int restIndex;
        if (leftIndex >= left.size()) {
            rest = right;
            restIndex = rightIndex;
        } else {
            rest = left;
            restIndex = leftIndex;
        }

        for (int i = restIndex; i < rest.size(); i++) {
            whole.set(wholeIndex, rest.get(i));
            wholeIndex++;
        }
    }

    /**
     * Prints out the sorted array list with a specified number
     * @param num number of objects to be printed
     */
    public void print(int num) {
        if(num > wordList.size()) {
            num = wordList.size();
        }
        for (int i = 0; i < num; i++) {
            System.out.println(wordList.get(i).getWord() + " " + wordList.get(i).getOccurences());
        }
    }

    /**
     * Prints out the entire sorted array list.
     */
    public void print() {
        for (int i = 0; i < wordList.size(); i++) {
            System.out.println(wordList.get(i).getWord() + " " + wordList.get(i).getOccurences());
        }
    }
}
