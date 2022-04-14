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

import Controleur.Citoyen;
import Controleur.DemandeMono;
import Controleur.Employe;
import Controleur.Tableau;
import Controleur.TypeDemande;
import Modele.modele;

public class PanelDemandeMono extends PanelDeBase implements ActionListener{
	private JPanel panelForm = new JPanel();
	private JPanel panelTable = new JPanel();
	private JButton btEnregistrer= new JButton("Enregistrer");
	private JButton btAnnuler= new JButton("Annuler");
	private JTextField txtDateDemande= new JTextField();
	
	private JComboBox<String> cbxEtat = new JComboBox<String>(); 
	private JComboBox<String> cbxCitoyen = new JComboBox<String>(); 
	private JComboBox<String> cbxtypeD= new JComboBox<String>(); 
	private JComboBox<String> cbxEmploye = new JComboBox<String>(); 
	private JTable uneTable=null;
	//zone de recherche - par requeque like
	private JTextField txtMot = new JTextField();
	private JButton btRechercher= new JButton("Rechercher");
	private static Tableau unTableau =null;
	
	
	public PanelDemandeMono() 
	{
		super(new Color(163, 222, 142));
		this.panelForm.setLayout(new GridLayout(6,2));
		this.panelForm.setBackground(Color.white);	
		
		this.panelForm.add(new JLabel(" Date demande"));
		this.panelForm.add(this.txtDateDemande);
		
		
		this.panelForm.add(new JLabel(" état"));
		this.panelForm.add(this.cbxEtat);
		
		this.panelForm.add(new JLabel(" Citoyen"));
		this.panelForm.add(this.cbxCitoyen);
		
		this.panelForm.add(new JLabel(" Type demande"));
		this.panelForm.add(this.cbxtypeD);
		
		this.panelForm.add(new JLabel(" Employe"));
		this.panelForm.add(this.cbxEmploye);
		
		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btEnregistrer);
		
		this.panelForm.setBounds(20,20,300,200);
		this.add(this.panelForm);
		
		//add la liste de service en combo box
		this.cbxEtat.addItem("1-En cours de traitement");
		this.cbxEtat.addItem("2-Demande acceptée");
		this.cbxEtat.addItem("3-Demande refusée");
		this.cbxEtat.addItem("");
		
		this.remplirCBX ();
		
		// rendre les boutons ecoutables
		
		this.btEnregistrer.addActionListener(this);
		this.btAnnuler.addActionListener(this);
		this.btRechercher.addActionListener(this);
		//construction du panel Table
		this.panelTable.setBounds(340,20,560,320);
		this.panelTable.setBackground(new Color(163, 222, 142));
		this.panelTable.setLayout(null);
	
		//nom de colonne : entete
		
		String entetes[] = {"ID Demande","Date demande","DateRep","Etat","Citoyen","typeDemande","Employe"};
		Object donnees [][] = this.getLesDonnees("");
		unTableau = new Tableau (entetes, donnees);
		//appel du constructeur
		
		
	
		this.uneTable= new JTable(unTableau);
		JScrollPane uneScroll = new JScrollPane(this.uneTable);
		uneScroll.setBounds(10,60,560,200);
		this.panelTable.add(uneScroll);
		
		//placement de la zone de recherche
		this.txtMot.setBounds(100,20,120,25);
		this.panelTable.add(this.txtMot);
		this.btRechercher.setBounds(240,20,120,25);
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
					int retour =JOptionPane.showConfirmDialog(null, "Vous voulez valider cet demande ?",
							"Valider de demande", JOptionPane.YES_NO_OPTION);
					if (retour == 0)
					{
						int idemande = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
						
						modele.ValiderDemandeMono(idemande);
						String strEtat = "2-Demande acceptée";
						cbxEtat.setSelectedItem(strEtat);

					}
					else 
					{
						int idemande = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
						
						modele.RefuserDemandeMono(idemande);
						String strEtat = "3-Demande refusée";
						cbxEtat.setSelectedItem(strEtat);

						
					
					}
				
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
		ArrayList<DemandeMono> lesDemandeM= null;
		if(mot.equals(""))
			{
			lesDemandeM=modele.selectAllDemandeM();
			}
		else
		{
			lesDemandeM=modele.selectLikeDemandeM(mot);
		}
		//create matrice empty 
		//size() nb ligne
		Object [][] matrice = new Object[lesDemandeM.size()][7];
		int i=0;
		
