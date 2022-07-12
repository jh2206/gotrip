package com.bitc.gotrip.dto;

import lombok.Data;

@Data
public class FileDto {
    private int filePk;
    private int tableNum;
    private int pkNum;
    private String originalFileName;
    private String fileUuid;
    private String storedFilePath;
    private long fileSize;
    private String deletedYn;
}
