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

import Controleur.Tableau;
import Controleur.TypeEveEnfant;
import Modele.modele;

public class PanelTypeEveEnfant extends PanelDeBase implements ActionListener
{
	private JPanel panelForm = new JPanel();
	private JPanel panelTable = new JPanel();
	private JButton btEnregistrer= new JButton("Enregistrer");
	private JButton btAnnuler= new JButton("Annuler");
	private JTextField txtCodeType= new JTextField();
	private JTextField txtNomtype= new JTextField();
	private JTextField txtAgeMin= new JTextField();
	private JTextField txtAgeMax= new JTextField();
	//private JTextField txtAccompagant= new JTextField();
	private JComboBox<String> cbAccompagnant= new JComboBox<String>();
	//zone de recherche - par requeque like
	private JTextField txtMot = new JTextField();
	private JButton btRechercher= new JButton("Rechercher");
	
	private static Tableau unTableau =null;
	private JTable uneTable=null;
	
	public PanelTypeEveEnfant()
	{
		super(new Color(163, 222, 142));
		this.panelForm.setLayout(new GridLayout(6,2));
		this.panelForm.setBackground(Color.white);	
		
		this.panelForm.add(new JLabel(" Code type"));
		this.panelForm.add(this.txtCodeType);
		
		this.panelForm.add(new JLabel(" Nom"));
		this.panelForm.add(this.txtNomtype);
		
		this.panelForm.add(new JLabel(" Age-min"));
		this.panelForm.add(this.txtAgeMin);
		
		this.panelForm.add(new JLabel(" Age-max"));
		this.panelForm.add(this.txtAgeMax);
		
		this.panelForm.add(new JLabel(" Accompagant"));
		this.panelForm.add(this.cbAccompagnant);
		

		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btEnregistrer);
		
		this.panelForm.setBounds(20,20,300,150);
		this.add(this.panelForm);
		
		//combobox accompagant
		
		this.cbAccompagnant.addItem("oui");
		this.cbAccompagnant.addItem("non");
		this.cbAccompagnant.addItem("");
		
		
		//construction du panel Table
		this.panelTable.setBounds(360,20,500,320);
		this.panelTable.setBackground(new Color(163, 222, 142));
		this.panelTable.setLayout(null);
		
		//nom de colonne : entete
		
		String entetes[] = {"CodeTypeE","Nom","AgeMin","AgeMax","Accompagant"};
		Object donnees [][] = this.getLesDonnees("");
		unTableau = new Tableau (entetes, donnees);
		
		this.uneTable= new JTable(unTableau);
		JScrollPane uneScroll = new JScrollPane(this.uneTable);
		uneScroll.setBounds(0,60,500,200);
		this.panelTable.add(uneScroll);
		
		//placement de la zone de recherche
		this.txtMot.setBounds(130,10,120,25);
		this.panelTable.add(this.txtMot);
		this.btRechercher.setBounds(270,10,120,25);
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
					int retour =JOptionPane.showConfirmDialog(null, "Vous voulez suppimer ce type ?",
							"Suprresion type evénement enfant", JOptionPane.YES_NO_OPTION);
					if (retour == 0)
					{
						//suppresion du type eve enfant
						String codeTypeE = unTableau.getValueAt(numLigne, 0).toString();
						modele.deletetypeEveEnfant(codeTypeE);
						
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
					
					String ageMin=unTableau.getValueAt(numLigne1,2).toString();
					txtAgeMin.setText(ageMin);
										
					String ageMax = uneTable.getValueAt(numLigne1,3).toString();
					txtAgeMax.setText(ageMax);
					
					String accompagnant = uneTable.getValueAt(numLigne1,4).toString();
					cbAccompagnant.setSelectedItem(accompagnant);

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
		ArrayList<TypeEveEnfant> lesTypeEveEnfants= null;
		if(mot.equals(""))
			{
			lesTypeEveEnfants=modele.selectAllTypeEveEnfant();
			}
		else
		{
			lesTypeEveEnfants=modele.selectLikeTypeEveEnfant(mot);
		}
		//create matrice empty 
		//size() nb ligne
		Object [][] matrice = new Object[lesTypeEveEnfants.size()][5];
		int i=0;
		
		for(TypeEveEnfant uneTypeEveEnfant : lesTypeEveEnfants)
		{
			matrice[i][0]=uneTypeEveEnfant.getCodeTypeE();
			matrice[i][1]=uneTypeEveEnfant.getNomTypeE();
			matrice[i][2]=uneTypeEveEnfant.getAgeMin();
			matrice[i][3]=uneTypeEveEnfant.getAgeMax();
			matrice[i][4]=uneTypeEveEnfant.isAccompagnant();
			i++;
		}
		return matrice;
	}
	
