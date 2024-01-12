package com.train.aimforthehead.controllers.views;

import com.train.aimforthehead.domain.dto.BookDto;
import com.train.aimforthehead.domain.entities.BookEntity;
import com.train.aimforthehead.domain.entities.CategoryDetail;
import com.train.aimforthehead.domain.entities.CategoryEntity;
import com.train.aimforthehead.domain.entities.PurchaseDetail;
import com.train.aimforthehead.mappers.Mapper;
import com.train.aimforthehead.repositories.CategoryRepository;
import com.train.aimforthehead.repositories.PurchaseDetailRepository;
import com.train.aimforthehead.services.BookService;
import com.train.aimforthehead.services.CategoryDetailService;
import com.train.aimforthehead.services.PurchaseDetailService;
import com.train.aimforthehead.services.UserService;
import com.train.aimforthehead.services.impl.UserServiceImpl;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@Log
public class BookViewController {
    private BookService bookService;
    private Mapper<BookEntity, BookDto> bookMapper;
    private CategoryDetailService categoryDetailService;
    private PurchaseDetailRepository purchaseDetailRepository;
    private CategoryRepository categoryRepository;
    @Autowired
    private UserServiceImpl userService;
    public BookViewController(BookService bookService, Mapper<BookEntity, BookDto> bookMapper, CategoryDetailService categoryDetailService, PurchaseDetailRepository purchaseDetailRepository, CategoryRepository categoryRepository) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
        this.categoryDetailService = categoryDetailService;
        this.purchaseDetailRepository = purchaseDetailRepository;
        this.categoryRepository = categoryRepository;
    }

//    @GetMapping(path = "/books/view")
//    public String viewAllBooks(Model model)
//    {
////        List<BookEntity> bookEntityList = bookService.findAll();
////        List<CategoryEntity> categoryEntityList = categoryRepository.findAll();
////        model.addAttribute("books", bookEntityList);
////        model.addAttribute("categories", categoryEntityList);
//        return "index";
//    }
    @GetMapping(path = "/book/{id}")
    public String viewBookDetail(@PathVariable("id") int id, Model model)
    {
        model.addAttribute("user", userService.getInfo());
        Optional<BookEntity> bookEntity = bookService.findOne(id);
        if(bookEntity.isEmpty())
        {
            return "index";
        }
        else
        {
            return bookEntity.map(bookEntity1 -> {
                List<CategoryDetail> categoryDetailList = categoryDetailService.findByBookID(bookEntity1.getId());
                model.addAttribute("book", bookEntity1);
                model.addAttribute("categoryDetails", categoryDetailList);
                return "detail";
            }).orElse("index");
        }
    }
//    @PostMapping(path = "/books/view/result")
//    public String viewBooksContain(@ModelAttribute("request") BookEntity result, Model model)
//    {
//        String message = "No result";
//        List<BookEntity> bookEntityList = bookService.findBooksLike(result.getTitle());
//        if(bookEntityList.isEmpty())
//        {
//            model.addAttribute("message", message);
//        }
//        else
//        {
//            model.addAttribute("books", bookEntityList);
//        }
//        return "index";
//    }
//    @PostMapping(path = "/filter/category")
//    public String filerByCategory(@ModelAttribute("filter_cat_id") int cat_id, Model model){
//        List<CategoryDetail> categoryDetailList = categoryDetailService.findByCategoryID(cat_id);
//        List<BookEntity> bookEntityList = new ArrayList<>();
//        for(CategoryDetail categoryDetail: categoryDetailList){
//            bookEntityList.add(categoryDetail.getBook());
//        }
//        model.addAttribute("books", bookEntityList);
//        return "index";
//    }
}
