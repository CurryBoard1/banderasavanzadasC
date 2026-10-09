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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(modifier = Modifier.fillMaxSize()) {
                Box(contentAlignment = Alignment.Center) {
                    BanderaSudafricaCL(
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
fun BanderaSudafricaCL(modifier: Modifier = Modifier) {
    ConstraintLayout(modifier = modifier.fillMaxSize()) {
        val lienzo = createRef()
        val (cielo, mar) = createRefs()
        val horizonte = createGuidelineFromTop(0.5f)

        Box(
            modifier = Modifier
                .constrainAs(cielo) {
                    top.linkTo(parent.top)
                    bottom.linkTo(horizonte)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }
                .background(Color(0xFFDE3831))
        )

        Box(
            modifier = Modifier
                .constrainAs(mar) {
                    top.linkTo(horizonte)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }
                .background(Color(0xFF002395))
        )

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
            val cy = h / 2f

            val verde = Color(0xFF007A4D)
            val dorado = Color(0xFFFFB612)

            val arriba = Offset(0f, 0f)
            val abajo = Offset(0f, h)
            val union = Offset(h * 0.54f, cy)
            val derecha = Offset(w, cy)

            clipRect {
                val grosorBlanco = h * 0.30f
                drawLine(Color.White, arriba, union, grosorBlanco, cap = StrokeCap.Round)
                drawLine(Color.White, abajo, union, grosorBlanco, cap = StrokeCap.Round)
                drawLine(Color.White, union, derecha, grosorBlanco, cap = StrokeCap.Round)

                val grosorVerde = h * 0.20f
                drawLine(verde, arriba, union, grosorVerde, cap = StrokeCap.Round)
                drawLine(verde, abajo, union, grosorVerde, cap = StrokeCap.Round)
                drawLine(verde, union, derecha, grosorVerde, cap = StrokeCap.Round)

                val trianguloDorado = Path().apply {
                    moveTo(0f, h * 0.136f)
                    lineTo(h * 0.393f, cy)
                    lineTo(0f, h * 0.864f)
                    close()
                }
                drawPath(trianguloDorado, color = dorado)

                val trianguloNegro = Path().apply {
                    moveTo(0f, h * 0.204f)
                    lineTo(h * 0.320f, cy)
                    lineTo(0f, h * 0.796f)
                    close()
                }
                drawPath(trianguloNegro, color = Color.Black)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BanderaSudafricaCLPreview() {
    Surface {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            BanderaSudafricaCL(Modifier.fillMaxWidth().aspectRatio(3f / 2f))
        }
    }
}
