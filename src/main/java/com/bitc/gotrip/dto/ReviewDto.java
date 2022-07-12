package com.bitc.gotrip.dto;

import lombok.Data;

import java.util.List;

@Data
public class ReviewDto {
    private int reviewPk;
    private int tripPk;
    private int reservePk;
    private String memberId;
    private String reviewTitle;
    private String reviewContents;
    private int reviewScore;    private String reviewCreate;
    private String reviewUpdate;
    private String reviewDelete;
    private String deletedYn;
    private int reRef;
    private int reLev;
    private int reSeq;
    private String tripName;
    private List<FileDto> fileList;

}
