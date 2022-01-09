package com.ftn.Taverna.elastic.services;

import com.ftn.Taverna.elastic.controllers.dtoS.SimpleQueryES;
import com.ftn.Taverna.lucene.search.QueryBuilderCustom;
import com.ftn.Taverna.util.SearchType;
import org.elasticsearch.index.query.QueryBuilder;

public class SearchQueryGenerator {

    public static QueryBuilder createMatchQueryBuilder(SimpleQueryES simpleQueryES) {

        if(simpleQueryES.getValue().startsWith("\"") && simpleQueryES.getValue().endsWith("\"") ){
            return QueryBuilderCustom.buildQuery(SearchType.PHRASE, simpleQueryES.getField(), simpleQueryES.getValue());

        }else{
            return QueryBuilderCustom.buildQuery(SearchType.FUZZY,simpleQueryES.getField(),simpleQueryES.getValue());
        }
    }

    public static QueryBuilder createRangeQuery(SimpleQueryES simpleQueryES){
        return QueryBuilderCustom.buildQuery(SearchType.RANGE, simpleQueryES.getField(), simpleQueryES.getValue());

    }
    public static QueryBuilder createWordQuery(SimpleQueryES simpleQueryES){
        return QueryBuilderCustom.buildQuery(SearchType.MATCH, simpleQueryES.getField(), simpleQueryES.getValue());

    }

}
