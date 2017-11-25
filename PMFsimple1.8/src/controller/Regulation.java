package controller;

import model.SerialClass.serialReader;

public class Regulation implements serialReader{
	
	private float tempConsigne;
	private boolean allumer;
	private float h;
	private float in;
	@SuppressWarnings("unused")
	private float out;

	public Regulation() {	
		setAllumer(true);
		tempConsigne = 18;
	}
	
	@Override
	public void afficherNouvelleDonnees(float h, float in, float out) {
		
		this.h= h;
		this.in = in;
		this.out = out;
		
	}
	
	public float getTempConsigne() {
		return tempConsigne;
	}

	public void setTempConsigne(float tempConsigne) {
		this.tempConsigne = tempConsigne;
	}

	public boolean isAllumer() {
		return allumer;
	}

	public void setAllumer(boolean allumer) {
		this.allumer = allumer;
	}

	public void decider() {
		
		double h = this.h / 100;
		double tempRosee = Math.pow(h , 1.0/8) * (112 + (0.9*this.in)) + (0.1*this.in) - 112;
		
		System.out.println("Température de rosée : " + tempRosee);
		
		if (in > tempConsigne - 1 )
		{
			setAllumer(true);
		}
		else
		{
			setAllumer(false);
		}
		
	}	
	
}
