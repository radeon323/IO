package com.luxoft.oleksandr_shevchenko.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class FileAnalyzer {
    public static void main(String[] args) throws IOException {

        String path = args[0];
        String word = args[1];
//        String path = "story.txt";
//        String word = "duck";
        byte[] content = readContent(path).toLowerCase().getBytes();
        byte[] wordArray = word.getBytes();

        System.out.println("Number of entries of the word '" + word + "' in the file is " + countOfSequence(wordArray, content));
        System.out.println();
        printSentences(path, word);

    }

    static String readContent(String path) throws IOException {
        File pathToFile = new File(path);
        InputStream inputStream = new FileInputStream(pathToFile);
        int fileLength = (int) pathToFile.length();
        byte[] contentArray = new byte[fileLength];
        inputStream.read(contentArray);
        return new String(contentArray);
    }

    static int countOfSequence(byte[]sentence, byte[]array) {
        int result = 0;
        for (int i = 0; i < array.length; i++) {
            int count = 0;
            if (array[i] == sentence[count]) {
                for (int j = 0; j < sentence.length; j++) {
                    if (array[i + j] == sentence[j] && j == sentence.length - 1) {
                        result++;
                    } else if (array[i + j] != sentence[j]) {
                        break;
                    }
                }
            }
        }
        return result;
    }

    static void printSentences(String path, String word) throws IOException {
        String[] str = readContent(path).split("[\\.\\!\\?]");
        for (String s : str) {
            if (s.contains(word)) {
                System.out.println(s);
            }
        }
    }



}
