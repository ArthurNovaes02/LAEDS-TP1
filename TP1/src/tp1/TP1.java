/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 *
 * @author arthur
 */
public class TP1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        
        Scanner ler = new Scanner(System.in);
        
        System.out.printf("Informe o nome de arquivo texto:\n");
        String nome = ler.nextLine();
        
        // le o arquivo e guarda em arq
        FileReader arq = new FileReader(nome);
        
        BufferedReader lerArq = new BufferedReader(arq);
 
        String linha = lerArq.readLine();   // lê a primeira linha
                                            // a variável "linha" recebe o valor "null" quando o processo
                                            // de repetição atingir o final do arquivo texto
        // @TODO: O texto foi passado como parâmetro e não o arquivo, mudar isso
        
        
        
        
        
    }
    
}
