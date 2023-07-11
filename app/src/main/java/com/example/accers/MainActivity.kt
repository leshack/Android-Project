package com.example.accers

import android.app.*
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.navigateUp
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import android.app.ProgressDialog
import android.graphics.drawable.ColorDrawable
import android.widget.TextView

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var firebaseAuth: FirebaseAuth
    lateinit var drawerLayout: DrawerLayout
    lateinit var notificationChannel: NotificationChannel
    lateinit var notificationManager: NotificationManager
    lateinit var builder: Notification.Builder
    private lateinit var progressDialog:ProgressDialog
    private val channelId = "12345"
    private val description = "Test Notification"

    // private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firebaseAuth = FirebaseAuth.getInstance()

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please wait")
        progressDialog.setIcon(R.drawable.ic_baseline_architecture)
        progressDialog.setCanceledOnTouchOutside(false)

        drawerLayout = findViewById(R.id.drawableLayout)
        val navigationView: NavigationView = findViewById(R.id.nav_view)
        setSupportActionBar(findViewById(R.id.toolbar)) // Add this line to set the support action bar

        actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#6740AC")))

        // Rest of your code...
        navigationView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home->{ Toast.makeText(this, "Home clicked", Toast.LENGTH_SHORT).show()
                    val intent : Intent = Intent(this , MainActivity::class.java)
                    startActivity(intent)}
                R.id.profile->replaceFragment(ProfileFragment(),it.title.toString())
                R.id.graph->{ Toast.makeText(this, "Graph and reports clicked", Toast.LENGTH_SHORT).show()
                    replaceFragment(GraphFragment(),it.title.toString())}
                R.id.logout->{  checkUser()
                }
                R.id.access->{ Toast.makeText(this, "Access clicked", Toast.LENGTH_SHORT).show()
                    replaceFragment(AccessCodeFragment(),it.title.toString())}
                R.id.design->{Toast.makeText(this, "Design clicked", Toast.LENGTH_SHORT).show()
                    replaceFragment(DesignFragment(),it.title.toString())}
                R.id.fit->{Toast.makeText(this, "Google fit clicked", Toast.LENGTH_SHORT).show()
                    replaceFragment(GoogleFitFragment(),it.title.toString())}
                R.id.reminders->{Toast.makeText(this, "Reminders clicked", Toast.LENGTH_SHORT).show()
                    replaceFragment(RemindersFragment(),it.title.toString())}
                R.id.about->{Toast.makeText(this, "About clicked", Toast.LENGTH_SHORT).show()
                    replaceFragment(AboutusFragment(),it.title.toString())}
                R.id.share->{Toast.makeText(this, "Share application clicked", Toast.LENGTH_SHORT).show()}
                R.id.help->{Toast.makeText(this, "Help clicked", Toast.LENGTH_SHORT).show()}
                R.id.rate->{Toast.makeText(this, "Rate us clicked", Toast.LENGTH_SHORT).show()}
            }

            true
        }
        ///navigation bottom nav replacing the fragments
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.mainContainer) as NavHostFragment
        navController = navHostFragment.navController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        setupWithNavController(bottomNavigationView, navController)

        val floatingActionButton = findViewById<FloatingActionButton>(R.id.fab)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as
                NotificationManager

        floatingActionButton.setOnClickListener{
            //Notification when a user clicks at the Insight
            val intent = Intent(this, MainActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationChannel = NotificationChannel(channelId, description, NotificationManager .IMPORTANCE_HIGH)
                notificationChannel.lightColor = Color.BLUE
                notificationChannel.enableVibration(true)
                notificationManager.createNotificationChannel(notificationChannel)
                builder = Notification.Builder(this, channelId).setContentTitle("Accers Insight  " +
                        "").setContentText("Hi Lawez. Check the Latest Insight. Health is a concern🤷‍♂️!")
                    .setSmallIcon(R.drawable .ic_baseline_architecture)
                    .setLargeIcon(
                        BitmapFactory.decodeResource(this.resources, R.drawable
                            .ic_launcher_background)).setContentIntent(pendingIntent)
            }
            notificationManager.notify(12345, builder.build())

            ///floating action button replacing it with the associated fragment
            val navHostFragment = InsightFragment()
            supportFragmentManager.beginTransaction().replace(R.id.mainContainer, navHostFragment, NavHostFragment::class.java.simpleName )
                .commit()
        }
    }
    ///passing the drawables fragments
    private fun replaceFragment(fragment: Fragment, title: String) {

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.framelayout,fragment)
        fragmentTransaction.commit()
        drawerLayout.closeDrawers()
        setTitle(title)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)){
            true
        }
        return super.onOptionsItemSelected(item)
    }
    private fun checkUser(){
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null){
            val email = firebaseUser.email
            // Toast.makeText(this,"You logged out$email",Toast.LENGTH_LONG).show()
            progressDialog.setMessage("Logging Out....$email")
            progressDialog.show()
            firebaseAuth.signOut()
            var intent = Intent(this, StartPage::class.java)
            startActivity(intent)
        }else{
            Toast.makeText(this,"You logged In",Toast.LENGTH_LONG).show()
        }
    }

}
