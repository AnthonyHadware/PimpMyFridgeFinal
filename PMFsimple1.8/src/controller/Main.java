package controller;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel;

import model.SerialClass;
import view.Fenetre;

public class Main {

	public static void main(String[] ag) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				// Définition du LookAndFeel
				try {
					UIManager.setLookAndFeel(new SubstanceBusinessBlackSteelLookAndFeel());
				} catch (UnsupportedLookAndFeelException e) {
					System.err.println("Impossible de charger le LookAndFeel");
				}
				
				// initialisation des fenêtres + module régulation
				Regulation regul = new Regulation();
				Fenetre fen = new Fenetre(regul);
				
				new Thread(new Runnable() {
					public void run() {
						SerialClass obj = new SerialClass(regul);
						obj.addObservateur(fen);
						obj.initialize();
					}
				}).start();
				
			}
		});

	}

}
