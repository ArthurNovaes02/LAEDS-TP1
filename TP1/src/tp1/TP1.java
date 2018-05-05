/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp1;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Scanner;



/**
 *
 * @author arthur
 */
public class TP1 {

    public static void main(String[] args) throws Exception{
        ArvorePatricia a = new ArvorePatricia(16);

        StringBuilder s;
                
        ExtraiPalavra palavras = new ExtraiPalavra("delim", "exemplo1");
        String palavra = null;

        String[] palavrasArray = new String[100000000];
        
        int linha = 0, coluna = 0;

        int totPal = 0;
        
        while ((palavra = palavras.proximaPalavra())!= null) {
            // O primeiro espaço depois da palavra não é codificado
            if(palavra.equals(" ")) continue;
//            palavrasList.add(palavra);
            palavrasArray[totPal] = palavra;
            totPal++;
//            System.out.println(palavrasArray[totPal-1]);

        }
        palavras.fecharArquivos();
        
        
        
        // Converte cada palavra em um vetor de char
        char [][] caracteres = null;
//        int [][] caracteresInt = null;
        int dec;
 
        String binario = "";
//        StringBuilder binarioCompleto;
        String binarioCompleto;

        
        
        BitSet caracteresBit = new BitSet(16);        
        
        caracteres = new char [totPal][16]; // cria a matriz que vai armazenar as palavras e as letras
//        caracteresInt = new int [totPal+1][totPal+1]; // cria a matriz que vai armazenar as palavras e as letras
        char [] textoArray;
        
        for (int i = 0; i < totPal; i++){     // linha
            int [] r = new int[totPal+1];
            int [] q = new int[totPal+1];
        
            textoArray = palavrasArray[i].toCharArray();    // converte o a palavra para um array
            
            // limita só a tabela ascii
            if (textoArray[0] == '\n'){
                coluna = 0;
                linha ++;
            }
            if ((textoArray[0] >= 65 && textoArray[0] <= 90) || (textoArray[0] >= 97 && textoArray[0] <= 122)){
//                System.out.println(textoArray);
//                System.out.println("linha: " + linha + " coluna: " + coluna + "\n");
                coluna ++;
                
//                binarioCompleto = new StringBuilder(); // zera o binario completo
                binarioCompleto = "";
                for (int j = 0; j < 16; j++){
                    // cria o vetor de cada palavra e completa com zero se a palavra tiver menos que 16 caracteris
                    if(j < textoArray.length)
                        caracteres[i][j] = textoArray[j];
                    else
                        caracteres[i][j] = ' ';
                    
//                    System.out.println(caracteres[i][j]);

                    // converte cada caracter por um int
                    dec = (int)caracteres[i][j];
                    int b = 0;  // variavel iteradora
                    // enquanto o quociente for diferente de 1, o programa continuará calculando
                    while (dec != 1){     
                        q[b] = dec/2;   // pega a parte inteira

                        r[b] = dec % 2; //array que calcula e armazena os restos
                        dec=q[b];       //atribuindo novo valor para q[i], pra que seja possível a continuação do cálculo
                        b++;
                    }
                    // pega o ultimo resto
                    if (q[b-1] == 1) r[b] = 1;


                    // salva o vetor em uma string
                    binario = "";   // zera a string
                    // adiciona termo a termo de traz para frente
                    for (int it = 7; it >= 0; it --){
                        binario += r[it];
                    }
//                    System.out.println(binario);
                    
//                    binarioCompleto.append(binario);
                    binarioCompleto += binario;
                                    
                }
//                System.out.println(binarioCompleto);
                // montado o bitset
                caracteresBit.clear();  // limpa o bitset
                for (int c = 0; c < binarioCompleto.length(); c++) {
                    if (binarioCompleto.charAt(c) == '1') {
                        caracteresBit.set(c);
                    }
//                        if(caracteresBit.get(c)) System.out.print("1");
//                        else System.out.print("0");
                }
//                    System.out.println("");

                // bitset montado, hora de enviar para a arvore
                a.insere(caracteresBit, linha, coluna);
            }
        }
        
        
        
        /************************* P E S Q U I S A ****************************/
        
        // para fazer a pesquisa é necessário que a palavra inserida seja convertida também para um bitset
        String [] procura = new String[] {  "ztraaaaaalho`````",
                                            "çomputacao``````", 
                                            "goveao``````````", 
                                            "educacao````````", 
                                            "tecnologia``````", 
                                            "formacao````````", 
                                            "desenvolvimento`", 
                                            "que`````````````", 
                                            "informatica`````", 
                                            "em``````````````",
                                            "crise```````````"};
        // Converte a string para bit set
        
        char caracteresProcura[][] = new char [procura.length][16]; // cria a matriz que vai armazenar as palavras e as letras
        
        for (int i = 0; i < procura.length; i++){     // palavras que vão ser procuradas
            int [] r = new int[procura.length];
            int [] q = new int[procura.length];
            
            int recebe = 1;
            
            textoArray = procura[i].toCharArray();    // converte o a palavra para um array
            
            binarioCompleto = ""; // zera o binario completo
            for (int j = 0; j < 16 && recebe == 1; j++){    // coluna
                // cria o vetor de cada palavra e completa com zero se a palavra tiver menos que 16 caracteris
                if(j < textoArray.length)
                    caracteresProcura[i][j] = textoArray[j];
                else
                    caracteresProcura[i][j] = '0';
                
                
                // converte cada caracter por um int
                dec = (int)caracteresProcura[i][j];
                int b = 0;  // variavel iteradora
                // enquanto o quociente for diferente de 1, o programa continuará calculando
                while (dec != 1){     
                    q[b] = dec/2;   // pega a parte inteira

                    r[b] = dec % 2; //array que calcula e armazena os restos
                    dec=q[b];       //atribuindo novo valor para q[i], pra que seja possível a continuação do cálculo
                    b++;
                }
                // pega o ultimo resto
                if (q[b-1] == 1) r[b] = 1;
                
                
                // salva o vetor em uma string
                binario = "";   // zera a string
                // adiciona termo a termo de traz para frente
                for (int it = 7; it >= 0; it --){
                    binario += r[it];
                }
                binarioCompleto += binario;
            }

//            System.out.println(binarioCompleto);
            // montado o bitset
            
            char [] charArray = binarioCompleto.toCharArray();
            caracteresBit.clear();  // limpa o bitset
            for (int x = 0; x < binarioCompleto.length(); x++) {
                if (binarioCompleto.charAt(x) == '1') {
                    caracteresBit.set(x);
                }
            }
           
//            System.out.println(caracteresBit);
            
//            System.out.println("");
            

            // bitset do caracter montado, hora de enviar para a arvore
            a.pesquisa(caracteresBit);
                
                
//                if (recebe == 0){
//                    System.out.println("Palavra não encontrada");
//                }
//            if (recebe == 1)
//                System.out.println("Palavra encontrada");
//            else 
//                System.out.println("Palavra NÃO encontrada");
//            
//            recebe = 1;

        }
        
        
        
    }
}