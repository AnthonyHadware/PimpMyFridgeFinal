// Code Arduino
// Projet Pimp My Fridge - Exia.Cesi A2 2017-2018

// Il est possible de contrôle l'allumage et l'extinction du réfrigérateur en envoyant
// des commandes à l'arduino via la liaison série.
// Envoyer 1 va allumer le réfrigérateur.
// Envoyer 0 va eteindre le réfrigérateur.
// L'arduino réponds toujours pour valider la commande. Il renvoie :
// R:1   quand il allume le réfrigérateur.
// R:0   quand il coupe le réfrigérateur.

// CONFIGURATION
#define DHTPIN 2       // Pin de commande du capteur DTH interne
#define DHTTYPE DHT22   // Type de DTH : DHT22 (AM2302), AM2321
int PIN_FRIGO = 5;      // Pin de contrôle de l'alimentation du frigo
int RESISTANCE_THERMISTANCE = 10000;  // Résistance en ohm de la thermistance externe
// FIN DE LA CONFIGURATION

// Librairie du capteur DHT
#include "DHT.h"
DHT dht(DHTPIN, DHTTYPE);

// Setup de variables
int readValue = 0;
bool writing = false;


/**
 * Setup de l'application.
 */
void setup() {
  // Ouverture de la liaison série
  Serial.begin(9600);
  // Passage du pin du frigo en écriture
  pinMode(PIN_FRIGO, OUTPUT);
  // Activation librairie DHT
  dht.begin();
}


void loop() {
  
  // On met un délai entre chaque mesure
  delay(750);

  // CAPTEUR DHT INTERNE AU FRIGO
  //Lecture de l'humidité
  float Humidity = dht.readHumidity();
  // Lecture de la température (en degrés Celsius)
  float internalTemperature = dht.readTemperature();

  // CAPTEUR PT100 TEMPERATURE EXTERNE AU FRIGO
  // Lecture de la tension
  float thermistanceReading = analogRead(A0);
  float thermistanceVoltage = thermistanceReading * 5.0 / 1023.0;
  float Resistance = (5.0 - thermistanceVoltage) * RESISTANCE_THERMISTANCE / thermistanceVoltage;
  float externalTemperature = (1/(1.258935919*pow(10, -3)
    + 2.144918397*pow(10, -4)*log(Resistance)
    + 1.513438730*pow(10, -7)*pow(log(Resistance), 3)))
    - 273.15;

  // On envoie une trame de données sur la liaison série
  while (writing) {} // synchro
  writing = true;
  Serial.println(Humidity);
  Serial.println(internalTemperature);
  Serial.println(externalTemperature);
  writing = false;
}

/**
 * Quand une donnée est reçue sur la liaison série.
 */
 
void serialEvent()
{
  // Quand des données sont disponibles
  while (Serial.available())
  {
    // On récupère la commande depuis le programme en Java, sous forme d'entier
    readValue = Serial.parseInt();
    
    // Le frigo s'éteint
    if (readValue == 0)
    {
      digitalWrite(PIN_FRIGO, LOW);
    }
    
    // Le frigo s'allume
    else if (readValue == 1)
    {
      digitalWrite(PIN_FRIGO, HIGH);
    }
    
    else {
      readValue = 3;
    }
    // Boucle de réponse
    while (writing) {} // permet la synchronisation
    writing = true;
    Serial.print("R:");
    Serial.println(readValue);
    writing = false;
  }
}
