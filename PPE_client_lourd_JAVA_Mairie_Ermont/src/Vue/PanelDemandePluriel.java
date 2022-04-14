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
import Controleur.DemandePluriel;
import Controleur.Employe;
import Controleur.Tableau;
import Controleur.TypeDemande;
import Modele.modele;

public class PanelDemandePluriel extends PanelDeBase implements ActionListener
{
	private JPanel panelForm = new JPanel();
	private JPanel panelTable = new JPanel();
	private JButton btEnregistrer= new JButton("Enregistrer");
	private JButton btAnnuler= new JButton("Annuler");
	private JTextField txtDateDemande= new JTextField();	
	private JComboBox<String> cbxEtat = new JComboBox<String>(); 
	private JComboBox<String> cbxCitoyen1 = new JComboBox<String>(); 
	private JComboBox<String> cbxCitoyen2 = new JComboBox<String>(); 
	private JComboBox<String> cbxtypeD= new JComboBox<String>(); 
	private JComboBox<String> cbxEmploye = new JComboBox<String>(); 
	private JTable uneTable=null;
	//zone de recherche - par requeque like
	private JTextField txtMot = new JTextField();
	private JButton btRechercher= new JButton("Rechercher");
	private static Tableau unTableau =null;
	
	
	public PanelDemandePluriel() 
	{
		super(new Color(163, 222, 142));
		this.panelForm.setLayout(new GridLayout(7,2));
		this.panelForm.setBackground(Color.white);	
		
		this.panelForm.add(new JLabel(" Date demande"));
		this.panelForm.add(this.txtDateDemande);
				
		this.panelForm.add(new JLabel(" état"));
		this.panelForm.add(this.cbxEtat);
		
		this.panelForm.add(new JLabel(" Citoyen1"));
		this.panelForm.add(this.cbxCitoyen1);
		
		this.panelForm.add(new JLabel(" Citoyen2"));
		this.panelForm.add(this.cbxCitoyen2);
		
		this.panelForm.add(new JLabel(" Type demande"));
		this.panelForm.add(this.cbxtypeD);
		
		this.panelForm.add(new JLabel(" Employe"));
		this.panelForm.add(this.cbxEmploye);
		
		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btEnregistrer);
		
		this.panelForm.setBounds(20,20,300,220);
		this.add(this.panelForm);
		
		//add la liste de service en combo box
		this.cbxEtat.addItem("1-En cours de traitement");
		this.cbxEtat.addItem("2-Demande acceptée");
		this.cbxEtat.addItem("3-Demande refusée");
		this.cbxEtat.addItem("");
		
		this.remplirCBX();
		
		// rendre les boutons ecoutables
		
		this.btEnregistrer.addActionListener(this);
		this.btAnnuler.addActionListener(this);
		this.btRechercher.addActionListener(this);
		//construction du panel Table
		this.panelTable.setBounds(340,20,560,320);
		this.panelTable.setBackground(new Color(163, 222, 142));
		this.panelTable.setLayout(null);
		
//nom de colonne : entete
		
		String entetes[] = {"ID Demande","Date demande","DateRep","Etat","Citoyen1","Citoyen2","typeDemande","Employe"};
		Object donnees [][] = this.getLesDonnees("");
		unTableau = new Tableau (entetes, donnees);
		//appel du constructeur
		
		
	
		this.uneTable= new JTable(unTableau);
		JScrollPane uneScroll = new JScrollPane(this.uneTable);
		uneScroll.setBounds(0,60,560,200);
		this.panelTable.add(uneScroll);
		
		//placement de la zone de recherche
		this.txtMot.setBounds(130,10,120,25);
		this.panelTable.add(this.txtMot);
		this.btRechercher.setBounds(270,10,120,25);
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
						
