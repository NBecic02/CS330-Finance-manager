package rs.ac.metropolitan.cs330_projekat.ui.incomes

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.em
import androidx.lifecycle.viewmodel.compose.viewModel
import rs.ac.metropolitan.cs330_projekat.ui.common.ViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IncomesHomeScreen(vm: ViewModel = viewModel()) {
    Column {
        Scaffold(topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Button(
                            onClick = {
                                vm.navigateToExpenses()
                            }, colors = ButtonDefaults.buttonColors(Color.Transparent)

                        ) {
                            Text(text = "Expenses", color = Color.Black, fontSize = 7.em)
                        }
                        Button(onClick = {
                            vm.navigateToIncomes()
                        }, colors = ButtonDefaults.buttonColors(Color.Transparent)) {
                            Text(text = "Incomes", color = Color.Black, fontSize = 7.em)
                        }
                    }
                }
            )
        },
            floatingActionButton = {
                FloatingActionButton(onClick = { vm.navigateToNewIncome() }) {
                    Icon(Icons.Filled.Add, contentDescription = "Create income!")
                }
            }) { innerPadding ->
            IncomeListPage(vm, innerPadding)
        }
    }
}