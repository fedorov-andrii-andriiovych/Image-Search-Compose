package com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.settingsscreen.settingscomponents

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fedorov.andrii.andriiovych.imagesearch.R
import com.fedorov.andrii.andriiovych.imagesearch.ui.theme.DividerBackground
import com.fedorov.andrii.andriiovych.imagesearch.ui.theme.SecondaryBackground
import com.fedorov.andrii.andriiovych.imagesearch.ui.theme.SettingsSecondColor

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SettingsClickableItem(
    @DrawableRes icon: Int,
    @StringRes iconDesc: Int,
    @StringRes name: Int,
    description: String,
    onClick: () -> Unit
) {
    Surface(
        color = SecondaryBackground,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        onClick = onClick,
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painterResource(id = icon),
                        contentDescription = stringResource(id = iconDesc),
                        modifier = Modifier
                            .size(32.dp),
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column() {
                        Text(
                            text = stringResource(id = name),
                            textAlign = TextAlign.Start,
                            overflow = TextOverflow.Ellipsis,
                            color = Color.White
                        )
                        Text(
                            text = description,
                            color = SettingsSecondColor,
                            fontSize = 14.sp,
                            modifier = Modifier
                                .padding(bottom = 4.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.weight(1.0f))
                Icon(
                    Icons.Rounded.KeyboardArrowRight,
                    contentDescription = stringResource(id = R.string.arrow_forward),
                    tint = Color.White
                )
            }
            Divider(color = DividerBackground)
        }

    }
}