	public void viderChamps()
	{
		this.txtCodeType.setText("");
		this.txtNomtype.setText("");
		this.txtAgeMin.setText("");
		this.txtAgeMax.setText("");
		this.cbAccompagnant.setSelectedItem("");
	}
	
	public TypeEveEnfant saisirtypeEveEnfant()
	{
		TypeEveEnfant uneTypeEveEnfant=null;
		String errorMessage = "";
		String codeTypeE =this.txtCodeType.getText();
		String nomTypeE =this.txtNomtype.getText();
		String txtAgeMin =this.txtAgeMin.getText();
		String txtAgeMax =this.txtAgeMax.getText();
		Integer ageMin = 0;
		Integer ageMax = 0;
		boolean accompagnant = false;
		
		String txtAccompagant = this.cbAccompagnant.getSelectedItem().toString();
		
		if(txtAccompagant.equals(""))
		{
			this.txtCodeType.setBackground(Color.red);
			errorMessage += "Info accompagnant est requis \n";
		}
		else
		{
			if(txtAccompagant == "oui")
			{
				accompagnant = true;
			}
			else
			{
				accompagnant = false;
			}
		}
		
		//int ageMin =Integer.parseInt(this.txtAgeMin.getText());
		//int ageMax =Integer.parseInt(this.txtAgeMax.getText());
		//boolean accompagnant = Boolean.parseBoolean(this.txtAccompagant.getText());
		
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
			errorMessage += "Nom type est requis \n";
		}
		else 
		{
			this.txtNomtype.setBackground(Color.white);
		}
		
		//check age min
		try 
		{
			ageMin = Integer.parseInt(txtAgeMin);
			this.txtAgeMin.setBackground(Color.white);
		} catch (NumberFormatException nfe) 
		{
		  this.txtAgeMin.setBackground(Color.red);
		  errorMessage += "Age minimum est requis \n";
		}
		
		//check age max
		try 
		{
			ageMax = Integer.parseInt(txtAgeMax);
			this.txtAgeMax.setBackground(Color.white);
		} catch (NumberFormatException nfe) 
		{
		  this.txtAgeMax.setBackground(Color.red);
		  errorMessage += "Age maximum est requis \n";
		}
		
		
		
		
		if(errorMessage != "")
		{
			JOptionPane.showMessageDialog(this, "Erreur de saisir: \n" + errorMessage);
			
			return null;
		}

		//**********************************************

		//check type de boolean
		if(!codeTypeE.equals("") && !nomTypeE.equals("")
			&& ( accompagnant != true  || accompagnant != false)	)			
		{
			uneTypeEveEnfant = new TypeEveEnfant(codeTypeE,nomTypeE,ageMin,ageMax,accompagnant);
		}
		else 
		{
			return null;
		}
		return uneTypeEveEnfant;
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
			TypeEveEnfant uneTypeEveEnfant = this.saisirtypeEveEnfant();
			//check if object is null ne rien faire
			if(uneTypeEveEnfant == null)
				return;
			
			modele.insertTypeEveEnfant(uneTypeEveEnfant);
			
			
			//mettre a jour l'affichage
			Object ligne[]= {uneTypeEveEnfant.getCodeTypeE(),uneTypeEveEnfant.getNomTypeE(), uneTypeEveEnfant.getAgeMin(),
					uneTypeEveEnfant.getAgeMax(),uneTypeEveEnfant.isAccompagnant()};
												
			unTableau.ajouterLigne(ligne);

			JOptionPane.showMessageDialog(this, "Insertion Reussie");
			this.viderChamps();				
		}
		
	else if (e.getSource()==this.btEnregistrer && e.getActionCommand().equals("Modifier"))
	{
	
		TypeEveEnfant uneTypeEveEnfant = this.saisirtypeEveEnfant();
		if(uneTypeEveEnfant == null)
			return;
		
		JOptionPane.showMessageDialog(this, "Modification effectuee");
		int numLigne =this.uneTable.getSelectedRow();
		
		String codeTypeE = unTableau.getValueAt(numLigne, 0).toString();
		uneTypeEveEnfant.setCodeTypeE(codeTypeE);
		
		Object ligne[]= 
		{
			uneTypeEveEnfant.getCodeTypeE(),uneTypeEveEnfant.getNomTypeE(), uneTypeEveEnfant.getAgeMin(),
			uneTypeEveEnfant.getAgeMax(),uneTypeEveEnfant.isAccompagnant()
		};
		
		unTableau.modifierLigne(numLigne,ligne);
		modele.updateTypeEveEnfant(uneTypeEveEnfant);
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
