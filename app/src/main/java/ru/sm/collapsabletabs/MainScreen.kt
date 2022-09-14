@file:OptIn(ExperimentalMaterial3Api::class)

package ru.sm.collapsabletabs

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

private const val Tag = "MainScreen"

@Composable
fun MainScreen() {
    CollapsableTopBarScaffold(
        topBar = { FakeTabs() },
        content = { ScrollableContent() }
    )
}

@Composable
private fun ScrollableContent(
    modifier: Modifier = Modifier
) {
    val numbers = remember { (1..30).toList() }
    LazyColumn(modifier = modifier) {
        items(
            items = numbers,
            key = { it }
        ) { item ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(if (item % 2 == 0) Color.White else Color.LightGray),
                contentAlignment = Alignment.Center
            ) { Text(text = item.toString()) }
        }
    }
}

private class TopBarState {
    var maxHeight = 0F
    var offset by mutableStateOf(0F)
}

@Composable
private fun CollapsableTopBarScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    val state = remember { TopBarState() }
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                Log.d(Tag, "onPreScroll: available = $available, source = $source")
                val prevCollapsableOffset = state.offset
                state.offset = (state.offset + available.y).coerceIn(-state.maxHeight, 0F)
                return if (prevCollapsableOffset != state.offset) {
                    available.copy(x = 0f)
                } else {
                    Offset.Zero
                }
            }
        }
    }
    Layout(
        modifier = modifier.nestedScroll(nestedScrollConnection),
        content = {
            topBar()
            content()
        }
    ) { measurables, constraints ->
        val localConstraints = constraints.copy(minHeight = 0)
        val topBarPlaceable = measurables.first().measure(localConstraints)
        val contentPlaceable = measurables.last().measure(localConstraints)
        if (state.maxHeight == 0F) {
            state.maxHeight = topBarPlaceable.height.toFloat()
        }
        layout(constraints.maxWidth, constraints.maxHeight) {
            topBarPlaceable.place(0, state.offset.roundToInt())
            contentPlaceable.place(0, state.offset.roundToInt() + topBarPlaceable.height)
        }
    }
}


@Composable
private fun FakeTabs(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Yellow)
    ) {
        (1..3).forEach { number ->
            Box(
                modifier = Modifier
                    .height(80.dp)
                    .weight(1F)
            ) {
                Text(
                    text = "Tab $number",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}