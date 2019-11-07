package com.mz.sapsync.filewalker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileWalkerTest {
    private static final String TEST_RESOURCES = "src/test/resources/";
    private static final String FLAT_DIR = TEST_RESOURCES + "flatdir";
    private static final String RECURSIVE_DIR = TEST_RESOURCES + "recdir";
    private final String EMPTY_DIR = TEST_RESOURCES + "emptydir";
    FileWalker fileWalker;
    @BeforeEach
    public void beforeEach(){
        fileWalker=new FileWalker();
    }

    @Test
    public void getEmptyDirectory(){
        assertTrue(fileWalker.listDirectory(EMPTY_DIR).isEmpty());
    }
    
    @Test
    public void getFlatDirectoryFiles(){
        List<String> files = fileWalker.listDirectory(FLAT_DIR);
        assertEquals(2,files.size());
    }

    @Test
    public void getRecursiveDirectory(){
        List<String> files = fileWalker.listDirectory(RECURSIVE_DIR);
        assertEquals(4, files.size());
        assertTrue(files.contains(RECURSIVE_DIR + "/testfile1.txt"));
        assertTrue(files.contains(RECURSIVE_DIR + "/testfile2.js"));
        assertTrue(files.contains(RECURSIVE_DIR + "/innerDir/testfile1.txt"));
        assertTrue(files.contains(RECURSIVE_DIR + "/innerDir/testfile2.js"));
    }

    @Test
    public void fileWalkerPopulatesCommands(){
        // TODO implement missing
        FileFoundAction fileFoundTestAction = Mockito.mock(FileFoundAction);
        fileWalker.addFileFoundAction(fileFoundTestAction);
        Mockito.atLeast(4).verify(fileFoundTestAction.run());
    }

}