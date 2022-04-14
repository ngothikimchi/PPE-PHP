package Vue;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import Controleur.EU;
import Modele.modele;
import Controleur.EU;
import Controleur.Tableau;
import Modele.modele;

public class PanelDeBord extends PanelDeBase
{
	private JPanel unPanel = new JPanel();
	public PanelDeBord() 
	{
		super(new Color(238, 237, 222));
		super.setBounds(20,70,950,200);
		//this.unPanel.setBackground(Color.gray);
		this.unPanel.setBounds(50,50,850,100);
		this.unPanel.setLayout(null);
		
		String entetes [] = {"Id_user","Id employe","Nom","Prénom","Email","Service"};
		Object matrice [][] = this.getDonnees();
		Tableau unTableau = new Tableau (entetes,matrice);
		JTable uneTable = new JTable(unTableau);
		//mettre en center
		DefaultTableCellRenderer centerRenderer =new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		for (int i=0;i<uneTable.getColumnCount();i++)
		{
			uneTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}

		JScrollPane uneScroll = new JScrollPane(uneTable);
		uneScroll.setBounds(0,0,850,100);
		this.unPanel.add(uneScroll);
	
		this.add(this.unPanel);
	}
	private Object[][] getDonnees() {
		ArrayList<EU> lesEUs = modele.selectAllEu();
		Object matrice [][] = new Object[lesEUs.size()][6];
		
		int i=0;
		for(EU unEU : lesEUs)
		{
			matrice[i][0] =unEU.getIduser();
			matrice[i][1] =unEU.getIdemploye();
			matrice[i][2] =unEU.getNom();
			matrice[i][3] =unEU.getPrenom();
			matrice[i][4] =unEU.getEmail();
			matrice[i][5] =unEU.getService();
			i++;
			
		}
		
		return matrice;
	}
}
