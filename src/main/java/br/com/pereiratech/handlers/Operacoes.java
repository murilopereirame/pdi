/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pereiratech.handlers;

import br.com.pereiratech.utils.Singleton;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author mrx
 */
public class Operacoes {
    /**
     * Extrai as informações do header da imagem
     *
     * @param fileName Nome do arquivo de imagem P2
     * @return String[]
     * @since 1.1
     */
    public static String[] extrairHeader(final String fileName) {
        final File file = new File(fileName);

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (final FileNotFoundException e1) {
            e1.printStackTrace();
        }

        int index = 0;

        String st;
        String[] finalStr = new String[3];

        try {
            while ((st = br.readLine()) != null) {
                if (index > 2) {
                    break;
                }

                finalStr[index] = st;
                index++;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return finalStr;
    }
    /**
     * Escurece uma matriz MxN
     *
     * @param entrada Matriz original da imagem
     * @param coef Coeficiente de escurecimento
     * @param output Local do arquivo para salver o escurecimento
     * @param headers Headers da imagem original 
    * @since 1.1
     */
    public static void escurecerPGM(int[][] entrada, int coef, String output, String[] headers) {
        if (coef < 0) {
            throw new IllegalArgumentException("O coef deve ser positivo");
        }

        int height = entrada[0].length;
        int width = entrada.length;

        int[][] aux = new int[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int resultado = entrada[i][j] - coef;

                if (resultado < 0) {
                    aux[i][j] = 0;
                } else {
                    aux[i][j] = resultado;
                }
            }
        }
        Singleton.getInstance().setModificadoPGM(aux);
        Singleton.getInstance().setHeaderModificado(headers);
        Matrix.gravarMatrizEmArquivoPGM(aux, output, headers);
    }
    /**
     * Escurece uma matriz MxNx3
     *
     * @param entrada Matriz original da imagem
     * @param coef Coeficiente de escurecimento
     * @param output Local do arquivo para salver o escurecimento
     * @param headers Headers da imagem original 
    * @since 1.1
     */
    public static void escurecerPPM(int[][][] entrada, int coef, String output, String[] headers) {
        if (coef < 0) {
            throw new IllegalArgumentException("O coef deve ser positivo");
        }

        int height = entrada[0].length;
        int width = entrada.length;

        int[][][] aux = new int[width][height][3];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int resultado = entrada[i][j][0] - coef;
                int resultado1 = entrada[i][j][1] - coef;
                int resultado2 = entrada[i][j][2] - coef;

                if (resultado < 0) {
                    aux[i][j][0] = 0;
                } else {
                    aux[i][j][0] = resultado;
                }
                
                if (resultado1 < 0) {
                    aux[i][j][1] = 0;
                } else {
                    aux[i][j][1] = resultado1;
                }
                
                if (resultado2 < 0) {
                    aux[i][j][2] = 0;
                } else {
                    aux[i][j][2] = resultado2;
                }
                
            }
        }
        Singleton.getInstance().setModificadoPPM(aux);
        Singleton.getInstance().setHeaderModificado(headers);
        Matrix.gravarMatrizEmArquivoPPM(aux, output, headers);
    }
    /**
     * Clareia uma matriz MxN
     *
     * @param entrada Matriz original da imagem
     * @param coef Coeficiente de clareamento
     * @param output Local do arquivo para salver o clareamento
     * @param headers Headers da imagem original
     * @since 1.1
     */
    public static void clarearPGM(int[][] entrada, int coef, String output, String[] headers) {
        if (coef < 0) {
            throw new IllegalArgumentException("O coef deve ser positivo");
        }

        int height = entrada[0].length;
        int width = entrada.length;

        int[][] aux = new int[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int resultado = entrada[i][j] + coef;

                if (resultado > 255) {
                    aux[i][j] = 255;
                } else {
                    aux[i][j] = resultado;
                }
            }
        }
        Singleton.getInstance().setModificadoPGM(aux);
        Singleton.getInstance().setHeaderModificado(headers);
        Matrix.gravarMatrizEmArquivoPGM(aux, output, headers);
    }
    /**
     * Clareia uma matriz MxNx3
     *
     * @param entrada Matriz original da imagem
     * @param coef Coeficiente de clareamento
     * @param output Local do arquivo para salver o clareamento
     * @param headers Headers da imagem original
     * @since 1.1
     */
    public static void clarearPPM(int[][][] entrada, int coef, String output, String[] headers) {
        if (coef < 0) {
            throw new IllegalArgumentException("O coef deve ser positivo");
        }

        int height = entrada[0].length;
        int width = entrada.length;

        int[][][] aux = new int[width][height][3];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int resultado = entrada[i][j][0] + coef;
                int resultado1 = entrada[i][j][1] + coef;
                int resultado2 = entrada[i][j][2] + coef;

                if (resultado > 255) {
                    aux[i][j][0] = 255;
                } else {
                    aux[i][j][0] = resultado1;
                }
                
                if (resultado1 > 255) {
                    aux[i][j][1] = 255;
                } else {
                    aux[i][j][1] = resultado1;
                }
                
                if (resultado2 > 255) {
                    aux[i][j][2] = 255;
                } else {
                    aux[i][j][2] = resultado2;
                }
            }
        }
        Singleton.getInstance().setModificadoPPM(aux);
        Singleton.getInstance().setHeaderModificado(headers);
        Matrix.gravarMatrizEmArquivoPPM(aux, output, headers);
    }
    /**
     * Clareia uma matriz MxN
     *
     * @param entrada Matriz original da imagem
     * @param output Local do arquivo para salver o negativo
     * @param headers Headers da imagem original
     * @since 1.1
     */
    public static void negativaPGM(int[][] entrada, String output, String[] headers) {

        int height = entrada[0].length;
        int width = entrada.length;

        int[][] aux = new int[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int resultado = 255 - entrada[i][j];

                if (resultado < 0) {
                    aux[i][j] = 0;
                } else {
                    aux[i][j] = resultado;
                }
            }
        }
        Singleton.getInstance().setModificadoPGM(aux);
        Singleton.getInstance().setHeaderModificado(headers);
        Matrix.gravarMatrizEmArquivoPGM(aux, output, headers);
    }
    /**
     * Clareia uma matriz MxNx3
     *
     * @param entrada Matriz original da imagem
     * @param output Local do arquivo para salver o negativo
     * @param headers Headers da imagem original
     * @since 1.1
     */
    public static void negativaPPM(int[][][] entrada, String output, String[] headers) {

        int height = entrada[0].length;
        int width = entrada.length;

        int[][][] aux = new int[width][height][3];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int resultado = 255 - entrada[i][j][0];
                int resultado1 = 255 - entrada[i][j][1];
                int resultado2 = 255 - entrada[i][j][2];

                if (resultado < 0) {
                    aux[i][j][0] = 0;
                } else {
                    aux[i][j][0] = resultado;
                }
                
                if (resultado1 < 0) {
                    aux[i][j][1] = 0;
                } else {
                    aux[i][j][1] = resultado1;
                }
                
                if (resultado2 < 0) {
                    aux[i][j][2] = 0;
                } else {
                    aux[i][j][2] = resultado2;
                }
            }
        }
        Singleton.getInstance().setModificadoPPM(aux);
        Singleton.getInstance().setHeaderModificado(headers);
        Matrix.gravarMatrizEmArquivoPPM(aux, output, headers);
    }
    /**
     * Padroniza uma imagem para evitar erro de leitura (Imagemagick, GIMP, geram arquivos diferentes)
     *
     * @param fileName Nome do arquivo de entrada/saída
     * @since 1.1
     * @return boolean
     */
    public static boolean normalizarImagemPGM(final String fileName) {

        int[][] aux;

        final File file = new File(fileName);       

        String st = "";

        try {
            st = Files.readString(Paths.get(fileName));
        } catch (Exception ex) {
            return false;
        }       
        st = st.replace(System.getProperty("line.separator"), " ").replace("  ", " ");
        String[] splited = st.split(" ");

        String tipo = splited[0];
        int width = Integer.parseInt(splited[1]);
        int height = Integer.parseInt(splited[2]);
        int scale = Integer.parseInt(splited[3]);

        aux = new int[width][height];

        int linha = 0;
        int coluna = 0;

        for (int i = 4; i < splited.length; i++) {
            aux[coluna][linha] = Integer.parseInt(splited[i]);
            coluna++;

            if (coluna == width) {
                coluna = 0;
                linha++;
            }
        }

        FileWriter fr = null;
        BufferedWriter bw = null;

        String content = tipo + System.getProperty("line.separator") + width + " " + height
                + System.getProperty("line.separator") + scale + System.getProperty("line.separator");

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                content += String.format("%03d", aux[j][i]) + " ";
            }
            content += System.getProperty("line.separator");
        }

        try {
            fr = new FileWriter(file);
            bw = new BufferedWriter(fr);

            bw.write(content);

            bw.close();
            fr.close();
        } catch (final IOException e) {
            return false;
        }

        return true;
    }
    /**
     * Padroniza uma imagem para evitar erro de leitura (Imagemagick, GIMP, geram arquivos diferentes)
     *
     * @param fileName Nome do arquivo de entrada/saída
     * @since 1.2
     * @return boolean
     */
    public static boolean normalizarImagemPPM(final String fileName){

        int[][] aux;

        final File file2 = new File(fileName+"2");
        
        String contents = "";
        try {
            contents = new String(Files.readAllBytes(Paths.get(fileName)));    
        } catch (Exception ex) {
            
        }
        contents = contents.replace("\n", " ");
        contents = contents.replace("  ", " ");
        String[] splited = contents.split(" ");

        String tipo = splited[0];
        int width = Integer.parseInt(splited[1]);
        int height = Integer.parseInt(splited[2]);
        int scale = Integer.parseInt(splited[3]);
        
        aux = new int[width*4][height];

        int linha = 0;
        int coluna = 0;

        for (int i = 4; i < splited.length; i++) {
            aux[coluna][linha] = Integer.parseInt(splited[i]);
            coluna++;

            if (coluna == width*3) {
                coluna = 0;
                linha++;               
            }
        }

        FileWriter fr = null;
        BufferedWriter bw = null;

        String content = tipo + System.getProperty("line.separator") + width + " " + height
                + System.getProperty("line.separator") + scale + System.getProperty("line.separator");

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width*3; j++) {
                content += String.format("%03d", aux[j][i]) + " ";
            }
            content += System.getProperty("line.separator");
        }

        try {
            fr = new FileWriter(file2);
            bw = new BufferedWriter(fr);

            bw.write(content);

            bw.close();
            fr.close();
        } catch (final IOException e) {
            return false;
        }
        
        return true;
    }
    /**
     * Realiza a extração de canais RGB de uma matriz MxNx3
     *
     * @param ppmMatrix Matriz do arquivo original
     * @return ArrayList<int[][]>
     * @since 1.2
     */
    public static ArrayList<int[][]> extrairCanais(int[][][] ppmMatrix) {
        ArrayList<int[][]> canais = new ArrayList<>();
        
        int width = ppmMatrix.length;
        int height = ppmMatrix[0].length;

        int[][] r = new int[width][height];
        int[][] g = new int[width][height];
        int[][] b = new int[width][height];
        
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                r[j][i] = ppmMatrix[j][i][0];
                g[j][i] = ppmMatrix[j][i][1];
                b[j][i] = ppmMatrix[j][i][2];
            }
        }
        
        canais.add(r);
        canais.add(g);
        canais.add(b);
        
        return canais;
    }
    /**
     * Realiza a extração e gravação de canais RGB de um arquivo PPM
     *
     * @param fileName Arquivo PPM original
     * @return boolean
     * @since 1.2
     */
    public static boolean gravarCanaisSeparados(String fileName, String R, String G, String B) {
        String[] headers = Operacoes.extrairHeader(fileName);
        String[] tamanhos = headers[1].split(" ");

        int width = Integer.parseInt(tamanhos[0]);
        int height = Integer.parseInt(tamanhos[1]);

        int[][][] matriz = Matrix.lerMatrizEmArquivoPPM(fileName, height, width);
        ArrayList<int[][]> canais = Operacoes.extrairCanais(matriz);
        for(int i = 0; i < canais.size(); i++) {
            String exportFileName = "";
            headers[0] = "P2";
            switch(i) {
                case 0:
                    exportFileName = R;
                    break;
                case 1:
                    exportFileName = G;
                    break;
                case 2:
                    exportFileName = B;
                    break;
                default:
                    return false;
            }
            Matrix.gravarMatrizEmArquivoPGM(canais.get(i), exportFileName, headers);
        }
        
        return true;
    }
    /**
     * Realiza a junção de canais RGB de três matrizes MxN
     *
     * @param canais Lista de matrizes que representem os canais na ordem RGB
     * @param height Altura da imagem
     * @param width Largura da imagem
     * @return int[][][]
     * @since 1.2
     */
    public static int[][][] juntarCanais(ArrayList<int[][]> canais, int height, int width) {
        int[][][] joinedMatrix = new int[width][height][3];
        
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                joinedMatrix[j][i][0] = canais.get(0)[j][i];
                joinedMatrix[j][i][1] = canais.get(1)[j][i];
                joinedMatrix[j][i][2] = canais.get(2)[j][i];
            }
        }
        
        return joinedMatrix;
    }
    /**
     * Realiza a junção de canais RGB de três arquivos PGM
     *
     * @param RFile Arquivo do canal R
     * @param GFile Arquivo do canal G
     * @param BFile Arquivo do canal B
     * @param FFile Arquivo final
     * @since 1.2
     */
    public static void gravarCanaisJuntos(String RFile, String GFile, String BFile, String FFile) {
        ArrayList<int[][]> canaisLidos = new ArrayList<>();
        String[] headersLidos = Operacoes.extrairHeader(RFile);
        String[] tamanhosLidos = headersLidos[1].split(" ");
        int[][] R = Matrix.lerMatrizEmArquivoPGM(RFile, Integer.parseInt(tamanhosLidos[0]), Integer.parseInt(tamanhosLidos[1]));
        int[][] G = Matrix.lerMatrizEmArquivoPGM(GFile, Integer.parseInt(tamanhosLidos[0]), Integer.parseInt(tamanhosLidos[1]));
        int[][] B = Matrix.lerMatrizEmArquivoPGM(BFile, Integer.parseInt(tamanhosLidos[0]), Integer.parseInt(tamanhosLidos[1]));
        canaisLidos.add(R);
        canaisLidos.add(G);
        canaisLidos.add(B);
        int[][][] jMatrix = juntarCanais(canaisLidos, Integer.parseInt(tamanhosLidos[1]), Integer.parseInt(tamanhosLidos[0]));        
        headersLidos[0] = "P3";
        Matrix.gravarMatrizEmArquivoPPM(jMatrix, FFile, headersLidos);
    }
    /**
     * Realiza a rotação de uma matriz MxN e a grava em um arquivo
     *
     * @param matriz Matriz a ser rotacionada
     * @param direcao Direção a rotacionar a matriz (0-Horário, 1-Anti-Horário)
     * @param output Arquivo de saída da rotação
     * @param headers Cabeçalho do arquivo original
     * @since 1.3
     */
    public static void rotacionar(int[][] matriz, int direcao, String output, String[] headers) {
        int[][] r = Matrix.girar90(matriz, direcao);
        int colunas = Integer.parseInt(headers[1].split(" ")[0]);
        int linhas = Integer.parseInt(headers[1].split(" ")[1]);
        String[] headersCP = new String[3];
        System.arraycopy(headers, 0, headersCP, 0, 3);  
        headersCP[1] = linhas + " " + colunas;
        Singleton.getInstance().setModificadoPGM(r);
        Singleton.getInstance().setHeaderModificado(headersCP);
        Matrix.gravarMatrizEmArquivoPGM(r, output, headersCP);
    }
    /**
     * Realiza a rotação de uma matriz MxN e a grava em um arquivo
     *
     * @param matriz Matriz a ser rotacionada
     * @param direcao Direção a rotacionar a matriz (0-Horário, 1-Anti-Horário)
     * @param output Arquivo de saída da rotação
     * @param headers Cabeçalho do arquivo original
     * @since 1.3
     */
    public static void rotacionarPPM(int[][][] matriz, int direcao, String output, String[] headers) {
        int[][][] r = Matrix.girar90PPM(matriz, direcao);
        int colunas = Integer.parseInt(headers[1].split(" ")[0]);
        int linhas = Integer.parseInt(headers[1].split(" ")[1]);
        String[] headersCP = new String[3];
        System.arraycopy(headers, 0, headersCP, 0, 3);  
        headersCP[1] = linhas + " " + colunas;
        Singleton.getInstance().setModificadoPPM(r);
        Singleton.getInstance().setHeaderModificado(headersCP);
        Matrix.gravarMatrizEmArquivoPPM(r, output, headersCP);
    }
    /**
     * Realiza o flip de uma matriz MxNx3 e a grava em um arquivo
     *
     * @param matriz Matriz a ser flipada
     * @param direcao Direção a flipar a matriz (0-Horizontal, 1-Vertical)
     * @param output Arquivo de saída do flip
     * @param headers Cabeçalho do arquivo original
     * @since 1.3
     */
    public static void fliparPPM(int[][][] matriz, int direcao, String output, String[] headers) {
        int[][][] r = Matrix.fliparPPM(matriz, direcao);     
        Singleton.getInstance().setModificadoPPM(r);
        Singleton.getInstance().setHeaderModificado(headers);
        Matrix.gravarMatrizEmArquivoPPM(r, output, headers);
    }
    /**
     * Realiza o flip de uma matriz MxN e a grava em um arquivo
     *
     * @param matriz Matriz a ser flipada
     * @param direcao Direção a flipar a matriz (0-Horizontal, 1-Vertical)
     * @param output Arquivo de saída do flip
     * @param headers Cabeçalho do arquivo original
     * @since 1.3
     */
    public static void flipar(int[][] matriz, int direcao, String output, String[] headers) {
        int[][] r = Matrix.flipar(matriz, direcao);     
        Singleton.getInstance().setModificadoPGM(r);
        Singleton.getInstance().setHeaderModificado(headers);
        Matrix.gravarMatrizEmArquivoPGM(r, output, headers);
    }
    /**
     * Realiza a equalização de um histograma de uma matriz MxN
     *
     * @param matriz Matriz a ter o histograma equalizado
     * @param fileName Caminho do arquivo final equlizado
     * @param headers Cabeçalho do arquivo original
     * @since 1.3
     */
    public static void equalizar(int[][] matriz, String[] headers, String fileName) {
        int L = Integer.parseInt(headers[2]);
        int[][] finalMatriz = Matrix.equalizar(matriz, L+1);
        Singleton.getInstance().setModificadoPGM(finalMatriz);
        Singleton.getInstance().setHeaderModificado(headers);
        Matrix.gravarMatrizEmArquivoPGM(finalMatriz, fileName, headers);
    }
    /**
     * Realiza a equalização de um histograma de uma matriz MxNx3
     *
     * @param matriz Matriz a ter o histograma equalizado
     * @param fileName Caminho do arquivo final equlizado
     * @param headers Cabeçalho do arquivo original
     * @since 1.3
     */
    public static void equalizarPPM(int[][][] matriz, String[] headers, String fileName) {
        int L = Integer.parseInt(headers[2]);
        int[][][] finalMatriz = Matrix.equalizarPPM(matriz, L+1);
        Singleton.getInstance().setModificadoPPM(finalMatriz);
        Singleton.getInstance().setHeaderModificado(headers);
        Matrix.gravarMatrizEmArquivoPPM(finalMatriz, fileName, headers);
    }
    /**
     * Realiza o fatiamento de uma matriz MxN
     *
     * @param matriz Matriz a ser fatiada
     * @param a Limite inferior
     * @param b Limite superior
     * @param modo Modo de fatiamento (0-Altera o que está fora, 1-Altera somente o que esta no limite)
     * @param entre Valor a ser assumido caso o valor da matriz esteja dentro do limite
     * @param fora Valor a ser assumido caso o valor  da matriz esteja fora do limite
     * @param output Caminho do arquivo final fatiado
     * @param headers Cabeçalho do arquivo original
     * @since 1.3
     */
    public static void fatiar(int[][] matriz, int a, int b, int modo, int entre, int fora, String output, String[] headers) {
        int[][] finalMatriz = Matrix.fatiamento(matriz, a, b, modo, entre, fora);
        Singleton.getInstance().setModificadoPGM(finalMatriz);
        Singleton.getInstance().setHeaderModificado(headers);
        Matrix.gravarMatrizEmArquivoPGM(finalMatriz, output, headers);
    }
    /**
     * Realiza o fatiamento de uma matriz MxN
     *
     * @param matriz Matriz a ser fatiada
     * @param a Limite inferior
     * @param b Limite superior
     * @param modo Modo de fatiamento (0-Altera o que está fora, 1-Altera somente o que esta no limite)
     * @param entre Valor a ser assumido caso o valor da matriz esteja dentro do limite
     * @param fora Valor a ser assumido caso o valor  da matriz esteja fora do limite
     * @param output Caminho do arquivo final fatiado
     * @param headers Cabeçalho do arquivo original
     * @since 1.3
     */
    public static void fatiarPPM(int[][][] matriz, int a, int b, int modo, int entre, int fora, String output, String[] headers) {
        int[][][] finalMatriz = Matrix.fatiamentoPPM(matriz, a, b, modo, entre, fora);
        Singleton.getInstance().setModificadoPPM(finalMatriz);
        Singleton.getInstance().setHeaderModificado(headers);
        Matrix.gravarMatrizEmArquivoPPM(finalMatriz, output, headers);
    }
    /**
     * Realiza a função gama em uma matriz MxN
     *
     * @param matriz Matriz a ser fatiada
     * @param c Constante
     * @param gama Nível de gama
     * @param headers Cabeçalho do arquivo original
     * @param output Caminho do arquivo final aplicado na função
     * @since 1.3
     */
    public static void gama(int[][] matriz, double c, double gama, String[] headers, String output) {
        int[][] finalMatriz = Matrix.gama(matriz, c, gama);
        Singleton.getInstance().setModificadoPGM(finalMatriz);
        Singleton.getInstance().setHeaderModificado(headers);
        Matrix.gravarMatrizEmArquivoPGM(finalMatriz, output, headers);
    }
    /**
     * Realiza a função gama em uma matriz MxN
     *
     * @param matriz Matriz a ser fatiada
     * @param c Constante
     * @param gama Nível de gama
     * @param headers Cabeçalho do arquivo original
     * @param output Caminho do arquivo final aplicado na função
     * @since 1.3
     */
    public static void gamaPPM(int[][][] matriz, double c, double gama, String[] headers, String output) {
        int[][][] finalMatriz = Matrix.gamaPPM(matriz, c, gama);
        Singleton.getInstance().setModificadoPPM(finalMatriz);
        Singleton.getInstance().setHeaderModificado(headers);
        Matrix.gravarMatrizEmArquivoPPM(finalMatriz, output, headers);
    }
}
