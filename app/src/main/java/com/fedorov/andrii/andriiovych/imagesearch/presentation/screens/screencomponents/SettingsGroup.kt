package com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.screencomponents

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.fedorov.andrii.andriiovych.imagesearch.ui.theme.SettingsBackground

@Composable
fun SettingsGroup(
    @StringRes name: Int,
    content: @Composable ColumnScope.() -> Unit ){
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(stringResource(id = name), color = Color.White)
        Spacer(modifier = Modifier.height(8.dp))
        Surface(
            color = SettingsBackground,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(4),
        ) {
            Column {
                content()
            }
        }
    }
}