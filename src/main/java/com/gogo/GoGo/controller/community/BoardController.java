package com.gogo.GoGo.controller.community;

import com.gogo.GoGo.domain.community.Board;
import com.gogo.GoGo.repository.BoardRepository;
import com.gogo.GoGo.service.CommunityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(value = "/community/board")
@RestController
@Slf4j
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    //게시판 종류 보기
    @GetMapping
    public List<Board> getBoard(){
        return boardRepository.findAll();
    }
}
