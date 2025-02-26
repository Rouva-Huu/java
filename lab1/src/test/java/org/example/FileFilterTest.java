package org.example;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class FileFilterTest {
    @Test
    public void testFileFilter() throws IOException {
        FileFilter filter = new FileFilter();
        filter.processArguments(new String[]{"-o", "src/test/", "-p", "test_", "src/test/resources/test1.txt", "src/test/resources/test2.txt"});
        filter.processFiles();
        filter.printStatistics();

        assertTrue(Files.exists(Path.of("src/test/test_integers.txt")));
        assertTrue(Files.exists(Path.of("src/test/test_floats.txt")));
        assertTrue(Files.exists(Path.of("src/test/test_strings.txt")));
    }
}