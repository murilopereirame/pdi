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
    private static String[] headerModificado;
    private static int[][] originalPGM;
    private static int[][] modificadoPGM;
    private static int[][][] originalPPM;
    private static int[][][] modificadoPPM;
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

    public String[] getHeaderModificado() {
        return headerModificado;
    }

    public void setHeaderModificado(String[] headerModificado) {
        this.headerModificado = headerModificado;
    }

    public int[][] getOriginalPGM() {
        return originalPGM;
    }

    public void setOriginalPGM(int[][] originalPGM) {
        this.originalPGM = originalPGM;
    }

    public int[][] getModificadoPGM() {
        return modificadoPGM;
    }

    public void setModificadoPGM(int[][] modificadoPGM) {
        this.modificadoPGM = modificadoPGM;
    }

    public int[][][] getOriginalPPM() {
        return originalPPM;
    }

    public void setOriginalPPM(int[][][] originalPPM) {
        this.originalPPM = originalPPM;
    }

    public int[][][] getModificadoPPM() {
        return modificadoPPM;
    }

    public void setModificadoPPM(int[][][] modificadoPPM) {
        this.modificadoPPM = modificadoPPM;
    }
    
    public void setFormato(String formato) {
        this.formato = formato;
    }
    
    public String getFormato() {
        return this.formato;
    }
    
    
}
