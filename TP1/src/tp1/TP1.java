/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp1;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Scanner;

import tp1.ArvorePatricia;



/**
 *
 * @author arthur
 */
public class TP1 {

    public static void main(String[] args) throws Exception{
        ArvorePatricia a = new ArvorePatricia(128);

        ExtraiPalavra palavras = new ExtraiPalavra("delim", "exemplo2");
        String palavra = null;
        
        // array que contem todas as palavras to texto
        String[] textoArray = new String[100000000];
        
        int linha = 0, coluna = 0;

        int totPal = 0;
        
        while ((palavra = palavras.proximaPalavra())!= null) {
            // O primeiro espaço depois da palavra não é codificado
            if(palavra.equals(" ")) continue;
            textoArray[totPal] = palavra;
            totPal++;
        }
        palavras.fecharArquivos();
        
        // cria uma matriz pra armazenar todo o texto sendo que cada coluna possui uma plaavra e cada linha uma letra
        char [][] caracteres = new char [totPal][16]; ;
        // variavel que vai armazenar o valor decimal de cada letra
        int dec;
 
        String binario = "";        // começa zerando a string
        String binarioCompleto;     // String que vai armazenar o valor binario de cada palavra
        
        // variavel que armazena a sequencia de bit de cada palavra
        BitSet caracteresBit = new BitSet(128);
        caracteresBit.clear();
        a.insere(caracteresBit, linha, coluna);
        
        // array que vai armazenar uma unica palavra
        char [] palavraArray;
        
        // faz essa iteracao ate ler todas as palavras
        for (int i = 0; i < totPal; i++){
            int [] r = new int[totPal+1];
            int [] q = new int[totPal+1];
        
            // converte cada palavra para um array
            palavraArray = textoArray[i].toCharArray();    
            
            // os dois proximos if's sao para limita caracteres somente da tabela ascii
            if (palavraArray[0] == '\n'){
                coluna = 0;
                linha ++;
            }
            if ((palavraArray[0] >= 65 && palavraArray[0] <= 90) || (palavraArray[0] >= 97 && palavraArray[0] <= 122)){
                coluna ++;
                
                // limpa a string que recebe o valor binario completo de uma palavra
                binarioCompleto = "";
                
                // dentro de cada palavra, agora converte cada caracter para um valor binario
                // lebrando que cada palavra so pode ter 16 caracteres
                for (int j = 0; j < 16; j++){
                    // cria o vetor de cada palavra
                    if(j < palavraArray.length)
                        caracteres[i][j] = palavraArray[j];
                    // completa com zero se a palavra tiver menos que 16 caracteris
                    else
                        caracteres[i][j] = ' ';
                    
                    // converte explicitamente cada caracter por um int
                    dec = (int)caracteres[i][j];
                    
                    /********* Conversao de decimal para binario **************/
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
                    /******************** fim da conversao ********************/

                    // salva o vetor em uma string
                    binario = "";   // zera a string
                    // adiciona termo a termo de traz para frente
                    for (int it = 7; it >= 0; it --){
                        binario += r[it];
                    }
                    
                    // Concatena todas as letras em uma string
                    binarioCompleto += binario;
                }
                
                
                // insere na arvore
                caracteresBit = a.atualizaBitSet(binarioCompleto);
                a.insere(caracteresBit, linha, coluna);
            }
        }
        
        a.imprime();
        
        /************************* P E S Q U I S A ****************************/
        
        // para fazer a pesquisa é necessário que a palavra inserida seja convertida também para um bitset
        String [] procura = new String[] {  "sociedade```````",
                                            "software````````",
                                            "ideia```````````",
                                            "pessoa``````````",
                                            "Informatica`````",
                                            "etica```````````",
                                            "muito```````````",
                                            "ciencia`````````",
                                            "computacao``````",
                                            "que`````````````",
                                            "area````````````",
                                            "Moral```````````"};
            
        // faz essa iteracao ate ler todas as palavras
        for (int i = 0; i < procura.length; i++){
            int [] r = new int[procura.length];
            int [] q = new int[procura.length];
        
            // converte cada palavra para um array
            palavraArray = textoArray[i].toCharArray();    
            
                
            // limpa a string que recebe o valor binario completo de uma palavra
            binarioCompleto = "";
            
            
            // dentro de cada palavra, agora converte cada caracter para um valor binario
            // lebrando que cada palavra so pode ter 16 caracteres
            for (int j = 0; j < 16; j++){
                // cria o vetor de cada palavra
                if(j < palavraArray.length)
                    caracteres[i][j] = palavraArray[j];
                // completa com zero se a palavra tiver menos que 16 caracteris
                else
                    caracteres[i][j] = ' ';

                // converte explicitamente cada caracter por um int
                dec = (int)caracteres[i][j];

                /********* Conversao de decimal para binario **************/
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
                /******************** fim da conversao ********************/

                // salva o vetor em uma string
                binario = "";   // zera a string
                // adiciona termo a termo de traz para frente
                for (int it = 7; it >= 0; it --){
                    binario += r[it];
                }

                // Concatena todas as letras em uma string
                binarioCompleto += binario;
            }

            // com bitset do caracter montado, hora de enviar para a arvore
            caracteresBit = a.atualizaBitSet(binarioCompleto);
            a.pesquisa(caracteresBit);
        }
    }
}