package com.example.bisayatranslationquizapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.bisayatranslationquizapp.QuizContract.*;

import java.util.ArrayList;
import java.util.List;


public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "QuizDatabase.db";
    private static final int DATABASE_VERSION = 3;

    private static QuizDbHelper instance;

    private SQLiteDatabase db;

    private QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized QuizDbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new QuizDbHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_CATEGORIES_TABLE = "CREATE TABLE " +
                CategoriesTable.TABLE_NAME + "( " +
                CategoriesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CategoriesTable.COLUMN_NAME + " TEXT " +
                ")";

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuestionsTable.COLUMN_DIFFICULTY + " TEXT, " +
                QuestionsTable.COLUMN_CATEGORY_ID + " INTEGER, " +
                "FOREIGN KEY(" + QuestionsTable.COLUMN_CATEGORY_ID + ") REFERENCES " +
                CategoriesTable.TABLE_NAME + "(" + CategoriesTable._ID + ")" + "ON DELETE CASCADE" +
                ")";

        db.execSQL(SQL_CREATE_CATEGORIES_TABLE);
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillCategoriesTable();
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CategoriesTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    private void fillCategoriesTable() {
        Category c1 = new Category("English to Bisaya");
        insertCategory(c1);
        Category c2 = new Category("Bisaya to English");
        insertCategory(c2);
    }

    public void addCategory(Category category) {
        db = getWritableDatabase();
        insertCategory(category);
    }

    public void addCategories(List<Category> categories) {
        db = getWritableDatabase();

        for (Category category : categories) {
            insertCategory(category);
        }
    }

    private void insertCategory(Category category) {
        ContentValues cv = new ContentValues();
        cv.put(CategoriesTable.COLUMN_NAME, category.getName());
        db.insert(CategoriesTable.TABLE_NAME, null, cv);
    }

    private void fillQuestionsTable() {
        //BIS TO ENG EASY
        Question q1 = new Question("Iring",
                "Cat", "Dog", "Spinosaurus", 1,
                Question.DIFFICULTY_EASY, Category.BISTOENG);
        insertQuestion(q1);
        Question q2 = new Question("Iro",
                "Snoop", "Dog", "Pterodactyl", 2,
                Question.DIFFICULTY_EASY, Category.BISTOENG);
        insertQuestion(q2);
        Question q3 = new Question("Dahom",
                "Nightmares", "Water", "Dream", 3,
                Question.DIFFICULTY_EASY, Category.BISTOENG);
        insertQuestion(q3);
        Question q4 = new Question("Liog",
                "Neck", "Deep", "Leg", 1,
                Question.DIFFICULTY_EASY, Category.BISTOENG);
        insertQuestion(q4);
        Question q5 = new Question("Lami",
                "Epic", "Unsavory", "Delicious", 3,
                Question.DIFFICULTY_EASY, Category.BISTOENG);
        insertQuestion(q5);

        //ENG TO BIS EASY
        Question q6 = new Question("Nice",
                "Ayus", "Aso", "Wala", 1,
                Question.DIFFICULTY_EASY, Category.ENGTOBIS);
        insertQuestion(q6);
        Question q7 = new Question("Love",
                "Lami", "Buang", "Gugma", 3,
                Question.DIFFICULTY_EASY, Category.ENGTOBIS);
        insertQuestion(q7);
        Question q8 = new Question("Sleep",
                "Tutok", "Tug", "Tugstak", 2,
                Question.DIFFICULTY_EASY, Category.ENGTOBIS);
        insertQuestion(q8);
        Question q9 = new Question("Mine",
                "Imoha", "Akoa", "Atoa", 2,
                Question.DIFFICULTY_EASY, Category.ENGTOBIS);
        insertQuestion(q9);
        Question q10 = new Question("Back",
                "Likod", "Atubangan", "Kilid", 1,
                Question.DIFFICULTY_EASY, Category.ENGTOBIS);
        insertQuestion(q10);

        //MEDIUM ENG TO BIS
        Question q11 = new Question("Sky",
                "Ayus", "Langit", "Wala", 2,
                Question.DIFFICULTY_MEDIUM, Category.ENGTOBIS);
        insertQuestion(q11);
        Question q12 = new Question("Fourteen",
                "Kanding", "Katabi", "Katorse", 3,
                Question.DIFFICULTY_MEDIUM, Category.ENGTOBIS);
        insertQuestion(q12);
        Question q13 = new Question("Disturbance",
                "Pagsamo", "Samok", "Sayaw", 2,
                Question.DIFFICULTY_MEDIUM, Category.ENGTOBIS);
        insertQuestion(q13);
        Question q14 = new Question("Door",
                "Window", "Purtahan", "Pinto", 2,
                Question.DIFFICULTY_MEDIUM, Category.ENGTOBIS);
        insertQuestion(q14);
        Question q15 = new Question("Demon",
                "Ikaw", "Yawa", "Nawong", 2,
                Question.DIFFICULTY_MEDIUM, Category.ENGTOBIS);
        insertQuestion(q15);

        //MEDIUM BIS TO ENG
        Question q16 = new Question("Gipasindan-an",
                "Warned", "Said", "Have", 1,
                Question.DIFFICULTY_MEDIUM, Category.BISTOENG);
        insertQuestion(q16);
        Question q17 = new Question("Bitoon",
                "Moon", "Star", "Grandma's House", 2,
                Question.DIFFICULTY_MEDIUM, Category.BISTOENG);
        insertQuestion(q17);
        Question q18 = new Question("Paglaom",
                "Hope", "Despair", "Leverage", 1,
                Question.DIFFICULTY_MEDIUM, Category.BISTOENG);
        insertQuestion(q18);
        Question q19 = new Question("Kahangturan",
                "Destination", "Forever", "End", 2,
                Question.DIFFICULTY_MEDIUM, Category.BISTOENG);
        insertQuestion(q19);
        Question q20 = new Question("Kanunay",
                "A little bit of both", "Sometimes", "Always", 3,
                Question.DIFFICULTY_MEDIUM, Category.BISTOENG);
        insertQuestion(q20);

        //HARD BIS TO ENG
        Question q21 = new Question("Asa ka gikan?",
                "Where did you come from?", "You stole my carpet?", "I'm very nice?", 1,
                Question.DIFFICULTY_HARD, Category.BISTOENG);
        insertQuestion(q21);
        Question q22 = new Question("Naa ko'y balay dire.",
                "I was the president of the USA.", "I have a house here.", "My dog ate my assignment.", 2,
                Question.DIFFICULTY_HARD, Category.BISTOENG);
        insertQuestion(q22);
        Question q23 = new Question("Nasaag nako!",
                "I love you!", "I am lost!", "I stole your cat!", 2,
                Question.DIFFICULTY_HARD, Category.BISTOENG);
        insertQuestion(q23);
        Question q24 = new Question("Palihog ko sa akong gamit.",
                "Give me your money legally.", "Put the milk before the cereal.", "Hand me my things please.", 3,
                Question.DIFFICULTY_HARD, Category.BISTOENG);
        insertQuestion(q24);
        Question q25 = new Question("Giingnan na tika, diba?",
                "I told you, right?", "The carpet does not fly, right?", "We have the same mother, right?", 1,
                Question.DIFFICULTY_HARD, Category.BISTOENG);
        insertQuestion(q25);

        //HARD ENG TO BIS
        Question q26 = new Question("I am so happy.",
                "Lipay kaayo ko.", "Gusto nako mahimong ligid.", "Kasaba ba nimo.", 1,
                Question.DIFFICULTY_HARD, Category.ENGTOBIS);
        insertQuestion(q26);
        Question q27 = new Question("It is all because of you.",
                "Akoa na lang ka bi.", "Tungod ni tanan sa imoha.", "Naunsa na man ko oy.", 2,
                Question.DIFFICULTY_HARD, Category.ENGTOBIS);
        insertQuestion(q27);
        Question q28 = new Question("Ma, meet my girlfriend.",
                "Ma, ila ilaha akong uyab.", "Ma, ihatag sa akoa imong balay og yuta.", "Ma, wa nako kasabot.", 1,
                Question.DIFFICULTY_HARD, Category.ENGTOBIS);
        insertQuestion(q28);
        Question q29 = new Question("Do not worry.",
                "Ayaw og kabalaka.", "Padayon sa paglambo.", "Maadto jud kog langit.", 1,
                Question.DIFFICULTY_HARD, Category.ENGTOBIS);
        insertQuestion(q29);
        Question q30 = new Question("I see you.",
                "Lipaya oy.", "Kita tika.", "Naay bitin.", 2,
                Question.DIFFICULTY_HARD, Category.ENGTOBIS);
        insertQuestion(q30);

    }

    public void addQuestion(Question question) {
        db = getWritableDatabase();
        insertQuestion(question);
    }

    public void addQuestions(List<Question> questions) {
        db = getWritableDatabase();

        for (Question question : questions) {
            insertQuestion(question);
        }
    }

    private void insertQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuestionsTable.COLUMN_DIFFICULTY, question.getDifficulty());
        cv.put(QuestionsTable.COLUMN_CATEGORY_ID, question.getCategoryID());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + CategoriesTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Category category = new Category();
                category.setId(c.getInt(c.getColumnIndex(CategoriesTable._ID)));
                category.setName(c.getString(c.getColumnIndex(CategoriesTable.COLUMN_NAME)));
                categoryList.add(category);
            } while (c.moveToNext());
        }

        c.close();
        return categoryList;
    }

    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }

    public ArrayList<Question> getQuestions(int categoryID, String difficulty) {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        String selection = QuestionsTable.COLUMN_CATEGORY_ID + " = ? " +
                " AND " + QuestionsTable.COLUMN_DIFFICULTY + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(categoryID), difficulty};

        Cursor c = db.query(
                QuestionsTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
}