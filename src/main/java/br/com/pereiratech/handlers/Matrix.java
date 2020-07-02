package br.com.pereiratech.handlers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * @author Murilo Pereira <mrex@tuta.io>
 * @version 1.1
 * @since 05/03/2020
 */
public class Matrix {
    /**
     * Imprime uma matriz no console utilizando três digitos a esquerda
     *
     * @param matriz Matriz a ser impressa
     * @since 1.0
     */
    public static void imprimeMatrizPGM(final int[][] matriz) {
        for (int i = 0; i < matriz[0].length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                System.out.print(String.format("%03d", matriz[j][i]) + " ");
            }
            System.out.println(" ");
        }
    }
    
    /** Gera uma matriz aleatória de tamanho n X m com valores no intervalo
     * [0,100]
     *
     * @param linhas Número inteiro total de linhas da matriz
     * @param colunas Número inteiro total de colunas da matriz
     * @return int[][]
     * @since 1.1
     */
    public static int[][] gerarMatrizPGM(int linhas, int colunas) {
        Random rand = new Random();
        int[][] matrizFinal = new int[colunas][linhas];

        for (int i = 0; i < colunas; i++) {
            for (int j = 0; j < linhas; j++) {
                matrizFinal[i][j] = rand.nextInt(100);
            }
        }

        return matrizFinal;
    }
    
    /**
     * Efetua a soma entre duas matrizes dadas, a + b, e então retorna a matriz
     * resultante
     *
     * @param a Primeira matriz
     * @param b Segunda matriz
     * @param limite Limite máximo da soma
     * @return int[][]
     * @since 1.0
     */
    public static int[][] somaMatrizesPGM(final int[][] a, final int[][] b, int limite) {
        if (a.length != b.length) {
            return null;
        } else if (a[0].length != b[0].length) {
            return null;
        }

        final int[][] aux = new int[a.length][a[0].length];

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                final int data = a[i][j] + b[i][j];

                if (data <= limite) {
                    aux[i][j] = data;
                } else {
                    aux[i][j] = limite;
                }
            }
        }

        return aux;
    }
    
    /**
     * Efetua a subtração entre duas matrizes dadas, a - b, e então retorna a
     * matriz resultante
     *
     * @param a Primeira matriz
     * @param b Segunda matriz
     * @return int[][]
     * @since 1.0
     */
    public static int[][] subtrairMatrizesPGM(final int[][] a, final int[][] b) {
        if (a.length != b.length) {
            return null;
        } else if (a[0].length != b[0].length) {
            return null;
        }

        final int[][] aux = new int[a.length][a[0].length];

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                final int data = a[i][j] - b[i][j];

                if (data <= 0) {
                    aux[i][j] = 0;
                } else {
                    aux[i][j] = data;
                }
            }
        }

        return aux;
    }
    
    /**
     * Efetua a leitura de uma matriz quadrada contida em um arquivo
     *
     * @param fileName Nome do arquivo de origem
     * @param linhas Total de linhas da matriz
     * @param colunas Total de colunas da matriz
     * @return int[][]
     * @since 1.1
     */
    public static int[][] lerMatrizEmArquivoPGM(final String fileName, final int linhas, final int colunas) {
        final File file = new File(fileName);

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (final FileNotFoundException e1) {
            e1.printStackTrace();
        }

        int startIndex = 0;

        String st;
        final int[][] matrizFinal = new int[colunas][linhas];
        int linha = 0;
        try {
            while ((st = br.readLine()) != null) {
                if (startIndex > 2) {
                    final String[] aux = st.split(" ");                    
                    int coluna = 0;
                    for (final String string : aux) {
                        try {
                            matrizFinal[coluna][linha] = Integer.parseInt(string);                        
                        } catch(Exception ex) {
                            System.out.println(coluna + " | " + linha);
                        }
                        coluna++;
                    }
                    linha++;
                } else {
                    startIndex++;
                }
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }

        return matrizFinal;
    }
    
    /**
     * Grava uma determinada matriz quadrada em um arquivo de texto
     *
     * @param matriz Matriz a ser gravada
     * @param fileName Nome do arquivo destino
     * @return void
     * @since 1.1
     */
    public static void gravarMatrizEmArquivoPGM(final int[][] matriz, final String fileName, String[] headers) {
        final File file = new File(fileName);
        FileWriter fr = null;
        BufferedWriter br = null;

        String content = "";

        for (int i = 0; i < headers.length; i++) {
            content += headers[i] + "\n";
        }

        for (int i = 0; i < matriz[0].length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                content += String.format("%03d", matriz[j][i]) + " ";
            }
            content += System.getProperty("line.separator");
        }

        try {
            fr = new FileWriter(file);
            br = new BufferedWriter(fr);

            br.write(content);

            br.close();
            fr.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Imprime uma matriz cubica no console utilizando três digitos a esquerda
     *
     * @param matriz Matriz a ser impressa
     * @return void
     * @since 1.0
     */
    public static void imprimeMatrizPPM(final int[][][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(String.format("%03d", matriz[i][j][0]) + " " + String.format("%03d", matriz[i][j][1]) + " " + String.format("%03d", matriz[i][j][2]) + " ");
            }
            System.out.println(" ");
        }
    }
    
    /**
     * Efetua a leitura de uma matriz cubica contida em um arquivo
     *
     * @param fileName Nome do arquivo de origem
     * @param linhas Total de linhas da matriz
     * @param colunas Total de colunas da matriz
     * @return int[][]
     * @since 1.2
     */
    public static int[][][] lerMatrizEmArquivoPPM(final String fileName, final int linhas, final int colunas) {;
        final File file = new File(fileName);

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (final FileNotFoundException e1) {
            e1.printStackTrace();
        }

        int startIndex = 0;

        String st;
        final int[][][] matrizFinal = new int[colunas][linhas][3];
        int linha = 0;
        try {
            while ((st = br.readLine()) != null) {
                if (startIndex > 2) {
                    final String[] aux = st.split(" ");

                    int coluna = 0;
                    int colAux = 0;
                    while(coluna < colunas) {
                        matrizFinal[coluna][linha][0] = Integer.parseInt(aux[colAux]);
                        matrizFinal[coluna][linha][1] = Integer.parseInt(aux[colAux+1]);
                        matrizFinal[coluna][linha][2] = Integer.parseInt(aux[colAux+2]);
                        coluna++;
                        colAux += 3;
                    }
                    linha++;
                } else {
                    startIndex++;
                }
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return matrizFinal;
    }
    
    /**
     * Grava uma determinada matriz cubica em um arquivo de texto
     *
     * @param matriz Matriz a ser gravada
     * @param fileName Nome do arquivo destino
     * @param headers Cabeçalho do arquivo
     * @return void
     * @since 1.2
     */
    public static void gravarMatrizEmArquivoPPM(final int[][][] matriz, final String fileName, String[] headers) {
        final File file = new File(fileName);
        FileWriter fr = null;
        BufferedWriter br = null;

        String content = "";

        for (int i = 0; i < headers.length; i++) {
            content += headers[i] + "\n";
        }

        for (int i = 0; i < matriz[0].length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                content += String.format("%03d", matriz[j][i][0]) + " " + String.format("%03d", matriz[j][i][1]) + " " + String.format("%03d", matriz[j][i][2]) + " ";
            }
            content += System.getProperty("line.separator");
        }

        try {
            fr = new FileWriter(file);
            br = new BufferedWriter(fr);

            br.write(content);

            br.close();
            fr.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Gira uma dada matriz MxN em 90º
     *
     * @param matriz Matriz a ser rodacionada
     * @param direcao Direção para que a matriz será redirecionada (0-Horário, 1-Anti-Horário)
     * @return int[][]
     * @since 1.3
     */
    public static int[][] girar90(int[][] matriz, int direcao) {
        int colunas = matriz.length;
        int linhas = matriz[0].length;
        
        int[][] mAux = new int[linhas][colunas];
        
        if(direcao == 0) {
            for(int x = 0; x < linhas; x++){
                for(int y = 0; y < colunas; y++) {
                    mAux[linhas-x-1][colunas - y - 1] = matriz[colunas - y - 1][x]; //Rodando normal 
                }
            }
        } else {
            for(int x = 0; x < linhas; x++){
                for(int y = 0; y < colunas; y++) {
                    mAux[linhas-x-1][colunas - y - 1] = matriz[y][x]; //Rodando normal 
                }
            }
        }
        
        return mAux;
    }
    /**
     * Gira uma dada matriz MxNx3 em 90º
     *
     * @param matriz Matriz a ser rotacionada
     * @param direcao Direção para que a matriz será redirecionada (0-Horário, 1-Anti-Horário)
     * @return int[][][]
     * @since 1.3
     */
    public static int[][][] girar90PPM(int[][][] matriz, int direcao) {
        int colunas = matriz.length;
        int linhas = matriz[0].length;
        
        int[][][] mAux = new int[linhas][colunas][3];
        
        if(direcao == 0) {
            for(int x = 0; x < linhas; x++){
                for(int y = 0; y < colunas; y++) {
                    mAux[linhas-x-1][colunas - y - 1][0] = matriz[colunas - y - 1][x][0];
                    mAux[linhas-x-1][colunas - y - 1][1] = matriz[colunas - y - 1][x][1];
                    mAux[linhas-x-1][colunas - y - 1][2] = matriz[colunas - y - 1][x][2];//Rodando normal 
                }
            }
        } else {
            for(int x = 0; x < linhas; x++){
                for(int y = 0; y < colunas; y++) {
                    mAux[linhas-x-1][colunas - y - 1][0] = matriz[y][x][0]; //Rodando normal 
                    mAux[linhas-x-1][colunas - y - 1][1] = matriz[y][x][1];
                    mAux[linhas-x-1][colunas - y - 1][2] = matriz[y][x][2];
                }
            }
        }
        
        return mAux;
    }
    /**
     * Faz um flip horizontal ou vertical em uma matriz MxN
     *
     * @param matriz Matriz a ser flipada
     * @param direcao Direção para que a matriz será redirecionada (0-Horizontal, 1-Vertical)
     * @return int[][]
     * @since 1.3
     */
    public static int[][] flipar(int[][] matriz, int direcao) {
        int colunas = matriz.length;
        int linhas = matriz[0].length;
        
        int[][] mAux = new int[colunas][linhas];
        
        if(direcao == 0) {
            for(int i = 0; i < colunas; i++) {
                for(int j = 0; j < linhas; j++) {
                    mAux[i][j] = matriz[colunas-i-1][j];
                }
            }
        } else {
            for(int i = 0; i < colunas; i++) {
                for(int j = 0; j < linhas; j++) {
                    mAux[i][linhas - j - 1] = matriz[i][j];
                }
            }
        }
        
        return mAux;
    }
    /**
     * Faz um flip horizontal ou vertical em uma matriz MxNx3
     *
     * @param matriz Matriz a ser flipada
     * @param direcao Direção para que a matriz será redirecionada (0-Horizontal, 1-Vertical)
     * @return int[][][]
     * @since 1.3
     */
    public static int[][][] fliparPPM(int[][][] matriz, int direcao) {
        int colunas = matriz.length;
        int linhas = matriz[0].length;
        
        int[][][] mAux = new int[colunas][linhas][];
        
        if(direcao == 0) {
            for(int i = 0; i < colunas; i++) {
                for(int j = 0; j < linhas; j++) {
                    mAux[i][j][0] = matriz[colunas-i-1][j][0];
                    mAux[i][j][1] = matriz[colunas-i-1][j][1];
                    mAux[i][j][2] = matriz[colunas-i-1][j][2];//Flipando
                }
            }
        } else {
            for(int i = 0; i < colunas; i++) {
                for(int j = 0; j < linhas; j++) {
                    mAux[i][linhas - j - 1][0] = matriz[i][j][0];
                    mAux[i][linhas - j - 1][1] = matriz[i][j][1];
                    mAux[i][linhas - j - 1][2] = matriz[i][j][2];
                }
            }
        }
        
        return mAux;
    }
    /**
     * Realiza o fatiamente de uma imagem P&B MxN
     *
     * @param matriz Matriz a ser fatiada
     * @param a Limite inferior
     * @param b Limite superior
     * @param modo Modo de fatiamento (0-Altera o que está fora, 1-Altera somente o que esta no limite)
     * @param entre Valor a ser assumido caso o valor da matriz esteja dentro do limite
     * @param fora Valor a ser assumido caso o valor  da matriz esteja fora do limite
     * @return int[][]
     * @since 1.3
     */
    public static int[][] fatiamento(int[][] matriz, int a, int b, int modo, int entre, int fora) {
        int colunas = matriz.length;
        int linhas = matriz[0].length;
        
        int[][] mAux = new int[colunas][linhas];
        
        for(int i = 0; i < colunas; i++) {
            for(int j = 0; j < linhas; j++) {
                if(matriz[i][j] >= a && matriz[i][j] <= b) {
                    mAux[i][j] = entre;
                }
                else {
                    if(modo == 0) {
                        mAux[i][j] = fora;
                    }
                    else {
                        mAux[i][j] = matriz[i][j];
                    }
                }
            }
        }
        
        return mAux;
    }
    /**
     * Realiza a função gama em uma matriz MxN
     *
     * @param matriz Matriz a ser fatiada
     * @param c Constante
     * @param gama Nível de gama
     * @return int[][]
     * @since 1.3
     */
    public static int[][] gama(int[][] matriz, int c, int gama) {
        int colunas = matriz.length;
        int linhas = matriz[0].length;
        
        int[][] mAux = new int[colunas][linhas];
        
        for(int i = 0; i < colunas; i++) {
            for(int j = 0; j < linhas; j++) {
                double convertido = matriz[i][j]/255;
                int funcao = (int)Math.round((c*(Math.pow(convertido, gama))));
                mAux[i][j] = funcao * 255;
            }
        }   
        
        return mAux;
    }
    /**
     * Realiza a equalização do histograma de uma matriz MxN
     *
     * @param matriz Matriz a ser equalizada
     * @param L Número de bits máximos da imagem
     * @return int[][]
     * @since 1.3
     */
    public static int[][] equalizar(int[][] matriz, int L) {
        double[] somatoria = new double[L];
        int[] Sk = new int[L];
        int colunas = matriz.length;
        int linhas = matriz[0].length;
        
        int[][] mAux = new int[colunas][linhas];
        
        for(int i = 0; i < colunas; i++) {
            for(int j = 0; j < linhas; j++) {
                int valor = matriz[i][j];
                somatoria[valor] += 1;
            }    
        }
        
        double acumulado = 0;
        
        for(int i = 0; i < Sk.length; i++) {
            double p = somatoria[i]/(colunas*linhas);
            acumulado += p;
            double result = (L-2)*acumulado;
            Sk[i] = (int)Math.round(result);
        }
        
        for(int i = 0; i < colunas; i++) {
            for(int j = 0; j < linhas; j++) {
                int valor = matriz[i][j];
                mAux[i][j] = Sk[valor];
            }    
        }
        
        return mAux;
    }
}
