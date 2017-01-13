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
        return "admin/news/addNews";
    }

    @RequestMapping(value = "/admin/news/add", method = {RequestMethod.PUT, RequestMethod.POST})
    public String add(@Valid News news, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/news/addNews";
        } else {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (news.getId() == null) {
                news.setAuthor(user.getUsername());
            }
            model.addAttribute("news", news);
            newsService.create(news);
            model.addAttribute("message", "akobet.news.addSuccess");
            model.addAttribute("link", "add");
            model.addAttribute("linkMessage", "akobet.news.add.redirect");
            return "message";
        }
    }

    @RequestMapping(value = "/admin/news/manage", method = RequestMethod.GET)
    public String showManage() {
        return "admin/news/manageNews";
    }

    @RequestMapping(value = "/admin/news/edit/{id}", method = RequestMethod.GET)
    public String showEditForm(@PathVariable Long id, Model model) {

        News news = newsService.findById(id);
        if (news == null) {
            model.addAttribute("message", "akobet.news.notExists");
            model.addAttribute("link", "../manage");
            model.addAttribute("linkMessage", "akobet.news.list.redirect");
            return "message";
        } else {
            model.addAttribute("news", news);
            return "admin/news/addNews";
        }
    }

    @RequestMapping(value = "/admin/news/delete/{id}")
    public String deleteNews(@PathVariable Long id, Model model) {

        if (newsService.findById(id) == null) {
            model.addAttribute("message", "akobet.news.notExists");
        } else {
            newsService.deleteById(id);
            model.addAttribute("message", "akobet.news.deleteSuccess");
        }
        model.addAttribute("linkMessage", "akobet.news.list.redirect");
        model.addAttribute("link", "../manage");
        return "message";

    }

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public String showNews() {
        return "user/news/news";
    }

    @RequestMapping(value = "/newsView/{id}", method = RequestMethod.GET)
    public String showArticle(@PathVariable Long id, Model model) {

        News article = newsService.findById(id);
        if (article == null) {
            model.addAttribute("message", "akobet.news.notExists");
            model.addAttribute("link", "../news");
            model.addAttribute("linkMessage", "akobet.news.list.go");
            return "message";
        } else {
            model.addAttribute("article", article);
        }
        return "user/news/newsView";
    }
}
