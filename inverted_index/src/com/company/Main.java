package com.company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Map<String, Set<Integer>> map = Index.createIndexFile("D:\\прог\\java\\infopoisk-homeworks\\inverted_index\\src\\result\\words.txt", "D:\\прог\\java\\infopoisk-homeworks\\inverted_index\\src\\result\\index.txt");
        String[] array = {"профессионально", "шгпу"};
        Set<String> set = new HashSet<>(Arrays.asList(array));
        String[] options = {"and", "or", "not"};
        Set<Integer> result = BooleanSearch.search(set, options[0], map);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\прог\\java\\infopoisk-homeworks\\inverted_index\\src\\result\\search.txt"));
            writer.write("{0,5,6} & {0,2,5,6,8} \n");
            for (Integer i : result) {
                writer.write(i + " ");
            }
            writer.write("\n");
            array = new String[]{"профессионально", "шгпу", "артиллерия", "путешествие", "отборный"};
            set = new HashSet<>(Arrays.asList(array));
            writer.write("{0,5,6} OR {0,2,5,6,8} OR {148} OR {130, 150} OR {38} \n");
            result = BooleanSearch.search(set, options[1], map);
            for (Integer i : result) {
                writer.write(i + " ");
            }
            writer.write("\n");
            writer.write("NOT {0,5,6} & {0,2,5,6,8} & {148} & {130, 150} & {38} \n");
            result = BooleanSearch.search(set, options[2], map);
            for (Integer i : result) {
                writer.write(i + " ");
            }
            writer.close();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
