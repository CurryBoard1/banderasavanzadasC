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
                    BanderaPapuaNuevaGuineaCL(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(4f / 3f)
                    )
                }
            }
        }
    }
}

private fun crearEstrella(cx: Float, cy: Float, radio: Float): Path {
    val path = Path()
    val radioInterior = radio * 0.4f
    for (i in 0 until 10) {
        val r = if (i % 2 == 0) radio else radioInterior
        val angulo = Math.toRadians((-90 + i * 36).toDouble())
        val x = cx + r * cos(angulo).toFloat()
        val y = cy + r * sin(angulo).toFloat()
        if (i == 0) path.moveTo(x, y) else path.lineTo(x, y)
    }
    path.close()
    return path
}

@Composable
fun BanderaPapuaNuevaGuineaCL(modifier: Modifier = Modifier) {
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
            val rojo = Color(0xFFCE1126)
            val dorado = Color(0xFFFCD116)

            drawRect(color = rojo)

            val negro = Path().apply {
                moveTo(0f, 0f)
                lineTo(w, h)
                lineTo(0f, h)
                close()
            }
            drawPath(negro, color = Color.Black)

            val cx = w * 0.27f
            val cy = h * 0.70f
            val grande = h * 0.05f

            drawPath(crearEstrella(cx, cy - h * 0.22f, grande), color = Color.White)
            drawPath(crearEstrella(cx, cy + h * 0.22f, grande), color = Color.White)
            drawPath(crearEstrella(cx - h * 0.17f, cy, grande), color = Color.White)
            drawPath(crearEstrella(cx + h * 0.15f, cy - h * 0.04f, grande), color = Color.White)
            drawPath(crearEstrella(cx + h * 0.08f, cy + h * 0.07f, h * 0.03f), color = Color.White)

            val centroAve = Offset(w * 0.68f, h * 0.28f)
            val escala = h * 0.20f
            val puntos = listOf(
                0.9f to -0.7f,
                0.5f to -0.8f,
                0.3f to -0.4f,
                0.8f to -0.1f,
                0.2f to 0.0f,
                0.5f to 0.7f,
                0.0f to 0.3f,
                -0.4f to 1.0f,
                -0.3f to 0.2f,
                -0.9f to 0.6f,
                -0.5f to -0.1f,
                -0.1f to -0.5f
            )

            val ave = Path()
            puntos.forEachIndexed { i, (dx, dy) ->
                val x = centroAve.x + dx * escala
                val y = centroAve.y + dy * escala
                if (i == 0) ave.moveTo(x, y) else ave.lineTo(x, y)
            }
            ave.close()
            drawPath(ave, color = dorado)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BanderaPapuaNuevaGuineaCLPreview() {
    Surface {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            BanderaPapuaNuevaGuineaCL(Modifier.fillMaxWidth().aspectRatio(4f / 3f))
        }
    }
}
