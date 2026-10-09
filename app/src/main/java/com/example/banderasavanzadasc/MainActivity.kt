package com.example.banderasavanzadasc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(modifier = Modifier.fillMaxSize()) {
                Box(contentAlignment = Alignment.Center) {
                    BanderaSuizaCL(Modifier.fillMaxWidth())
                }
            }
        }
    }
}

@Composable
fun BanderaSuizaCL(modifier: Modifier = Modifier) {
    ConstraintLayout(
        modifier = modifier
            .aspectRatio(1f)
            .background(Color(0xFFD52B1E))
    ) {
        val (vertical, horizontal) = createRefs()

        val izquierda = createGuidelineFromStart(0.4f)
        val derecha = createGuidelineFromStart(0.6f)
        val largoInicioX = createGuidelineFromStart(0.19f)
        val largoFinX = createGuidelineFromStart(0.81f)
        val arriba = createGuidelineFromTop(0.4f)
        val abajo = createGuidelineFromTop(0.6f)
        val largoInicioY = createGuidelineFromTop(0.19f)
        val largoFinY = createGuidelineFromTop(0.81f)

        Box(
            modifier = Modifier
                .constrainAs(vertical) {
                    start.linkTo(izquierda)
                    end.linkTo(derecha)
                    top.linkTo(largoInicioY)
                    bottom.linkTo(largoFinY)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }
                .background(Color.White)
        )

        Box(
            modifier = Modifier
                .constrainAs(horizontal) {
                    start.linkTo(largoInicioX)
                    end.linkTo(largoFinX)
                    top.linkTo(arriba)
                    bottom.linkTo(abajo)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }
                .background(Color.White)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BanderaSuizaCLPreview() {
    Surface {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            BanderaSuizaCL(Modifier.fillMaxWidth())
        }
    }
}
