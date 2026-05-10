
package prog2.vista;

import java.util.List;
import java.util.Scanner;
import prog2.adaptador.Adaptador;
import java.util.ArrayList;

/**
 * Classe principal de la interfície d'usuari de l'aplicació BiblioUB.
 * Gestiona els menús textuals, la interacció amb l'usuari i les crides
 * a l'adaptador per processar les operacions de la biblioteca.
 */
public class BiblioUB {

    /**
     * Declara les constants del menu principal
     */
    static private enum OpcionsMenuPrincipal {
        MENU_PRINCIPAL_EXEMPLARS,
        MENU_PRINCIPAL_USUARIS,
        MENU_PRINCIPAL_PRESTECS,
        MENU_PRINCIPAL_SAVE,
        MENU_PRINCIPAL_LOAD,
        MENU_PRINCIPAL_EXIT};

    /**
     * Declara descripcions personalitzades per a les opcions del menú principal
     */
    static private String[] descMenuPrincipal={"Gestió Exemplars",
                                               "Gestió Usuaris",
                                               "Gestió Prestecs",
                                               "Guardar Dades",
                                               "Recuperar Dades",
                                               "Sortir"};

    /**
     * Enumeració per a les opcions del submenú de gestió d'exemplars.
     */
    static private enum OpcionsMenuGestioExemplars {
        MENU_GESTIO_EXEMPLARS_ADD,
        MENU_GESTIO_EXEMPLARS_VIEW,
        MENU_GESTIO_EXEMPLARS_EXIT
    };

    /**
     * Declara descripcions personalitzades per a les opcions del submenú de gestió d'exemplars
     */
    static private String[] descMenuGestioExemplars ={"Afegir Exemplar",
                                                      "Visualitzar Exemplars",
                                                      "Sortir"};

    /**
     * Enumeració per a les opcions del submenú de gestió de clients.
     */
    static private enum OpcionsMenuGestioClients {
        MENU_GESTIO_USUARIS_ADD,
        MENU_GESTIO_USUARIS_VIEW,
        MENU_GESTIO_USUARIS_EXIT
    };

    /**
     * Declara descripcions personalitzades per a les opcions del submenú de gestio d'usuaris
     */
    static private String[] descMenuGestioUsuaris ={"Afegir Usuari",
                                                    "Visualitzar Usuaris",
                                                    "Sortir"};

    /**
     * Enumeració per a les opcions del submenú de gestió de prestecs.
     */
    static private enum OpcionsMenuGestioPrestecs {
        MENU_GESTIO_PRESTECS_ADD,
        MENU_GESTIO_PRESTECS_REMOVE,
        MENU_GESTIO_PRESTECS_VIEW,
        MENU_GESTIO_PRESTECS_VIEW_URG,
        MENU_GESTIO_PRESTECS_EXIT
    };

    /**
     * Declara descripcions personalitzades per a les opcions del submenú de gestio de prestecs
     */
    static private String[] descMenuGestioPrestecs ={"Afegir Prestec",
                                                     "Retornar Prestec",
                                                     "Visualitzar Prestecs",
                                                     "Visualitzar Prestecs no Retornats",
                                                     "Sortir"};

    
    /** Adaptador de l'aplicació */
    private Adaptador adaptador;

    /**
     * Constructor de la classe BiblioUB.
     * Inicialitza l'adaptador que actuarà com a mediador amb les dades.
     */
    public BiblioUB() {
        adaptador = new Adaptador();
    }

