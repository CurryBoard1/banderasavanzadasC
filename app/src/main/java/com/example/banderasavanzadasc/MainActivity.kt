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
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import kotlin.math.hypot

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(modifier = Modifier.fillMaxSize()) {
                Box(contentAlignment = Alignment.Center) {
                    BanderaReinoUnidoCL(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(2f)
                    )
                }
            }
        }
    }
}

@Composable
fun BanderaReinoUnidoCL(modifier: Modifier = Modifier) {
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
            val azul = Color(0xFF012169)
            val rojo = Color(0xFFC8102E)
            val centro = Offset(w / 2f, h / 2f)

            clipRect {
                drawRect(color = azul)

                val grosorBlanca = h * 0.20f
                drawLine(Color.White, Offset(0f, 0f), Offset(w, h), grosorBlanca)
                drawLine(Color.White, Offset(w, 0f), Offset(0f, h), grosorBlanca)

                val largo = hypot(w, h)
                val corrimiento = h * 0.033f
                val grosorRoja = h * 0.067f
                val p1 = Offset(-h / largo, w / largo) * corrimiento
                val p2 = Offset(h / largo, w / largo) * corrimiento

                drawLine(rojo, centro + p1, Offset(0f, 0f) + p1, grosorRoja)
                drawLine(rojo, centro - p1, Offset(w, h) - p1, grosorRoja)
                drawLine(rojo, centro - p2, Offset(w, 0f) - p2, grosorRoja)
                drawLine(rojo, centro + p2, Offset(0f, h) + p2, grosorRoja)

                val anchoBlanca = h * 0.333f
                drawRect(
                    color = Color.White,
                    topLeft = Offset(0f, centro.y - anchoBlanca / 2f),
                    size = Size(w, anchoBlanca)
                )
                drawRect(
                    color = Color.White,
                    topLeft = Offset(centro.x - anchoBlanca / 2f, 0f),
                    size = Size(anchoBlanca, h)
                )

                val anchoRoja = h * 0.20f
                drawRect(
                    color = rojo,
                    topLeft = Offset(0f, centro.y - anchoRoja / 2f),
                    size = Size(w, anchoRoja)
                )
                drawRect(
                    color = rojo,
                    topLeft = Offset(centro.x - anchoRoja / 2f, 0f),
                    size = Size(anchoRoja, h)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BanderaReinoUnidoCLPreview() {
    Surface {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            BanderaReinoUnidoCL(Modifier.fillMaxWidth().aspectRatio(2f))
        }
    }
}
