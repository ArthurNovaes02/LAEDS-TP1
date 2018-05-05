/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp1;

import java.util.Scanner;



/**
 *
 * @author arthur
 */
public class TP1 {

    public static void main(String[] args) throws Exception{
        // TODO code application logic here
        
        /*Scanner ler = new Scanner(System.in);
        
        System.out.printf("Informe o texto:\n");
        String texto = ler.nextLine();
        
        String [] textoSeparado = texto.split(" ");
        
        
        System.out.println("\n--------------\nVetor: ");
        for (int i = 0; i < textoSeparado.length; i++){
            System.out.println(textoSeparado[i]);
        }
  
        char [][] caracteres = null;
        caracteres = new char [30][30];
        char [] textoArray;
        for (int i = 0; i < textoSeparado.length; i++){ // linha
            textoArray = textoSeparado[i].toCharArray();
            for (int j = 0; j < textoArray.length; j++){
                caracteres[i][j] = textoArray[j];
                System.out.println(caracteres[i][j]);
            }
        }*/
        
        
        ExtraiPalavra palavras = new ExtraiPalavra("delim", "exemplo1");
        String palavra = null;

        String[] palavrasArray = null;
        palavrasArray = new String[100000000];

        int totPal = 0;
        
        while ((palavra = palavras.proximaPalavra())!= null) {
            // O primeiro espaço depois da palavra não é codificado
            if(palavra.equals(" ")) continue;
            palavrasArray[totPal] = palavra;
            totPal++;
            System.out.println(palavrasArray[totPal-1]);


        }
        palavras.fecharArquivos();
        
        System.out.println("totPal = " + totPal);
        // teste
        
        
        
        char [][] caracteres = null;
        caracteres = new char [totPal+1][totPal+1];
        char [] textoArray;
        for (int i = 0; i < palavrasArray.length; i++){ // linha
            textoArray = palavrasArray[i].toCharArray();
            for (int j = 0; j < textoArray.length; j++){
                caracteres[i][j] = textoArray[j];
                System.out.println(caracteres[i][j]);
            }
        }
        
        for (int i = 0; i < caracteres.length; i++){
            System.out.println(caracteres[i]);
        }
    }
}