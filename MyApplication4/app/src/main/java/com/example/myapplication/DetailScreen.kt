package com.example.myapplication

//import com.example.myapplication.navigation.DetailDestination
//import com.example.myapplication.navigation.EditDestination
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.navigation.DetailDestination
import com.example.myapplication.navigation.EditDestination
import com.example.myapplication.ui.theme.Anzx100
import dev.enro.annotations.NavigationDestination
import dev.enro.core.compose.navigationHandle
import dev.enro.core.push


@Composable
@NavigationDestination(DetailDestination::class)
fun DetailScreen(
    //destination: DetailDestination
    goalName: String = "",targetAmount: Int = 0, currentAmount: Int = 0
) {
//    val exampleResultChannel = registerForNavigationResult<DetailDestination> { result ->
//        val goalName = result.goalName
//        val targetAmount = result.targetAmount
//        val currentAmount = result.currentAmount
//    }
    val navigation = navigationHandle<DetailDestination>()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleForEachPage("Saving Goal!", "Saving for your future :)")
        ProgressCircle(
            modifier = Modifier
                .padding(top = 30.dp),
            targetAmount = targetAmount,
            currentAmount = currentAmount,
            radius = 70.dp,
            strokeWidth = 90f,
            fontSize = 50.sp
        )
        Text(
            modifier = Modifier.padding(top = 20.dp),
            text = "$goalName",
            fontSize = 30.sp,
            color = Color.Gray,
            fontWeight = FontWeight.Medium,
            )

        Text(
            modifier = Modifier.padding(top = 30.dp),
            color = Color.Gray,
            fontWeight = FontWeight.Medium,
            text = "Target Amount: $targetAmount",
            fontSize = 25.sp
        )

        Text(text = "Current Amount: $currentAmount",
            modifier = Modifier
                .padding(top = 20.dp),
            color = Color.Gray,
            fontWeight = FontWeight.Medium,
            fontSize = 25.sp
        )


        CustomButton(text = "Edit",
            modifier = Modifier.padding(top = 80.dp),
            onClick = {
                navigation.push(EditDestination())
                })

        CustomButton(text = "Delete",
            modifier = Modifier
                .padding(top = 20.dp),
            containerColor = Color.White,
            textColor = Anzx100,
            borderColor = Anzx100,
            onClick = { /*TODO*/ })
    }
}
@Composable
fun CustomButton(text: String,
                 modifier: Modifier = Modifier,
                 containerColor: Color = Anzx100,
                 textColor: Color = Color.White,
                 width: Dp = 280.dp,
                 height: Dp = 50.dp,
                 borderColor: Color = Anzx100,
                 borderWidth: Dp = 2.dp,
                 cornerRadius: Dp = 24.dp,
                 fontSize: TextUnit = 20.sp,
                 onClick: () -> Unit) {
    Button(modifier = modifier

        .size(width = width, height = height)
        .border(
            borderWidth,
            borderColor,
            shape = RoundedCornerShape(cornerRadius)
        ),
        colors = ButtonDefaults.buttonColors(containerColor = containerColor),
        onClick = onClick) {
        Text(
            color = textColor,
            text = text,
            fontSize = fontSize)
    }
}

//@Preview(showBackground = true)
//@Composable
//fun DetailPreview() {
//    //DetailScreen(100,80,"PS5")
//}
