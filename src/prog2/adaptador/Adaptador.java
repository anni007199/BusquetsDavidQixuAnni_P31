package prog2.adaptador;

import prog2.model.*;
import java.io.*;
import java.util.ArrayList;

import prog2.vista.BiblioException;

/**
 * Classe que actua com a mediadora entre la vista i el model de l'aplicació.
 */
public class Adaptador implements Serializable {
    // Atributs
    private Dades dades;

    /**
     * Constructor de la classe Adaptador.
     * Inicialitza una nova instància de la classe Dades per gestionar el sistema.
     */
    public Adaptador(){
        dades = new Dades();
    }

    /**
     * Sol·licita al model l'alta d'un nou exemplar.
     * @param id Identificador únic de l'exemplar.
     * @param titol Títol de l'obra.
     * @param autor Autor de l'obra.
     * @param admetPrestecLlarg Indica si permet la modalitat de préstec llarg.
     * @throws BiblioException Si es produeix un error en la validació de dades al model.
     */
    public void afegirExemplar(String id, String titol, String autor, boolean admetPrestecLlarg) throws BiblioException{
        dades.afegirExemplar(id, titol, autor, admetPrestecLlarg);
    }

    /**
     * Obté la representació textual de tots els exemplars.
     * @return ArrayList de String amb la informació de cada exemplar obtinguda via toString().
     */
    public ArrayList<String> getLlistaExemplars(){
        // crear llista per guardar els exemplars en format String
        ArrayList<String> llista = new ArrayList<>();
        // bucle 'for' per recórrer la llista d'exemplars de dades
        for (Exemplar exemplar: dades.recuperaExemplars()){
            // afegir exemplar com a String
            llista.add(exemplar.toString());
        }
        // retorna llista en format String
        return llista;
    }

    /**
     * Sol·licita al model l'alta d'un nou usuari.
     * @param email Correu electrònic de l'usuari.
     * @param nom Nom complet de l'usuari.
     * @param adreca Adreça postal.
     * @param esEstudiant Cert si el perfil és estudiant, fals si és professor.
     * @throws BiblioException Si l'usuari ja existeix o les dades són invàlides.
     */
    public void afegirUsuari(String email, String nom, String adreca, boolean esEstudiant) throws BiblioException {
        dades.afegirUsuari(email, nom, adreca, esEstudiant);
    }

    /**
     * Obté la representació textual de tots els usuaris.
     * @return ArrayList de String amb la informació de cada usuari.
     */
    public ArrayList<String> getLlistaUsuaris(){
        // crear llista per guardar usuaris en format String
        ArrayList<String> llista = new ArrayList<>();
        // bucle 'for' per recórrer la llista dels usuaris de dades
        for (Usuari usuari: dades.recuperaUsuaris()){
            // afegir usuari com a String
            llista.add(usuari.toString());
        }
        // retorna llista en format String
        return llista;
    }

    /**
     * Fa la creació d'un préstec mitjançant les posicions dels elements.
     * @param exemplarPos Índex de l'exemplar a la llista.
     * @param usuariPos Índex de l'usuari a la llista.
     * @param esLlarg Cert si el préstec és de llarga durada.
     * @throws BiblioException Si no es compleixen les restriccions de préstec del model.
     */
    public void afegirPrestec(int exemplarPos, int usuariPos, boolean esLlarg) throws BiblioException {
        dades.afegirPrestec(exemplarPos, usuariPos, esLlarg);
    }

    /**
     * Fa el retorn d'un préstec existent.
     * @param. position Índex del préstec a retornar.
     * @throws BiblioException Si el préstec no es pot retornar.
     */
    public void retornarPrestec(int position) throws BiblioException{
        dades.retornarPrestec(position);
    }

    /**
     * Obté la representació textual de tot l'historial de préstecs.
     * @return ArrayList de String amb la informació detallada dels préstecs.
     */
    public ArrayList<String> getLlistaPrestecs(){
        // crear llista per guardar préstecs en format String
        ArrayList<String> llista = new ArrayList<>();
        // bucle 'for' per recórrer la llista dels préstecs de dades
        for (Prestec prestec: dades.recuperaPrestecs()){
            // afegir préstec com a String
            llista.add(prestec.toString());
        }
        // retorna llista en format String
        return llista;
    }

    /**
     * Obté la representació textual dels préstecs que encara no s'han retornat.
     * @return ArrayList de String amb els préstecs pendents.
     */
    public ArrayList<String> getLlistaPrestecsNoRetornats(){
        // crear llista per guardar préstecs no retornats en format String
        ArrayList<String> llista = new ArrayList<>();
        // bucle 'for' per recórrer la llista dels préstecs no retornats de dades
        for (Prestec prestec: dades.recuperaPrestecsNoRetornats()){
            // afegir préstec com a String
            llista.add(prestec.toString());
        }
        // retorna llista en format String
        return llista;
    }

    /**
     * Retorna la quantitat d'exemplars de la biblioteca
     * @return int
     */
    public int getNumExemplars() {
        return dades.getLlistaExemplars().getSize();
    }

    /**
     * Retorna la quantitat d'usuaris de la biblioteca
     * @return int
     */
    public int getNumUsuaris(){
        return dades.getLlistaUsuaris().getSize();
    }

    /**
     * Retorna la quantitat de prestecs de la biblioteca
     * @return int
     */
    public int getNumPrestecs(){
        return dades.getLlistaPrestecs().getSize();
    }

    /**
     * Guarda l'estat actual del model en un fitxer binari mitjançant serialització.
     * @param camiDesti Ruta del fitxer on es guardaran les dades.
     * @throws BiblioException Si es produeix un error d'E/S durant l'escriptura.
     */
    public void guardaDades(String camiDesti) throws BiblioException{
        try {
            // obrir fitxer per escriure
            java.io.FileOutputStream fitxer = new java.io.FileOutputStream(camiDesti);
            // crear objecte per escriure
            java.io.ObjectOutputStream escriptor = new java.io.ObjectOutputStream(fitxer);
            // escriure objecte actual
            escriptor.writeObject(dades);
            // tancar fitxer
            escriptor.close();
        } catch (java.io.IOException error){
            throw new BiblioException("ERROR en guardar:"+error.getMessage());
        }
    }

    /** Carrega l'estat del model des d'un fitxer binari prèviament guardat.
     * @param camiOrigen Ruta del fitxer d'on es llegiran les dades.
     * @throws BiblioException Si el fitxer no existeix o el format és incorrecte.
     */
    public void carregaDades(String camiOrigen) throws BiblioException{
            try {
                FileInputStream fitxer = new FileInputStream(camiOrigen);
                ObjectInputStream lector = new ObjectInputStream(fitxer);
                Dades dades =(Dades) lector.readObject();
                lector.close();
            } catch (IOException | ClassNotFoundException error){
                throw new BiblioException("ERROR en carregar:"+error.getMessage());
            }
    }
}
