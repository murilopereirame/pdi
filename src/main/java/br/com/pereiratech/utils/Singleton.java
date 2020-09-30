/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pereiratech.utils;

/**
 *
 * @author mrx
 */
public class Singleton {
    private static Singleton instance;
    private static String[] headerOriginal;
    private static int[][] originalPGM;
    private static int[][][] originalPPM;
    private static String formato;
    
    public static Singleton getInstance() {
        if(instance == null)
            instance = new Singleton();
        return instance;
    }

    public String[] getHeaderOriginal() {
        return headerOriginal;
    }

    public void setHeaderOriginal(String[] headerOriginal) {
        this.headerOriginal = headerOriginal;
    }

    public int[][] getOriginalPGM() {
        return originalPGM;
    }

    public void setOriginalPGM(int[][] originalPGM) {
        this.originalPGM = originalPGM;
    }

    public int[][][] getOriginalPPM() {
        return originalPPM;
    }

    public void setOriginalPPM(int[][][] originalPPM) {
        this.originalPPM = originalPPM;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }
    
    public String getFormato() {
        return this.formato;
    }
    
    
}
