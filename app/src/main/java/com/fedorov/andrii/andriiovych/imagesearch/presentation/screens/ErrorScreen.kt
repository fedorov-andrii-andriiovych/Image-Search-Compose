package com.fedorov.andrii.andriiovych.imagesearch.presentation.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fedorov.andrii.andriiovych.imagesearch.R
import kotlinx.coroutines.launch


@Composable
fun ErrorScreen(message: String, onClickRetry: () -> Unit) {
    Surface(color = MaterialTheme.colors.background) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = message,
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.padding(top = 48.dp))
            AnimationButton(
                text = stringResource(R.string.retry),
                onClickRetry = {
                    onClickRetry()
                })
        }
    }
}

@Composable
fun AnimationButton(
    text: String,
    onClickRetry: () -> Unit,
    animationDuration: Int = 100,
    scaleDown: Float = 0.9f
) {

    val interactionSource = MutableInteractionSource()

    val coroutineScope = rememberCoroutineScope()

    val scale = remember {
        Animatable(1f)
    }

    Box(
        modifier = Modifier
            .scale(scale = scale.value)
            .background(
                color = Color.Black,
                shape = RoundedCornerShape(25.dp)
            )
            .border(border = BorderStroke(1.dp, Color.White),
                shape = RoundedCornerShape(25.dp))
            .clickable(interactionSource = interactionSource, indication = null) {
                onClickRetry()
                coroutineScope.launch {
                    scale.animateTo(
                        scaleDown,
                        animationSpec = tween(animationDuration),
                    )
                    scale.animateTo(
                        1f,
                        animationSpec = tween(animationDuration),
                    )
                }
            }
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 36.dp, vertical = 12.dp),
            fontSize = 26.sp,
            color = Color.Yellow,
            fontWeight = FontWeight.Medium
        )
    }

}
