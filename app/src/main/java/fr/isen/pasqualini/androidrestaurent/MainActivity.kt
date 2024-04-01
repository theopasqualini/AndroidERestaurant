package fr.isen.pasqualini.androidrestaurent

import android.content.Intent
import android.os.Bundle
import android.support.wearable.watchface.decomposition.ImageComponent
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.isen.pasqualini.androidrestaurent.ui.theme.AndroidREstaurentTheme
import fr.isen.pasqualini.androidrestaurent.ui.theme.Orange
import fr.isen.pasqualini.androidrestaurent.ui.theme.White

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidREstaurentTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Greeting("Android")
                    PreviewImageComponent()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidREstaurentTheme {
        Greeting("Android")
    }
}

@Composable
fun ThreePostButtons(imageResId: Int, contentDescription: String? = null) {
    val imagePainter: Painter = painterResource(id = imageResId)

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Image
        Image(
            painter = imagePainter,
            contentDescription = contentDescription,
            modifier = Modifier
                .height(500.dp) // Taille de l'image
                .aspectRatio(1f), // Aspect ratio carré
            contentScale = ContentScale.Fit // Ajustement de l'échelle de l'image
        )

        // Espacement entre l'image et les boutons
        Spacer(modifier = Modifier.height(0.dp))

        // Trois boutons
        PostButton(text = "Entrée")
        Text(
            text = "---------------------------------------"
        )
        PostButton(text = "Plat")
        Text(
            text = "---------------------------------------"
        )
        PostButton(text = "Dessert")
    }
}

@Composable
fun PostButton(text: String) {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 0.dp, vertical = 0.dp), // Ajoute des marges horizontales
        horizontalArrangement = Arrangement.Absolute.Center
    ) {
        Spacer(modifier = Modifier.width(16.dp)) // Ajoute un espace à gauche du bouton
        Box(
            modifier = Modifier
                .size(50.dp) // Taille fixe du bouton
                .background(color = White, shape = RectangleShape)
                .weight(1f)
                .clickable {
                    val intent = Intent(context, Entreeactivity::class.java)
                    launcher.launch(intent)
                },
            contentAlignment = Alignment.Center // Centrer le contenu dans le cercle
        ) {
            Text(
                text = text,
                style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold),
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .size(256.dp) // Taille du texte
                    .padding(horizontal = 30.dp, vertical = 0.dp),

                )
        }
        Spacer(modifier = Modifier.width(16.dp)) // Ajoute un espace à droite du bouton
    }
}

@Preview
@Composable
fun PreviewImageComponent() {
    ThreePostButtons(imageResId = R.drawable.capture, contentDescription = "Your Image")
}