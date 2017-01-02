package com.akoBet.controller;

import com.akoBet.entity.News;
import com.akoBet.entity.User;
import com.akoBet.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by Arek on 27.12.2016.
 */
@Controller
public class NewsController {

    @Autowired
    private NewsService newsService;

    @RequestMapping(value = "/admin/news/add", method = RequestMethod.GET)
    public String showForm(News news) {
        return "addNews";
    }

    @RequestMapping(value = "/admin/news/add", method = RequestMethod.POST)
    public String add(@Valid News news, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "addNews";
        }
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        news.setAuthor(user.getUsername());
        model.addAttribute("news", news);
        newsService.create(news);
        model.addAttribute("message", "akobet.news.addSuccess");
        return "message";
    }

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public String showNews() {
        return "news";
    }

    @RequestMapping(value = "/newsView/{id}", method = RequestMethod.GET)
    public String showArticle(@PathVariable Long id, Model model) {

        News article = newsService.findById(id);
        model.addAttribute("article", article);

        return "newsView";
    }
}
