package prog2.model;

import prog2.vista.BiblioException;

import javax.annotation.processing.Processor;
import java.io.Serializable;
import java.util.Date;

/**
 * Classe abstracta que defineix l'estructura i el comportament base d'un préstec.
 * Implementa la interfície InPrestec i permet la persistència mitjançant Serializable.
 */
public abstract class Prestec implements InPrestec, Serializable {

    // Atributs
    private Exemplar exemplar;
    private Usuari usuari;
    private Date dataCreacio;
    private Date dataLimitRetorn;
    private boolean retornat;

    /**
     * Constructor per crear un nou préstec.
     * Calcula automàticament la data límit de retorn sumant la durada específica
     * del tipus de préstec a la data de creació.
     */
    public Prestec(Exemplar exemplar, Usuari usuari, Date dataCreacio){
        this.exemplar = exemplar;
        this.usuari = usuari;
        this.dataCreacio = dataCreacio;
        this.retornat = false;
        this.dataLimitRetorn = new Date(dataCreacio.getTime()+duradaPrestec());
    }
    // setters
    /**
     * Defineix l'exemplar associat al préstec.
     * @param exemplar Objecte Exemplar a assignar.
     */
    public void setExemplar(Exemplar exemplar){
        this.exemplar = exemplar;
    }

    /**
     * Defineix l'usuari titular del préstec.
     * @param usuari Objecte Usuari a assignar.
     */
    public void setUsuari(Usuari usuari){
        this.usuari = usuari;
    }

    /**
     * Defineix la data de creació del préstec.
     * @param data Objecte Date amb la data d'inici.
     */
    public void setDataCreacio(Date data){
        this.dataCreacio = data;
    }

    /**
     * Defineix la data límit per a la devolució de l'exemplar.
     * @param data Objecte Date amb la data límit.
     */
    public void setDataLimitRetorn(Date data){
        this.dataLimitRetorn = data;
    }

    /**
     * Actualitza l'estat de devolució del préstec.
     * @param retornat boolean que indica si s'ha retornat (true) o no (false).
     */
    public void setRetornat(boolean retornat){
        this.retornat = retornat;
    }

    // GETTERS
    /**
     * @return Exemplar objecte de préstec.
     */
    public Exemplar getExemplar(){return this.exemplar;}

    /**
     * @return Usuari titular del préstec.
     */
    public Usuari getUsuari(){
        return this.usuari;
    }

    /**
     * @return Date de creació del préstec.
     */
    public Date getDataCreacio(){
        return this.dataCreacio;
    }

    /**
     * @return Date límit de retorn.
     */
    public Date getDataLimitRetorn(){return this.dataLimitRetorn;}

    /**
     * @return boolean cert si el préstec ha estat retornat.
     */
    public boolean getRetornat(){return retornat;}

    /**
     * Mètode abstracte que retorna el nom del tipus de préstec.
     * @return String amb el tipus de préstec.
     */
    @Override
    public abstract String tipusPrestec();

    /**
     * Gestiona la devolució del préstec.
     * Actualitza l'estat de l'exemplar a disponible i decrementa el comptador
     * de préstecs actius de l'usuari segons el tipus de préstec realitzat.
     */
    public void retorna(){
        // si no estava retornat, ara es marca com a retornat
        retornat = true;
        // a l'exemplar ja està disponible, ja que s'ha retornat el llibre
        exemplar.setDisponible(true);
        // depenen de si és un préstec normal o llarg, es resta 1 a la classe que li toqui
        if(this instanceof PrestecNormal){
            this.usuari.setNumPrestecsNormals(usuari.getNumPrestecsNormals()-1);
        } else if(this instanceof PrestecLlarg){
            this.usuari.setNumPrestecsLlargs(usuari.getNumPrestecsLlargs()-1);
        }
    }

    /**
     * Mètode abstracte que defineix quant de temps pot durar el préstec.
     * @return long amb la durada en mil·lisegons.
     */
    @Override
    public abstract long duradaPrestec();

    /**
     * Comprova si el préstec ha superat la data límit sense ser retornat.
     * @return boolean cert si la data actual és posterior a la data límit i no s'ha retornat.
     */
    @Override
    public boolean prestecEndarrerit(){
        // condició 'if' per comprovar si s'ha retornat o no
        // si s'ha retorna, no hi ha endarreriment de préstec
        if (retornat){
            return false;
        }
        // crear una data actual
        Date dataActual = new Date();
        // usant "after", retorna 'true' si la data actual és després de la data límit
        return dataActual.after(dataLimitRetorn);
    }

    /**
     * Retorna una cadena amb tota la informació del préstec.
     * @return String amb la informació de l'objecte.
     */
    @Override
    public String toString(){
        return "Tipus="+tipusPrestec()+", Exemplar="+getExemplar()+", Usuari="+getUsuari()+", Data de creacio="+getDataCreacio()+", Data límit retorn="+getDataLimitRetorn()+", Retornat="+getRetornat();
    }
}
