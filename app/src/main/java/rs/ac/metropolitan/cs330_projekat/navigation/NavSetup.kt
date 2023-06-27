package rs.ac.metropolitan.cs330_projekat.navigation

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import rs.ac.metropolitan.cs330_projekat.ui.common.ViewModel
import rs.ac.metropolitan.cs330_projekat.ui.expenses.ExpenseDetails
import rs.ac.metropolitan.cs330_projekat.ui.expenses.ExpensesHomeScreen
import rs.ac.metropolitan.cs330_projekat.ui.expenses.NewExpenseScreen
import rs.ac.metropolitan.cs330_projekat.ui.incomes.IncomeDetails
import rs.ac.metropolitan.cs330_projekat.ui.incomes.IncomesHomeScreen
import rs.ac.metropolitan.cs330_projekat.ui.incomes.NewIncomeScreen

@Composable
fun NavSetup(navController: NavHostController) {
    val vm: ViewModel = viewModel()
    val paddingValues = PaddingValues()
    vm.navController = navController

    NavHost(navController = navController, startDestination = NavigationRoutes.Expenses.route) {
        composable(route = NavigationRoutes.Expenses.route) {
            ExpensesHomeScreen(vm, paddingValues)
        }
        composable(route = NavigationRoutes.Incomes.route) {
            IncomesHomeScreen(vm, paddingValues)
        }
        composable(route = NavigationRoutes.ExpenseDetails.route) { navBackStackEntry ->
            val elementId = navBackStackEntry.arguments?.getString("elementId")
            if (elementId != null) {
                ExpenseDetails(vm, elementId, paddingValues)
            }else{
                Toast.makeText(navController.context, "Error, elementId is required!", Toast.LENGTH_SHORT).show()
            }
        }
        composable(route = NavigationRoutes.IncomeDetails.route) { navBackStackEntry ->
            val elementId = navBackStackEntry.arguments?.getString("elementId")
            if (elementId != null) {
                IncomeDetails(vm, elementId, paddingValues)
            }else{
                Toast.makeText(navController.context, "Error, elementId is required!", Toast.LENGTH_SHORT).show()
            }
        }
        composable(route = NavigationRoutes.NewExpense.route) {
            NewExpenseScreen(vm, paddingValues)
        }
        composable(route = NavigationRoutes.NewIncome.route) {
            NewIncomeScreen(vm, paddingValues)
        }
    }
}