package org.example;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.file.Files;
import java.util.Scanner;

public class FileManager {
    Scanner scr;
    String input;
    private String currentFolder;
    private String root;
    private String classLocation = this.getClass().getName().replace(".", "\\");

    public FileManager(String currentFolder) {
        this.root = currentFolder;
        this.currentFolder = currentFolder;
    }

    public void runManager() {
        scr = new Scanner(System.in);
        input = scr.nextLine();

        while (!input.equals(Commands.EXIT)) {
            String[] tokens = input.split(" ");
            String comand = tokens[0];
            System.out.println(comand);
            switch (comand) {
                case Commands.LIST_OF_FILES:
                    listOfFiles(false);
                    break;
                case Commands.LIST_OF_FILES_WITH_SIZE:
                    listOfFiles(true);
                    break;
                case Commands.COPY_FILE:
                    copyFile(tokens[1], tokens[2]);
                    break;
            }
            input = scr.nextLine();
        }


    }

    private void copyFile(String token, String token1) {

    }

    private void listOfFiles(boolean withSize) {
        File listOfFiles = new File(currentFolder);
        File[] files = listOfFiles.listFiles();
        System.out.println("========================================================================");
        for (File file : files) {
            if (file.isDirectory()) {
                if (withSize) {
                    System.out.println(file.getName() + "\\      :" + FileUtils.sizeOfDirectory(file));
                } else {
                    System.out.print(file.getName());
                }
            } else {
                if (withSize) {
                    System.out.println(file.getName() + "   : " + file.length());
                } else {
                    System.out.println(file.getName());
                }
            }
        }
        System.out.println();

    }
}
