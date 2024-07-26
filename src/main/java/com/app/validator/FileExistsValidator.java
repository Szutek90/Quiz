package com.app.validator;

import java.nio.file.Files;
import java.nio.file.Paths;

public interface FileExistsValidator {
    public static void fileExists(String fileName) {
        if (!Files.exists(Paths.get(fileName))) {
            try {
                Files.createFile(Paths.get(fileName));
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
