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
@SuppressWarnings("ALL")
public class MyFilingTest {

    @Test
    public void writeV2007WithTemplate() throws IOException {
        InputStream inputStream = FileUtil.getResourcesFileInputStream("导出所选报备单模板.xlsx");
        OutputStream out = new FileOutputStream("C:\\Users\\xiaobei\\Desktop\\报备单列表20190903.xlsx");
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

    @Test
    public void writeV2007WithTemplateMoreTable() throws IOException {
        InputStream inputStream = FileUtil.getResourcesFileInputStream("报备订单信息导出模板.xlsx");
        OutputStream out = new FileOutputStream("C:\\Users\\xiaobei\\Desktop\\报备-2007.xlsx");
        ExcelWriter writer = EasyExcelFactory.getWriterWithTemp(inputStream,out,ExcelTypeEnum.XLSX,true);

        //写第三个sheet包含多个table情况
        Sheet sheet3 = new Sheet(1, 0);
        sheet3.setSheetName("第一个sheet");
        sheet3.setStartRow(2);
        Table baseInfoTable = new Table(1);
//        baseInfoTable.setHead(createTestListStringHead());
        writer.write1(createTestListObject(), sheet3, baseInfoTable);

        //写sheet2  模型上打有表头的注解
        Table moreTable = new Table(2);
        moreTable.setTableStyle(createTableStyle());
        moreTable.setClazz(WriteModel.class);
        writer.write(createTestListJavaMode(), sheet3, moreTable);

        writer.finish();
        out.close();

    }
}
