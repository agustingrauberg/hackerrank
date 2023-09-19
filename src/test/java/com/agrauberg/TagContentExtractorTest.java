package com.agrauberg;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TagContentExtractorTest {

    @BeforeEach
    public void setUp() {
        TagContentExtractor.tags.clear();
        TagContentExtractor.phrases.clear();
    }

    @ParameterizedTest
    @MethodSource("testCases")
    void testExtraction(String input, String expectedOutput) {
//        assertEquals(expectedOutput, TagContentExtractor.extract(input));
    }

    private static Stream<Object[]> testCases() {
        return Stream.of(
                new Object[]{"<a>Hello</a><b>World</b>", "Hello\r\nWorld\r\n"},
                new Object[]{"", ""},
                new Object[]{"This is a test.", "This is a test."},
                new Object[]{"This is a </b>test.", "This is a Noneb>test.\r\n"},
                new Object[]{"<a><b>Inner</b></a>", "Inner\r\n"},
                new Object[]{"<a>TagA</a><b>TagB</b>", "TagA\r\nTagB\r\n"},
                new Object[]{"This is a </a>test.", "This is a Nonea>test."},
                new Object[]{"<a></a><b></b>", "\r\n\r\n"},
                new Object[]{"<a href=\"https://example.com\">Link</a>", "Link\r\n"}
        );
    }
}
