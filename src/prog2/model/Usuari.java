package prog2.model;

import javax.annotation.processing.Processor;

public abstract class Usuari implements InUsuari {
    // Atributs
    private String email;
    private String nom;
    private String adreca;
    private int numPrestecsNormals;
    private int numPrestecsLlargs;

    // Constructor
    public Usuari(String email, String nom, String adreca){
        this.email = email;
        this.nom = nom;
        this.adreca = adreca;
        this.numPrestecsNormals = 0;
        this.numPrestecsLlargs = 0;
    }
    // setters
    public void setEmail(String email){
        this.email = email;
    }
    public void setNom(String nom){
        this.nom = nom;
    }
    public void setAdreca(String adreca){
        this.adreca = adreca;
    }
    public void setNumPrestecsNormals(int numPrestecsNormals){
        this.numPrestecsNormals = numPrestecsNormals;
    }
    public void setNumPrestecsLlargs(int numPrestecsLlargs){
        this.numPrestecsLlargs = numPrestecsLlargs;
    }
    // getters
    public String getEmail(){
        return this.email;
    }
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

    @Override
    public String tipusUsuari(){
        if(this instanceof Processor){
            return "Professor";
        }
        if(this instanceof Estudiant) {
            return "Estudiant";
        }
        return "Usuari";
    }

    @Override
    public int getMaxPrestecsNormals(){
        return 0;
    }
    @Override
    public int getMaxPrestecsLlargs(){
        return 0;
    }

    @Override
    public String toString(){
        return "Tipus="+tipusUsuari()+", Email="+getEmail()+", Nom="+getNom()+"Adreca="+getAdreca()+",Num. prestecs normals="+getNumPrestecsNormals()+", Num. prestecs llargs="+getNumPrestecsLlargs();
    }
}
