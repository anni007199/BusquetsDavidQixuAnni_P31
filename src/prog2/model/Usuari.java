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

    /**
     * Retorna l'adreça de l'usuari.
     * * @return String amb l'adreça de l'usuari.
     */
    public String getAdreca(){
        return this.adreca;
    }

    /**
     * Retorna el nombre total de préstecs normals que té l'usuari.
     * * @return int que representa la quantitat de préstecs normals.
     */
    public int getNumPrestecsNormals(){
        return this.numPrestecsNormals;
    }

    /**
     * Retorna el nombre total de préstecs llargs que té l'usuari.
     * * @return int que representa la quantitat de préstecs llargs.
     */
    public int getNumPrestecsLlargs(){
        return this.numPrestecsLlargs;
    }

    /**
     * Retorna una cadena de caràcters que identifica la categoria o rol de l'usuari.
     * Mètode abstracte que s'ha d'implementar a les subclasses.
     * * @return String amb el tipus d'usuari.
     */
    @Override
    public abstract String tipusUsuari();

    /**
     * Retorna el límit màxim de préstecs normals que pot realitzar l'usuari.
     * En aquesta implementació per defecte retorna 0.
     * * @return int que representa el màxim de préstecs normals.
     */
    @Override
    public int getMaxPrestecsNormals(){
        return 0;
    }

    /**
     * Retorna el límit màxim de préstecs llargs que pot realitzar l'usuari.
     * En aquesta implementació per defecte retorna 0.
     * * @return int que representa el màxim de préstecs llargs.
     */
    @Override
    public int getMaxPrestecsLlargs(){
        return 0;
    }

    /**
     * Retorna una representació en format String de l'objecte Usuari,
     * incloent les dades de contacte i l'estat dels seus préstecs.
     * * @return String amb la informació detallada de l'usuari.
     */
    @Override
    public String toString(){
        return "Tipus="+tipusUsuari()+", Email="+getEmail()+", Nom="+getNom()+"Adreca="+getAdreca()+",Num. prestecs normals="+getNumPrestecsNormals()+", Num. prestecs llargs="+getNumPrestecsLlargs();
    }
}
