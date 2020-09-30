package br.com.pereiratech.handlers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
     * Efetua a soma entre duas matrizes dadas, a + b, e então retorna a matriz
     * resultante
     *
     * @param a Primeira matriz
     * @param b Segunda matriz
     * @param limite Limite máximo da soma
     * @return int[][][]
     * @since 1.6
     */
    public static int[][][] somaMatrizesPPM(final int[][][] a, final int[][][] b, int limite) {
        if (a.length != b.length) {
            return null;
        } else if (a[0].length != b[0].length) {
            return null;
        }

        final int[][][] aux = new int[a.length][a[0].length][3];

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                final int R = a[i][j][0] + b[i][j][0];
                final int G = a[i][j][1] + b[i][j][1];
                final int B = a[i][j][2] + b[i][j][2];

                if(R > 255)
                    aux[i][j][0] = 255;
                else
                    aux[i][j][0] = R;

                if(G > 255)
                    aux[i][j][1] = 255;
                else
                    aux[i][j][1] = G;

                if(B > 255)
                    aux[i][j][2] = 255;
                else
                    aux[i][j][2] = B;
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
     * @return int[][][]
     * @since 1.6
     */
    public static int[][][] subtrairMatrizesPPM(final int[][][] a, final int[][][] b) {
        if (a.length != b.length) {
            return null;
        } else if (a[0].length != b[0].length) {
            return null;
        }

        final int[][][] aux = new int[a.length][a[0].length][3];

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                final int R = a[i][j][0] - b[i][j][0];
                final int G = a[i][j][1] - b[i][j][1];
                final int B = a[i][j][2] - b[i][j][2];

                if(R < 0)
                    aux[i][j][0] = 0;
                else
                    aux[i][j][0] = R;

                if(G < 0)
                    aux[i][j][1] = 0;
                else
                    aux[i][j][1] = G;

                if(B < 0)
                    aux[i][j][2] = 0;
                else
                    aux[i][j][2] = B;
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
        } catch (Exception ex) {
            System.out.println(ex);
        }

        int startIndex = 0;

        String st;
        final int[][] matrizFinal = new int[colunas][linhas];
        int linha = 0;
        try {
            while ((st = br.readLine()) != null) {
                if (startIndex > 2) {
                    final String[] aux = st.split(" ");    
                    if(st.contains("#"))
                        continue;                
                    int coluna = 0;
                    for (final String string : aux) {
                        try {
                            matrizFinal[coluna][linha] = Integer.parseInt(string);                        
                        } catch(Exception ex) {
                            System.out.println(ex);
                        }
                        coluna++;
                    }
                    linha++;
                } else {
                    startIndex++;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
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

        content += "";
        
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
        
        //System.out.println("Gravado!");
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
    public static int[][][] lerMatrizEmArquivoPPM(final String fileName, final int linhas, final int colunas) {
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
     * Grava uma determinada matriz Nx3 em um arquivo de texto
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
                    mAux[x][colunas - y-1] = matriz[y][x]; //Rodando normal 
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
                    mAux[x][colunas - y-1][0] = matriz[y][x][0]; //Rodando normal 
                    mAux[x][colunas - y-1][1] = matriz[y][x][1];
                    mAux[x][colunas - y-1][2] = matriz[y][x][2];
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
        
        int[][][] mAux = new int[colunas][linhas][3];
        
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
    public static int[][][] fatiamentoPPM(int[][][] matriz, int a, int b, int modo, int entre, int fora) {
        int colunas = matriz.length;
        int linhas = matriz[0].length;
        
        int[][][] mAux = new int[colunas][linhas][3];
        
        for(int i = 0; i < colunas; i++) {
            for(int j = 0; j < linhas; j++) {
                if(matriz[i][j][0] >= a && matriz[i][j][0] <= b) {
                    mAux[i][j][0] = entre;
                }
                else {
                    if(modo == 0) {
                        mAux[i][j][0] = fora;
                    }
                    else {
                        mAux[i][j][0] = matriz[i][j][0];
                    }
                }
                
                if(matriz[i][j][1] >= a && matriz[i][j][1] <= b) {
                    mAux[i][j][1] = entre;
                }
                else {
                    if(modo == 0) {
                        mAux[i][j][1] = fora;
                    }
                    else {
                        mAux[i][j][1] = matriz[i][j][1];
                    }
                }
                
                if(matriz[i][j][2] >= a && matriz[i][j][2] <= b) {
                    mAux[i][j][2] = entre;
                }
                else {
                    if(modo == 0) {
                        mAux[i][j][2] = fora;
                    }
                    else {
                        mAux[i][j][2] = matriz[i][j][2];
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
    public static int[][] gama(int[][] matriz, double c, double gama) {
        int colunas = matriz.length;
        int linhas = matriz[0].length;
        
        int[][] mAux = new int[colunas][linhas];
        
        for(int i = 0; i < colunas; i++) {
            for(int j = 0; j < linhas; j++) {
                double convertido = matriz[i][j]/255.0;
                int funcao = (int)Math.round((c*(Math.pow(convertido, gama))));
                //System.out.println(matriz[i][j]);
                mAux[i][j] = (int)Math.round(funcao * 255.0);
            }
        }   
        
        return mAux;
    }
    /**
     * Realiza a função gama em uma matriz MxNx3
     *
     * @param matriz Matriz a ser fatiada
     * @param c Constante
     * @param gama Nível de gama
     * @return int[][]
     * @since 1.3
     */
    public static int[][][] gamaPPM(int[][][] matriz, double c, double gama) {
        int colunas = matriz.length;
        int linhas = matriz[0].length;
        
        int[][][] mAux = new int[colunas][linhas][3];
        
        for(int i = 0; i < colunas; i++) {
            for(int j = 0; j < linhas; j++) {
                double convertido1 = matriz[i][j][0]/255.0;
                double convertido2 = matriz[i][j][1]/255.0;
                double convertido3 = matriz[i][j][2]/255.0;
                int funcao = (int)Math.round((c*(Math.pow(convertido1, gama))));
                int funcao1 = (int)Math.round((c*(Math.pow(convertido2, gama))));
                int funcao2 = (int)Math.round((c*(Math.pow(convertido3, gama))));
                mAux[i][j][0] = (int)Math.round(funcao * 255.0);
                mAux[i][j][1] = (int)Math.round(funcao1 * 255.0);
                mAux[i][j][2] = (int)Math.round(funcao2 * 255.0);
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
            //System.out.println("SK["+i+"]: " + Sk[i] + " | Acc: " + acumulado + " | P: " + p + " | Result: " + result);
        }
        
        for(int i = 0; i < colunas; i++) {
            for(int j = 0; j < linhas; j++) {
                int valor = matriz[i][j];
                mAux[i][j] = Sk[valor];
            }    
        }
        
        return mAux;
    }
    
    /**
     * Realiza a equalização do histograma de uma matriz MxNx3
     *
     * @param matriz Matriz a ser equalizada
     * @param L Número de bits máximos da imagem
     * @return int[][]
     * @since 1.3
     */
    public static int[][][] equalizarPPM(int[][][] matriz, int L) {
        double[] somatoria = new double[L];
        double[] somatoria1 = new double[L];
        double[] somatoria2 = new double[L];
        int[] Sk = new int[L];
        int[] Sk1 = new int[L];
        int[] Sk2 = new int[L];
        int colunas = matriz.length;
        int linhas = matriz[0].length;
        
        int[][][] mAux = new int[colunas][linhas][3];
        
        for(int i = 0; i < colunas; i++) {
            for(int j = 0; j < linhas; j++) {
                int valor = matriz[i][j][0];
                somatoria[valor] += 1;
                
                int valor1 = matriz[i][j][1];
                somatoria1[valor1] += 1;
                
                int valor2 = matriz[i][j][2];
                somatoria2[valor2] += 1;
            }    
        }
        
        double acumulado = 0;
        double acumulado1 = 0;
        double acumulado2 = 0;
        
        for(int i = 0; i < Sk.length; i++) {
            double p = somatoria[i]/(colunas*linhas);
            acumulado += p;
            double result = (L-2)*acumulado;
            Sk[i] = (int)Math.round(result);
            
            double p1 = somatoria1[i]/(colunas*linhas);
            acumulado1 += p1;
            double result1 = (L-2)*acumulado1;
            Sk1[i] = (int)Math.round(result1);
            
            double p2 = somatoria2[i]/(colunas*linhas);
            acumulado2 += p2;
            double result2 = (L-2)*acumulado2;
            Sk2[i] = (int)Math.round(result2);
        }
        
        for(int i = 0; i < colunas; i++) {
            for(int j = 0; j < linhas; j++) {
                int valor = matriz[i][j][2];
                mAux[i][j][0] = Sk[valor];
                
                int valor1 = matriz[i][j][1];
                mAux[i][j][1] = Sk1[valor1];
                
                int valor2 = matriz[i][j][2];
                mAux[i][j][2] = Sk2[valor2];
            }    
        }
        
        return mAux;
    }
    
    /**
     * Aplica o filtro laplaciando em uma matriz NxM
     *
     * @param matriz Matriz a receber o filtro
     * @param modo Modo como o filtro será aplicado
     * 0 - Leva negativos a 0
     * 1 - Mantém negativos
     * 2 - Leva a zero proporcionalmente
     * @param elemCentral Elemento central do filtro 1 - Positivo 2 - Negativo
     * @return int[][]
     * @since 1.4
     */

    public static int[][] laplaciando(int[][] matriz, int modo, int elemCentral) {
        int aux = elemCentral == 1 ? 1 : -1;
        int[][] filtroLaplaciando = {{0, -1, 0}, {-1, 4, -1}, {0, -1, 0}};
       int largura = matriz.length;
       int altura = matriz[0].length;
       
       int[][] matrizAuxiliar = new int[largura][altura];
       
       for(int i = 0; i < largura; i++) {
           for(int j = 0; j < altura; j++) {
               int soma = 0;
               if((i - 1) >= 0) {
                   if(j - 1 >= 0)
                       soma += aux * filtroLaplaciando[0][0] * matriz[i-1][j-1];
                   if(j + 1 < altura)
                       soma += aux * filtroLaplaciando[0][2] * matriz[i-1][j+1];
                   
                   soma += aux * filtroLaplaciando[0][1] * matriz[i-1][j];
               }
               
               if(j-1 >= 0) {
                   if(i+1 < largura)
                       soma += aux * filtroLaplaciando[2][0] * matriz[i+1][j-1];
                   soma += aux * filtroLaplaciando[1][0] * matriz[i][j-1];
               }
               
               if(i+1 < largura) {
                   if(j+1 < altura)
                       soma += aux * filtroLaplaciando[2][2] * matriz[i+1][j+1];
                   soma += aux * filtroLaplaciando[2][1] * matriz[i+1][j];
               }
               
               if(j + 1 < altura)
                   soma += aux * filtroLaplaciando[1][2] * matriz[i][j+1];
               
               soma += aux * filtroLaplaciando[1][1] * matriz[i][j];
               
               if(modo == 0) {
                if(soma > 255)
                    matrizAuxiliar[i][j] = 255;
                else if(soma < 0)
                    matrizAuxiliar[i][j] = 0;
                else
                    matrizAuxiliar[i][j] = soma;                              
               } else{
                   matrizAuxiliar[i][j] = soma;
               }
           }
       }
       
        if(modo == 2) {
            int minValue = 0;
            for(int i = 0; i < largura; i++) {
                for(int j = 0; j < altura; j++) {
                    //System.out.println(matrizAuxiliar[i][j]);
                    if(matrizAuxiliar[i][j] < minValue)
                        minValue = matrizAuxiliar[i][j];
                }
            }            
            minValue = Math.abs(minValue);

            for(int i = 0; i < largura; i++) {
                for(int j = 0; j < altura; j++) {
                    if(matrizAuxiliar[i][j] < 0)
                        matrizAuxiliar[i][j] += minValue;
                }
            }
        }
       
       return matrizAuxiliar;
    }
    
    /**
     * Aplica o filtro laplaciando com elemento central 8 em uma matriz MxN
     * 
     * @param matriz Matriz a receber o filtro
     * @param modo Modo como o filtro será aplicado
     * 0 - Leva negativos a 0
     * 1 - Mantém negativos
     * 2 - Leva a zero proporcionalmente
     * @param elemCentral Elemento central do filtro 1 - Positivo 2 - Negativo
     * @return int[][]
     * @since 1.4
     */

    public static int[][] laplaciando2(int[][] matriz, int modo, int elemCentral) {
        int aux = elemCentral == 1 ? 1 : -1;
       int[][] filtroLaplaciando = {{-1, -1, -1}, {-1, 8, -1}, {-1, -1, -1}};
       imprimeMatrizPGM(filtroLaplaciando);
       int largura = matriz.length;
       int altura = matriz[0].length;
       
       int[][] matrizAuxiliar = new int[largura][altura];
       
       for(int i = 0; i < largura; i++) {
           for(int j = 0; j < altura; j++) {
               int soma = 0;
               if((i - 1) >= 0) {
                   if(j - 1 >= 0)
                       soma += aux * filtroLaplaciando[0][0] * matriz[i-1][j-1];
                   if(j + 1 < altura)
                       soma += aux * filtroLaplaciando[0][2] * matriz[i-1][j+1];
                   
                   soma += aux * filtroLaplaciando[0][1] * matriz[i-1][j];
               }
               
               if(j-1 >= 0) {
                   if(i+1 < largura)
                       soma += aux * filtroLaplaciando[2][0] * matriz[i+1][j-1];
                   soma += aux * filtroLaplaciando[1][0] * matriz[i][j-1];
               }
               
               if(i+1 < largura) {
                   if(j+1 < altura)
                       soma += aux * filtroLaplaciando[2][2] * matriz[i+1][j+1];
                   soma += aux * filtroLaplaciando[2][1] * matriz[i+1][j];
               }
               
               if(j + 1 < altura)
                   soma += aux * filtroLaplaciando[1][2] * matriz[i][j+1];
               
               soma += aux * filtroLaplaciando[1][1] * matriz[i][j];
               
               if(modo == 0) {
                if(soma > 255)
                    matrizAuxiliar[i][j] = 255;
                else if(soma < 0)
                    matrizAuxiliar[i][j] = 0;
                else
                    matrizAuxiliar[i][j] = soma;                              
               } else{
                   matrizAuxiliar[i][j] = soma;
               }
           }
       }
       
        if(modo == 2) {
            int minValue = 0;
            for(int i = 0; i < largura; i++) {
                for(int j = 0; j < altura; j++) {
                    //System.out.println(matrizAuxiliar[i][j]);
                    if(matrizAuxiliar[i][j] < minValue)
                        minValue = matrizAuxiliar[i][j];
                }
            }            
            minValue = Math.abs(minValue);

            for(int i = 0; i < largura; i++) {
                for(int j = 0; j < altura; j++) {
                    if(matrizAuxiliar[i][j] < 0)
                        matrizAuxiliar[i][j] += minValue;
                }
            }
        }
       
       return matrizAuxiliar;
    }

    /**
     * Aplica o filtro médio em uma matriz MxN
     *
     * @param matriz Matriz a receber o filtro
     * @param n Dimensão do filtro     
     * @return int[][]
     * @since 1.4
     */

    public static int[][] filtroMedio(int[][] matriz, int n) {
        int largura = matriz.length;
        int altura = matriz[0].length;
        int offset = n/2;
        int[][] mAux = new int[largura][altura];
       
        for(int i = 0; i < largura; i++) {
           for(int j = 0; j < altura; j++) {
                double soma = 0;
                for(int k = -1*offset; k <= offset; k++) {
                    for(int l = -1*offset; l <= offset; l++) {
                        try {
                            soma += ((double)1/(n*n)) * matriz[i+k][j+l];
                            //System.out.println("Soma: " + soma + " | Matriz: " + matriz[i+k][j+l] + " | Mult: " + (((double)coef/(n*n))));
                        } catch(Exception ex) {
                            continue;
                        }
                    }
                }
                if((int)Math.round(soma) > 255)
                    mAux[i][j] = 255;
                else
                    mAux[i][j] = (int)Math.round(soma);
           }
       }
       
       return mAux;
    }

    /**
     * Aplica o filtro da mediana em uma matriz MxN
     *
     * @param matriz Matriz a receber o filtro   
     * @return int[][]
     * @since 1.6
     */

    public static int[][] filtroMediana(int[][] matriz) {
        int largura = matriz.length;
        int altura = matriz[0].length;
        int[][] mAux = new int[largura][altura];
       
        for(int i = 0; i < largura; i++) {
           for(int j = 0; j < altura; j++) {
                int[] neiborhood = new int[9];
                neiborhood[0] = matriz[i][j];
                neiborhood[1] = i - 1 >= 0 ? matriz[i-1][j] : 0;
                neiborhood[2] = j - 1 >= 0 ? matriz[i][j-1] : 0;
                neiborhood[3] = i + 1 < largura ? matriz[i+1][j] : 0;
                neiborhood[4] = j + 1 < altura ? matriz[i][j+1] : 0;
                neiborhood[5] = i - 1 >= 0 && j - 1 >= 0 ? matriz[i-1][j-1] : 0;
                neiborhood[6] = i - 1 >= 0 && j + 1 < altura ? matriz[i-1][j+1] : 0;
                neiborhood[7] = i + 1 < largura && j - 1 >= 0 ? matriz[i+1][j-1] : 0;
                neiborhood[8] = i + 1 < largura && j + 1 < altura ? matriz[i+1][j+1] : 0;

                Arrays.sort(neiborhood);
                int mediana = neiborhood[9/2];

                mAux[i][j] = mediana;
           }
       }
       
       return mAux;
    }

    /**
     * Aplica a binarização em uma matriz MxN
     *
     * @param matriz Matriz a receber a binarização
     * @param k Ponto de mudança     
     * @return int[][]
     * @since 1.4
     */
    
    public static int[][] binarizacao(int[][] matriz, int k) {
        int largura = matriz.length;
        int altura = matriz[0].length;
        
        int[][] mAux = new int[largura][altura];
       
        for(int i = 0; i < largura; i++) {
           for(int j = 0; j < altura; j++) {
               if(matriz[i][j] < k)
                   mAux[i][j] = 0;
               else if(matriz[i][j] >= k)
                   mAux[i][j] = 255;               
           }
        }
        
        return mAux;
    }

    /**
     * Faz a extração dos canais RGB para HSI
     *
     * @param channelList Lista de canais RGB
     * @return ArrayList<int[][]>
     * @since 1.5
     */

    public static ArrayList<int[][]> RGB2HSIConvert(ArrayList<int[][]> channelList) {
        int[][] channelR = channelList.get(0);
        int[][] channelG = channelList.get(1);
        int[][] channelB = channelList.get(2);

        int largura = channelR.length;
        int altura = channelR[0].length;    

        int[][] channelH = new int[largura][altura];
        int[][] channelS = new int[largura][altura];
        int[][] channelI = new int[largura][altura];                   

        for(int k = 0; k < largura; k++) {
            for(int j = 0; j < altura; j++) {
                double R = channelR[k][j];
                double G = channelG[k][j];
                double B = channelB[k][j];

                double r = (R/(R + B + G));
                double g = (G/(R + B + G));
                double b = (B/(R + B + G));
            
                double h = 0;
                double s = 0;
                double i = 0;

                double numerator = 0.5 * ((r - g) + (r - b));
                double denominator = Math.sqrt(Math.pow((r-g), 2) + ((r-b)*(g-b)));

                if(b > g) {
                    h = 2*Math.PI - Math.cos(numerator/denominator);

                    if(h > 2.0*Math.PI)
                        h = 2.0 * Math.PI;
                    
                    if(h < Math.PI)
                        h = Math.PI;
                } else {                                                           
                    h = Math.cos(numerator/denominator);

                    if(h > Math.PI)
                        h = Math.PI;

                    if(h < 0)
                        h = 0;
                    
                }

                double min = Math.min(r, g);
                min = Math.min(min, b);

                s = 1.0 - 3.0*min;

                if(s > 1.0)
                    s = 1.0;
                
                if(s < 0)
                    s = 0;

                i = (R + G + B)/(3.0*255.0);

                if(i > 1.0)
                    i = 1.0;
                
                if(i < 0)
                    i = 0;

                channelH[k][j] = (int)Math.round(h*255.0);
                channelS[k][j] = (int)Math.round(s*255.0);
                channelI[k][j] = (int)Math.round(i*255.0);                
            }
        }

        ArrayList<int[][]> channels = new ArrayList<>();
        channels.add(channelH);
        channels.add(channelS);
        channels.add(channelI);

        return channels;
    }

    /**
     * Efetua a soma de uma constante K a todos os pixeis da imagem
     *
     * @param a Primeira matriz
     * @param k constante a ser somada
     * @return int[][]
     * @since 1.0
     */
    public static int[][] SumKPGM(int[][] a, int k) {
        int largura = a.length;
        int altura = a[0].length;
        int[][] aux = new int[largura][altura];

        for (int i = 0; i < largura; i++) {
            for (int j = 0; j < altura; j++) {
                int data = a[i][j] + k;

                if (data <= 255) {
                    aux[i][j] = data;
                } else {
                    aux[i][j] = 255;
                }
            }
        }

        return aux;
    }

    /**
     * Efetua a soma de uma constante K a todos os pixeis de todos os canais
     * da imagem
     *
     * @param a Primeira matriz
     * @param k constante a ser somada
     * @return int[][][]
     * @since 1.6
     */
    public static int[][][] SumKPPM(int[][][] a, int k) {
        int largura = a.length;
        int altura = a[0].length;
        int[][][] aux = new int[largura][a[0].length][3];

        for (int i = 0; i < largura; i++) {
            for (int j = 0; j < altura; j++) {
                final int R = a[i][j][0] + k;
                final int G = a[i][j][1] + k;
                final int B = a[i][j][2] + k;

                if(R > 255)
                    aux[i][j][0] = 255;
                else
                    aux[i][j][0] = R;

                if(G > 255)
                    aux[i][j][1] = 255;
                else
                    aux[i][j][1] = G;

                if(B > 255)
                    aux[i][j][2] = 255;
                else
                    aux[i][j][2] = B;
            }
        }

        return aux;
    }

    /**
     * Efetua a subtração de uma constante K a todos os pixeis da imagem
     *
     * @param a Primeira matriz
     * @param k constante a ser subtraida
     * @return int[][]
     * @since 1.0
     */
    public static int[][] SubKPGM(int[][] a, int k) {
        int largura = a.length;
        int altura = a[0].length;
        int[][] aux = new int[largura][altura];

        for (int i = 0; i < largura; i++) {
            for (int j = 0; j < altura; j++) {
                int data = a[i][j] - k;

                if (data >= 0) {
                    aux[i][j] = data;
                } else {
                    aux[i][j] = 0;
                }
            }
        }

        return aux;
    }

    /**
     * Efetua a subtração de uma constante K a todos os pixeis de todos os canais
     * da imagem
     *
     * @param a Primeira matriz
     * @param k constante a ser subtraida
     * @return int[][][]
     * @since 1.6
     */
    public static int[][][] SubKPPM(int[][][] a, int k) {
        int largura = a.length;
        int altura = a[0].length;
        int[][][] aux = new int[largura][a[0].length][3];

        for (int i = 0; i < largura; i++) {
            for (int j = 0; j < altura; j++) {
                final int R = a[i][j][0] - k;
                final int G = a[i][j][1] - k;
                final int B = a[i][j][2] - k;

                if(R < 0)
                    aux[i][j][0] = 0;
                else
                    aux[i][j][0] = R;

                if(G < 0)
                    aux[i][j][1] = 0;
                else
                    aux[i][j][1] = G;

                if(B < 0)
                    aux[i][j][2] = 0;
                else
                    aux[i][j][2] = B;
            }
        }

        return aux;
    }

    /**
     * Efetua a subtração de uma constante K a todos os pixeis da imagem
     *
     * @param a Primeira matriz
     * @param k constante a ser subtraida
     * @return int[][]
     * @since 1.0
     */
    public static int[][] MultKPGM(int[][] a, double k) {
        int largura = a.length;
        int altura = a[0].length;
        int[][] aux = new int[largura][altura];

        for (int i = 0; i < largura; i++) {
            for (int j = 0; j < altura; j++) {
                int data = (int)Math.round(a[i][j] * k);

                if (data > 255) {
                    aux[i][j] = 255;
                } else if(data < 0){
                    aux[i][j] = 0;
                } else {
                    aux[i][j] = data;
                }
            }
        }

        return aux;
    }

    /**
     * Efetua a subtração de uma constante K a todos os pixeis de todos os canais
     * da imagem
     *
     * @param a Primeira matriz
     * @param k constante a ser subtraida
     * @return int[][][]
     * @since 1.6
     */
    public static int[][][] MultKPPM(int[][][] a, double k) {
        int largura = a.length;
        int altura = a[0].length;
        int[][][] aux = new int[largura][a[0].length][3];

        for (int i = 0; i < largura; i++) {
            for (int j = 0; j < altura; j++) {
                final int R = (int)Math.round(a[i][j][0] * k);
                final int G = (int)Math.round(a[i][j][1] * k);
                final int B = (int)Math.round(a[i][j][2] * k);

                if(R < 0)
                    aux[i][j][0] = 0;
                else if(R > 255)
                    aux[i][j][0] = 255;
                else
                    aux[i][j][0] = R;

                if(G < 0)
                    aux[i][j][1] = 0;
                else if(G > 255)
                    aux[i][j][1] = 255;
                else
                    aux[i][j][1] = G;

                if(B < 0)
                    aux[i][j][2] = 0;
                else if(B > 255)
                    aux[i][j][2] = 255;
                else
                    aux[i][j][2] = B;
            }
        }

        return aux;
    }
}
