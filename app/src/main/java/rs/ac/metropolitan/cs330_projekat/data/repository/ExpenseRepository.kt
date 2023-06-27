package rs.ac.metropolitan.cs330_projekat.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import rs.ac.metropolitan.cs330_projekat.data.database.FinanceManagerDatabaseDao
import rs.ac.metropolitan.cs330_projekat.data.model.Expense

class ExpenseRepository(private val financeManagerDatabaseDao: FinanceManagerDatabaseDao) {

    var expenseFlow: Flow<List<Expense>> = flowOf(listOf())
    var expenseList: List<Expense> = listOf()

    suspend fun loadExpenses() {
        val result = financeManagerDatabaseDao.getExpenses()
        if (result != null)
            expenseFlow = flowOf(result)
    }

    suspend fun addExpense(expense: Expense) {
        financeManagerDatabaseDao.insertExpense(expense)
    }

    suspend fun deleteById(id: Int) {
        financeManagerDatabaseDao.deleteExpense(id)
    }
}