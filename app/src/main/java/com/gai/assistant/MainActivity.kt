
package com.gai.assistant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val GaiBlack = Color(0xFF101014)
private val GaiCard = Color(0xFF1D1D25)
private val GaiGold = Color(0xFFD6AD55)
private val GaiBlue = Color(0xFF526FA8)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GaiTheme {
                GaiApp()
            }
        }
    }
}

@Composable
fun GaiTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = darkColorScheme(
            primary = GaiGold,
            secondary = GaiBlue,
            background = GaiBlack,
            surface = GaiCard
        ),
        content = content
    )
}

@Composable
fun GaiApp() {
    var showChat by remember { mutableStateOf(false) }

    if (showChat) {
        ChatScreen(onBack = { showChat = false })
    } else {
        Dashboard(onOpenChat = { showChat = true })
    }
}

@Composable
fun Dashboard(onOpenChat: () -> Unit) {
    Scaffold(
        containerColor = GaiBlack,
        floatingActionButton = {
            FloatingActionButton(
                onClick = onOpenChat,
                containerColor = GaiGold,
                contentColor = GaiBlack
            ) {
                Text("✦", fontSize = 26.sp)
            }
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {

            Text(
                text = "GAI",
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                color = GaiGold
            )

            Text(
                text = "GODWIN'S ARTIFICIAL INTELLIGENCE",
                fontSize = 11.sp,
                color = Color.LightGray,
                letterSpacing = 2.sp
            )

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "Good day.",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Text(
                text = "Your assistant. Your operations. Your world.",
                color = Color.LightGray,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(25.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = GaiCard
                ),
                shape = RoundedCornerShape(18.dp)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        "GAI SYSTEM",
                        color = GaiGold,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        "Interface initialized",
                        color = Color.White,
                        fontSize = 18.sp
                    )

                    Text(
                        "AI services are not connected yet.",
                        color = Color.LightGray,
                        fontSize = 13.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            Text(
                "YOUR WORKSPACE",
                color = GaiGold,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            DashboardCard("◷", "Tasks", "Your work and activities")
            DashboardCard("◉", "Reminders", "Things that need your attention")
            DashboardCard("◇", "Projects", "Manage your ongoing projects")
            DashboardCard("◎", "Departments", "Access your different operations")
            DashboardCard("⚙", "Settings", "Configure your GAI experience")

            Spacer(modifier = Modifier.height(25.dp))

            Text(
                "GAI • BUILD 001",
                color = Color.Gray,
                fontSize = 11.sp
            )
        }
    }
}

@Composable
fun DashboardCard(
    symbol: String,
    title: String,
    description: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        colors = CardDefaults.cardColors(
            containerColor = GaiCard
        ),
        shape = RoundedCornerShape(14.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = symbol,
                color = GaiGold,
                fontSize = 25.sp
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    title,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    description,
                    color = Color.LightGray,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
fun ChatScreen(onBack: () -> Unit) {
    var message by remember { mutableStateOf("") }
    var messages by remember {
        mutableStateOf(listOf("GAI interface is ready. AI connection is coming next."))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GaiBlack)
            .padding(18.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            TextButton(onClick = onBack) {
                Text("← Back", color = GaiGold)
            }

            Text(
                "GAI CHAT",
                color = GaiGold,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            messages.forEach { item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = GaiCard
                    )
                ) {
                    Text(
                        item,
                        color = Color.White,
                        modifier = Modifier.padding(15.dp)
                    )
                }
            }
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = message,
                onValueChange = { message = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Message GAI...") },
                singleLine = true
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = {
                if (message.isNotBlank()) {
                    messages = messages + "You: $message"
                    message = ""
                }
            }) {
                Text("Send")
            }
        }
    }
}
