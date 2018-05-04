/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Item;
import java.io.*;

/**
 *
 * @author arthur
 */
public class MeuItem implements Item {
  private int chave;
  // @{\it outros componentes do registro}@

  public MeuItem (int chave) { this.chave = chave; }
  
  public int compara (Item it) {
    MeuItem item = (MeuItem) it;
    if (this.chave < item.chave) return -1;
    else if (this.chave > item.chave) return 1;
    return 0;
  }
  
  public void alteraChave (Object chave) {
    Integer ch = (Integer) chave; this.chave = ch.intValue ();
  }
  
  public Object recuperaChave () { return new Integer (this.chave); }
  
  public String toString () { return "" + this.chave; }
  
  public void gravaArq (RandomAccessFile arq) throws IOException {
    arq.writeInt (this.chave);
  }
  
  public void leArq (RandomAccessFile arq) throws IOException {
    this.chave = arq.readInt ();
  }
 
  public static int tamanho () { return 4; /* @{\it 4 bytes}@ */ }
}
