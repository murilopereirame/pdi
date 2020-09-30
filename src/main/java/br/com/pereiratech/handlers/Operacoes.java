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
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

                if(!st.startsWith("#")) {
                    finalStr[index] = st;
                    index++;
                }
                
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
        Singleton.getInstance().setOriginalPGM(aux);
        Singleton.getInstance().setHeaderOriginal(headers);
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
        Singleton.getInstance().setOriginalPPM(aux);
        Singleton.getInstance().setHeaderOriginal(headers);
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
        Singleton.getInstance().setOriginalPGM(aux);
        Singleton.getInstance().setHeaderOriginal(headers);
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
        Singleton.getInstance().setOriginalPPM(aux);
        Singleton.getInstance().setHeaderOriginal(headers);
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
        Singleton.getInstance().setOriginalPGM(aux);
        Singleton.getInstance().setHeaderOriginal(headers);
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
        Singleton.getInstance().setOriginalPPM(aux);
        Singleton.getInstance().setHeaderOriginal(headers);
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

        Pattern pattern = Pattern.compile("(#.*?)(?=\n|\r)");
        Matcher matcher = pattern.matcher(st);

        while(matcher.find()) {
            st = st.replace(matcher.group(1)+System.getProperty("line.separator"), "");
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

        String content = tipo + System.getProperty("line.separator") + width + " " + height + System.getProperty("line.separator") +
                + scale + System.getProperty("line.separator");

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

        final File file2 = new File(fileName);
        
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
    public static boolean gravarCanaisSeparados(int[][][] matriz, String R, String G, String B) {
        String[] headers = Singleton.getInstance().getHeaderOriginal().clone();

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
        Singleton.getInstance().setOriginalPGM(r);
        Singleton.getInstance().setHeaderOriginal(headersCP);
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
        Singleton.getInstance().setOriginalPPM(r);
        Singleton.getInstance().setHeaderOriginal(headersCP);
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
        Singleton.getInstance().setOriginalPPM(r);
        Singleton.getInstance().setHeaderOriginal(headers);
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
        Singleton.getInstance().setOriginalPGM(r);
        Singleton.getInstance().setHeaderOriginal(headers);
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
        Singleton.getInstance().setOriginalPGM(finalMatriz);
        Singleton.getInstance().setHeaderOriginal(headers);
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
        Singleton.getInstance().setOriginalPPM(finalMatriz);
        Singleton.getInstance().setHeaderOriginal(headers);
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
        Singleton.getInstance().setOriginalPGM(finalMatriz);
        Singleton.getInstance().setHeaderOriginal(headers);
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
        Singleton.getInstance().setOriginalPPM(finalMatriz);
        Singleton.getInstance().setHeaderOriginal(headers);
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
        Singleton.getInstance().setOriginalPGM(finalMatriz);
        Singleton.getInstance().setHeaderOriginal(headers);
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
        Singleton.getInstance().setOriginalPPM(finalMatriz);
        Singleton.getInstance().setHeaderOriginal(headers);
        Matrix.gravarMatrizEmArquivoPPM(finalMatriz, output, headers);
    }
    /**
     * Realiza a aplicação do filtro laplaciando com elemento central 4
     * em uma matriz MxN
     * 
     * @param matriz Matriz a ser fatiada
     * @param headers Cabeçalho do arquivo original
     * @param output Caminho do arquivo final aplicado na função
     * @param modo Modo como o filtro será aplicado
     * 0 - Leva negativos a 0
     * 1 - Mantém negativos
     * 2 - Leva a zero proporcionalmente
     * @since 1.4
     */
    public static void laplaciando(int[][] matriz, String[] headers, String output, int modo, int sinal) {
        int[][] finalMatriz = Matrix.laplaciando(matriz, modo, sinal);
        Singleton.getInstance().setOriginalPGM(finalMatriz);
        Singleton.getInstance().setHeaderOriginal(headers);
        Matrix.gravarMatrizEmArquivoPGM(finalMatriz, output, headers);       
    }
    /**
     * Realiza a aplicação do filtro laplaciando com elemento central 4
     * em uma matriz MxN e soma com a matriz original
     * 
     * @param matriz Matriz a ser filtrada
     * @param headers Cabeçalho do arquivo original
     * @param output Caminho do arquivo final aplicado na função
     * @param modo Modo como o filtro será aplicado
     * 0 - Leva negativos a 0
     * 1 - Mantém negativos
     * 2 - Leva a zero proporcionalmente
     * @since 1.4
     */
    public static void laplaciandoPlusOrig(int[][] matriz, String[] headers, String output, int modo, int sinal) {
        int[][] finalMatriz = Matrix.laplaciando(matriz, modo, sinal);
        finalMatriz = Matrix.somaMatrizesPGM(finalMatriz, matriz, 255);
        Singleton.getInstance().setOriginalPGM(finalMatriz);
        Singleton.getInstance().setHeaderOriginal(headers);
        Matrix.gravarMatrizEmArquivoPGM(finalMatriz, output, headers); 
    }
    /**
     * Realiza a aplicação do filtro laplaciando com elemento central 8
     * em uma matriz MxN
     * 
     * @param matriz Matriz a ser filtrada
     * @param headers Cabeçalho do arquivo original
     * @param output Caminho do arquivo final aplicado na função
     * @param modo Modo como o filtro será aplicado
     * 0 - Leva negativos a 0
     * 1 - Mantém negativos
     * 2 - Leva a zero proporcionalmente
     * @since 1.4
     */
    public static void laplaciando2(int[][] matriz, String[] headers, String output, int modo, int sinal) {
        int[][] finalMatriz = Matrix.laplaciando2(matriz, modo, sinal);
        Singleton.getInstance().setOriginalPGM(finalMatriz);
        Singleton.getInstance().setHeaderOriginal(headers);
        Matrix.gravarMatrizEmArquivoPGM(finalMatriz, output, headers);       
    }
    /**
     * Realiza a aplicação do filtro laplaciando com elemento central 8
     * em uma matriz MxN e soma com a matriz original
     * 
     * @param matriz Matriz a ser filtrada
     * @param headers Cabeçalho do arquivo original
     * @param output Caminho do arquivo final aplicado na função
     * @param modo Modo como o filtro será aplicado
     * 0 - Leva negativos a 0
     * 1 - Mantém negativos
     * 2 - Leva a zero proporcionalmente
     * @since 1.4
     */
    public static void laplaciando2PlusOrig(int[][] matriz, String[] headers, String output, int modo, int sinal) {
        int[][] finalMatriz = Matrix.laplaciando2(matriz, modo, sinal);
        finalMatriz = Matrix.somaMatrizesPGM(finalMatriz, matriz, 255);
        Singleton.getInstance().setOriginalPGM(finalMatriz);
        Singleton.getInstance().setHeaderOriginal(headers);
        Matrix.gravarMatrizEmArquivoPGM(finalMatriz, output, headers); 
    }
    /**
     * Realiza a aplicação do filtro médio em uma matriz MxN
     * 
     * @param matriz Matriz a ser filtrada
     * @param headers Cabeçalho do arquivo original
     * @param output Caminho do arquivo final aplicado na função
     * @param n Dimensão do filtro
     * @since 1.4
     */
    public static void filtroMedio(int[][] matriz, String[] headers, String output, int n) {
        int[][] finalMatriz = Matrix.filtroMedio(matriz, n);
        Singleton.getInstance().setOriginalPGM(finalMatriz);
        Singleton.getInstance().setHeaderOriginal(headers);
        Matrix.gravarMatrizEmArquivoPGM(finalMatriz, output, headers); 
    }
    /**
     * Realiza a binarização em uma matriz MxN
     * 
     * @param matriz Matriz a ser filtrada
     * @param headers Cabeçalho do arquivo original
     * @param output Caminho do arquivo final aplicado na função
     * @param k Ponto de mudança
     * @since 1.4
     */
    public static void binarizacao(int[][] matriz, String[] headers, String output, int k) {
        int[][] finalMatriz = Matrix.binarizacao(matriz, k);
        Singleton.getInstance().setOriginalPGM(finalMatriz);
        Singleton.getInstance().setHeaderOriginal(headers);
        Matrix.gravarMatrizEmArquivoPGM(finalMatriz, output, headers); 
    }
    /**
     * Realiza a extração de canais RGB em HSI
     * 
     * @param matriz Matriz a ser filtrada
     * @param headers Cabeçalho do arquivo original
     * @param HOut Caminho do arquivo final do canal H
     * @param HOut Caminho do arquivo final do canal S
     * @param HOut Caminho do arquivo final do canal I
     * @since 1.5
     */
    public static void RGB2HSI(int[][][] matriz, String[] headers, String HOut, String SOut, String IOut) {
        ArrayList<int[][]> channelList = extrairCanais(matriz);
        ArrayList<int[][]> converted = Matrix.RGB2HSIConvert(channelList);
        String[] newHeaders = headers.clone();
        
        newHeaders[0] = "P2";
        
        Matrix.gravarMatrizEmArquivoPGM(converted.get(0), HOut, newHeaders);
        Matrix.gravarMatrizEmArquivoPGM(converted.get(1), SOut, newHeaders);
        Matrix.gravarMatrizEmArquivoPGM(converted.get(2), IOut, newHeaders);
    }
    /**
     * Realiza a soma de duas imagens PGM
     * 
     * @param imageA Matriz da imagem inicial
     * @param headers Cabeçalho da imagem inicial
     * @param imageB Matriz da imagem a ser somada com a imagem inicial
     * @param output Caminho para a imagem final
     * @since 1.6
     */
    public static void SumImagePGM(int[][] imageA, int[][] imageB, String[] headers, String output) {
        int[][] finalMatriz = Matrix.somaMatrizesPGM(imageA, imageB, 255);
        Singleton.getInstance().setOriginalPGM(finalMatriz);
        Singleton.getInstance().setHeaderOriginal(headers);
        Matrix.gravarMatrizEmArquivoPGM(finalMatriz, output, headers); 
    }
    /**
     * Realiza a soma de duas imagens PPM
     * 
     * @param imageA Matriz da imagem inicial
     * @param headers Cabeçalho da imagem inicial
     * @param imageB Matriz da imagem a ser somada com a imagem inicial
     * @param output Caminho para a imagem final
     * @since 1.6
     */
    public static void SumImagePPM(int[][][] imageA, int[][][] imageB, String[] headers, String output) {
        int[][][] finalMatriz = Matrix.somaMatrizesPPM(imageA, imageB, 255);
        Singleton.getInstance().setOriginalPPM(finalMatriz);
        Singleton.getInstance().setHeaderOriginal(headers);
        Matrix.gravarMatrizEmArquivoPPM(finalMatriz, output, headers); 
    }
    /**
     * Realiza a soma de duas imagens PGM
     * 
     * @param imageA Matriz da imagem inicial
     * @param headers Cabeçalho da imagem inicial
     * @param imageB Matriz da imagem a ser subtraida da imagem inicial
     * @param output Caminho para a imagem final
     * @since 1.6
     */
    public static void SubImagePGM(int[][] imageA, int[][] imageB, String[] headers, String output) {
        int[][] finalMatriz = Matrix.subtrairMatrizesPGM(imageA, imageB);
        Singleton.getInstance().setOriginalPGM(finalMatriz);
        Singleton.getInstance().setHeaderOriginal(headers);
        Matrix.gravarMatrizEmArquivoPGM(finalMatriz, output, headers); 
    }
    /**
     * Realiza a soma de duas imagens PPM
     * 
     * @param imageA Matriz da imagem inicial
     * @param headers Cabeçalho da imagem inicial
     * @param imageB Matriz da imagem a ser subtraida da imagem inicial
     * @param output Caminho para a imagem final
     * @since 1.6
     */
    public static void SubImagePPM(int[][][] imageA, int[][][] imageB, String[] headers, String output) {
        int[][][] finalMatriz = Matrix.subtrairMatrizesPPM(imageA, imageB);
        Singleton.getInstance().setOriginalPPM(finalMatriz);
        Singleton.getInstance().setHeaderOriginal(headers);
        Matrix.gravarMatrizEmArquivoPPM(finalMatriz, output, headers); 
    }
    /**
     * Realiza a multiplicação da matriz por um K
     * 
     * @param imageA Matriz da imagem inicial
     * @param headers Cabeçalho da imagem inicial
     * @param k Constante a ser aplicada na matriz
     * @param output Caminho para a imagem final
     * @since 1.6
     */
    public static void MultImageKPGM(int[][] imageA, String[] headers, String output, int k) {
        int[][] finalMatriz = Matrix.MultKPGM(imageA, k);
        Singleton.getInstance().setOriginalPGM(finalMatriz);
        Singleton.getInstance().setHeaderOriginal(headers);
        Matrix.gravarMatrizEmArquivoPGM(finalMatriz, output, headers); 
    }
    /**
     * Realiza a multiplicação da matriz por um K
     * 
     * @param imageA Matriz da imagem inicial
     * @param headers Cabeçalho da imagem inicial
     * @param k Constante a ser aplicada na matriz
     * @param output Caminho para a imagem final
     * @since 1.6
     */
    public static void MultImageKPPM(int[][][] imageA, String[] headers, String output, int k) {
        int[][][] finalMatriz = Matrix.MultKPPM(imageA, k);
        Singleton.getInstance().setOriginalPPM(finalMatriz);
        Singleton.getInstance().setHeaderOriginal(headers);
        Matrix.gravarMatrizEmArquivoPPM(finalMatriz, output, headers); 
    }

    /**
     * Realiza a soma de uma constante k na matriz
     * 
     * @param imageA Matriz da imagem inicial
     * @param headers Cabeçalho da imagem inicial
     * @param k Constante a ser somada na matriz
     * @param output Caminho para a imagem final
     * @since 1.6
     */
    public static void SumImageKPGM(int[][] imageA, String[] headers, String output, int k) {
        int[][] finalMatriz = Matrix.SumKPGM(imageA, k);
        Singleton.getInstance().setOriginalPGM(finalMatriz);
        Singleton.getInstance().setHeaderOriginal(headers);
        Matrix.gravarMatrizEmArquivoPGM(finalMatriz, output, headers); 
    }
    /**
     * Realiza a soma de uma constante k na matriz
     * 
     * @param imageA Matriz da imagem inicial
     * @param headers Cabeçalho da imagem inicial
     * @param k Constante a ser somada na matriz
     * @param output Caminho para a imagem final
     * @since 1.6
     */
    public static void SumImageKPPM(int[][][] imageA, String[] headers, String output, int k) {
        int[][][] finalMatriz = Matrix.SumKPPM(imageA, k);
        Singleton.getInstance().setOriginalPPM(finalMatriz);
        Singleton.getInstance().setHeaderOriginal(headers);
        Matrix.gravarMatrizEmArquivoPPM(finalMatriz, output, headers); 
    }
     /**
     * Realiza a aplicação do filtro da mediana na matriz
     * 
     * @param imageA Matriz da imagem inicial
     * @param headers Cabeçalho da imagem inicial
     * @param k Constante a ser somada na matriz
     * @param output Caminho para a imagem final
     * @since 1.6
     */
    public static void filtroMediana(int[][] matriz, String[] headers, String output) {
        int[][] finalMatriz = Matrix.filtroMediana(matriz);
        Singleton.getInstance().setOriginalPGM(finalMatriz);
        Singleton.getInstance().setHeaderOriginal(headers);
        Matrix.gravarMatrizEmArquivoPGM(finalMatriz, output, headers); 
    }
    
    public static void processarInstrucoes(Map<String, ArrayList<String>> operacoes) {
        for (String key : operacoes.keySet()) {
            switch(key) {
                case "load":
                    carregarImagem(operacoes.get(key).get(0));                 
                break;

                case "save":
                    if(Singleton.getInstance().getFormato() == "pgm")
                        Matrix.gravarMatrizEmArquivoPGM(Singleton.getInstance().getOriginalPGM(), operacoes.get(key).get(0), Singleton.getInstance().getHeaderOriginal());
                    else
                        Matrix.gravarMatrizEmArquivoPPM(Singleton.getInstance().getOriginalPPM(), operacoes.get(key).get(0), Singleton.getInstance().getHeaderOriginal());
                break;

                case "90left":
                    rotacionar(Singleton.getInstance().getOriginalPGM(), 1, "temp.pgm", Singleton.getInstance().getHeaderOriginal());
                break;

                case "90right":
                    rotacionar(Singleton.getInstance().getOriginalPGM(), 0, "temp.pgm", Singleton.getInstance().getHeaderOriginal());
                break;

                case "flipv": 
                    if(Singleton.getInstance().getFormato() == "pgm")
                        flipar(Singleton.getInstance().getOriginalPGM(), 1, "temp.pgm", Singleton.getInstance().getHeaderOriginal());
                    else
                        fliparPPM(Singleton.getInstance().getOriginalPPM(), 1, "temp.pgm", Singleton.getInstance().getHeaderOriginal());
                break;

                case "fliph": 
                    if(Singleton.getInstance().getFormato() == "pgm")
                        flipar(Singleton.getInstance().getOriginalPGM(), 0, "temp.pgm", Singleton.getInstance().getHeaderOriginal());
                    else
                        fliparPPM(Singleton.getInstance().getOriginalPPM(), 0, "temp.pgm", Singleton.getInstance().getHeaderOriginal());
                break;

                case "gama": 
                    if(Singleton.getInstance().getFormato() == "pgm")
                        gama(Singleton.getInstance().getOriginalPGM(), Double.parseDouble(operacoes.get(key).get(0)), Double.parseDouble(operacoes.get(key).get(1)), Singleton.getInstance().getHeaderOriginal(), "temp.pgm");
                    else
                        gamaPPM(Singleton.getInstance().getOriginalPPM(), Double.parseDouble(operacoes.get(key).get(0)), Double.parseDouble(operacoes.get(key).get(1)), Singleton.getInstance().getHeaderOriginal(), "temp.ppm");
                break;

                case "equalizar":
                    if(Singleton.getInstance().getFormato() == "pgm")
                        equalizar(Singleton.getInstance().getOriginalPGM(), Singleton.getInstance().getHeaderOriginal(), "temp.pgm");
                    else
                        equalizarPPM(Singleton.getInstance().getOriginalPPM(), Singleton.getInstance().getHeaderOriginal(), "temp.ppm");          
                break;

                case "clarear":
                    if(Singleton.getInstance().getFormato() == "pgm")
                        clarearPGM(Singleton.getInstance().getOriginalPGM(), Integer.parseInt(operacoes.get(key).get(0)), "temp.pgm", Singleton.getInstance().getHeaderOriginal());
                    else
                        clarearPPM(Singleton.getInstance().getOriginalPPM(), Integer.parseInt(operacoes.get(key).get(0)),  "temp.ppm", Singleton.getInstance().getHeaderOriginal());          
                break;

                case "escurecer":
                    if(Singleton.getInstance().getFormato() == "pgm")
                        escurecerPGM(Singleton.getInstance().getOriginalPGM(), Integer.parseInt(operacoes.get(key).get(0)), "temp.pgm", Singleton.getInstance().getHeaderOriginal());
                    else
                        escurecerPPM(Singleton.getInstance().getOriginalPPM(), Integer.parseInt(operacoes.get(key).get(0)),  "temp.ppm", Singleton.getInstance().getHeaderOriginal());          
                break;

                case "fatiar":
                    if(Singleton.getInstance().getFormato() == "pgm")
                        fatiar(Singleton.getInstance().getOriginalPGM(), Integer.parseInt(operacoes.get(key).get(0)), Integer.parseInt(operacoes.get(key).get(1)), Integer.parseInt(operacoes.get(key).get(2)), Integer.parseInt(operacoes.get(key).get(3)), Integer.parseInt(operacoes.get(key).get(4)), "temp.pgm", Singleton.getInstance().getHeaderOriginal());
                    else            
                        fatiarPPM(Singleton.getInstance().getOriginalPPM(), Integer.parseInt(operacoes.get(key).get(0)),  Integer.parseInt(operacoes.get(key).get(1)), Integer.parseInt(operacoes.get(key).get(2)), Integer.parseInt(operacoes.get(key).get(3)), Integer.parseInt(operacoes.get(key).get(4)), "temp.ppm", Singleton.getInstance().getHeaderOriginal());          
                break;

                case "negativo":
                    if(Singleton.getInstance().getFormato() == "pgm")
                        negativaPGM(Singleton.getInstance().getOriginalPGM(), "temp.pgm", Singleton.getInstance().getHeaderOriginal());
                    else            
                        negativaPPM(Singleton.getInstance().getOriginalPPM(), "temp.ppm", Singleton.getInstance().getHeaderOriginal());          
                break;

                case "split":
                    if(Singleton.getInstance().getFormato() == "ppm") {
                        gravarCanaisSeparados(Singleton.getInstance().getOriginalPPM(), operacoes.get(key).get(0), operacoes.get(key).get(1), operacoes.get(key).get(2));
                    }      
                break;

                case "join":
                    juntarCanais(operacoes.get(key).get(0), operacoes.get(key).get(1), operacoes.get(key).get(2));
                break;

                case "laplaciano":
                    laplaciando(Singleton.getInstance().getOriginalPGM(), Singleton.getInstance().getHeaderOriginal(), "temp.pgm", Integer.parseInt(operacoes.get(key).get(0)), Integer.parseInt(operacoes.get(key).get(1)));
                break;

                case "laplaciano2":
                    laplaciando2(Singleton.getInstance().getOriginalPGM(), Singleton.getInstance().getHeaderOriginal(), "temp.pgm", Integer.parseInt(operacoes.get(key).get(0)), Integer.parseInt(operacoes.get(key).get(1)));
                break;

                case "media":
                    filtroMedio(Singleton.getInstance().getOriginalPGM(), Singleton.getInstance().getHeaderOriginal(), "temp.pgm", Integer.parseInt(operacoes.get(key).get(0)));
                break;

                case "mediana":
                    filtroMediana(Singleton.getInstance().getOriginalPGM(), Singleton.getInstance().getHeaderOriginal(), "temp.pgm");
                break;

                case "bin":
                    binarizacao(Singleton.getInstance().getOriginalPGM(), Singleton.getInstance().getHeaderOriginal(), "temp.pgm", Integer.parseInt(operacoes.get(key).get(0)));
                break;

                case "rgb2hsi":
                    RGB2HSI(Singleton.getInstance().getOriginalPPM(), Singleton.getInstance().getHeaderOriginal(), operacoes.get(key).get(0), operacoes.get(key).get(1), operacoes.get(key).get(2));
                break;
                
                case "multk":
                    if(Singleton.getInstance().getFormato() == "pgm")
                        MultImageKPGM(Singleton.getInstance().getOriginalPGM(), Singleton.getInstance().getHeaderOriginal(), "temp.pgm", Integer.parseInt(operacoes.get(key).get(0)));
                    else
                        MultImageKPPM(Singleton.getInstance().getOriginalPPM(), Singleton.getInstance().getHeaderOriginal(), "temp.ppm", Integer.parseInt(operacoes.get(key).get(0)));
                break;

                case "sumk":
                    if(Singleton.getInstance().getFormato() == "pgm")
                        SumImageKPGM(Singleton.getInstance().getOriginalPGM(), Singleton.getInstance().getHeaderOriginal(), "temp.pgm", Integer.parseInt(operacoes.get(key).get(0)));
                    else
                        SumImageKPPM(Singleton.getInstance().getOriginalPPM(), Singleton.getInstance().getHeaderOriginal(), "temp.ppm", Integer.parseInt(operacoes.get(key).get(0)));
                break;

                case "sumimg":
                    if(Singleton.getInstance().getFormato() == "pgm") {
                        int[][] secondSumImg = lerImagemPGM(operacoes.get(key).get(0));
                        SumImagePGM(Singleton.getInstance().getOriginalPGM(), secondSumImg, Singleton.getInstance().getHeaderOriginal(), "temp.pgm");
                    }
                    else {
                        int[][][] secondSumImg = lerImagemPPM(operacoes.get(key).get(0));
                        SumImagePPM(Singleton.getInstance().getOriginalPPM(), secondSumImg, Singleton.getInstance().getHeaderOriginal(), "temp.ppm");
                    }
                break;

                case "subimg":
                    if(Singleton.getInstance().getFormato() == "pgm") {
                        int[][] secondSubImg = lerImagemPGM(operacoes.get(key).get(0));
                        SubImagePGM(Singleton.getInstance().getOriginalPGM(), secondSubImg, Singleton.getInstance().getHeaderOriginal(), "temp.pgm");
                    }
                    else {
                        int[][][] secondSubImg = lerImagemPPM(operacoes.get(key).get(0));
                        SubImagePPM(Singleton.getInstance().getOriginalPPM(), secondSubImg, Singleton.getInstance().getHeaderOriginal(), "temp.ppm");
                    }
                break;

                default:
                    System.out.println("Operação inválida! Op: " + key);
                break;
            }
        }
    }

    public static void juntarCanais(String R, String G, String B) {
        ArrayList<int[][]> canais = new ArrayList<int[][]>();
        String[] headers = extrairHeader(R);
        headers[0] = "P3";
        String[] tamanhosLidos = headers[1].split(" ");

        int[][] matrizR = Matrix.lerMatrizEmArquivoPGM(R, Integer.parseInt(tamanhosLidos[0]), Integer.parseInt(tamanhosLidos[1]));
        int[][] matrizG = Matrix.lerMatrizEmArquivoPGM(G, Integer.parseInt(tamanhosLidos[0]), Integer.parseInt(tamanhosLidos[1]));
        int[][] matrizB = Matrix.lerMatrizEmArquivoPGM(B, Integer.parseInt(tamanhosLidos[0]), Integer.parseInt(tamanhosLidos[1]));


        canais.add(matrizR);
        canais.add(matrizG);
        canais.add(matrizB);        

        int[][][] finalMatriz = juntarCanais(canais, Integer.parseInt(tamanhosLidos[0]), Integer.parseInt(tamanhosLidos[1]));
        Singleton.getInstance().setOriginalPPM(finalMatriz);
        Singleton.getInstance().setHeaderOriginal(headers);
        Matrix.gravarMatrizEmArquivoPPM(finalMatriz, "temp.ppm", headers); 
    }

    public static int[][] lerImagemPGM(String fileName) {
        String[] h = Operacoes.extrairHeader(fileName);
        int largura = Integer.parseInt(h[1].split(" ")[0]);
        int altura = Integer.parseInt(h[1].split(" ")[1]);
        
        Operacoes.normalizarImagemPGM(fileName);
        int[][] m = Matrix.lerMatrizEmArquivoPGM(fileName, altura, largura);
        return m;
    }

    public static int[][][] lerImagemPPM(String fileName) {
        String[] h = Operacoes.extrairHeader(fileName);
        int largura = Integer.parseInt(h[1].split(" ")[0]);
        int altura = Integer.parseInt(h[1].split(" ")[1]);
        
        Operacoes.normalizarImagemPPM(fileName);
        int[][][] m = Matrix.lerMatrizEmArquivoPPM(fileName, altura, largura);
        return m;
    }

    public static void carregarImagem(String fileName) {
        String[] h = Operacoes.extrairHeader(fileName);
        int largura = Integer.parseInt(h[1].split(" ")[0]);
        int altura = Integer.parseInt(h[1].split(" ")[1]);
        Singleton.getInstance().setHeaderOriginal(h);

        if(fileName.toLowerCase().endsWith(".pgm")) {
            Operacoes.normalizarImagemPGM(fileName);
            int[][] m = Matrix.lerMatrizEmArquivoPGM(fileName, altura, largura);
            Singleton.getInstance().setOriginalPGM(m);
            Singleton.getInstance().setFormato("pgm");
        }
        else {
            Operacoes.normalizarImagemPPM(fileName);
            int[][][] m = Matrix.lerMatrizEmArquivoPPM(fileName, altura, largura);
            Singleton.getInstance().setOriginalPPM(m);
            Singleton.getInstance().setFormato("ppm");
        }     
    }
    

    public static Map<String, ArrayList<String>> lerInstrucoes(String fileName) {
        Map<String, ArrayList<String>> operacoes = new LinkedHashMap<String, ArrayList<String>>();
        final File file = new File(fileName);

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (final FileNotFoundException e1) {
            e1.printStackTrace();
        }
        String st;
        try {
            while ((st = br.readLine()) != null) {
                if(!st.startsWith("#")) {
                    String[] splited = st.split(" ");
                    String action = splited[0];
                    ArrayList<String> args = new ArrayList<>();
                    for(int i = 1; i < splited.length; i++) {
                        args.add(splited[i]);
                    }
                    operacoes.put(action, args);
                }
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }

        return operacoes;
    }
}
