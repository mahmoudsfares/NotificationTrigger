package com.example.notificationtrigger.utils;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.notificationtrigger.sync.SchedulerFirebaseJobService;
import com.firebase.jobdispatcher.Driver;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;


public class JobSchedulerUtils {

    private static final String TRIGGER_NOTIFICATION_JOB_TAG = "trigger-notification";

    synchronized public static void scheduleNotification(@NonNull final Context context, int time){

        Driver driver = new GooglePlayDriver(context);
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(driver);
        // job is triggered in the service so we set service to SchedulerFirebaseJobService,
        // but here we define the parameters of this job to be done in the background..
        // in other words, services's doInBackground is triggered using the following parameters
        // and after the event in setTrigger occurs
        Job job = dispatcher.newJobBuilder().setService(SchedulerFirebaseJobService.class)
                .setTag(TRIGGER_NOTIFICATION_JOB_TAG)
                .setLifetime(Lifetime.FOREVER)
                .setRecurring(false)
                .setTrigger(Trigger.executionWindow(time*60, time*60))
                .setReplaceCurrent(true)
                .build();

        dispatcher.schedule(job);
    }
}
