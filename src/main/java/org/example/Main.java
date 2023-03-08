package org.example;

import java.io.File;
import java.sql.SQLOutput;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {

        Properties prop = System.getProperties();
        String dirCurent = prop.getProperty("user.dir");
        System.out.println("dir Cur " + dirCurent);
//        String root = dirCurent.substring(0,dirCurent.indexOf("\\"));
//
//        FileManager fm = new FileManager(dirCurent.substring(0,dirCurent.indexOf("\\")));
        FileManager fm = new FileManager("C:\\Java\\my_solution\\FileManager\\src\\main\\java");
        fm.runManager();


    }
}