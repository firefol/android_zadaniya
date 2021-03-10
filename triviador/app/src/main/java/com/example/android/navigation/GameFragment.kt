/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.android.navigation.databinding.FragmentGameBinding

class GameFragment : Fragment() {
    data class Question(
            val text: String,
            val answers: List<String>)


    private val questions: MutableList<Question> = mutableListOf(
            Question(text = "В каком городе не работал великий композитор 18-го века Кристоф Виллибальд Глюк?",
                    answers = listOf("Берлин", "Милан", "Вена", "Париж")),
            Question(text = "Кто первым доказал периодичность появления комет?",
                    answers = listOf("Галлей", "Галилей", "Коперник", "Кеплер")),
            Question(text = "Про какую летнюю погоду говорят \"Вёдро\" ?",
                    answers = listOf("Сухая ясная", "Теплая дождливая", "Прохладная дождливая", "Длительные заморозки")),
            Question(text = "С какой из этих стран Чехия не граничит?",
                    answers = listOf("Венгрия", "Германия", "Австрия", "Польша")),
            Question(text = "Где в основном проживают таты?",
                    answers = listOf("Дагестан", "Татарстан", "Башкортостан", "Туркменистан")),
            Question(text = "Как называется курс парусного судна, совпадающий с направлением ветра?",
                    answers = listOf("Фордевинд", "Бейдевинд", "Галфинд", "Бакштаг")),
            Question(text = "На вершине какой горы расположена сорокаметровая статуя Христа, являющаяся символом Рио-де-Жанейро?",
                    answers = listOf("Корковадо", "Тупунгато", "Уаскаран", "Ильимани")),
            Question(text = "Какое брюхо, согласно спорной русской пословице, глухо к ученью?",
                    answers = listOf("Сытое", "Толстое", "Тощее", "Пустое")),
            Question(text = "Что из этого является видом ткани?",
                    answers = listOf("Креп-жоржет", "Креп-лизет", "Креп-мюзет", "Креп-жаннет")),
            Question(text = "Как называется комедия В. В. Маяковского?",
                    answers = listOf("Клоп", "Пена", "Жук", "Паук"))
    )

    lateinit var currentQuestion: Question
    lateinit var answers: MutableList<String>
    private var questionIndex = 0
    private val numQuestions = Math.min((questions.size + 1) / 2, 3)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        val binding = DataBindingUtil.inflate<FragmentGameBinding>(
                inflater, R.layout.fragment_game, container, false)


        randomizeQuestions()// задает рандомные вопросы

        binding.game = this


        binding.submitButton.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER")
        { view: View ->
            val checkedId = binding.questionRadioGroup.checkedRadioButtonId

            if (-1 != checkedId) {
                var answerIndex = 0
                when (checkedId) {
                    R.id.secondAnswerRadioButton -> answerIndex = 1
                    R.id.thirdAnswerRadioButton -> answerIndex = 2
                    R.id.fourthAnswerRadioButton -> answerIndex = 3
                }

                if (answers[answerIndex] == currentQuestion.answers[0]) {
                    questionIndex++
                    // переход к следующему вопросу
                    if (questionIndex < numQuestions) {
                        currentQuestion = questions[questionIndex]
                        setQuestion()
                        binding.invalidateAll()
                    } else {
                        // выигрыш
                        view.findNavController().navigate(GameFragmentDirections.actionGameFragmentToGameWonFragment(numQuestions, questionIndex))
                    }
                } else {
                    // поражение
                    view.findNavController().navigate(GameFragmentDirections.actionGameFragmentToGameOverFragment2())
                }
            }
        }
        return binding.root
    }

    // рандом вопроса
    private fun randomizeQuestions() {
        questions.shuffle()
        questionIndex = 0
        setQuestion()
    }

    // Устанавливает вопрос и рандомизирует ответы. Изменяются только данные но не интерфейс
    private fun setQuestion() {
        currentQuestion = questions[questionIndex]
        // зарандомить ответы и скопировать их в массив
        answers = currentQuestion.answers.toMutableList()
        // и перемешать их
        answers.shuffle()
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.title_android_trivia_question, questionIndex + 1, numQuestions)
    }
}
