package nl.omererdem.madlevel1task1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import nl.omererdem.madlevel1task1.databinding.ActivityHigherLowerBinding

class HigherLowerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHigherLowerBinding
    private var currentThrow: Int = 1
    private var lastThrow: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHigherLowerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        binding.lowerBtn.setOnClickListener {
            onLowerClick()
        }
        binding.equalsBtn.setOnClickListener {
            onEqualClick()
        }
        binding.higherBtn.setOnClickListener {
            onHigherClick()
        }
        updateUI()
    }

    private fun updateUI() {
        binding.lastThrow.text = getString(R.string.last_throw, lastThrow)
        if (currentThrow == 1) {
            binding.image.setImageResource(R.drawable.dice1)
        } else if (currentThrow == 2) {
            binding.image.setImageResource(R.drawable.dice2)
        } else if (currentThrow == 3) {
            binding.image.setImageResource(R.drawable.dice3)
        } else if (currentThrow == 4) {
            binding.image.setImageResource(R.drawable.dice4)
        } else if (currentThrow == 5) {
            binding.image.setImageResource(R.drawable.dice5)
        } else if (currentThrow == 6) {
            binding.image.setImageResource(R.drawable.dice6)
        }
    }

    private fun rollDice() {
        lastThrow = currentThrow
        currentThrow = (1..6).random()
        updateUI()
    }

    private fun onHigherClick() {
        rollDice()
        if (currentThrow > lastThrow) {
            onAnswerCorrect()
        } else {
            onAnswerIncorrect()
        }
    }

    private fun onLowerClick() {
        rollDice()
        if (currentThrow < lastThrow) {
            onAnswerCorrect()
        } else {
            onAnswerIncorrect()
        }
    }

    private fun onEqualClick() {
        rollDice()
        if (currentThrow == lastThrow) {
            onAnswerCorrect()
        } else {
            onAnswerIncorrect()
        }
    }

    private fun onAnswerCorrect() {
        Toast.makeText(this, getString(R.string.correct), Toast.LENGTH_LONG).show()
    }

    private fun onAnswerIncorrect() {
        Toast.makeText(this, getString(R.string.incorrect), Toast.LENGTH_LONG).show()
    }
}