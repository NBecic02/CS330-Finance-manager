package rs.ac.metropolitan.cs330_projekat.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import rs.ac.metropolitan.cs330_projekat.data.model.Income
import rs.ac.metropolitan.cs330_projekat.data.model.Expense

@Database(entities = [Expense::class, Income::class], version = 1, exportSchema = false)
abstract class FinanceManagerDatabase : RoomDatabase() {

    abstract fun financeManagerDatabaseDao(): FinanceManagerDatabaseDao

    companion object{
        @Volatile
        private var INSTANCE: FinanceManagerDatabase? = null

        fun getDatabase(context: Context): FinanceManagerDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance;
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FinanceManagerDatabase::class.java,
                    "cs330_finance_manager"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}