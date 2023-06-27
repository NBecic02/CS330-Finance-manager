package rs.ac.metropolitan.cs330_projekat.navigation

sealed class NavigationRoutes(val route: String) {
    object Expenses: NavigationRoutes(route = "expenses")
    object Incomes: NavigationRoutes(route = "incomes")
    object NewExpense : NavigationRoutes(route = "new")
    object NewIncome : NavigationRoutes(route = "new")
    object ExpenseDetails: NavigationRoutes(route = "detail/{elementId}"){
        fun createRoute(elementId: String) = "detail/$elementId"
    }
    object IncomeDetails: NavigationRoutes(route = "detail/{elementId}"){
        fun createRoute(elementId: String) = "detail/$elementId"
    }
}