package prog2.model;

/**
 * Interfície que defineix les propietats bàsiques d'un exemplar de la biblioteca.
 * Estableix els mètodes per gestionar la identificació, la possesió i les
 * condicions de préstec de cada unitat.
 */
public interface InExemplar {

    /**
     * Defineix l'identificador únic de l'exemplar.
     * @param id String amb el ID identificador.
     */
    void setId(String id);

    /**
     * Retorna l'identificador únic de l'exemplar.
     * @return String amb l'ID de l'exemplar.
     */
    String getId();

    /**
     * Defineix el títol de l'exemplar
     * @param titol String amb el nom de l'exemplar.
     */
    void setTitol(String titol);

    /**
     * Retorna el títol de l'exemplar.
     * @return String amb el títol.
     */
    String getTitol();

    /**
     * Defineix l'autor o autors de l'exemplar.
     * @param autor String amb el nom de l'autor.
     */
    void setAutor(String autor);

    /**
     * Retorna l'autor de l'exemplar.
     * @return String amb l'autor.
     */
    String getAutor();

    /**
     * Defineix si aquest exemplar en concret pot ser objecte de préstecs
     * de llarga durada.
     * @param admetPrestecLlarg boolean cert si admet préstec llarg, fals en cas contrari.
     */
    void setAdmetPrestecLlarg(boolean admetPrestecLlarg);

    /**
     * Indica si l'exemplar permet la modalitat de préstec de llarga durada.
     * @return boolean cert si la configuració de l'exemplar ho permet.
     */
    boolean getAdmetPrestecLlarg();

    /**
     * Retorna una representació textual amb la informació de l'exemplar.
     * @return String amb la informació detallada de l'objecte.
     */
    @Override
    String toString();
}
