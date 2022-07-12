package com.bitc.gotrip.dto;

import lombok.Data;

import java.util.List;

@Data
public class QnADto {
    private int qnaPk;
    private String memberId;
    private String qnaTitle;
    private String qnaContents;
    private String qnaCreate;
    private String qnaUpdate;
    private String qnaDelete;
    private String deletedYn;
    private int reRef;
    private int reLev;
    private int reSeq;
    private List<FileDto> fileList;
}
