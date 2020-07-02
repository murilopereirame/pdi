package br.com.pereiratech.main;

import br.com.pereiratech.handlers.*;
import java.io.File;
import java.util.ArrayList;

public class Main {

    public static void main(String args[]) {
//        String fileName = "/home/mrx/Documentos/Faculdade/5º termo/Processamento Digital de Imagens/Exercícios/lena480640.pgm";
//        String FR = "/home/mrx/Documentos/Faculdade/5º termo/Processamento Digital de Imagens/Exercícios/leninha2R.pgm";
//        String FG = "/home/mrx/Documentos/Faculdade/5º termo/Processamento Digital de Imagens/Exercícios/leninha2G.pgm";
//        String FB = "/home/mrx/Documentos/Faculdade/5º termo/Processamento Digital de Imagens/Exercícios/leninha2B.pgm";
//        String FF = "/home/mrx/Documentos/Faculdade/5º termo/Processamento Digital de Imagens/Exercícios/leninhaRGB.ppm";
//        Operacoes.normalizarImagemPPM(fileName);
//        Operacoes.normalizarImagemPGM(fileName);
//        String[] headers = Operacoes.extrairHeader(fileName);
//        int colunas = Integer.parseInt(headers[1].split(" ")[0]);
//        int linhas = Integer.parseInt(headers[1].split(" ")[1]);
//        int[][] ma = Matrix.lerMatrizEmArquivoPGM(fileName, linhas, colunas);
//        ArrayList<int[][]> lst = Operacoes.extrairCanais(ma);
//        Operacoes.juntarCanais(lst, linhas, colunas);
//        Operacoes.gravarCanaisSeparados(fileName);
//        Operacoes.gravarCanaisJuntos(FR, FG, FB, FF);
//        Matrix.imprimeMatrizPPM(ma);
//        Matrix.gravarMatrizEmArquivoPPM(ma, fileName+"2", headers);
//        int[][] ma = Matrix.lerMatrizEmArquivoPGM(fileName, linhas, colunas);
//        int[][] m1 = Operacoes.clarearPGM(ma, 50);
//        int[][] m2 = Operacoes.escurecerPGM(ma, 50);
//        int[][] m3 = Operacoes.negativaPGM(ma);
//        Matrix.imprimeMatrizPGM(ma);        
//        Matrix.gravarMatrizEmArquivoPGM(ma, fileName+"2", headers);
//        Matrix.gravarMatrizEmArquivoPGM(m1, fileName+"3", headers);
//        Matrix.gravarMatrizEmArquivoPGM(m2, fileName+"4", headers);
//        Matrix.gravarMatrizEmArquivoPGM(m3, fileName+"5", headers);
//        Operacoes.flipar(ma, 0, "/home/mrx/Documentos/Faculdade/5º termo/Processamento Digital de Imagens/Exercícios/lena480640FH.pgm", headers);
//        Operacoes.flipar(ma, 1, "/home/mrx/Documentos/Faculdade/5º termo/Processamento Digital de Imagens/Exercícios/lena480640FV.pgm", headers);
//        Operacoes.rotacionar(ma, 0, "/home/mrx/Documentos/Faculdade/5º termo/Processamento Digital de Imagens/Exercícios/lena480640RH.pgm", headers);
//        Operacoes.rotacionar(ma, 1, "/home/mrx/Documentos/Faculdade/5º termo/Processamento Digital de Imagens/Exercícios/lena480640RA.pgm", headers);
        
        File folder = new File("/home/mrx/Documentos/Faculdade/5º termo/Processamento Digital de Imagens/Exercícios/Images/Input");
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                String fileName = file.getName();
                String inFolder = "/home/mrx/Documentos/Faculdade/5º termo/Processamento Digital de Imagens/Exercícios/Images/Input/";
                String outFolder = "/home/mrx/Documentos/Faculdade/5º termo/Processamento Digital de Imagens/Exercícios/Images/Output/";
                String fileInput = inFolder + fileName;
                String fileClaro = outFolder + file.getName().replace(".pgm", "_claro.pgm");
                String fileEscuro = outFolder + file.getName().replace(".pgm", "_escuro.pgm");
                String fileEqualizado = outFolder + file.getName().replace(".pgm", "_eq.pgm");
                String fileFatiado1 = outFolder + file.getName().replace(".pgm", "_fat1.pgm");
                String fileFatiado2 = outFolder + file.getName().replace(".pgm", "_fat2.pgm");
                String fileFlipadoHorizontal = outFolder + file.getName().replace(".pgm", "_fh.pgm");
                String fileFlipadoVertical = outFolder + file.getName().replace(".pgm", "_fv.pgm");
                String fileGama = outFolder + file.getName().replace(".pgm", "_gama.pgm");
                String fileNegativo = outFolder + file.getName().replace(".pgm", "_negativo.pgm");
                String file90H = outFolder + file.getName().replace(".pgm", "_90H.pgm");
                String file90AH = outFolder + file.getName().replace(".pgm", "_90AH.pgm");
                
                Operacoes.normalizarImagemPGM(fileInput);
                String[] headers = Operacoes.extrairHeader(fileInput);
                int colunas = Integer.parseInt(headers[1].split(" ")[0]);
                int linhas = Integer.parseInt(headers[1].split(" ")[1]);
                int[][] ma = Matrix.lerMatrizEmArquivoPGM(fileInput, linhas, colunas);
                Operacoes.flipar(ma, 0, fileFlipadoHorizontal, headers);
                Operacoes.flipar(ma, 1, fileFlipadoVertical, headers);
                Operacoes.clarearPGM(ma, 20, fileClaro, headers);
                Operacoes.escurecerPGM(ma, 20, fileEscuro, headers);
                Operacoes.equalizar(ma, headers, fileEqualizado);
                Operacoes.fatiar(ma, 30, 100, 0, 150, 15, fileFatiado1, headers);
                Operacoes.fatiar(ma, 30, 100, 1, 50, 0, fileFatiado2, headers);
                Operacoes.gama(ma, 1, 24, headers, fileGama);
                Operacoes.negativaPGM(ma, fileNegativo, headers);
                Operacoes.rotacionar(ma, 0, file90H, headers);
                Operacoes.rotacionar(ma, 1, file90AH, headers);
            }
        }
    
    }
}
