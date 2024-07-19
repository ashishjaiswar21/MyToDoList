package com.example.mytodolist

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.mytodolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //code
        // Initialize NavController using the NavHostFragment
        navController = findNavController(R.id.fragmentContainerView)

        //ise  jo fragment ka text hoga whi action bar pe likha ayega
        // Setup action bar with NavController to handle navigation actions
         setupActionBarWithNavController(navController)

    }
    // Override onNavigateUp to handle up navigation actions
    //This method overrides the onNavigateUp() method from the parent class (usually AppCompatActivity).
   //navController navgateUp -> This method attempts to navigate "up" in the app's navigation hierarchy. It handles the navigation actions such as navigating back to the previous fragment or activity in the stack.
    //super.onNavigateAUp-> If navController.navigateUp() returns false, the method calls the superclass's onNavigateUp() method to allow the default behavior to occur.
    override fun onNavigateUp(): Boolean {
        // Navigate up using NavController or fallback to the default behavior
        return navController.navigateUp() || super.onNavigateUp()
    }
}