package com.company;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BooleanSearch {

    static protected Set<Integer> search(Set<String> args, String option, Map<String, Set<Integer>> map) {
        return switch (option) {
            case "and" -> searchAND(args, map);
            case "or" -> searchOR(args, map);
            case "not" -> searchNOT(args, map);
            default -> null;
        };

    }

    static private Set<Integer> searchAND(Set<String> args, Map<String, Set<Integer>> map) {
        Set<Integer> intersection = map.get(args.toArray()[0]);
        for (String s : args) {
            intersection.retainAll(map.get(s));
        }
        return intersection;
    }

    static private Set<Integer> searchOR(Set<String> args, Map<String, Set<Integer>> map) {
        Set<Integer> result = new HashSet<>();
        for (String s : args) {
            result.addAll(map.get(s));
        }
        return result;
    }

    static private Set<Integer> searchNOT(Set<String> args, Map<String, Set<Integer>> map) {
        Set<Integer> result = new HashSet<>();
        for (String s : map.keySet()) {
            result.addAll(map.get(s));
        }
        for (String s : args) {
            if (map.get(s) != null) {
                result.removeAll(map.get(s));
            }
        }
        return result;
    }

}
