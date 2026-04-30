package prog2.model;

/**
 *
 *
 */
public class Estudiant extends Usuari {
    // Constructor
    /**
     * Constructor per un estudiant
     * Fa servir super per referir a usuari
     */
    public Estudiant(String email, String nom, String adreca){
        super(email,nom,adreca);
    }

    /**
     * retorna el tipus d'usuari, que és estudiant
     * @return string
     */
    @Override
    public String tipusUsuari(){
        return "Estudiant";
    }

    /**
     * retorna la quantitat maxima de prestacs normals que pot fer un estudiant, per defecte és 2
     * @return int
     */
    @Override
    public int getMaxPrestecsNormals(){
        return 2;
    }

    /**
     * retorna la quantitat maxima de prestacs llargs que pot fer un estudiant, per defecte és 1
     * @return int
     */
    @Override
    public int getMaxPrestecsLlargs(){
        return 1;
    }
}
