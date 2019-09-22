import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.html.HTMLDocument.Iterator;

class GraphePanel extends JPanel implements Printable
{
	public int N[];
	public int C;
	public int[][] axeX2;
	public int[][] axeY2;
	public float val;

	public String StringTitre[];
	public JLabel LabelTitre[];

	private JButton imprimer = new JButton("imprimer");
	protected Font dialog   = new Font("Dialog", Font.BOLD + Font.ITALIC, 15);

	JPanel panMEMU = new JPanel();

	SaisieFrame fen3 = new SaisieFrame();

	public void Titrage(JLabel LabelTitre[], String StringTitre[], int indice)
	{
		LabelTitre[indice] = new JLabel(StringTitre[indice]);
		LabelTitre[indice].setBounds(750,100+(indice*25)+30-20,200,40+30-20);
		this.add(LabelTitre[indice]);
	}

	public void LegendeCouleur(Graphics g, Color[] c,int indice)
	{
		if(indice>10)
			g.setColor(c[10]);

		g.setColor(c[indice]);
		g.fillRect(720,118+(indice*25)+30+15-30,20,5);
	}

	public GraphePanel(int N[],int C)
	{
		//this.N=new int[C];
		this.C=C;
		LabelTitre = new JLabel[C];
		setLayout(null);

		Font police  = new Font("Arial", Font.ITALIC , 25);
		Font policeC = new Font("Arial", Font.ITALIC , 12);

		JTextArea Area = new JTextArea("Projet réalisé par ABBAZI YASSINE \n"+
				"Master SécuRISE \n" +
				"Pr. M. JEDRA");

		Area.setEditable(false);
		Area.setBounds(705,572+30-60,250,50+30);
		Area.setFont(policeC);

		JLabel legende = new JLabel("Légende");
		legende.setBounds(750,50+30-40,200,40+30);
		legende.setFont(police);


		this.add(Area);
		this.add(legende);

	}

	public void changerLaCouleur(Graphics g,Color[] c,int indice)
	{
		if(indice>10)
			g.setColor(c[10]);
		g.setColor(c[indice]);

	}

	///////////////////////////////////////////////////////////////////////////////////////////////////
	////////                           Fonction d'ajustement                               ///////////
	/////////////////////////////////////////////////////////////////////////////////////////////////

	public char trier(int[][] axeX22, int[][] axeY22,int N[], int n)

	{

		int tamponX;
		int tamponY;
		int maxX;
		int maxY;

		for(int i=0;i<N[n]-1;i++)
		{
			for (int j=i+1;j<N[n];j++)
			{
				if(axeX22[i][n] > axeX22[j][n])
				{
					tamponX  = axeX22[i][n];
					tamponY  = axeY22[i][n];

					axeX22[i][n] = axeX22[j][n];
					axeY22[i][n] = axeY22[j][n];

					axeX22[j][n] = tamponX;
					axeY22[j][n] = tamponY;
				}
			}
		}
		maxX=axeX22[0][n];
		maxY=axeY22[0][n];
		for(int i=1;i<N[n];i++)
		{
			if(maxX<axeX22[i][n])
				maxX=axeX22[i][n];
			if(maxY<axeY22[i][n])
				maxY=axeY22[i][n];
		}
		//	System.out.println("xxxxxx "+maxX);
		//	System.out.println("yyyyyy "+maxY);
		if (maxX>maxY) return 'x';
		else return 'y';


	}
	///////////////////////////////////////////////////////////////////////////////////////////////////
	//////// 		                             l'echelle                               ///////////
	/////////////////////////////////////////////////////////////////////////////////////////////////

	public float echelleX(int[][] axeX22,int N[],int n)
	{
		int j=n;
		int k=(int) Math.abs(axeX22[0][j]);

		for(int i=1;i<N[n];i++)
		{
			if(k<Math.abs(axeX22[i][j]))
				k=(int) Math.abs(axeX22[i][j]);

		}
		//System.out.println(" k à l'interieur de echelleX  " +k);

		if(k<300)
		{
			int i=1;
			while(i*k <300)
				i++;
			return i;
		}


		else
		{
			int i=1;
			while(k/i >300)
				i++;
			float m= i;
			m=1/m;
			//System.out.println(" 1/i à l'interieur de echelleX  " +m);
			return m;
		}

	}


