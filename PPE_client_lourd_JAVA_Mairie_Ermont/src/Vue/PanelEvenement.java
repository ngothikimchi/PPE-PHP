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
import Controleur.Employe;
import Controleur.Evenement;
import Controleur.Tableau;
import Controleur.TypeEve;
import Modele.modele;

public class PanelEvenement extends PanelDeBase implements ActionListener
{
	private JPanel panelForm = new JPanel();
	private JPanel panelTable = new JPanel();
	private JButton btEnregistrer= new JButton("Enregistrer");
	private JButton btAnnuler= new JButton("Annuler");
	private JTextField txtNomEve= new JTextField();
	private JTextField txtContenuE= new JTextField();
	private JTextField txtAdresseE= new JTextField();
	private JTextField txtDateDebut= new JTextField();
	private JTextField txtDateFin= new JTextField();
	private JTextField txtDatefinIns= new JTextField();
	private JTextField txtNbMax= new JTextField();
	
	//ajouter la liste de choisies
	private JComboBox<String> cbCodetypeE= new JComboBox<>();
	private JComboBox<String> cbIdAssociation= new JComboBox<>();
	
	private JTable uneTable=null;
	private static Tableau unTableau =null;
	
	//zone de recherche - par requeque like
	private JTextField txtMot = new JTextField();
	private JButton btRechercher= new JButton("Rechercher");
	
	public PanelEvenement() 
	{
		super(new Color(163, 222, 142));
		this.panelForm.setLayout(new GridLayout(10,2));
		this.panelForm.setBackground(Color.white);	
		this.panelForm.add(new JLabel(" Nom evenement"));
		this.panelForm.add(this.txtNomEve);
		this.panelForm.add(new JLabel(" Contenu"));
		this.panelForm.add(this.txtContenuE);
		this.panelForm.add(new JLabel(" Adresse"));
		this.panelForm.add(this.txtAdresseE);
		this.panelForm.add(new JLabel(" Date debut"));
		this.panelForm.add(this.txtDateDebut);
		this.panelForm.add(new JLabel(" Date fin"));
		this.panelForm.add(this.txtDateFin);
		this.panelForm.add(new JLabel(" Date fin Inscription"));
		this.panelForm.add(this.txtDatefinIns);
		this.panelForm.add(new JLabel(" NB Max"));
		this.panelForm.add(this.txtNbMax);
		this.panelForm.add(new JLabel(" TypeEvenement"));
		this.panelForm.add(this.cbCodetypeE);
		this.panelForm.add(new JLabel(" Id Association"));
		this.panelForm.add(this.cbIdAssociation);
		
		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btEnregistrer);
	
		this.panelForm.setBounds(20,20,300,260);
		this.add(this.panelForm);
		
		this.remplirCBX ();
		//construction du panel Table
		this.panelTable.setBounds(340,20,850,320);
		this.panelTable.setBackground(new Color(163, 222, 142));
		this.panelTable.setLayout(null);
	
		//nom de colonne : entete
		
		String entetes[] = {"ID evenement","Nom","Contenu","Adresse","Date debut",
				"Date fin","Date fin inscription","Nb max",
				"Code type E","Id Assoc"};
		Object donnees [][] = this.getLesDonnees("");
		unTableau = new Tableau (entetes, donnees);
		//appel du constructeur

