package com.agrauberg;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DuplicateWords {

    public static void main(String[] args) {
        String regex = "\\b(\\w+)(\\s+\\1\\b)+";
        Pattern p = Pattern.compile(regex);
        try (Scanner in = new Scanner(System.in)) {
            while (in.hasNext()) {
                String input = in.nextLine();
                Matcher m = p.matcher(input);

                // Check for subsequences of input that match the compiled pattern
                while (m.find()) {
                    input = input.replaceAll(m.group(0), m.group(1));
                }

                // Prints the modified sentence.
                System.out.println(input);
            }
        }
    }
}
