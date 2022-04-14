package Vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class PanelTypeEve extends PanelDeBase implements ActionListener
{
	private JButton btTypeEveEnfant= new JButton("G- Type Evenement Enfant");
	private JButton btTypeEveAdulte= new JButton("G- Type Evenement Adulte");
	private PanelTypeEveEnfant unPanelTypeEveEnfant= new PanelTypeEveEnfant();
	private PanelTypeEveAdulte unPanelTypeEveAdulte= new PanelTypeEveAdulte();
	
		public PanelTypeEve ()
		{
			super(new Color(163, 222, 142));
			this.btTypeEveEnfant.setBounds(250,30,200,30);
			this.btTypeEveEnfant.setBackground(new Color(243, 240, 215));
			this.btTypeEveEnfant.setFont(new Font("Verdana", Font.BOLD, 12));
			
			this.btTypeEveAdulte.setBounds(480,30,200,30);
			this.btTypeEveAdulte.setBackground(new Color(243, 240, 215));
			this.btTypeEveAdulte.setFont(new Font("Verdana", Font.BOLD, 12));
			
			this.add(this.btTypeEveEnfant);
			this.add(this.btTypeEveAdulte);
			
			this.add(this.unPanelTypeEveEnfant);
			this.add(this.unPanelTypeEveAdulte);
			
			unPanelTypeEveEnfant.setVisible(false);
			unPanelTypeEveAdulte.setVisible(false);
			
			
			this.btTypeEveEnfant.addActionListener(this);
			this.btTypeEveAdulte.addActionListener(this);
			
			
		}

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getSource() == this.btTypeEveEnfant)				
			{
				unPanelTypeEveEnfant.setVisible(true);
				unPanelTypeEveAdulte.setVisible(false);
			}
			else if(e.getSource() == this.btTypeEveAdulte)
			{
				unPanelTypeEveEnfant.setVisible(false);
				unPanelTypeEveAdulte.setVisible(true);
			}
			
		}
}