		this.uneTable= new JTable(unTableau);
		/*** Rendre la table scrollable horizontalement & verticallement ***/		
		uneTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane uneScroll = new JScrollPane(this.uneTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		uneScroll.setBounds(10,60,580,200);
		this.panelTable.add(uneScroll);
		/*******/
		
		// rendre les boutons ecoutables
		
		this.btEnregistrer.addActionListener(this);
		this.btAnnuler.addActionListener(this);
		this.btRechercher.addActionListener(this);
		
		
		//placement de la zone de recherche
		this.txtMot.setBounds(150,10,120,30);
		this.panelTable.add(this.txtMot);
		this.btRechercher.setBounds(290,10,120,30);
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
					int retour =JOptionPane.showConfirmDialog(null, "Vous voulez suppimer cet événement ?",
							"Suprresion événement", JOptionPane.YES_NO_OPTION);
					if (retour == 0)
					{
						//suppresion du citoyen
						int idE = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
						modele.deleteEvenement(idE);
						
						//actualiser l'affichage
						unTableau.supprimerLigne(numLigne);
					}
					
				}
				else if(nbclic==1)
				{
					int numLigne1=uneTable.getSelectedRow();
					
					String nomE = uneTable.getValueAt(numLigne1,1).toString();
					txtNomEve.setText(nomE);
					
					String contenuE = uneTable.getValueAt(numLigne1,2).toString();
					txtContenuE.setText(contenuE);
					
					String adresseE = uneTable.getValueAt(numLigne1,3).toString();
					txtAdresseE.setText(adresseE);
					
					String dateDebut = uneTable.getValueAt(numLigne1,4).toString();
					txtDateDebut.setText(dateDebut);
					
					String dateFin = uneTable.getValueAt(numLigne1,5).toString();
					txtDateFin.setText(dateFin);
					
					String dateFinInscription = uneTable.getValueAt(numLigne1,6).toString();
					txtDatefinIns.setText(dateFinInscription);
					
					String nbMax = uneTable.getValueAt(numLigne1,7).toString();
					txtNbMax.setText(nbMax);
					
					String codeTypeE = uneTable.getValueAt(numLigne1,8).toString();
					cbCodetypeE.setSelectedItem(codeTypeE);
					
					String idAssoc = uneTable.getValueAt(numLigne1,9).toString();
					cbIdAssociation.setSelectedItem(idAssoc);

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

	private void remplirCBX() {
		this.cbCodetypeE.addItem("");
		ArrayList<TypeEve> lesTypeEves = modele.selectAllTypeEves(); 
		for (TypeEve uneTypeE : lesTypeEves)
		{
			this.cbCodetypeE.addItem(uneTypeE.getCodeTypeE()+"-" 
							+ uneTypeE.getNomTypeE());
			
		}
		this.cbIdAssociation.addItem("");
		ArrayList<Association> lesAssocs = modele.selectAllAssociations(); 
		for (Association uneAssoc : lesAssocs)
		{
			this.cbIdAssociation.addItem(uneAssoc.getIdassoc()+"-"
					+ uneAssoc.getNom());
		}
		
	}

	public void viderChamps()
	{
		this.txtNomEve.setText("");
		this.txtContenuE.setText("");
		this.txtAdresseE.setText("");
		this.txtDateDebut.setText("");
		this.txtDateFin.setText("");
		this.txtDatefinIns.setText("");
		this.txtDatefinIns.setText("");
		cbCodetypeE.setSelectedItem("");
		cbIdAssociation.setSelectedItem("");

	}
	
	private Object[][] getLesDonnees(String mot) {
		ArrayList<Evenement> lesEvenements= null;
		if(mot.equals(""))
			{
			lesEvenements=modele.selectAllEvenements();
			}
		else
		{
			lesEvenements=modele.selectLikeEvenements(mot);
		}
		//create matrice empty 
		//size() nb ligne
		Object [][] matrice = new Object[lesEvenements.size()][10];
		int i=0;
		
		for(Evenement uneEve: lesEvenements)
		{
			matrice[i][0]=uneEve.getIdE();
			matrice[i][1]=uneEve.getNomE();
			matrice[i][2]=uneEve.getContenuE();
			matrice[i][3]=uneEve.getAdresseE();
			matrice[i][4]=uneEve.getDateDebut();
			matrice[i][5]=uneEve.getDateFin();
			matrice[i][6]=uneEve.getDateFinInscription();
			matrice[i][7]=uneEve.getNbMax();
			matrice[i][8]=uneEve.getCodeTypeE();
			matrice[i][9]=uneEve.getIdAssoc();
			
			i++;
		}
		return matrice;
	}
	
	public Evenement saisirEve()
	{
		Evenement uneEve=null;
		String errorMessage = "";
		String nomE =this.txtNomEve.getText();
		String contenuE =this.txtContenuE.getText();
		String adresseE =this.txtAdresseE.getText();
		String dateDebut =this.txtDateDebut.getText();
		String dateFin =this.txtDateFin.getText();
		String dateFinInscription =this.txtDatefinIns.getText();
		String strNbrMax = txtNbMax.getText();
		Integer nbMax = 0;
		
		String tab[] = this.cbCodetypeE.getSelectedItem().toString().split("-");
		String codeTypeE = ""; 
		
		String tab1[] = this.cbIdAssociation.getSelectedItem().toString().split("-");
		int idAssoc = 0; 
		
		//check saisi ****************************
		//check nom événement
		if(nomE.equals(""))
		{
			this.txtNomEve.setBackground(Color.red);
			errorMessage += "Nom d'événement est requis \n";
		}
		else 
		{
			this.txtNomEve.setBackground(Color.white);
		}
		
		//check contenu
		if(contenuE.equals(""))
		{
			this.txtContenuE.setBackground(Color.red);
			errorMessage += "Le contenu est requis \n";
		}
		else 
		{
			this.txtContenuE.setBackground(Color.white);
		}
		
		//check adresse
		if(adresseE.equals(""))
		{
			this.txtAdresseE.setBackground(Color.red);
			errorMessage += "L'adresse est requis \n";
		}
		else 
		{
			this.txtAdresseE.setBackground(Color.white);
		}
		
		//check date debut
		if(dateDebut.equals(""))
		{
			this.txtDateDebut.setBackground(Color.red);
			errorMessage += "Date de début est requis \n";
		}
		else 
		{
			this.txtDateDebut.setBackground(Color.white);
		}
		
		//check date fin
		if(dateFin.equals(""))
		{
			this.txtDateFin.setBackground(Color.red);
			errorMessage += "Date fin est requis \n";
		}
		else 
		{
			this.txtDateFin.setBackground(Color.white);
		}
		
		//check date fin inscription
		if(dateFinInscription.equals(""))
		{
			this.txtDatefinIns.setBackground(Color.red);
			errorMessage += "Date fin d'inscription est requis \n";
		}
		else 
		{
			this.txtDatefinIns.setBackground(Color.white);
		}
		
		try 
		{
			nbMax = Integer.parseInt(strNbrMax);
			this.txtNbMax.setBackground(Color.white);
		} catch (NumberFormatException nfe) 
		{
		  this.txtNbMax.setBackground(Color.red);
		  errorMessage += "Nombre max est requis \n";
		}
		//check type evenement
		if(tab.length !=2)
		{
			this.cbCodetypeE.setBackground(Color.red);
			errorMessage += "Code type évenement est requis \n";
		}
		else
		{
			codeTypeE= tab[0];
		}
		
		//check association
		if(tab1.length !=2)
		{
			this.cbIdAssociation.setBackground(Color.red);
			errorMessage += "Id association est requis \n";
		}
		else
		{
			idAssoc= Integer.parseInt(tab[0]);
		}
		
		if(errorMessage != "")
		{
			JOptionPane.showMessageDialog(this, "Erreur de saisir: \n" + errorMessage);
			
			return null;
		}
		
		//*************************************************
		
		
		
		
		

		if(! nomE.equals("") 
				&& ! contenuE.equals("") 
				&& ! adresseE.equals("") 
				&& ! contenuE.equals("") 
				&& ! dateDebut.equals("")
				&& ! dateFin.equals("") 
				&& ! dateFinInscription.equals("")
				 )
		{
			uneEve = new Evenement(nomE,contenuE,adresseE,dateDebut,dateFin,dateFinInscription,nbMax,
					codeTypeE,	idAssoc	);
		}
		else 
		{
			return null;
		}
		return uneEve;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==this.btAnnuler)
		{
			this.viderChamps();	
		}
		else if (e.getSource()==this.btEnregistrer && e.getActionCommand().equals("Enregistrer"))
		{
			Evenement uneEve = this.saisirEve();
			
			if(uneEve == null)
				return;
				
				modele.insertEvenement(uneEve);
				
				//RECUPERER id auto_increment de la bdd
				uneEve =modele.selectWhereEve(uneEve.getNomE(), uneEve.getContenuE(), uneEve.getAdresseE(),
						uneEve.getDateDebut(), uneEve.getDateFin(), uneEve.getDateFinInscription(),
						uneEve.getNbMax(), uneEve.getCodeTypeE(), uneEve.getIdAssoc());
				
			
				
				//mettre a jour l'affichage
				Object ligne[]= {uneEve.getIdE(),uneEve.getNomE(), uneEve.getContenuE(), uneEve.getAdresseE(),
						uneEve.getDateDebut(), uneEve.getDateFin(), uneEve.getDateFinInscription(),
						uneEve.getNbMax(), uneEve.getCodeTypeE(), uneEve.getIdAssoc()};
													
				unTableau.ajouterLigne(ligne);
	
				JOptionPane.showMessageDialog(this, "Insertion Reussie");
				this.viderChamps();				
		}
		
	else if (e.getSource()==this.btEnregistrer && e.getActionCommand().equals("Modifier"))
	{
	
		Evenement uneEve = this.saisirEve();
		
		JOptionPane.showMessageDialog(this, "Modification effectuee");
		int numLigne =this.uneTable.getSelectedRow();
		
		int idE = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
		uneEve.setIdE(idE);
		
		Object ligne[]= 
		{
			uneEve.getIdE(),uneEve.getNomE(), uneEve.getContenuE(), uneEve.getAdresseE(),
			uneEve.getDateDebut(), uneEve.getDateFin(), uneEve.getDateFinInscription(),
			uneEve.getNbMax(), uneEve.getCodeTypeE(), uneEve.getIdAssoc()
		};
		
		unTableau.modifierLigne(numLigne,ligne);
		modele.updateEve(uneEve);
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
