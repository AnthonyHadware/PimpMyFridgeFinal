package view;

import model.DataArduino;

public interface Observateur {
	public void afficherNotification(DataArduino data);
}
