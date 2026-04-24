package prog2.model;

import prog2.vista.BiblioException;

import javax.annotation.processing.Processor;
import java.io.Serializable;
import java.util.Date;

public abstract class Prestec implements InPrestec, Serializable {
    // Atributs
    private Exemplar exemplar;
    private Usuari usuari;
    private Date dataCreacio;
    private Date dataLimitRetorn;
    private boolean retornat;
    // Constructor
    public Prestec(Exemplar exemplar, Usuari usuari, Date dataCreacio){
        this.exemplar = exemplar;
        this.usuari = usuari;
        this.dataCreacio = dataCreacio;
        this.retornat = false;
        this.dataLimitRetorn = new Date(dataCreacio.getTime()+duradaPrestec());
    }
    // setters
    public void setExemplar(Exemplar exemplar){
        this.exemplar = exemplar;
    }
    public void setUsuari(Usuari usuari){
        this.usuari = usuari;
    }
    public void setDataCreacio(Date data){
        this.dataCreacio = data;
    }
    public void setDataLimitRetorn(Date data){
        this.dataLimitRetorn = data;
    }
    public void setRetornat(boolean retornat){
        this.retornat = retornat;
    }
    // getters
    public Exemplar getExemplar(){
        return this.exemplar;
    }
    public Usuari getUsuari(){
        return this.usuari;
    }
    public Date getDataCreacio(){
        return this.dataCreacio;
    }
    public Date getDataLimitRetorn(){
        return this.dataLimitRetorn;
    }
    public boolean getRetornat(){
        return retornat;
    }

    @Override
    public abstract String tipusPrestec();

    // Retornar prestec. Llança excepció si el prestec ja es vaig retornar
    public void retorna(){
        // condició 'if' per comprovar si ja s'ha retornat o no
        // en cas contrari, retorna excepció
        if(retornat){
            throw new RuntimeException("Ja està retornat");
        }
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

    @Override
    //Retornar durada prestec. La durada del prestec depen del tipus de prestec
    public abstract long duradaPrestec();

    @Override
    //Retornar true si el prestec està endarrerit per a la data actual
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

    @Override
    public String toString(){
        return "Tipus="+tipusPrestec()+", Exemplar="+getExemplar()+", Usuari="+getUsuari()+", Data de creacio="+getDataCreacio()+", Data límit retorn="+getDataLimitRetorn()+", Retornat="+getRetornat();
    }
}