		for(DemandeMono unDemandeM : lesDemandeM)
		{
			matrice[i][0]=unDemandeM.getIdDemande();
			matrice[i][1]=unDemandeM.getDateDemande();
			matrice[i][2]=unDemandeM.getDateRep();
			matrice[i][3]=unDemandeM.getEtat();
			matrice[i][4]=unDemandeM.getIdcitoyen();
			matrice[i][5]=unDemandeM.getIdTypeD();
			matrice[i][6]=unDemandeM.getIdEmploye();
			i++;
		}
		return matrice;
	}
	
	public void remplirCBX ()
	{
		this.cbxCitoyen.addItem("");
		ArrayList<Citoyen> lesCitoyens = modele.selectAllCitoyens(); 
		for (Citoyen unCitoyen : lesCitoyens)
		{
			this.cbxCitoyen.addItem(unCitoyen.getIdcitoyen()+"-" 
							+ unCitoyen.getNom());
			
		}
		this.cbxtypeD.addItem("");
		ArrayList<TypeDemande> lesTypeDs = modele.selectAllTypeDs(); 
		for (TypeDemande untypeD : lesTypeDs)
		{
			this.cbxtypeD.addItem(untypeD.getId()+"-"
					+ untypeD.getNom());
		}
		this.cbxEmploye.addItem("");
		ArrayList<Employe> lesEmployes = modele.selectAllEmployes(); 
		for (Employe unEmploye : lesEmployes)
		{
			this.cbxEmploye.addItem(unEmploye.getIdemploye()+"-"
					+ unEmploye.getNom());
		}
	}
	
	public void viderChamps()
	{
		this.txtDateDemande.setText("");
		cbxCitoyen.setSelectedItem("");
		cbxEmploye.setSelectedItem("");
		cbxtypeD.setSelectedItem("");
		
	}
	public DemandeMono saisirDemandeM()
	{
		DemandeMono unDemandeM=null;
		String errorMessage = "";
		String dateDemande =this.txtDateDemande.getText();
		String tabE[] = this.cbxEtat.getSelectedItem().toString().split("-");
		String etat = "";
		String tab[] = this.cbxCitoyen.getSelectedItem().toString().split("-");
		int idcitoyen = 0;
		String tab1[] = this.cbxtypeD.getSelectedItem().toString().split("-");
		int idtypeD=0;
		String tab2[] = this.cbxEmploye.getSelectedItem().toString().split("-");
		int idemploye = 0;

		//check date demande saisi
		if(dateDemande.equals(""))
		{
			this.txtDateDemande.setBackground(Color.red);
			errorMessage += "Date de demande est requis \n";
		}
		else 
		{
			this.txtDateDemande.setBackground(Color.white);
		}
		//check état
		if(tabE.length !=2)
		{
			this.cbxEtat.setBackground(Color.red);
			errorMessage += "état est requis \n";
		}
		else
		{
			etat = tabE[1];
		}
		
		//check citoyen 
		if(tab.length !=2)
		{
			this.cbxCitoyen.setBackground(Color.red);
			errorMessage += "Citoyen est requis \n";
		}
		else
		{
			idcitoyen = Integer.parseInt(tab[0]);
		}
		
		//check type demande
		if(tab1.length !=2)
		{
			this.cbxtypeD.setBackground(Color.red);
			errorMessage += "Type demande est requis \n";
		}
		else
		{
			idtypeD = Integer.parseInt(tab1[0]);
		}
		//check employe
		if(tab2.length !=2)
		{
			this.cbxEmploye.setBackground(Color.red);
			errorMessage += "employe est requis \n";
		}
		else
		{
			idemploye = Integer.parseInt(tab2[0]);
		}

		if(errorMessage != "")
		{
			JOptionPane.showMessageDialog(this, "Erreur de saisir: \n" + errorMessage);
			
			return null;
		}
		
		// à faire check combobox
		if(! dateDemande.equals("")  &&  !etat.equals("") )
		{
			unDemandeM = new DemandeMono(dateDemande, etat, idcitoyen, idtypeD,idemploye);
		}
		else 
		{
			return null;
		}
		return unDemandeM;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource()==this.btEnregistrer && e.getActionCommand().equals("Enregistrer"))
			{
				
				
			DemandeMono uneDemandeM = this.saisirDemandeM();
			if(uneDemandeM == null)
				return;
			modele.insertDemandeM(uneDemandeM);
					
			//RECUPERER id auto_increment de la bdd
			uneDemandeM =modele.selectWhereDemandeM(uneDemandeM.getDateDemande(), uneDemandeM.getDateRep(), uneDemandeM.getEtat(), 
			uneDemandeM.getIdcitoyen(), uneDemandeM.getIdTypeD(),uneDemandeM.getIdEmploye());
					
				
					
					//mettre a jour l'affichage
			Object ligne[]= {uneDemandeM.getIdDemande(),uneDemandeM.getDateDemande(), 
			uneDemandeM.getDateRep(), uneDemandeM.getEtat(), 
			uneDemandeM.getIdcitoyen(), uneDemandeM.getIdTypeD(),uneDemandeM.getIdEmploye()};
					
										
					unTableau.ajouterLigne(ligne);					
					JOptionPane.showMessageDialog(this, "Insertion Reussie");
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
