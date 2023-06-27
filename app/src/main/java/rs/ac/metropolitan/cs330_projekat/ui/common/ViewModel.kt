package rs.ac.metropolitan.cs330_projekat.ui.common

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import rs.ac.metropolitan.cs330_projekat.data.database.FinanceManagerDatabase
import rs.ac.metropolitan.cs330_projekat.data.model.Expense
import rs.ac.metropolitan.cs330_projekat.data.model.Income
import rs.ac.metropolitan.cs330_projekat.data.repository.ExpenseRepository
import rs.ac.metropolitan.cs330_projekat.data.repository.IncomeRepository
import rs.ac.metropolitan.cs330_projekat.navigation.NavigationRoutes

class ViewModel(application: Application) : AndroidViewModel(application) {

    private val expenseRepository: ExpenseRepository
    private val incomeRepository: IncomeRepository

    lateinit var navController: NavHostController
    var granted = mutableStateOf(false)

    private val _expenses = MutableLiveData<List<Expense>>()
    val expenses: LiveData<List<Expense>>
        get() = _expenses

    private val _incomes = MutableLiveData<List<Income>>()
    val incomes: LiveData<List<Income>>
        get() = _incomes

    init {
        val financeManagerDao = FinanceManagerDatabase.getDatabase(application).financeManagerDatabaseDao()
        expenseRepository = ExpenseRepository(financeManagerDao)
        incomeRepository = IncomeRepository(financeManagerDao)
    }

    fun addExpense(expense: Expense) {
        viewModelScope.launch(Dispatchers.IO) {
            expenseRepository.addExpense(expense)
        }
    }

    fun loadExpenses() {
        GlobalScope.launch {
            expenseRepository.loadExpenses()
            MainScope().launch {
                expenseRepository.expenseFlow.collect {
                    _expenses.value = it
                }
            }
        }
    }

    fun getExpense(id: Int): Expense? {
        return _expenses.value?.find { it.id == id }
    }

    fun deleteExpense(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            expenseRepository.deleteById(id)
        }
    }

    fun addIncome(income: Income) {
        viewModelScope.launch(Dispatchers.IO) {
            incomeRepository.addIncome(income)
        }
    }

    fun loadIncome() {
        GlobalScope.launch {
            incomeRepository.loadIncomes()
            MainScope().launch {
                incomeRepository.incomeFlow.collect {
                    _incomes.value = it
                }
            }
        }
    }

    fun getIncomes(id: Int): Income? {
        return _incomes.value?.find { it.id == id }
    }

    fun deleteIncome(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            incomeRepository.deleteById(id)
        }
    }

    // Routing methods
    fun navigateToExpenseDetails(id: String) {
        navController.navigate(NavigationRoutes.ExpenseDetails.createRoute(id))
    }
    fun navigateToIncomeDetails(id: String){
        navController.navigate(NavigationRoutes.IncomeDetails.createRoute(id))
    }
    fun navigateToNewExpense() {
        navController.navigate(NavigationRoutes.NewExpense.route)
    }
    fun navigateToNewIncome() {
        navController.navigate(NavigationRoutes.NewIncome.route)
    }
    fun navigateToExpenses(){
        navController.navigate(NavigationRoutes.Expenses.route)
    }
    fun navigateToIncomes(){
        navController.navigate(NavigationRoutes.Incomes.route)
    }
    fun goBack() {
        navController.popBackStack()
    }

}