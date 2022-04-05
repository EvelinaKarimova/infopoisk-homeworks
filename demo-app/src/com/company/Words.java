package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Words {


    private static Set<String> allWords  = new HashSet<>();;
    private ArrayList<String> stopWords;
    private int count;

    public Words() {
        this.stopWords = generateStopWordsList();
        count = 0;
    }

    private static ArrayList<String> generateStopWordsList() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("D:\\прог\\java\\infopoisk-homeworks\\vector_search\\src\\stop_words_russian.txt"));
            String str;
            ArrayList<String> stopWords = new ArrayList<>();
            while (reader.ready()) {
                str = reader.readLine();
                stopWords.add(str);
            }
            return stopWords;
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    protected void createWordsFile(int num) {
        BufferedReader reader;
        ArrayList<String> words = new ArrayList<>();
        String currentLine;
        Matcher matcher;
        Pattern pattern = Pattern.compile("[а-я]+");
        try {
            reader = new BufferedReader(new FileReader("D:\\прог\\java\\infopoisk-homeworks\\vector_search\\src\\downloaded_files\\file" + num + ".txt"));
            while (reader.ready()) {
                currentLine = reader.readLine().toLowerCase();
                matcher = pattern.matcher(currentLine);
                while (matcher.find()) {
                    words.add(matcher.group());
                }
            }
            reader.close();
            if (words.size() > 0) {
                words.removeAll(stopWords);
                allWords.addAll(words);
                BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\прог\\java\\infopoisk-homeworks\\vector_search\\src\\files\\file" + count + ".txt"));
                count++;
                for (String s : words) {
                    writer.write(s + "\n");
                }
                writer.close();
            }
        } catch (IOException ignored) {

        }
    }

    public void createAllWordsFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\прог\\java\\infopoisk-homeworks\\vector_search\\src\\allWords.txt"));
            for (String s : allWords) {
                writer.write(s + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static Set<String> getAllWords() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("D:\\прог\\java\\infopoisk-homeworks\\demo-app\\src\\resources\\allWords.txt"));
            String currentLine = null;
            while (reader.ready()) {
                currentLine = reader.readLine();
                allWords.add(currentLine);
            }
            reader.close();
            return allWords;
        } catch (IOException e) {
            throw  new IllegalArgumentException(e);
        }
    }
}
