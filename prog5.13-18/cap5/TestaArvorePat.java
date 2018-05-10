package cap5;
import cap4.PermutacaoRandomica;

public class TestaArvorePat {
  public static void main (String[] args) {
    ArvorePatricia dicionario = new ArvorePatricia (8);
    int min = 32, max = 126;
    
    char vetor[] = new char[max-min+1];

    for (int i = min; i <= max; i++)
      vetor[i-min] = (char)i;

    // @{\it Gera uma permuta\c{c}\~ao aleat\'oria de chaves dos caracteres UNICODE 32 a  126}@
    PermutacaoRandomica.permut (vetor, vetor.length);
    
    // @{\it Insere cada chave na \'arvore}@
    for (int i = 0; i < vetor.length; i++) { 
      char c = vetor[i];
      dicionario.insere (c);
      //System.out.println ("Inseriu chave"+ i + ": " + (int)c + " -- char:" + c);
    }
    dicionario.imprime ();

    // @{\it Gera outra permuta\c{c}\~ao aleat\'oria de chaves}@
    PermutacaoRandomica.permut (vetor, vetor.length);

    // @{\it Pesquisa cada chave na \'arvore}@
    for (int i = 0; i < vetor.length; i++) {
      char c = vetor[i];
      //System.out.println ("Pesquisando chave" + i + ": " + c);
      dicionario.pesquisa (c);
    }
  }
}
