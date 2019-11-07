package com.mz.sapsync.filewalker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileWalker {

    public List<String> listDirectory(String path) {
        List<String> result = null;
        try (Stream<Path> walk = Files.walk(Paths.get(path))) {

            result = walk.filter(Files::isRegularFile)
                    .map(Path::toString).collect(Collectors.toList());

            result.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
