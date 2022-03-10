package com.company;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokenization {

    static int N = 169;
    static Pattern pattern = Pattern.compile("[А-Я][а-я]+|[а-я][а-я]+");
    static Set<String> set = new HashSet<>();

    public Set<String> tokenization() {
        getTokensFromAllFiles("D:\\прог\\nlp\\src\\result\\words.txt");
        //getTokensFromAllFilesTokenEdition("D:\\прог\\nlp\\src\\result\\words(Token).txt", "!: qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM{}[]=1234567890/.-<>\"?;',*|()°#_@%^&—   \t\n«»№–+\\�$ '©’");
        return set;
    }

    public void getTokensFromAllFiles(String outputFile) {
        try {
            String currentLine;
            Matcher matcher;
            BufferedReader reader;
            File file;
            for (int i = 0; i < N; i++) {
                file = new File(("D:\\прог\\nlp\\src\\files\\file" + i + ".txt"));
                if (file.exists()) {
                    System.out.println(i);
                    reader = new BufferedReader(new FileReader(file));
                    while (reader.ready()) {
                        currentLine = reader.readLine();
                        matcher = pattern.matcher(currentLine);
                        while (matcher.find()) {
                            currentLine = matcher.group().toLowerCase();
                            set.add(currentLine);
                        }
                    }
                    reader.close();
                }
            }
            removeStopWords();
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
            for (String s : set) {
                if (!s.equals("")) writer.write(s + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void removeStopWords() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("D:\\прог\\nlp\\src\\result\\stop_words_russian.txt"));
            String str;
            ArrayList<String> stopWords = new ArrayList<>();
            while (reader.ready()) {
                str = reader.readLine();
                if (!str.isEmpty()) {
                    stopWords.add(str);
                }
            }
            ArrayList<String> allWords = new ArrayList<>(set);
            allWords.removeAll(stopWords);
            set = new HashSet<>(allWords);
        } catch (IOException e){
            throw new IllegalArgumentException(e);
        }
    }

    public void getTokensFromFile(String inputFile, String outputFile, String divider) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
            String currentLine;
            StringTokenizer tokenizer;

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            while (reader.ready()) {
                currentLine = reader.readLine();
                tokenizer = new StringTokenizer(currentLine, divider);
                while (tokenizer.hasMoreTokens()) {
                    writer.write(tokenizer.nextToken());
                    writer.write("\n");
                }
            }
            reader.close();
            writer.close();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void getTokensFromAllFilesTokenEdition(String outputFile, String divider) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
            String currentLine;
            StringTokenizer tokenizer;

            BufferedReader reader;
            File file;
            for (int i = 0; i < N; i++) {
                file = new File(("D:\\прог\\nlp\\src\\files\\file" + i + ".txt"));
                if (file.exists()) {
                    System.out.println(i);
                    reader = new BufferedReader(new FileReader(file));
                    while (reader.ready()) {
                        currentLine = reader.readLine();
                        tokenizer = new StringTokenizer(currentLine, divider);
                        while (tokenizer.hasMoreTokens()) {
                            writer.write(tokenizer.nextToken());
                            writer.write("\n");
                        }
                    }
                    reader.close();
                }
            }
            writer.close();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
