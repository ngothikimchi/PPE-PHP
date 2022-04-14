package Vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import Controleur.Tableau;
import Controleur.TypeEveAdulte;
import Modele.modele;

public class PanelTypeEveAdulte extends PanelDeBase implements ActionListener{
	private JPanel panelForm = new JPanel();
	private JPanel panelTable = new JPanel();
	private JButton btEnregistrer= new JButton("Enregistrer");
	private JButton btAnnuler= new JButton("Annuler");
	private JTextField txtCodeType= new JTextField();
	private JTextField txtNomtype= new JTextField();
	//zone de recherche - par requeque like
	private JTextField txtMot = new JTextField();
	private JButton btRechercher= new JButton("Rechercher");
	
	private static Tableau unTableau =null;
	private JTable uneTable=null;
	
	public PanelTypeEveAdulte()
	{
		super(new Color(163, 222, 142));
		this.panelForm.setLayout(new GridLayout(3,2));
		this.panelForm.setBackground(Color.white);	
		
		this.panelForm.add(new JLabel(" Code type"));
		this.panelForm.add(this.txtCodeType);
		
		this.panelForm.add(new JLabel(" Nom"));
		this.panelForm.add(this.txtNomtype);

		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btEnregistrer);
		
		this.panelForm.setBounds(20,20,300,100);
		this.add(this.panelForm);
		
		//construction du panel Table
		this.panelTable.setBounds(360,20,440,320);
		this.panelTable.setBackground(new Color(163, 222, 142));
		this.panelTable.setLayout(null);
		
		//nom de colonne : entete
		
		String entetes[] = {"CodeTypeE","Nom"};
		Object donnees [][] = this.getLesDonnees("");
		unTableau = new Tableau (entetes, donnees);
		
		this.uneTable= new JTable(unTableau);
		JScrollPane uneScroll = new JScrollPane(this.uneTable);
		uneScroll.setBounds(0,60,440,200);
		this.panelTable.add(uneScroll);
		
		//placement de la zone de recherche
		this.txtMot.setBounds(100,10,120,25);
		this.panelTable.add(this.txtMot);
		this.btRechercher.setBounds(240,10,120,25);
		this.panelTable.add(this.btRechercher);
		this.add(this.panelTable);		
		
		this.btEnregistrer.addActionListener(this);
		this.btAnnuler.addActionListener(this);
		this.btRechercher.addActionListener(this);
			
