package prog2.model;

import java.util.ArrayList;

import prog2.vista.BiblioException;

/**
 * Interfície que defineix el sistema de gestió de dades de la biblioteca.
 * Actua com a capa de control per gestionar les col·leccions de dades.
 */
public interface InDades {

    /**
     * Registra un nou exemplar al catàleg de la biblioteca.
     * @throws BiblioException Si ja existeix un exemplar amb el mateix identificador.
     */
    public void afegirExemplar(String id, String titol, String autor, boolean admetPrestecLlarg) throws BiblioException;

    /**
     * Recupera la llista completa de tots els exemplars registrats al sistema.
     * * @return ArrayList d'objectes Exemplar.
     */
    public ArrayList<Exemplar> recuperaExemplars();

    /**
     * Registra un nou usuari al sistema, ja sigui estudiant o professor.
     * @throws BiblioException Si ja existeix un usuari registrat amb aquest correu electrònic.
     */
    public void afegirUsuari(String email, String nom, String adreca, boolean esEstudiant) throws BiblioException;

    /**
     * Recupera la llista completa de tots els usuaris registrats al sistema.
     * * @return ArrayList d'objectes Usuari.
     */
    public ArrayList<Usuari> recuperaUsuaris();

    /**
     * Afegeix préstec. Ha de fer diferents comprovacions que poden llançar excepcions.
     * Quan s'afegeix el préstec, s'han de tenir en compte les posicions d'exemplar
     * i usuari dins dels seus ArrayLists
     */
    public void afegirPrestec(int exemplarPos, int usuariPos, boolean esLlarg) throws BiblioException;

    /**
     * Retornar préstec. Llança excepció si el prestec ja es vaig retornar.
     * El préstec s'identifica amb la seva posició dins de l'ArrayList
     */
    public void retornarPrestec(int position) throws BiblioException;

    /**
     * Recuperar préstecs. Retorna un ArrayList amb tots els préstecs
     */
    public ArrayList<Prestec> recuperaPrestecs();

    /**
     * Recuperar préstecs. Retorna un ArrayList amb els préstecs no retornats
     */
    public ArrayList<Prestec> recuperaPrestecsNoRetornats();
}
