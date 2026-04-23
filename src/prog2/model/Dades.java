package prog2.model;

import prog2.vista.BiblioException;
import java.util.ArrayList;
import java.util.Date;

public class Dades implements InDades{
    // Atributs
    private LlistaExemplars llistaExemplars;
    private LlistaUsuaris llistaUsuaris;
    private LlistaPrestecs llistaPrestecs;

    // Constructor
    public Dades(){
        llistaExemplars = new LlistaExemplars();
        llistaUsuaris = new LlistaUsuaris();
        llistaPrestecs = new LlistaPrestecs();
    }

    // getters
    public LlistaExemplars getLlistaExemplars(){
        return llistaExemplars;
    }
    public LlistaUsuaris getLlistaUsuaris(){
        return llistaUsuaris;
    }
    public LlistaPrestecs getLlistaPrestecs(){
        return llistaPrestecs;
    }

    @Override
    //Afegeix exemplar. Llança excepció si l'id ja existeix
    public void afegirExemplar(String id, String titol, String autor, boolean admetPrestecLlarg) throws BiblioException{
        // crear objecte Exemplar
        Exemplar nouExemplar = new Exemplar(id, titol, autor, admetPrestecLlarg);
        // afegir l'exemplar a la llista
        llistaExemplars.afegir(nouExemplar);
    }

    @Override
    // Recuperar préstecs. Retorna un ArrayList amb tots els exemplars
    public ArrayList<Exemplar> recuperaExemplars(){
        return llistaExemplars.getArrayList();
    }

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

    @Override
    // Recuperar usuaris. Retorna un ArrayList amb tots els usuaris
    public ArrayList<Usuari> recuperaUsuaris(){
        return llistaUsuaris.getArrayList();
    }

    @Override
    /* Afegeix préstec. Ha de fer diferents comprovacions que poden llançar excepcions.
     * Quan s'afegeix el préstec, s'han de tenir en compte les posicions d'exemplar
     * i usuari dins dels seus ArrayLists
     */
    public void afegirPrestec(int exemplarPos, int usuariPos, boolean esLlarg) throws BiblioException{
        // Es pot usar el mètode ".getAt()" que retorna l'element de la posició donada
        // A partir de la posició donada, es pot obtenir l'exemplar i l'usuari.
        Exemplar exemplar = llistaExemplars.getAt(exemplarPos);
        Usuari usuari = llistaUsuaris.getAt(usuariPos);
        // Condició 'if' per comprovar si existeix o no
        // si no existeix, llença excepció
        if(exemplar == null){
            throw new BiblioException("No s'ha trobat exemplar");
        }
        if(usuari == null){
            throw new BiblioException("No s'ha trobat usuari");
        }
        // Condició 'if' per comprovar si l'exemplar està disponible o no
        // si no està disponible, llença excepció
        if(!exemplar.isDisponible()){
            throw new BiblioException("L'exemplar no està disponible");
        }
        // condició 'if' per comprovar si l'exemplar pot ser de préstec llarg o no
        // si no ho és, llença excepció
        if(esLlarg && !exemplar.getAdmetPrestecLlarg()){
            throw new BiblioException("Aquest exemplar no permet préstecs de llarg termini");
        }
        // condició 'i' per comprovar si l'usuari té préstecs endarrerits o no
        // si ho té, llença excepció
        for(int i = 0; i < llistaPrestecs.getSize(); i++){
            Prestec prestec = llistaPrestecs.getAt(i);
            if(prestec.getUsuari().equals(usuari) && !prestec.getRetornat() && prestec.prestecEndarrerit()){
                throw new BiblioException("L'usuari té préstecs endarrerits");
            }
        }
        // comprovar els límits de l'usuari
        if(esLlarg){
            if(usuari.getNumPrestecsLlargs() >= usuari.getMaxPrestecsLlargs()){
                throw new BiblioException("ERROS: L'usuari ha superat el límit de préstecs llargs");
            }
        } else {
            if (usuari.getNumPrestecsNormals() >= usuari.getMaxPrestecsNormals()){
                throw new BiblioException("ERROR: L'usuari ha superat el límit de préstecs normals");
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
    /* Retornar préstec. Llança excepció si el prestec ja es vaig retornar.
     * El préstec s'identifica amb la seva posició dins de l'ArrayList
     */
    public void retornarPrestec(int position) throws BiblioException{
        // A partir de la posició donada, obtenir préstec
        Prestec prestec = llistaPrestecs.getAt(position);
        // condició 'if' per comprovar si existeix
        if(prestec == null){
            throw new BiblioException("No s'ha trobat préstec");
        }
        // condició 'if' per comprovar si s'ha retornat o no
        if (prestec.getRetornat()){
            throw new BiblioException("Ja s'ha retornat");
        }
        // retornar el préstec
        prestec.retorna();
    }

    @Override
    // Recuperar préstecs. Retorna un ArrayList amb tots els préstecs
    public ArrayList<Prestec> recuperaPrestecs(){
        return llistaPrestecs.getArrayList();
    }

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
