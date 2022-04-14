package Vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import Controleur.Association;
import Controleur.Tableau;
import Controleur.TypeDemande;
import Modele.modele;

public class PanelTypeDemande extends PanelDeBase implements ActionListener
{
	private JPanel panelForm = new JPanel();
	private JPanel panelTable = new JPanel();
	private JButton btEnregistrer= new JButton("Enregistrer");
	private JButton btAnnuler= new JButton("Annuler");
	private JTextField txtNom= new JTextField();
	//private JTextField txtEtrePluriel= new JTextField();
	private JComboBox<String> cbEtrePluriel= new JComboBox<String>();
	
	
	//zone de recherche - par requeque like
	private JTextField txtMot = new JTextField();
	private JButton btRechercher= new JButton("Rechercher");
	
	private static Tableau unTableau =null;
	private JTable uneTable=null;
	
	public PanelTypeDemande()
	{
		super(new Color(195, 232, 199));
		this.panelForm.setLayout(new GridLayout(3,2));
		this.panelForm.setBackground(Color.white);
		
		this.panelForm.add(new JLabel(" Nom"));
		this.panelForm.add(this.txtNom);
		
		this.panelForm.add(new JLabel(" EtrePluriel"));
		this.panelForm.add(this.cbEtrePluriel);
		

		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btEnregistrer);
		
		this.panelForm.setBounds(20,20,300,100);
		this.add(this.panelForm);
		
		//etre pluriel combobox
		this.cbEtrePluriel.addItem("oui");
		this.cbEtrePluriel.addItem("non");
		this.cbEtrePluriel.addItem("");
		
		
		//construction du panel Table
		this.panelTable.setBounds(420,20,400,320);
		this.panelTable.setBackground(new Color(195, 232, 199));
		this.panelTable.setLayout(null);
		
		//nom de colonne : entete
		
		String entetes[] = {"ID Type Demande","Nom","EtrePluriel"};
		Object donnees [][] = this.getLesDonnees("");
		unTableau = new Tableau (entetes, donnees);
		
		this.uneTable= new JTable(unTableau);
		JScrollPane uneScroll = new JScrollPane(this.uneTable);
		uneScroll.setBounds(0,60,380,200);
		this.panelTable.add(uneScroll);
		
		//placement de la zone de recherche
		this.txtMot.setBounds(60,10,120,25);
		this.panelTable.add(this.txtMot);
		this.btRechercher.setBounds(200,10,120,25);
		this.panelTable.add(this.btRechercher);
		this.add(this.panelTable);
		
		this.uneTable.addMouseListener(new MouseListener()
		{

			@Override
			public void mouseClicked(MouseEvent e) {
				int nbclic = e.getClickCount();
				if(nbclic==2)
				{
					int numLigne=uneTable.getSelectedRow();
					int retour =JOptionPane.showConfirmDialog(null, "Vous voulez suppimer cet type de demande ?",
							"Suprresion type de demande", JOptionPane.YES_NO_OPTION);
					if (retour == 0)
					{
						//suppresion du citoyen
						int idTypeD = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
						modele.deletetypeD(idTypeD);
						
						//actualiser l'affichage
						unTableau.supprimerLigne(numLigne);
					}
					
				}
				else if(nbclic==1)
				{
					int numLigne1=uneTable.getSelectedRow();
					
					String nom = uneTable.getValueAt(numLigne1,1).toString();
					txtNom.setText(nom);
					
					String etrePluriel = uneTable.getValueAt(numLigne1,2).toString();
					if(etrePluriel == "true")
					{
						cbEtrePluriel.setSelectedItem("oui");
					}
					else
					{
						cbEtrePluriel.setSelectedItem("non");
					}
					

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
		
		// rendre les boutons ecoutables
		
		this.btEnregistrer.addActionListener(this);
		this.btAnnuler.addActionListener(this);
		this.btRechercher.addActionListener(this);
		
	}

	private Object[][] getLesDonnees(String mot) {
		ArrayList<TypeDemande> lesTypeDs= null;
		if(mot.equals(""))
			{
			lesTypeDs=modele.selectAllTypeDs();
			}
		else
		{
			lesTypeDs=modele.selectLikeTypeD(mot);
		}
		//create matrice empty 
		//size() nb ligne
		Object [][] matrice = new Object[lesTypeDs.size()][4];
		int i=0;
		
		for(TypeDemande uneTypeD : lesTypeDs)
		{
			matrice[i][0]=uneTypeD.getId();
			matrice[i][1]=uneTypeD.getNom();
			matrice[i][2]=uneTypeD.isEtrePlurielDem();
			i++;
		}
		return matrice;
	}
	
	public void viderChamps()
	{
		this.txtNom.setText("");
		this.cbEtrePluriel.setSelectedItem("");;
	}
	
	public TypeDemande saisirtypeD()
	{
		TypeDemande uneTypeD=null;
		String errorMessage = "";
		String nom =this.txtNom.getText();
		boolean etrePluriel = false;
		
		//check saisir
		//check nom
		if(nom.equals(""))
		{
			this.txtNom.setBackground(Color.red);
			errorMessage += "Nom de service est requis \n";
		}
		else 
		{
			this.txtNom.setBackground(Color.white);
		}
		//check etre pluriel
		if(this.cbEtrePluriel.getSelectedItem().toString().equals(""))
		{
			this.cbEtrePluriel.setBackground(Color.red);
			errorMessage += "Type de service mono/pluriel est requis \n";
		}
		else 
		{
			etrePluriel = this.cbEtrePluriel.getSelectedItem().toString() == "oui" ? true : false;
		}
		if(errorMessage != "")
		{
			JOptionPane.showMessageDialog(this, "Erreur de saisir: \n" + errorMessage);
			
			return null;
		}
		//*************************************
		
		return new TypeDemande(nom, etrePluriel);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btAnnuler)
		{
			this.viderChamps();	
		}
		else if (e.getSource()==this.btEnregistrer && e.getActionCommand().equals("Enregistrer"))
		{
			TypeDemande uneTypeD = this.saisirtypeD();
			//check if object is null ne rien faire
			if(uneTypeD == null)
				return;
			
			modele.insertTypeD(uneTypeD);
			
			//RECUPERER id auto_increment de la bdd
			uneTypeD =modele.selectWheretypeD(uneTypeD.getNom(), uneTypeD.isEtrePlurielDem());

			//mettre a jour l'affichage
			Object ligne[]= {uneTypeD.getId(),uneTypeD.getNom(), uneTypeD.isEtrePlurielDem()};
												
			unTableau.ajouterLigne(ligne);

			JOptionPane.showMessageDialog(this, "Insertion Reussie");
			this.viderChamps();				
		}
		
	else if (e.getSource()==this.btEnregistrer && e.getActionCommand().equals("Modifier"))
	{
	
		TypeDemande uneTypeD = this.saisirtypeD();
		if(uneTypeD == null)
			return;
		
		JOptionPane.showMessageDialog(this, "Modification effectuee");
		int numLigne =this.uneTable.getSelectedRow();
		
		int idTypeD = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
		uneTypeD.setId(idTypeD);
		
		Object ligne[]= 
		{
			uneTypeD.getId(),uneTypeD.getNom(), uneTypeD.isEtrePlurielDem()
		};
		
		unTableau.modifierLigne(numLigne,ligne);
		modele.updateTypeD(uneTypeD);
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
