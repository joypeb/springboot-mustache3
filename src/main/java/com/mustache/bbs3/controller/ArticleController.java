package com.mustache.bbs3.controller;

import com.mustache.bbs3.domain.dto.ArticleDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/article")
public class ArticleController {

    @GetMapping("")
    public String index() {
        return "";
    }

    @GetMapping("/new")
    public String createPage() {
        return "new";
    }

    @PostMapping("/add")
    public String add(ArticleDto articleDto) {
        log.info(articleDto.getTitle());

    }
}
