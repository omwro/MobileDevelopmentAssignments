package nl.omererdem.madlevel3task2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.activity_main.*

// Global variable states for the portals
val portals = arrayListOf<Portal>()
val portalAdapter = PortalAdapter(portals)

class MainActivity : AppCompatActivity() {
    // The navigation controller
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        // Define the navigation controller
        navController = findNavController(R.id.nav_host_fragment)

        // Navigate to other fragment on FAB click
        fab.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_addPortalFragment)
        }

        // Toggle display the FAB
        fabToggler()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    // Hide the FAB if the add fragment is the destination fragment
    private fun fabToggler() {
        findNavController(R.id.nav_host_fragment).addOnDestinationChangedListener {
                _, destination, _ ->
            if (destination.id in arrayListOf(R.id.addPortalFragment)) {
                fab.hide()
            } else {
                fab.show()
            }
        }
    }
}