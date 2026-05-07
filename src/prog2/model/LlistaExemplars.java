package prog2.model;

import prog2.vista.BiblioException;

import java.io.Serializable;
import java.util.Iterator;

/**
 * Especialització de la classe Llista per a objectes de tipus Exemplar.
 * Aquesta classe assegura que no hi hagi duplicats en el catàleg basant-se
 * en l'identificador únic de cada exemplar.
 */
public class LlistaExemplars extends Llista <Exemplar> implements Serializable {

    /**
     * {@inheritDoc}
     * Abans d'afegir l'exemplar, recorre la llista per verificar que l'ID no estigui ja registrat.
     * @throws BiblioException Si es detecta que l'identificador ja existeix a la llista.
     */
    @Override
    public void afegir(Exemplar exemplar) throws BiblioException {
        // L'enunciat diu que no es podran afegir dos exemplars amb el mateix identificador.
        // Crear un iterator per recórrer la llista
        Iterator<Exemplar> iterator = llista.iterator();
        // Bucle 'while' per recórrer la llista
        while (iterator.hasNext()){
            // Amb condició 'if' per comprovar si n'hi ha algun id igual
            if (iterator.next().getId().equals(exemplar.getId())){
                // si s'ha trobat, es llença una excepció
                throw new BiblioException("Ja existeix un exemplar amb aquest id.");
            }
        }
        // si no s'ha trobat id iguals, s'afegeix a la llista
        llista.add(exemplar);
    }

    /**
     * Comprova si existeix algun exemplar a la llista amb l'identificador especificat.
     * @param id String amb l'identificador a cercar.
     * @return boolean cert si l'ID s'ha trobat, fals en cas contrari.
     */
    public boolean contains(String id){
        // crear un iterator
        Iterator<Exemplar> iterator = llista.iterator();
        // Bucle per recórrer la llista
        while(iterator.hasNext()){
            // Condició 'if' per comprovar un per un, si n'hi ha algun id igual
            if (iterator.next().getId().equals(id)){
               // Si n'hi ha, retrona 'true'
                return true;
            }
        }
        // si no s'ha trobat cap id igual, retorna 'false'
        return false;
    }
}