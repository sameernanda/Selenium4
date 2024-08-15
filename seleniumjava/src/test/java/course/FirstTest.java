package course;

import static org.assertj.core.api.Assumptions.assumeThat;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;


class FirstTest {
    
  WebDriver driver;
  PageObjects po;

  @BeforeEach
  void setupClass() {
    String browser = "chrome";
    switch (browser) {
      case "firefox":
        WebDriverManager.firefoxdriver().setup();
        //current Firefox version - 127.0.6533.72

        //To use specific chrome version
        //WebDriverManager.firefoxdriver().driverVersion("126.0.6478.182").setup();
        driver = new FirefoxDriver();
        break;
      case "safari":
        WebDriverManager.safaridriver().setup();
        //current Safari version - 127.0.6533.72

        //To use specific chrome version
        //WebDriverManager.firefoxdriver().driverVersion("126.0.6478.182").setup();

        //FOR MAC SYSTEM
        Optional<Path> browserPath = WebDriverManager.safaridriver().getBrowserPath();
        assumeThat(browserPath).isPresent();
        driver = new SafariDriver();
        break;

      default:
        WebDriverManager.chromedriver().setup();
        //current chrome version - 127.0.6533.72

        //To use specific chrome version
        //WebDriverManager.chromedriver().driverVersion("126.0.6478.182").setup();
        ChromeOptions chromeoptions = new ChromeOptions();
        chromeoptions.addArguments("--remote-allow-origins=*");
        chromeoptions.addArguments("--disable-search-engine-choice-screen");
        driver = new ChromeDriver(chromeoptions);
        break;
    }
    driver.get("https://the-internet.herokuapp.com/");
    driver.manage().window().maximize();
    po = new PageObjects(driver);
  }

  @AfterEach
  void teardown() {
      driver.quit();
  }

  @Test
  void test() throws InterruptedException, IOException {
      //Window management
      System.out.println("Browser width : "+driver.manage().window().getSize().getWidth());
      System.out.println("Browser height : "+driver.manage().window().getSize().getHeight());
      driver.manage().window().setSize(new Dimension(1000, 615));
      System.out.println("Browser width : "+driver.manage().window().getSize().getWidth());
      System.out.println("Browser height : "+driver.manage().window().getSize().getHeight());


      driver.get("https://google.com");
      String parentWindow = driver.getWindowHandle();
      driver.navigate().to("https://the-internet.herokuapp.com/");
      driver.manage().window().maximize();

      //Use of Window Type
      driver.switchTo().newWindow(WindowType.TAB);
      driver.switchTo().newWindow(WindowType.WINDOW);
      driver.switchTo().window(parentWindow);

      // Takes screenshot
      File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
      FileUtils.copyFile(src,new File("./image.png"));


      // Takes particular Element Screenshot
      WebElement ele = driver.findElement(By.cssSelector(".heading"));
      File srcfile1 = ele.getScreenshotAs(OutputType.FILE);
      FileUtils.copyFile(srcfile1,new File("./image1.png"));

      //javascript Executer
      JavascriptExecutor js = (JavascriptExecutor)driver;
      js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
      //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);

      //Verify Title and current URL
    assertThat(driver.getTitle()).isEqualTo("The Internet");
    assertThat(driver.getCurrentUrl()).isEqualTo("https://the-internet.herokuapp.com/");
    }

  @Test
  void test_Authorization() throws InterruptedException {
      String username = "admin";
      String password = "admin";
      String url = "https://"+username+":"+password+"@"+"the-internet.herokuapp.com/basic_auth";
      System.out.println(url);
      driver.get(url);
      driver.manage().window().maximize();
      String text = po.get_Basic_Auth_Post_Success_Login_Text();
      assertThat(text).isEqualTo("Congratulations! You must have the proper credentials.");
  }

  @Test
  void test_Broken_Image() throws Exception {
    List<String> URLs = new ArrayList<>();
    po.click_On_Broken_Image_Link();
    URLs.add(po.get_URL_of_First_Image());
    URLs.add(po.get_URL_of_Second_Image());
    for(String u : URLs) {
      URL url = new URL(u);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      assertThat(conn.getResponseCode()).isNotEqualTo(200);
    }
    String URI = po.get_URL_of_Third_Image();
    URL url = new URL(URI);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    assertThat(conn.getResponseCode()).isEqualTo(200);
  }

  @Test
  void test_Challenging_DOM() throws Exception {
    po.click_On_Challenging_DOM_Link();

    //Check clicking on Blue link changes the text
    String prev_blue_link_text = po.get_text_Of_Blue_Link();
    po.click_On_Blue_Link();po.click_On_Blue_Link();
    String curr_blue_link_text = po.get_text_Of_Blue_Link();
    assertThat(prev_blue_link_text).isNotEqualTo(curr_blue_link_text);

    //Check clicking on Red link changes the text
    String prev_red_link_text = po.get_text_Of_Red_Link();
    po.click_On_Red_Link();po.click_On_Red_Link();
    String curr_red_link_text = po.get_text_Of_Red_Link();
    assertThat(prev_blue_link_text).isNotEqualTo(curr_red_link_text);

    //Check clicking on Green link changes the text
    String prev_green_link_text = po.get_text_Of_Green_Link();
    po.click_On_Green_Link();po.click_On_Green_Link();
    String curr_green_link_text = po.get_text_Of_Green_Link();
    assertThat(prev_blue_link_text).isNotEqualTo(curr_green_link_text);

    //Check on page refresh the Answer number changes
    String prev_answer = po.get_text_Of_Answer();
    driver.navigate().refresh();
    String curr_answer = po.get_text_Of_Answer();
    assertThat(prev_answer).isNotEqualTo(curr_answer);

    //Edit second Line
    po.click_On_second_row_edit_Link();
    assertThat(driver.getCurrentUrl()).isEqualTo("https://the-internet.herokuapp.com/challenging_dom#edit");

    //Delete 7th Line
    po.click_On_seventh_row_delete_Link();
    assertThat(driver.getCurrentUrl()).isEqualTo("https://the-internet.herokuapp.com/challenging_dom#delete");

  }

