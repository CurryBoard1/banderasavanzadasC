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
                    BanderaCubaCL(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(2f)
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
fun BanderaCubaCL(modifier: Modifier = Modifier) {
    ConstraintLayout(modifier = modifier.fillMaxSize().background(Color.White)) {
        val (franja1, franja2, franja3, triangulo) = createRefs()
        val azul = Color(0xFF002E6E)

        val g1 = createGuidelineFromTop(0.2f)
        val g2 = createGuidelineFromTop(0.4f)
        val g3 = createGuidelineFromTop(0.6f)
        val g4 = createGuidelineFromTop(0.8f)

        Box(
            modifier = Modifier
                .constrainAs(franja1) {
                    top.linkTo(parent.top)
                    bottom.linkTo(g1)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }
                .background(azul)
        )
        Box(
            modifier = Modifier
                .constrainAs(franja2) {
                    top.linkTo(g2)
                    bottom.linkTo(g3)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }
                .background(azul)
        )
        Box(
            modifier = Modifier
                .constrainAs(franja3) {
                    top.linkTo(g4)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }
                .background(azul)
        )

        Canvas(
            modifier = Modifier.constrainAs(triangulo) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            }
        ) {
            val h = size.height
            val anchoTriangulo = h * 0.866f

            val formaTriangulo = Path().apply {
                moveTo(0f, 0f)
                lineTo(anchoTriangulo, h / 2f)
                lineTo(0f, h)
                close()
            }
            drawPath(formaTriangulo, color = Color(0xFFCB1428))

            drawPath(
                path = crearEstrella(
                    cx = anchoTriangulo / 3f,
                    cy = h / 2f,
                    radio = h * 0.13f
                ),
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BanderaCubaCLPreview() {
    Surface {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            BanderaCubaCL(Modifier.fillMaxWidth().aspectRatio(2f))
        }
    }
}
