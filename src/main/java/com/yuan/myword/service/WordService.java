package com.yuan.myword.service;

import com.yuan.myword.pojo.Word;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WordService {
    void insert(Word word);

    List<Word> select();
}
