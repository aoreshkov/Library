package app.oreshkov.library.worker

import android.content.Context
import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.work.WorkerInject
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import app.oreshkov.library.data.local.BookDao
import app.oreshkov.library.data.local.BookLocalEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import kotlinx.coroutines.coroutineScope
import javax.inject.Singleton

@Singleton
class SeedDatabaseWorker @WorkerInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    val bookDao: BookDao
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = coroutineScope {
        try {
            applicationContext.assets.open("books.json").use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val bookType = object : TypeToken<List<BookLocalEntity>>() {}.type
                    val bookList: List<BookLocalEntity> = Gson().fromJson(jsonReader, bookType)

                    bookDao.insertAll(bookList)

                    Result.success()
                }
            }
        } catch (ex: Exception) {
            Log.e(TAG, "Error seeding database", ex)
            Result.failure()
        }
    }

    companion object {
        private val TAG = SeedDatabaseWorker::class.java.simpleName
    }
}