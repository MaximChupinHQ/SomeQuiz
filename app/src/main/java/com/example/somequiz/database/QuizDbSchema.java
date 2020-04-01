package com.example.somequiz.database;

public class QuizDbSchema {
    //внутренний класс для описания таблицы
    public static final class QuizQuestionTable {
        //Имя таблицы
        public static final String NAME = "questions";
        //описание столбцов
        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String QuestionText = "questionText";
        }
    }
}
