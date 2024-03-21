package layout
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
@Dao
interface user_historyDAO {
    @Insert
    fun insertUserHistory(userHistory: UserHistory)

    @Update
    fun updateUserHistory(userHistory: UserHistory)

    @Delete
    fun deleteUserHistory(userHistory: UserHistory)

    @Query("SELECT * FROM user_history")
    fun getAllUserHistories(): List<UserHistory>

    @Query("SELECT * FROM user_history WHERE userId = :userId")
    fun getUserHistoriesByUserId(userId: Int): List<UserHistory>



}