package com.app.file_operator.impl;

import com.app.file_operator.FileOperator;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public abstract class AbstractFileOperator<T, U> implements FileOperator {
    final String filename;

    protected AbstractFileOperator(String filename) {
        this.filename = filename;
    }

    @Override
    public void save(String text) {
        try {
            Files.writeString(Paths.get(filename), text, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public abstract Map<T, U> readFile();
}
