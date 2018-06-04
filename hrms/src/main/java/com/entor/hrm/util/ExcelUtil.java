package com.entor.hrm.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import javax.servlet.ServletOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * Excel工具类
 */
public class ExcelUtil<T> {

    private static final Logger LOGGER = LogManager.getLogger(ExcelUtil.class);

    /**
     * 导出excel
     *
     * @param titles
     * @param out
     * @throws Exception
     */
    public void export(Map<String, String> titles, ServletOutputStream out, List<T> list, String sheetName) throws Exception {
        // 第一步，创建一个workbook，对应一个Excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet hssfSheet = workbook.createSheet(sheetName);
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow hssfRow = hssfSheet.createRow(0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle hssfCellStyle = workbook.createCellStyle();
        //居中样式
        hssfCellStyle.setAlignment(HorizontalAlignment.CENTER);

        HSSFCell hssfCell = null;
        Object[] properties = titles.keySet().toArray();
        for (int i = 0; i < properties.length; i++) {
            hssfCell = hssfRow.createCell(i);//列索引从0开始
            hssfCell.setCellValue(titles.get(properties[i]));//列名1
            hssfCell.setCellStyle(hssfCellStyle);//列居中显示
        }

        // 第五步，写入实体数据
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                hssfRow = hssfSheet.createRow(i + 1);

                // 第六步，创建单元格，并设置值
                T t = list.get(i);
                Class clazz = t.getClass();
                for (int j = 0; j < properties.length; j++) {
                    String filedName = (String) properties[j];
                    Field property = clazz.getDeclaredField(filedName);
                    Type type = property.getType();
                    Method method = clazz.getMethod("get" + filedName.substring(0, 1).toUpperCase() + filedName.substring(1), null);
                    Object value = method.invoke(t, null);
                    if (value instanceof java.util.Date)
                        hssfRow.createCell(j).setCellValue(sdf.format(value));
                    else if (value instanceof String)
                        hssfRow.createCell(j).setCellValue((String) value);
                    else if (value instanceof Integer)
                        hssfRow.createCell(j).setCellValue((Integer) value);
                }
            }
        }

        // 第七步，将文件输出到客户端浏览器
        LOGGER.info("=============>编辑完成===============>");
        workbook.write(out);
        out.flush();
        out.close();
    }

}
