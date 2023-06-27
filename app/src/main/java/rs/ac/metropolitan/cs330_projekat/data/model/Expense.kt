package rs.ac.metropolitan.cs330_projekat.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.util.*

@Entity(tableName = "expenses")
data class Expense(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(name = "date")
    var date: String,

    @ColumnInfo(name = "shop")
    var shop: String,

    @ColumnInfo(name = "price")
    var price: Double
)