	public void paintComponent(Graphics G)
	{
		super.paintComponent(G);

		Graphics2D g = (Graphics2D) G;
		setBackground(Color.white);

		g.setStroke(new BasicStroke(2,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
		/// le cadre du graphe ///////
		g.drawLine(50, 10+30-20, getWidth()-249, 10+30-20);                          //cadre X
		g.drawLine(50, getHeight()-10-30+30-20, getWidth()-249, getHeight()-10-30+30-20);  //cadre X

		g.drawLine(50, 10+30-20, 50 , getHeight()-10-30+30-20);                         //cadre Y
		g.drawLine(getWidth()-249, 10+30-20, getWidth()-249, getHeight()-10-30+30-20);  //cadre Y

		/// le cadre de la légende //
		g.drawLine(getWidth()-249+10, 100+30-20, getWidth()-50, 100+30-20);                          //cadre X
		g.drawLine(getWidth()-249+10, getHeight()-110+30-20, getWidth()-50, getHeight()-110+30-20);  //cadre X

		g.drawLine(getWidth()-249+10, 100+30-20, getWidth()-249+10 , getHeight()-110+30-20);                         //cadre Y
		g.drawLine(getWidth()-50, 100+30-20, getWidth()-50, getHeight()-110+30-20);  //cadre Y


		/// le repère  ///////////////
		g.drawLine(65, getHeight()/2-30+15+30-20, 675, getHeight()/2-30+15+30-20);//axe des X
		g.drawString(">",672,getHeight()/2-30+20-1+30-20);
		g.drawString("<",65,getHeight()/2-30+20-1+30-20);
		g.drawLine(getWidth()/2-149+50, 30-5+30-20, getWidth()/2-149+50, getHeight()-23-30+30-20);  //axe des Y
		g.drawString("A",getWidth()/2-149+50-3,30-5+5+30-20);
		g.drawString("V",getWidth()/2-149+50-3,getHeight()-23-30+5+30-20);

		Font policeC = new Font("Arial", Font.ITALIC , 12);
		JLabel lx = new JLabel("x");
		JLabel ly = new JLabel("y");
		lx.setFont(policeC);
		ly.setFont(policeC);

		lx.setBounds(675, getHeight()/2-30+15+4+30-40,15,15+30);
		ly.setBounds(getWidth()/2-149+50+10, 30-10+30-40,15,15+30);

		this.add(lx);
		this.add(ly);
		/// les flèches /////////////

		//  g.drawOval(400, 400, 100, 100);

		for (int i=1;i<12;i++)
		{
			g.drawString("+",19+50+(i)*50, getHeight()-317-30-1+30);
			g.drawString("-",getWidth()/2-150+50, 26 + (i)*50+30-40 );
		}	

		for(int i=0;i<6;i++)
		{
			g.drawString(String.valueOf(i), 249+125+(i)*50, getHeight()-317-30+10+30);
			if(i<5)
				g.drawString(String.valueOf(i+1), 249+125+2, getHeight()-317-30-(i+1)*50+30);
		}

		for(int i=0;i<5;i++)
		{
			g.drawString(String.valueOf(i+1), 249+125+2, getHeight()-317-30+(i+1)*50+30);
			g.drawString(String.valueOf(-(i+1)), 249+125-9+(-(i+1))*50, getHeight()-317-30+10+30);
		}

		int x[][]= new int[1000][C];
		int y[][]= new int[1000][C];

		Color[] Couleur = new Color[11];
		Couleur[0] =  Color.blue;
		Couleur[1] =  Color.green;
		Couleur[2] =  Color.yellow;
		Couleur[3] =  Color.magenta;
		Couleur[4] =  Color.pink;
		Couleur[5] =  Color.orange;
		Couleur[6] =  Color.cyan;		            
		Couleur[7] =  Color.darkGray;
		Couleur[8] =  Color.gray;
		Couleur[9] =  Color.red;
		Couleur[10]=  Color.black;

		for(int j=0;j<C;j++)
		{
			char c=trier(axeX2,axeY2,N,j);
			//System.out.println("ccc  "+c);
			/*	if(c=='x')
				val=echelleX(axeX2,N,j);
			else
				val=echelleX(axeY2,N,j);
			//System.out.println("val  "+val);
			 */
			x[0][j]= (int) (375+echelleX(axeX2,N,j)*axeX2[0][j]);
			y[0][j]= (int) ((getHeight()-325-30+2+30)-echelleX(axeY2,N,j)*axeY2[0][j]);
			//g.drawString("x", x[0][j], y[0][j]);


			for(int i=1;i<N[j];i++)

			{


				LegendeCouleur(g, Couleur ,j);

				changerLaCouleur(g, Couleur,j);

				x[i][j]=(int) (375+echelleX(axeX2,N,j)*axeX2[i][j]);
				y[i][j]=(int) ((getHeight()-325-30+2+30)-echelleX(axeY2,N,j)*axeY2[i][j]);

				g.setStroke(new BasicStroke(2,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
				g.drawLine(x[i-1][j], y[i-1][j],x[i][j], y[i][j]);

				//g.setColor(Color.red);
				//g.fillOval(x[i][j], y[i][j], 5, 5);

				// g.drawString("x", x[i][j], y[i][j]);


			}
			Titrage(LabelTitre, StringTitre, j);

		}

	}



	@Override
	public int print(Graphics G, PageFormat pf, int ind)
			throws PrinterException {
		if (ind >= 1) {
			return Printable.NO_SUCH_PAGE;
		}

		Graphics2D g = (Graphics2D) G;

		g.translate(pf.getImageableX(), pf.getImageableY());
		g.setColor(Color.black);
		//paint(g2);
		paintComponent(g);

		return Printable.PAGE_EXISTS;
	}




}