						modele.ValiderDemandePluriel(idemande);
						/*String strEtat = "2-Demande acceptée";
						cbxEtat.setSelectedItem(strEtat);*/

					}
					else 
					{
						int idemande = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
						
						modele.RefuserDemandePluriel(idemande);
						/*String strEtat = "3-Demande refusée";
						cbxEtat.setSelectedItem(strEtat);*/

						
					
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
		ArrayList<DemandePluriel> lesDemandeP= null;
		if(mot.equals(""))
			{
			lesDemandeP=modele.selectAllDemandeP();
			}
		else
		{
			lesDemandeP=modele.selectLikeDemandeP(mot);
		}
		//create matrice empty 
		//size() nb ligne
		Object [][] matrice = new Object[lesDemandeP.size()][8];
		int i=0;
		
		for(DemandePluriel uneDemandeP : lesDemandeP)
		{
			matrice[i][0]=uneDemandeP.getIdDemande();
			matrice[i][1]=uneDemandeP.getDateDemande();
			matrice[i][2]=uneDemandeP.getDateRep();
			matrice[i][3]=uneDemandeP.getEtat();
			matrice[i][4]=uneDemandeP.getIdcitoyen1();
			matrice[i][5]=uneDemandeP.getIdcitoyen2();
			matrice[i][6]=uneDemandeP.getIdTypeD();
			matrice[i][7]=uneDemandeP.getIdEmploye();
			
			i++;
		}
		return matrice;
	}



	private void remplirCBX() 
	{
		this.cbxCitoyen1.addItem("");
		ArrayList<Citoyen> lesCitoyens = modele.selectAllCitoyens(); 
		for (Citoyen unCitoyen : lesCitoyens)
		{
			this.cbxCitoyen1.addItem(unCitoyen.getIdcitoyen()+"-" 
							+ unCitoyen.getNom());
			
		}
		this.cbxCitoyen2.addItem("");
		ArrayList<Citoyen> lesCitoyen2s = modele.selectAllCitoyens(); 
		for (Citoyen unCitoyen : lesCitoyen2s)
		{
			this.cbxCitoyen2.addItem(unCitoyen.getIdcitoyen()+"-" 
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
		cbxCitoyen1.setSelectedItem("");
		cbxCitoyen2.setSelectedItem("");
		cbxEmploye.setSelectedItem("");
		cbxtypeD.setSelectedItem("");
		
	}
	
	public DemandePluriel saisirDemandeP()
	{
		DemandePluriel uneDemandeP=null;
		String errorMessage = "";
		
		String dateDemande =this.txtDateDemande.getText();
		String tabE[] = this.cbxEtat.getSelectedItem().toString().split("-");
		String etat ="";
		
		String tab1[] = this.cbxCitoyen1.getSelectedItem().toString().split("-");
		int idcitoyen1 = 0; 
		
		String tab2[] = this.cbxCitoyen1.getSelectedItem().toString().split("-");
		int idcitoyen2 = 0; 
		
		String tab3[] = this.cbxtypeD.getSelectedItem().toString().split("-");
		int idtypeD = 0; 
		String tab4[] = this.cbxEmploye.getSelectedItem().toString().split("-");
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
		else {
			etat =tabE[1];
		}
		//check citoyen1
		if(tab1.length !=2)
		{
			this.cbxCitoyen1.setBackground(Color.red);
			errorMessage += "Citoyen 1 est requis \n";
		}
		else {
			idcitoyen1 = Integer.parseInt(tab1[0]);
		}
		//check citoyen 2
		if(tab2.length !=2)
		{
			this.cbxCitoyen2.setBackground(Color.red);
			errorMessage += "Citoyen 2 est requis \n";
		}
		else {
			idcitoyen2 = Integer.parseInt(tab2[0]);
		}
		//check type demande
		if(tab3.length !=2)
		{
			this.cbxtypeD.setBackground(Color.red);
			errorMessage += "Type demande est requis \n";
		}
		else {
			idtypeD = Integer.parseInt(tab3[0]);
		}
		//check employe
		
		if(tab4.length !=2)
		{
			this.cbxEmploye.setBackground(Color.red);
			errorMessage += "employe est requis \n";
		}
		else {
			idemploye = Integer.parseInt(tab4[0]); 
		
		}
		
		
		if(errorMessage != "")
		{
			JOptionPane.showMessageDialog(this, "Erreur de saisir: \n" + errorMessage);
			
			return null;
		}

		// à faire check combobox
		if(! dateDemande.equals("")  &&  !etat.equals("") )
		{
			uneDemandeP = new DemandePluriel(dateDemande, etat, idcitoyen1, idcitoyen2, idtypeD,idemploye);
		}
		else 
		{
			return null;
		}
		return uneDemandeP;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==this.btEnregistrer && e.getActionCommand().equals("Enregistrer"))
		{
			
			
		DemandePluriel uneDemandeP = this.saisirDemandeP();
		if(uneDemandeP == null)
			return;
		modele.insertDemandeP(uneDemandeP);
				
		//RECUPERER id auto_increment de la bdd
		uneDemandeP =modele.selectWhereDemandeP(uneDemandeP.getDateDemande(), uneDemandeP.getDateRep(), uneDemandeP.getEtat(), 
		uneDemandeP.getIdcitoyen1(),uneDemandeP.getIdcitoyen2(), uneDemandeP.getIdTypeD(),uneDemandeP.getIdEmploye());
				
			
				
				//mettre a jour l'affichage
		Object ligne[]= {uneDemandeP.getIdDemande(),uneDemandeP.getDateDemande(), 
				uneDemandeP.getDateRep(), uneDemandeP.getEtat(), 
				uneDemandeP.getIdcitoyen1(), uneDemandeP.getIdcitoyen2(),uneDemandeP.getIdTypeD(),uneDemandeP.getIdEmploye()};
				
									
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
