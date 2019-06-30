package com.product;

import java.util.List;

public class Keywords {
    List<KeyWordFrequencies> keywordFrequencies;

    public Keywords(){}

    public Keywords(List<KeyWordFrequencies> keywordFrequencies){
        this.setKeywordFrequencies(keywordFrequencies);

    }

    public List<KeyWordFrequencies> getKeywordFrequencies() {
        return keywordFrequencies;
    }

    public void setKeywordFrequencies(List<KeyWordFrequencies> keywordFrequencies) {
        this.keywordFrequencies = keywordFrequencies;
    }
}
