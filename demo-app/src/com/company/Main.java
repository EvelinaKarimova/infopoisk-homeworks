package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static int N = 168;
    static int actN = 27;

    public static void main(String[] args) throws IOException {
        System.out.println("Введите поисковый запрос");
        Scanner scanner = new Scanner(System.in);
        String searchLine = scanner.nextLine().toLowerCase();
        ArrayList<String> search = new ArrayList<>(List.of(searchLine.split(" ")));
        ArrayList<String> allWords = new ArrayList<>(Words.getAllWords());
        search.retainAll(allWords);
        int numOfAllWords = allWords.size();
        TFCounter tfCounter = new TFCounter(N);
        double[] searchVector = new double[numOfAllWords];
        for (int i = 0; i < numOfAllWords; i++) {
            searchVector[i] = tfCounter.frequency(search, allWords.get(i));
            System.out.print(searchVector[i]);
        }
        System.out.println();

        double[] similarity = new double[actN + 1];

        double aixbi = 0;
        double ai2 = 0;
        double bi2 = 0;
        double max = 0;
        int maxIndex = 0;
        BufferedReader reader;
        String[] line;
        double d;
        for (int i = 0; i <= actN; i++) {
            reader = new BufferedReader(new FileReader("D:\\прог\\java\\infopoisk-homeworks\\demo-app\\src\\vectors\\file" + i + ".txt"));
            line = reader.readLine().split(" ");
            for (int j = 0; j < numOfAllWords; j++) {
                d = Double.parseDouble(line[j]);
                aixbi += d * searchVector[j];
                ai2 += d * d;
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
