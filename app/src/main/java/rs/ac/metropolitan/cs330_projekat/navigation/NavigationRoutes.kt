package rs.ac.metropolitan.cs330_projekat.navigation

sealed class NavigationRoutes(val route: String) {
    object Expenses: NavigationRoutes(route = "expenses")
    object Incomes: NavigationRoutes(route = "incomes")
    object NewExpense : NavigationRoutes(route = "newExpense")
    object NewIncome : NavigationRoutes(route = "newIncome")
    object ExpenseDetails: NavigationRoutes(route = "expenseDetail/{elementId}"){
        fun createRoute(elementId: String) = "expenseDetail/$elementId"
    }
    object IncomeDetails: NavigationRoutes(route = "incomeDetail/{elementId}"){
        fun createRoute(elementId: String) = "incomeDetail/$elementId"
    }
}