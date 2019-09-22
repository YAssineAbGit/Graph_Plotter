import java.awt.print.PrinterJob;

import javax.swing.UIManager;




public class MainTraceur {


	public static void main(String[] args) {

		try{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}
		catch(Exception e){

		}
		//GrapheFenetre fen= new GrapheFenetre(11,1);
		ChoixFrame fen2=new ChoixFrame();
		//SaisieFrame fen3 = new SaisieFrame();
		//GraphePanel fen4 = new GraphePanel(2,5);
		fen2.setVisible(true);




	}

}