    /**
     * Mètode principal que arrenca el bucle de control del menú principal.
     * Gestiona les redireccions als submenús
     */
    public void gestioBiblioUB() {
        // Creem un objecte per llegir des del teclat
        Scanner sc = new Scanner(System.in);
        
        // Creem l'objecte per al menú. Li passem com a primer paràmetre el nom del menú
        Menu<OpcionsMenuPrincipal> menu = new Menu<>("Menu principal", OpcionsMenuPrincipal.values());

        // Assignem la descripció de les opcions
        menu.setDescripcions(descMenuPrincipal);
        
        OpcionsMenuPrincipal opcio;
        do {
            // Mostrem les opcions del menú i demanem una opció
            menu.mostrarMenu();
            opcio = menu.getOpcio(sc);

            // Fem les accions necessàries per a l'opció triada
            switch(opcio) {
                case MENU_PRINCIPAL_EXEMPLARS:
                    // Mostra el menú per a la gestió d'exemplars
                    menuGestioExemplars(sc);
                    break;

                case MENU_PRINCIPAL_USUARIS:
                    // Mostra el menú per a la gestió d'usuaris
                    menuGestioUsuaris(sc);
                    break;

                case MENU_PRINCIPAL_PRESTECS:
                    // Mostra el menú per a la gestió de prestecs
                    menuGestioPrestecs(sc);
                    break;

                case MENU_PRINCIPAL_SAVE:
                    // Guardar dades
                    String dstFile = getFilePath(sc,false); // Obtenir el fitxer de sortida
                    if(dstFile != null) {
                        // Guardar les dades al fitxer triat
                        try {
                             this.adaptador.guardaDades(dstFile);
                             System.err.println("Les dades estan guardades de manera correcta.");
                        } catch (BiblioException ex) {
                            System.out.println("ERROR en guardar les dades: " + ex.getMessage());
                        }
                    }                   
                    break;
                case MENU_PRINCIPAL_LOAD:
                    // Carregar dades                   
                    String srcFile = getFilePath(sc,false); // Obtenir el fitxer d'entrada
                    if(srcFile != null) {
                        // Carregar les dades del fitxer triat
                        try {
                             this.adaptador.carregaDades(srcFile);
                             System.err.println("Les dades estan carregades de manera correcta.");
                        } catch(BiblioException ex) {
                            System.out.println("ERROR en carregar les dades: " + ex.getMessage());
                        }
                    }     
                    break;
                case MENU_PRINCIPAL_EXIT:
                    // Sortir      1
                    System.err.println("Sortir de l'aplicació.");
                    break;
            }
        } while(opcio != OpcionsMenuPrincipal.MENU_PRINCIPAL_EXIT);
    }

    /**
     * Gestiona el submenú de gestió d'exemplars.
     * @param sc Scanner per a la lectura del teclat.
     */
    private void menuGestioExemplars(Scanner sc) {
        // crear menu amb opcions d'exemplars
        Menu<OpcionsMenuGestioExemplars> menu = new Menu<>("Gestió Exemplars", OpcionsMenuGestioExemplars.values());
        // posar les descripcions del menú
        menu.setDescripcions(descMenuGestioExemplars);

        OpcionsMenuGestioExemplars opcionsMenuGestioExemplars;
        // Bucle 'do-while'
        do{
            // mostrar menu
            menu.mostrarMenu();
            // llegir opció triada
            opcionsMenuGestioExemplars = menu.getOpcio(sc);

            switch (opcionsMenuGestioExemplars){
                case MENU_GESTIO_EXEMPLARS_ADD:
                    // afegir exemplar
                    afegirExemplar(sc);
                    break;
                case MENU_GESTIO_EXEMPLARS_VIEW:
                    // mostrar llista d'exemplars
                    showList("Llista d'exemplars",adaptador.getLlistaExemplars());
                    break;
                case MENU_GESTIO_EXEMPLARS_EXIT:
                    // sortir del menú
                    System.out.println("Sortir del menú gestió exemplars per anar al menú principal.");
                    break;
            }
            // si no és "MENU_GESTIO_EXEMPLARS_EXIT" mai surt del bucle
        } while(opcionsMenuGestioExemplars != OpcionsMenuGestioExemplars.MENU_GESTIO_EXEMPLARS_EXIT);
    }
    
    /**
     * Demana les dades per teclat per registrar un nou exemplar.
     * @param sc Scanner per a la lectura del teclat.
     */
    private void afegirExemplar(Scanner sc){
        System.out.println("Afegir un nou exemplar");
        System.out.println("Introdueix el ID: ");
        String id = sc.nextLine();
        System.out.println("Introdueix el títol: ");
        String titol = sc.nextLine();
        System.out.println("Introdueix l'autor: ");
        String autor = sc.nextLine();
        System.out.println("Admet préstec llarg? (true/false): ");
        // "Boolean.parseBoolean(text)" això serveix per convertir el text a boolean
        boolean admetPrestecLlarg = Boolean.parseBoolean(sc.nextLine());
        // try-catch per captura excepció
        try{
            adaptador.afegirExemplar(id,titol,autor,admetPrestecLlarg);
            System.out.println("Exemplar s'ha afegit de manera correcta.");
        } catch (BiblioException error){
            System.out.println("ERROR: "+error.getMessage());
        }
    }

