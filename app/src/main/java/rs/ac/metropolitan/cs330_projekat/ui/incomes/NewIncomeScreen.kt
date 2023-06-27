package rs.ac.metropolitan.cs330_projekat.ui.incomes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import rs.ac.metropolitan.cs330_projekat.data.model.Income
import rs.ac.metropolitan.cs330_projekat.ui.common.ViewModel
import java.time.LocalDate
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewIncomeScreen(vm: ViewModel, paddingValues: PaddingValues) {
    var source by remember { mutableStateOf(TextFieldValue("")) }
    val calendar = rememberUseCaseState(visible = false)
    var date by rememberSaveable { mutableStateOf(LocalDate.now()) }
    var earning by remember { mutableStateOf(TextFieldValue("")) }

    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        shape = MaterialTheme.shapes.large,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(paddingValues)
        ) {
            item {
                Box(modifier = Modifier.fillMaxWidth()) {
                    IconButton(
                        modifier = Modifier
                            .background(Color.Transparent)
                            .scale(1.5f),
                        onClick = { vm.goBack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    Text(
                        text = "New Income", style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
            item {
                Button(onClick = { calendar.show() }) {
                    Text(text = "Date: ${date.toString()} ")
                }
                CalendarDialog(state = calendar,
                    config = CalendarConfig(
                        yearSelection = true,
                        monthSelection = true,
                        style = CalendarStyle.MONTH
                    ),
                    selection = CalendarSelection.Date(
                        selectedDate = LocalDate.now()
                    ) { newDate ->
                        date = newDate
                    })
            }
            item {
                TextField(
                    value = source,
                    onValueChange = {
                        source = it
                    },
                    label = { Text(text = "Source:") },
                    placeholder = { Text(text = "Enter source!") },
                )
            }
            item {
                TextField(
                    value = earning,
                    onValueChange = {
                        earning = it
                    },
                    label = { Text(text = "Earning:") },
                    placeholder = { Text(text = "Enter earning!") },
                )
            }
            item {
                Button(onClick = {
                    val income = Income(
                        date = date.toString(),
                        source = source.text,
                        earning = earning.text.toDouble()
                    )
                    vm.addIncome(income)
                    vm.goBack()
                }) {
                    Text(text = "Submit")
                }
            }
        }
    }
}