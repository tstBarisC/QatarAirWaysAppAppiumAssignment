import com.thoughtworks.gauge.Step;
import driver.BaseTest;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Assert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import driver.BaseTest;

import org.openqa.selenium.interactions.TouchScreen;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.Random;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class StepImplementation extends BaseTest {

    public String timeDeparture="";
    public String timeArrival="";
    public String price="";
    public String fromShort="";
    public String toShort="";

    public MobileElement findElement(String idOrXpath){
        MobileElement element;
        if(idOrXpath.substring(0, 2).equals("//")) {
            element = appiumDriver.findElementByXPath(idOrXpath);
        }else {
            element = appiumDriver.findElementById(idOrXpath);
        }
        return element;
    }


    @Step("Eleman görünüyor mu : <idOrXpath>")
    public void isElementVisible2(String idOrXpath) {
        MobileElement element= findElement(idOrXpath);
        if(element!=null) {
            logger.info("Sayfa yüklendi.");
            //System.out.println("sayfa geldi " + idOrXpath);
        }else {
            logger.info("Sayfa yüklenmedi.");
            //System.out.println("sayfa gelmedi " + idOrXpath);

        }

    }
    @Step("Elemana tıkla : <idOrXpath>")
    public void clickElement(String idOrXpath){

        findElement(idOrXpath).click();
        logger.info("Elemana tıklandı.");

    }

    @Step("Elemana değer gönder, <idOrXpath>, <deger>")
    public void sendKeys(String idOrXpath, String deger){
        MobileElement element=findElement(idOrXpath);
        element.clear();
        element.sendKeys(deger);
        logger.info("Elemente"+deger+" degeri gönderildi");

    }


    @Step("Wait <i> seconds.")
    public void waitForSeconds(int i) throws InterruptedException{
        Thread.sleep(1000*i);
    }

    @Step("<id> idli eleman <text> değerini içeriyor mu kontrol et")
    public  void assertByid(String id, String text){
        MobileElement element =appiumDriver.findElementById(id);
        Assert.assertTrue(element.getText()+"text değeri verilen değer birbirini tutmuyor",element.getText().equals(text));
    }


    public String getTextFromElementByIdOrXpath(String id){
        MobileElement element =findElement(id);
        String elText = element.getText();
        return elText;
    }

    @Step("Random Seç")
    public void randomSec() throws InterruptedException {
        String text= getTextFromElementByIdOrXpath("com.m.qr:id/rvmp_results_count");
        //System.out.println(text);
        text = text.replaceAll("[^0-9]", "");
        //System.out.println(text);
        int sayi=Integer.parseInt(text);
        //System.out.println(sayi);
        Random rn = new Random();
        int selected = rn.nextInt((sayi - 1) + 1) + 1;
        //System.out.println("seçilen random sayi:"+selected);
        int order=selected;
        MobileElement element= appiumDriver.findElementById("com.m.qr:id/rvmp_fragment_search_result_list");


        for (int i=0;i<order;i++){
            MobileElement testElement=null;
            try{
                testElement=findElement("//*[@resource-id='com.m.qr:id/rvmp_fragment_search_result_list'][1]//*[@resource-id='com.m.qr:id/rvmp_item_search_result_root_view' and @index='"+order+"']");
                timeDeparture    =findElement("//*[@index='"+order+"' and @resource-id='com.m.qr:id/rvmp_item_search_result_root_view']/*[@resource-id='com.m.qr:id/rvmp_departure_time']").getText();
                timeArrival      =findElement("//*[@index='"+order+"' and @resource-id='com.m.qr:id/rvmp_item_search_result_root_view']/*[@resource-id='com.m.qr:id/rvmp_arrival_time']").getText();
                price            =findElement("//*[@index='"+order+"' and @resource-id='com.m.qr:id/rvmp_item_search_result_root_view']/*[@resource-id='com.m.qr:id/rvmp_price']").getText();
                fromShort        =findElement("//*[@index='"+order+"' and @resource-id='com.m.qr:id/rvmp_item_search_result_root_view']/*[@resource-id='com.m.qr:id/rvmp_origin_iata_code']").getText();
                toShort          =findElement("//*[@index='"+order+"' and @resource-id='com.m.qr:id/rvmp_item_search_result_root_view']/*[@resource-id='com.m.qr:id/rvmp_destination_iata_code']").getText();
                //System.out.println("timeDeparture : "+timeDeparture);
                //System.out.println("timeArrival : "+timeArrival);
                //System.out.println("price : "+price);
                //System.out.println("fromShort : "+fromShort);
                //System.out.println("toShort : "+toShort);
                testElement.click();
                break;
            }
            catch (Exception e){
                System.out.println("Hata");
            }

            if(testElement!=null) {
                System.out.println(testElement.getId());
            }else {
                System.out.println("Hata : element boş");
            }

            TouchAction ta =new TouchAction(appiumDriver);
            ta.longPress(PointOption.point(750,2500)).moveTo(PointOption.point(750,1300)).release().perform();
            Thread.sleep(5000);

        }
        logger.info("Random uçuş seçildi");

    }

    @Step("karsilastir")
    public void karislastir(){
        String timeDepartureFin =findElement("com.m.qr:id/from_time").getText();
        String timeArrivalFin   =findElement("com.m.qr:id/to_time").getText();
        String priceFin         =findElement("com.m.qr:id/fare_info_grand_total_revenue").getText();
        String fromShortFin     =findElement("com.m.qr:id/from_station_code").getText();
        String toShortFin       =findElement("com.m.qr:id/to_station_code").getText();
        logger.info("Karşılaştırma sonucu:");
        logger.info("Uçuş seçim ekranı = Satın alım ekranı");

        logger.info(timeDeparture+" = "+timeDepartureFin  );
        logger.info(timeArrival  +" = "+timeArrivalFin    );
        logger.info(price        +" = "+priceFin          );
        logger.info(fromShort    +" = "+fromShortFin      );
        logger.info(toShort      +" = "+toShortFin        );
    }

    @Step("Tarih olayı")
    public void tarih(){
        String aylar[]={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"}; //Exp: Dec 2022
        int ayGunler[]={31,28,31,30,31,30,31,31,30,31,30,31};
        Calendar simdi=Calendar.getInstance();
        int otelenmisGun;
        String otelenmisAy="";
        int otelenmisYil=simdi.get(Calendar.YEAR);
        if(simdi.get(Calendar.DATE)+7>ayGunler[simdi.get(Calendar.MONTH)]){
            otelenmisGun=((simdi.get(Calendar.DATE)+7)-ayGunler[simdi.get(Calendar.MONTH)]);
            Integer test= new Integer(simdi.get(Calendar.MONTH));
            if(test.equals(11)){
                otelenmisAy=aylar[0];
                otelenmisYil=otelenmisYil+1;
            }else {
                otelenmisAy=aylar[simdi.get(Calendar.MONTH+1)];
            }
        }
        else {
            otelenmisGun=Calendar.DATE;
        }
        //System.out.println(otelenmisGun+" "+otelenmisAy+" "+otelenmisYil);
        String forSearch=otelenmisAy+" "+otelenmisYil;
        String isTrueDate = getTextFromElementByIdOrXpath("//*[@resource-id='com.m.qr:id/fragment_calendar_calendar_view']/android.widget.LinearLayout[1]/*[@resource-id='com.m.qr:id/calendar_month_header_text_view']");
        //System.out.println((isTrueDate.equals(forSearch)));
        MobileElement element= findElement("//*[@resource-id='com.m.qr:id/fragment_calendar_calendar_view']/android.widget.LinearLayout[1]//*[@text='"+otelenmisGun+"']");
        if(!(isTrueDate.equals(forSearch))){
            String isTrueDate2 = getTextFromElementByIdOrXpath("//*[@resource-id='com.m.qr:id/fragment_calendar_calendar_view']/android.widget.LinearLayout[2]/*[@resource-id='com.m.qr:id/calendar_month_header_text_view']");
            //System.out.println((isTrueDate2.equals(forSearch)));
            element= findElement("//*[@resource-id='com.m.qr:id/fragment_calendar_calendar_view']/android.widget.LinearLayout[2]//*[@text='"+otelenmisGun+"']");
        }
        element.click();
        logger.info("7 gün sonranın tarihi seçildi");

    }

}
