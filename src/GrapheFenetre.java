import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.PrintJob;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


class GrapheFenetre extends JFrame implements ActionListener 
{

	public int N[];
	public int C;
	public String T[];

	private GraphePanel p;
	JPanel pan;

	public int[][] axeX1;
	public int[][] axeY1;

	protected Font comics30 = new Font("Comics Sans MS", Font.BOLD, 30);
	protected Font comics18 = new Font("Comics Sans MS", Font.BOLD, 18);
	protected Font comics40 = new Font("Comics Sans MS", Font.BOLD, 70);

	protected Font arial  = new Font("Arial", Font.BOLD, 12);
	protected Font arial10  = new Font("Arial", Font.BOLD, 10);
	protected Font arial14  = new Font("Arial", Font.BOLD, 14);
	protected Font dialog   = new Font("Dialog", Font.BOLD + Font.ITALIC, 15);


	private JButton imprimer = new JButton("imprimer");
	private JButton Sauvegarder  = new JButton("Sauvegarder une image");


	public GrapheFenetre(int N[], int C)
	{

		// this.N= new int[C];
		this.C=C;

		setTitle("le graphe");
		setSize(958, 712);;
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	void Fonct(){
		//Container c=getContentPane();

		pan = new JPanel();
		pan.setBackground(Color.gray);
		pan.setPreferredSize(new Dimension(958,40));
		pan.add(imprimer);
		imprimer.setPreferredSize(new Dimension(100,33));
		imprimer.setFont(dialog);
		imprimer.addActionListener(this);


		pan.add(Sauvegarder);
		Sauvegarder.setPreferredSize(new Dimension(200,33));
		Sauvegarder.setFont(dialog);
		Sauvegarder.addActionListener(this);

		add(pan,BorderLayout.SOUTH);
		System.out.println("CC => "+C);

		p=new GraphePanel(N,C);
		add(p,BorderLayout.CENTER);
		//System.out.println("axeX1=> "+axeX1[2][0]);

		p.StringTitre=T;
		p.N=N;
		p.axeX2=axeX1;
		p.axeY2=axeY1;
		//c.add(p);

	}
	private void makePanelImage(Component panel)
	{
		Dimension size = panel.getSize();
		BufferedImage image = new BufferedImage(
				size.width, size.height 
				, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = image.createGraphics();
		panel.paint(g2);
		try
		{
			ImageIO.write(image, "png", new File("imageYes.png"));
			System.out.println("image sauvegardée.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}



	public void actionPerformed(ActionEvent e) {
		if((JButton)e.getSource() == Sauvegarder)
		{
			makePanelImage(this);

		}

		if((JButton)e.getSource() == imprimer)
		{
			PrinterJob printJob = PrinterJob.getPrinterJob();
			printJob.setPrintable(p);
			//p.print();
			PageFormat pf = printJob.pageDialog(
					printJob.defaultPage());
			if(printJob.printDialog()){
				try { printJob.print(); } catch (Exception ex) { }
			}

		}

	}

}