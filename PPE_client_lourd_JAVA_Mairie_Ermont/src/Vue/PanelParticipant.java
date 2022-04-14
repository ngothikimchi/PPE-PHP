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
import Controleur.Evenement;
import Controleur.Participer;
import Controleur.Tableau;
import Modele.modele;


public class PanelParticipant extends PanelDeBase implements ActionListener
{
	private JPanel panelForm = new JPanel();
	private JPanel panelTable = new JPanel();
	private JButton btEnregistrer= new JButton("Enregistrer");
	private JButton btAnnuler= new JButton("Annuler");
	private JComboBox<String> cbxIdCitoyen = new JComboBox<String>(); 
	private JComboBox<String> cbxIdEvenement = new JComboBox<String>(); 
	private JTextField txtDatedemande= new JTextField();
	
	//zone de recherche - par requeque like
	private JTextField txtMot = new JTextField();
	private JButton btRechercher= new JButton("Rechercher");
	
	private static Tableau unTableau =null;
	private JTable uneTable=null;
	
	public PanelParticipant()
	{
		super(new Color(163, 222, 142));		
		this.panelForm.setBackground(Color.white);	
		this.panelForm.setBounds(20, 20, 300,120);
		this.panelForm.setLayout(new GridLayout(4,2));
		this.panelForm.add(new JLabel(" Id citoyen : ")); 
		this.panelForm.add(this.cbxIdCitoyen);
		this.panelForm.add(new JLabel(" Id evenement : ")); 
		this.panelForm.add(this.cbxIdEvenement);
		this.panelForm.add(new JLabel(" Date demande : ")); 
		this.panelForm.add(this.txtDatedemande);
		this.panelForm.add(this.btAnnuler); 
		this.panelForm.add(this.btEnregistrer);
		
		this.add(this.panelForm);
		
		this.remplirCBX ();
		// rendre les boutons ecoutables
		
		this.btEnregistrer.addActionListener(this);
		this.btAnnuler.addActionListener(this);
		this.btRechercher.addActionListener(this);
		
		//construction du panel Table
		this.panelTable.setBounds(420,20,440,320);
		this.panelTable.setBackground(new Color(163, 222, 142));
		this.panelTable.setLayout(null);
		
		//nom de colonne : entete
		
		String entetes[] = {"ID citoyen","Id evenement ","Date demande"};
		Object donnees [][] = this.getLesDonnees("");
		unTableau = new Tableau (entetes, donnees);
		
		this.uneTable= new JTable(unTableau);
		JScrollPane uneScroll = new JScrollPane(this.uneTable);
		uneScroll.setBounds(0,60,440,200);
		this.panelTable.add(uneScroll);
		
		//placement de la zone de recherche
		this.txtMot.setBounds(70,10,120,25);
		this.panelTable.add(this.txtMot);
		this.btRechercher.setBounds(210,10,120,25);
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
					int retour =JOptionPane.showConfirmDialog(null, "Vous voulez suppimer cet participant?",
							"Suprresion participant", JOptionPane.YES_NO_OPTION);
					if (retour == 0)
					{
						//suppresion du citoyen
						int idcitoyen = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
						int idE = Integer.parseInt(unTableau.getValueAt(numLigne, 1).toString());
						modele.deleteParticipant(idcitoyen,idE);
						
						//actualiser l'affichage
						unTableau.supprimerLigne(numLigne);
					}
					
				}
				else if(nbclic==1)
				{
					int numLigne1=uneTable.getSelectedRow();
					
					String idcitoyen = uneTable.getValueAt(numLigne1,0).toString();
					String nomCitoyen = "";
					ArrayList<Citoyen> lesCitoyens = modele.selectAllCitoyens();
					for (Citoyen unCitoyen :lesCitoyens)
					{	
						if(String.valueOf(unCitoyen.getIdcitoyen()) == idcitoyen)
						{
							nomCitoyen = unCitoyen.getNom();
							break;
						}
					}
					
					cbxIdCitoyen.setSelectedItem(idcitoyen + "-" + nomCitoyen);
					
					String idE = uneTable.getValueAt(numLigne1,1).toString();
					String nomE = "";
					ArrayList<Evenement> lesEves = modele.selectAllEvenements();
					for (Evenement unEve :lesEves)
					{	
						if(String.valueOf(unEve.getIdE()) == idE)
						{
							nomE = unEve.getNomE();
							break;
						}
					}
					
					
					cbxIdEvenement.setSelectedItem(idE + "-" + nomE);
					
					String dateDemande = uneTable.getValueAt(numLigne1,3).toString();
					txtDatedemande.setText(dateDemande);
				
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
		ArrayList<Participer> lesParticipants= null;
		if(mot.equals(""))
			{
			lesParticipants=modele.selectAllParticipants();
			}
		else
		{
			lesParticipants=modele.selectLikeParticipant(mot);
		}
		//create matrice empty 
		//size() nb ligne
		Object [][] matrice = new Object[lesParticipants.size()][3];
		int i=0;
		
		for(Participer unParticipant : lesParticipants)
		{
			matrice[i][0]=unParticipant.getIdcitoyen();
			matrice[i][1]=unParticipant.getIdE();
			matrice[i][2]=unParticipant.getDateDemande();
			i++;
		}
		return matrice;
	}
	
