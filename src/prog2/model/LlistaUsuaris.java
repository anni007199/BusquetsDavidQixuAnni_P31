package prog2.model;

import prog2.vista.BiblioException;

import java.io.Serializable;
import java.util.Iterator;

/**
 * Especialització de la classe Llista per a objectes de tipus Usuari.
 * Gestiona el conjunt d'usuaris del sistema i garanteix que no es dupliquin
 * registres amb la mateixa adreça de correu electrònic.
 */
public class LlistaUsuaris extends Llista <Usuari> implements Serializable {

    /**
     * {@inheritDoc}
     * Verifica mitjançant un iterador que no existeixi cap altre usuari a la llista amb el mateix email abans de procedir a l'alta.
     * @throws BiblioException Si ja hi ha un usuari registrat amb el mateix correu electrònic.
     */
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

    /**
     * Cerca a la llista si ja existeix un usuari vinculat a un correu electrònic concret.
     * @param email String amb l'adreça de correu a verificar.
     * @return boolean cert si l'usuari existeix a la llista, fals en cas contrari.
     */
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
