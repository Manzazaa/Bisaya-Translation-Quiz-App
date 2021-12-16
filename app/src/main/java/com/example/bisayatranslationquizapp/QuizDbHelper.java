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
    private static final String DATABASE_NAME = "TranslationQuizDB.db";
    private static final int DATABASE_VERSION = 1;

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
    //the first time the db is created, we will create it
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

    @Override //if there are changes to the structure of the db, we upgrade it
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
    //method for filling the category table
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

    private void fillQuestionsTable() {//filling the questions table
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
        Question q31 = new Question("Dagan",
                "Crawl", "Run", "Jump", 2,
                Question.DIFFICULTY_EASY, Category.BISTOENG);
        insertQuestion(q31);
        Question q32 = new Question("Hilak",
                "Kiss", "Breathe", "Cry", 3,
                Question.DIFFICULTY_EASY, Category.BISTOENG);
        insertQuestion(q32);
        Question q33 = new Question("Halok",
                "Talk", "Cry", "Kiss", 3,
                Question.DIFFICULTY_EASY, Category.BISTOENG);
        insertQuestion(q33);
        Question q34 = new Question("Ngipon",
                "Teeth", "Mouth", "Lips", 1,
                Question.DIFFICULTY_EASY, Category.BISTOENG);
        insertQuestion(q34);
        Question q35 = new Question("Barato",
                "Stone", "Cheap", "Boat", 2,
                Question.DIFFICULTY_EASY, Category.BISTOENG);
        insertQuestion(q35);


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
        Question q36 = new Question("Snore",
                "Dakop", "Hadlok", "Hagok", 3,
                Question.DIFFICULTY_EASY, Category.ENGTOBIS);
        insertQuestion(q36);
        Question q37 = new Question( "Chase",
                "Tuklod", "Gukod", "Bukol", 2,
                Question.DIFFICULTY_EASY, Category.ENGTOBIS);
        insertQuestion(q37);
        Question q38 = new Question("Maybe",
                "Siguro", "Sigurado", "Sulod", 1,
                Question.DIFFICULTY_EASY, Category.ENGTOBIS);
        insertQuestion(q38);
        Question q39 = new Question("Handsome",
                "Kamot", "Gwapa", "Gwapo", 3,
                Question.DIFFICULTY_EASY, Category.ENGTOBIS);
        insertQuestion(q39);
        Question q40 = new Question("Beautiful",
                "Gwapo", "Gagmay", "Gwapa", 3,
                Question.DIFFICULTY_EASY, Category.ENGTOBIS);
        insertQuestion(q40);

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
        Question q15 = new Question("Rice",
                "Kanon", "Saba", "Nawong", 2,
                Question.DIFFICULTY_MEDIUM, Category.ENGTOBIS);
        insertQuestion(q15);
        Question q41 = new Question("Path",
                "Agianan", "Adlawan", "Ganiha", 1,
                Question.DIFFICULTY_MEDIUM, Category.ENGTOBIS);
        insertQuestion(q41);
        Question q42 = new Question("Sea",
                "Langit", "Nakita", "Dagat", 3,
                Question.DIFFICULTY_MEDIUM, Category.ENGTOBIS);
        insertQuestion(q42);
        Question q43 = new Question("Shoulders",
                "Ulo", "Likod", "Abaga", 3,
                Question.DIFFICULTY_MEDIUM, Category.ENGTOBIS);
        insertQuestion(q43);
        Question q44 = new Question("Soft",
                "Humok", "Gahi", "Humot", 1,
                Question.DIFFICULTY_MEDIUM, Category.ENGTOBIS);
        insertQuestion(q44);
        Question q45 = new Question("Island",
                "Isda", "Isla", "Isa", 2,
                Question.DIFFICULTY_MEDIUM, Category.ENGTOBIS);
        insertQuestion(q45);

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
        Question q46 = new Question("Hugaw",
                "Talk", "Dirty", "To me", 2,
                Question.DIFFICULTY_MEDIUM, Category.BISTOENG);
        insertQuestion(q46);
        Question q47 = new Question("Giapangayo",
                "Just a little bit", "Add more", "Asked for", 3,
                Question.DIFFICULTY_MEDIUM, Category.BISTOENG);
        insertQuestion(q47);
        Question q48 = new Question("Kanamo",
                "Us", "You", "Me", 1,
                Question.DIFFICULTY_MEDIUM, Category.BISTOENG);
        insertQuestion(q48);
        Question q49 = new Question("Libog",
                "Annoyed", "Confused", "Thousand", 2,
                Question.DIFFICULTY_MEDIUM, Category.BISTOENG);
        insertQuestion(q49);
        Question q50 = new Question("Binhod",
                "Dumb", "Numb", "Bonfire", 2,
                Question.DIFFICULTY_MEDIUM, Category.BISTOENG);
        insertQuestion(q50);


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
        Question q51 = new Question("Kumusta imong adlaw?",
                "Are you alright?", "How's your day?", "Is the sun okay?", 2,
                Question.DIFFICULTY_HARD, Category.BISTOENG);
        insertQuestion(q51);
        Question q52 = new Question("Gimingaw kaayo ko nimo.",
                "Let's go out.", "I really like the weather.", "I really miss you.", 3,
                Question.DIFFICULTY_HARD, Category.BISTOENG);
        insertQuestion(q52);
        Question q53 = new Question("Wala ko kasabot.",
                "There's no grass.", "I don't know.", "I don't understand.", 3,
                Question.DIFFICULTY_HARD, Category.BISTOENG);
        insertQuestion(q53);
        Question q54 = new Question("Imong mama",
                "Your mother", "Your father", "Gentleman", 1,
                Question.DIFFICULTY_HARD, Category.BISTOENG);
        insertQuestion(q54);
        Question q55 = new Question("Asa ka muadto?",
                "What are you up to?", "Where are you going?", "Where is your house?", 2,
                Question.DIFFICULTY_HARD, Category.BISTOENG);
        insertQuestion(q55);

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
        Question q56 = new Question("It's very expensive.",
                "Mahal na ata kita.", "Kita tika.", "Mahal kaayo.", 3,
                Question.DIFFICULTY_HARD, Category.ENGTOBIS);
        insertQuestion(q56);
        Question q57 = new Question("I’d like to pay.",
                "Gusto ko makig dula.", "Muuli nako.", "Mubayad nako.", 3,
                Question.DIFFICULTY_HARD, Category.ENGTOBIS);
        insertQuestion(q57);
        Question q58 = new Question("See you later.",
                "Kita lang ta unya.", "Padayon sa paglambo.", "Kita nalang duha.", 1,
                Question.DIFFICULTY_HARD, Category.ENGTOBIS);
        insertQuestion(q58);
        Question q59 = new Question("Where's the toilet?",
                "The carpet does not fly, right?", "Asa ang banyo?", "Asa namo?", 2,
                Question.DIFFICULTY_HARD, Category.ENGTOBIS);
        insertQuestion(q59);
        Question q60 = new Question("One language is never enough",
                "Dili gayod sakto kung usa ra ka sinutian.", "Lamin na kaayo matulog.", "Dili sakto kung guot.", 1,
                Question.DIFFICULTY_HARD, Category.ENGTOBIS);
        insertQuestion(q60);


    }

    public void addQuestion(Question question) { //creating a new question instance to be added dynamically
        db = getWritableDatabase();
        insertQuestion(question);
    }

    public void addQuestions(List<Question> questions) {
        db = getWritableDatabase();

        for (Question question : questions) {
            insertQuestion(question);
        }
    }
    //inserting into the database fetched from the Question object
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
    //for retrieving the questions
    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {//if the first entry exists, retrieve it, otherwise move to next
            do {//loop the rows and store them into the table
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