package rs.ac.metropolitan.cs330_projekat.ui.incomes

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
import rs.ac.metropolitan.cs330_projekat.data.model.Income
import rs.ac.metropolitan.cs330_projekat.ui.common.ViewModel


@Composable
fun IncomeDetails(vm: ViewModel, elementId: String) {
    IncomeBasicData(
        income = vm.getIncomes(Integer.parseInt(elementId)),
        goBack = { vm.goBack() },
        vm = vm
    )
}

@Composable
fun IncomeBasicData(income: Income?, vm: ViewModel, goBack: () -> Unit) {

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
                text = "Income details",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(
                    Alignment.Center
                )
            )
        }
        income?.let {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Text(
                    text = "Income",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(top = 16.dp)
                )
                Text(
                    text = "Source: ${it.source}", color = Color.Gray,
                    modifier = Modifier.padding(4.dp)
                )
                Text(
                    text = "Earning: ${it.earning} RSD", color = Color.Gray,
                    modifier = Modifier.padding(4.dp)
                )
                Text(
                    text = "Date: ${it.date}", color = Color.Gray,
                    modifier = Modifier.padding(4.dp)
                )
                Button(onClick = {
                    vm.deleteIncome(it.id)
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