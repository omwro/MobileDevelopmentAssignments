package nl.omererdem.madlevel1task2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import nl.omererdem.madlevel1task2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // Binding for the application
    private lateinit var binding: ActivityMainBinding

    // The total amount of correct answers for the quiz
    private var totalCorrectAnswers: Int = 0

    // The initialized map for all the inputs with their correct value
    private var answersMap = emptyMap<EditText, String>()

    // On creating the application
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    // Initializing the view elements and required data
    private fun initViews() {
        binding.submitBtn.setOnClickListener {
            onSubmit()
        }
        answersMap = mapOf(
            binding.input1 to "t",
            binding.input2 to "f",
            binding.input3 to "f",
            binding.input4 to "f"
        )
    }

    // On every submit, check and count the correct answers, show the toast and reset score
    private fun onSubmit() {
        for ((input, answer) in answersMap) {
            if (input.text.toString().toLowerCase() == answer) {
                totalCorrectAnswers++
            }
        }
        showToast()
        resetScore()
    }

    // Show the user a long toast with the total correct answers text
    private fun showToast() {
        Toast.makeText(this, getString(R.string.correct_answer, totalCorrectAnswers), Toast.LENGTH_LONG).show()
    }

    // Reset the score to 0
    private fun resetScore() {
        totalCorrectAnswers = 0
    }
}