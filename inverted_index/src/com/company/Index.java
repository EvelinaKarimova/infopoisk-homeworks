package com.company;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Index {

    static final int N = 169;

    static protected Map<String, Set<Integer>> createIndexFile(String inputFile, String outputFile) {
        HashMap<String, Set<Integer>> map = createIndex(inputFile);
        Object[] set = map.keySet().toArray();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
            for (Object s : set) {
                writer.write(s + " ");
                for (Integer j : map.get(s.toString())) {
                    writer.write(j + " ");
                }
                writer.write("\n");
            }
            writer.close();
            return map;
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    static private HashMap<String, Set<Integer>> createIndex(String inputFile) {
        HashMap<String, Set<Integer>> map = new HashMap<>();
        String s;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            while (reader.ready()) {
                s = reader.readLine();
                Set<Integer> array = findUsages(s);
                if (!array.isEmpty()) map.put(s, array);
            }
            reader.close();
            return map;
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    static private Set<Integer> findUsages(String s) {
        BufferedReader reader = null;
        String currentLine;
        Matcher matcher;
        Pattern pattern = Pattern.compile(s);
        Set<Integer> array = new HashSet<>();
        for (int i = 0; i < N; i++) {
            try {
                reader = new BufferedReader(new FileReader("D:\\прог\\java\\infopoisk-homeworks\\inverted_index\\src\\files\\file" + i + ".txt"));
                while (reader.ready()) {
                    currentLine = reader.readLine().toLowerCase();
                    matcher = pattern.matcher(currentLine);
                    if (matcher.find()) {
                        array.add(i);
                    }
                }
            } catch (IOException e) {
                i++;
            }
            try {
                if (reader != null) reader.close();
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
        }
        return array;
    }
}