		this.uneTable.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent e) {
				int nbclic = e.getClickCount();
				if(nbclic==2)
				{
					int numLigne=uneTable.getSelectedRow();
					int retour =JOptionPane.showConfirmDialog(null, "Vous voulez suppimer cet type de evenement ?",
							"Suprresion type d'événement", JOptionPane.YES_NO_OPTION);
					if (retour == 0)
					{
						//suppresion du citoyen
						String codeTypeE = unTableau.getValueAt(numLigne, 0).toString();
						modele.deletetypeEveAdulte(codeTypeE);
						
						//actualiser l'affichage
						unTableau.supprimerLigne(numLigne);
					}
					
				}
				else if(nbclic==1)
				{
					int numLigne1=uneTable.getSelectedRow();
					
					String codeTypeE = uneTable.getValueAt(numLigne1,0).toString();
					txtCodeType.setText(codeTypeE);
					
					String nomTypeE = uneTable.getValueAt(numLigne1,1).toString();
					txtNomtype.setText(nomTypeE);

					btEnregistrer.setText("Modifier");
					
					
				}
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
	
		});
	
		
		
		
	}

	private Object[][] getLesDonnees(String mot) {
		ArrayList<TypeEveAdulte> lesTypeEveAdultes= null;
		if(mot.equals(""))
			{
			lesTypeEveAdultes=modele.selectAllTypeEveAdultes();
			}
		else
		{
			lesTypeEveAdultes=modele.selectLikeTypeEveAdulte(mot);
		}
		//create matrice empty 
		//size() nb ligne
		Object [][] matrice = new Object[lesTypeEveAdultes.size()][2];
		int i=0;
		
		for(TypeEveAdulte uneTypeEveAdulte : lesTypeEveAdultes)
		{
			matrice[i][0]=uneTypeEveAdulte.getCodeTypeE();
			matrice[i][1]=uneTypeEveAdulte.getNomTypeE();
			
			i++;
		}
		return matrice;
	}
	
	public void viderChamps()
	{
		this.txtCodeType.setText("");
		this.txtNomtype.setText("");
	}
	
	public TypeEveAdulte saisirtypeEveAdulte()
	{
		TypeEveAdulte uneTypeEveAdulte=null;
		String errorMessage = "";
		String codeTypeE =this.txtCodeType.getText();
		String nomTypeE = this.txtNomtype.getText();
		

		//check saisi ****************************
		//check code type
		if(codeTypeE.equals(""))
		{
			this.txtCodeType.setBackground(Color.red);
			errorMessage += "Code type est requis \n";
		}
		else 
		{
			this.txtCodeType.setBackground(Color.white);
		}
		
		//check nom
		if(nomTypeE.equals(""))
		{
			this.txtNomtype.setBackground(Color.red);
			errorMessage += "Nom de type est requis \n";
		}
		else 
		{
			this.txtNomtype.setBackground(Color.white);
		}
		if(errorMessage != "")
		{
			JOptionPane.showMessageDialog(this, "Erreur de saisir: \n" + errorMessage);
			
			return null;
		}
		
		//*************************************************

		//check type de boolean
		if(!codeTypeE.equals("") && !nomTypeE.equals("") )				
		{
			
			uneTypeEveAdulte = new TypeEveAdulte(codeTypeE, nomTypeE);
		}
		else 
		{
			return null;
		}
		return uneTypeEveAdulte;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btAnnuler)
		{
			this.viderChamps();	
		}
		else if (e.getSource()==this.btEnregistrer && e.getActionCommand().equals("Enregistrer"))
		{
			TypeEveAdulte uneTypeEveAdulte = this.saisirtypeEveAdulte();
			//check if object is null ne rien faire
			if(uneTypeEveAdulte == null)
				return;
			
			modele.insertTypeEveAdulte(uneTypeEveAdulte);
			
			

			//mettre a jour l'affichage
			Object ligne[]= {uneTypeEveAdulte.getCodeTypeE(),uneTypeEveAdulte.getNomTypeE()};
												
			unTableau.ajouterLigne(ligne);

			JOptionPane.showMessageDialog(this, "Insertion Reussie");
			this.viderChamps();				
		}
		
	else if (e.getSource()==this.btEnregistrer && e.getActionCommand().equals("Modifier"))
	{
	
		TypeEveAdulte uneTypeEveAdulte = this.saisirtypeEveAdulte();
		if(uneTypeEveAdulte == null)
			return;
		
		JOptionPane.showMessageDialog(this, "Modification effectuee");
		int numLigne =this.uneTable.getSelectedRow();
		
		String codeTypeE = unTableau.getValueAt(numLigne, 0).toString();
		uneTypeEveAdulte.setCodeTypeE(codeTypeE);
		
		Object ligne[]= 
		{
			uneTypeEveAdulte.getCodeTypeE(),uneTypeEveAdulte.getNomTypeE()
		};
		
		unTableau.modifierLigne(numLigne,ligne);
		modele.updateTypeEveAdulte(uneTypeEveAdulte);
		this.btEnregistrer.setText("Enregistrer");
		this.viderChamps();
	}
	else if (e.getSource()==this.btRechercher)	
		{
			String mot =this.txtMot.getText();
			
			Object matrice [][] =this.getLesDonnees(mot);
			unTableau.setDonnees(matrice);
		}	
	
		
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
