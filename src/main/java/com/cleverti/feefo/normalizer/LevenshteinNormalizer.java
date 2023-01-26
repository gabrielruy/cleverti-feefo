package com.cleverti.feefo.normalizer;

import com.cleverti.feefo.calculator.LevenshteinDistance;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class LevenshteinNormalizer {

    // List of strings to compare and normalize words
    private static final List<String> normalizedWords = List.of("Architect", "Software Engineer", "Quantity Surveyor", "Accountant");

    String normalize(String string) {

        // If the received string is null, the application will return null
        if (string == null) {
            System.out.println("null");
            return null;
        }

        Double lowerDistance = Double.MAX_VALUE;
        String normalizedTitle = "";

        // Calculating the levenshtein distance, smaller it is, closer to the normalized word
        // If there is no correspondence, the levenshtein distance will be 1.0
        // In this situation, the input word will be kept
        for (String normalizedWord : normalizedWords) {
            Integer distance = LevenshteinDistance.calculate(normalizedWord, string);
            Double levenshteinDistance = distance.doubleValue() /
                    Math.max(normalizedWord.length(), string.length());
            if (!levenshteinDistance.equals(1.0) && levenshteinDistance < lowerDistance) {
                lowerDistance = levenshteinDistance;
                normalizedTitle = normalizedWord;
            }
        }

        // Returning the received string if none of the characters matches with the characters from the normalized words
        if (lowerDistance == Double.MAX_VALUE) {
            System.out.println(string);
            return string;
        }

        System.out.println(normalizedTitle);
        return normalizedTitle;
    }
}
