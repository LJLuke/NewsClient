package com.example.luke.newsclient.presenter.recommend;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.luke.newsclient.base.BasePresenter;
import com.example.luke.newsclient.database.KeyWordHelper;
import com.example.luke.newsclient.database.NewsRecordHelper;
import com.example.luke.newsclient.entity.News;
import com.example.luke.newsclient.model.recommend.RecommendModel;
import com.example.luke.newsclient.view.fragment.recommend.RecommendTabFragment;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RecommendPre extends BasePresenter<RecommendTabFragment> implements IRecommendPre{
    private NewsRecordHelper newsRecordHelper;
    private KeyWordHelper keyWordHelper;
    private SQLiteDatabase mDatabase;

    private List<String> newsTypeList = new ArrayList<>();
    private List<String> keyWordsList = new ArrayList<>();

    private RecommendModel recommendModel;

    private int currentI = 0;
    public RecommendPre(){
        recommendModel = new RecommendModel();
    }

    @Override
    public void refreshNews() {

        newsRecordHelper = new NewsRecordHelper(getView().getContext(), "news.db", null, 1);
        keyWordHelper = new KeyWordHelper(getView().getContext(),"keyWords.db",null,1);
        mDatabase = newsRecordHelper.getWritableDatabase();
        keyWordsList.clear();
        keyWordsList.addAll(getKeyWords());
        int i = 0;
        List<News> newsList = new ArrayList<>();
        while (newsList.size() < 20 && i < keyWordsList.size()){
            String keyWord = keyWordsList.get(i);
            newsList.addAll(getNewsLikeKeyWord(keyWord));
            i++;
        }
        currentI = i;
        getView().onNewsArrived(newsList);
    }

    @Override
    public void loadMoreNews() {
        int i = currentI + 1;
        List<News> newsList = new ArrayList<>();
        while (newsList.size() < 20 && i < keyWordsList.size()){
            String keyWord = keyWordsList.get(i);
            newsList.addAll(getNewsLikeKeyWord(keyWord));
            i++;
        }
        currentI = i;
        getView().onMoreNewsArrived(newsList);
    }

    @Override
    public void initDb() {
        newsTypeList.add("top");
        newsTypeList.add("shehui");
        newsTypeList.add("caijing");
        newsTypeList.add("guoji");
        newsTypeList.add("yule");
        newsTypeList.add("tiyu");
        newsTypeList.add("junshi");
        newsTypeList.add("keji");
        newsTypeList.add("shishang");
        for (int i = 0;i < newsTypeList.size();i++){
            recommendModel.getRecommendNews(newsTypeList.get(i), new Observer<List<News>>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(List<News> newsList) {
                    addNewsIntoDb(newsList);
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

    public void addNewsIntoDb(List<News> list) {
        mDatabase = newsRecordHelper.getWritableDatabase();
        for (int i = 0;i < list.size();i++){
            String title = list.get(i).getTitle();
            String url = list.get(i).getUrl();
            String imageUrl = list.get(i).getThumbnail_pic_s();
            Cursor cursor = newsRecordHelper.getReadableDatabase().rawQuery(
                    "select * from news where title = '" + title + "' order by id desc ", null);
            if (cursor.getCount() == 0){
                mDatabase.execSQL("insert into news(title,url,imageUrl) values('" + title + "','" + url + "','" + imageUrl + "')");
            }
        }
        mDatabase.close();
    }

    private List<String> getKeyWords(){
        List<String> list = new ArrayList<>();
        Cursor cursor = keyWordHelper.getReadableDatabase().rawQuery(
                "select * from keyWords where keyWord like '%" + "" + "%' order by id desc ", null);
        while (cursor.moveToNext()) {
            list.add(cursor.getString(cursor.getColumnIndex("keyWord")));
        }
        return list;
    }

    private List<News> getNewsLikeKeyWord(String keyWord) {
        List<News> list = new ArrayList<>();
        Cursor cursor = newsRecordHelper.getReadableDatabase().rawQuery(
                "select * from news where title like '%" + keyWord + "%' order by id desc ", null);
        while (cursor.moveToNext()) {
            News news = new News();
            news.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            news.setUrl(cursor.getString(cursor.getColumnIndex("url")));
            news.setThumbnail_pic_s(cursor.getString(cursor.getColumnIndex("imageUrl")));
            list.add(news);
        }
        return list;
    }
}
