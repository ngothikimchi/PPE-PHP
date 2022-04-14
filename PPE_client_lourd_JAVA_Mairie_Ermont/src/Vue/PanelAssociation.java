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

import Controleur.Association;
import Controleur.Citoyen;
import Controleur.Tableau;
import Modele.modele;

public class PanelAssociation extends PanelDeBase implements ActionListener{
	private JPanel panelForm = new JPanel();
	private JPanel panelTable = new JPanel();
	private JButton btEnregistrer= new JButton("Enregistrer");
	private JButton btAnnuler= new JButton("Annuler");
	private JTextField txtNom= new JTextField();
	private JTextField txtAdresse= new JTextField();
	private JTextField txtCP= new JTextField();
	private JTextField txtVille= new JTextField();
	private JTextField txtTel= new JTextField();
	
	//zone de recherche - par requeque like
	private JTextField txtMot = new JTextField();
	private JButton btRechercher= new JButton("Rechercher");
	
	private static Tableau unTableau =null;
	private JTable uneTable=null;
		
		
		public PanelAssociation()
		{
			super(new Color(195, 232, 199));
			this.panelForm.setLayout(new GridLayout(6,2));
			this.panelForm.setBackground(Color.white);
			this.panelForm.add(new JLabel(" Nom"));
			this.panelForm.add(this.txtNom);
			
			this.panelForm.add(new JLabel(" Adresse"));
			this.panelForm.add(this.txtAdresse);
			
			this.panelForm.add(new JLabel(" Code Postal"));
			this.panelForm.add(this.txtCP);
			
			this.panelForm.add(new JLabel(" Ville"));
			this.panelForm.add(this.txtVille);
			
			this.panelForm.add(new JLabel(" Téléphone"));
			this.panelForm.add(this.txtTel);

			this.panelForm.add(this.btAnnuler);
			this.panelForm.add(this.btEnregistrer);
			
			this.panelForm.setBounds(20,20,300,200);
			this.add(this.panelForm);
			
			
			//construction du panel Table
			this.panelTable.setBounds(360,20,550,320);
			this.panelTable.setBackground(new Color(195, 232, 199));
			this.panelTable.setLayout(null);
			
			//nom de colonne : entete
			
			String entetes[] = {"ID Association","Nom","Adresse","CP","Ville","Téléphone"};
			Object donnees [][] = this.getLesDonnees("");
			unTableau = new Tableau (entetes, donnees);
			
			this.uneTable= new JTable(unTableau);
			JScrollPane uneScroll = new JScrollPane(this.uneTable);
			uneScroll.setBounds(0,60,550,200);
			this.panelTable.add(uneScroll);
			
			
			//placement de la zone de recherche
			this.txtMot.setBounds(150,10,120,25);
			this.panelTable.add(this.txtMot);
			this.btRechercher.setBounds(290,10,120,25);
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
						int retour =JOptionPane.showConfirmDialog(null, "Vous voulez suppimer cet association ?",
								"Suprresion association", JOptionPane.YES_NO_OPTION);
						if (retour == 0)
						{
							//suppresion du citoyen
							int idassoc = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
							modele.deleteAssociation(idassoc);
							
							//actualiser l'affichage
							unTableau.supprimerLigne(numLigne);
						}
						
					}
					else if(nbclic==1)
					{
						int numLigne1=uneTable.getSelectedRow();
						
						String nom = uneTable.getValueAt(numLigne1,1).toString();
						txtNom.setText(nom);
						
						String adresse = uneTable.getValueAt(numLigne1,2).toString();
						txtAdresse.setText(adresse);
						
						String cp = uneTable.getValueAt(numLigne1,3).toString();
						txtCP.setText(cp);
						
						String ville = uneTable.getValueAt(numLigne1,4).toString();
						txtVille.setText(ville);
						
						String tel = uneTable.getValueAt(numLigne1,5).toString();
						txtTel.setText(tel);
						
						
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
			ArrayList<Association> lesAssocs= null;
			if(mot.equals(""))
				{
				lesAssocs=modele.selectAllAssociations();
				}
			else
			{
				lesAssocs=modele.selectLikeAssociation(mot);
			}
			//create matrice empty 
			//size() nb ligne
			Object [][] matrice = new Object[lesAssocs.size()][6];
			int i=0;
			
			for(Association uneAssoc : lesAssocs)
			{
				matrice[i][0]=uneAssoc.getIdassoc();
				matrice[i][1]=uneAssoc.getNom();
				matrice[i][2]=uneAssoc.getAdresse();
				matrice[i][3]=uneAssoc.getCp();
				matrice[i][4]=uneAssoc.getVille();
				matrice[i][5]=uneAssoc.getTel();
				i++;
			}
			return matrice;
		}
		
