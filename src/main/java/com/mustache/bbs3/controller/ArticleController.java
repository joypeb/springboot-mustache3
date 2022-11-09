package com.mustache.bbs3.controller;

import com.mustache.bbs3.domain.Article;
import com.mustache.bbs3.domain.dto.ArticleDto;
import com.mustache.bbs3.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Autowired


    @GetMapping("")
    public String index() {
        return "redirect:/articles/list";
    }

    @GetMapping("/new")
    public String createPage() {
        return "new";
    }

    @PostMapping("/add")
    public String add(ArticleDto articleDto) {
        log.info(articleDto.getTitle());
        Article saveArticle = articleRepository.save(articleDto.toEntity());

        String returnUrl = "redirect:/articles/id/" + saveArticle.getId();
        return returnUrl;
    }

    @GetMapping("/id/{id}")
    public String selectOne(@PathVariable Long id, Model model) {
        Optional<Article> selectOne = articleRepository.findById(id);

        if(selectOne.isEmpty()) {
            return "error";
        }

        model.addAttribute("article", selectOne.get());
        return "id";
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("articles", articleRepository.findAll());
        return "list";
    }
}
