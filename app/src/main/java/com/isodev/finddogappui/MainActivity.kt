package com.isodev.finddogappui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.isodev.finddogappui.ui.theme.FindDogAppUiTheme
import kotlin.math.roundToInt


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FindDogAppUiTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier) {
    val sampleImages = listOf(
        R.drawable.dog1,
        R.drawable.dog2,
        R.drawable.dog3,
        R.drawable.dog1,
        R.drawable.dog2,
        R.drawable.dog3,
    )
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xF7B327).copy(alpha = 0.3f), Color(0x00C4C4C4)),
                    start = Offset(0f, 0f),
                    end = Offset(0f, Float.POSITIVE_INFINITY)
                )
            )
    ) {
        Image(
            painter = painterResource(R.drawable.profile_icon), contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 25.dp)
                .offset(y = 50.dp)
                .size(50.dp)
        )
        Image(
            painter = painterResource(R.drawable.location), contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(y = 130.dp)
                .padding(start = 25.dp)
        )
        Image(
            painter = painterResource(R.drawable.london__uk), contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(y = 150.dp)
                .padding(start = 25.dp)
        )
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(y = 190.dp)
                .padding(start = 25.dp)


        ) {
            Image(
                painter = painterResource(R.drawable.adjust_horizontal_settings),
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )
        }
        DogsLazy(
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(y = 190.dp)
                .padding(start = 75.dp), images = sampleImages
        )
        SwipeCardExample(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = 155.dp)
        )
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 25.dp)
                .offset(y = 50.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.top_end_icon), contentDescription = null
            )
        }
        NavBar(modifier = Modifier.align(Alignment.BottomCenter))
    }
}


