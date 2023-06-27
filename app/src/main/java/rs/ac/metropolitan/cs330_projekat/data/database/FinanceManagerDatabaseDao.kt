package rs.ac.metropolitan.cs330_projekat.data.database

import androidx.room.*
import rs.ac.metropolitan.cs330_projekat.data.model.Income
import rs.ac.metropolitan.cs330_projekat.data.model.Expense

@Dao
interface FinanceManagerDatabaseDao {

    @Upsert
    suspend fun insertExpense(expense: Expense)

    @Upsert
    suspend fun insertIncome(income: Income)

    @Query("DELETE FROM incomes WHERE id = :id")
    fun deleteIncome(id: Int)

    @Query("DELETE FROM expenses WHERE id = :id")
    fun deleteExpense(id: Int)

    @Query("SELECT * FROM expenses ORDER BY date DESC")
    fun getExpenses(): List<Expense>

    @Query("SELECT * FROM incomes ORDER BY date DESC")
    fun getIncomes(): List<Income>
}
