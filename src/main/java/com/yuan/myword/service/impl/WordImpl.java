package com.yuan.myword.service.impl;

import com.yuan.myword.mapper.WordMapper;
import com.yuan.myword.pojo.Word;
import com.yuan.myword.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WordImpl implements WordService {
    @Autowired
    private WordMapper wordMapper;
    @Override
    public void insert(Word word) {
        wordMapper.insert(word);
    }

    @Override
    public List<Word> select() {
        return wordMapper.select();
    }
}
