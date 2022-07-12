package com.bitc.gotrip.service;

import com.bitc.gotrip.dto.ReviewDto;
import com.github.pagehelper.Page;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface ReviewService {
    Page<ReviewDto> selectBoardListPage(int pageNum)throws Exception;

//    void insertBoard(ReviewDto review, MultipartHttpServletRequest multiUploadFiles) throws Exception;
}
