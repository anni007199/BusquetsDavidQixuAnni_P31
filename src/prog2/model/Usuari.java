package prog2.model;

import javax.annotation.processing.Processor;
import java.io.Serializable;

/**
 * La clase Usuari defineix les dades inicials de l'usuari i la quantitat de prestecs que han fet.
 * ELs parametres d'un usuari son email, nom, adreca, numPrestacsNormals, numPrestecsLlargs
 */
public abstract class Usuari implements InUsuari, Serializable {
    // Atributs
    private String email;
    private String nom;
    private String adreca;
    private int numPrestecsNormals;
    private int numPrestecsLlargs;


    /**
     * Constructor per un usuari
     * Posa la quantitat actual de prestacs normals i llargs a 0
     */
    public Usuari(String email, String nom, String adreca){
        this.email = email;
        this.nom = nom;
        this.adreca = adreca;
        this.numPrestecsNormals = 0;
        this.numPrestecsLlargs = 0;
    }
    // setters
    /**
     * Canvia l'email de l'usuari
     * @param email
     */
    public void setEmail(String email){
        this.email = email;
    }

    /**
     * Canvia el nom de l'usuari
     * @param nom
     */
    public void setNom(String nom){
        this.nom = nom;
    }

    /**
     * Canvia l'adreca de l'usuari
     * @param adreca
     */
    public void setAdreca(String adreca){
        this.adreca = adreca;
    }

    /**
     * Canvia el número de prestecs normals actuals de l'usuari
     * @param numPrestecsNormals
     */
    public void setNumPrestecsNormals(int numPrestecsNormals){
        this.numPrestecsNormals = numPrestecsNormals;
    }

    /**
     * Canvia el número de prestecs llargs actuals de l'usuari
     * @param numPrestecsLlargs
     */
    public void setNumPrestecsLlargs(int numPrestecsLlargs){
        this.numPrestecsLlargs = numPrestecsLlargs;
    }

    // getters
    /**
     * Retorna l'email de l'usuari
     * @return string email
     */
    public String getEmail(){
        return this.email;
    }

    /**
     * Retorna el nom de l'usuari
     * @return string nom
     */
    public String getNom(){
        return this.nom;
    }
    public String getAdreca(){
        return this.adreca;
    }
    public int getNumPrestecsNormals(){
        return this.numPrestecsNormals;
    }
    public int getNumPrestecsLlargs(){
        return this.numPrestecsLlargs;
    }

    /**
     *
     *
     */
    @Override
    public abstract String tipusUsuari();

    /**
     *
     *
     */
    @Override
    public int getMaxPrestecsNormals(){
        return 0;
    }

    /**
     *
     *
     */
    @Override
    public int getMaxPrestecsLlargs(){
        return 0;
    }

    /**
     *
     *
     */
    @Override
    public String toString(){
        return "Tipus="+tipusUsuari()+", Email="+getEmail()+", Nom="+getNom()+"Adreca="+getAdreca()+",Num. prestecs normals="+getNumPrestecsNormals()+", Num. prestecs llargs="+getNumPrestecsLlargs();
    }
}
