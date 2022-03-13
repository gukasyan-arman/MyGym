package com.example.mygym.screen.rules

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mygym.databinding.FragmentRulesBinding

class RulesFragment : Fragment() {

    lateinit var binding: FragmentRulesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activity?.actionBar?.title = "Правила посещения"
        binding = FragmentRulesBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rules.text = "Хочу обратиться к Дагестанцам пацанам:\n" +
                "Я сам из Дагестана, Алейкум, ассалам!\n" +
                "Хочу обратиться к Дагестанцам пацанам:\n" +
                "Ау! Ау! Ау!\n" +
                "\n" +
                "Зауженные джинсы, кепки 228.\n" +
                "Мокасины красные, никого не просим.\n" +
                "По городу шатаетесь, чушь свою несете.\n" +
                "На хатах зависаете, курите и пьете.\n" +
                "\n" +
                "Ведите себя достойно, иншалла.\n" +
                "Отца не позорьте. Парни, машалла.\n" +
                "Ведите себя достойно, иншалла.\n" +
                "Ведите достойно, а?\n" +
                "\n" +
                "Хочу обратиться к Дагестанцам пацанам:\n" +
                "Я сам из Дагестана, Алейкум ассалам.\n" +
                "Хочу обратиться к Дагестанцам пацанам:\n" +
                "Ау! Ау! Ау!\n" +
                "\n" +
                "Зауженные джинсы, кепки 228.\n" +
                "Мокасины красные, никого не просим.\n" +
                "Источник teksty-pesenok.ru\n" +
                "Вы девочек цепляете, девочки в слезах.\n" +
                "Мужчины так не делают. Видит все Аллах!\n" +
                "\n" +
                "Ведите себя достойно, иншалла.\n" +
                "Отца не позорьте; парни, машалла.\n" +
                "Ведите себя достойно, иншалла.\n" +
                "Ведите достойно, а?\n" +
                "\n" +
                "Ведите себя достойно, пацаны!\n" +
                "Ведите себя достойно, пацаны!\n" +
                "Ведите себя достойно, пацаны!\n" +
                "Ведите себя достойно, пацаны!"

    }

}