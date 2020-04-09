package com.bs.domain.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;


/**
 * 
 * @author J. Milton Chambi M.
 */
public class FileUtil {

    private static Logger log = Logger.getLogger(FileUtil.class.getName());

    public static InputStream getInputStreamResource(String fileName) {
        InputStream inputStream = null;
        try {
            File file = new File(getJbossServerConfigDir() + File.separator + fileName);

            if (file.exists()) {
                inputStream = new FileInputStream(file);
            } else {
                inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            }
        } catch (FileNotFoundException e) {
            log.log(Level.SEVERE, "Error occurred reading the File [" + fileName + "], " + e.getMessage(), e);
        }
        return inputStream;
    }

    public static String getJbossServerConfigDir() {
        return System.getProperty("jboss.server.config.dir");
    }

    public static String getJbossServerDataDir() {
        return System.getProperty("jboss.server.data.dir");
    }

    public static boolean delete(String pathname) {
        File file = new File(pathname);
        return file.delete();
    }

    public static boolean delete(List<String> lstFilesPath) {
        for (String pathname : lstFilesPath) {
            if (!delete(pathname)) {
                return false;
            }
        }
        return true;
    }

    public static long length(String pathname) {
        File file = new File(pathname);
        return file.length();
    }

    public static boolean exists(String path) {
        File file = new File(path);
        return file.exists();
    }

    public static void writeToFile(byte[] bytes, String fullpath) {
        OutputStream outstream = null;
        try {
            outstream = new FileOutputStream(new File(fullpath));
            InputStream is = new BufferedInputStream(new ByteArrayInputStream(bytes));
            byte[] buffer = new byte[4096];
            int len;
            while ((len = is.read(buffer)) > 0) {
                outstream.write(buffer, 0, len);
            }   outstream.close(); // in a finally block of course
            is.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                outstream.close();
            } catch (IOException ex) {
                Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static String readContentHTMLFile(InputStream resourceAsStream) {
        StringBuilder contentBuilder = new StringBuilder();
        try {
            return IOUtils.toString(resourceAsStream,"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static boolean duplicate(String source, String target) {
        try {
            File targetFile = new File(target);
            File sourceFile = new File(source);
            FileUtils.copyFile(sourceFile, targetFile);
            return true;
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error al crear copia de archivo: source: " + source + ", target: " + target, e);
            return false;
        }
    }
    
}
