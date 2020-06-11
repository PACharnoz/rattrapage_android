package fr.isen.charnoz.pauladriendroidpizza

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
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
        setContentView(R.layout.activity_main)

        //
        val sharedPref: SharedPreferences = getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        //
        if(sharedPref.getBoolean(PREF_NAME, false)){}
        else{
            var first_name_textEdit = findViewById<EditText>(R.id.prenom)
            var last_name_textEdit = findViewById<EditText>(R.id.nom)

            first_name_textEdit.setText(sharedPref.getString(FIRST_NAME_KEY,""))
            last_name_textEdit.setText(sharedPref.getString(LAST_NAME_KEY,""))
        }
        //
        var Btn = findViewById<Button>(R.id.validButton).setOnClickListener {
            var last_name = findViewById<EditText>(R.id.nom).getText().toString()
            var first_name = findViewById<EditText>(R.id.prenom).getText().toString()
            var adresse = findViewById<EditText>(R.id.adresse).getText().toString()
            var phone = findViewById<EditText>(R.id.phone).getText().toString()

            var pizza = findViewById<Spinner>(R.id.pizzaChoice).selectedItem


            val timePicker = findViewById<TimePicker>(R.id.timePicker)
            var hour = timePicker.currentHour
            var minute = timePicker.currentMinute
            var time = hour.toString() + "h"+ minute.toString()

            Log.v("timePicker", "($hour:$minute) // $time")
            Log.v("Spinner", "$pizza")

            if(last_name.isNotEmpty()) {
                if (first_name.isNotEmpty()) {
                    if (adresse.isNotEmpty()) {
                        if (phone.isNotEmpty()) {
                            if (phone.length == 10) {
                                Log.v("EditText", " last_name : $last_name / first_name : $first_name / address : $adresse / phone number : $phone")
                                Toast.makeText(this,"Tous les champs sont remplis, veuillez patienter.", Toast.LENGTH_SHORT).show()

                                //
                                val editor = sharedPref.edit()
                                editor.putString(FIRST_NAME_KEY,first_name)
                                editor.putString(LAST_NAME_KEY,last_name)
                                editor.apply()
                                Log.v("SharedPref","Shared Pref : ${sharedPref.getString(FIRST_NAME_KEY,"")} / ${sharedPref.getString(LAST_NAME_KEY,"")}")
                                //
                                val i = Intent(this, OrderConfirmation::class.java)
                                i.putExtra(key_adresse,adresse)
                                i.putExtra(key_pizza,"$pizza")
                                i.putExtra(key_time, time)
                                startActivity(i)
                            } else {
                                Toast.makeText(this,"Numéro incorrect", Toast.LENGTH_LONG).show()
                            }
                        } else {
                            Toast.makeText(this,"Le champs numéro est vide !", Toast.LENGTH_LONG).show()
                        }
                    } else {
                        Toast.makeText(this,"Le champs adresse est vide !", Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(this,"Le champs prénom est vide !", Toast.LENGTH_LONG).show()
                }
            }
            else{
                Log.e("EmptyArray","Un des champs est vide.")
                Toast.makeText(this,"Le champs nom est vide !", Toast.LENGTH_LONG).show()
            }
        }
        //Begin Spinner
        val spinner: Spinner = findViewById(R.id.pizzaChoice)
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(this,R.array.pizza_array,android.R.layout.simple_spinner_item)
            .also{
                adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                spinner.adapter = adapter
            }
        //End spinner



    }
}
