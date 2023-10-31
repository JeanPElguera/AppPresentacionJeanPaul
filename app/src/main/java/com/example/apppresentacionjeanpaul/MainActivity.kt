package com.example.apppresentacionjeanpaul

import android.net.Uri
import android.os.Bundle
import android.widget.VideoView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.apppresentacionjeanpaul.ui.theme.AppPresentacionJeanPaulTheme



class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppPresentacionJeanPaulTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                  //  ComposeArticleApp()
                    GreetingImage(stringResource(R.string.Titulo),
                        stringResource(R.string.Nombre),
                        stringResource(R.string.Movil),
                        stringResource(R.string.Correo),
                        stringResource(R.string.Direccion),
                        painterResource(R.drawable.fondo_cabecera),
                        painterResource(R.drawable.correo),
                        painterResource(R.drawable.direccion),
                        painterResource(R.drawable.movil),
                        painterResource(R.drawable.persona)
                        )
                }
            }
        }
    }

}
@Composable
fun GreetingImage(
    titulo: String,
    nombre: String,
    Movil : String,
    Direccion : String,
    Correo : String,
    fondo: Painter,
    correo: Painter,
    direccion: Painter,
    movil: Painter,
    persona: Painter,
    modifier: Modifier = Modifier,
    ) {
    // Create a box to overlap image and texts




        Image(
            painter = painterResource(id = R.drawable.ciudad),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier.size(1920.dp,1080.dp)
        )
        Greeting(
            titulo = titulo ,
            nombre= nombre,
            Movil  =Movil ,
            Direccion = Direccion,
            Correo = Correo,
            fondo = fondo,
            correo = correo,
            direccion = direccion,
            movil = movil,
            persona = persona,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        )

}

@Composable
fun Greeting(
    titulo: String,
    nombre: String,
    Movil: String,
    Direccion: String,
    Correo: String,
    fondo: Painter,
    correo: Painter,
    direccion: Painter,
    movil: Painter,
    persona: Painter,
    modifier: Modifier = Modifier,
) {
    //Variable para dar un estilo de letra
    val myTextStyle = TextStyle(
        fontSize = 18.sp,
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.1.sp
    )
    val context = LocalContext.current
    val videoView = remember { VideoView(context) }
    val videoUri = "android.resource://${LocalContext.current.packageName}/${R.raw.elixir}"
    // Variable para controlar la visibilidad del diálogo
    var isPlaying by remember { mutableStateOf(false )}
    val showDialog = remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        // Imagen de fondo
        Image(
            painter = fondo,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
        )

        // Contenido de la pantalla
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 30.dp,end= 16.dp, top = 35.dp)
        ) {
            Text(
                text = titulo,
                fontSize = 24.sp,
                style = myTextStyle.copy(color = Color.White)
            )


        }
    }



    Column(
            modifier = Modifier

        ) {
            AndroidView(
                factory = { context ->
                    videoView.apply {
                        setVideoURI(Uri.parse(videoUri))
                        setOnPreparedListener { mediaPlayer ->
                            mediaPlayer.isLooping = true // Para hacer el video en bucle
                        }
                    }
                },
                modifier = Modifier

                    .padding(start = 50.dp, top = 120.dp)
                    .size(300.dp, 200.dp)
                    .pointerInput(Unit) {
                        detectTapGestures { offset ->
                            if (offset.x > 0f && offset.y > 0f && offset.x < 300.dp.toPx() && offset.y < 200.dp.toPx()) {
                                if (isPlaying) {
                                    videoView.pause()
                                } else {
                                    videoView.start()
                                }
                                isPlaying = !isPlaying
                            }
                        }
                    }
            )



            Image(
                painter = persona,
                contentDescription = null,
                modifier = Modifier.padding(start = 100.dp, top=10.dp, bottom = 10.dp).size(200.dp)
                    .clip(CircleShape)
                    .border(3.dp, Color.Black, CircleShape)
                    .background(Color.White)

            )


            Text(
                text = nombre,
                textAlign = TextAlign.Justify,
                style = myTextStyle.copy(color = Color.White),
                modifier = Modifier.padding(start = 80.dp, end = 10.dp)
            )
        Button(
            onClick = { showDialog.value = true },
            modifier = Modifier.padding(start = 80.dp, top = 40.dp)
                .width(250.dp) // Cambia el valor para el ancho
                .height(40.dp) // Cambia el valor para la altura

        ) {
            Text(text = "Conocimientos Profecionales")
        }

        // Diálogo de mensaje
        if (showDialog.value) {
            AlertDialog(
                onDismissRequest = { showDialog.value = false },
                title = { Text(text = "Skills") },
                text = { Text(text = "Java: 5 años de experiencia\nKotlin: 3 años de experiencia\nPython: 7 años de experiencia") },
                confirmButton = {
                    Button(
                        onClick = { showDialog.value = false },
                        colors = ButtonDefaults.buttonColors(Color.Gray)
                    ) {
                        Text(text = "Terminaste la presentacion :)")
                    }
                }
            )
        }

        }

        Column(
            modifier = Modifier
                .padding(start = 80.dp, end = 16.dp, top = 700.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = movil,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = Movil,
                    textAlign = TextAlign.Justify,
                    style = myTextStyle.copy(color = Color.White)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = correo,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = Correo,
                    textAlign = TextAlign.Justify,
                    style = myTextStyle.copy(color = Color.White)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = direccion,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = Direccion,
                    textAlign = TextAlign.Justify,
                    style = myTextStyle.copy(color = Color.White)
                )
            }
        }





}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppPresentacionJeanPaulTheme {
        GreetingImage(stringResource(R.string.Titulo),
            stringResource(R.string.Nombre),
            stringResource(R.string.Movil),
            stringResource(R.string.Correo),
            stringResource(R.string.Direccion),
            painterResource(R.drawable.fondo_cabecera),
            painterResource(R.drawable.correo),
            painterResource(R.drawable.direccion),
            painterResource(R.drawable.movil),
            painterResource(R.drawable.persona))
    }
}