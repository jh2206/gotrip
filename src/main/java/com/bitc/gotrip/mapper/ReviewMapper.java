package com.bitc.gotrip.mapper;

import com.bitc.gotrip.dto.FileDto;
import com.bitc.gotrip.dto.ReviewDto;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {
    Page<ReviewDto> selectBoardListPage() throws Exception;

//    void insertBoard(ReviewDto review)throws Exception;
//
//    void insertBoardFileList(List<FileDto> fileList) throws Exception;
}
