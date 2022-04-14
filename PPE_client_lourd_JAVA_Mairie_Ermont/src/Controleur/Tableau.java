package Controleur;

import javax.swing.table.AbstractTableModel;
// Abstracttablemodel ux/ui interface
public class Tableau extends AbstractTableModel{
	private String entetes[];
	private Object [][] donnees;
	
	
	public Tableau(String[] entetes, Object[][] donnees) {
		super();
		this.entetes = entetes;
		this.donnees = donnees;
	}

	@Override
	public int getRowCount() {
		
		return this.donnees.length;
	}

	@Override
	public int getColumnCount() {
		
		return this.entetes.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
	
		return this.donnees[rowIndex][columnIndex];
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return this.entetes[column];
	}
	public void ajouterLigne (Object ligne[])
	{
		Object matrice [][] = new Object [this.getRowCount()+1][this.getColumnCount()];
		for (int i=0; i<this.getRowCount();i++)
		{
			matrice[i]=this.donnees[i];
			
		}
		//ajout de la ligne a la fin du tableau matrice
		matrice[this.getRowCount()]=ligne;
		//ecrasement de la donnees
		this.donnees=matrice;
		//actualisation de l'affichage
		this.fireTableDataChanged();
	}

	public void supprimerLigne(int numLigne) {
		Object matrice [][] = new Object [this.getRowCount()-1][this.getColumnCount()];
		int j=0;
		for (int i=0; i<this.getRowCount();i++)
		{
			
			if(numLigne != i)
			{
				matrice[j]=this.donnees[i];
				j++;
			}
			
		}
		
		
		this.donnees=matrice;
		this.fireTableDataChanged();
		
	}
	public void modifierLigne(int numLigne, Object[] ligne)
	{
		Object matrice [][] = new Object [this.getRowCount()][this.getColumnCount()];
		
		for (int i=0; i<this.getRowCount();i++)
		{
			
			if(numLigne == i)
			{
				matrice[i]=ligne;
			}
			else {
				matrice[i]=this.donnees[i];
			}		
		}
			
		this.donnees=matrice;
		this.fireTableDataChanged();
	}

	public void setDonnees(Object[][] matrice) {
		this.donnees=matrice;
		this.fireTableDataChanged();
		
	}
	
}



















