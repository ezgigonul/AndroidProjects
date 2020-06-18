package com.example.englishpractice;

import android.content.Context;
import android.os.AsyncTask;

//AsycTask Class
public class BackgroundTask extends AsyncTask<String, String, String> {
    Context ctx;
    BackgroundTask(Context ctx)
    {
        this.ctx=ctx;
    }
    @Override
    protected String doInBackground(String... strings) {
        String method=strings[0];
        if(method.equals("addinfo"))
        {
            String name= strings[1];
            String email= strings[2];
            String password= strings[3];
            Database db = new Database(ctx);
            //Insert new user info to DB
            db.insert(name,email,password);
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
