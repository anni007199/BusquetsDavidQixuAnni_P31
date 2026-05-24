package prog2.model;

import prog2.vista.BiblioException;
import java.util.ArrayList;
import java.util.Date;
import java.io.Serializable;

/**
 * Classe que implementa la gestió de dades de la biblioteca.
 * Conté les llistes d'exemplars, usuaris i préstecs, i controla la
 * lògica de negoci i les restriccions del sistema.
 */
public class Dades implements InDades, Serializable {
    // Atributs
    private LlistaExemplars llistaExemplars;
    private LlistaUsuaris llistaUsuaris;
    private LlistaPrestecs llistaPrestecs;

    // Constructor
    /**
     * Constructor per a un nou objecte Dades.
     * Inicialitza les tres llistes principals de l'aplicació: llistaExemplars,
     * llistaUsuaris i llistaPrestecs
     */
    public Dades(){
        llistaExemplars = new LlistaExemplars();
        llistaUsuaris = new LlistaUsuaris();
        llistaPrestecs = new LlistaPrestecs();
    }

    // getters
    /**
     * Retorna la llista d'Exemplars
     * @return llistaExemplars
     */
    public LlistaExemplars getLlistaExemplars(){
        return llistaExemplars;
    }

    /**
     * Retorna la llista d'Usuaris
     * @return llistaUsuaris
     */
    public LlistaUsuaris getLlistaUsuaris(){
        return llistaUsuaris;
    }

    /**
     * Retorna la llista d'Prestecs
     * @return llistaPrestecs
     */
    public LlistaPrestecs getLlistaPrestecs(){
        return llistaPrestecs;
    }

    /**
     * Afegeix un nou exemplar a la llista de la biblioteca.
     * @param id String amb l'identificador únic de l'exemplar.
     * @param titol String amb el títol de l'obra.
     * @param autor String amb l'autor de l'obra.
     * @param admetPrestecLlarg boolean que indica si l'exemplar permet préstecs de llarga durada.
     * @throws BiblioException Si l'identificador ja existeix a la llista.
     */
    @Override
    //Afegeix exemplar. Llança excepció si l'id ja existeix
    public void afegirExemplar(String id, String titol, String autor, boolean admetPrestecLlarg) throws BiblioException{
        // crear objecte Exemplar
        Exemplar nouExemplar = new Exemplar(id, titol, autor, admetPrestecLlarg);
        // afegir l'exemplar a la llista
        llistaExemplars.afegir(nouExemplar);
    }

    /**
     * Recupera tots els exemplars de la biblioteca.
     * @return ArrayList d'Exemplar amb tots els elements de la llista.
     */
    @Override
    // Recuperar préstecs. Retorna un ArrayList amb tots els exemplars
    public ArrayList<Exemplar> recuperaExemplars(){
        return llistaExemplars.getArrayList();
    }

    /**
     * Afegeix un usuari (Estudiant o Professor) a la llista.
     * @param email String amb el correu electrònic (clau única).
     * @param nom String amb el nom complet de l'usuari.
     * @param adreca String amb l'adreça de residència.
     * @param esEstudiant boolean cert si és Estudiant, fals si és Professor.
     * @throws BiblioException Si l'email ja està registrat al sistema.
     */
    @Override
    // Afegeix usuari. Llança excepció si l'email ja existeix
    public void afegirUsuari(String email, String nom, String adreca, boolean esEstudiant) throws BiblioException{
        // declarar variable
        Usuari nouUsuari;
        // Condició 'if' per comprovar si és estudiant o no
        // si ho és, es crea objecte Estudiant
        if (esEstudiant){
            nouUsuari = new Estudiant(email,nom,adreca);
          // si no ho és, es crea objecte professor
        } else {
            nouUsuari = new Professor(email,nom,adreca);
        }
        // afegir l'usuari a la llista
        llistaUsuaris.afegir(nouUsuari);
    }

    /**
     * Recupera tots els usuaris registrats.
     * @return ArrayList d'Usuari amb tots els usuaris de la biblioteca.
     */
    @Override
    // Recuperar usuaris. Retorna un ArrayList amb tots els usuaris
    public ArrayList<Usuari> recuperaUsuaris(){
        return llistaUsuaris.getArrayList();
    }

