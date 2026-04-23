package prog2.model;

import java.io.Serializable;

public class Exemplar implements InExemplar, Serializable {
    // Atributs
    private String id;
    private String titol;
    private String autor;
    private boolean admetPrestecLlarg;
    private boolean disponible;

    // Constructor
    public Exemplar (String id, String titol, String autor, boolean admetPrestecLlarg){
        this.id = id;
        this.titol = titol;
        this.autor = autor;
        this.admetPrestecLlarg = admetPrestecLlarg;
    }
    // setters
    public void setId(String id){
        this.id = id;
    }
    public void setTitol(String titol){
        this.titol = titol;
    }
    public void setAutor(String autor){
        this.autor = autor;
    }
    public void setAdmetPrestecLlarg(boolean admetPrestecLlarg){
        this.admetPrestecLlarg = admetPrestecLlarg;
    }
    public void setDisponible(boolean disponible){
        this.disponible = disponible;
    }
    // getters
    public String getId(){
        return this.id;
    }
    public String getTitol(){
        return this.titol;
    }
    public String getAutor(){
        return this.autor;
    }
    public boolean getAdmetPrestecLlarg(){
        return this.admetPrestecLlarg;
    }
    public boolean getDisponible(){
        return this.disponible;
    }
    // Mètode 'toString'
    @Override
    public String toString(){
        return "Id="+getId()+", Titol="+getTitol()+", Autor="+getAutor()+", Admet Prestec Llarg"+getAdmetPrestecLlarg()+", Disponible"+getDisponible();
    }
}
