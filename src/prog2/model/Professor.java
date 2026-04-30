package prog2.model;

public class Professor extends Usuari{
    // Constructor
    public Professor (String email, String nom, String adreca) { super(email,nom,adreca); }

    /**
     *
     *
     */
    @Override
    public String tipusUsuari(){
        return "Professor";
    }

    /**
     * retorna la quantitat maxima de prestacs normals que pot fer un profesor, per defecte és 2
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
