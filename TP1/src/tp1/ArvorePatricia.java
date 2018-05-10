/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp1;

import java.util.BitSet;

/**
 *
 * @author arthur
 */
public class ArvorePatricia {
  private static abstract class PatNo { 
  }
  
  private static class PatNoInt extends PatNo {
    int index; 
    PatNo esq, dir;
  }  
  private static class PatNoExt extends PatNo {
    BitSet chave; // @{\it O tipo da chave depende da aplica\c{c}\~ao}@
    int linha, coluna;
  }
  
  private PatNo raiz;
  private int nbitsChave;
  int linha, coluna;
 
  // @{\it Retorna o i-\'esimo bit da chave k a partir da esquerda}@
  private int bit (int i, BitSet k) {
    return k.get(i) ? 1 : 0;
  }

  // @{\it Verifica se p \'e n\'o externo}@
  private boolean eExterno (PatNo p) {    
        Class classe = p.getClass();
        return classe.getName().equals(PatNoExt.class.getName());
  }

  private PatNo criaNoInt (int i, PatNo esq, PatNo dir) {
    PatNoInt p = new PatNoInt ();
    p.index = i; 
    p.esq = esq; 
    p.dir = dir;
    return p;
  }

    private PatNo criaNoExt(BitSet k, int linha, int coluna) {
        PatNoExt p = new PatNoExt();
        p.chave = k;
        p.linha = linha;
        p.coluna = coluna;
        return p;
    }
  
  private void pesquisa (BitSet k, PatNo t) {
    if (this.eExterno (t)) {
      PatNoExt aux = (PatNoExt)t;
        
      if (aux.chave.equals(k)){
          System.out.println ("Elemento encontrado");
          System.out.println("Linha: " + aux.linha);
          System.out.println("Coluna: " + aux.coluna);
      }
      else
          System.out.println ("Elemento nao encontrado");
    }
    else { 
      PatNoInt aux = (PatNoInt)t;
      if (this.bit (aux.index, k) == 0) pesquisa (k, aux.esq);
      else pesquisa (k, aux.dir);
    }
  }
  
  /*private void pesquisa_palavra(String palavra){
      // converte a string para array de char
      char [] palavraArray = palavra.toCharArray();
      for (int i = 0; i < palavra.length(); i++){
          pesquisa(k);
      }
  }*/

  private PatNo insereEntre (BitSet k, PatNo t, int i, int linha, int coluna) {
    PatNoInt aux = null; 
    if (!this.eExterno (t)) aux = (PatNoInt)t;
    if (this.eExterno (t) || (i < aux.index)) { // @{\it Cria um novo n\'o externo}@
      PatNo p = this.criaNoExt (k, linha, coluna);
      if (this.bit (i, k) == 1) return this.criaNoInt (i, t, p);
      else return this.criaNoInt (i, p, t);
    }
    else {
      if (this.bit (aux.index, k) == 1) 
        aux.dir = this.insereEntre (k, aux.dir, i, linha, coluna);
      else aux.esq = this.insereEntre (k, aux.esq, i, linha, coluna);
      return aux;
    }
  }
  
  private PatNo insere (BitSet k, PatNo t, int linha, int coluna) {      
    if (t == null) {
        return this.criaNoExt(k, linha, coluna);
    } 
    else {
        PatNo p = t;
        
        // enquanto p for um no interno
        while ((!this.eExterno(p))) {
            PatNoInt aux = (PatNoInt)p;
            if (this.bit(aux.index, k) == 1) {
                p = aux.dir;
            } 
            else {
                p = aux.esq;
            }
        }
        
        PatNoExt aux = (PatNoExt) p;
        int i = 1; // @{\it acha o primeiro bit diferente}@
        while ((i <= this.nbitsChave) && (this.bit(i, k) == this.bit(i, aux.chave))) {
            i++;
        }
        if (i > this.nbitsChave) {
//		System.out.println("Erro: chave ja esta na arvore");
            return t;
        } else {
            return this.insereEntre(k, t, i, linha, coluna);
        }
    }   
}

private void central(PatNo pai, PatNo filho, String msg) {
    if (filho != null) {
        if (!this.eExterno(filho)) {
            PatNoInt aux = (PatNoInt) filho;
            central(filho, aux.esq, "ESQ");
            if (pai != null) 
                System.out.println("Pai: " + ((PatNoInt) pai).index + " " + msg + " Int: " + aux.index);
            else 
                System.out.println("Pai: " + pai + " " + msg + " Int: " + aux.index);
            central(filho, aux.dir, "DIR");
        } 
        else {
            PatNoExt aux = (PatNoExt) filho;
            if (pai != null) 
                System.out.println("Pai: " + ((PatNoInt) pai).index + " " + msg + " Ext: " + aux.chave);
            else 
                System.out.println("Pai: " + pai + " " + msg + " Ext: " + aux.chave);
        }
    }
}

      

  public void imprime () {
    this.central (null, this.raiz, "RAIZ");
  }

  public ArvorePatricia (int nbitsChave) {
    this.raiz = null; 
    this.nbitsChave = nbitsChave; 
  }
  
  public void pesquisa (BitSet k) { 
      this.pesquisa (k, this.raiz); 
  }
  
  public void insere (BitSet k, int linha, int coluna) { 
      this.raiz = this.insere(k, this.raiz, linha, coluna);
  } 
  
  public BitSet getRaiz(){
      PatNoExt viz = (PatNoExt)this.raiz;
      return viz.chave;
  }
  
    public BitSet atualizaBitSet(String binarioCompleto){
        BitSet caracteresBit = new BitSet(128);
        for (int c = 0; c < binarioCompleto.length(); c++) {
                if (binarioCompleto.charAt(c) == '1') {
                    caracteresBit.set(c);
                }
        }
        return caracteresBit;
    }
}
