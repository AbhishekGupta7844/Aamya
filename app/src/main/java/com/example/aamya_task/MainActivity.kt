package com.example.aamya_task

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aamya_task.ui.theme.Aamya_TaskTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Aamya_TaskTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFF9FAFB)
                ) {
                    HomeScreen()
                }
            }
        }
    }
}

@Composable
fun HomeScreen() {
    val scrollState = rememberScrollState()

    Scaffold(
        bottomBar = { BottomNavBar() },
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(16.dp)
        ) {
            TopBar()
            Spacer(Modifier.height(16.dp))
            SearchBar()
            Spacer(Modifier.height(16.dp))
            Date_Doctor()
            Spacer(Modifier.height(16.dp))
            CategoryRow()
            Spacer(Modifier.height(16.dp))
            ScheduleSection()
        }
    }
}

// top of screen
@Composable
fun TopBar() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.person),
            contentDescription = "Person",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color.Gray),
            contentScale = ContentScale.Crop
        )
        Icon(
            Icons.Default.Notifications,
            modifier = Modifier
                .size(30.dp)
                .clip(CircleShape)
                .background(Color.White),
            contentDescription = "Refresh"
        )
    }
}

// search bar
@Composable
fun SearchBar() {
    OutlinedTextField(
        value = "",
        onValueChange = {},
        placeholder = { Text("Search by doctor's name", color = Color.Gray) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White
        ),
        leadingIcon = { Icon(Icons.Default.Search, null) },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp)
    )
}

// select date and doctor list
@Composable
fun Date_Doctor() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White, shape = RoundedCornerShape(16.dp))
            .padding(16.dp),
    )
    {
        DateSelector()
        Spacer(Modifier.height(16.dp))
        DoctorRow()
    }
}

// select date
@Composable
fun DateSelector() {
    val days = listOf(
        "15" to "Sun",
        "16" to "Mon",
        "17" to "Tue",
        "18" to "Wed",
        "19" to "Thu",
        "20" to "Fri",
        "21" to "Sat"
    )
    var selected by remember { mutableStateOf("18") }

    LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        items(days) { (date, day) ->
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(if (selected == date) Color(0xFFFFC107) else Color(0x196868FF))
                    .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(day, fontSize = 12.sp)
                Text(date, fontWeight = FontWeight.Bold)
            }
        }
    }
}

// doctor list
@Composable
fun DoctorRow() {
    val doctors = listOf(
        Doctor(R.drawable.doctor_one, "Ralph Edward", "Dentist Specialist", 4.8),
        Doctor(R.drawable.doctor_two, "Bessie Cooper", "Surgery Specialist", 4.6),
        Doctor(R.drawable.doctor_three, "Annette Black", "Urology Specialist", 4.2)
    )

    LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        items(doctors) { doc ->
            Card(
                modifier = Modifier.width(130.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0x196868FF)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(Modifier.padding(12.dp)) {
                    Row {
                        Image(
                            painter = painterResource(doc.image),
                            contentDescription = "Image",
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(Color.Gray),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text("⭐ ${doc.rating}")
                    }
                    Spacer(Modifier.height(8.dp))
                    Text(doc.name, fontWeight = FontWeight.Bold)
                    Text(doc.specialty, fontSize = 12.sp, color = Color.Gray)
                }
            }
        }
    }
}

// facilities
@Composable
fun CategoryRow() {

    val categories = listOf(
        Category(R.drawable.hospital, "Hospital"),
        Category(R.drawable.consultant, "Consultant"),
        Category(R.drawable.advice, "Advice")
    )

    LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        items(categories) { cat ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0x196868FF)),
                shape = RoundedCornerShape(48.dp)
            ) {
                Column(Modifier.padding(12.dp)) {
                    Row {
                        Image(
                            painter = painterResource(cat.image),
                            contentDescription = "Image",
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(Color.Gray),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(
                            cat.facility
                        )
                    }
                }
            }
        }
    }

}

// schedule
@Composable
fun ScheduleSection() {
    Row {
        Text("Upcoming Schedule", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(Modifier.weight(1f))
        Text("See All", color = Color.Gray, fontWeight = FontWeight.Bold, fontSize = 18.sp)
    }

    Spacer(Modifier.height(12.dp))
    ScheduleCard(
        R.drawable.doctor_one,
        "Dr. Lailas Russell",
        "Dermatologist Specialist",
        "20 September - 12:30 - 10:30 PM"
    )

    Spacer(Modifier.height(12.dp))
    ScheduleCard(
        R.drawable.doctor_two,
        "Dr. Cries Jacks",
        "Pediatrician Specialist",
        "21 September - 12:30 - 10:30 PM"
    )
    Spacer(Modifier.height(12.dp))
    ScheduleCard(
        R.drawable.doctor_three,
        "Dr. Annette Black",
        "Urology Specialist",
        "22 September - 12:30 - 10:30 PM"
    )
}


@Composable
fun ScheduleCard(image: Int, name: String, specialty: String, time: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(image),
                contentDescription = "Doctor",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.Gray),
                contentScale = ContentScale.Crop
            )
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text(name, fontWeight = FontWeight.Bold)
                Text(specialty, fontSize = 12.sp, color = Color.Gray)
            }
            Image(
                painter = painterResource(id = R.drawable.chat),
                contentDescription = "Chat",
                modifier = Modifier
                    .size(30.dp)
                    .padding(5.dp)
                    .clip(CircleShape)
                    .background(Color.Gray),
                contentScale = ContentScale.Crop
            )
            Spacer(Modifier.width(8.dp))
            Icon(Icons.Default.Call, contentDescription = "Call", tint = Color(0xFFC6B454))
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .size(50.dp)
                .clip(CircleShape)
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp)
                .background(Color(0xFFF6F6F6)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(time, fontSize = 12.sp, color = Color.Black)
        }
    }
}

// bottom of screen
@Composable
fun BottomNavBar() {
    NavigationBar {
        NavigationBarItem(
            selected = true, onClick = {}, icon = { Icon(Icons.Default.Home, null) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                indicatorColor = Color(0xFFFFB808)
            )
        )

        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Icon(Icons.Default.Email, null) })

        NavigationBarItem(selected = false, onClick = {}, icon = {
            FloatingActionButton(
                onClick = {},
                containerColor = Color.Black,
                shape = CircleShape
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add", tint = Color.White)
            }
        })

        NavigationBarItem(selected = false, onClick = {}, icon = { Icon(Icons.Default.Menu, null) })
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Icon(Icons.Default.Person, null) })
    }

}

// preview
@Preview(showBackground = true)
@Composable
fun Preview() {
    Aamya_TaskTheme {
        HomeScreen()
    }
}