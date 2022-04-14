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


import Controleur.Employe;
import Controleur.Tableau;
import Modele.modele;

public class PanelEmploye extends PanelDeBase implements ActionListener {
	private JPanel panelForm = new JPanel();
	private JPanel panelTable = new JPanel();
	private JButton btEnregistrer= new JButton("Enregistrer");
	private JButton btAnnuler= new JButton("Annuler");
	private JTextField txtNomE= new JTextField();
	private JTextField txtPrenomE= new JTextField();
	private JTextField txtEmailE= new JTextField();
	private JComboBox<String> cbxIdService = new JComboBox<String>(); 
	//zone de recherche - par requeque like
	private JTextField txtMot = new JTextField();
	private JButton btRechercher= new JButton("Rechercher");
	
	private static Tableau unTableau =null;
	private JTable uneTable=null;
	
	public PanelEmploye()
	{
		super(new Color(195, 232, 199));		
		this.panelForm.setBackground(Color.white);	
		this.panelForm.setBounds(20, 20, 300, 160);
		this.panelForm.setLayout(new GridLayout(5,2));
		this.panelForm.add(new JLabel(" Nom : ")); 
		this.panelForm.add(this.txtNomE); 
		this.panelForm.add(new JLabel(" Prénom : ")); 
		this.panelForm.add(this.txtPrenomE);
		this.panelForm.add(new JLabel(" Email : ")); 
		this.panelForm.add(this.txtEmailE);
		this.panelForm.add(new JLabel(" Service : ")); 
		this.panelForm.add(this.cbxIdService);
		this.panelForm.add(this.btAnnuler); 
		this.panelForm.add(this.btEnregistrer);
		
		this.add(this.panelForm);
		
		//add la liste de service en combo box
		this.cbxIdService.addItem("");
		this.cbxIdService.addItem("1-Administration");
		this.cbxIdService.addItem("2-Communication");

		// rendre les boutons ecoutables
		
		this.btEnregistrer.addActionListener(this);
		this.btAnnuler.addActionListener(this);
		this.btRechercher.addActionListener(this);
		
		//construction du panel Table
		this.panelTable.setBounds(360,20,550,200);
		this.panelTable.setBackground(new Color(195, 232, 199));
		this.panelTable.setLayout(null);
		
		//nom de colonne : entete
		
		String entetes[] = {"ID employe","Nom","Prénom","Email","Service"};
		Object donnees [][] = this.getLesDonnees("");
		unTableau = new Tableau (entetes, donnees);

		this.uneTable= new JTable(unTableau);
		JScrollPane uneScroll = new JScrollPane(this.uneTable);
		uneScroll.setBounds(0,60,550,200);
		this.panelTable.add(uneScroll);
		
		
		/*******/
		
		
		
		//placement de la zone de recherche
		this.txtMot.setBounds(150,10,120,25);
		this.panelTable.add(this.txtMot);
		this.btRechercher.setBounds(290,10,120,25);
		this.panelTable.add(this.btRechercher);
		this.add(this.panelTable);
		
		this.uneTable.addMouseListener(new MouseListener()
		{

			@Override
			public void mouseClicked(MouseEvent e) 
			{
				int nbclic = e.getClickCount();
				if(nbclic==2)
				{
					int numLigne=uneTable.getSelectedRow();
					int retour =JOptionPane.showConfirmDialog(null, "Vous voulez suppimer cet employe ?",
							"Suprresion employé", JOptionPane.YES_NO_OPTION);
					if (retour == 0)
					{
						//suppresion du citoyen
						int idemploye = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
						modele.deleteEmploye(idemploye);
						
						//actualiser l'affichage
						unTableau.supprimerLigne(numLigne);
					}
					
				}
				else if(nbclic==1)
				{
					int numLigne1=uneTable.getSelectedRow();
					
					String nom = uneTable.getValueAt(numLigne1,1).toString();
					txtNomE.setText(nom);
					
					String prenom = uneTable.getValueAt(numLigne1,2).toString();
					txtPrenomE.setText(prenom);
					
					String email = uneTable.getValueAt(numLigne1,3).toString();
					txtEmailE.setText(email);
					
					int idservice = (Integer)uneTable.getValueAt(numLigne1,4);
					
					String strIdService = "";
					
					switch(idservice) {
					  case 1:
						  strIdService = "1-Administration";
					    break;
					  case 2:
						  strIdService = "2-Communication";
					    break;
					  default:
					    
					}
					
					if(strIdService != "")					
						cbxIdService.setSelectedItem(strIdService);
					
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
		ArrayList<Employe> lesEmployes= null;
		if(mot.equals(""))
			{
			lesEmployes=modele.selectAllEmployes();
			}
		else
		{
			lesEmployes=modele.selectLikeEmploye(mot);
		}
		//create matrice empty 
		//size() nb ligne
		Object [][] matrice = new Object[lesEmployes.size()][6];
		int i=0;
		
		for(Employe unEmploye : lesEmployes)
		{
			matrice[i][0]=unEmploye.getIdemploye();
			matrice[i][1]=unEmploye.getNom();
			matrice[i][2]=unEmploye.getPrenom();
			matrice[i][3]=unEmploye.getEmail();
			matrice[i][4]=unEmploye.getIdservice();
			i++;
		}
		return matrice;
	}
	
	public void viderChamps()
	{
		this.txtNomE.setText("");
		this.txtPrenomE.setText("");
		this.txtEmailE.setText("");
		this.cbxIdService.setSelectedItem("");
		
	}
	
	public Employe saisirEmploye()
	{

		Employe unEmploye=null;
		String errorMessage = "";
		String nom =this.txtNomE.getText();
		String prenom =this.txtPrenomE.getText();
		String email =this.txtEmailE.getText();
		String tab[] = this.cbxIdService.getSelectedItem().toString().split("-");
		int idservice = 0; 
		
		//check sécurisé **************************************************************************
		//securité
		if(nom.equals(""))
		{
			this.txtNomE.setBackground(Color.red);
			errorMessage += "Nom employé est requis \n";
		}
		else {
			this.txtNomE.setBackground(Color.white);			
		}
		//check prenom
		if(prenom.equals(""))
		{
			this.txtPrenomE.setBackground(Color.red);
			errorMessage += "Prénom employé est requis \n";
		}
		else 
		{	
			this.txtPrenomE.setBackground(Color.white);
		}
		//check email
		if(email.equals(""))
		{
			this.txtEmailE.setBackground(Color.red);
			errorMessage += "Email employé est requis \n";
		}
		else 
		{
			this.txtEmailE.setBackground(Color.white);
		}
		//check service
		if(tab.length != 2)
		{
			this.cbxIdService.setBackground(Color.red);
			errorMessage += "Service est requis \n";
		}
		else {
			idservice = Integer.parseInt(tab[0]);
		}
		
		if(errorMessage != "")
		{
			JOptionPane.showMessageDialog(this, "Erreur de saisir: \n" + errorMessage);
			
			return null;
		}

		//fin check -------------**********************************************************
		if(! nom.equals("") 
			&& ! prenom.equals("") 
			&& ! email.equals("") 
			)
		{
			unEmploye = new Employe(idservice,nom, prenom, email);
		}
		else 
		{
			return null;
		}
		return unEmploye;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btAnnuler)
		{
			this.viderChamps();	
		}
		else if (e.getSource()==this.btEnregistrer && e.getActionCommand().equals("Enregistrer"))
		{
			Employe unEmploye = this.saisirEmploye();
			
			// si unemploye is null, arrete de exécuter
			if(unEmploye == null)
				return;

				modele.insertEmploye(unEmploye);
				
				//RECUPERER id auto_increment de la bdd
				unEmploye =modele.selectWhereEmploye(unEmploye.getIdservice(), unEmploye.getNom(), unEmploye.getPrenom(),
						unEmploye.getEmail());
				
			
				
				//mettre a jour l'affichage
				Object ligne[]= {unEmploye.getIdemploye(), unEmploye.getNom(), unEmploye.getPrenom(),
						unEmploye.getEmail(), unEmploye.getIdservice()};
													
				unTableau.ajouterLigne(ligne);
	
				JOptionPane.showMessageDialog(this, "Insertion Reussie");
				this.viderChamps();				
			}
		
	else if (e.getSource()==this.btEnregistrer && e.getActionCommand().equals("Modifier"))
	{
	
		Employe unEmploye = this.saisirEmploye();
		
		JOptionPane.showMessageDialog(this, "Modification effectuee");
		int numLigne =this.uneTable.getSelectedRow();
		
		int idemploye = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
		unEmploye.setIdemploye(idemploye);
		
		Object ligne[]= 
		{
			unEmploye.getIdemploye(), unEmploye.getNom(), unEmploye.getPrenom(),
			unEmploye.getEmail(), unEmploye.getIdservice()
		};
		
		unTableau.modifierLigne(numLigne,ligne);
		modele.updateEmploye(unEmploye);
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
