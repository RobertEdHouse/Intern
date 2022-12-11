package com.example.intertn.model;

import java.util.ArrayList;
import java.util.List;

public class LoadConfig{

    public static List<Answer> readAnswers() {
        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer(0, "Температура піднялася й почуваюся недобре."));
        answers.add(new Answer(1, "Було дуже жарко."));
        answers.add(new Answer(2, "Вже декілька разів знудило."));
        answers.add(new Answer(3, "Нудило всю ніч."));
        answers.add(new Answer(4, "Дуже сильна діарея."));
        answers.add(new Answer(5, "Всю ніч сидів на білому троні."));
        answers.add(new Answer(6, "Почуваюсь погано й сильно сушить."));
        answers.add(new Answer(7, "Неспалося."));
        answers.add(new Answer(8, "Болить кишечник."));
        answers.add(new Answer(9, "Ледве вдалося заснути."));
        answers.add(new Answer(10, "Болить кишечник, аж ріже."));
        answers.add(new Answer(11, "Ніяк не спалося через кишечник."));
        answers.add(new Answer(12, "Щось мене нудить."));
        answers.add(new Answer(13, "Погано."));
        answers.add(new Answer(14, "Болить шлунок."));
        answers.add(new Answer(15, "Неспалося."));
        answers.add(new Answer(16, "Болить шлунок, аж ріже."));
        answers.add(new Answer(17, "Ледве вдалося заснути."));
        answers.add(new Answer(18, "Задишка турбує."));
        answers.add(new Answer(19, "Непогано."));
        answers.add(new Answer(20, "Тубрує кашель."));
        answers.add(new Answer(21, "Всю ніч турбував кашель."));
        answers.add(new Answer(22, "Сильна задуха, складно дихати."));
        answers.add(new Answer(23, "Боявся заснути, щоб не задихнутися."));
        answers.add(new Answer(24, "Печінка тубрує та сильно гірчить у роті."));
        answers.add(new Answer(25, "Нормально."));
        answers.add(new Answer(26, "У голові паморочиться."));
        answers.add(new Answer(27, "Добре."));
        answers.add(new Answer(28, "Голова болить, аж тріщить."));
        answers.add(new Answer(29, "Через голову заснути не міг."));
        answers.add(new Answer(30, "Важко... думки плутаються... марить."));
        answers.add(new Answer(31, "Погано."));
        return answers;
    }
}
