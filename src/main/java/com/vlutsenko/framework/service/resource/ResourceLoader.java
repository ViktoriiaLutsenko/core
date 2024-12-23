package com.vlutsenko.framework.service.resource;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class ResourceLoader {

    public static InputStream getResources(String path) throws IOException {
        InputStream stream = ResourceLoader.class.getClassLoader().getResourceAsStream(path);
        return (stream != null)
            ? stream
            : Files.newInputStream(Path.of(path));
    }
}
