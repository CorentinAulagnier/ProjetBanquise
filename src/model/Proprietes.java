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
    
    
    public static final String IMAGE_SONON_PATH = "/ressources/decor/r_music-on.png";
    public static final String IMAGE_SONOFF_PATH = "/ressources/decor/r_son-off.png";
    public static final String IMAGE_MUSIQUEON_PATH = "/ressources/decor/r_music-on.png";
    public static final String IMAGE_MUSIQUEOFF_PATH = "/ressources/decor/r_music-off.png";
    
    public static final String IMAGE_JOUEUR_PATH = "/ressources/joueurs/"; /* + MODEJEU +COULEUR.png*/
    
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
    		"Préparation de la partie",
    		"Tour de jeu (1)",
    		"Tour de jeu (2)",
    		"Fin de la partie"
    };
    public static final String[] reglesCorps = {
    		"Sur la banquise, les pingouins partent à la chasse au poisson en sautant d’un bloc de glace à l’autre.\n\n"
    				+"Pour cela, les joueurs essaient d’attraper un maximum de poissons, tout en veillant à ne pas être bloqués ou isolés sur la banquise.",
    		"Placement de la banquise :\n  - La banquise est générée aléatoire- ment selon 8 lignes de blocs de glaces, (alternativement 7 et 8).\n"
    				+"  - La répartition du nombre de poissons sur les blocs est aléatoire.\n "
    				+"Placement des pinguouins :\n Chaque joueur place tour à tour un de ses pingouins sur un bloc de glace contenant un et un seul poisson. "
    				+"Il ne peut y avoir qu’un seul pingouin par bloc de glace. On continue jusqu’à ce que tous les joueurs aient placé tous leurs pingouins",
    		"Durant son tour, un joueur peut déplacer un seul de ses pinguouins :\n   "
    				+"- en ligne droite (interdiction de changer de direction en chemin).\n   "
    				+"- dans une des 6 directions qui entoure son bloc de glace.\n   "
    				+"- d’autant de cases que le joueur le souhaite.\n   "
    				+"- sans franchir d’obstacle ",
    		"Un obstacle est soit une extrémité de la banquise, soit un trou dans la banquise, soit un bloc de glace occupé par un autre pingouin.\n"
    				+"A la fin du tour, le joueur gagne le bloc de glace dont son pinguouin est parti et le nombre de poissons associés. Si l'un de ses pinguouins est bloqué, il est retiré du plateau. Le joueur gagne le bloc de glace et les poissons associés.",
    		"Lorsqu'un joueur n'a plus de pinguouins, il s'arrête. Lorsqu'aucun joeur ne peut jouer, la partie s'arrête.\n"+
    				"Le joueur qui a attrapé le plus de poissons gagne la partie. En cas d’égalité, le joueur qui a le plus de bloc de glace gagne la partie. "
    				+"En cas de nouvelle égalité, les ex-aequo gagnent ensemble la partie."
    };

    
}