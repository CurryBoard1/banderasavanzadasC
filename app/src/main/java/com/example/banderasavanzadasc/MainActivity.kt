package com.example.banderasavanzadasc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
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
                    BanderaIsraelCL(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(11f / 8f)
                    )
                }
            }
        }
    }
}

private fun trianguloPath(cx: Float, cy: Float, r: Float, rotacion: Float): Path {
    val path = Path()
    for (i in 0..2) {
        val angulo = Math.toRadians((rotacion + i * 120).toDouble())
        val x = cx + r * cos(angulo).toFloat()
        val y = cy + r * sin(angulo).toFloat()
        if (i == 0) path.moveTo(x, y) else path.lineTo(x, y)
    }
    path.close()
    return path
}

@Composable
fun BanderaIsraelCL(modifier: Modifier = Modifier) {
    ConstraintLayout(modifier = modifier.fillMaxSize().background(Color.White)) {
        val (franjaArriba, franjaAbajo, estrella) = createRefs()
        val azul = Color(0xFF0038B8)

        val arribaInicio = createGuidelineFromTop(0.09f)
        val arribaFin = createGuidelineFromTop(0.25f)
        val abajoInicio = createGuidelineFromTop(0.75f)
        val abajoFin = createGuidelineFromTop(0.91f)

        Box(
            modifier = Modifier
                .constrainAs(franjaArriba) {
                    top.linkTo(arribaInicio)
                    bottom.linkTo(arribaFin)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }
                .background(azul)
        )

        Box(
            modifier = Modifier
                .constrainAs(franjaAbajo) {
                    top.linkTo(abajoInicio)
                    bottom.linkTo(abajoFin)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }
                .background(azul)
        )

        Canvas(
            modifier = Modifier.constrainAs(estrella) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            }
        ) {
            val cx = size.width / 2f
            val cy = size.height / 2f
            val r = size.height * 0.21f
            val trazo = Stroke(width = size.height * 0.035f)

            drawPath(trianguloPath(cx, cy, r, -90f), color = azul, style = trazo)
            drawPath(trianguloPath(cx, cy, r, 90f), color = azul, style = trazo)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BanderaIsraelCLPreview() {
    Surface {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            BanderaIsraelCL(Modifier.fillMaxWidth().aspectRatio(11f / 8f))
        }
    }
}
