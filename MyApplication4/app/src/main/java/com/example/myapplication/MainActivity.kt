package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.navigation.HomeDestination
import com.example.myapplication.ui.theme.Anzx100
import com.example.myapplication.ui.theme.Brand200
import com.example.myapplication.ui.theme.Primary500
import dev.enro.core.compose.rememberNavigationContainer
import dev.enro.core.container.EmptyBehavior
import dev.enro.core.controller.NavigationApplication
import dev.enro.core.controller.createNavigationController

data class ClickableBoxData(
    val isEdit: Boolean,
    val text: String,
    val targetAmount: Int = 0,
    val currentAmount: Int = 0,
    val onClick: () -> Unit = {}
)

//@NavigationComponent
class MainActivity: ComponentActivity(), NavigationApplication {

    override val navigationController = createNavigationController { }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val container = rememberNavigationContainer(
                root = HomeDestination(),
                emptyBehavior = EmptyBehavior.AllowEmpty,
            )
            Box(modifier = Modifier.fillMaxSize()) {
                container.Render()
            }
//            MyApplicationTheme {
//                container.open(HomeScreen())
//            }
        }
    }
}

@Composable
fun TitleForEachPage(Header: String, SubHeader: String) {
    Text(
        modifier = Modifier
            .padding(top = 60.dp),
        text = "$Header",
        color = Brand200,
        fontSize = 40.sp,
        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
    )
    Text(
        modifier = Modifier
            .padding(top = 8.dp),
        text = "$SubHeader",
        color = Primary500,
        fontSize = 16.sp,
        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
    )
}
@Composable
fun ProgressCircle(targetAmount: Int, currentAmount: Int,modifier: Modifier = Modifier, fontSize: TextUnit = 30.sp, radius: Dp = 35.dp, color: Color = Anzx100, strokeWidth: Float = 60f, animDuration: Int = 1000, animDelay: Int = 0) {
    var anamationPlayed by remember { mutableStateOf(false) }
    val calculatedPercentage: Float = currentAmount.toFloat() / targetAmount.toFloat()
    val curPercentage = animateFloatAsState(
        targetValue = if (anamationPlayed) calculatedPercentage.toFloat() else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = animDelay
        )
    )
    LaunchedEffect(key1 = true) {
        anamationPlayed = true
        
    }
    Box(contentAlignment = Alignment.Center,
        modifier = modifier
            .padding(30.dp)
            .size(radius * 2)

    ) {
        Canvas(
            modifier = Modifier
                .size(radius * 2)
                .align(Alignment.Center)


        ) {
            drawArc(
                color = Anzx100,
                startAngle = -90f,
                sweepAngle = 360f * curPercentage.value,
                useCenter = false,
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
            )
        }
        Text(
            text = "${currentAmount}",
            fontSize = fontSize,
            color = Primary500,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Medium,
            //textAlign = TextAlign.Center,
            modifier = Modifier
                .align(alignment = Alignment.Center)

        )
    }

}
@Composable
fun CustomRow(boxes: List<ClickableBoxData>) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        boxes.forEach { boxData ->
            ClickableBox(
                isEdit = boxData.isEdit,
                text = boxData.text,
                targetAmount = boxData.targetAmount,
                currentAmount = boxData.currentAmount,
                onClick = boxData.onClick,
            )
        }
    }
}
@Composable
fun ClickableBox(isEdit: Boolean, text:String,targetAmount:Int = 0,currentAmount:Int = 0, modifier: Modifier = Modifier, fontSize: TextUnit = 20.sp, onClick: () -> Unit = {}) {
    Box(modifier = Modifier
        .padding(24.dp)
        .shadow(
            elevation = 4.dp,
            shape = RoundedCornerShape(8.dp)
        )
        .clip(RoundedCornerShape(8.dp))
        .border(1.dp, Color.White)
        .background(Color.White)
        .size(150.dp),
        Alignment.TopCenter

    ) {
        if(isEdit){
            ProgressCircle(targetAmount = targetAmount, currentAmount = currentAmount)
        }else{

            Text(
                text = "+",
                color = Color.LightGray,
                fontSize = 80.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Text(
            text = "$text",
            fontSize = fontSize,
            color = Color.LightGray,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 5.dp)
                .align(alignment = Alignment.BottomCenter)

        )

    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//
//        ClickableBox(isEdit = false, text = "")
//
//}
