package rs.ac.metropolitan.cs330_projekat.ui.incomes


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
import rs.ac.metropolitan.cs330_projekat.data.model.Income
import rs.ac.metropolitan.cs330_projekat.ui.common.ViewModel

@Composable
fun IncomeListPage(vm: ViewModel, paddingValues: PaddingValues) {
    val incomes = vm.incomes.observeAsState()
    LaunchedEffect(vm.loadIncome()) {
    }

    LazyColumn(modifier = Modifier
        .padding(paddingValues)
        .fillMaxWidth()) {
        incomes.value?.let {
            items(it) { income ->
                IncomeCardView(income) {
                    vm.navigateToIncomeDetails(it)
                }
            }
        }
    }
}

@Composable
fun IncomeCardView(income: Income, onSelected: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onSelected(income.id.toString()) }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = income.source,
                fontSize = 18.sp,
                modifier = Modifier.padding(15.dp)
            )
            Text(
                text = "+${income.earning} RSD",
                color = Color.Green,
                fontSize = 20.sp,
                modifier = Modifier.padding(10.dp)
            )
            Text(
                text = income.date,
                color = Color.Gray,
                fontSize = 20.sp,
                modifier = Modifier.padding(10.dp)
            )
        }
    }
}