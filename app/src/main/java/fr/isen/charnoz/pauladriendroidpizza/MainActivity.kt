package fr.isen.charnoz.pauladriendroidpizza

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner

class MainActivity : AppCompatActivity() {
    //sharedPreference
    private var PRIVATE_MODE = 0
    private val PREF_NAME = "private_info"
    val sharedPreference : SharedPreferences = getSharedPreferences(PREF_NAME, PRIVATE_MODE)
    var editor = sharedPreference.edit()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
