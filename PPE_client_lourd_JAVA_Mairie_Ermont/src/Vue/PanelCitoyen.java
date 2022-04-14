package Vue;

import java.awt.Color;
import java.awt.Dimension;
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
import Controleur.Tableau;
import Modele.modele;

public class PanelCitoyen extends PanelDeBase implements ActionListener
{
	private JPanel panelForm = new JPanel();
	private JPanel panelTable = new JPanel();
	private JButton btEnregistrer= new JButton("Enregistrer");
	private JButton btAnnuler= new JButton("Annuler");
	private JTextField txtNomC= new JTextField();
	private JTextField txtPrenomC= new JTextField();
	//private JTextField txtSexeC= new JTextField();
	private JComboBox<String> cbSexeC= new JComboBox<String>();
	private JTextField txtDateNaissance= new JTextField();
	private JTextField txtLieuDateNaissance= new JTextField();
	private JTextField txtCpLieuNaissance= new JTextField();
	private JTextField txtAdresseC= new JTextField();
	private JTextField txtvilleC= new JTextField();
	private JTextField txtCpC= new JTextField();
	//private JTextField txtSituation= new JTextField();
	private JComboBox<String> cbSituation= new JComboBox<String>();
	private JTextField txtEmail= new JTextField();
	private JComboBox<String> cbQuestion= new JComboBox<String>();
	private JTextField txtReponse= new JTextField();
	
	//zone de recherche - par requeque like
	private JTextField txtMot = new JTextField();
	private JButton btRechercher= new JButton("Rechercher");
	
	private static Tableau unTableau =null;
	private JTable uneTable=null;
	
	public PanelCitoyen() 
	{
		super(new Color(195, 232, 199));
		this.panelForm.setLayout(new GridLayout(14,2));
		this.panelForm.setBackground(Color.white);
		this.panelForm.add(new JLabel(" Nom"));
		this.panelForm.add(this.txtNomC);
		
		this.panelForm.add(new JLabel(" Prenom"));
		this.panelForm.add(this.txtPrenomC);
		
		this.panelForm.add(new JLabel(" Sexe"));
		this.panelForm.add(this.cbSexeC);
		
		this.panelForm.add(new JLabel(" Date de naissance"));
		this.panelForm.add(this.txtDateNaissance);
		
		this.panelForm.add(new JLabel(" Lieu de naissance"));
		this.panelForm.add(this.txtLieuDateNaissance);
		
		this.panelForm.add(new JLabel(" CP lieu de naissance"));
		this.panelForm.add(this.txtCpLieuNaissance);
		
		this.panelForm.add(new JLabel(" Adresse"));
		this.panelForm.add(this.txtAdresseC);
		
		this.panelForm.add(new JLabel(" Ville"));
		this.panelForm.add(this.txtvilleC);
		
		this.panelForm.add(new JLabel(" Code Postal"));
		this.panelForm.add(this.txtCpC);
		
		this.panelForm.add(new JLabel(" Situation familiale"));
		this.panelForm.add(this.cbSituation);
		
		
		this.panelForm.add(new JLabel(" Email"));
		this.panelForm.add(this.txtEmail);
		
		this.panelForm.add(new JLabel(" Question"));
		this.panelForm.add(this.cbQuestion);
		
		this.panelForm.add(new JLabel(" Reponse"));
		this.panelForm.add(this.txtReponse);
		
		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btEnregistrer);
		
		this.panelForm.setBounds(20,20,280,300);
		this.add(this.panelForm);
		
		
		//construction du panel Table - back ground table de données
		this.panelTable.setBounds(310,20,620,320);
		this.panelTable.setBackground(new Color(195, 232, 199));
		this.panelTable.setLayout(null);
		
		//Combobox-question
		
		this.cbQuestion.addItem("Ecole primaire");
		this.cbQuestion.addItem("Personnage historique");
		this.cbQuestion.addItem("Premiere amour");
		this.cbQuestion.addItem("Nom de jeune fille votre mère");
		this.cbQuestion.addItem("Professeur préféré");
		this.cbQuestion.addItem("");
		
		//Combobox-sitationfamille
		
