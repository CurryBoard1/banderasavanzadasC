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
                    BanderaTurquiaCL(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(3f / 2f)
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
        val angulo = Math.toRadians((180 + i * 36).toDouble())
        val x = cx + r * cos(angulo).toFloat()
        val y = cy + r * sin(angulo).toFloat()
        if (i == 0) path.moveTo(x, y) else path.lineTo(x, y)
    }
    path.close()
    return path
}

@Composable
fun BanderaTurquiaCL(modifier: Modifier = Modifier) {
    ConstraintLayout(modifier = modifier) {
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
            val rojo = Color(0xFFE30A17)
            val cy = h / 2f

            drawRect(color = rojo)

            drawCircle(
                color = Color.White,
                radius = h * 0.30f,
                center = Offset(w * 0.38f, cy)
            )
            drawCircle(
                color = rojo,
                radius = h * 0.24f,
                center = Offset(w * 0.38f + h * 0.09f, cy)
            )

            drawPath(
                path = crearEstrella(
                    cx = w * 0.38f + h * 0.42f,
                    cy = cy,
                    radio = h * 0.12f
                ),
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BanderaTurquiaCLPreview() {
    Surface {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            BanderaTurquiaCL(
                Modifier
                    .fillMaxWidth()
                    .aspectRatio(3f / 2f)
            )
        }
    }
}
