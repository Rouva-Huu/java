package org.example;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileFilter {
    private final List<String> inputFiles = new ArrayList<>();
    private String outputPath = ".";
    private String prefix = "";
    private boolean appendMode = false;
    private int statistics = 0;

    private final Statistics intStats = new Statistics();
    private final Statistics floatStats = new Statistics();
    private final Statistics stringStats = new Statistics();

    public void processArguments(String[] args) {
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-o":
                    outputPath = args[++i];
                    break;
                case "-p":
                    prefix = args[++i];
                    break;
                case "-a":
                    appendMode = true;
                    break;
                case "-s":
                    statistics = 1;
                    break;
                case "-f":
                    statistics = 2;
                    break;
                default:
                    inputFiles.add(args[i]);
            }
        }
    }

    public void processFiles() throws IOException {
        for (String file : inputFiles) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    processLine(line);
                }
            }
        }
        writeResults();
    }

    private void processLine(String line) {
        try {
            int intValue = Integer.parseInt(line);
            intStats.add(intValue);
        } catch (NumberFormatException e1) {
            try {
                double floatValue = Double.parseDouble(line);
                floatStats.add(floatValue);
            } catch (NumberFormatException e2) {
                stringStats.add(line);
            }
        }
    }

    private void writeResults() throws IOException {
        writeFile(prefix + "integers.txt", intStats.getValues());
        writeFile(prefix + "floats.txt", floatStats.getValues());
        writeFile(prefix + "strings.txt", stringStats.getValues());
    }

    private void writeFile(String fileName, List<?> values) throws IOException {
        if (values.isEmpty()) return;

        Path filePath = Paths.get(outputPath, fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile(), appendMode))) {
            for (Object value : values) {
                writer.write(value.toString());
                writer.newLine();
            }
        }
    }

    public void printStatistics() {
        if (statistics != 0) {
            System.out.println("Integers: " + intStats.getSummary(statistics));
            System.out.println("Floats: " + floatStats.getSummary(statistics));
            System.out.println("Strings: " + stringStats.getSummary(statistics));
        }
    }
}
