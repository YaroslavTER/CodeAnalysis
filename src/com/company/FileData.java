package com.company;

/**
 * Created by pc on 5/25/2016.
 */
public class FileData {
    private String dir_name;
    private int NOM, NOC, LOC, NELOC;

    public FileData(String dir_name){
        this.dir_name = dir_name;
    }

    public void set_nom(int NOM){
        this.NOM = NOM;
    }

    public void set_noc(int NOC){
        this.NOC = NOC;
    }

    public void set_loc(int LOC){
        this.LOC = LOC;
    }

    public void set_neloc(int NELOC){
        this.NELOC = NELOC;
    }

    public String get_dir_name(){
        return dir_name;
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
}
