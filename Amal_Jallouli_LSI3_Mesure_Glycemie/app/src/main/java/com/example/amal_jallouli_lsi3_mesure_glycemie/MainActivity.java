package com.example.amal_jallouli_lsi3_mesure_glycemie;

// Importing necessary packages and classes
import androidx.appcompat.app.AppCompatActivity; // Importing the base class for activities in Android
import android.annotation.SuppressLint; // Importing annotation for suppressing lint warnings
import android.os.Bundle; // Importing the Bundle class for passing data between activities
import android.util.Log; // Importing Log class for logging messages
import android.view.View; // Importing View class for UI components
import android.widget.Button; // Importing Button class for buttons
import android.widget.EditText; // Importing EditText class for text input
import android.widget.RadioButton; // Importing RadioButton class for radio buttons
import android.widget.RadioGroup; // Importing RadioGroup class for radio button groups
import android.widget.SeekBar; // Importing SeekBar class for seek bars
import android.widget.TextView; // Importing TextView class for displaying text
import android.widget.Toast; // Importing Toast class for displaying short-lived messages

// Defining the main activity class
public class MainActivity extends AppCompatActivity
{
    // Declaring private variables for UI elements
    private TextView tvage , tvresultat; // Declaring TextView variables for displaying age and result
    private SeekBar sbage ; // Declaring SeekBar variable for age selection
    private RadioButton rboui ; // Declaring RadioButton variable for "yes" option
    private RadioButton rbnon ; // Declaring RadioButton variable for "no" option
    private EditText etvaleur; // Declaring EditText variable for value input
    private Button btnConsulter ; // Declaring Button variable for consultation button

    // Initializing UI elements
    private void init()
    {
        etvaleur = (EditText) findViewById(R.id.etvaleur); // Initializing EditText for value input
        sbage = (SeekBar) findViewById(R.id.sbage); // Initializing SeekBar for age selection
        tvage = (TextView)findViewById(R.id.tvage); // Initializing TextView for displaying age
        rboui = (RadioButton) findViewById(R.id.rboui); // Initializing RadioButton for "yes" option
        rbnon = (RadioButton) findViewById(R.id.rbnon); // Initializing RadioButton for "no" option
        btnConsulter=(Button) findViewById(R.id.btConsulter); // Initializing Button for consultation
        tvresultat = (TextView) findViewById(R.id.tvresultat); // Initializing TextView for displaying result
    }

    // Overriding the onCreate method
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState); // Calling the superclass's implementation of onCreate
        setContentView(R.layout.activity_main); // Setting the content view to the specified layout
        init(); // Initializing UI elements

        // Setting up a listener for SeekBar changes
        sbage.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("Information", "onProgressChanged " + progress); // Logging progress information
                tvage.setText("Votre âge : " + progress); // Setting text for displaying age
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        // Setting up a click listener for the "Consulter" button
        btnConsulter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                calculer(v);
            }

            // Method to perform calculations based on user input
            private void calculer(View v)
            {
                int age ;
                float valeur;
                boolean verifAge = false , verifValeur = false ;

                // Checking if age is selected on SeekBar
                if(sbage.getProgress()!=0)
                    verifAge= true ;
                else
                    Toast.makeText(MainActivity.this,"Veuillez verifier votre age",Toast.LENGTH_SHORT).show();

                // Checking if value is entered in the measurement field
                if(!etvaleur.getText().toString().isEmpty())
                    verifValeur=true;
                else
                    Toast.makeText(MainActivity.this, "Veuillez verifier votre valeur mesure", Toast.LENGTH_LONG).show();

                // If both age and value are verified
                if(verifAge && verifValeur)
                {
                    // Extracting and converting user input
                    String contenuTexte = etvaleur.getText().toString();
                    double niveauGlycemie = Double.parseDouble(contenuTexte);
                    int contenuAge = sbage.getProgress();
                    boolean estAJean = rboui.isChecked();
                    String message;

                    // Checking conditions based on user input for age and measurement
                    if (estAJean) {

                        if (contenuAge >= 13) {
                            if (niveauGlycemie < 5.0)
                                message = "Le niveau de glycémie est bas avant le repas ";
                            else if (niveauGlycemie >= 5.0 && niveauGlycemie <= 7.2)
                                message = "Le niveau de glycémie est normal avant le repas ";
                            else
                                message = "Le niveau de glycémie est élevé avant le repas";

                        }
                        else if (contenuAge >= 6 && contenuAge <= 12) {
                            if (niveauGlycemie < 5.0)
                                message = "Le niveau de glycémie est bas avant le repas";
                            else if (niveauGlycemie >= 5.0 && niveauGlycemie <= 10.0)
                                message = "Le niveau de glycémie est normal avant le repas";
                            else
                                message = "Le niveau de glycémie est élevé avant le repas";

                        } else {
                            if (niveauGlycemie < 5.5)
                                message = "Le niveau de glycémie est bas avant le repas";

                            else if (niveauGlycemie >= 5.0 && niveauGlycemie <= 10.0)
                                message = "Le niveau de glycémie est normal avant le repas";
                            else
                                message = "Le niveau de glycémie est élevé avant le repas";
                        }
                    }

                    else {      //!Ajean
                        if ( niveauGlycemie > 10.3 && niveauGlycemie < 10.5)
                            message = "Le niveau de glycémie est normal apres le repas";
                        else if (niveauGlycemie>10.5)
                            message = "Le niveau de glycémie est trop élevé apres le repas";
                        else {
                            message = "Le niveau de glycémie est bas apres le repas";
                        }
                    }

                    // Displaying the result message
                    tvresultat.setText(message);
                }
            }
        });
    }
}

