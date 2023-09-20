package com.agrauberg.exercises;

import com.agrauberg.exercises.TagContentExtractor;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TagContentExtractorTest {
    @ParameterizedTest
    @MethodSource("testCasesProvider")
    void testMethod(String input, String expectedOutput) throws IOException {
        // Set up input and output streams
        try (InputStream inputStream = new ByteArrayInputStream(input.getBytes());
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            PrintStream printStream = new PrintStream(outputStream);
            // Redirect System.in and System.out
            System.setIn(inputStream);
            System.setOut(printStream);

            // Run the method
            TagContentExtractor.main(new String[]{});

            // Assert the output
            assertEquals(expectedOutput, outputStream.toString());
        }
    }

    private static Stream<Arguments> testCasesProvider() {
        return Stream.of(
                Arguments.of("1\n<tag>First test case</tag>", "First test case"),
                Arguments.of("1\n<invalid_tag>Second test case</invalid_tag_close>", "None")
                // Add more test cases as needed
        );
    }
}