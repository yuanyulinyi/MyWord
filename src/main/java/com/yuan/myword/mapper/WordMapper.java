package com.yuan.myword.mapper;

import com.yuan.myword.pojo.Word;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface WordMapper {
    @Insert("insert into word(name,trans) values(#{name},#{trans})")
    void insert(Word word);

    @Select("select * from word")
    List<Word> select();
}
