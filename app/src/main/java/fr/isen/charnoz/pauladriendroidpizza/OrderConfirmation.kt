package fr.isen.charnoz.pauladriendroidpizza

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_order_confirmation.*


class OrderConfirmation : AppCompatActivity() {
    //sharedPreference
    private var PRIVATE_MODE = 0
    private val PREF_NAME = "sharedPref_user"
    private val FIRST_NAME_KEY = "first_name_key"
    private val LAST_NAME_KEY = "last_name_key"
    //Intent
    private val key_adresse = "adresse"
    private val key_pizza = "pizza"
    private val key_time = "time"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_confirmation)

        //
        val extras = intent.extras

        var adresse = extras?.getString(key_adresse)
        var pizza = extras?.getString(key_pizza)
        var time = extras?.getString(key_time)

        val sharedPref: SharedPreferences = getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        //
        var prenom = sharedPref.getString(FIRST_NAME_KEY,"")
        var nom = sharedPref.getString(LAST_NAME_KEY,"")

        //
        val first_last_name = findViewById<TextView>(R.id.clientFirstLastName).setText(nom + " " + prenom)
        val time_ordered = findViewById<TextView>(R.id.timeOrdered).setText(time)
        val place_ordered = findViewById<TextView>(R.id.placeOrdered).setText(adresse)
        val pizza_ordered = findViewById<TextView>(R.id.pizzaOrdered).setText(pizza)
        //
        Log.v("Confirmation commande","$nom $prenom à commander une pizza $pizza à l'adresse $adresse pour $time")

        var btn = findViewById<Button>(R.id.confMail).setOnClickListener {
            val emailIntent = Intent(
                Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", "marc.mollinari@gmail.com", null
                )
            )
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Confirmation de Commande")

            emailIntent.putExtra(Intent.EXTRA_TEXT, "Votre commande a bien été enregistrée")

            if (emailIntent.resolveActivity(packageManager) != null) {
                // Prompt the user to select a mail app
                startActivity(Intent.createChooser(emailIntent, "Choose your mail application"))
            } else {
                // Inform the user that no email clients are installed or provide an alternative
            }
        }
    }
}
