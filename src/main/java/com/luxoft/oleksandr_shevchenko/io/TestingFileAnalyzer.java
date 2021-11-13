package com.luxoft.oleksandr_shevchenko.io;

import java.io.*;

public class TestingFileAnalyzer {
    public static void main(String[] args) throws IOException {
//        File path = new File("test.log");
//        System.out.println(path.exists());
//        System.out.println(path.getAbsolutePath());
//        System.out.println(path.createNewFile());

//        File path = new File("C:\\Windows");
//        System.out.println(path.isDirectory());
//        File[] innerPaths = path.listFiles();
//        for (File innerPath : innerPaths) {
//            String type = innerPath.isFile() ? "FILE: " : "DIR: ";
//            System.out.println(type + innerPath);
//        }

//        OutputStream outputStream = new FileOutputStream("test.log");
//        char c = 'H';
//        byte value = (byte) c;
//        outputStream.write(value);
//        String restStr = "ello IO";
//        byte[] bytes = restStr.getBytes();
//        outputStream.write(bytes);

//        writeContent("test2.txt", "Hello java");

//        String content = readContent("test2.txt");
//        System.out.println(content);
        for (String s: args) {
            System.out.println(s);
        }
    }

    static String readContent(String path) throws IOException {
        File pathToFile = new File(path);
        InputStream inputStream = new FileInputStream(pathToFile);

        int fileLength = (int) pathToFile.length();
        byte[] contentArray = new byte[fileLength];
        inputStream.read(contentArray);
//        int index = 0;
//        int value;
//
//        while (true) {
//            value = inputStream.read();
//            if(value == -1) {
//                break;
//            }
//            contentArray[index] = (byte) value;
//            index++;
//        }
        return new String(contentArray);
    }

    static void writeContent(String path, String content) throws IOException {
        OutputStream outputStream = new FileOutputStream(path);
        byte[] bytes = content.getBytes();
        outputStream.write(bytes);
    }
}
