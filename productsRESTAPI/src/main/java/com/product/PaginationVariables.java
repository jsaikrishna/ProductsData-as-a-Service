package com.product;

import java.security.PublicKey;

public class PaginationVariables {

    public int from;
    public int size;

    public PaginationVariables(){}

    public PaginationVariables(int from, int size){
        this.setFrom(from);
        this.setSize(size);
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
