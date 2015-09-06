package PartialScreenshot;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.junit.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.Test;

public class ElementScreenshot {
	private WebDriver driver;
    private java.lang.String baseUrl;
    private WebElement element;
    
    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        baseUrl = "https://uk.wikipedia.org/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    
	@Test
	public void takeElementScreen() throws Exception {
		driver.get(baseUrl);
		driver.findElement(By.xpath("//*[@id='featurded_article']/ul/descendant::a[2]")).click();
		element = driver.findElement(By.cssSelector(".image>img"));
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		BufferedImage  fullImage = ImageIO.read(screenshot);
		Point point = element.getLocation();
		int elementWidth = element.getSize().getWidth();
		int elementHeight = element.getSize().getHeight();
		BufferedImage elementScreenshot= fullImage.getSubimage(point.getX(), point.getY(), elementWidth,
			    elementHeight);
			ImageIO.write(elementScreenshot, "png", screenshot);
			FileUtils.copyFile(screenshot, new File("C:\\Selenium\\Screenshots\\ElementScreen.png"));
		}
	
	@After
    public void tearDown() throws Exception {
        driver.quit();
        }
    }