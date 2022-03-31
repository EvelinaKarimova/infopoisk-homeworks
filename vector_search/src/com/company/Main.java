package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    static int N = 168;
    static int actN = 27;

    public static void main(String[] args) {
        Words words = new Words();
        for (int i = 0; i <= N; i++) words.createWordsFile(i);
        words.createAllWordsFile();
        ArrayList<String> allWordsArray = new ArrayList<>(words.getAllWords());
        int numOfAllWords = allWordsArray.size();
        TFCounter tfCounter = new TFCounter(actN);
        double[][] vectors = new double[actN + 1][numOfAllWords];
        for (int i = 0; i <= actN; i++) {
            for (int j = 0; j < numOfAllWords; j++) {
                vectors[i][j] = tfCounter.frequency("D:\\прог\\java\\infopoisk-homeworks\\vector_search\\src\\files\\file" + i + ".txt", allWordsArray.get(j));
                System.out.print(vectors[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("Закончился подсчет векторов");
        Scanner scanner = new Scanner(System.in);
        String searchLine = scanner.nextLine().toLowerCase();
        ArrayList<String> search = new ArrayList<>(List.of(searchLine.split(" ")));
        search.retainAll(allWordsArray);

        double[] searchVector = new double[numOfAllWords];
        for (int i = 0; i < numOfAllWords; i++) {
            searchVector[i] = tfCounter.frequency(search, allWordsArray.get(i));
            System.out.print(searchVector[i]);
        }
        System.out.println();

        double[] similarity = new double[actN + 1];

        double aixbi = 0;
        double ai2 = 0;
        double bi2 = 0;
        double max = 0;
        int maxIndex = 0;
        for (int i = 0; i <= actN; i++) {
            for (int j = 0; j < numOfAllWords; j++) {
                aixbi += vectors[i][j] * searchVector[j];
                ai2 += vectors[i][j] * vectors[i][j];
                bi2 += searchVector[j] * searchVector[j];
            }
            similarity[i] = aixbi / (Math.sqrt(ai2) * Math.sqrt(bi2));
            System.out.print(similarity[i] + " ");
            if (similarity[i] > max) {
                max = similarity[i];
                maxIndex = i;
            }
        }
        System.out.println("\n" + maxIndex);
    }
}