	public void viderChamps()
	{
		this.cbxIdCitoyen.setSelectedItem("");
		this.cbxIdEvenement.setSelectedItem("");
		this.txtDatedemande.setText("");
		
	}
	public void remplirCBX()
	{
		this.cbxIdCitoyen.addItem("");
		ArrayList<Citoyen> lesCitoyens =modele.selectAllCitoyens();
		for (Citoyen unCitoyen :lesCitoyens)
		{
			this.cbxIdCitoyen.addItem(unCitoyen.getIdcitoyen()+"-"+unCitoyen.getNom());
		}
		
		this.cbxIdEvenement.addItem("");
		ArrayList<Evenement> lesEves =modele.selectAllEvenements();
		for (Evenement uneEve: lesEves)
		{
			this.cbxIdEvenement.addItem(uneEve.getIdE()+"-"+uneEve.getNomE());
		}
		
	}
	
	public Participer saisirParticipant()
	{
		Participer unParticipant=null;
		String errorMessage = "";
		String tab[] = this.cbxIdCitoyen.getSelectedItem().toString().split("-");
		int idcitoyen = 0; 
		String tab1[] = this.cbxIdEvenement.getSelectedItem().toString().split("-");
		int idE = 0; 
		String dateDemande =this.txtDatedemande.getText();
		
		if(dateDemande.equals(""))
		{
			this.txtDatedemande.setBackground(Color.red);
			errorMessage += "Date de demande est requis \n";
		}
		else {
			this.txtDatedemande.setBackground(Color.white);			
		}
		
		//check idcitoyen
		if(tab.length !=2)
		{
			this.cbxIdCitoyen.setBackground(Color.red);
			errorMessage += "Citoyen est requis \n";
		}
		else
		{
			idcitoyen = Integer.parseInt(tab[0]);
		}
		//check evenement
		if(tab1.length !=2)
		{
			this.cbxIdEvenement.setBackground(Color.red);
			errorMessage += "évenement est requis \n";
		}
		else
		{
			idE = Integer.parseInt(tab1[0]);
		}
		if(errorMessage != "")
		{
			JOptionPane.showMessageDialog(this, "Erreur de saisir: \n" + errorMessage);
			
			return null;
		}
		
		//******************fin check

		if(! dateDemande.equals("") 
				 )
		{
			unParticipant = new Participer(idcitoyen,idE,dateDemande);
		}
		else 
		{
			return null;
		}
		return unParticipant;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btAnnuler)
		{
			this.viderChamps();	
		}
		else if (e.getSource()==this.btEnregistrer && e.getActionCommand().equals("Enregistrer"))
		{
			Participer unParticipant = this.saisirParticipant();
			if(unParticipant == null)
				return;
			
				modele.insertParticipant(unParticipant);
				
				//mettre a jour l'affichage
				Object ligne[]= {unParticipant.getIdcitoyen(), unParticipant.getIdE(), unParticipant.getDateDemande()};
													
				unTableau.ajouterLigne(ligne);
	
				JOptionPane.showMessageDialog(this, "Insertion Reussie");
				this.viderChamps();				
			}
		
	else if (e.getSource()==this.btEnregistrer && e.getActionCommand().equals("Modifier"))
	{
	
		Participer unParticipant = this.saisirParticipant();
		
		JOptionPane.showMessageDialog(this, "Modification effectuee");
		int numLigne =this.uneTable.getSelectedRow();
		
		int idcitoyen = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
		unParticipant.setIdcitoyen(idcitoyen);
		
		int idE = Integer.parseInt(unTableau.getValueAt(numLigne, 1).toString());
		unParticipant.setIdcitoyen(idE);
		
		
		Object ligne[]= 
		{
			unParticipant.getIdcitoyen(), unParticipant.getIdE(), unParticipant.getDateDemande()
		};
		
		unTableau.modifierLigne(numLigne,ligne);
		modele.updateParticipant(unParticipant);
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
