package com.luxoft.oleksandr_shevchenko.io;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileAnalyzerTest {
    String path = "src/main/resources/story.txt";
    String word = "duck";
    byte[] content;
    byte[] wordArray = word.getBytes();

    {
        try {
            content = FileAnalyzer.readContent(path).toLowerCase().getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testCountOfSequence(){
        assertEquals(23, FileAnalyzer.countOfSequence(wordArray, content));
    }
}
