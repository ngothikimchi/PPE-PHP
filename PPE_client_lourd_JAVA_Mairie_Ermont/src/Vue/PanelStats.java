package Vue;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import Controleur.Tableau;
import Modele.modele;

public class PanelStats extends PanelDeBase
{
	private JPanel unPanel = new JPanel();
	public PanelStats()
	{
		super(new Color(238, 237, 222));
		super.setBounds(20,70,950,200);
		this.unPanel.setBackground(new Color(238, 237, 222));
		this.unPanel.setBounds(50,50,850,100);
		this.unPanel.setLayout(new GridLayout(2,1));
		int nbCitoyens = modele.countCitoyens();
		int nbEmployee = modele.countEmployees();
		int nbAssociations = modele.countAssociations();
		int nbTypedeService = modele.countTypeDeService();
		int nbDemandeValide = modele.countDemandeValide();
		int nbDemandeRefuse = modele.countDemandeRefuse();
		int nbEvenements = modele.countEvenements();
		String entetes [] = {"Nb de citoyens","Nb employes","Nb de associations",
				"Nb de type service","Nb de demande validé","Nb de demande refusée",	
				"Nb de événement"	
		};
		Object matrice [][] = {{nbCitoyens,nbEmployee,nbAssociations,nbTypedeService,nbDemandeValide,nbDemandeRefuse,nbEvenements}};
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
		uneScroll.setBounds(50,50,850,100);
		this.unPanel.add(uneScroll);
		
		
		
		this.add(this.unPanel);
	}
}
