package com.yuan.myword.utils;

import com.alibaba.excel.EasyExcel;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Slf4j
public class ExcelUtil<T> {
    public Class<T> clazz;
    public ExcelUtil(Class<T> clazz)
    {
        this.clazz = clazz;
    }
    /**
     * 对excel表单默认第一个索引名转换成list（EasyExcel）
     *
     * @param is 输入流
     * @return 转换后集合
     */
    public List<T> importEasyExcel(InputStream is) throws Exception
    {
        return EasyExcel.read(is).head(clazz).sheet().doReadSync();
    }

    /**
     * 对list数据源将其里面的数据导入到excel表单（EasyExcel）
     * @param list 导出数据集合
     * @param sheetName 工作表的名称
     * @return 结果
     */
    public void exportEasyExcel(HttpServletResponse response, List<T> list, String sheetName)
    {
        try
        {
            EasyExcel.write(response.getOutputStream(), clazz).sheet(sheetName).doWrite(list);
        }
        catch (IOException e)
        {
            log.error("导出EasyExcel异常{}", e.getMessage());
        }
    }
}
