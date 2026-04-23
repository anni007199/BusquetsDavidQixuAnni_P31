package prog2.adaptador;

import prog2.model.*;
import java.io.*;
import java.util.ArrayList;

import prog2.vista.BiblioException;

public class Adaptador implements Serializable {
    // Atributs
    private Dades dades;

    // Constructor
    public Adaptador(){
        dades = new Dades();
    }

    public void afegirExemplar(String id, String titol, String autor, boolean admetPrestecLlarg) throws BiblioException{
        dades.afegirExemplar(id, titol, autor, admetPrestecLlarg);
    }

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

    public void afegirUsuari(String email, String nom, String adreca, boolean esEstudiant) throws BiblioException {
        dades.afegirUsuari(email, nom, adreca, esEstudiant);
    }

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

    public void afegirPrestec(int exemplarPos, int usuariPos, boolean esLlarg) throws BiblioException {
        dades.afegirPrestec(exemplarPos, usuariPos, esLlarg);
    }

    public void retornarPrestec(int position) throws BiblioException{
        dades.retornarPrestec(position);
    }

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

    public int getNumExemplars() {
        return dades.getLlistaExemplars().getSize();
    }

    public int getNumUsuaris(){
        return dades.getLlistaUsuaris().getSize();
    }

    public int getNumPrestecs(){
        return dades.getLlistaPrestecs().getSize();
    }

    public void guardarDades(String camiDesti) throws BiblioException{
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
