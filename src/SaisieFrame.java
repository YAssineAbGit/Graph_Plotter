import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JTextField;


class SaisieFrame extends JFrame
{	
	public int Longueur[];
	public int NombreCourbe;
	public String TitreCourbe[];
	public int[][] axeX;
	public int[][] axeY;
	int hh=0;
	int hh2=0;
	int j;

	private ChoixFrame ChFen;

	private JLabel L     = new JLabel("Entrer les coordonnées comme indiqué :");
	private JLabel L0   = new JLabel("Donner un titre à votre Courbe: ");
	private JLabel L1    = new JLabel("le nombre de Courbes:  ");
	private JLabel L2    = new JLabel("Exemple : x1,  x2,  x3, . . . . . . . . . . xN");
	private JLabel L3    = new JLabel("Exemple : y1,  y2,  y3, . . . . . . . . . . yN");

	private JLabel T  = new JLabel("Titre   :");
	private JLabel N  = new JLabel("n   : ");
	private JLabel X  = new JLabel("x   : ");
	private JLabel Y  = new JLabel("y   : ");

	private JTextField TF = new JTextField("Courbe 1");
	private JTextField Tn = new JTextField("1");
	//	private JTextField T1 = new JTextField("0,1,2,3,4,5,6,7,8,9,10,-1,-2,-3,-4,-5,-6,-7,-8,-9,-10");
	//	private JTextField T2 = new JTextField("0,1,4,9,16,25,36,49,64,81,100,1,4,9,16,25,36,49,64,81,100");

	private JTextField T1 = new JTextField("0,1,2,3,4,5,6,7,8,9,12,-1,-2,-3,-4,-5,-6,-7,-8,-9,-10");
	private JTextField T2 = new JTextField("0,-1,-8,-27,-64,-125,-216,-343,-512,-729,-1000,1,8,27,64,125,216,343,512,729,1000");

	private JButton B  = new JButton("OK");
	private JButton B2 = new JButton("Précedant >");

	public void initial(int C)
	{	
		axeX= new int[1000][C];
		axeY= new int[1000][C];
	}

	public void change(int C)
	{
		this.Longueur=new int[C];
		this.TitreCourbe= new String[C];
		this.NombreCourbe=C;
	}

	public SaisieFrame()
	{

		/////////////////////////////////////////////////////////////////////////////////////////

		Font police = new Font("Arial", Font.BOLD, 14);
		T1.setFont(police);
		T2.setFont(police);
		Tn.setFont(police);
		TF.setFont(police);
		setLayout(new FlowLayout());

		setTitle("La Saisie");
		setSize(600,400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c=getContentPane();


		setLayout(null);
		//L.setLocation(100,100);

		L.setBounds(170,20,300,15);

		L0.setBounds(120,35+30+50,400,26);
		T.setBounds(61,88+50,50,26);
		TF.setBounds(120,90+50,200,26);
		TF.setBackground(Color.white);

		L1.setBounds(120,5+50,400,26);
		N.setBounds(80,28+50,50,26);
		Tn.setBounds(120,30+50,200,26);
		Tn.setBackground(Color.white);

		L2.setBounds(120,95 +30+50,400,26);
		X.setBounds(80,118+30+50,50,26);
		T1.setBounds(120,120+30+50,400,26);
		T1.setBackground(Color.white);

		L3.setBounds(120,155+30+50,400,26);
		Y.setBounds(80,178+30+50,50,26);
		T2.setBounds(120,180+30+50,400,26);
		T2.setBackground(Color.white);

		B.setBounds(120,250+60,200,30);
		B2.setBounds(330,250+60,120,30);

		B.addActionListener(new StateListener2());
		B2.addActionListener(new StateListener2());

		T1.setForeground(Color.BLUE);
		T2.setForeground(Color.BLUE);
		Tn.setForeground(Color.BLUE);
		TF.setForeground(Color.BLUE);

		c.add(L);
		c.add(T);
		c.add(L0);
		c.add(TF);
		c.add(N);
		c.add(L1);
		c.add(Tn);
		c.add(L2);
		c.add(X);
		c.add(T1);
		c.add(L3);
		c.add(Y);
		c.add(T2);
		c.add(B);
		c.add(B2);
		c.setBackground(Color.white);
	}




	class StateListener2 implements ActionListener{

		public void actionPerformed(ActionEvent e) {

			if( (JButton)e.getSource() == B2)
			{
				ChFen = new ChoixFrame();
				ChFen.setVisible(true);
				dispose();
			}

			if(hh==0) { int j=0; hh=1; }

			if(hh2==0) 
			{ 
				String NombreCourbeString = new String(Tn.getText()); 
				try {
					NombreCourbe=Integer.parseInt(NombreCourbeString);
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null,
							"Vous n'avez pas bien fait la saisi... voir Aide! \n" +
									"en cliquant sur Précedant... " +
									"Merci...",
									"Erreur", JOptionPane.ERROR_MESSAGE);
				}
				initial(NombreCourbe);
				change(NombreCourbe);
				hh2=1;
			}
			String st;
			String st2;
			TitreCourbe[j] =TF.getText();
			String Nombre2 = new String(Tn.getText()); 
			int k=Integer.parseInt(Nombre2);

			// int k = Nombre22;
			// System.out.println(" kkk --> "+k);
			if( (JButton)e.getSource() == B && k !=0)
			{
				if(k>1)
				{
					st2="Courbe "+(j+2);
					TF.setText(st2);
				}

				st=""+(k-1);
				Tn.setText(st);
				Tn.setEditable(false);

				String text1 = new String( T1.getText());
				String text2 = new String( T2.getText());
				String[] Tab1 = text1.split(",");
				String[] Tab2 = text2.split(",");


				Longueur[j]=Tab1.length;

				//System.out.println("Longueur SaisieFrame "+Longueur[0]);

				for(int i=0;i<Longueur[j];i++)
				{
					try {
						axeX[i][j]=(int)Float.parseFloat(Tab1[i]);
						axeY[i][j]=(int)Float.parseFloat(Tab2[i]);
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null,
								"Vous n'avez pas bien fait la saisi... voir Aide! \n" +
										"en cliquant sur Précedant... " +
										"Merci...",
										"Erreur", JOptionPane.ERROR_MESSAGE);
					} 

				}
				j++; 

				B.setText("Saisir un autre Courbe");

				if(k==2 )
					B.setText("Voir votre Graphe");

				if(k==1){


					GrapheFenetre fen = new GrapheFenetre(Longueur,NombreCourbe);

					fen.N=Longueur;
					fen.T=TitreCourbe;
					//System.out.println("Longueur SaisieFrame "+Longueur[0]);
					//System.out.println("N SaisieFrame "+fen.N[0]);
					fen.axeX1=axeX; 
					fen.axeY1=axeY;
					fen.Fonct();

					//System.out.println("fen.axeX1=> "+fen.axeX1[2][0]);

					fen.setVisible(true);
					dispose();
				}

			}

		}
	}

}

