package rs.ac.metropolitan.cs330_projekat

import android.os.Bundle
import android.view.Menu
import androidx.activity.compose.setContent
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import rs.ac.metropolitan.cs330_projekat.databinding.ActivityMainBinding
import rs.ac.metropolitan.cs330_projekat.navigation.NavSetup
import rs.ac.metropolitan.cs330_projekat.ui.theme.ComponentTheme

class MainActivity : AppCompatActivity() {
    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComponentTheme {
                // A surface container using the 'background' color from the theme
                navController = rememberNavController()
                NavSetup(navController = navController)
            }
        }
    }
}