package com.company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Saving {

    static int N = 168;
    static int actN = 27;

    public static void main(String[] args) {
        try {
            Words words = new Words();
            for (int i = 0; i <= N; i++) words.createWordsFile(i);
            words.createAllWordsFile();
            ArrayList<String> allWordsArray = new ArrayList<>(words.getAllWords());
            int numOfAllWords = allWordsArray.size();
            TFCounter tfCounter = new TFCounter(actN);
            StringBuilder builder = new StringBuilder();
            String str;
            BufferedWriter writer;
            for (int i = 0; i <= actN; i++) {
                for (int j = 0; j < numOfAllWords; j++) {
                    builder.append(tfCounter.frequency("D:\\прог\\java\\infopoisk-homeworks\\demo-app\\src\\files\\file" + i + ".txt", allWordsArray.get(j))).append(" ");
                }
                str = builder.toString();
                writer = new BufferedWriter(new FileWriter("D:\\прог\\java\\infopoisk-homeworks\\demo-app\\src\\vectors\\file" + i + ".txt"));
                writer.write(str);
                writer.close();
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

    }
}
