package model;

public class Proprietes {
	public static final String ECRAN_ACCUEIL = "Menu";
    public static final String ECRAN_ACCUEIL_FXML = "fxml/Menu.fxml";
    public static final String ECRAN_REGLES = "Regles";
    public static final String ECRAN_REGLES_FXML = "fxml/Regles.fxml"; 
    public static final String ECRAN_MODE = "Mode";
    public static final String ECRAN_MODE_FXML = "fxml/ModeJeu.fxml";
    public static final String ECRAN_JEU = "Jeu";
    public static final String ECRAN_JEU_FXML = "fxml/Jeu.fxml";
    public static final String ECRAN_VICTOIRE = "Victoire";
    public static final String ECRAN_VICTOIRE_FXML = "fxml/Victoire.fxml";
    public static final String ECRAN_MENU_MULTI = "MenuMulti";
    public static final String ECRAN_MENU_MULTI_FXML = "fxml/MenuMulti.fxml";
    public static final String ECRAN_CREATION = "CreerPartie";
    public static final String ECRAN_CREATION_FXML = "fxml/CreerPartie.fxml";
    public static final String ECRAN_MULTI = "JeuMulti";
    public static final String ECRAN_MULTI_FXML = "fxml/JeuMulti.fxml";
    
    public static final String BUTTON_PATH = "src/ressources/decor/button.wav";
    public static final String MEDIA_PATH = "src/ressources/decor/christmas.mp3";
    public static final String IMAGE_SONON_PATH = "/ressources/decor/r_son-on.png";
    public static final String IMAGE_SONOFF_PATH = "/ressources/decor/r_son-off.png";
    public static final String IMAGE_MUSIQUEON_PATH = "/ressources/decor/r_music-on.png";
    public static final String IMAGE_MUSIQUEOFF_PATH = "/ressources/decor/r_music-off.png";
    
    public static final String IMAGE_JOUEUR_PATH = "/ressources/joueurs/"; /* + MODEJEU +COULEUR.png*/
    public static final String IMAGE_TUILE_1POISSON = "/ressources/decor/j_bloc_1poisson.png";
    public static final String IMAGE_TUILE_2POISSON = "/ressources/decor/j_bloc_2poisson.png";
    public static final String IMAGE_TUILE_3POISSON = "/ressources/decor/j_bloc_3poisson.png";
    
    public static final String AURA_JAUNE = "/ressources/decor/AuraJaune.png";
    public static final String AURA_VERTE = "/ressources/decor/AuraVerte.png";
	public static final String AURA_ROUGE = "/ressources/decor/AuraRouge.png";
	public static final String AURA_BLEUE = "/ressources/decor/AuraBleue.png";
    
    public static final String[] AURAS = { AURA_JAUNE, AURA_VERTE, AURA_ROUGE, AURA_BLEUE} ;
    
    public final static int AUCUN = 0 ;
    public final static int JOUEUR = 1 ;
    public final static int CREVETTE = 2 ;
    public final static int EVE = 3 ;
    public final static int ORQUE = 4 ;
   
    public final static int JAUNE = 0 ; 
    public final static int VERT = 1 ;
    public final static int ROUGE = 2 ;
    public final static int BLEU = 3 ;
    
    public static final String STYLE_NORMAL = "-fx-background-color: transparent; -fx-padding: 5 5 5 5 ;";
    public static final String STYLE_PRESSED = "-fx-background-color: transparent; -fx-padding: 6 5 5 6;";
    
    public static final String[] reglesTitres = {
    		"Objectif",
    		"Phases de jeu",
    		"Phases de jeu",
    		"Fin de tour",
    		"Fin de la partie"
    };
    
    public static final String[] reglesCorps = {
    		"Sur la banquise, les pingouins partent à la chasse au poisson en sautant d’un bloc de glace à l’autre.\n\n"
    				+"Pour cela, les joueurs essaient d’attraper un maximum de poissons, tout en veillant à ne pas être bloqués ou isolés sur la banquise.\n\n"
    				+"Cette banquise est générée aléatoirement selon 8 lignes de blocs de glaces, (alternativement 7 et 8 blocs). "
    				+"La répartition du nombre de poissons sur ces blocs est aléatoire.",
    		"Dans un premier temps, chacun son tour, les joueurs placent un de leurs pingouins sur un bloc de glace innoccupé ayant un seul poisson. "
    				+"Ils continuent jusqu’à ce que tous les pingouins soient placés.\n\n"
    				+"Ensuite, chacun son tour, un joueur peut déplacer un seul de ses pinguouins :\n"
    				+"\t- en ligne droite,\n"
    				+"\t- dans une des 6 directions qui entoure son bloc de glace,\n"
    				+"\t- d’autant de cases que souhaité,\n"
    				+"\t- sans franchir d’obstacle.\n\n"
    				+"Un obstacle est soit une extrémité de la banquise, soit un trou dans la banquise, soit un bloc de glace occupé par un autre pingouin.\n",
    		"",
    		"A la fin d'un tour, le joueur gagne le bloc de glace dont son pinguouin est parti ainsi que le nombre de poissons associés.\n\n"
    				+"Si l'un de ses pinguouins est isolé sur un bloc de glace entièrement entouré d'eau, le pingouin est retiré de la banquise. "
    				+"Le joueur gagne ce bloc de glace et les poissons associés.",
    		"Lorsqu'un joueur n'a plus aucun pingouins sur la banquise, il passe son tour jusqu'à la fin de la partie. Quand tous les joueurs sont dans ce cas, la partie s'arrête.\n\n"+
    				"Le joueur qui a attrapé le plus de poissons gagne la partie. En cas d’égalité, le joueur qui a le plus de bloc de glace gagne la partie. "
    				+"En cas de nouvelle égalité, les ex-aequo gagnent ensemble la partie."
    };

    
}