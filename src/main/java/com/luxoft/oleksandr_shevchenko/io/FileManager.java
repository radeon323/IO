package com.luxoft.oleksandr_shevchenko.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

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
        public static void copy(String from, String to) throws IOException {
            File pathFrom = new File(from);
            File pathTo = new File(to);
            if (pathFrom.isDirectory()) {
                if (!pathTo.exists()) {
                    Files.createDirectories(Paths.get(to));
                }
            }
            if (pathFrom.isFile()) {
                copyFile(from, to);
            } else if (pathFrom.isDirectory()){
                copyFolder(from, to);
            }
        }

        static void copyFile(String from, String to) throws IOException {

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

        static void copyFolder(String from, String to) throws IOException {
            File pathFrom = new File(from);
            File pathTo = new File(to);
            if (!pathTo.exists()) {
                pathTo.mkdir();
            }
            File[] files = pathFrom.listFiles();
            if (files.length != 0) {
                for (File file : files) {
                    File fileFrom = new File(pathFrom, file.getName());
                    File fileTo = new File(pathTo, file.getName());
                    copy(fileFrom.toString(), fileTo.toString());
                }
            }
        }

//    public static void move(String from, String to) - метод по перемещению папок и файлов.
//    Параметр from - путь к файлу или папке, параметр to - путь к папке куда будет производиться копирование.
        public static void move(String from, String to) throws IOException {
            File pathFrom = new File(from);
            if (pathFrom.isFile()) {
                new File(from).renameTo(new File(to));
            } else if (pathFrom.isDirectory()){
                copyFolder(from, to);
                deleteDirWithFiles(from);
                pathFrom.delete();
            }
        }

    static void deleteDirWithFiles(String dir) {
        File path = new File(dir);
        File[] files = path.listFiles();
        for (File file : files) {
            if(file.isDirectory()) {
                deleteDirWithFiles(file.getPath());
                file.delete();
            }
            file.delete();
        }
        path.delete();
    }


}
