package com.company;

import java.io.*;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by pc on 3/14/2016.
 */
public class Analysis {
    private Vector<String> lines = new Vector<String>();
    private Pattern pattern;
    private Matcher matcher;

    public Analysis(){

    }

    public void read_file(String filename) throws IOException {
        FileReader file_reader = new FileReader(filename);
        BufferedReader buff_reader = new BufferedReader(file_reader);
        String line = buff_reader.readLine();
        while(line != null){
            lines.add(line);
            line = buff_reader.readLine();
        }
    }

    public int calculate_methods(){
        int index = 0, method_counter = 0;
        while(index < lines.size()){
            if(is_method(lines.get(index)) || is_constructor(lines.get(index)))
                method_counter++;
            index++;
        }
        return method_counter;
    }

    private String remove_symb(char symbol, String line){
        String new_line = "";
        for(int index = 0; index < line.length(); index++){
            if(line.charAt(index) != symbol)
                new_line += line.charAt(index);
        }
        return new_line;
    }

    public int lines_of_code(){
        return lines.size();
    }

    public int non_empty_lines_of_code(){
        int number = 0;
        for(String line : lines){
            if(!is_empty(line))
                number++;
        }
        return number;
    }

    private boolean is_empty(String line) {
        boolean result = false;
        //pattern = Pattern.compile("\\s*[\\{\\}]+");
        pattern = Pattern.compile("\\s*");
        matcher = pattern.matcher(line);
        result = matcher.matches();
        if(line == null || line == "" || line.hashCode() == 0)
            result = true;
        return result;
    }

    public boolean is_method(String line){
        line = remove_symb('"',line);
        pattern = Pattern.compile("\\s*((static)|(public)|(private))?\\s*((static)|(public)|(private))+\\s*" +
                "[a-zA-Z_0-9]+\\s+[a-zA-Z_0-9]+\\s*\\(+[a-zA-Z_0-9\\s\\,\\[\\]]*\\)+\\{*[0-9a-z" +
                "A-Z_\\s\\.\\;\\(\\,\\)\\{\\}\\[\\]\\+\\-\\*\\/\\?\\$]*\\}*$");
        matcher = pattern.matcher(line);
        return matcher.matches();
    }

    public boolean is_constructor(String line){
        line = remove_symb('"', line);
        pattern = Pattern.compile("\\s*((public)|(private))+\\s*[a-zA-Z_0-9]*\\s*\\(+[a-zA-Z_0-9\\s\\,\\[\\]]*\\)" +
                                  "+\\{*[0-9a-zA-Z_\\s\\.\\;\\(\\,\\)\\{\\}\\[\\]\\+\\-\\*\\/\\?\\$]*\\}*$");
        matcher = pattern.matcher(line);
        return matcher.matches();
    }

    public double structing_methods(){ return (double)lines_of_code()/(double)calculate_methods(); }

    public double structing_methods_(){ return (double)non_empty_lines_of_code()/(double)calculate_methods(); }

    public double structing_classes(){ return (double)calculate_methods()/(double)number_of_classes(); }

    public int number_of_classes(){
        int index = 0, class_counter = 0;
        while(index < lines.size()){
            if(is_class(lines.get(index)))
                class_counter++;
            index++;
        }
        return class_counter;
    }

    public boolean is_class(String line){
        line = remove_symb('"',line);
        pattern = Pattern.compile("\\s*((public)|(private)|(protected))+\\s*(class)+\\s*[a-zA-Z_0-9\\{]+\\s" +
                                  "*\\{?[0-9a-zA-Z_\\s\\.\\;\\(\\,\\)\\{\\}\\[\\]\\+\\-\\*\\/\\?\\$]*\\}?");
        matcher = pattern.matcher(line);
        return matcher.matches();
    }
}
