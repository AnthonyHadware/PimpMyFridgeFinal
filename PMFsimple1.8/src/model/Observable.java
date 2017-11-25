package model;

import view.Observateur;

public interface Observable {
	public void addObservateur(Observateur obs);
	public void notifyObservateurs();
}
