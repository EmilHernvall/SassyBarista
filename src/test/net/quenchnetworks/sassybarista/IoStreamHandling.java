package net.quenchnetworks.sassybarista;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;

public class IoStreamHandling {

    public static void closeQuietly(Reader reader) {
        if (reader == null) {
            return;
        }

        try {
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException("Could not close stream (See Cause)", e);
        }
    }

    public static void closeQuietly(OutputStream out) {
        if (out == null) {
            return;
        }

        try {
            out.close();
        } catch (IOException e) {
            throw new RuntimeException("Could not close stream (See Cause)", e);
        }
    }

    public static String readFileAsString(File file, String charsetName) {
        try {
            return readFully(new FileInputStream(file), charsetName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static String readFully(InputStream inputStream, String charsetName) {

        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, charsetName));
            char[] charBuffer = new char[512];
            int bytesRead = -1;
            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                stringBuilder.append(charBuffer, 0, bytesRead);
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        } finally {
            closeQuietly(bufferedReader);
        }
        return stringBuilder.toString();
    }


}