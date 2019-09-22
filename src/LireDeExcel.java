

import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.CellType;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class LireDeExcel {

	public int[][] axeX;
	public int[][] axeY;
	public String T[];
	int NL[];//=10;
	// Sheet sheet[];
	GrapheFenetre FEN;

	private String inputFile;

	public void setInputFile(String inputFile) {
		this.inputFile = inputFile;
	}

	public void read() throws IOException  {
		File inputWorkbook = new File(inputFile);
		Workbook w;
		try {
			w = Workbook.getWorkbook(inputWorkbook);
			int totalSheet = w.getNumberOfSheets();
			//System.out.println("nombre de pages : " + totalSheet);

			NL = new int[totalSheet];
			T  = new String[totalSheet];


			for(int i=0;i<totalSheet;i++)
			{
				Sheet sheet=w.getSheet(i);
				T[i] = sheet.getName();
				NL[i]=sheet.getRows();
			}

			FEN = new GrapheFenetre(NL,totalSheet);
			FEN.N=NL;
			FEN.axeX1=new int[1000][totalSheet];
			FEN.axeY1=new int[1000][totalSheet];

			for (int k=0;k<totalSheet;k++)
			{
				Sheet s= w.getSheet(k);

				for (int i = 0; i < s.getRows(); i++) 
				{

					NumberCell cell0=(NumberCell) s.getCell(0,i);
					FEN.axeX1[i][k]= (int) Float.parseFloat(cell0.getContents());

					NumberCell cell1=(NumberCell) s.getCell(1,i);
					FEN.axeY1[i][k]= (int) Float.parseFloat(cell1.getContents());


				}
			}


		}


		catch (BiffException e) {
			e.printStackTrace();
		}


		FEN.T=T;
		FEN.Fonct();
		FEN.setVisible(true);

	}


}
