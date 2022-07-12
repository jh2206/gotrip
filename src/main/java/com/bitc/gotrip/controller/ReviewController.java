package com.bitc.gotrip.controller;

import com.bitc.gotrip.dto.ReviewDto;
import com.bitc.gotrip.service.ReviewService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @RequestMapping("/")
    public String index() throws Exception {
        return "index";
    }

    // 게시판 전체 목록 출력
    @RequestMapping("/review/boardList")
    public ModelAndView reviewOpenBoardList(@RequestParam(required = false, defaultValue = "1") int pageNum, HttpServletRequest request) throws Exception{
        ModelAndView mv = new ModelAndView("/review/boardList");
        PageInfo<ReviewDto> dataList = new PageInfo<>(reviewService.selectBoardListPage(pageNum), 5);
        mv.addObject("dataList", dataList);

        return mv;

    }

    @RequestMapping("/review/writeBoard")
    public String reviewWriteBoard() throws Exception {
        return "/review/boardWrite";
    }
//    @RequestMapping("/review/writeBoard")
//    public ModelAndView reviewWriteBoard() throws Exception {
//        ModelAndView mv = new ModelAndView("/review/boardWrite");
//
//        HttpSession session = request.getSession();
//
//        MemberDto user = new MemberDto();
//        user.setMemberId((String)session.getAttribute("memberId"));
//
//
//        List<ReserveDto> reserveList = reserveService.selectReseveListById(user.getMemberId());
//        mv.addObject("reserveList", reserveList);
//        return mv;
//    }

//    @RequestMapping("/review/insertBoard")
//    public String reviewInsertBoard(ReviewDto review, MultipartHttpServletRequest multiUploadFiles)throws Exception {
//        reviewService.insertBoard(review, multiUploadFiles);
//        return "redirect:/review/boardList";
//    }
}
