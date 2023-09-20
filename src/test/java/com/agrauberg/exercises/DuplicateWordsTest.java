package com.agrauberg.exercises;

import com.agrauberg.exercises.DuplicateWords;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class DuplicateWordsTest {

    @ParameterizedTest
    @CsvSource({
            "hello hello world world, hello world",
            "this is is a test, this is a test",
            "programming programming is fun, programming is fun"
    })
    public void testMethod(String input, String expectedOutput) {
        // Set up the input stream
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Redirect the output to capture it
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Run the method
        DuplicateWords.main(new String[]{});

        // Restore System.in and System.out
        System.setIn(System.in);
        System.setOut(System.out);

        // Capture and trim the actual output
        String actualOutput = outputStream.toString().trim();

        // Assert the output
        assertEquals(expectedOutput, actualOutput);
    }
}