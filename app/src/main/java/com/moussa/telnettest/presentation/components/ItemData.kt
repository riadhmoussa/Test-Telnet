package com.moussa.telnettest.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.moussa.telnettest.util.dateFormatter


@Composable
fun ItemData(label:String,data:String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.weight(8f)
        )
        Text(
            text = data,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.weight(8f)
        )
    }
}