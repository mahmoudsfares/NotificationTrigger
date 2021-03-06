package com.example.notificationtrigger.sync;

import android.app.IntentService;
import android.content.Intent;
import androidx.annotation.Nullable;

/*
run work inside onHandleIntent in a separate background thread
 */
public class TriggerIntentService extends IntentService {

    public TriggerIntentService() {
        super("TriggerIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String action = intent.getAction();
        TriggerTasks.executeTask(this, action);
    }
}
