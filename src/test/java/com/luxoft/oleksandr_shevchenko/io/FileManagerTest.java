package com.luxoft.oleksandr_shevchenko.io;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class FileManagerTest {

    @Test
    public void testCountFiles(){
        assertEquals(5, FileManager.countFiles("d:/JAVA/io/src/"));
    }

    @Test
    public void testCountDirs(){
        assertEquals(13, FileManager.countDirs("d:/JAVA/io/src/"));
    }

    @Test
    public void testCopyFile() throws IOException {
        String from = "d:/JAVA/io/CopyFrom/story.txt";
        String to = "d:/JAVA/io/CopyTo/story.txt";
        File fileFrom = new File(from);
        File fileTo = new File(to);
        assertTrue(fileFrom.isFile());
        assertFalse(fileTo.exists());
        FileManager.copy(from, to);
        assertTrue(fileTo.isFile());
        fileTo.delete();
    }

    @Test
    public void testCopyFolder() throws IOException {
        String from = "d:/JAVA/io/CopyFrom/story/";
        String to = "d:/JAVA/io/CopyTo/story/";
        File fileFrom = new File(from);
        File fileTo = new File(to);
        assertTrue(fileFrom.isDirectory());
        assertFalse(fileTo.exists());
        FileManager.copy(from, to);
        assertTrue(fileTo.isDirectory());
        fileTo.delete();
    }

    @Test
    public void testMoveFile() throws IOException {
        String from = "d:/JAVA/io/CopyFrom1/story.txt";
        String to = "d:/JAVA/io/CopyTo/story.txt";
        File fileFrom = new File(from);
        File fileTo = new File(to);
        assertTrue(fileFrom.isFile());
        assertFalse(fileTo.exists());
        FileManager.move(from, to);
        assertTrue(fileTo.exists());
        assertFalse(fileFrom.exists());
        fileTo.delete();
    }

    @Test
    public void testMoveFolder() throws IOException {
        String from = "d:/JAVA/io/CopyFrom1/story/";
        String to = "d:/JAVA/io/CopyTo/story/";
        File fileFrom = new File(from);
        File fileTo = new File(to);
        assertTrue(fileFrom.isDirectory());
        assertFalse(fileTo.exists());
        FileManager.move(from, to);
        assertTrue(fileTo.exists());
        assertFalse(fileFrom.exists());
        fileTo.delete();
    }
}
