import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.twoup.personalfinance.local.date.DateTimeUtil
import kotlinx.datetime.LocalDateTime

class AddNoteUiState(
    id: Long = 0L,
    title: String = "",
    description: String = "",
    created: LocalDateTime = DateTimeUtil.now(),
) {
    var id by mutableStateOf(id)
        private set
    var title by mutableStateOf(title)
        private set
    var description by mutableStateOf(description)
        private set
    var created by mutableStateOf(created)
        private set
    var editMode by mutableStateOf(false)
        private set

    fun toggleEditMode() {
        editMode = !editMode
    }

    fun updateTitle(newTitle: String) {
        title = newTitle
    }

    fun updateDescription(newDescription: String) {
        description = newDescription
    }

    fun updateId(newId: Long) {
        id = newId
    }
}
