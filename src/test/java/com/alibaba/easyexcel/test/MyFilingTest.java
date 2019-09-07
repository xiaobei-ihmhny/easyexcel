package com.alibaba.easyexcel.test;

import com.alibaba.easyexcel.test.model.FilingBaseInfo;
import com.alibaba.easyexcel.test.model.WriteModel;
import com.alibaba.easyexcel.test.util.FileUtil;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Font;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.metadata.TableStyle;
import com.alibaba.excel.support.ExcelTypeEnum;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellCopyPolicy;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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
    public void testPOIAndEasyExcelTogether() throws Exception {
        // 1. 先用poi填充表头并生成临时excel文件
        poiSetHeader();
        // 2. 使用easyexcel读取poi填充好的表头，并填充列表数据
        easyExcelSetList();
    }

    @Test
    public void easyExcelSetList() throws Exception {
        InputStream inputStream = FileUtil.getResourcesFileInputStream("2007-temp.xlsx");
        OutputStream out = new FileOutputStream("C:\\Users\\xiaobei\\Desktop\\2007-1.xlsx");
        ExcelWriter writer = EasyExcelFactory.getWriterWithTemp(inputStream,out,ExcelTypeEnum.XLSX,true);
        //写第一个sheet, sheet1  数据全是List<String> 无模型映射关系
        Sheet sheet1 = new Sheet(1, 9);
        sheet1.setSheetName("第一个sheet");
        sheet1.setStartRow(9);
        //or 设置自适应宽度
        sheet1.setAutoWidth(Boolean.FALSE);
        writer.write1(createTestListObject(), sheet1);

        writer.finish();
        out.close();
    }

    /**
     * 使用poi设置header
     * @throws IOException
     */
    private void poiSetHeader() throws IOException {
        InputStream fis = FileUtil.getResourcesFileInputStream("工作簿1.xlsx");
        XSSFWorkbook workBookTemp = new XSSFWorkbook(fis);
        OutputStream out = new FileOutputStream("C:\\Users\\xiaobei\\Desktop\\2007.xlsx");
        XSSFSheet sheetTemp = workBookTemp.getSheetAt(0);

        // 新建excel
        XSSFWorkbook workBook = new XSSFWorkbook();
        workBook = workBookTemp;
        XSSFSheet sheet = workBook.getSheetAt(0);
        sheet.getRow(1).getCell(2).setCellValue("小贝贝");
        sheet.getRow(2).getCell(2).setCellValue("13683501521");
        sheet.getRow(3).getCell(2).setCellValue("中华人民共和国");
        sheet.getRow(1).getCell(13).setCellValue("央财平台");
        sheet.getRow(2).getCell(13).setCellValue("2019-10-11 11:12:13");
        sheet.getRow(3).getCell(13).setCellValue("小慧慧");
        sheet.getRow(4).getCell(2).setCellValue("北京市海淀区北苑家园");
        sheet.getRow(5).getCell(2).setCellValue("这是一个备注");
        try {
            workBookTemp.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void writeV2007() throws IOException {
        OutputStream out = new FileOutputStream("C:\\Users\\xiaobei\\Desktop\\2007.xlsx");
        ExcelWriter writer = EasyExcelFactory.getWriter(out);
        //写第一个sheet, sheet1  数据全是List<String> 无模型映射关系
        Sheet sheet1 = new Sheet(1, 9);
        sheet1.setSheetName("第一个sheet");

        //设置列宽 设置每列的宽度
        Map columnWidth = new HashMap();

//        columnWidth.put(0,10000);columnWidth.put(1,40000);columnWidth.put(2,10000);columnWidth.put(3,10000);
//        sheet1.setColumnWidthMap(columnWidth);
        // 获取数据
        FilingBaseInfo baseInfo = getFilingBaseInfo();

        TableStyle tableStyle = new TableStyle();
        tableStyle.setTableHeadBackGroundColor(IndexedColors.WHITE);

        Font tableHeadFont = new Font();
        tableHeadFont.setFontName("微软雅黑");
        tableHeadFont.setFontHeightInPoints((short) 11);
        tableStyle.setTableHeadFont(tableHeadFont);

        Font tableContentFont = new Font();
        tableContentFont.setFontName("宋体");
        tableContentFont.setFontHeightInPoints((short) 11);
        tableContentFont.setBold(false);
        tableStyle.setTableContentFont(tableContentFont);

        tableStyle.setTableContentBackGroundColor(IndexedColors.WHITE);

        sheet1.setTableStyle(tableStyle);
        sheet1.setHead(createFilingListStringHead(baseInfo));

//        sheet1.setHead(createTestListStringHead());
        //or 设置自适应宽度
        sheet1.setAutoWidth(Boolean.TRUE);
        writer.write1(createTestListObject(), sheet1);

        writer.finish();
        out.close();

    }

    private FilingBaseInfo getFilingBaseInfo() {
        FilingBaseInfo baseInfo = new FilingBaseInfo();
        baseInfo.setRegistrarName("小贝");
        baseInfo.setRegistrarPhone("13683501521");
        baseInfo.setPurchaseCompany("小贝集团");
        baseInfo.setDeliveryFullAddress("我的地址");
        baseInfo.setRemark("这是一个备注");
        baseInfo.setPurchasePlatformName("央财平台");
        baseInfo.setRegistrarEndTime(new Date());
        baseInfo.setConsigneeName("小惠惠");
        return baseInfo;
    }


    public static List<List<String>> createFilingListStringHead(FilingBaseInfo baseInfo) {
        //写sheet3  模型上没有注解，表头数据动态传入
        List<List<String>> head = new ArrayList<List<String>>();
        List<String> headCoulumn1 = new ArrayList<String>();
        List<String> headCoulumn2 = new ArrayList<String>();
        List<String> headCoulumn3 = new ArrayList<String>();
        List<String> headCoulumn4 = new ArrayList<String>();
        List<String> headCoulumn5 = new ArrayList<String>();
        List<String> headCoulumn6 = new ArrayList<String>();
        List<String> headCoulumn7 = new ArrayList<String>();
        List<String> headCoulumn8 = new ArrayList<String>();
        List<String> headCoulumn9 = new ArrayList<String>();
        List<String> headCoulumn10 = new ArrayList<String>();
        List<String> headCoulumn11 = new ArrayList<String>();
        List<String> headCoulumn12 = new ArrayList<String>();
        List<String> headCoulumn13 = new ArrayList<String>();
        List<String> headCoulumn14 = new ArrayList<String>();
        List<String> headCoulumn15 = new ArrayList<String>();
        List<String> headCoulumn16 = new ArrayList<String>();
        List<String> headCoulumn17 = new ArrayList<String>();
        List<String> headCoulumn18 = new ArrayList<String>();
        List<String> headCoulumn19 = new ArrayList<String>();
        List<String> headCoulumn20 = new ArrayList<String>();

        headCoulumn1.add("报备基础信息");
        headCoulumn1.add("报备人姓名");
        headCoulumn1.add("报备人电话");
        headCoulumn1.add("采购单位");
        headCoulumn1.add("收货地址");
        headCoulumn1.add("备注");
        headCoulumn1.add("报备商品信息");
        headCoulumn1.add("注意事项：标*列为必填项，其他列按照对应点单平台要求选填");
        headCoulumn1.add("*序号");

        headCoulumn2.add("报备基础信息");
        headCoulumn2.add("报备人姓名");
        headCoulumn2.add("报备人电话");
        headCoulumn2.add("采购单位");
        headCoulumn2.add("收货地址");
        headCoulumn2.add("备注");
        headCoulumn2.add("报备商品信息");
        headCoulumn2.add("注意事项：标*列为必填项，其他列按照对应点单平台要求选填");
        headCoulumn2.add("*类目");

        headCoulumn3.add("报备基础信息");
        headCoulumn3.add(baseInfo.getRegistrarName());
        headCoulumn3.add(baseInfo.getRegistrarPhone());
        headCoulumn3.add(baseInfo.getPurchaseCompany());
        headCoulumn3.add(baseInfo.getDeliveryFullAddress());
        headCoulumn3.add(baseInfo.getRemark());
        headCoulumn3.add("报备商品信息");
        headCoulumn3.add("注意事项：标*列为必填项，其他列按照对应点单平台要求选填");
        headCoulumn3.add("*品牌");

        headCoulumn4.add("报备基础信息");
        headCoulumn4.add(baseInfo.getRegistrarName());
        headCoulumn4.add(baseInfo.getRegistrarPhone());
        headCoulumn4.add(baseInfo.getPurchaseCompany());
        headCoulumn4.add(baseInfo.getDeliveryFullAddress());
        headCoulumn4.add(baseInfo.getRemark());
        headCoulumn4.add("报备商品信息");
        headCoulumn4.add("注意事项：标*列为必填项，其他列按照对应点单平台要求选填");
        headCoulumn4.add("*商品名称");

        headCoulumn5.add("报备基础信息");
        headCoulumn5.add(baseInfo.getRegistrarName());
        headCoulumn5.add(baseInfo.getRegistrarPhone());
        headCoulumn5.add(baseInfo.getPurchaseCompany());
        headCoulumn5.add(baseInfo.getDeliveryFullAddress());
        headCoulumn5.add(baseInfo.getRemark());
        headCoulumn5.add("报备商品信息");
        headCoulumn5.add("注意事项：标*列为必填项，其他列按照对应点单平台要求选填");
        headCoulumn5.add("*型号");

        headCoulumn6.add("报备基础信息");
        headCoulumn6.add(baseInfo.getRegistrarName());
        headCoulumn6.add(baseInfo.getRegistrarPhone());
        headCoulumn6.add(baseInfo.getPurchaseCompany());
        headCoulumn6.add(baseInfo.getDeliveryFullAddress());
        headCoulumn6.add(baseInfo.getRemark());
        headCoulumn6.add("报备商品信息");
        headCoulumn6.add("注意事项：标*列为必填项，其他列按照对应点单平台要求选填");
        headCoulumn6.add("*数量");

        headCoulumn7.add("报备基础信息");
        headCoulumn7.add(baseInfo.getRegistrarName());
        headCoulumn7.add(baseInfo.getRegistrarPhone());
        headCoulumn7.add(baseInfo.getPurchaseCompany());
        headCoulumn7.add(baseInfo.getDeliveryFullAddress());
        headCoulumn7.add(baseInfo.getRemark());
        headCoulumn7.add("报备商品信息");
        headCoulumn7.add("注意事项：标*列为必填项，其他列按照对应点单平台要求选填");
        headCoulumn7.add("*单位");

        headCoulumn8.add("报备基础信息");
        headCoulumn8.add(baseInfo.getRegistrarName());
        headCoulumn8.add(baseInfo.getRegistrarPhone());
        headCoulumn8.add(baseInfo.getPurchaseCompany());
        headCoulumn8.add(baseInfo.getDeliveryFullAddress());
        headCoulumn8.add(baseInfo.getRemark());
        headCoulumn8.add("报备商品信息");
        headCoulumn8.add("注意事项：标*列为必填项，其他列按照对应点单平台要求选填");
        headCoulumn8.add("重量 （kg）");

        headCoulumn9.add("报备基础信息");
        headCoulumn9.add(baseInfo.getRegistrarName());
        headCoulumn9.add(baseInfo.getRegistrarPhone());
        headCoulumn9.add(baseInfo.getPurchaseCompany());
        headCoulumn9.add(baseInfo.getDeliveryFullAddress());
        headCoulumn9.add(baseInfo.getRemark());
        headCoulumn9.add("报备商品信息");
        headCoulumn9.add("注意事项：标*列为必填项，其他列按照对应点单平台要求选填");
        headCoulumn9.add("*成交价");

        headCoulumn10.add("报备基础信息");
        headCoulumn10.add(baseInfo.getRegistrarName());
        headCoulumn10.add(baseInfo.getRegistrarPhone());
        headCoulumn10.add(baseInfo.getPurchaseCompany());
        headCoulumn10.add(baseInfo.getDeliveryFullAddress());
        headCoulumn10.add(baseInfo.getRemark());
        headCoulumn10.add("报备商品信息");
        headCoulumn10.add("注意事项：标*列为必填项，其他列按照对应点单平台要求选填");
        headCoulumn10.add("*挂网价  （元）销售价");

        headCoulumn11.add("报备基础信息");
        headCoulumn11.add(baseInfo.getRegistrarName());
        headCoulumn11.add(baseInfo.getRegistrarPhone());
        headCoulumn11.add(baseInfo.getPurchaseCompany());
        headCoulumn11.add(baseInfo.getDeliveryFullAddress());
        headCoulumn11.add(baseInfo.getRemark());
        headCoulumn11.add("报备商品信息");
        headCoulumn11.add("注意事项：标*列为必填项，其他列按照对应点单平台要求选填");
        headCoulumn11.add("*参数规格");

        headCoulumn12.add("报备基础信息");
        headCoulumn12.add(baseInfo.getRegistrarName());
        headCoulumn12.add(baseInfo.getRegistrarPhone());
        headCoulumn12.add(baseInfo.getPurchaseCompany());
        headCoulumn12.add(baseInfo.getDeliveryFullAddress());
        headCoulumn12.add(baseInfo.getRemark());
        headCoulumn12.add("报备商品信息");
        headCoulumn12.add("注意事项：标*列为必填项，其他列按照对应点单平台要求选填");
        headCoulumn12.add("包装清单");

        headCoulumn13.add("报备基础信息");
        headCoulumn13.add("点单平台");
        headCoulumn13.add("项目报备时间");
        headCoulumn13.add("收货人");
        headCoulumn13.add(baseInfo.getDeliveryFullAddress());
        headCoulumn13.add(baseInfo.getRemark());
        headCoulumn13.add("报备商品信息");
        headCoulumn13.add("注意事项：标*列为必填项，其他列按照对应点单平台要求选填");
        headCoulumn13.add("节能产品政府采购清单期数及所在页码、行数和证书号");

        headCoulumn14.add("报备基础信息");
        headCoulumn14.add(baseInfo.getPurchaseCompany());
        headCoulumn14.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(baseInfo.getRegistrarEndTime()));
        headCoulumn14.add(baseInfo.getConsigneeName());
        headCoulumn14.add(baseInfo.getDeliveryFullAddress());
        headCoulumn14.add(baseInfo.getRemark());
        headCoulumn14.add("报备商品信息");
        headCoulumn14.add("注意事项：标*列为必填项，其他列按照对应点单平台要求选填");
        headCoulumn14.add("环保产品政府采购清单期数及所在页码、行数和证书号");

        headCoulumn15.add("报备基础信息");
        headCoulumn15.add(baseInfo.getPurchaseCompany());
        headCoulumn15.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(baseInfo.getRegistrarEndTime()));
        headCoulumn15.add(baseInfo.getConsigneeName());
        headCoulumn15.add(baseInfo.getDeliveryFullAddress());
        headCoulumn15.add(baseInfo.getRemark());
        headCoulumn15.add("报备商品信息");
        headCoulumn15.add("注意事项：标*列为必填项，其他列按照对应点单平台要求选填");
        headCoulumn15.add("产地");

        headCoulumn16.add("报备基础信息");
        headCoulumn16.add(baseInfo.getPurchaseCompany());
        headCoulumn16.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(baseInfo.getRegistrarEndTime()));
        headCoulumn16.add(baseInfo.getConsigneeName());
        headCoulumn16.add(baseInfo.getDeliveryFullAddress());
        headCoulumn16.add(baseInfo.getRemark());
        headCoulumn16.add("报备商品信息");
        headCoulumn16.add("注意事项：标*列为必填项，其他列按照对应点单平台要求选填");
        headCoulumn16.add("生产商\n" +
                "（福建省、政采云平台必填）");

        headCoulumn17.add("报备基础信息");
        headCoulumn17.add(baseInfo.getPurchaseCompany());
        headCoulumn17.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(baseInfo.getRegistrarEndTime()));
        headCoulumn17.add(baseInfo.getConsigneeName());
        headCoulumn17.add(baseInfo.getDeliveryFullAddress());
        headCoulumn17.add(baseInfo.getRemark());
        headCoulumn17.add("报备商品信息");
        headCoulumn17.add("链接、图片至少提供一项填写");
        headCoulumn17.add("参考链接1\n" +
                "（京东/妥了网）");

        headCoulumn18.add("报备基础信息");
        headCoulumn18.add(baseInfo.getPurchaseCompany());
        headCoulumn18.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(baseInfo.getRegistrarEndTime()));
        headCoulumn18.add(baseInfo.getConsigneeName());
        headCoulumn18.add(baseInfo.getDeliveryFullAddress());
        headCoulumn18.add(baseInfo.getRemark());
        headCoulumn18.add("报备商品信息");
        headCoulumn18.add("链接、图片至少提供一项填写");
        headCoulumn18.add("参考链接2\n" +
                "（产品官网）");

        headCoulumn19.add("报备基础信息");
        headCoulumn19.add(baseInfo.getPurchaseCompany());
        headCoulumn19.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(baseInfo.getRegistrarEndTime()));
        headCoulumn19.add(baseInfo.getConsigneeName());
        headCoulumn19.add(baseInfo.getDeliveryFullAddress());
        headCoulumn19.add(baseInfo.getRemark());
        headCoulumn19.add("报备商品信息");
        headCoulumn19.add("链接、图片至少提供一项填写");
        headCoulumn19.add("参考链接3\n" +
                "（天猫/苏宁/国美等）");

        headCoulumn20.add("报备基础信息");
        headCoulumn20.add(baseInfo.getPurchaseCompany());
        headCoulumn20.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(baseInfo.getRegistrarEndTime()));
        headCoulumn20.add(baseInfo.getConsigneeName());
        headCoulumn20.add(baseInfo.getDeliveryFullAddress());
        headCoulumn20.add(baseInfo.getRemark());
        headCoulumn20.add("报备商品信息");
        headCoulumn20.add("链接、图片至少提供一项填写");
        headCoulumn20.add("产品参考图片（3-5张）\n" +
                "将图片缩小完全放置对应单元格内");

        head.add(headCoulumn1);
        head.add(headCoulumn2);
        head.add(headCoulumn3);
        head.add(headCoulumn4);
        head.add(headCoulumn5);
        head.add(headCoulumn6);
        head.add(headCoulumn7);
        head.add(headCoulumn8);
        head.add(headCoulumn9);
        head.add(headCoulumn10);
        head.add(headCoulumn11);
        head.add(headCoulumn12);
        head.add(headCoulumn13);
        head.add(headCoulumn14);
        head.add(headCoulumn15);
        head.add(headCoulumn16);
        head.add(headCoulumn17);
        head.add(headCoulumn18);
        head.add(headCoulumn19);
        head.add(headCoulumn20);
        return head;
    }

    @Test
    public void writeV2007WithTemplate() throws IOException {
        InputStream inputStream = FileUtil.getResourcesFileInputStream("导出所选报备单模板.xlsx");
        OutputStream out = new FileOutputStream("C:\\Users\\xiaobei\\Desktop\\报备单列表20190903.xlsx");
        ExcelWriter writer = EasyExcelFactory.getWriterWithTemp(inputStream, out, ExcelTypeEnum.XLSX, true);
        //写第一个sheet, sheet1  数据全是List<String> 无模型映射关系
        Sheet sheet1 = new Sheet(1, 3);
//        sheet1.setSheetName("报备单列表");
        sheet1.setStartRow(10);

        //or 设置自适应宽度
        //sheet1.setAutoWidth(Boolean.TRUE);
        writer.write1(createTestListObject(), sheet1);
        writer.finish();
        out.close();

    }

    @Test
    public void writeV2007WithTemplateMoreTable() throws IOException {
        InputStream inputStream = FileUtil.getResourcesFileInputStream("报备订单信息导出模板.xlsx");
        OutputStream out = new FileOutputStream("C:\\Users\\Legend\\Desktop\\报备-2007.xlsx");
        ExcelWriter writer = EasyExcelFactory.getWriterWithTemp(inputStream, out, ExcelTypeEnum.XLSX, true);

        XSSFWorkbook workBook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet0 = workBook.getSheetAt(0);
        sheet0.getRow(1).getCell(2).setCellValue("小贝贝");
        sheet0.getRow(2).getCell(2).setCellValue("13683501521");
        sheet0.getRow(3).getCell(2).setCellValue("中华人民共和国");
        sheet0.getRow(1).getCell(13).setCellValue("央财平台");
        sheet0.getRow(2).getCell(13).setCellValue("2019-10-11 11:12:13");
        sheet0.getRow(3).getCell(13).setCellValue("小慧慧");
        sheet0.getRow(4).getCell(2).setCellValue("北京市海淀区北苑家园");
        sheet0.getRow(5).getCell(2).setCellValue("这是一个备注");

        //写第三个sheet包含多个table情况
        Sheet sheet = new Sheet(1, 0);
        TableStyle tableStyle = sheet.getTableStyle();
        sheet.setSheetName("第一个sheet");
        sheet.setStartRow(2);
        Table baseInfoTable = new Table(1);
//        baseInfoTable.setHead(createTestListStringHead());
        writer.write1(createTestListObject(), sheet, baseInfoTable);

        //写sheet2  模型上打有表头的注解
        Table moreTable = new Table(2);
        moreTable.setTableStyle(createTableStyle());
        moreTable.setClazz(WriteModel.class);
        writer.write(createTestListJavaMode(), sheet, moreTable);

        writer.finish();
        out.close();

    }

    @Test
    public void testPOIExport() throws Exception {
        InputStream fis = FileUtil.getResourcesFileInputStream("报备订单信息导出模板.xlsx");
        XSSFWorkbook workBookTemp = new XSSFWorkbook(fis);
        OutputStream out = new FileOutputStream("C:\\Users\\xiaobei\\Desktop\\2007.xlsx");
        XSSFSheet sheetTemp = workBookTemp.getSheetAt(0);

        // 新建excel
        XSSFWorkbook workBook = new XSSFWorkbook();
        workBook = workBookTemp;
        XSSFSheet sheet = workBook.getSheetAt(0);
        sheet.getRow(1).getCell(2).setCellValue("小贝贝");
        sheet.getRow(2).getCell(2).setCellValue("13683501521");
        sheet.getRow(3).getCell(2).setCellValue("中华人民共和国");
        sheet.getRow(1).getCell(13).setCellValue("央财平台");
        sheet.getRow(2).getCell(13).setCellValue("2019-10-11 11:12:13");
        sheet.getRow(3).getCell(13).setCellValue("小慧慧");
        sheet.getRow(4).getCell(2).setCellValue("北京市海淀区北苑家园");
        sheet.getRow(5).getCell(2).setCellValue("这是一个备注");
        List<List<String>> testListObject = createTestListString();
        for (int row = 0; row < testListObject.size(); row++) {
            List<String> objects = testListObject.get(row);
            XSSFRow row1 = sheet.createRow(row + 9);
            for (int i = 0; i < objects.size(); i++) {
                CellCopyPolicy cellCopyPolicy = new CellCopyPolicy();
                row1.createCell(i).setCellValue(objects.get(i));
            }
        }

        //第一行3,4,5合并
//        CellRangeAddress region3 = new CellRangeAddress(1, 1, (short) 2, (short) 11);
//        sheet.addMergedRegion(region3);
//        sheet.createRow(1).createCell(2).setCellValue("小贝贝");
        try {
            workBookTemp.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPOI() {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet();
        //第一行前三个合并
        CellRangeAddress region1 = new CellRangeAddress(0, 0, (short) 0, (short) 2);
        sheet.addMergedRegion(region1);
        sheet.createRow(0).createCell(0).setCellValue("第一行前三个");
        //第二行前三个合并
        CellRangeAddress region2 = new CellRangeAddress(1, 1, (short) 0, (short) 2);
        sheet.addMergedRegion(region2);
        sheet.createRow(1).createCell(0).setCellValue("第二行前三个");
        //第一行3,4,5合并
        CellRangeAddress region3 = new CellRangeAddress(0, 0, (short) 3, (short) 5);
        sheet.addMergedRegion(region3);
        sheet.createRow(0).createCell(3).setCellValue("第一行3,4,5");
        FileOutputStream fileOut;
        try {
            fileOut = new FileOutputStream("C:\\Users\\Legend\\Desktop\\报备-2007.xls");
            wb.write(fileOut);
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
