package prog2.model;

/**
 * Interfície que defineix el contracte per a qualsevol usuari del sistema.
 * Estableix els mètodes necessaris per gestionar la informació personal i
 * els límits de préstecs de cada perfil.
 */
public interface InUsuari {

    /**
     * Defineix l'adreça de correu electrònic de l'usuari.
     * * @param email String amb el nou correu electrònic.
     */
    void setEmail(String email);

    /**
     * Retorna l'adreça de correu electrònic de l'usuari.
     * * @return String amb l'email actual.
     */
    String getEmail();

    /**
     * Defineix el nom complet de l'usuari.
     * * @param nom String amb el nom .
     */
    void setNom(String nom);

    /**
     * Retorna el nom complet de l'usuari.
     * * @return String amb el nom de l'usuari.
     */
    String getNom();

    /**
     * Defineix l'adreça postal de residència de l'usuari.
     * * @param adreca String amb la nova adreça.
     */
    void setAdreca(String adreca);

    /**
     * Retorna l'adreça postal de l'usuari.
     * * @return String amb l'adreça actual.
     */
    String getAdreca();

    /**
     * Retorna una cadena de caràcters que identifica el rol de l'usuari (Ex: "Estudiant").
     * * @return String amb el tipus d'usuari.
     */
    String tipusUsuari();

    /**
     * Actualitza el comptador de préstecs s que l'usuari té actius.
     * * @param numPrestecsNormals int amb la nova quantitat de préstecs.
     */
    void setNumPrestecsNormals(int numPrestecsNormals);

    /**
     * Retorna la quantitat actual de préstecs normals realitzats per l'usuari.
     * * @return int amb el nombre de préstecs normals actius.
     */
    int getNumPrestecsNormals();

    /**
     * Actualitza el comptador de préstecs llargs que l'usuari té actius.
     * * @param numPrestecstLlargs int amb la nova quantitat de préstecs.
     */
    void setNumPrestecsLlargs(int numPrestecstLlargs);

    /**
     * Retorna la quantitat actual de préstecs llargs realitzats per l'usuari.
     * * @return int amb el nombre de préstecs llargs actius.
     */
    int getNumPrestecsLlargs();

    /**
     * Retorna el límit màxim permès de préstecs normals segons el tipus d'usuari.
     * * @return int amb la capacitat màxima de préstecs normals.
     */
    int getMaxPrestecsNormals();

    /**
     * Retorna el límit màxim permès de préstecs llargs segons el tipus d'usuari.
     * * @return int amb la capacitat màxima de préstecs llargs.
     */
    int getMaxPrestecsLlargs();

    /**
     * Retorna una informació textual detallada de l'usuari.
     * * @return String amb la informació de l'objecte.
     */
    @Override
    String toString();
};