    /**
     * Gestiona el submenú de gestió d'usuaris.
     * @param sc Scanner per a la lectura del teclat.
     */
    private void menuGestioUsuaris(Scanner sc) {
        // crear menu amb opcions d'usuaris
        Menu<OpcionsMenuGestioClients> menu = new Menu<>("Gestió Clients", OpcionsMenuGestioClients.values());
        // posar les descripcions del menú
        menu.setDescripcions(descMenuGestioUsuaris);

        OpcionsMenuGestioClients opcionsMenuGestioClients;
        // Bucle 'do-while'
        do{
            // mostrar menu
            menu.mostrarMenu();
            // llegir opció triada
            opcionsMenuGestioClients = menu.getOpcio(sc);

            switch (opcionsMenuGestioClients){
                case MENU_GESTIO_USUARIS_ADD:
                    // afegir usuari
                    afegirUsuari(sc);
                    break;
                case MENU_GESTIO_USUARIS_VIEW:
                    // mostrar llista d'usuaris
                    showList("Llista d'usuaris",adaptador.getLlistaUsuaris());
                    break;
                case MENU_GESTIO_USUARIS_EXIT:
                    // sortir del menú
                    System.out.println("Sortir del menú gestió exemplars per anar al menú principal.");
                    break;
            }
            // si no és "MENU_GESTIO_USUARIS_EXIT" mai surt del bucle
        } while(opcionsMenuGestioClients != OpcionsMenuGestioClients.MENU_GESTIO_USUARIS_EXIT);
    }

    /**
     * Demana les dades per teclat per registrar un nou usuari.
     * @param sc Scanner per a la lectura del teclat.
     */
    private void afegirUsuari(Scanner sc){
        System.out.println("Afegir un nou usuari");
        System.out.println("Introdueix l'email: ");
        String email = sc.nextLine();
        System.out.println("Introdueix el nom: ");
        String nom = sc.nextLine();
        System.out.println("Introdueix l'adreça: ");
        String adreca = sc.nextLine();
        System.out.println("Ets estudiant? (true/false): ");
        // "Boolean.parseBoolean(text)" això serveix per convertir el text a boolean
        boolean esEstudiant = Boolean.parseBoolean(sc.nextLine());
        // try-catch per captura excepció
        try{
            adaptador.afegirUsuari(email,nom,adreca,esEstudiant);
            System.out.println("Usuari s'ha afegit de manera correcta.");
        } catch (BiblioException error){
            System.out.println("ERROR: "+error.getMessage());
        }
    }

    /**
     * Gestiona el submenú per a les operacions amb préstecs.
     * @param sc Scanner per a la lectura del teclat.
     */
    private void menuGestioPrestecs(Scanner sc) {
        // crear menu amb opcions dels préstecs
        Menu<OpcionsMenuGestioPrestecs> menu = new Menu<>("Gestió Préstecs", OpcionsMenuGestioPrestecs.values());
        // posar les descripcions del menú
        menu.setDescripcions(descMenuGestioPrestecs);

        OpcionsMenuGestioPrestecs opcionsMenuGestioPrestecs;
        // Bucle 'do-while'
        do{
            // mostrar menu
            menu.mostrarMenu();
            // llegir opció triada
            opcionsMenuGestioPrestecs = menu.getOpcio(sc);

            switch (opcionsMenuGestioPrestecs){
                case MENU_GESTIO_PRESTECS_ADD:
                    // afegir préstec
                    afegirPrestec(sc);
                    break;
                case MENU_GESTIO_PRESTECS_REMOVE:
                    cancelarPrestec(sc);
                    break;
                case MENU_GESTIO_PRESTECS_VIEW:
                    // mostrar llista d'exemplars
                    showList("Llista de préstecs",adaptador.getLlistaPrestecs());
                    break;
                case MENU_GESTIO_PRESTECS_VIEW_URG:
                    showList("Llista de préstecs no retornats",adaptador.getLlistaPrestecsNoRetornats());
                    break;
                case MENU_GESTIO_PRESTECS_EXIT:
                    // sortir del menú
                    System.out.println("Sortir del menú gestió exemplars per anar al menú principal.");
                    break;
            }
            // si no és "MENU_GESTIO_EXEMPLARS_EXIT" mai surt del bucle
        } while(opcionsMenuGestioPrestecs != OpcionsMenuGestioPrestecs.MENU_GESTIO_PRESTECS_EXIT);
    }

