package prog2.model;

import java.util.Date;

/**
 * Interfície que defineix les operacions i l'estat d'un préstec al sistema.
 * Gestiona la relació entre un usuari i un exemplar i
 * el control de dates i terminis de devolució.
 */
public interface InPrestec {

    /**
     * Assigna l'exemplar que és objecte del préstec.
     * @param exemplar L'objecte de tipus Exemplar a prestar.
     */
    void setExemplar(Exemplar exemplar);

    /**
     * Retorna l'exemplar associat a aquest préstec.
     * @return Exemplar que s'ha prestat.
     */
    Exemplar getExemplar();

    /**
     * Assigna l'usuari que realitza el préstec.
     * @param usuari L'objecte de tipus Usuari que rep l'exemplar.
     */
    void setUsuari(Usuari usuari);

    /**
     * Retorna l'usuari que té actualment el préstec.
     * @return Usuari titular del préstec.
     */
    Usuari getUsuari();

    /**
     * Defineix la data en què es formalitza el préstec.
     * @param data Objecte Date amb la data de creació.
     */
    void setDataCreacio(Date data);

    /**
     * Retorna la data en què es va realitzar el préstec.
     * @return Date de creació del préstec.
     */
    Date getDataCreacio();

    /**
     * Estableix la data màxima per retornar l'exemplar sense penalització.
     * @param data Objecte Date amb la data límit.
     */
    void setDataLimitRetorn(Date data);

    /**
     * Retorna la data límit fixada per a la devolució de l'exemplar.
     * @return Date límit de retorn.
     */
    Date getDataLimitRetorn();

    /**
     * Retorna una cadena de caràcters que identifica la modalitat del préstec.
     * @return String amb el tipus de préstec (ex: "Normal" o "Llarg").
     */
    String tipusPrestec();

    /**
     * Actualitza l'estat del préstec per indicar si l'exemplar ha estat retornat.
     * @param retornat boolean cert si ja s'ha retornat, fals en cas contrari.
     */
    void setRetornat(boolean retornat);

    /**
     * Indica si l'exemplar associat al préstec ja es troba a la biblioteca.
     * @return boolean cert si el préstec ha finalitzat, fals si segueix actiu.
     */
    boolean getRetornat();

    /**
     * Executa la lògica de devolució del préstec, marcant-lo com a retornat
     */
    void retorna();

    /**
     * Calcula la durada total del préstec en funció de les seves característiques.
     * La durada pot variar segons si el tipus de préstec és normal o llarg.
     * @return long que representa la durada en mil·lisegons
     */
    long duradaPrestec();

    /**
     * Comprova si la data actual és posterior a la data límit de retorn
     * sense que l'exemplar hagi estat retornat.
     * @return boolean cert si el préstec està fora de termini, fals en cas contrari.
     */
    boolean prestecEndarrerit();

    /**
     * Retorna una representació textual de les dades del préstec.
     * @return String amb la informació detallada del préstec.
     */
    @Override
    String toString();
}
