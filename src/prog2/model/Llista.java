
package prog2.model;

import java.io.Serializable;
import java.util.ArrayList;
import prog2.vista.BiblioException;

/**
 * Classe genèrica que implementa una llista d'elements de tipus T.
 * Proporciona les operacions bàsiques per gestionar llistes d'objectes
 * (com Exemplars, Usuaris o Préstecs)
 * @param <T> El tipus d'elements que contindrà la llista.
 */
public class Llista<T> implements Serializable {
   protected ArrayList<T> llista;

   /**
    * Constructor de la classe Llista.
    * Inicialitza l'ArrayList intern on es guardaran els objectes.
    */
    public Llista() {
       llista = new ArrayList<>();
    }

    /**
     * Retorna el nombre total d'elements continguts actualment a la llista.
     * @return int que representa la mida de la llista.
     */
    public int getSize() {
          // TO-BE-DONE
        return llista.size();
    }

    /**
     * Afegeix un nou element a la llista.
     * @param t L'objecte de tipus T que es vol inserir.
     * @throws BiblioException Si es produeix algun error durant l'addició.
     */
    public void afegir(T t) throws BiblioException {
        llista.add(t);
    }

    /**
     * Elimina un element específic de la llista si aquest hi és present.
     * @param t L'objecte de tipus T que es vol esborrar.
     */
    public void esborrar(T t) {
        llista.remove(t);
    }

    /**
     * Retornar element de la llista a la posició position
     */
    public T getAt(int position) {
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
     * Comprova si la llista no conté cap element.
     * @return boolean cert si la llista és buida, fals en cas contrari.
     */
    public boolean isEmpty() {
        // condició 'if' per comprovar si la llista està buida o no
        if(llista.size() == 0){
            return true;
        }
        return false;
    }

    /**
     * Retorna una còpia de l'ArrayList intern utilitzat per la classe.
     */
    public ArrayList<T> getArrayList() {
        ArrayList<T> arrlist = new ArrayList<>(llista);
        return arrlist;
    }
}
