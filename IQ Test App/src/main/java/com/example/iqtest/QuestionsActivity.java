package com.example.iqtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.animation.Animator;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.Subject;

public class QuestionsActivity extends AppCompatActivity {
    private TextView question, noIndicator;
    private LinearLayout optionsContainer;
    private Button nextBtn;
    private int count = 0;
    private List<QuestionModel>list;
    private int position = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        question = findViewById(R.id.question);
        noIndicator = findViewById(R.id.no_indicator);
        optionsContainer = findViewById(R.id.options_container);
        nextBtn = findViewById(R.id.next_btn);


        list = new ArrayList<>();
        list.add(new QuestionModel("What are the basic units of life?","Cells","Genes","Bacteria","DNA","Cells"));
        list.add(new QuestionModel("Plants mainly receive nutrients from what medium?","Water","Light","Air","Soil","Soil"));
        list.add(new QuestionModel("What is DNA stand for?","Duoxey nucleotide acid","Deoxyribonuclic acid","Deoxynitrifying amine","Deoxynuclid acid","Deoxyribonuclic acid"));
        list.add(new QuestionModel("What DNA base is paired with adenine?","Thymine","Lytosine","Guanine","Cytosine","Thymine"));
        list.add(new QuestionModel("What is the first stage of development after fertilization of an egg?","Embroy","Morula","Zygote","Blastula","Zygote"));
        list.add(new QuestionModel("What term is used for the most basic classification of a species?","Kingdom","Domain","Genus","Class","Kingdom"));
        list.add(new QuestionModel("What are plants growing in dry conditions called?","lithophytes","Mesophytes","Xenomorphs","Xerpphytes","Xerpphytes"));
        list.add(new QuestionModel("Which is not a part of an animal cell?","Mitochondria","Cell Walls","Cytoplasm","Cell membrane","Cell Walls"));


        for (int i = 0; i<4 ; i++){
                    optionsContainer.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                        @Override
                public void onClick(View v) {
                    checkAnswer((Button) v);
                }
            });
        }


        playAnim(question,0,list.get(position).getQuestion());

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextBtn.setEnabled(false);
                nextBtn.setAlpha(0.3f);
                enableOption(true);
                position++;
                if (position == list.size()){
                    Intent scoreIntent = new Intent(QuestionsActivity.this,ScoreActivity.class);
                    scoreIntent.putExtra("score",score);
                    scoreIntent.putExtra("total",list.size());
                    startActivity(scoreIntent);
                    finish();


                    return;
                    //score activity
                }
                count = 0;
                playAnim(question,0, list.get(position).getQuestion());
            }
        });

    }

    private void playAnim(final View view, final int value, final String data){
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(100).setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
              if (value == 0 && count <4 ){
                  String option = "";
                  if (count == 0){
                      option = list.get(position).getOptionA();
                  }else if (count == 1){
                      option = list.get(position).getOptionB();
                  }else if (count == 2){
                      option = list.get(position).getOptionC();
                  }else if (count == 3){
                      option = list.get(position).getOptionD();

                  }
                  playAnim(optionsContainer.getChildAt(count),0,option);
                  count ++;
              }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if  (value == 0 ){
                    try{
                        ((TextView)view).setText(data);
                        noIndicator.setText(position+1+"/"+list.size());

                    }catch (ClassCastException ex){
                        ((Button)view).setText(data);
                    }
                    view.setTag(data);
                    playAnim(view, 1,data);
                }

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }
    private void checkAnswer(Button selectedOption){
    enableOption(false);
    nextBtn.setEnabled(true);
    nextBtn.setAlpha(1);
    if (selectedOption.getText().toString().equals(list.get(position).getCorrectANS())){
        //correct answer
        score++;
        selectedOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4CAF50")));
    }else{
        //incorrect
        selectedOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
       Button correctoption= (Button) optionsContainer.findViewWithTag(list.get(position).getCorrectANS());
        correctoption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4CAF50")));
    }
    }
    private void enableOption(boolean enable){
        for (int i = 0; i<4 ; i++){
           optionsContainer.getChildAt(i).setEnabled(enable);
           if(enable == true){
               optionsContainer.getChildAt(i).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FA7074")));
           }
        }
    }
}
