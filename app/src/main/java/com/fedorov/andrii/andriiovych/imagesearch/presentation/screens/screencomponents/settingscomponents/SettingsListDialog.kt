package com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.screencomponents.settingscomponents

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fedorov.andrii.andriiovych.imagesearch.presentation.viewmodels.Settings
import com.fedorov.andrii.andriiovych.imagesearch.ui.theme.DividerBackground
import com.fedorov.andrii.andriiovych.imagesearch.ui.theme.SettingsBackground

@Composable
fun SettingsListDialog(
    dialogParams: DialogParams,
    onItemClicked: (String,Settings) -> Unit,
    onDismissClicked: () -> Unit
) {
    AlertDialog(
        modifier = Modifier.border(
            BorderStroke(1.dp, Color.White),
            shape = RoundedCornerShape(25.dp)
        ),
        onDismissRequest = {
            onDismissClicked()
        },
        text = {
            LazyColumn(
                modifier = Modifier.fillMaxWidth()

            ) {
                item {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = dialogParams.title,
                        color = Color.White,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(50.dp))
                }
                items(dialogParams.listSettings) { item ->
                    SettingsItem(name = item,
                        onItemClicked = { name ->
                            onItemClicked(name,dialogParams.type)
                        })
                }

            }
        },
        shape = RoundedCornerShape(25.dp),
        buttons = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp), contentAlignment = Alignment.CenterEnd
            ) {
                TextButton(onClick = { onDismissClicked() }) {
                    Text(text = "Dismiss", color = Color.White)
                }
            }
        },
        backgroundColor = SettingsBackground
    )
}

@Composable
fun SettingsItem(name: String, onItemClicked: (String) -> Unit) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)
        .clickable {
            onItemClicked(name)
        }
    ) {
        Text(text = name, color = Color.White, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Divider(color = DividerBackground)
    }
}