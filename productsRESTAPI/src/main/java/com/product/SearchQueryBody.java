package com.product;

import java.util.List;

public class SearchQueryBody {

    public List<ConditionVariables> conditions;
    public PaginationVariables pagination;

    public SearchQueryBody(){};

    public SearchQueryBody(List<ConditionVariables> conditions, PaginationVariables pagination){
        this.setConditions(conditions);
        this.setPagination(pagination);
    }

    public List<ConditionVariables> getConditions() {
        return conditions;
    }

    public void setConditions(List<ConditionVariables> conditions) {
        this.conditions = conditions;
    }

    public PaginationVariables getPagination() {
        return pagination;
    }

    public void setPagination(PaginationVariables pagination) {
        this.pagination = pagination;
    }
}
