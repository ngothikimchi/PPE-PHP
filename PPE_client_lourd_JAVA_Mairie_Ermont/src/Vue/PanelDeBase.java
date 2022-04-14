package Vue;

import java.awt.Color;

import javax.swing.JPanel;

public abstract class PanelDeBase extends JPanel
{
	
	public PanelDeBase(Color uneCouleur)
	{
		this.setBounds(20,70,950,360);
		this.setLayout(null);
		this.setBackground(uneCouleur);
		this.setVisible(false);
	}
	
	
}
