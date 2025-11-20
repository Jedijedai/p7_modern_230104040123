package id.antasari.p7_modern_230104040123.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    isDarkTheme: Boolean,              // Menerima status Dark Mode saat ini
    onThemeChanged: (Boolean) -> Unit  // Fungsi untuk lapor kalau tombol ditekan
) {
    var isNotifEnabled by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Pengaturan") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Item Pengaturan 1: Dark Mode
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Mode Gelap (Dark Mode)", style = MaterialTheme.typography.bodyLarge)
                Switch(
                    checked = isDarkTheme, // Status tombol ikut data dari MainActivity
                    onCheckedChange = { isChecked ->
                        onThemeChanged(isChecked) // Lapor ke MainActivity
                    }
                )
            }
            Divider()

            // Item Pengaturan 2: Notifikasi (Masih dummy local)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Notifications, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Notifikasi", style = MaterialTheme.typography.bodyLarge)
                }
                Switch(
                    checked = isNotifEnabled,
                    onCheckedChange = { isNotifEnabled = it }
                )
            }
            Divider()

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Versi Aplikasi 1.0.0",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.outline
            )
        }
    }
}