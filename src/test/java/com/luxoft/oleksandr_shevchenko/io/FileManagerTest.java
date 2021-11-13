package com.luxoft.oleksandr_shevchenko.io;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class FileManagerTest {

    private final String fromFolder = "src/main/resources/MoveFrom/story";
    private final String toFolder = "src/main/resources/CopyTo/story";

    private final String fromFile = "src/main/resources/MoveFrom/story.txt";
    private final String toFile = "src/main/resources/CopyTo/story.txt";

    @Test
    public void testCountFiles(){
        assertEquals(3, FileManager.countFiles(fromFolder));
    }

    @Test
    public void testCountDirs(){
        assertEquals(2, FileManager.countDirs(fromFolder));
    }

    @Test
    public void testCopyFile() throws IOException {
        File fileFrom = new File(fromFile);
        File fileTo = new File(toFile);
        assertTrue(fileFrom.isFile());
        assertFalse(fileTo.exists());
        FileManager.copy(fromFile, toFile);
        assertTrue(fileTo.isFile());
        fileTo.delete();
    }

    @Test
    public void testCopyFolder() throws IOException {
        File fileFrom = new File(fromFolder);
        File fileTo = new File(toFolder);
        assertTrue(fileFrom.isDirectory());
        assertFalse(fileTo.exists());
        FileManager.copy(fromFolder, toFolder);
        assertTrue(fileTo.isDirectory());
        assertEquals(3, FileManager.countFiles(toFolder));
        assertEquals(2, FileManager.countDirs(toFolder));
        FileManager.deleteDirWithFiles(toFolder);
    }

    @Test
    public void testMoveFile() throws IOException {
        File fileFrom = new File(fromFile);
        File fileTo = new File(toFile);
        assertTrue(fileFrom.isFile());
        assertFalse(fileTo.exists());
        FileManager.move(fromFile, toFile);
        assertTrue(fileTo.exists());
        assertFalse(fileFrom.exists());
        FileManager.move(toFile, fromFile);
    }

    @Test
    public void testMoveFolder() throws IOException {
        File fileFrom = new File(fromFolder);
        File fileTo = new File(toFolder);
        assertTrue(fileFrom.isDirectory());
        assertFalse(fileTo.exists());
        FileManager.move(fromFolder, toFolder);
        assertTrue(fileTo.exists());
        assertFalse(fileFrom.exists());
        assertEquals(3, FileManager.countFiles(toFolder));
        assertEquals(2, FileManager.countDirs(toFolder));
        FileManager.move(toFolder, fromFolder);
    }
}
