package com.luxoft.oleksandr_shevchenko.io;

import java.io.*;

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
                copyFile(from, to);
            } else if (path.isDirectory()){
                copyFolder(from, to);
            }
        }

        public static void copyFile(String from, String to) throws IOException {
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

        public static void copyFolder(String from, String to) throws IOException {
            File pathFrom = new File(from);
            File pathTo = new File(to);
            pathTo.mkdir();

            StringBuilder fromBuilder = new StringBuilder(from);
            StringBuilder toBuilder = new StringBuilder(to);

            File[] files = pathFrom.listFiles();
            if (files.length != 0) {

                for (File file : files) {
                    fromBuilder.append("/").append(file.getName());
                    toBuilder.append("/").append(file.getName());
                    copy(fromBuilder.toString(), toBuilder.toString());
                }
            }
        }



//    public static void move(String from, String to) - метод по перемещению папок и файлов.
//    Параметр from - путь к файлу или папке, параметр to - путь к папке куда будет производиться копирование.
        public static void move(String from, String to) throws IOException {
            File pathFrom = new File(from);
            if (pathFrom.isFile()) {
                copyFile(from, to);
                pathFrom.delete();
            } else if (pathFrom.isDirectory()){
                copyFolder(from, to);
                deleteDirWithFiles(from);
                pathFrom.delete();
            }
        }

    public static void deleteDirWithFiles(String dir) {
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
