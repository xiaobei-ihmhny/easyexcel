package com.alibaba.easyexcel.test;

import com.alibaba.easyexcel.test.model.WriteModel;
import com.alibaba.easyexcel.test.util.FileUtil;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.support.ExcelTypeEnum;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import static com.alibaba.easyexcel.test.util.DataUtil.*;
import static com.alibaba.easyexcel.test.util.DataUtil.createTestListJavaMode;

/**
 * 报备测试
 *
 * @author xiaobei-ihmhny
 * @date 2019-09-03 07:08:08
 */
public class MyFilingTest {

    @Test
    public void writeV2007WithTemplate() throws IOException {
        InputStream inputStream = FileUtil.getResourcesFileInputStream("导出所选报备单模板.xlsx");
        OutputStream out = new FileOutputStream("C:\\Users\\Legend\\Desktop\\报备单列表20190903.xlsx");
        ExcelWriter writer = EasyExcelFactory.getWriterWithTemp(inputStream,out, ExcelTypeEnum.XLSX,true);
        //写第一个sheet, sheet1  数据全是List<String> 无模型映射关系
        Sheet sheet1 = new Sheet(1, 3);
//        sheet1.setSheetName("报备单列表");
//        sheet1.setStartRow(1);

        //or 设置自适应宽度
        //sheet1.setAutoWidth(Boolean.TRUE);
        writer.write1(createTestListObject(), sheet1);
        writer.finish();
        out.close();

    }
}
