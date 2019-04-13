package com.example.luke.newsclient.netWork;

public class SearchWrapper <T> {
    int errno;
    T data;

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static class DataBean<T>{
        String query;
        T news;

        public String getQuery() {
            return query;
        }

        public void setQuery(String query) {
            this.query = query;
        }

        public T getNews() {
            return news;
        }

        public void setNews(T news) {
            this.news = news;
        }
    }
}
