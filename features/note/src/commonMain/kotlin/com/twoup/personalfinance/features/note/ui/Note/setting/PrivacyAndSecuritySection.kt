import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.twoup.personalfinance.features.note.viewmodel.note.NoteViewModel

@Composable
fun PrivacySetting(){
    Text(
        buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append("Privacy & Security\n")
            }
            append("At MemoMate, we take your privacy and the security of your data seriously. This Privacy & Security policy outlines how we collect, use, and protect your personal information when you use our app.\n\n")

            append("Data Collection and Usage\n")
            append("Account Information: When you create an account with MemoMate, we collect your name, email address, and a securely hashed password. This information is used for authentication and personalization purposes within the app.\n\n")

            append("Memo Data: Your memos, notes, and any associated content that you create within MemoMate are securely stored on our servers. We do not access, read, or share your memo data unless required by law or with your explicit consent.\n\n")

            append("Data Security\n")
            append("Encryption: We employ industry-standard encryption techniques to protect your data. All data transmissions between the app and our servers are encrypted using secure protocols such as HTTPS.\n\n")

            append("Access Controls: Access to your personal data is restricted to authorized personnel only. We maintain strict access controls and regularly review our security measures to ensure the safety of your information.\n\n")

            append("Data Retention: We retain your memo data for as long as you maintain an active account with MemoMate. If you decide to delete your account, your memo data will be securely and permanently deleted from our servers.\n\n")

            append("Third-Party Services\n")
            append("Analytics: We may use third-party analytics services to collect anonymized usage data to improve the app's performance and user experience. These services do not collect personally identifiable information.\n\n")

            append("Third-Party Links: MemoMate may contain links to third-party websites or services. We are not responsible for the privacy practices or content of these external sites. We encourage you to review their privacy policies before providing any personal information.\n\n")

            append("Your Rights and Choices\n")
            append("Account Control: You have the right to access, update, or delete your personal information within the MemoMate app. You can manage your account settings and preferences in the app's settings menu.\n\n")

            append("Data Portability: You can request a copy of your memo data in a machine-readable format for personal use or for transferring to another service.\n\n")

            append("Opt-Out: You have the option to opt-out of receiving promotional communications from MemoMate. You can manage your communication preferences within the app's settings menu.\n\n")

            append("Updates to this Policy\n")
            append("We may update this Privacy & Security policy from time to time. We will notify you of any significant changes via email or within the MemoMate app. We recommend reviewing this policy periodically to stay informed about how we collect, use, and protect your information.\n\n")

            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append("If you have any questions or concerns about our Privacy & Security practices, please contact us at privacy@memomate.com.")
            }
        }
    )


}