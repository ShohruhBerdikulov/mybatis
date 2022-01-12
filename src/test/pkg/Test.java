package test.pkg;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import test.pkg.bean.OppoValidation;
import test.pkg.bean.PayTypes;
import test.pkg.db.MyBatisHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Test {
    private final static String URLDOC = "" ;// there your google sheets url
//    public static void main(String[] args) {
//        System.out.println("Hello!");
//
//        AppUser user = new AppUser();
//        user.setForename("John");
//        user.setLastname("Doe");
//
//        AppUser user2 = new MyBatisHelper().selectOne("selectUserById", user);
//
//        if (user2 == null) {
//            user2 = new AppUser();
////            user2.setId(1L);
//            user2.setLastname("Doe");
//            user2.setForename("John");
//            user2.setEmail("john@doe.com");
//            user2.setPhone("+9989900998959");
//
//            new MyBatisHelper().insert("insertAppUser", user2);
//            System.out.println(user2.getId());
//            AppUser user3 = new AppUser();
////            user3.setId(2L);
//            user3.setLastname("Doe");
//            user3.setForename("John");
//            user3.setEmail("john@doe.com");
//            user3.setPhone("+9989900998959");
//
//            new MyBatisHelper().insert("insertAppUser", user3);
//
//            List<AppUser> list = new MyBatisHelper().selectList("findAllByIds", Arrays.asList(9));
//            System.out.println(list);
//        }
//    }

    public static void main(String[] args) throws IOException {
        RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT)
                .build();

        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.STANDARD_STRICT)
                .build();

        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
        HttpGet httpGet = new HttpGet(URLDOC);
        httpGet.setConfig(localConfig);
        HttpEntity entity = httpClient.execute(httpGet).getEntity();
        InputStream is = entity.getContent();
        XSSFWorkbook workbook = new XSSFWorkbook(is);
        XSSFSheet sheetAt = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheetAt.iterator();
        rowIterator.next();
        final List<OppoValidation> oppoList = new ArrayList<>();
        while (rowIterator.hasNext()) {
            Row next = rowIterator.next();
            OppoValidation oppo = new OppoValidation();
            String oppoid = null;
            try {
                oppoid = String.valueOf(next.getCell(0).getNumericCellValue());
            } catch (IllegalStateException e) {
                oppoid = next.getCell(0).getStringCellValue();
            } catch (NullPointerException nullPointerException) {
                break;
            } finally {
                oppo.setOppoId(oppoid);
            }
            oppo.setName(next.getCell(1).getStringCellValue());
            oppo.setMinimum((long) next.getCell(2).getNumericCellValue());
            oppo.setMaximum((long) next.getCell(3).getNumericCellValue());
            oppo.setPayTypes(
                    next.getCell(4).getStringCellValue().equals("Merchant") ? PayTypes.MERCHANT : PayTypes.SALES_REP);

            oppo.setStatus(
                    next.getCell(5).getStringCellValue().equals("Enabled")
            );
//            oppo.setPhoneNum(String.valueOf((long) next.getCell(6).getNumericCellValue()));

            next.getCell(6).setCellType(CellType.STRING);
            oppo.setPhoneNum(next.getCell(6).getStringCellValue());

            oppo.setHasSms(
                    next.getCell(7).getStringCellValue().equals("yes")
            );
            oppoList.add(oppo);
        }
        System.out.println(oppoList.size());
        new MyBatisHelper().insert("insertOppoList", oppoList);
    }
}

