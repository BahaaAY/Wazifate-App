package com.wazifate.wazifate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.wazifate.wazifate.ApiService.ApiService;
import com.wazifate.wazifate.ApiService.RetrofitClient;
import com.wazifate.wazifate.Models.Quiz.QuizQuestion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuizScreen extends AppCompatActivity {
    private int currentQuestionIndex = 0;
    private TextView tvQuestion, tvQuestionNumber, text1, text2, text3, text4;
    private Button btnNext;
    private CardView radioButton1, radioButton2, radioButton3, radioButton4;
    private List<QuizQuestion> questions;
    private ProgressBar progressBar;
    private int correctQuestion = 0;
    String txt;
    private ImageView cardBg, backArrowBTN, cardBg2, cardBg3, cardBg4;


    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        apiService = RetrofitClient.getInstance().getRestApi();

        Intent intent = getIntent();
        int quizId = intent.getIntExtra("quizId", 1);
        System.out.println("Quiz ID: " + quizId);
        initView();
//        variantClick1();
//        variantClick2();
//        variantClick3();
//        variantClick4();
        apiService.getQuizQuestions(quizId).enqueue(new Callback<List<QuizQuestion>>() {
            @Override
            public void onResponse(Call<List<QuizQuestion>> call, Response<List<QuizQuestion>> response) {
                    questions = response.body();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    questions.forEach(quizQuestion -> {
                        System.out.println("Question: " + quizQuestion.getQuestion());
                    });
                }
                displayData();


            }

            @Override
            public void onFailure(Call<List<QuizQuestion>> call, Throwable t) {
                Toast.makeText(QuizScreen.this, "Failed to load questions", Toast.LENGTH_SHORT).show();
            }
        });


//        String subject = intent.getStringExtra("todo");
//
//        if (subject.equals("todo")) {
////            questionsAnswerMap = Utils.getRandomLiteratureAndGeographyQuestions(this, getString(R.string.literature), Constants.QUESTION_SHOWING);
//        }

        backArrowBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.btnNextQuestion).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View view) {

                if (!txt.isEmpty()) {

                    //check if the answer is correct
                    if (questions.get(currentQuestionIndex).getCorrectAnswer().equals(txt)) {
                        correctQuestion++;
                    }

                    currentQuestionIndex++;
                    variantClear();
                    if (btnNext.getText().equals(getString(R.string.next))) {
                        displayNextQuestions();
                    } else {
//                        Intent intentResult = new Intent(GeographyOrLiteratureQuizActivity.this, FinalResultActivity.class);
//                        intentResult.putExtra(Constants.SUBJECT, subject);
//                        intentResult.putExtra(Constants.CORRECT, correctQuestion);
//                        intentResult.putExtra(Constants.TYPE, "math");
//                        intentResult.putExtra(Constants.INCORRECT, Constants.QUESTION_SHOWING - correctQuestion);
//                        intentResult.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                        startActivity(intentResult);
                    }
                } else {
                    Toast.makeText(QuizScreen.this, "wwwwww", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });



    }

    private void variantClick1() {
        radioButton1.setOnClickListener(v -> {
                    cardBg.setImageResource(R.drawable.set_checked_to_variant);
                    cardBg2.setImageResource(R.drawable.set_un_checked_to_variant);
                    cardBg3.setImageResource(R.drawable.set_un_checked_to_variant);
                    cardBg4.setImageResource(R.drawable.set_un_checked_to_variant);
                    txt = text1.getText().toString();
                }
        );
    }

    private void variantClick2() {
        radioButton2.setOnClickListener(v -> {
                    cardBg2.setImageResource(R.drawable.set_checked_to_variant);
                    cardBg.setImageResource(R.drawable.set_un_checked_to_variant);
                    cardBg3.setImageResource(R.drawable.set_un_checked_to_variant);
                    cardBg4.setImageResource(R.drawable.set_un_checked_to_variant);
                    txt = text2.getText().toString();

                }
        );
    }

    private void variantClick3() {
        radioButton3.setOnClickListener(v -> {
                    cardBg3.setImageResource(R.drawable.set_checked_to_variant);
                    cardBg2.setImageResource(R.drawable.set_un_checked_to_variant);
                    cardBg.setImageResource(R.drawable.set_un_checked_to_variant);
                    cardBg4.setImageResource(R.drawable.set_un_checked_to_variant);
                    txt = text3.getText().toString();

                }
        );
    }

    private void variantClick4() {
        radioButton4.setOnClickListener(v -> {
                    cardBg4.setImageResource(R.drawable.set_checked_to_variant);
                    cardBg2.setImageResource(R.drawable.set_un_checked_to_variant);
                    cardBg3.setImageResource(R.drawable.set_un_checked_to_variant);
                    cardBg.setImageResource(R.drawable.set_un_checked_to_variant);
                    txt = text4.getText().toString();

                }
        );
    }

    private void variantClear() {
        txt = "";
        cardBg.setImageResource(R.drawable.set_un_checked_to_variant);
        cardBg2.setImageResource(R.drawable.set_un_checked_to_variant);
        cardBg3.setImageResource(R.drawable.set_un_checked_to_variant);
        cardBg4.setImageResource(R.drawable.set_un_checked_to_variant);
    }

    @SuppressLint("SetTextI18n")
    private void displayNextQuestions() {
        setAnswersToRadioButton();
        tvQuestion.setText(questions.get(currentQuestionIndex).getQuestion());
        tvQuestionNumber.setText("" + (currentQuestionIndex + 1));
        progressBar.setProgress(currentQuestionIndex * 10);
        progressBar.setMax(100);


        if (currentQuestionIndex == 10 - 1) {
            btnNext.setText("Finish");
        }
    }

    @SuppressLint("SetTextI18n")
    private void displayData() {
        tvQuestion.setText(questions.get(currentQuestionIndex).getQuestion());
        tvQuestionNumber.setText("" + (currentQuestionIndex + 1));

        setAnswersToRadioButton();
    }

    private void setAnswersToRadioButton() {


        text1.setText(questions.get(currentQuestionIndex).getChoices().get(0).getChoice());
        text2.setText(questions.get(currentQuestionIndex).getChoices().get(1).getChoice());
        text3.setText(questions.get(currentQuestionIndex).getChoices().get(2).getChoice());
        text4.setText(questions.get(currentQuestionIndex).getChoices().get(3).getChoice());

    }
    public void initView() {
//        questions = new ArrayList<>(questionsAnswerMap.keySet());
        txt = "";

        tvQuestion = findViewById(R.id.textView78);
        tvQuestionNumber = findViewById(R.id.textView18);
        btnNext = findViewById(R.id.btnNextQuestion);
        text1 = findViewById(R.id.radioButton1Text);
        text2 = findViewById(R.id.radioButto21Text);
        text3 = findViewById(R.id.radioButton3Text);
        text4 = findViewById(R.id.radioButton4Text);
        backArrowBTN = findViewById(R.id.back_arrow);
        cardBg = findViewById(R.id.setChecked);
        cardBg2 = findViewById(R.id.setChecked2);
        cardBg3 = findViewById(R.id.setChecked3);
        cardBg4 = findViewById(R.id.setChecked4);
        progressBar = findViewById(R.id.progressbar);
        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);
        radioButton4 = findViewById(R.id.radioButton4);
    }
}