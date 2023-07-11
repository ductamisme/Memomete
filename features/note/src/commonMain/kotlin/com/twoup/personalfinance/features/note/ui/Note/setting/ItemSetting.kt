import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle

@Composable
fun PrivacySetting() {
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

@Composable
fun AboutText() {
    Text(
        buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append("Memomates - Your Personal Reminder App\n\n")
            }
            append("Version: 1.0.0\n")
            append("Developer: XYZ Corporation\n\n")

            append("Memomates is a simple and intuitive app designed to help you stay organized and never miss an important task or event. With Memomates, you can easily create reminders, set notification preferences, and manage your tasks effortlessly.\n\n")

            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append("Key Features:\n")
            }
            append("- Create and manage reminders with customizable settings\n")
            append("- Set notification preferences for timely reminders\n")
            append("- Choose from various themes to personalize your app experience\n")
            append("- Backup and restore your data for peace of mind\n")
            append("- Privacy-focused design to protect your personal information\n\n")

            append("Thank you for choosing Memomates. We strive to continuously improve the app and provide you with the best user experience. If you have any feedback or suggestions, please don't hesitate to reach out to us.\n\n")

            append("Stay organized and never miss a beat with Memomates!")
        }
    )
}

@Composable
fun HelpText() {
    Text(
        buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append("Frequently Asked Questions (FAQs):\n\n")
            }
            append("1. How do I create a new reminder?\n")
            append("   To create a new reminder, simply open the app and click on the \"+\" button or the \"Add Reminder\" option. Enter the necessary details such as title, date, time, and any additional notes. Save the reminder, and it will be added to your list.\n\n")

            append("2. How can I edit or delete a reminder?\n")
            append("   To edit or delete a reminder, navigate to the reminder list or calendar view and locate the specific reminder. Tap on the reminder to view its details. From there, you can choose to edit the reminder or delete it permanently.\n\n")

            append("3. How do I set notification preferences?\n")
            append("   You can customize your notification preferences by going to the Settings screen. Look for the \"Notification Settings\" option and adjust the settings according to your preferences. You can enable or disable notifications, set the notification time, and choose the notification sound.\n\n")

            append("4. Can I back up my reminder data?\n")
            append("   Yes, you can back up your reminder data to ensure you don't lose important information. In the Settings screen, find the \"Backup and Restore\" option. Choose the backup method (cloud storage or local backup) and follow the instructions to create a backup of your reminders.\n\n")

            append("5. How can I contact support?\n")
            append("   If you have any questions, feedback, or need assistance, you can reach our support team by emailing support@memomates.com. We are here to help you with any issues or inquiries you may have.\n\n")

            append("For additional help or information, please visit our website at www.memomates.com or refer to the in-app documentation.\n\n")

            append("Thank you for using Memomates! We appreciate your support.")
        }
    )
}
