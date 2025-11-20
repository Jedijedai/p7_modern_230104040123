package id.antasari.p7_modern_230104040123

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import id.antasari.p7_modern_230104040123.ui.P7_Modern_UI_230104040123Theme

// Import Semua Screen
import id.antasari.p7_modern_230104040123.ui.screen.LoginScreen
import id.antasari.p7_modern_230104040123.ui.screen.HomeScreen
import id.antasari.p7_modern_230104040123.ui.screen.ProfileScreen
import id.antasari.p7_modern_230104040123.ui.screen.SettingsScreen
import id.antasari.p7_modern_230104040123.ui.screen.NotificationScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // 1. Logika Dark Mode Global
            val systemInDarkTheme = isSystemInDarkTheme()
            var isAppDark by remember { mutableStateOf(systemInDarkTheme) }

            P7_Modern_UI_230104040123Theme(darkTheme = isAppDark) {

                // 2. State untuk Login & Navigasi
                var isLoggedIn by remember { mutableStateOf(false) }

                // Tab 0=Home, 1=Profile, 2=Settings, 3=Notification (Hidden)
                var selectedTab by remember { mutableIntStateOf(0) }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (!isLoggedIn) {
                        // TAMPILAN LOGIN
                        LoginScreen(onLoginSuccess = { isLoggedIn = true })
                    } else {
                        // TAMPILAN UTAMA (Setelah Login)
                        Scaffold(
                            bottomBar = {
                                // Sembunyikan BottomBar jika sedang di halaman Notifikasi (Tab 3)
                                if (selectedTab != 3) {
                                    NavigationBar {
                                        NavigationBarItem(
                                            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                                            label = { Text("Home") },
                                            selected = selectedTab == 0,
                                            onClick = { selectedTab = 0 }
                                        )
                                        NavigationBarItem(
                                            icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                                            label = { Text("Profile") },
                                            selected = selectedTab == 1,
                                            onClick = { selectedTab = 1 }
                                        )
                                        NavigationBarItem(
                                            icon = { Icon(Icons.Default.Settings, contentDescription = "Settings") },
                                            label = { Text("Settings") },
                                            selected = selectedTab == 2,
                                            onClick = { selectedTab = 2 }
                                        )
                                    }
                                }
                            }
                        ) { innerPadding ->
                            Box(modifier = Modifier.padding(innerPadding)) {
                                when (selectedTab) {
                                    // Home Screen mengirim event klik notifikasi -> Pindah ke Tab 3
                                    0 -> HomeScreen(
                                        onNotificationClick = { selectedTab = 3 }
                                    )
                                    1 -> ProfileScreen()
                                    // Settings Screen mengirim event ubah tema -> Update variable isAppDark
                                    2 -> SettingsScreen(
                                        isDarkTheme = isAppDark,
                                        onThemeChanged = { newMode -> isAppDark = newMode }
                                    )
                                    // Notification Screen (Tab Rahasia)
                                    3 -> NotificationScreen(
                                        onBackClick = { selectedTab = 0 } // Balik ke Home
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}