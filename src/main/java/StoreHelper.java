import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.FileWriter;
import java.io.IOException;
public class StoreHelper {
        public static void writeToTxt() {
            try {
                FileWriter writer = new FileWriter("Store.txt", true);
                writer.write("İhale No                  :"+readOnWeb("ihaleNo"));
                writer.write("\r\n");
                writer.write("Niteligi, Türü ve Miktarı :"+readOnWeb("niteligiTuruMiktari"));
                writer.write("\r\n");
                writer.write("İşin Yapılacağı Yer       :"+readOnWeb("Yer"));
                writer.write("\r\n");
                writer.write("İhale Türü                :"+readOnWeb("ihaleTuru"));
                writer.write("\r\n");
                writer.write("------------------------------------------------------------");
                writer.write("\r\n");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    public static String readOnWeb(String thing){
        WebDriverWait wait = new WebDriverWait(main.driver, 15);
        String xpath = null;
        String text= null;
        if ("ihaleNo".equals(thing)) {
            xpath="//*[@class=\"crazy-box\"]//*[contains(text(),'Kayıt No')]/../*[@class=\"td\"]";
        } else if ("niteligiTuruMiktari".equals(thing)) {
            xpath="//*[@class=\"crazy-box\"]//*[contains(text(),'Miktarı')]/../*[@class=\"td\"]";
        } else if ("Yer".equals(thing)) {
            xpath="//*[@class=\"crazy-box\"]//*[contains(text(),'Yer')]/../*[@class=\"td\"]";
        } else if ("ihaleTuru".equals(thing)) {
            xpath="//*[@class=\"crazy-box\"]//*[contains(text(),'İhale Türü')]/../*[@class=\"td\"]";
        } else {
            System.out.println("Hatalı istek");
        }
        if(doesElementExist(xpath,4)){
            WebElement element = (WebElement) main.fuluentWait.until(ExpectedConditions.visibilityOf
                    (main.driver.findElement(By.xpath(xpath))));
            text=element.getText().replace("  ","");
            return text;
        }
        return "----";
    }

    public static boolean doesElementExist(String xpath, int time) {
        try {
            WebDriverWait wait = new WebDriverWait(main.driver, time);
            wait.until(ExpectedConditions.visibilityOf(main.driver.findElement(By.xpath(xpath))));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
