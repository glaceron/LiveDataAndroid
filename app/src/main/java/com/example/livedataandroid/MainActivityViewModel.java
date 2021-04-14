package com.example.livedataandroid;

import android.os.CountDownTimer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel
{
    private CountDownTimer timer;
    private MutableLiveData<Integer> _seconds = new MutableLiveData<Integer>();
    private MutableLiveData<Boolean> finished = new MutableLiveData<Boolean>();
    public MutableLiveData<Long> timerValue = new MutableLiveData<Long>();

    public void startTimer()
    {
        timer = new CountDownTimer(timerValue.getValue(),1000)
        {
            @Override
            public void onTick(long millisUntilFinished)
            {
                int timeLeft = (int) (millisUntilFinished/1000);
                _seconds.setValue(timeLeft);            }

            @Override
            public void onFinish()
            {
                finished.setValue(true);
            }
        }.start();
    }

    public void stopTimer()
    {
        timer.cancel();
    }

    public LiveData<Integer> getSeconds()
    {
        return _seconds;
    }

    public MutableLiveData<Boolean> getFinished()
    {
        return finished;
    }
}
