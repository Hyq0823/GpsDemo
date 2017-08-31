package com.spzh.form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by hyq on 2017/8/31.
 * 历史轨迹Form
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class HistoryTrailForm {
    private String result;
    private List<GpsForm> tracks;
    private PageForm pagination;
    //{"result":0,"tracks":null,"pagination":null}



    public PageForm getPagination() {
        return pagination;
    }
    public void setPagination(PageForm pagination) {
        this.pagination = pagination;
    }
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<GpsForm> getTracks() {
        return tracks;
    }

    public void setTracks(List<GpsForm> tracks) {
        this.tracks = tracks;
    }

}
