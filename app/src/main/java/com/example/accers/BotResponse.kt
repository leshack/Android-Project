package com.example.accers

import com.example.accers.Constants.OPEN_GOOGLE
import com.example.accers.Constants.OPEN_SEARCH
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat

object BotResponse {

    fun basicResponses(_message: String): String {

        val random = (0..2).random()
        val message =_message.toLowerCase()

        return when {

            //Flips a coin
            message.contains("flip") && message.contains("coin") -> {
                val r = (0..1).random()
                val result = if (r == 0) "heads" else "tails"

                "I flipped a coin and it landed on $result"
            }

            //Math calculations
            message.contains("solve") -> {
                val equation: String? = message.substringAfterLast("solve")
                return try {
                    val answer = SolveMath.solveMath(equation ?: "0")
                    "The answer is = $answer"

                } catch (e: Exception) {
                    "Sorry, I can't solve that."
                }
            }

            //Hello
            message.contains("hello") -> {
                when (random) {
                    0 -> "Hello there!"
                    1 -> "Hallo"
                    2 -> "Bonjour!"
                    3 -> "Hola!"
                    else -> "error"
                }
            }
            //Help
            message.contains("help") -> {
                "1:Check health activity(ex:my health activity)\n" +
                        "2:Search for something in google(ex:search gym)\n" +
                        "3:Tell jokes(ex:tell me a joke)\n" +
                        "4:Solve math (ex:solve(2*3)\n" +
                        "5:Flip a coin(ex:flip a coin)\n" +
                        "6:Ask the current time(ex:time)\n" +
                        "7:Open google(ex:open google)"
            }
            //jokes
            message.contains("tell me a joke") -> {
                when (random) {
                    0 -> "Doctor: What's the condition of the boy who swallowed the quarter?\n" + "Nurse: No change yet."
                    1 -> "Smoking will kill you\n"+"Bacon will kill you\n"+"But smoking bacon will cure it"
                    2 -> "Laughter is the best medicine—except when it comes to treating diarrhea"
                    3 -> "Recent studies show patients who have a cold feel better on Saturdays and Sundays. Evidence points to a weekend immune system"
                    3 -> "Who’s idea was it to sing “Happy Birthday” while washing your hands? Now every time I go to the bathroom, my kids expect me to walk out with a cake"
                    else -> "error"
                }

            }

            //health
            message.contains("my health activity") -> {
                when (random) {
                    0 -> "Today eat a heavy healthy lunch," +
                            "drink a glass of water," +
                            "take advantage of self care," +
                            "and fit in a quick workout"

                    1 -> "Avoid the snooze button," +
                            "give yourself enough time to get to work," +
                            "enjoy a cup of coffee or tea," +
                            "and say positive affirmations"

                    2 -> "Meditate by taking deep breaths," +
                            "prioritize important tasks," +
                            "listen to motivational music," +
                            "and call a loved one"

                    3 -> "Tackle a few chore," +
                            "think creatively," +
                            "do a crossword puzzle," +
                            "and perform a retrospective"
                    else -> "error"
                }

            }

            //How are you?
            message.contains("how are you") -> {
                when (random) {
                    0 -> "I'm doing fine, thanks!"
                    1 -> "I'm hungry... of knowledge"
                    2 -> "Pretty good! How about you?"
                    else -> "error"
                }
            }

            //What time is it?
            message.contains("time") && message.contains("?")-> {
                val timeStamp = Timestamp(System.currentTimeMillis())
                val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm")
                val date = sdf.format(Date(timeStamp.time))

                date.toString()
            }

            //Open Google
            message.contains("open") && message.contains("google")-> {
                OPEN_GOOGLE
            }

            //Search on the internet
            message.contains("search")-> {
                OPEN_SEARCH
            }

            //When the programme doesn't understand...
            else -> {
                when (random) {
                    0 -> "I don't understand..."
                    1 -> "Type help to get to know what i can do for you?"
                    2 -> "Try asking me something different"
                    else -> "error"
                }
            }
        }
    }
}