  @Test
  void test_TextBox_Select(){
    po.click_On_CheckBoxes_Link();
    assertThat(po.check_First_CheckBox_isSelected()).isFalse();
    assertThat(po.check_Second_CheckBox_isSelected()).isTrue();

    po.click_First_CheckBox();po.click_Second_CheckBox();
    assertThat(po.check_First_CheckBox_isSelected()).isTrue();
    assertThat(po.check_Second_CheckBox_isSelected()).isFalse();
  }

  @Test
  void test_Context_Click() {
    po.click_On_Context_Menu_Link();
    boolean isAlertPresent = po.right_Click_Box();
    assertThat(isAlertPresent).isTrue();
    assertThat(driver.switchTo().alert().getText()).isEqualTo("You selected a context menu");
    driver.switchTo().alert().accept();
  }

  @Test
  void test_Disappearing_Elements() {
    po.click_On_Disappearing_Link();
    boolean gallery_Displayed = po.verify_gallery_present_on_page_refresh();
    assertThat(gallery_Displayed).isTrue();
  }

  @Test
  void test_DragDrop(){
    po.click_On_Dragdrop_Link();
    assertThat(po.get_Text_at_columnA()).isEqualTo("A");
    assertThat(po.get_Text_at_columnB()).isEqualTo("B");
    po.move_columnA_to_B();
    assertThat(po.get_Text_at_columnA()).isEqualTo("B");
    assertThat(po.get_Text_at_columnB()).isEqualTo("A");
  }

  @Test
  void test_Dropdown() {
    po.click_On_Dropdown_Link();
    String selectedOption = po.select_value_by_value("Option 2");
    assertThat(selectedOption).isEqualTo("Option 2");
  }

  @Test
  void test_Dynamic_Content(){
    po.click_On_Dynamic_Content_Link();
    String row_1_Image_URL_Value_Prev = po.get_Dynamic_Attribute(po.dynamic_Content_Row_1_Image,"src");
    String row_2_Image_URL_Value_Prev = po.get_Dynamic_Attribute(po.dynamic_Content_Row_2_Image,"src");
    String row_3_Image_URL_Value_Prev = po.get_Dynamic_Attribute(po.dynamic_Content_Row_3_Image,"src");
    String row_1_Image_Text_Value_Prev = po.get_Dynamic_Text(po.dynamic_Content_Row_1_Text);
    String row_2_Image_Text_Value_Prev = po.get_Dynamic_Text(po.dynamic_Content_Row_2_Text);
    String row_3_Image_Text_Value_Prev = po.get_Dynamic_Text(po.dynamic_Content_Row_3_Text);
    driver.navigate().refresh();
    String row_1_Image_URL_Value = po.get_Dynamic_Attribute(po.dynamic_Content_Row_1_Image,"src");
    String row_2_Image_URL_Value = po.get_Dynamic_Attribute(po.dynamic_Content_Row_2_Image,"src");
    String row_3_Image_URL_Value = po.get_Dynamic_Attribute(po.dynamic_Content_Row_3_Image,"src");
    String row_1_Image_Text_Value = po.get_Dynamic_Text(po.dynamic_Content_Row_1_Text);
    String row_2_Image_Text_Value = po.get_Dynamic_Text(po.dynamic_Content_Row_2_Text);
    String row_3_Image_Text_Value = po.get_Dynamic_Text(po.dynamic_Content_Row_3_Text);
    assertAll("Contents should not be equal",
            () -> assertNotEquals(row_1_Image_URL_Value_Prev, row_1_Image_URL_Value),
            () -> assertNotEquals(row_2_Image_URL_Value_Prev, row_2_Image_URL_Value),
            () -> assertNotEquals(row_3_Image_URL_Value_Prev, row_3_Image_URL_Value),
            () -> assertNotEquals(row_1_Image_Text_Value_Prev, row_1_Image_Text_Value),
            () -> assertNotEquals(row_2_Image_Text_Value_Prev, row_2_Image_Text_Value),
            () -> assertNotEquals(row_3_Image_Text_Value_Prev, row_3_Image_Text_Value)
    );
  }

  @Test
  void test_Dynamic_Controls(){
    po.click_On_Dynamic_Controls_Link();
    //check checkbox is not checked
    assertThat(po.check_Dynamic_Control_Checkbox_is_Checked()).isFalse();
    //check input field is disabled
    assertThat(po.check_Dynamic_Control_Input_Field_is_Enabled()).isFalse();
    //click on remove and check no checkbox should be present
    assertThat(po.remove_Dynamic_Controls_Checkbox_Link()).isFalse();
    //add checkbox and select it
    assertThat(po.add_Dynamic_Controls_Checkbox_Link()).isTrue();
    //enable input field
    assertThat(po.click_On_Dynamic_Controls_Enable_Link()).isTrue();
    //disable input field
    assertThat(po.click_On_Dynamic_Controls_Disable_Link()).isFalse();

  }
}
