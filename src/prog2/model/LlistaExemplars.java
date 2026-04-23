package prog2.model;

import prog2.vista.BiblioException;

import java.io.Serializable;
import java.util.Iterator;

public class LlistaExemplars extends Llista <Exemplar> implements Serializable {
    // Mètode "afegir"
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
                throw new BiblioException("Ja existeix un exemplar amb el mateix id");
            }
        }
        // si no s'ha trobat id iguals, s'afegeix a la llista
        llista.add(exemplar);
    }

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