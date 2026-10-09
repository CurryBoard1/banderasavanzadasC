package com.example.banderasavanzadasc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import kotlin.math.cos
import kotlin.math.sin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(modifier = Modifier.fillMaxSize()) {
                Box(contentAlignment = Alignment.Center) {
                    BanderaSeychellesCL(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(2f)
                    )
                }
            }
        }
    }
}

private fun puntoEnBorde(w: Float, h: Float, grados: Float): Offset {
    val a = Math.toRadians(grados.toDouble())
    val dx = cos(a).toFloat()
    val dy = sin(a).toFloat()
    val haciaDerecha = if (dx > 0.0001f) w / dx else Float.MAX_VALUE
    val haciaArriba = if (dy > 0.0001f) h / dy else Float.MAX_VALUE
    val t = minOf(haciaDerecha, haciaArriba)
    return Offset(dx * t, h - dy * t)
}

@Composable
fun BanderaSeychellesCL(modifier: Modifier = Modifier) {
    ConstraintLayout(modifier = modifier.fillMaxSize()) {
        val lienzo = createRef()
        Canvas(
            modifier = Modifier.constrainAs(lienzo) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            }
        ) {
            val w = size.width
            val h = size.height

            val azul = Color(0xFF003F87)
            val amarillo = Color(0xFFFCD856)
            val rojo = Color(0xFFD62828)
            val verde = Color(0xFF007A3D)

            val origen = Offset(0f, h)
            val colores = listOf(azul, amarillo, rojo, Color.White, verde)
            val angulos = listOf(90f, 72f, 54f, 36f, 18f, 0f)

            for (i in 0 until 5) {
                val p1 = puntoEnBorde(w, h, angulos[i])
                val p2 = puntoEnBorde(w, h, angulos[i + 1])
                val franja = Path().apply {
                    moveTo(origen.x, origen.y)
                    lineTo(p1.x, p1.y)
                    if (p1.y < 1f && p2.x > w - 1f) {
                        lineTo(w, 0f)
                    }
                    lineTo(p2.x, p2.y)
                    close()
                }
                drawPath(franja, color = colores[i])
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BanderaSeychellesCLPreview() {
    Surface {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            BanderaSeychellesCL(Modifier.fillMaxWidth().aspectRatio(2f))
        }
    }
}
