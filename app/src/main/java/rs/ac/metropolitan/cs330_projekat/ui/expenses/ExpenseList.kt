package rs.ac.metropolitan.cs330_projekat.ui.expenses

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import rs.ac.metropolitan.cs330_projekat.data.model.Expense
import rs.ac.metropolitan.cs330_projekat.ui.common.ViewModel

@Composable
fun ExpenseListPage(vm: ViewModel, paddingValues: PaddingValues) {
    val expenses = vm.expenses.observeAsState()
    LaunchedEffect(vm.loadExpenses()) {
    }

    LazyColumn(modifier = Modifier.padding(paddingValues).fillMaxWidth()) {
        expenses.value?.let {
            items(it) { expense ->
                ExpenseCardView(expense) {
                    vm.navigateToExpenseDetails(it)
                }
            }
        }
    }
}

@Composable
fun ExpenseCardView(expense: Expense, onSelected: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onSelected(expense.id.toString()) }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = expense.shop,
                fontSize = 20.sp,
                modifier = Modifier.padding(10.dp)
            )
            Text(
                text = "-${expense.price} RSD",
                color = Color.Red,
                fontSize = 20.sp,
                modifier = Modifier.padding(10.dp)
            )
            Text(
                text = expense.date,
                color = Color.Gray,
                fontSize = 20.sp,
                modifier = Modifier.padding(10.dp)
            )
        }
    }
}