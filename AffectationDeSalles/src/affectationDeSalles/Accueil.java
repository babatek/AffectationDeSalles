package affectationDeSalles;



import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



@SuppressWarnings("serial")
public class Accueil extends JFrame implements ActionListener{
	
	private JPanel container = new JPanel();
	private JButton checking = new JButton("Checking ! ");
	private JButton affectation = new JButton("Affectation");
	private static Accueil accueil = new Accueil();

	public void actionPerformed(ActionEvent e)
	{
	
	}

	public Accueil(){
	
		this.setTitle("Accueil");
		this.setMinimumSize(new Dimension(320, 320));
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
  
		container.setBackground(Color.white);
		container.setLayout(null);
  
		checking.setBounds(85, 60, 150, 30);
		container.add(checking);
  
		checking.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Checking checking = new Checking();
				checking.affiche();
				dispose();
			}
		}
		);
  
		affectation.setBounds(85, 160, 150, 30);
		container.add(affectation);
  
		affectation.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Identification identification = new Identification();
				identification.affiche();
				dispose();
			}
		}
		);
  
		this.setContentPane(container);            
	}

	public void affiche()
	{
		setVisible(true);
	}

	public static void main(String[] args)
	{
		accueil.affiche();
	}
}