		this.cbSituation.addItem("Célibataire");
		this.cbSituation.addItem("Marié(e)");
		this.cbSituation.addItem("Divorcé(e)");
		this.cbSituation.addItem("Pacsé(e)");
		this.cbSituation.addItem("");
	
		//Combobox-sitationfamille
		
		this.cbSexeC.addItem("Masculin");
		this.cbSexeC.addItem("Feminin");
		this.cbSexeC.addItem("");
		
		
		//nom de colonne : entete
		
		String entetes[] = {"ID Citoyen","Nom","Prenom","Sexe","DatedeNaissance","Lieu de naissance"
				,"cpLieuNaissCit","adresseCit","villeCit","cpCit","situationFamilialeCit","emailCit","question","reponse"};
		Object donnees [][] = this.getLesDonnees("");
		unTableau = new Tableau (entetes, donnees);
		
		this.uneTable= new JTable(unTableau);
		/*** Rendre la table scrollable horizontalement & verticallement ***/		
		uneTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane uneScroll = new JScrollPane(this.uneTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		uneScroll.setBounds(10,60,620,200);
		this.panelTable.add(uneScroll);
		/*******/
		
		//placement de la zone de recherche
		this.txtMot.setBounds(150,10,120,30);
		this.panelTable.add(this.txtMot);
		this.btRechercher.setBounds(290,10,120,30);
		this.panelTable.add(this.btRechercher);
		this.add(this.panelTable);
		
		//gestion de la JTable avec MouListener
		this.uneTable.addMouseListener(new MouseListener()
				{
 
					@Override
					public void mouseClicked(MouseEvent e) {
						int nbclic = e.getClickCount();
						if(nbclic==2)
						{
							int numLigne=uneTable.getSelectedRow();
							int retour =JOptionPane.showConfirmDialog(null, "Vous voulez suppimer ce citoyen ?",
									"Suprresion citoyen", JOptionPane.YES_NO_OPTION);
							if (retour == 0)
							{
								//suppresion du citoyen
								int idcitoyen = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
								modele.deleteCitoyen(idcitoyen);
								
								//actualiser l'affichage
								unTableau.supprimerLigne(numLigne);
							}
							
						}
						else if(nbclic==1)
						{
							int numLigne1=uneTable.getSelectedRow();
							
							String nom = uneTable.getValueAt(numLigne1,1).toString();
							txtNomC.setText(nom);
							
							String prenom = uneTable.getValueAt(numLigne1,2).toString();
							txtPrenomC.setText(prenom);
							
							String sexe = uneTable.getValueAt(numLigne1,3).toString();
							cbSexeC.setSelectedItem(sexe);
							
							String dateNaissance = uneTable.getValueAt(numLigne1,4).toString();
							txtDateNaissance.setText(dateNaissance);
							
							String lieuNaissance = uneTable.getValueAt(numLigne1,5).toString();
							txtLieuDateNaissance.setText(lieuNaissance);
							
							String cpNaissance = uneTable.getValueAt(numLigne1,6).toString();
							txtCpLieuNaissance.setText(cpNaissance);
							
							String adresse = uneTable.getValueAt(numLigne1,7).toString();
							txtAdresseC.setText(adresse);
							
							String ville = uneTable.getValueAt(numLigne1,8).toString();
							txtvilleC.setText(ville);
							
							String cp = uneTable.getValueAt(numLigne1,9).toString();
							txtCpC.setText(cp);
							
							String situation = uneTable.getValueAt(numLigne1,10).toString();
							cbSituation.setSelectedItem(situation);
							
							String email = uneTable.getValueAt(numLigne1,11).toString();
							txtEmail.setText(email);
							
							String question = uneTable.getValueAt(numLigne1,12).toString();
							cbQuestion.setSelectedItem(question);
							
							String reponse = uneTable.getValueAt(numLigne1,13).toString();
							txtReponse.setText(reponse);
														
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
		ArrayList<Citoyen> lesCitoyens= null;
		if(mot.equals(""))
			{
			lesCitoyens=modele.selectAllCitoyens();
			}
		else
		{
			lesCitoyens=modele.selectLikeCitoyen(mot);
		}
		//create matrice empty 
		//size() nb ligne
		Object [][] matrice = new Object[lesCitoyens.size()][14];
		int i=0;
		
		for(Citoyen unCitoyen : lesCitoyens)
		{
			matrice[i][0]=unCitoyen.getIdcitoyen();
			matrice[i][1]=unCitoyen.getNom();
			matrice[i][2]=unCitoyen.getPrenom();
			matrice[i][3]=unCitoyen.getSexe();
			matrice[i][4]=unCitoyen.getDateNaissance();
			matrice[i][5]=unCitoyen.getLieuNaissance();
			matrice[i][6]=unCitoyen.getCpNaissance();
			matrice[i][7]=unCitoyen.getAdresse();
			matrice[i][8]=unCitoyen.getVille();
			matrice[i][9]=unCitoyen.getCp();
			matrice[i][10]=unCitoyen.getSituation();
			matrice[i][11]=unCitoyen.getEmail();
			matrice[i][12]=unCitoyen.getQuestion();
			matrice[i][13]=unCitoyen.getReponse();
			i++;
		}
		return matrice;
	}
	public void viderChamps()
	{
		this.txtNomC.setText("");
		this.txtPrenomC.setText("");
		this.cbSexeC.setSelectedItem("");
		this.txtDateNaissance.setText("");
		this.txtLieuDateNaissance.setText("");
		this.txtCpLieuNaissance.setText("");
		this.txtAdresseC.setText("");
		this.txtvilleC.setText("");
		this.txtCpC.setText("");
		this.cbSituation.setSelectedItem("");
		this.txtEmail.setText("");
		this.cbQuestion.setSelectedItem("");
		this.txtReponse.setText("");
	}
	
	public Citoyen saisirCitoyen()
	{		
		String errorMessage = "";
		String nom =this.txtNomC.getText();
		String prenom =this.txtPrenomC.getText();
		String sexe =this.cbSexeC.getSelectedItem().toString();
		String dateNaissance =this.txtDateNaissance.getText();
		String lieuNaissance =this.txtLieuDateNaissance.getText();
		String cpNaissance =this.txtCpLieuNaissance.getText();
		String adresse =this.txtAdresseC.getText();
		String ville =this.txtvilleC.getText();
		String cp =this.txtCpC.getText();
		String situation =this.cbSituation.getSelectedItem().toString();
		String email =this.txtEmail.getText();
		String question = this.cbQuestion.getSelectedItem().toString();
		String reponse =this.txtReponse.getText();
		
		//check saisir
		if(nom.equals(""))
		{
			this.txtNomC.setBackground(Color.red);
			errorMessage += "Nom citoyen est requis \n";
		}
		else {
			this.txtNomC.setBackground(Color.white);			
		}
		//check prenom
		if(prenom.equals(""))
		{
			this.txtPrenomC.setBackground(Color.red);
			errorMessage += "Prénom citoyen est requis \n";
		}
		else 
		{	
			this.txtPrenomC.setBackground(Color.white);
		}
		//check sexe
		if(sexe.equals(""))
		{
			this.cbSexeC.setBackground(Color.red);
			errorMessage += "Sexe de citoyen est requis \n";
		}
	
		//check adresse
		if(adresse.equals(""))
		{
			this.txtAdresseC.setBackground(Color.red);
			errorMessage += "Adresse est requis \n";
		}
		else 
		{
			this.txtAdresseC.setBackground(Color.white);
		}
		
		//check date de naissance
		if(dateNaissance.equals(""))
		{
			this.txtDateNaissance.setBackground(Color.red);
			errorMessage += "Date de naissance est requis \n";
		}
		else 
		{
			this.txtDateNaissance.setBackground(Color.white);
		}
		
		//check CP de naissance
		if(cpNaissance.equals(""))
		{
			this.txtCpLieuNaissance.setBackground(Color.red);
			errorMessage += "CP de la ville de naissance est requis \n";
		}
		else 
		{
			this.txtCpLieuNaissance.setBackground(Color.white);
		}
		//check lieu de naissance
		if(lieuNaissance.equals(""))
		{
			this.txtLieuDateNaissance.setBackground(Color.red);
			errorMessage += "Lieu de naissance est requis \n";
		}
		else 
		{
			this.txtLieuDateNaissance.setBackground(Color.white);
		}
		//check ville
		if(ville.equals(""))
		{
			this.txtvilleC.setBackground(Color.red);
			errorMessage += "Nom de la ville est requis \n";
		}
		else 
		{
			this.txtvilleC.setBackground(Color.white);
		}
		//check code postal
		if(cp.equals(""))
		{
			this.txtCpC.setBackground(Color.red);
			errorMessage += "CP est requis \n";
		}
		else 
		{
			this.txtCpC.setBackground(Color.white);
		}
		//check situation familiale
		if(situation.equals(""))
		{
			this.cbSituation.setBackground(Color.red);
			errorMessage += "Situation de citoyen est requis \n";
		}
		
		//check email
		if(email.equals(""))
		{
			this.txtEmail.setBackground(Color.red);
			errorMessage += "Email est requis \n";
		}
		else 
		{
			this.txtEmail.setBackground(Color.white);
		}
		//check question
		if(question.equals(""))
		{
			this.cbQuestion.setBackground(Color.red);
			errorMessage += "Question secrète est requis \n";
		}
		//check reponse
		if(reponse.equals(""))
		{
			this.txtReponse.setBackground(Color.red);
			errorMessage += "Réponse est requis \n";
		}
		else 
		{
			this.txtReponse.setBackground(Color.white);
		}

		if(errorMessage != "")
		{
			JOptionPane.showMessageDialog(this, "Erreur de saisir: \n" + errorMessage);
			
			return null;
		}
		
		//fincheck -------------------*****************************************************

		return new Citoyen (nom
				, prenom
				, sexe
				, dateNaissance
				, lieuNaissance
				, cpNaissance
				, adresse
				, ville
				, cp
				, situation
				, email
				,question
				,reponse);
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
				Citoyen unCitoyen = this.saisirCitoyen();
				
				if(unCitoyen == null)
					return;
				
				modele.insertCitoyen(unCitoyen);
				
				//RECUPERER id auto_increment de la bdd
				unCitoyen =modele.selectWhereCitoyen(unCitoyen.getNom(), unCitoyen.getPrenom(), unCitoyen.getSexe(),
						unCitoyen.getDateNaissance(), unCitoyen.getLieuNaissance(), unCitoyen.getCpNaissance(), 
						unCitoyen.getAdresse(), unCitoyen.getVille(), unCitoyen.getCp(),unCitoyen.getSituation(), unCitoyen.getEmail(),
						unCitoyen.getQuestion(),unCitoyen.getReponse());
				
			
				
				//mettre a jour l'affichage
				Object ligne[]= {unCitoyen.getIdcitoyen(),unCitoyen.getNom(), unCitoyen.getPrenom(), unCitoyen.getSexe(),
						unCitoyen.getDateNaissance(), unCitoyen.getLieuNaissance(), unCitoyen.getCpNaissance(), 
						unCitoyen.getAdresse(), unCitoyen.getVille(), unCitoyen.getCp(),unCitoyen.getSituation(), unCitoyen.getEmail(),
						unCitoyen.getQuestion(),unCitoyen.getReponse()};
													
				unTableau.ajouterLigne(ligne);
	
				JOptionPane.showMessageDialog(this, "Insertion Reussie");
				this.viderChamps();				
			}
		
	else if (e.getSource()==this.btEnregistrer && e.getActionCommand().equals("Modifier"))
	{
	
		Citoyen unCitoyen = this.saisirCitoyen();		
		JOptionPane.showMessageDialog(this, "Modification effectuee");
		int numLigne =this.uneTable.getSelectedRow();
		
		int idcitoyen = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
		unCitoyen.setIdcitoyen(idcitoyen);
		
		Object ligne[]= 
		{
			unCitoyen.getIdcitoyen(),unCitoyen.getNom(), unCitoyen.getPrenom(), unCitoyen.getSexe(),
			unCitoyen.getDateNaissance(), unCitoyen.getLieuNaissance(), unCitoyen.getCpNaissance(), 
			unCitoyen.getAdresse(), unCitoyen.getVille(), unCitoyen.getCp(),unCitoyen.getSituation(), unCitoyen.getEmail(),
			unCitoyen.getQuestion(),unCitoyen.getReponse()
		};
		
		unTableau.modifierLigne(numLigne,ligne);
		modele.updateCitoyen(unCitoyen);
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
