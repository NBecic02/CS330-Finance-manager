package rs.ac.metropolitan.cs330_projekat.ui.expenses

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import rs.ac.metropolitan.cs330_projekat.data.model.Expense
import rs.ac.metropolitan.cs330_projekat.ui.common.ViewModel


@Composable
fun ExpenseDetails(vm: ViewModel, elementId: String) {
    ExpenseBasicData(
        expense = vm.getExpense(Integer.parseInt(elementId)),
        goBack = { vm.goBack() },
        vm = vm
    )
}

@Composable
fun ExpenseBasicData(expense: Expense?, vm: ViewModel, goBack: () -> Unit) {

    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        shape = MaterialTheme.shapes.large,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            IconButton(
                modifier = Modifier
                    .background(Color.Transparent)
                    .scale(1.5f),
                onClick = { goBack() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            Text(
                text = "Expense details",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(
                    Alignment.Center
                )
            )
        }
        expense?.let {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Text(
                    text = "Expense",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(top = 16.dp)
                )
                Text(
                    text = "Shop: ${it.shop}", color = Color.Gray,
                    modifier = Modifier.padding(4.dp)
                )
                Text(
                    text = "Price: ${it.price} RSD", color = Color.Gray,
                    modifier = Modifier.padding(4.dp)
                )
                Text(
                    text = "Date: ${it.date}", color = Color.Gray,
                    modifier = Modifier.padding(4.dp)
                )
                Button(onClick = {
                    vm.deleteExpense(it.id)
                    vm.goBack()
                }) {
                    Text(
                        text = "Delete", color = Color.White,
                        modifier = Modifier.padding(4.dp)
                    )
                }
            }
        }
    }
}