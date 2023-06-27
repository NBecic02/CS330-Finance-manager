package rs.ac.metropolitan.cs330_projekat.data.repository

import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import rs.ac.metropolitan.cs330_projekat.data.database.FinanceManagerDatabaseDao
import rs.ac.metropolitan.cs330_projekat.data.model.Income

class IncomeRepository(private val financeManagerDatabaseDao: FinanceManagerDatabaseDao) {
    var incomeFlow: Flow<List<Income>> = flowOf(listOf())
    var incomeList: List<Income> = listOf()

    suspend fun loadIncomes() {
        val result = financeManagerDatabaseDao.getIncomes()
        if (result != null)
            incomeFlow = flowOf(result)
    }

    suspend fun addIncome(income: Income) {
        financeManagerDatabaseDao.insertIncome(income)
    }

    suspend fun deleteById(id: Int) {
        financeManagerDatabaseDao.deleteIncome(id)
    }
}