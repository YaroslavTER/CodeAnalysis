package com.company;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Vector;

/**
 * Created by pc on 5/25/2016.
 */
public class SearchFolders {
    private Vector<FileData> file_dirs = new Vector<FileData>();
    private int NOM, NOC, LOC, NELOC;
    private double structing_methods, structing_methods_, structing_classes;

    public SearchFolders(){
    }

    public int get_nom(){
        return NOM;
    }

    public int get_noc(){
        return NOC;
    }

    public int get_loc(){
        return LOC;
    }

    public int get_neloc(){
        return NELOC;
    }

    public double structing_methods(){
        return structing_methods;
    }

    public double structing_methods_(){
        return structing_methods_;
    }

    public double structing_classes(){
        return structing_classes;
    }

    public void finder(String dir_name){
        File directory = new File(dir_name);
        File[] fList = directory.listFiles();
        String name = "";
        System.out.println("Finding...");
        for (File file : fList) {
            if (file.isFile()) {
                if(file.getName().endsWith(".java")) {
                    System.out.println(file.getName());
                    file_dirs.add(new FileData(dir_name + "\\" + file.getName()));
                }
            } else if (file.isDirectory()) {
                System.out.println(file.getName());
                name = file.getName();
            }
        }
        dir_name += "\\" + name;
        if(name != "")
            finder(dir_name);
        System.out.println("Finding is over.");
    }

    public void finder2(String dir_name){
        try {
            Path startPath = Paths.get(dir_name);
            Files.walkFileTree(startPath, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir,
                                                         BasicFileAttributes attrs) {
                    System.out.println("Dir: " + dir.toString());
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    System.out.println("File: " + file.toString());
                    if(file.toString().endsWith(".java"))
                        file_dirs.add(new FileData(file.toString()));
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException e) {
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void analyze(String dir_name) throws IOException {
        finder2(dir_name);
        System.out.println();
        for(int index = 0; index < file_dirs.size(); index++){
            Analysis analysis = new Analysis();
            analysis.read_file(file_dirs.elementAt(index).get_dir_name());

            file_dirs.elementAt(index).set_nom(analysis.calculate_methods());
            file_dirs.elementAt(index).set_loc(analysis.lines_of_code());
            file_dirs.elementAt(index).set_noc(analysis.number_of_classes());
            file_dirs.elementAt(index).set_neloc(analysis.non_empty_lines_of_code());

            NOM += file_dirs.elementAt(index).get_nom();
            NOC += file_dirs.elementAt(index).get_noc();
            LOC += file_dirs.elementAt(index).get_loc();
            NELOC += file_dirs.elementAt(index).get_neloc();

            structing_methods = (double)LOC/(double)NOM;
            structing_methods_ = (double)NELOC/(double)NOM;
            structing_classes = (double)NOM/(double)NOC;
        }
    }
}
