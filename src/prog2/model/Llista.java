/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog2.model;

import java.io.Serializable;
import java.util.ArrayList;
import prog2.vista.BiblioException;

public class Llista<T> implements Serializable {
   protected ArrayList<T> llista;

   public Llista() {
       llista = new ArrayList<>();
    }

    /**
     * Retornar nombre d'elements continguts a la llista
     */
    public int getSize() {
          // TO-BE-DONE
        return llista.size();
    }

    /**
     * Afegir element a la llista. Afegeix l'element t a la llista
     */
    public void afegir(T t) throws BiblioException {
          // TO-BE-DONE
        llista.add(t);
    }

    /**
     * Esborrar element de la llista. Esborra l'element t a la llista
     */
    public void esborrar(T t) {
          // TO-BE-DONE
        llista.remove(t);
    }

    /**
     * Retornar element de la llista a la posició position
     */
    public T getAt(int position) {
          // TO-BE-DONE
        // primer s'ha de comprovar si està dins de la llista o no
        if (position >= 0 && position < llista.size()){
            // si està dins de la llista, usant ".get()", es retorna l'element
            return llista.get(position);
        }
        // si està fora del rang, retorna 'null'
        return null;
    }

    /**
     * Buidar tots el elements de la llista
     */
    public void clear() {
          // TO-BE-DONE
        llista.clear();
    }

    /**
     * Retornar true si la llista és buida
     */
    public boolean isEmpty() {
          // TO-BE-DONE
        // condició 'if' per comprovar si la llista està buida o no
        if(llista.size() == 0){
            return true;
        }
        return false;
    }

    /**
     * Retornar l'ArrayList que es fa servir dins de la classe
     */
    public ArrayList<T> getArrayList() {
        ArrayList<T> arrlist = new ArrayList<>(llista);
        return arrlist;
    }
}