@Composable
fun DogsLazy(modifier: Modifier = Modifier, images: List<Int>) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        items(images) { imageResId ->
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(CircleShape)
                    .background(Color(0x99F7B327))
            ) {
                Image(
                    painter = painterResource(id = imageResId),
                    contentDescription = null,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
fun NavBar(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .align(Alignment.Center)
                .shadow(elevation = 32.dp)
        ) {
            val cornerRadius = 32.dp.toPx()
            val path = Path().apply {
                moveTo(0f, cornerRadius)
                lineTo(0f, size.height)
                lineTo(size.width, size.height)
                lineTo(size.width, cornerRadius)
                arcTo(
                    rect = Rect(0f, 0f, cornerRadius * 2, cornerRadius * 2),
                    startAngleDegrees = 180f,
                    sweepAngleDegrees = 90f,
                    forceMoveTo = false
                )
                lineTo(size.width - cornerRadius, 0f)
                arcTo(
                    rect = Rect(size.width - cornerRadius * 2, 0f, size.width, cornerRadius * 2),
                    startAngleDegrees = 270f,
                    sweepAngleDegrees = 90f,
                    forceMoveTo = false
                )
                close()
            }
            drawPath(
                path = path,
                color = Color.White
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .height(90.dp)
                .align(Alignment.Center)
        ) {
            IconButton(
                onClick = { /*TODO*/ }
            ) {
                Image(
                    painter = painterResource(R.drawable.home_icon),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Image(
                    painter = painterResource(R.drawable.text_icon),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
            Image(
                painter = painterResource(R.drawable.add_icon),
                contentDescription = null,
                modifier = Modifier
                    .offset(y = -30.dp)
            )
            IconButton(onClick = { /*TODO*/ }) {
                Image(
                    painter = painterResource(R.drawable.favorite_icon),
                    contentDescription = null,
                    modifier = Modifier.size(28.dp)
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Image(
                    painter = painterResource(R.drawable.profile_icon_nav),
                    contentDescription = null,
                    modifier = Modifier.size(28.dp)
                )
            }
        }
    }

}

@Composable
fun SwipeCardExample(modifier: Modifier) {
    val images = remember {
        mutableStateListOf(
            CardData(0, R.drawable.card_dog_1),
            CardData(1, R.drawable.card_dog_2),
            CardData(2, R.drawable.card_dog_3),
        )
    }
    var currentImageIndex by remember { mutableStateOf(0) }
    val iconStates = remember { mutableStateMapOf<Int, Pair<Boolean, Boolean>>() }
    val cardModifier = modifier
        .size(266.dp, 378.dp)

    Box(modifier = modifier.fillMaxSize()) {
        for (i in 0 until images.size) {
            val imageIndex = (currentImageIndex - i + images.size) % images.size
            val rotation = when (i) {
                0 -> -15f
                1 -> 15f
                2 -> 0f
                else -> 0f
            }
            SwipeCard(
                modifier = cardModifier
                    .zIndex((images.size + i).toFloat())
                    .graphicsLayer {
                        rotationZ = rotation
                    },
                cardData = images[imageIndex],
                onSwipeLeft = { cardData ->
                    iconStates[cardData.id] = true to false
                },
                onSwipeRight = { cardData ->
                    iconStates[cardData.id] = false to true
                },
                onDragEnd = { cardData ->
                    iconStates[cardData.id] = false to false
                    currentImageIndex = (currentImageIndex + 1) % images.size
                },
                sensitivityFactor = 3f
            ) { cardData ->
                Card(
                    modifier = Modifier.wrapContentSize(),
                    shape = RoundedCornerShape(16.dp),
                    border = BorderStroke(4.dp, Color.White)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.wrapContentSize()
                    ) {
                        Image(
                            painter = painterResource(images[imageIndex].image),
                            contentDescription = null,
                            modifier = Modifier
                                .align(Alignment.Center)
                                .fillMaxSize()
                        )
                        Row(
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(16.dp)
                        ) {
                            val (favIcon, closeIcon) = iconStates[cardData.id] ?: (false to false)
                            GradientCircleWithIcon(
                                icon = Icons.Default.FavoriteBorder,
                                selectedIcon = favIcon
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            GradientCircleWithIcon(
                                icon = Icons.Default.Close,
                                selectedIcon = closeIcon
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SwipeCard(
    modifier: Modifier,
    cardData: CardData,
    onSwipeLeft: (CardData) -> Unit = {},
    onSwipeRight: (CardData) -> Unit = {},
    onDragEnd: (CardData) -> Unit = {},
    sensitivityFactor: Float = 3f,
    content: @Composable (CardData) -> Unit,
) {
    var offset by remember { mutableStateOf(0f) }
    var dismissRight by remember { mutableStateOf(false) }
    var dismissLeft by remember { mutableStateOf(false) }
    val density = LocalDensity.current.density

    LaunchedEffect(dismissRight) {
        if (dismissRight) {
            onSwipeRight.invoke(cardData)
            dismissRight = false
        }
    }

    LaunchedEffect(dismissLeft) {
        if (dismissLeft) {
            onSwipeLeft.invoke(cardData)
            dismissLeft = false
        }
    }
    var dragStarted by remember { mutableStateOf(false) }

    Box(modifier = modifier
        .offset { IntOffset(offset.roundToInt(), 0) }
        .pointerInput(Unit) {
            detectHorizontalDragGestures(
                onDragStart = { dragStarted = true },
                onDragEnd = {
                    dragStarted = false
                    offset = 0f
                    onDragEnd(cardData)
                    dismissLeft = false
                    dismissRight = false
                }
            ) { change, dragAmount ->
                if (dragStarted) {
                    offset += (dragAmount / density) * sensitivityFactor
                }

                if (offset > 0) {
                    dismissRight = true
                } else if (offset < 0) {
                    dismissLeft = true
                }

                if (change.positionChange() != Offset.Zero) change.consume()
            }
        }
        .graphicsLayer(
            alpha = 10f - animateFloatAsState(if (dismissRight) 1f else 0f).value,
            rotationZ = animateFloatAsState(offset / 50).value
        )) {
        content(cardData)
    }
}

@Composable
fun GradientCircleWithIcon(icon: ImageVector, selectedIcon: Boolean) {
    Box(
        modifier = Modifier
            .size(53.dp)
    ) {
        Canvas(
            modifier = Modifier
                .matchParentSize()
        ) {
            val circleColor = if (selectedIcon) {
                Brush.linearGradient(
                    colors = listOf(Color(0xFFEF7E06), Color(0xFFF7B327)),
                    start = Offset.Zero,
                    end = Offset.Infinite
                )
            } else {
                SolidColor(Color.White)
            }

            drawCircle(
                brush = circleColor,
                radius = size.minDimension / 2,
                center = Offset(size.width / 2, size.height / 2)
            )
        }
        Icon(
            imageVector = icon,
            contentDescription = "Icon",
            tint = if (selectedIcon) Color.White else Color(0xFFEF7E06),
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.Center)
        )
    }
}

data class CardData(
    val id: Int,
    val image: Int,
    var favIcon: Boolean = false,
    var closeIcon: Boolean = false
)