import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Locale;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.OverlayLayout;
import javax.swing.UIManager;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;
import jxl.write.WritableWorkbook;



class ChoixFrame extends JFrame
{	
	protected Font comics10 = new Font("Comics Sans MS", Font.BOLD, 10);
	protected Font comics18 = new Font("Comics Sans MS", Font.BOLD, 18);
	protected Font comics20 = new Font("Comics Sans MS", Font.BOLD, 20);
	protected Font arial    = new Font("Arial", Font.BOLD, 12);
	protected Font dialog   = new Font("Dialog", Font.BOLD + Font.ITALIC, 15);

	private JPanel panAcceuil = new JPanel();
	private JPanel panCommencer = new JPanel();
	private JPanel panManuelle = new JPanel();
	private JPanel panExcel = new JPanel();
	private JPanel panCommentUtiliser = new JPanel();

	private JLabel L = new JLabel("Veuillez faire un choix : ");
	private JRadioButton R1 = new JRadioButton("Entrer les valeurs manuellement");
	private JRadioButton R2 = new JRadioButton("Entrer les valeurs par EXCEL ");
	private ButtonGroup bg = new ButtonGroup();
	private JButton B = new JButton("Je confirme");

	// BARRE DE MENU:
	private JMenuBar menuBarre = new JMenuBar();

	private JMenu Commencer = new JMenu("Commencer"),
			aPropos = new JMenu("À propos"),
			Aide = new JMenu("Aide!");

	private JMenuItem CommencerItem1 = new JMenuItem("Oui "),
			CommencerItem2 = new JMenuItem("Quitter"),
			AideItem1 = new JMenuItem("La saisie Manulle "),
			AideItem2 = new JMenuItem("La saisie Excel "),
			aProposItem1 = new JMenuItem("Comment Utiliser!"),
			aProposItem2 = new JMenuItem("    ?");

	private void initialiserMenu(){
		//Menu Commencer:
		Commencer.add(CommencerItem1);
		Commencer.addSeparator();
		Commencer.add(CommencerItem2);
		Commencer.setEnabled(false);
		//Menu Aide:
		Aide.add(AideItem1);
		Aide.add(AideItem2);
		Aide.setEnabled(false);

		//menu à propos
		aPropos.add(aProposItem1);
		aPropos.add(aProposItem2);

		//Ajout des menus dans la barre de menus
		menuBarre.add(Commencer);
		menuBarre.add(Aide);
		menuBarre.add(aPropos);

		//Ajout de la barre de menus sur la fenêtre
		this.setJMenuBar(menuBarre);
	}

