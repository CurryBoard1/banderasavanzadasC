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
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(modifier = Modifier.fillMaxSize()) {
                Box(contentAlignment = Alignment.Center) {
                    BanderaButanCL(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(3f / 2f)
                    )
                }
            }
        }
    }
}

@Composable
fun BanderaButanCL(modifier: Modifier = Modifier) {
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
            val amarillo = Color(0xFFFFD520)
            val naranja = Color(0xFFFF4E12)

            val triAmarillo = Path().apply {
                moveTo(0f, 0f)
                lineTo(w, 0f)
                lineTo(0f, h)
                close()
            }
            val triNaranja = Path().apply {
                moveTo(w, 0f)
                lineTo(w, h)
                lineTo(0f, h)
                close()
            }
            drawPath(triAmarillo, color = amarillo)
            drawPath(triNaranja, color = naranja)

            val cx = w / 2f
            val cy = h / 2f
            val sx = w * 0.30f
            val sy = h * 0.28f

            val cuerpo = Path().apply {
                moveTo(cx - sx, cy + sy)
                quadraticTo(cx - sx * 0.8f, cy - sy * 0.2f, cx - sx * 0.35f, cy + sy * 0.1f)
                quadraticTo(cx, cy + sy * 0.5f, cx + sx * 0.3f, cy - sy * 0.1f)
                quadraticTo(cx + sx * 0.6f, cy - sy * 0.6f, cx + sx * 0.9f, cy - sy * 0.7f)
            }
            drawPath(
                path = cuerpo,
                color = Color.White,
                style = Stroke(width = h * 0.05f, cap = StrokeCap.Round)
            )

            val cabeza = Offset(cx + sx * 0.95f, cy - sy * 0.75f)
            drawCircle(color = Color.White, radius = h * 0.05f, center = cabeza)

            val grosorLinea = h * 0.02f
            drawLine(
                Color.White,
                cabeza,
                Offset(cabeza.x - h * 0.04f, cabeza.y - h * 0.09f),
                grosorLinea,
                cap = StrokeCap.Round
            )
            drawLine(
                Color.White,
                cabeza,
                Offset(cabeza.x + h * 0.03f, cabeza.y - h * 0.09f),
                grosorLinea,
                cap = StrokeCap.Round
            )

            drawLine(
                Color.White,
                Offset(cx - sx * 0.2f, cy + sy * 0.2f),
                Offset(cx - sx * 0.3f, cy + sy * 0.6f),
                grosorLinea,
                cap = StrokeCap.Round
            )
            drawLine(
                Color.White,
                Offset(cx + sx * 0.15f, cy + sy * 0.2f),
                Offset(cx + sx * 0.2f, cy + sy * 0.6f),
                grosorLinea,
                cap = StrokeCap.Round
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BanderaButanCLPreview() {
    Surface {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            BanderaButanCL(Modifier.fillMaxWidth().aspectRatio(3f / 2f))
        }
    }
}