		public void viderChamps()
		{
			this.txtNom.setText("");
			this.txtAdresse.setText("");
			this.txtCP.setText("");
			this.txtVille.setText("");
			this.txtTel.setText("");
		}
		
		public Association saisirAssoc()
		{
			Association uneAssoc=null;
			String errorMessage = "";
			String nom =this.txtNom.getText();
			String adresse =this.txtAdresse.getText();
			String cp =this.txtCP.getText();
			String ville =this.txtVille.getText();
			String tel =this.txtTel.getText();
			
			//check sécurisé **************************************************************************
			//check nom associastion
			if(nom.equals(""))
			{
				this.txtNom.setBackground(Color.red);
				errorMessage += "Nom association est requis \n";
			}
			else {
				this.txtNom.setBackground(Color.white);			
			}
			//check adresse
			if(adresse.equals(""))
			{
				this.txtAdresse.setBackground(Color.red);
				errorMessage += "Adresse est requis \n";
				
			}
			else 
			{
				
				this.txtAdresse.setBackground(Color.white);
			}
			//check code postal
			if(cp.equals(""))
			{
				this.txtCP.setBackground(Color.red);
				errorMessage += "Code postal est requis \n";
			}
			else 
			{
				this.txtCP.setBackground(Color.white);
			}
			
			//check ville
			if(ville.equals(""))
			{
				this.txtVille.setBackground(Color.red);
				errorMessage += "Nom de la ville est requis \n";
			}
			else 
			{
				this.txtVille.setBackground(Color.white);
			}
			
			//check tel
			if(tel.equals(""))
			{
				this.txtTel.setBackground(Color.red);
				errorMessage += "Numéeo de téléphone est requis \n";
			}
			else 
			{
				this.txtTel.setBackground(Color.white);
			}
			
			if(errorMessage != "")
			{
				JOptionPane.showMessageDialog(this, "Erreur de saisir: \n" + errorMessage);
				
				return null;
			}

			//fin check -------------**********************************************************		
			

			if(! nom.equals("") 
					&& ! adresse.equals("") 
					&& ! cp.equals("") 
					&& ! ville.equals("")
					&& ! tel.equals("") )
			{
				uneAssoc = new Association (nom
						, adresse
						, cp
						, ville
						, tel);
			}
			else 
			{
				return null;
			}
			return uneAssoc;
		}



		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==this.btAnnuler)
			{
				this.viderChamps();	
			}
			else if (e.getSource()==this.btEnregistrer && e.getActionCommand().equals("Enregistrer"))
			{
					Association uneAssoc = this.saisirAssoc();
					if(uneAssoc == null)
						return;
					
					modele.insertAssociation(uneAssoc);
					
					//RECUPERER id auto_increment de la bdd
					uneAssoc =modele.selectWhereAssoc(uneAssoc.getNom(), uneAssoc.getAdresse(), uneAssoc.getCp(),
							uneAssoc.getVille(), uneAssoc.getTel());
					
				
					
					//mettre a jour l'affichage
					Object ligne[]= {uneAssoc.getIdassoc(),uneAssoc.getNom(), uneAssoc.getAdresse(), uneAssoc.getCp(),
							uneAssoc.getVille(), uneAssoc.getTel()};
														
					unTableau.ajouterLigne(ligne);
		
					JOptionPane.showMessageDialog(this, "Insertion Reussie");
					this.viderChamps();				
				}
			
		else if (e.getSource()==this.btEnregistrer && e.getActionCommand().equals("Modifier"))
		{
		
			Association uneAssoc = this.saisirAssoc();
			
			JOptionPane.showMessageDialog(this, "Modification effectuee");
			int numLigne =this.uneTable.getSelectedRow();
			
			int idassoc = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
			uneAssoc.setIdassoc(idassoc);
			
			Object ligne[]= 
			{
				uneAssoc.getIdassoc(),uneAssoc.getNom(), uneAssoc.getAdresse(), uneAssoc.getCp(),
				uneAssoc.getVille(), uneAssoc.getTel()
			};
			
			unTableau.modifierLigne(numLigne,ligne);
			modele.updateAssociation(uneAssoc);
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