	public ChoixFrame()
	{

		setLayout(new FlowLayout());

		setTitle("le choix");
		setSize(500,415);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Container c=getContentPane();

		this.setContentPane(panAcceuil);
		//this.add(panAcceuil);
		this.initialiserMenu();

		//L.setLocation(100,100);
		this.setLayout(null);
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Paneau : panAcceuil:
		JLabel Titre          = new JLabel   ("Traceur de courbes\n");
		Titre.setHorizontalAlignment(JLabel.CENTER);
		Titre.setFont(comics20);
		JTextArea AcceuilArea = new JTextArea("- Avant de Commencer ou bien avant de cliquer sur le menu \"Commencer\" \n"+
				"- Veuillez jeter un coup d'oeil sur le menu \"À propos\" cela va vous guider \n" +
				"- Veuillez voir aussi le menu \"Aide\" il va vous servir dans la 2ème étape \n" +
				"  Merci..");
		ImageIcon icon =new ImageIcon(getClass().getResource( "/imageGrapheABBAZIpetite.jpg" ));
		JLabel grapheImage = new JLabel(icon);
		grapheImage.setBounds(80,25,360,260);
		panAcceuil.add(grapheImage);

		AcceuilArea.setFont(arial);
		AcceuilArea.setEditable(false);
		AcceuilArea.setBackground(Color.white);
		//panAcceuil.setLayout(new BorderLayout());
		panAcceuil.add(Titre);
		//panAcceuil.add(Titre);
		Titre.setBounds(140,5,200,20);
		panAcceuil.add(AcceuilArea);
		//panAcceuil.setLayout(new OverlayLayout(panAcceuil));
		AcceuilArea.setBounds(35,285 ,450, 200);
		panAcceuil.setBackground(Color.white);

		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//  Affichage  paneau commencer :

		CommencerItem1.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				// panCommencer.setVisible(true);
				// panAcceuil.setVisible(false);
				JTextArea CommencerArea = new JTextArea("   Avant de faire un choix \n"+
						"- Veuillez voir le menu \"Aide\" il va vous servir dans cette étape \n"+
						"- Après avoir vu comment procéder, vous faîte un choix et vous\n" +
						"  confirmez avec le bouton \"je confirme\" \n"
						);
				CommencerArea.setFont(arial);
				CommencerArea.setEditable(false);
				CommencerArea.setBackground(Color.white);

				Aide.setEnabled(true);
				aPropos.setEnabled(false);

				panCommentUtiliser.removeAll();
				panCommentUtiliser.add(panCommencer);
				panCommentUtiliser.revalidate();

				panAcceuil.removeAll();
				panAcceuil.add(panCommencer);
				panAcceuil.revalidate();

				panManuelle.removeAll();
				panManuelle.add(panCommencer);
				panManuelle.revalidate();

				panExcel.removeAll();
				panExcel.add(panCommencer);
				panExcel.revalidate();

				setContentPane(panCommencer);
				setLayout(null);
				L.setBounds(160,20+120,200,15);
				R1.setBounds(130,70+120,250,15);
				R1.setBackground(Color.white);
				R2.setBounds(130,90+120,200,15);
				R2.setBackground(Color.white);
				CommencerArea.setBounds(50,35,450, 200);
				B.setBounds(150,280+20,200,30);

				R1.setSelected(false);
				B.addActionListener(new StateListener());

				bg.add(R1);
				bg.add(R2);
				//setContentPane(p);
				//p=new ChoixPanel();
				//this.setContentPane(p);

				//c.add(p);

				//setVisible(true);
				panCommencer.add(L);
				panCommencer.add(R1);
				panCommencer.add(R2);
				panCommencer.add(B);
				panCommencer.add(CommencerArea);
				panCommencer.setBackground(Color.white);
			}

		});
		CommencerItem2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}				
		});
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//  Affichage  paneau A propos :
		aProposItem1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){

				JLabel aproposTitre  = new JLabel("Comment Utiliser ce programme?");
				aproposTitre.setHorizontalAlignment(JLabel.CENTER);
				aproposTitre.setFont(comics18);
				JTextArea AproposArea = new JTextArea(" Ce programme passe  par plusieurs fenêtres chaqu'une à des règles à\n"+
						" respecter. \n"+
						" Après  avoir  cliquer  sur  \"Commencer\"  vous  devez  obligatoirement \n"+
						" passer  par le menu \"Aide\" là  où vous allez  trouver  des  explications\n" +
						" bien détaillées sur l'utilisation des options des étapes suivantes.\n" +
						" Si vous cliquer  sur le menu \"Commencer\" vous passer par une fenêt-\n" +
						" re où vous allez rencontrer deux choix :\n\n" +

                                                  " Le première choix vous permet de saisir le nombre de courbes que vo-\n" +
                                                  " us souhaitez afficher, le titre de chaque courbe, et bien sûr les valeurs\n"+
                                                  " des points x et y de votres courbes.\n\n" +

                                                  " Le deuxième choix vous  permet de saisir le nombre  de courbes, titre, \n" +
                                                  " et les points avec un fichier Excel qu'on a préparé préalablement.\n\n" +

                                                  " A la fin vous confirmer avec le bouton \"je confirme\". \n" +
                                                  " Merci de bien suivre nos explications.\n" +
                                                  " \t\t\tBonne chance..."
						);
				Commencer.setEnabled(true);
				panAcceuil.removeAll();
				panAcceuil.add(panCommentUtiliser);
				panAcceuil.revalidate();
				setContentPane(panCommentUtiliser);
				setLayout(null);
				panCommentUtiliser.add(aproposTitre);
				//panAcceuil.add(Titre);
				aproposTitre.setBounds(90,16,300,20);
				AproposArea.setFont(arial);
				AproposArea.setEditable(false);
				AproposArea.setBackground(Color.white);
				AproposArea.setBounds(45,50,450,400);

				panCommentUtiliser.add(AproposArea);
				panCommentUtiliser.setBackground(Color.white);

			}	    	
		});

		aProposItem2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JOptionPane.showMessageDialog(null,
						"Créateur      : ABBAZI Yassine\n" +
								"Master         : SécuRISE\n" +
								"Professeur : M. JEDRA",
								"Informations", JOptionPane.NO_OPTION);
				panAcceuil.removeAll();
				panAcceuil.revalidate();

			}
		});
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//  Affichage  paneau Aide :

		AideItem1.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				JLabel ManuelleTitre  = new JLabel("Comment la Saisie Manuelle est organisée?");
				//ManuelleTitre.setHorizontalAlignment(JLabel.CENTER);
				ManuelleTitre.setFont(comics18);

				JTextArea ManuelleArea = new JTextArea(    
						"1. Ici vous pouvez entrer le nombre de courbe que vous souhaiter afficher\n" +
								"    dans votre graphe.\n" +
								"2. Ici vous pouvez donner un titre pour chaque courbe, sinon le programme \n" +
								"    donne automatiquement un titre par Defaut comme ceci \"Courbe 5\".\n" +
								"3. Ici entrer les valeurs de l'abscisse x et y en les séparant par virgule \",\"  . \n" +
								"4. Ici le bouton \"OK\" pour saisir les valeurs d' un autre courbe \n" +
								"    le bouton \"Précedant\" pour revenir à la fenêtre précedante. "
						);
				//Aide.setEnabled(true);
				aPropos.setEnabled(false);

				panCommencer.removeAll();
				panCommencer.add(panManuelle);
				panCommencer.revalidate();

				/*panCommentUtiliser.removeAll();
        	panCommentUtiliser.add(panExcel);
        	panCommentUtiliser.revalidate();
				 */
				panAcceuil.removeAll();
				panAcceuil.add(panManuelle);
				panAcceuil.revalidate();

				setContentPane(panManuelle);
				setLayout(null);

				ImageIcon icon =new ImageIcon(getClass().getResource( "/imageSaisie.jpg" ));
				JLabel imageSaisie = new JLabel(icon);
				imageSaisie.setBounds(60,27,360,200);
				panManuelle.add(imageSaisie);

				panManuelle.add( ManuelleTitre);
				panManuelle.add( ManuelleArea);

				ManuelleTitre.setBounds(50,3,400,20);

				ManuelleArea.setFont(arial);
				ManuelleArea.setEditable(false);
				ManuelleArea.setBackground(Color.white);
				ManuelleArea.setBounds(30,235,450,105);

				panManuelle.setBackground(Color.white);
			}	    	
		});
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		AideItem2.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				JLabel ExcelTitre  = new JLabel("Comment la Saisie sous Excel est organisée?");
				ExcelTitre.setHorizontalAlignment(JLabel.CENTER);
				ExcelTitre.setFont(comics18);
				JTextArea ExcelArea = new JTextArea(    "  \n"+
						"- Lorsque vous voulez saisir des valeurs en x\n" +
						"  et y de votre graphe dans un ficher Excel.\n" +
						"- vous entrez les valeurs de x dans la co-\n" +
						"  lonne A comme indiqué ci-contre.\n" +
						"- vous entrez les valeurs de y dans la co-\n" +
						"  lonne B comme indiqué ci-contre.\n"

						);
				JTextArea ExcelArea2 = new JTextArea(    
						"- Si vous  voulez  donnez un titre à  votre  graphe vous  pouvez  le faire en \n" +
								"  renommant  le  titre de  la  feuille  (\"sheet\")  comme indiqué ci-dessous. \n" +
								"- Comme cela le nombre de feuilles crées indique commbien de courbes  \n" +
								"  vous voulez saisir." 
						);
				//Aide.setEnabled(true);
				aPropos.setEnabled(false);

				panCommencer.removeAll();
				panCommencer.add(panExcel);
				panCommencer.revalidate();

				panManuelle.removeAll();
				panManuelle.add(panExcel);
				panManuelle.revalidate();

				panAcceuil.removeAll();
				panAcceuil.add(panExcel);
				panAcceuil.revalidate();

				setContentPane(panExcel);
				setLayout(null);

				ImageIcon icon =new ImageIcon(getClass().getResource( "/imageAetB.jpg" ));
				JLabel AetB = new JLabel(icon);
				AetB.setBounds(210,45,360,150);
				panExcel.add(AetB);

				ImageIcon icon2 =new ImageIcon(getClass().getResource( "/imageSheets.jpg" ));
				JLabel imageSh = new JLabel(icon2);
				imageSh.setBounds(60,170,360,260);
				panExcel.add(imageSh);

				panExcel.add(ExcelTitre);
				panExcel.add(ExcelArea);
				panExcel.add(ExcelArea2);

				ExcelTitre.setBounds(45,10,400,20);


				ExcelArea .setFont(arial);
				ExcelArea .setEditable(false);
				ExcelArea .setBackground(Color.white);
				ExcelArea.setBounds(35,45,450, 150);

				ExcelArea2 .setFont(arial);
				ExcelArea2 .setEditable(false);
				ExcelArea2 .setBackground(Color.white);
				ExcelArea2 .setBounds(35,200,450, 80);

				panExcel.setBackground(Color.white);
			}	    	
		});

		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	}

	class StateListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			SaisieFrame fen2= new SaisieFrame();

			if((JButton)e.getSource() == B && R1.isSelected())       
			{fen2.setVisible(true);
			//setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			dispose();
			}
			else
				fen2.dispose();


			////////////////////////////////////////////////////////////////////////////////////
			//    utilisation: JExel 
			//////////////////////////////////////////////////////////////////////////////////:/


			if((JButton)e.getSource() == B && R2.isSelected())
			{
				javax.swing.filechooser.FileFilter E =  new Filtrage("Fichiers xls",".xls");

				JFileChooser chooser = new JFileChooser(".");

				chooser.setAcceptAllFileFilterUsed(false);
				chooser.addChoosableFileFilter(E);

				chooser.setMultiSelectionEnabled(false);
				chooser.showOpenDialog(null);

				File selection = chooser.getSelectedFile();

				String chemin =  selection.getAbsolutePath();

				LireDeExcel test = new LireDeExcel();
				test.setInputFile(chemin);
				try {
					test.read();
				} catch (IOException e1) {

					e1.printStackTrace();
				}


			}
		}
	}


}