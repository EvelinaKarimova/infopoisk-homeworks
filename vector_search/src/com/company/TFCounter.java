package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TFCounter {

    private int N;

    public TFCounter(int N) {
        this.N = N;
    }

    public double frequency(String inputFile, String word) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            String currentLine;
            int count = 0;
            while (reader.ready()) {
                currentLine = reader.readLine();
                if (currentLine.equals(word)) count++;
            }
            return ((double) count / N);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public double frequency(ArrayList<String> arrayList, String word) {
        int count = 0;
        for (String s : arrayList) {
            if (s.equals(word)) count++;
        }
        return ((double) count / N);
    }
}
