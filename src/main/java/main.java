import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import java.time.Duration;


public class main {

    protected static String baseUrl = "http://ilan.gov.tr/kategori-tum-ilanlar";
    public static WebDriver driver;
    public static FluentWait fuluentWait;

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "web_driver/chromedriver");
        driver = new ChromeDriver();
        fuluentWait = new FluentWait(driver);
        fuluentWait.withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(500));
        driver.get(baseUrl);
        driver.manage().window().fullscreen();
        goToIhalePage();
        startTheProcess();


    }
    public static void goToIhalePage(){
        driver.findElement(By.xpath("//*[@onclick=\"handleOutboundLinkClicks('İHALE','Ust Menu','Arama Sonuc');\"]")).click();
        System.out.println("İhale Sayfasına Girildi");
        System.out.println("İhale Sayfasına Girildi");



    }
    public static void startTheProcess() throws InterruptedException {
        int numberOfLinksOnThePage=20;
        int numberOfPages=20;

        for(int page=1;page<=numberOfPages;page++) {
            for (int i = 1; i <= numberOfLinksOnThePage; i++) {
                Thread.sleep(2000);

                WebElement element= (WebElement) fuluentWait.until(ExpectedConditions.elementToBeClickable
                        (By.xpath("(//*[@class=\"item column col-12\"])[" + i + "]")));
                element.click();
                Thread.sleep(2000);
                StoreHelper.writeToTxt();
                driver.navigate().back();
                System.out.println(page+". sayfada "+i+". İhale verileri alındı");
            }

            WebElement nextButton= (WebElement) fuluentWait.until((ExpectedConditions.elementToBeClickable
                    (By.xpath("//*[@class=\"next\"]"))));
            nextButton.click();
            System.out.println(page+". sayfaya geçildi");

        }
    }
}