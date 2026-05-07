package prog2.model;

import java.io.Serializable;

/**
 * La classe Exemplar representa un recurs físic de la biblioteca.
 * Implementa la interfície InExemplar i permet emmagatzemar la informació
 * sobre l'obra, la seva disponibilitat i les condicions de préstec.
 */
public class Exemplar implements InExemplar, Serializable {
    // Atributs
    private String id;
    private String titol;
    private String autor;
    private boolean admetPrestecLlarg;
    private boolean disponible;


    /**
     * Constructor per crear un nou exemplar.
     * Per defecte, qualsevol exemplar nou es crea amb l'estat de disponibilitat a cert.
     */
    public Exemplar (String id, String titol, String autor, boolean admetPrestecLlarg){
        this.id = id;
        this.titol = titol;
        this.autor = autor;
        this.admetPrestecLlarg = admetPrestecLlarg;
        // un exemplar nou, sempre és disponible
        this.disponible = true;
    }


    // SETTERS
    /**
     * {@inheritDoc}
     * @param id String amb el nou identificador.
     */
    public void setId(String id){
        this.id = id;
    }

    /**
     * {@inheritDoc}
     * @param titol String amb el nou títol.
     */
    public void setTitol(String titol){
        this.titol = titol;
    }

    /**
     * {@inheritDoc}
     * @param autor String amb el nou autor.
     */
    public void setAutor(String autor){
        this.autor = autor;
    }

    /**
     * {@inheritDoc}
     * @param admetPrestecLlarg boolean per habilitar o deshabilitar el préstec llarg.
     */
    public void setAdmetPrestecLlarg(boolean admetPrestecLlarg){
        this.admetPrestecLlarg = admetPrestecLlarg;
    }

    /**
     * Actualitza l'estat de disponibilitat de l'exemplar al prestatge.
     * @param disponible boolean cert si està lliure, fals si està prestat.
     */
    public void setDisponible(boolean disponible){
        this.disponible = disponible;
    }


    // GETTERS
    /**
     * {@inheritDoc}
     * @return String amb l'ID de l'exemplar.
     */
    public String getId(){
        return this.id;
    }

    /**
     * {@inheritDoc}
     * @return String amb el títol.
     */
    public String getTitol(){
        return this.titol;
    }

    /**
     * {@inheritDoc}
     * @return String amb l'autor.
     */
    public String getAutor(){
        return this.autor;
    }

    /**
     * {@inheritDoc}
     * @return boolean cert si l'exemplar permet préstecs llargs.
     */
    public boolean getAdmetPrestecLlarg(){
        return this.admetPrestecLlarg;
    }

    /**
     * Indica si l'exemplar es troba actualment disponible per ser prestat.
     * @return boolean cert si està disponible, fals en cas contrari.
     */
    public boolean isDisponible(){
        return this.disponible;
    }

    /**
     * Retorna una cadena amb tota la informació detallada de l'exemplar.
     * @return String amb la informació completa de l'exemplar.
     */
    @Override
    public String toString(){
        return "Id="+getId()+", Titol="+getTitol()+", Autor="+getAutor()+", Admet Prestec Llarg="+getAdmetPrestecLlarg()+", Disponible="+isDisponible();
    }
}
