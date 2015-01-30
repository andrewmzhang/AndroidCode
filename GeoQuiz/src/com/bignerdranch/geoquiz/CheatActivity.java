package com.bignerdranch.geoquiz;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.w3c.dom.Text;


public class CheatActivity extends Activity {

    private boolean mAnswerIsTrue;
    private Button mShowAnswer;

    private TextView mAnswerTextView;
    private TextView mAPILevel;


    private boolean mCheatStatus;

    private static final String KEY_CHEAT = "status";

    public static final String EXTRA_ANSWER_IS_TRUE =
            "com.bignerdranch.android.geoquiz.answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN =
            "com.bignerdranch.android.geoquiz.answer_shown";

    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        mCheatStatus = isAnswerShown;
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            mCheatStatus = savedInstanceState.getBoolean(KEY_CHEAT);
            setAnswerShownResult(mCheatStatus);

        } else {
            setAnswerShownResult(false);
        }

        setContentView(R.layout.activity_cheat);

        mAPILevel = (TextView) findViewById(R.id.APIText);
        mAPILevel.setText("API Level" + Build.VERSION.SDK_INT);


        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        mAnswerTextView = (TextView) findViewById(R.id.answerTextView);
        mShowAnswer = (Button) findViewById(R.id.showAnswerButton);
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAnswerIsTrue)
                    mAnswerTextView.setText(R.string.true_button);
                else
                    mAnswerTextView.setText(R.string.false_button);
                setAnswerShownResult(true);
            }
        });


    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean(KEY_CHEAT, mCheatStatus);
    }


}