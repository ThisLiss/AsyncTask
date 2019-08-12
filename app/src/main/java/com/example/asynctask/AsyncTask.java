package com.example.asynctask;

import java.util.concurrent.TimeUnit;

public class AsyncTask extends android.os.AsyncTask<String, Integer, String> {

    private OnAsync interAsync;

    public AsyncTask(OnAsync interAsync){
        this.interAsync = interAsync;
    }


    public interface OnAsync{
        void onProgBar(int pos);
        void onEnd(String url);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        if(strings[0].equals("www.ya.ru")) {
            int count = 0;
            for (int i = 0; i < 100; i++) {
                if (isCancelled()) return null;
                publishProgress(++count);
                try {
                    TimeUnit.MILLISECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return "https://yandex.ru/search/?text=android";
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        interAsync.onProgBar(values[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        interAsync.onEnd(s);
    }
}