    /**
     * Demana els índexs per pantalla per crear un préstec.
     * @param sc Scanner per a la lectura del teclat.
     */
    private void afegirPrestec(Scanner sc){
        System.out.println("Afegir un préstec ");
        // condició 'if' per comprovar si hi ha exemplars o no
        if(adaptador.getNumExemplars() == 0){
            System.out.println("ERROR: No s'ha trobat exemplars disponible per fer préstecs ");
            return;
        }
        // condició 'if' per comprovar si hi ha usuaris
        if(adaptador.getNumUsuaris() == 0){
            System.out.println("ERROR: No s'ha trobat usuaris disponible per fer préstecs ");
            return;
        }
        // mostrar llista d'exemplars
        showList("Exemplars disponibles",adaptador.getLlistaExemplars());
        System.out.println("Tria la posició de l'exemplar: ");
        int exemplarPos = Integer.parseInt(sc.nextLine());
        // mostrar llista d'usuaris
        showList("Usuaris disponibles",adaptador.getLlistaUsuaris());
        System.out.println("Tria la posició de l'usuari: ");
        int usuariPos = Integer.parseInt(sc.nextLine());
        // preguntar si l'usuari vol préstec llarg o no
        System.out.println("És de préstec llarg o no? (true/false): ");
        boolean esLlarg = Boolean.parseBoolean(sc.nextLine());

        try{
            adaptador.afegirPrestec(exemplarPos,usuariPos,esLlarg);
            System.out.println("Préstec s'ha afegit de manera correcta.");
        } catch (BiblioException error){
            System.out.println("ERROR: "+error.getMessage());
        }
    }

    /**
     * Demana l'índex del préstec que es desitja cancelar.
     * @param sc Scanner per a la lectura del teclat.
     */
    private void cancelarPrestec(Scanner sc){
        System.out.println("Cancel·lar préstec.");
        // condició 'if' per comprovar si hi ha préstec o no
        if(adaptador.getNumPrestecs()==0){
            System.out.println("ERROR: No hi ha préstecs.");
            return;
        }
        //mostrar llista de préstecs
        showList("Llista de préstecs",adaptador.getLlistaPrestecs());
        System.out.print("Tria la posició del préstec a retornar.");
        int posicio = Integer.parseInt(sc.nextLine());

        try{
            adaptador.retornarPrestec(posicio);
            System.out.println("Préstec s'ha retornat de manera correcta.");
        } catch (BiblioException error){
            System.out.println("ERROR: "+error.getMessage());
        }
    }

     /**
     * Mostra una llista d'objectes
     * @param title Títol a posar com a capçalera
     * @param lines Llista d'objectes per mostrar
     */
    private void showList(String title, List<String> lines) {
        System.out.println("============================================");
        System.out.println(title);
        System.out.println("============================================");
        int i = 0;
        for(String l : lines) {
            System.out.println("\t[" + (i++) + "] " + l);
        }
        System.out.println("============================================");
    }


    /**
     * Demana el camí d'un fitxer
     * @param sc Objecte per a la lectura de dades de teclat
     * @param mustExist Exigeix que el fitxer existeixi (True) o no (False)
     * @return Ruta al fitxer entrada per l'usuari o null si s'ha cancelat
     */
    private String getFilePath(Scanner sc, boolean mustExist) {
        String filePath = null;

        // Mostrar el missatge demanant la entrada
        System.out.println("Entra ruta completa fitxer (o ENTER per ometre):");

            // Llegim la ruta del fitxer
            filePath = sc.nextLine();

            // Si la ruta està buida retornem un null
            if(filePath.isEmpty()) {
                return null;
            }

        return filePath;
    }

}
