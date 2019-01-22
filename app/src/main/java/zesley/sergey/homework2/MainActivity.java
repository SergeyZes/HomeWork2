package zesley.sergey.homework2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;

public class MainActivity extends AppCompatActivity {
    EditText mEditTextTask1;
    TextView mTextViewTask1;
    PublishSubject<String> subTask1;
    EditText mEditTextSrc1;
    EditText mEditTextSrc2;
    EditText mEditTextEventBus;
    Button mButtonSrc1;
    Button mButtonSrc2;
    Button mButtonEventBus;
    TextView mTextViewSubscriber;
    TextView mTextViewSubscriber2;
    PublishSubject<String> subSrc1;
    PublishSubject<String> subSrc2;
    EventBus eventBus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditTextTask1 = findViewById(R.id.edit_text_task1);
        mTextViewTask1 = findViewById(R.id.text_view_task1);
        subTask1 = PublishSubject.create();
        subTask1.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                mTextViewTask1.setText(s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

        mEditTextTask1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                subTask1.onNext(mEditTextTask1.getText().toString());
            }
        });


        mEditTextSrc1 = findViewById(R.id.edit_text_task2_src1);
        mEditTextSrc2 = findViewById(R.id.edit_text_task2_src2);
        mEditTextEventBus = findViewById(R.id.edit_text_event_bus);

        mButtonSrc1 = findViewById(R.id.btn_task2_src1);
        mButtonSrc2 = findViewById(R.id.btn_task2_src2);
        mButtonEventBus = findViewById(R.id.btn_event_bus);
        mTextViewSubscriber = findViewById(R.id.text_view_subscriber);
        mTextViewSubscriber2 = findViewById(R.id.text_view_subscriber2);
        subSrc1 = PublishSubject.create();
        subSrc2 = PublishSubject.create();
        eventBus = new EventBus(subSrc1, subSrc2);

        mButtonSrc1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subSrc1.onNext(mEditTextSrc1.getText().toString());
            }
        });
        mButtonSrc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subSrc2.onNext(mEditTextSrc2.getText().toString());
            }
        });
        mButtonEventBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventBus.onNext(mEditTextEventBus.getText().toString());
            }
        });

        eventBus.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                mTextViewSubscriber.setText(s);

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
        eventBus.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                mTextViewSubscriber2.setText(s);

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }
}
