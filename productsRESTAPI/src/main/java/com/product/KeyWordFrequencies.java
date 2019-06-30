package com.product;

public class KeyWordFrequencies {

    public String keyword;
    public int count;

    public KeyWordFrequencies(){};

    public KeyWordFrequencies(String keyword, int count){
        this.setKeyword(keyword);
        this.setCount(count);
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
