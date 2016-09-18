package com.company;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here
        String project_dir;
        Scanner scanner = new Scanner(System.in);
        SearchFolders searcher = new SearchFolders();
        //C:\\Users\\pc\\Desktop\\ЕМПІ\\Projects\\java-stratego-code-23
        //C:\\Users\\pc\\Desktop\\ЕМПІ\\Projects\\sudokuki-code-70 класів
        System.out.print("Enter direction of project: ");
        project_dir = scanner.nextLine();
        searcher.analyze(project_dir);
        System.out.println("Result of analysis:");
        System.out.println("LOC = " + searcher.get_loc() + ";");
        System.out.println("NELOC = " + searcher.get_neloc() + ";");
        System.out.println("NOM = " + searcher.get_nom() + ";");
        System.out.println("NOC = " + searcher.get_noc() + ";");
        System.out.println("Structing methods = " + searcher.structing_methods() + ";");
        System.out.println("Structing methods(with non empty lines) = " + searcher.structing_methods_() + ";");
        System.out.println("Structing classes = " + searcher.structing_classes() + ";");
    }
}
