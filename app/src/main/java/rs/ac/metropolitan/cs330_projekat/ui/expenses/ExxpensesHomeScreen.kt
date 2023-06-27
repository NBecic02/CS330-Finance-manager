package rs.ac.metropolitan.cs330_projekat.ui.expenses

import android.Manifest
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.lifecycle.viewmodel.compose.viewModel
import rs.ac.metropolitan.cs330_projekat.ui.common.ViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpensesHomeScreen(vm: ViewModel = viewModel()) {
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        vm.granted.value = isGranted
    }

    Column {
        if (!vm.granted.value) {
            StoragePermission(launcher)
        } else {
            Scaffold(topBar = {
                TopAppBar(
                    title = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Button(onClick = {
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
                    FloatingActionButton(onClick = { vm.navigateToNewExpense()}) {
                        Icon(Icons.Filled.Add, contentDescription = "Create expense!")
                    }
                }) { innerPadding ->
                ExpenseListPage(vm, innerPadding)
            }
        }
    }
}

@Composable
private fun StoragePermission(launcher: ManagedActivityResultLauncher<String, Boolean>) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Storage permission not granted",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.padding(8.dp)
            )
            Button(onClick = { launcher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE) }) {
                Text("Request permission")
            }
        }
    }
}