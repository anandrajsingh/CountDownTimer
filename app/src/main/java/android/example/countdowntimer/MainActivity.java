package android.example.countdowntimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MalibuCountDownTimer countDownTimer;
    private long timeElapsed;
    private boolean timeHasStarted = false;
    private Button startB;
    private TextView text;
    private TextView timeElapsedView;

    private final long startTime = 50 * 1000;
    private final long interval = 1 * 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startB = (Button) findViewById(R.id.button);
        startB.setOnClickListener(this);

        text = (TextView) findViewById(R.id.timer);
        timeElapsedView = (TextView) findViewById(R.id.timeElapsed);

        countDownTimer = new MalibuCountDownTimer(startTime, interval);
        text.setText(text.getText() + String.valueOf(startTime));

    }

    @Override
    public void onClick(View view) {
        if(!timeHasStarted){
            countDownTimer.start();
            timeHasStarted = true;
            startB.setText("Start");
        }else{
            countDownTimer.cancel();
            timeHasStarted = false;
            startB.setText("Reset");
        }

    }

    private class MalibuCountDownTimer extends CountDownTimer {
        public MalibuCountDownTimer(long startTime, long interval) {
            super(startTime, interval);
        }

        @Override
        public void onTick(long l) {
            text.setText("Time remain: "+ l);
            timeElapsed = startTime - l;
            timeElapsedView.setText("Time Elapsed: "+ String.valueOf(timeElapsed));

        }

        @Override
        public void onFinish() {
            text.setText("Time's up!");
            timeElapsedView.setText("Time Elapsed: " + String.valueOf(startTime));
        }
    }
}