    /**
     * Crea un nou préstec validant totes les restriccions del sistema.
     * Comprova la disponibilitat de l'exemplar, que l'usuari no tingui deutes pendents
     * i que no hagi superat els seus límits màxims segons el seu tipus.
     * @param exemplarPos Posició de l'exemplar dins de l'ArrayList.
     * @param usuariPos Posició de l'usuari dins de l'ArrayList.
     * @param esLlarg boolean que indica si el préstec sol·licitat és de llarga durada.
     * @throws BiblioException Si l'exemplar no està disponible, si l'usuari té endarreriments,
     * o si s'ha superat el límit de préstecs permesos.
     */
    @Override
    public void afegirPrestec(int exemplarPos, int usuariPos, boolean esLlarg) throws BiblioException{
        // Es pot usar el mètode ".getAt()" que retorna l'element de la posició donada
        // A partir de la posició donada, es pot obtenir l'exemplar i l'usuari.
        Exemplar exemplar = llistaExemplars.getAt(exemplarPos);
        Usuari usuari = llistaUsuaris.getAt(usuariPos);
        // Condició 'if' per comprovar si existeix o no
        // si no existeix, llença excepció
        if(exemplar == null){
            throw new BiblioException("No s'ha trobat exemplars.");
        }
        if(usuari == null){
            throw new BiblioException("No s'ha trobat usuaris.");
        }
        // Condició 'if' per comprovar si l'exemplar està disponible o no
        // si no està disponible, llença excepció
        if(!exemplar.isDisponible()){
            throw new BiblioException("L'exemplar no està disponible.");
        }
        // condició 'if' per comprovar si l'exemplar pot ser de préstec llarg o no
        // si no ho és, llença excepció
        if(esLlarg && !exemplar.getAdmetPrestecLlarg()){
            throw new BiblioException("Aquest exemplar no permet préstecs de llarg termini.");
        }
        // condició 'i' per comprovar si l'usuari té préstecs endarrerits o no
        // si ho té, llença excepció
        for(int i = 0; i < llistaPrestecs.getSize(); i++){
            Prestec prestec = llistaPrestecs.getAt(i);
            if(prestec.getUsuari().equals(usuari) && !prestec.getRetornat() && prestec.prestecEndarrerit()){
                throw new BiblioException("L'usuari té préstecs endarrerits, no pot obtenir préstecs nous.");
            }
        }
        // comprovar els límits de l'usuari
        if(esLlarg){
            if(usuari.getNumPrestecsLlargs() >= usuari.getMaxPrestecsLlargs()){
                throw new BiblioException("ERROR: L'usuari ha passat el límit de préstecs llargs.");
            }
        } else {
            if (usuari.getNumPrestecsNormals() >= usuari.getMaxPrestecsNormals()){
                throw new BiblioException("ERROR: L'usuari ha passat el límit de préstecs normals.");
            }
        }
        // crear data actual
        Date dataCreacio = new Date();
        // crear préstec
        Prestec nouPrestec;
        // si és préstec llarg, crea PrestecLlarg i incrementar comptador de préstecs llargs de l'usuari
        if(esLlarg){
            nouPrestec = new PrestecLlarg(exemplar,usuari,dataCreacio);
            usuari.setNumPrestecsLlargs(usuari.getNumPrestecsLlargs()+1);
            // en cas contrari, fa el mateix, però en préstecs normals
        } else {
            nouPrestec = new PrestecNormal(exemplar,usuari,dataCreacio);
            usuari.setNumPrestecsNormals(usuari.getNumPrestecsNormals()+1);
        }
        // posar com a no disponible en l'exemplar
        exemplar.setDisponible(false);
        // afegir el préstec a la llista
        llistaPrestecs.afegir(nouPrestec);
    }

    @Override
    /**
     * Processa la devolució d'un préstec.
     * Marca el préstec com a retornat i torna a posar l'exemplar com a disponible.
     * @param position Índex del préstec dins de la llista.
     * @throws BiblioException Si el préstec no existeix o si ja havia estat retornat anteriorment.
     */
    public void retornarPrestec(int position) throws BiblioException{
        // A partir de la posició donada, obtenir préstec
        Prestec prestec = llistaPrestecs.getAt(position);
        // condició 'if' per comprovar si existeix
        if(prestec == null){
            throw new BiblioException("No s'ha trobat préstecs.");
        }
        // condició 'if' per comprovar si s'ha retornat o no
        if (prestec.getRetornat()){
            throw new BiblioException("Ja s'ha retornat.");
        }
        // retornar el préstec
        prestec.retorna();
    }

    /**
     * Recupera l'historial complet de préstecs realitzats.
     * @return ArrayList de Prestec amb tots els préstecs.
     */
    @Override
    // Recuperar préstecs. Retorna un ArrayList amb tots els préstecs
    public ArrayList<Prestec> recuperaPrestecs(){
        return llistaPrestecs.getArrayList();
    }

    /**
     * Recupera només aquells préstecs que encara no han estat retornats.
     * @return ArrayList de Prestec amb els préstecs actius.
     */
    @Override
    // Recuperar préstecs. Retorna un ArrayList amb els préstecs no retornats
    public ArrayList<Prestec> recuperaPrestecsNoRetornats(){
        // crear llista buida
        ArrayList<Prestec> noRetornats = new ArrayList<>();
        // Bucle 'for' per recórrer la llista de préstecs
        for (int i = 0; i < llistaPrestecs.getSize(); i++){
            // préstec actual
            Prestec prestec = llistaPrestecs.getAt(i);
            // si no està retornat el llibre, s'afegeix a la llista noRetornat
            if(!prestec.getRetornat()){
                noRetornats.add(prestec);
            }
        }
        return noRetornats;
    }
}
