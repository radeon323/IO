package com.luxoft.oleksandr_shevchenko.io;

import java.io.*;
import java.util.StringJoiner;

public class FileManager {

// public static int countFiles(String path) - принимает путь к папке,
// возвращает количество файлов в папке и всех подпапках по пути
        public static int countFiles(String path) {
            File pathFile = new File(path);
            File[] pathFileArray = pathFile.listFiles();
            int count = 0;
            for (File fileOrDir : pathFileArray) {
                if(fileOrDir.isFile()) {
                    count ++;
                } else {
                    count += countFiles(fileOrDir.getPath());
                }
            }
            return count;
        }

// public static int countDirs(String path) - принимает путь к папке,
// возвращает количество папок в папке и всех подпапках по пути
        public static int countDirs(String path) {
            File pathDir = new File(path);
            File[] pathDirArray = pathDir.listFiles();
            int count = 0;
            for (File fileOrDir : pathDirArray) {
                if(fileOrDir.isDirectory()) {
                    count++;
                    count += countDirs(fileOrDir.getPath());
                }
            }
            return count;
        }

//    public static void copy(String from, String to) - метод по копированию папок и файлов.
//    Параметр from - путь к файлу или папке, параметр to - путь к папке куда будет производиться копирование.
    // працює тільки з першим рівнем вкладення, копіює вкладені файли і папки. Треба подумати над створенням імен папок і файлів для правильного копіювання
        public static void copy(String from, String to) throws IOException {
            File path = new File(from);
            if (path.isFile()) {
                FileInputStream fileInputStream = new FileInputStream(from);
                FileOutputStream fileOutputStream = new FileOutputStream(to);

                int bufSize;
                byte[] buf = new byte[512];
                while ((bufSize = fileInputStream.read(buf)) > 0) {
                    fileOutputStream.write(buf, 0, bufSize);
                }
                fileInputStream.close();
                fileOutputStream.close();

            }
            else if (path.isDirectory()){
                File pathDir = new File(to);
                pathDir.mkdir();

                File[] files = path.listFiles();
                if (files.length != 0) {
                    for (File file : files) {
                        copy(from + file.getName(), to + file.getName());
//                        from = from + file.getName();
//                        to = to + file.getName();
                    }
                }
            }
        }

//    public static void move(String from, String to) - метод по перемещению папок и файлов.
//    Параметр from - путь к файлу или папке, параметр to - путь к папке куда будет производиться копирование.
        public static void move(String from, String to) {

        }
}
