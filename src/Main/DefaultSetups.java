package Main;

import javax.swing.*;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class DefaultSetups {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(DefaultSetups.getConfigNames()));
        ;
    }
    public static String[] getConfigNames() {
        ArrayList<String> result = new ArrayList<>();
        File[] files = new File("/Users/nico/Desktop/GameOfLife/src/Main/Configs/").listFiles();
        if (files==null) {
            return new String[]{""};
        }
        for (File file : files) {
            if (file.isFile()) {
                result.add(file.getName());
            }
        }

        String[] array = new String[result.size()+1];
        array[0] = "";
        int i = 1;
        for (String name : result) {
            array[i] = name;
            i++;
        }
        return array;
    }
    public static HashSet<Cell> getConfig(String name) {
        String filePath = "src/Main/Configs/" + name;
        HashSet<Cell> cells = new HashSet<>();
        if (name.isEmpty()) {
            return cells;
        }

        File folder = new File("../Main/Configs");


        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split(" ");
                int x = Integer.parseInt(words[0]);
                int y = Integer.parseInt(words[1]);
                cells.add(new Cell(x,y));
                // Process the line
                System.out.println(line);
            }

        } catch (IOException e) {
            System.out.println(filePath);
            e.printStackTrace();
        }
        return cells;
    }
    public static void saveConfig(HashSet<Cell> cells, String saveName) throws IOException {
        FileWriter myWriter = new FileWriter("Configs/"+saveName);
        for (Cell cell : cells) {
            myWriter.write(cell.toString());
            myWriter.write("\n");
        }
        myWriter.close();
        System.out.println("Successfully wrote to the file.");


    }
}
