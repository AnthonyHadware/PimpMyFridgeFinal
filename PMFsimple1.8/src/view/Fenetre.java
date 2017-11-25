package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import org.jfree.chart.ChartPanel;
import controller.Regulation;
import model.DataArduino;
import java.awt.CardLayout;

public class Fenetre implements ActionListener,Observateur{

	private JFrame frame;
	private LineChart chart;
	private Regulation regul;
	private JLabel DonneeTempInt;
	private JLabel consigneLabel;
	private JLabel DonneeHumidite;
	private JLabel DonneeTempExt;
	private JLabel lblTemperatureActuelle;
	private JLabel lblConsigne;
	private JLabel lblTempratureExterieure;
	private JLabel lblHumidite;
	private JLabel texterosee;
	private JLabel donneerosee;

	/**
	 * Crée une instance de initialize qui crée l'interface
	 */
	
	public Fenetre(Regulation regul) {
		initialize();
		this.regul = regul;
		frame.setVisible(true);
	}

	/**
	 * Initialise le contenu de la fenetre de l'interface
	 */
	
	private void initialize() {
		frame = new JFrame("Projet Pimp My Fridge");
		frame.setPreferredSize(new Dimension(1000,1150));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		JLabel image = new JLabel("");
		image.setIcon(new ImageIcon(Fenetre.class.getResource("/view/logo.png")));
		
		
		JPanel panel = new JPanel();
		chart = new LineChart(
				"Courbe des températures" ,
				"Température extérieure et intérieure");
		panel.setLayout(new CardLayout(0, 0));

		ChartPanel component = new ChartPanel(chart.getJChart());
		panel.add(component, "name_318427173349897");
		
		
		JButton BoutonMoins = new JButton("-");
		BoutonMoins.setBackground(Color.RED);
		BoutonMoins.addActionListener(this);
		
		JButton BoutonPlus = new JButton("+");
		BoutonPlus.setBackground(Color.GREEN);
		BoutonPlus.addActionListener(this);
		
		DonneeTempInt = new JLabel("0\u00B0C");
		DonneeTempInt.setFont(new Font("Comic Sans MS", Font.PLAIN, 17));
		DonneeTempInt.setHorizontalAlignment(SwingConstants.CENTER);
		DonneeTempInt.setForeground(new Color(0, 0, 0));
		
		lblTemperatureActuelle = new JLabel("Temperature actuelle :");
		lblTemperatureActuelle.setForeground(new Color(0, 0, 0));
		lblTemperatureActuelle.setFont(new Font("Comic Sans MS", Font.PLAIN, 17));
		
		consigneLabel = new JLabel("0\u00B0C");
		consigneLabel.setForeground(new Color(240, 128, 128));
		consigneLabel.setFont(new Font("Impact", Font.PLAIN, 46));
		consigneLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblConsigne = new JLabel("CONSIGNE");
		lblConsigne.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsigne.setForeground(new Color(240, 128, 128));
		lblConsigne.setFont(new Font("Impact", Font.PLAIN, 18));
				
		lblTempratureExterieure = new JLabel("Temperature exterieure :");
		lblTempratureExterieure.setForeground(new Color(0, 0, 0));
		lblTempratureExterieure.setFont(new Font("Comic Sans MS", Font.PLAIN, 17));
		
		DonneeTempExt = new JLabel("0 \u00B0C");
		DonneeTempExt.setForeground(new Color(0, 0, 0));
		DonneeTempExt.setHorizontalAlignment(SwingConstants.CENTER);
		DonneeTempExt.setFont(new Font("Comic Sans MS", Font.PLAIN, 17));
		
		lblHumidite = new JLabel("Taux d'humidité :");
		lblHumidite.setForeground(new Color(0, 0, 0));
		lblHumidite.setFont(new Font("Comic Sans MS", Font.PLAIN, 17));
		
		DonneeHumidite = new JLabel("0%");
		DonneeHumidite.setForeground(new Color(0, 0, 0));
		DonneeHumidite.setHorizontalAlignment(SwingConstants.CENTER);
		DonneeHumidite.setFont(new Font("Comic Sans MS", Font.PLAIN, 17));
		
		texterosee = new JLabel("Température de rosée :");
		texterosee.setForeground(new Color(0, 0, 0));
		texterosee.setHorizontalAlignment(SwingConstants.CENTER);
		texterosee.setFont(new Font("Comic Sans MS", Font.PLAIN, 17));
		
		donneerosee = new JLabel("0 \\u00B0C");
		donneerosee.setForeground(new Color(200, 0, 0));
		donneerosee.setHorizontalAlignment(SwingConstants.CENTER);
		donneerosee.setFont(new Font("Comic Sans MS", Font.PLAIN, 17));

		
	JPanel c1 = new JPanel();
	c1.setLayout(new BoxLayout(c1, BoxLayout.PAGE_AXIS));
	
	c1.add(consigneLabel);
	c1.add(lblConsigne);
	c1.add(lblTempratureExterieure);
	c1.add(lblTemperatureActuelle);
	c1.add(lblHumidite);
	c1.add(texterosee);
	
	JPanel c2 = new JPanel();
	c2.setLayout(new BoxLayout(c2, BoxLayout.PAGE_AXIS));
	
	c2.add(BoutonPlus);
	c2.add(BoutonMoins);
	c2.add(DonneeTempExt);
	c2.add(DonneeTempInt);
	c2.add(DonneeHumidite);
	c2.add(donneerosee);
	
	JPanel c = new JPanel();
	c.setLayout(new BoxLayout(c, BoxLayout.LINE_AXIS));
	
	c.add(c1);
	c.add(c2);
	
	JPanel a = new JPanel();
	a.setLayout(new BoxLayout(a, BoxLayout.PAGE_AXIS));
	
	a.add(image);
	a.add(component);
	a.add(c);
	
	
	frame.add(a);
	frame.pack();
	
	}

	private void afficherNouvelleDonnees(float h, float in, float out) {
		DonneeTempExt.setText(out + "°C");
		DonneeTempInt.setText(in + "°C");
		DonneeHumidite.setText(h + "%");
		double humrosee = h / 100;
		double tempRosee = Math.pow(humrosee , 1.0/8) * (112 + (0.9*in)) + (0.1*in) - 112;
		donneerosee.setText(tempRosee + "°C");
		consigneLabel.setText(regul.getTempConsigne() + "°C");
		
		chart.addData(in,out);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.err.println("Commande reçue");
		
		if (arg0.getActionCommand() == "+") {
			regul.setTempConsigne(regul.getTempConsigne() + 1);
		}
		else
		{
			regul.setTempConsigne(regul.getTempConsigne() - 1);
		}
		
		consigneLabel.setText(regul.getTempConsigne() + "°C");
		
	}

	@Override
	public void afficherNotification(DataArduino data) {
		afficherNouvelleDonnees(data.getH(), data.getInte(), data.getExt());
	}
}
