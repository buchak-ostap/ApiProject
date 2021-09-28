package com.apiProject.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.apiProject.exception.InternalTestGeneralException;
import com.apiProject.model.jackson.JSONObjectHolder;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class FileUtils {
    public FileUtils() {
    }

    public static List<String> retrieveJsonFilenamesFromJarOrFolder(String folderPath) throws IOException, URISyntaxException {
        List<String> list = new ArrayList();
        File jarFile = new File(folderPath);
        if (jarFile.isFile()) {
            JarFile jar = new JarFile(jarFile);

            try {
                Enumeration entries = jar.entries();

                while(entries.hasMoreElements()) {
                    String name = ((JarEntry)entries.nextElement()).getName();
                    if (name.startsWith(folderPath) && name.endsWith(".json")) {
                        list.add(name);
                    }
                }
            } catch (Throwable var10) {
                try {
                    jar.close();
                } catch (Throwable var9) {
                    var10.addSuppressed(var9);
                }

                throw var10;
            }

            jar.close();
        } else {
            URL url = ClassLoader.getPlatformClassLoader().getResource("/" + folderPath);
            if (url != null) {
                File apps = new File(url.toURI());
                File[] var13 = apps.listFiles();
                int var6 = var13.length;

                for(int var7 = 0; var7 < var6; ++var7) {
                    File file = var13[var7];
                    if (file.getName().endsWith(".json")) {
                        list.add(file.getName());
                    }
                }
            }
        }

        return list;
    }

    public static Object[][] getListOfFilenamesByPath(String folderPath) throws IOException, URISyntaxException {
        List<String> jsonFilenames = retrieveJsonFilenamesFromJarOrFolder(folderPath);
        List<Object[]> list = new ArrayList();
        Iterator var3 = jsonFilenames.iterator();

        while(var3.hasNext()) {
            String jsonFilename = (String)var3.next();
            JSONObjectHolder jsonDTO = (JSONObjectHolder)(new ObjectMapper()).readValue(FileUtils.class.getResourceAsStream("/" + folderPath + jsonFilename), JSONObjectHolder.class);
            list.add(new Object[]{jsonDTO.getTitle(), jsonDTO.getData()});
        }

        return (Object[][])list.toArray(new Object[list.size()][]);
    }

    public static String getTextFromResourceFile(String filePath) {
        InputStream resourceAsStream = FileUtils.class.getResourceAsStream(filePath);

        String var3;
        try {
            String text = IOUtils.toString(resourceAsStream, StandardCharsets.UTF_8);
            if (text == null) {
                throw new InternalTestGeneralException("Could not find file by path: " + filePath);
            }

            var3 = text;
        } catch (IOException var7) {
            throw new InternalTestGeneralException("Could not find file by path: " + filePath, var7);
        } finally {
            IOUtils.closeQuietly(resourceAsStream);
        }

        return var3;
    }
}