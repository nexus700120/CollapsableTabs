@file:OptIn(ExperimentalMotionApi::class)

package ru.sm.collapsabletabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout

private const val Tabs = "motion_tabs"
private const val Content = "motion_content"

@Composable
fun MotionScreen() {
//    ConstraintLayout(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.Cyan),
//        constraintSet = ConstraintSet {
//            val tabs = createRefFor(Tabs)
//            constrain(tabs) {
//                start.linkTo(parent.start)
//                end.linkTo(parent.end)
//                top.linkTo(parent.top)
//                width = Dimension.matchParent
//            }
//
//            val content = createRefFor(Content)
//            constrain(content) {
//                start.linkTo(parent.start)
//                end.linkTo(parent.end)
//                bottom.linkTo(parent.bottom)
//                top.linkTo(tabs.bottom)
//                width = Dimension.matchParent
//                height = Dimension.fillToConstraints
//            }
//        }
//    ) {
//        Box(
//            modifier = Modifier
//                .layoutId(Tabs)
//                .size(100.dp)
//                .background(Color.Blue.copy(alpha = 0.5F))
//        )
//
//        val numbers = remember { (1..100).toList() }
//        LazyColumn(
//            modifier = Modifier
//                .layoutId(Content)
//                .size(200.dp)
//                .background(Color.Green.copy(alpha = 0.5F))
//        ) {
//            items(numbers) {
//                Text(
//                    text = "Item $it",
//                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
//                )
//            }
//        }
//    }

    MotionLayout(
        
        start = ConstraintSet {
            val tabs = createRefFor(Tabs)
            constrain(tabs) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                width = Dimension.matchParent
            }

            val content = createRefFor(Content)
            constrain(content) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
                top.linkTo(tabs.bottom)
                width = Dimension.matchParent
                height = Dimension.fillToConstraints
            }
        },
        end = ConstraintSet {
            val tabs = createRefFor(Tabs)
            constrain(tabs) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                width = Dimension.matchParent
            }

            val content = createRefFor(Content)
            constrain(content) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
                top.linkTo(tabs.bottom)
                width = Dimension.matchParent
                height = Dimension.fillToConstraints
            }
        },
        progress = 0F
    ) {
        Box(
            modifier = Modifier
                .layoutId(Tabs)
                .size(100.dp)
                .background(Color.Blue.copy(alpha = 0.5F))
        )

        val numbers = remember { (1..100).toList() }
        LazyColumn(
            modifier = Modifier
                .layoutId(Content)
                .size(200.dp)
                .background(Color.Green.copy(alpha = 0.5F))
        ) {
            items(numbers) {
                Text(
                    text = "Item $it",
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                )
            }
        }
    }
//    ConstraintLayout(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.Cyan),
//        constraintSet = ConstraintSet {
//            val tabs = createRefFor(Tabs)
//            constrain(tabs) {
//                start.linkTo(parent.start)
//                end.linkTo(parent.end)
//                top.linkTo(parent.top)
//                width = Dimension.matchParent
//            }
//
//            val content = createRefFor(Content)
//            constrain(content) {
//                start.linkTo(parent.start)
//                end.linkTo(parent.end)
//                bottom.linkTo(parent.bottom)
//                top.linkTo(tabs.bottom)
//                width = Dimension.matchParent
//                height = Dimension.fillToConstraints
//            }
//        }
//    ) {
//        Box(
//            modifier = Modifier
//                .layoutId(Tabs)
//                .size(100.dp)
//                .background(Color.Blue.copy(alpha = 0.5F))
//        )
//
//        val numbers = remember { (1..100).toList() }
//        LazyColumn(
//            modifier = Modifier
//                .layoutId(Content)
//                .size(200.dp)
//                .background(Color.Green.copy(alpha = 0.5F))
//        ) {
//            items(numbers) {
//                Text(
//                    text = "Item $it",
//                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
//                )
//            }
//        }
//    }
}

//@Composable
//fun ScrollTextDSL() {
//    val scroll = rememberScrollState(0)
//    val big = 250.dp
//    val small = 50.dp
//    var scene = MotionScene() {
//        val start1 = ConstraintSet {
//            val title = createRefFor("title")
//            val image = createRefFor("image")
//            val icon = createRefFor("icon")
//            constrain(title) {
//                bottom.linkTo(image.bottom)
//                start.linkTo(image.start)
//            }
//            constrain(image) {
//                width = Dimension.matchParent
//                height = Dimension.value(big)
//                top.linkTo(parent.top)
//                customColor("cover", Color(0x000000FF))
//            }
//            constrain(icon) {
//                top.linkTo(image.top, 16.dp)
//                start.linkTo(image.start, 16.dp)
//                alpha = 0f
//            }
//        }
//        val end1 = ConstraintSet {
//            val title = createRefFor("title")
//            val image = createRefFor("image")
//            val icon = createRefFor("icon")
//            constrain(title) {
//                bottom.linkTo(image.bottom)
//                start.linkTo(icon.end)
//                centerVerticallyTo(image)
//                scaleX = 0.7f
//                scaleY = 0.7f
//            }
//            constrain(image) {
//                width = Dimension.matchParent
//                height = Dimension.value(small)
//                top.linkTo(parent.top)
//                customColor("cover", Color(0xFF0000FF))
//            }
//            constrain(icon) {
//                top.linkTo(image.top, 16.dp)
//                start.linkTo(image.start, 16.dp)
//            }
//        }
//        transition("default", start1, end1) {}
//    }
//
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = Modifier.verticalScroll(scroll)
//    ) {
//        Spacer(Modifier.height(big))
//        repeat(5) {
//            Text(
//                text = LoremIpsum(222).values.first(),
//                modifier = Modifier
//                    .background(Color.White)
//                    .padding(16.dp)
//            )
//        }
//    }
//    val gap =  with(LocalDensity.current){big.toPx() - small.toPx()}
//    val progress = min(scroll.value / gap, 1f);
//
//    MotionLayout(
//        modifier = Modifier.fillMaxSize(),
//        motionScene = scene,
//        progress = progress
//    ) {
//        Image(
//            modifier = Modifier.layoutId("image"),
//            painter = painterResource(R.drawable.bridge),
//            contentDescription = null,
//            contentScale = ContentScale.Crop
//        )
//        Box(modifier = Modifier
//            .layoutId("image")
//            .background(motionProperties("image").value.color("cover"))) {
//        }
//        Image(
//            modifier = Modifier.layoutId("icon"),
//            painter = painterResource(R.drawable.menu),
//            contentDescription = null
//        )
//        Text(
//            modifier = Modifier.layoutId("title"),
//            text = "San Francisco",
//            fontSize = 30.sp,
//            color = Color.White
//        )
//    }
//}