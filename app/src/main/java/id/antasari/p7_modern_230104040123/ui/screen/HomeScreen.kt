package id.antasari.p7_modern_230104040123.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import id.antasari.p7_modern_230104040123.ui.components.AppCard

// Data Class untuk Mata Kuliah
data class MatkulData(
    val title: String,
    val desc: String,
    val icon: ImageVector,
    val isHero: Boolean = false
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNotificationClick: () -> Unit
) {
    val context = LocalContext.current
    var searchQuery by remember { mutableStateOf("") }

    // Data Dummy Mata Kuliah
    val allMatkuls = listOf(
        MatkulData("Pemrograman Mobile", "Lab Komputer 1 • 08.00 - 10.30", Icons.Default.Code, true),
        MatkulData("Kecerdasan Buatan", "R. Teori 3 • Selasa, 10.00", Icons.Default.Computer),
        MatkulData("Etika Profesi", "Aula Utama • Kamis, 09.00", Icons.Default.Book),
        MatkulData("Cloud Computing", "Lab Jaringan • Rabu, 13.00", Icons.Default.Computer),
        MatkulData("Basis Data", "Lab Data • Jumat, 08.00", Icons.Default.Star),
        MatkulData("Jaringan Komputer", "Lab Jaringan • Senin, 13.00", Icons.Default.Computer)
    )

    // Logika Filter Pencarian
    val filteredMatkuls = allMatkuls.filter {
        it.title.contains(searchQuery, ignoreCase = true)
    }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .statusBarsPadding(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(text = "Selamat Pagi,", style = MaterialTheme.typography.bodyLarge)
                    Text(
                        text = "Mahasiswa Teladan",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                // Tombol Notifikasi
                IconButton(onClick = onNotificationClick) {
                    BadgedBox(badge = { Badge { Text("3") } }) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Notifikasi",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // --- SEARCH BAR ---
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                placeholder = { Text("Cari mata kuliah...") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(50.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
                    unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
                    disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
                    cursorColor = MaterialTheme.colorScheme.primary,
                    focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Tampilan Utama (Jika tidak sedang mencari)
            if (searchQuery.isEmpty()) {
                // HERO SECTION (Jadwal Terdekat)
                val heroData = allMatkuls.firstOrNull { it.isHero }
                if (heroData != null) {
                    Text(
                        text = "Jadwal Terdekat",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    AppCard(
                        title = heroData.title,
                        description = heroData.desc,
                        icon = heroData.icon,
                        backgroundColor = MaterialTheme.colorScheme.primaryContainer
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                }

                // BAGIAN KATEGORI SUDAH DIHAPUS DI SINI

                Text(
                    text = "Mata Kuliah Lainnya",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            } else {
                // Teks Hasil Pencarian
                Text(
                    text = "Hasil Pencarian: '$searchQuery'",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            // MENAMPILKAN LIST DATA (FILTERED)
            if (filteredMatkuls.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxWidth().padding(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Data tidak ditemukan", color = Color.Gray)
                }
            } else {
                filteredMatkuls.forEach { matkul ->
                    // Mencegah duplikasi Hero Card saat mode normal
                    if (searchQuery.isNotEmpty() || !matkul.isHero) {
                        AppCard(
                            title = matkul.title,
                            description = matkul.desc,
                            icon = matkul.icon
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}