package net.kigawa.chunkgenerator.util.all;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class Util {
    public static String createString(int[] ints) {
        StringBuilder str = new StringBuilder(Integer.toString(ints[0]));
        for (int i = 1; i < ints.length; i++) {
            str.append(", ").append(ints[i]);
        }
        return str.toString();
    }

    public static <T> List<T> changeListType(List list, Class<T> type) {
        List<T> list1 = new ArrayList<>();
        for (Object o : list) {
            list1.add((T) o);
        }
        return list1;
    }

    public static int[] getIntegerArrangement(List<Integer> list) {
        int[] ints = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ints[i] = list.get(i);
        }
        return ints;
    }

    public static String[] getStringArrangement(List<String> list) {
        String[] strings = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            strings[i] = list.get(i);
        }
        return strings;
    }

    public static <T> List<T> getList(T[] o) {
        List<T> list = new ArrayList<>();
        Collections.addAll(list, o);
        return list;
    }

    public static void runCommand(String[] command, File dir) {
        dir.mkdirs();
        System.out.println("run jar...");
        String result;
        Process process = null;
        Runtime runtime = Runtime.getRuntime();
        try {
            process = runtime.exec(command, null, dir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("out put log...");
        InputStream inputStream = process.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        try {
            while ((result = bufferedReader.readLine()) != null) {
                System.out.println(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void download(URL url, File file, String name) throws IOException {
        String path = url.getPath();
        File file1 = new File(file, name);

        if (file1.exists()) {
            file1.delete();
        }

        Files.copy(url.openStream(), file1.toPath(), REPLACE_EXISTING);
    }
}
