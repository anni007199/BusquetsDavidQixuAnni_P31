package prog2.model;

/**
 * La classe Estudiant representa un tipus d'usuari específic dins del sistema.
 * Estén la classe Usuari i defineix els límits de préstecs propis per a estudiants.
 */
public class Estudiant extends Usuari {
    // Constructor
    /**
     * Constructor per a un nou objecte Estudiant.
     * Fa servir el constructor de la superclasse Usuari per inicialitzar les dades bàsiques.
     */
    public Estudiant(String email, String nom, String adreca){
        super(email,nom,adreca);
    }

    /**
     * Retorna el tipus d'usuari, que en aquest cas és "Estudiant".
     * * @return String "Estudiant".
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
