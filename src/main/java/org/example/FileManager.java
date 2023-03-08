package org.example;

import org.apache.commons.io.FileUtils;

import java.io.*;
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
            switch (comand) {
                case Commands.LIST_OF_FILES:
                    listOfFiles(false);
                    break;
                case Commands.LIST_OF_FILES_WITH_SIZE:
                    listOfFiles(true);
                    break;
                case Commands.COPY_FILE:
                    String sourceFileName = tokens[1];
                    String destFileName = tokens[2];
                    copyFile(sourceFileName, destFileName);
                    break;
                case Commands.CREATE_FILE:
                    String newFile = tokens[1];
                    newFile(newFile);
                    break;
                case Commands.FILE_CONTENT:
                    String file = tokens[1];
                    readFile(file);
                    break;
                case Commands.CHANGE_DIRECTORY:
                    String newDir = tokens[1];
                    changeDir(newDir);
                    break;
            }
            input = scr.nextLine();
        }


    }

    private void changeDir(String folder) {
        if (folder.equals("\\")){
            currentFolder = root;
        } else if (folder.equals("..")) {
            int startLastFolderPosition = currentFolder.lastIndexOf("\\");
            currentFolder = currentFolder.substring(0,startLastFolderPosition);
        } else {
            File newPath = new File (currentFolder + "\\" + folder);
            if (newPath.isDirectory())
            currentFolder = newPath.toString();
        else System.out.println("Такой папки не существует");
        }
    }

    private void readFile(String name) {
        File file = new File(currentFolder + "\\" + name);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            System.out.println("Проблемы с открытием файла");
        }
        String line;
        try {
            System.out.println("--------------------------");
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Проблемы с чтением файла");

        }
    }

    private void newFile(String fileName) {
        File newFile = new File(fileName);
        try {
            newFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Не удалось создать файл");
        }

    }

    private void copyFile(String sourceFileName, String destFileName) {
        File sorce = new File(this.root + "\\" + sourceFileName);
        File dest = new File(this.root + "\\" + destFileName);
        System.out.println(sorce);
        System.out.println(dest);
        try {
            FileUtils.copyFile(sorce, dest);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Не получилось скопировать файл");
        }


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
