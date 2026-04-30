package prog2.model;

import prog2.vista.BiblioException;

import java.io.Serializable;
import java.util.Iterator;

public class LlistaUsuaris extends Llista <Usuari> implements Serializable {
    // Mètode "afegir"
    @Override
    public void afegir(Usuari usuari) throws BiblioException {
        // L'enunciat diu que no es podran afegir dos usuaris amb el mateix correu electrònic.
        // Crear un iterator
        Iterator<Usuari> iterator = llista.iterator();
        // Bucle 'while' per recórrer la llista
        while (iterator.hasNext()){
            // Amb condició 'if' per comprovar si n'hi ha algun email igual
            if (iterator.next().getEmail().equals(usuari.getEmail())){
                // si s'ha trobat, es llença una excepció
                throw new BiblioException("Ja existeix un usuari amb aquest email.");
            }
        }
        llista.add(usuari);
    }

    public boolean contains(String email){
        // crear un iterator
        Iterator<Usuari> iterator = llista.iterator();
        // Bucle per recórrer la llista
        while(iterator.hasNext()){
            // Condició 'if' per comprovar un per un, si n'hi ha algun email igual
            if (iterator.next().getEmail().equals(email)){
                // Si n'hi ha, retrona 'true'
                return true;
            }
        }
        return false;
    }
}
