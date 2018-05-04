package cap5;
public class ArvorePatricia {
  private static abstract class PatNo { }
  private static class PatNoInt extends PatNo {
    int index; PatNo esq, dir;
  }  
  private static class PatNoExt extends PatNo {
    char chave; // @{\it O tipo da chave depende da aplica\c{c}\~ao}@
  }
  
  private PatNo raiz;
  private int nbitsChave;
 
  // @{\it Retorna o i-\'esimo bit da chave k a partir da esquerda}@
  private int bit (int i, char k) {
    if (i == 0) return 0;
    int c = (int)k;
    for (int j = 1; j <= this.nbitsChave - i; j++) c = c/2;
    return c % 2;
  }

  // @{\it Verifica se p \'e n\'o externo}@
  private boolean eExterno (PatNo p) {    
    Class classe = p.getClass ();
    return classe.getName().equals(PatNoExt.class.getName());
  }

  private PatNo criaNoInt (int i, PatNo esq, PatNo dir) {
    PatNoInt p = new PatNoInt ();
    p.index = i; p.esq = esq; p.dir = dir;
    return p;
  }

  private PatNo criaNoExt (char k) {
    PatNoExt p = new PatNoExt ();
    p.chave = k;
    return p;
  }
  
  private void pesquisa (char k, PatNo t) {
    if (this.eExterno (t)) {
      PatNoExt aux = (PatNoExt)t;
      if (aux.chave == k) System.out.println ("Elemento encontrado");
      else System.out.println ("Elemento nao encontrado");
    }
    else { 
      PatNoInt aux = (PatNoInt)t;
      if (this.bit (aux.index, k) == 0) pesquisa (k, aux.esq);
      else pesquisa (k, aux.dir);
    }
  }

  private PatNo insereEntre (char k, PatNo t, int i) {
    PatNoInt aux = null; 
    if (!this.eExterno (t)) aux = (PatNoInt)t;
    if (this.eExterno (t) || (i < aux.index)) { // @{\it Cria um novo n\'o externo}@
      PatNo p = this.criaNoExt (k);
      if (this.bit (i, k) == 1) return this.criaNoInt (i, t, p);
      else return this.criaNoInt (i, p, t);
    }
    else {
      if (this.bit (aux.index, k) == 1) 
        aux.dir = this.insereEntre (k, aux.dir, i);
      else aux.esq = this.insereEntre (k, aux.esq, i);
      return aux;
    }
  }
  
  private PatNo insere (char k, PatNo t) {
    if (t == null) return this.criaNoExt (k);
    else {
      PatNo p = t;
      while (!this.eExterno (p)) {
        PatNoInt aux = (PatNoInt)p;
        if (this.bit (aux.index, k) == 1) p = aux.dir; else p = aux.esq;
      }
      PatNoExt aux = (PatNoExt)p;
      int i = 1; // @{\it acha o primeiro bit diferente}@
      while ((i <= this.nbitsChave)&&
             (this.bit (i, k) == this.bit (i, aux.chave))) i++;
      if (i > this.nbitsChave) {
        System.out.println ("Erro: chave ja esta na arvore"); 
        return t;
      }
      else return this.insereEntre (k, t, i);
    }
  }
  
  private void central (PatNo pai, PatNo filho, String msg) {
    if (filho != null) {
      if (!this.eExterno (filho)) {
        PatNoInt aux = (PatNoInt)filho; 
        central (filho, aux.esq, "ESQ");
        if (pai != null)
          System.out.println ("Pai: "+ ((PatNoInt)pai).index + " " + msg+ " Int: " + aux.index);
        else System.out.println ("Pai: "+ pai + " " + msg+ " Int: " + aux.index);
        central (filho, aux.dir, "DIR");
      } else {
        PatNoExt aux = (PatNoExt)filho;
        if (pai != null)
          System.out.println ("Pai: "+ ((PatNoInt)pai).index + " " + msg+ " Ext: " + aux.chave);
        else System.out.println ("Pai: "+ pai + " " + msg+ " Ext: " + aux.chave);
      }
    }
  }

  public void imprime () {
    this.central (null, this.raiz, "RAIZ");
  }

  public ArvorePatricia (int nbitsChave) {
    this.raiz = null; this.nbitsChave = nbitsChave; 
  }
  
  public void pesquisa (char k) { this.pesquisa (k, this.raiz); }
  
  public void insere (char k) { this.raiz = this.insere (k, this.raiz); } 
}
