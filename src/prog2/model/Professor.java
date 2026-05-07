package prog2.model;

/**
 * La classe Professor representa un tipus d'usuari dins del sistema.
 * Estén la classe Usuari i defineix una configuració de préstecs més àmplia que la dels estudiants.
 */
public class Professor extends Usuari{

    /**
     * Constructor per a un nou objecte Professor.
     * Utilitza el constructor de la superclasse Usuari per assignar els atributs bàsics.
     */
    public Professor (String email, String nom, String adreca) { super(email,nom,adreca); }

    /**
     * Retorna el tipus d'usuari, que en aquest cas és "Estudiant".
     * * @return String "Estudiant".
     */
    @Override
    public String tipusUsuari(){
        return "Professor";
    }

    /**
     * retorna la quantitat maxima de prestacs normals que pot fer un professor, per defecte és 2
     * @return int
     */
    @Override
    public int getMaxPrestecsNormals(){
        return 2;
    }

    /**
     * retorna la quantitat maxima de prestacs llargs que pot fer un professor, per defecte és 2
     * @return int
     */
    @Override
    public int getMaxPrestecsLlargs(){
        return 2;
    }
}
