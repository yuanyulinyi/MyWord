package com.yuan.myword.controller;

import com.yuan.myword.pojo.Result;
import com.yuan.myword.pojo.Word;
import com.yuan.myword.service.WordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class WordController {
    @Autowired
    private WordService wordService;

    @PostMapping("/insert")
    Result insert(@RequestBody Word word){
        wordService.insert(word);
        return Result.success();
    }

    @GetMapping("/select")
    Result select(){
        List<Word> list = wordService.select();
        return Result.success(list);
    }
}
