package com.example.finalexam07600498.db;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

public class Repository {
    private Context context;
    private Context mContent;

    public Repository(Context context) {
        this.context = context;
        this.mContent = context;
    }

    public interface GetCallback {
        void onSuccess(List<LedgerItem> ledgerItemList);
    }

    public void getUser(GetCallback callback) {
        GetTask getTask = new GetTask(context, callback);
        getTask.execute();
    }

    private static class GetTask extends AsyncTask<Void, Void, List<LedgerItem>> {
        private Context context;
        private GetCallback callback;

        public GetTask(Context context, GetCallback callback) {
            this.context = context;
            this.callback = callback;
        }

        @Override
        protected void onPostExecute(List<LedgerItem> ledgerItemList) {
            super.onPostExecute(ledgerItemList);
            callback.onSuccess(ledgerItemList);
        }

        @Override
        protected List<LedgerItem> doInBackground(Void... voids) {
            AppDatabase db = AppDatabase.getInstance(context);
            List<LedgerItem> itemList = db.ledgerDao().getAll();
            return itemList;
        }
    }

    public interface InsertCallback {
        void onSuccess();
    }

    public void insertRegister(LedgerItem ledgerItem, InsertCallback callback) {
        InsertTask insertTask = new InsertTask(mContent, callback);
        insertTask.execute(ledgerItem);
    }

    private static class InsertTask extends AsyncTask<LedgerItem, Void, Void> {
        private Context context;
        private InsertCallback callback;

        public InsertTask(Context context, InsertCallback callback) {
            this.context = context;
            this.callback = callback;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            callback.onSuccess();
        }

        @Override
        protected Void doInBackground(LedgerItem... appDatabaseEntities) {
            AppDatabase db = AppDatabase.getInstance(context);
            for (LedgerItem item : appDatabaseEntities) {
                db.ledgerDao().insert(item);
            }
            return null;
        }
    }
}
