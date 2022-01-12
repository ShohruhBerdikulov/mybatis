package test.pkg;

import org.oorsprong.websamples.ArrayOftContinent;
import org.oorsprong.websamples.TContinent;
import org.oorsprong.websamples.TCountryInfo;
import org.oorsprong.websamples.TCurrency;
import org.oorsprong.websamples_countryinfo.CountryInfoService;
import org.oorsprong.websamples_countryinfo.CountryInfoServiceSoapType;

import javax.xml.namespace.QName;
import java.net.MalformedURLException;
import java.net.URL;

public class SoapClientTest {
    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso?WSDL");
        CountryInfoService service = new CountryInfoService(url);
//        CountryInfoServiceSoapType countryInfoServiceSoap = service.getCountryInfoServiceSoap();
//        ArrayOftContinent arrayOftContinent = countryInfoServiceSoap.listOfContinentsByCode();
//        System.out.println("Size: " + arrayOftContinent.getTContinent().size());
//
//        final CountryInfoServiceSoapType serviceSoap12 = service.getCountryInfoServiceSoap12();
//        for (TContinent item :serviceSoap12.listOfContinentsByCode().getTContinent()) {
//            System.out.printf(item.getSCode() + "    " + item.getSName() + "\n");
//        }
//        for (TCountryInfo item : serviceSoap12.fullCountryInfoAllCountries().getTCountryInfo()) {
//            System.out.println(item.toString());
//        }
    }
}
