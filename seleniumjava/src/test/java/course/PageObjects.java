package course;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;

import static org.openqa.selenium.support.locators.RelativeLocator.with;


public class PageObjects {
  WebDriver driver;
  Wait<WebDriver> wait;
  String get_Basic_Auth_Post_Success_Login_Text = "//div[@id='content']//p";

  String Broken_Image_Link = "Broken Images";
  String first_Image = "(//div[@class=\"example\"]//img)[1]";
  String second_Image = "(//div[@class=\"example\"]//img)[2]";
  String third_Image = "(//div[@class=\"example\"]//img)[3]";

  String Challenging_DOM_Link ="Challenging DOM";
  String blue_Link ="//div[@class=\"large-2 columns\"]//a[@class=\"button\"]";
  String red_Link ="//div[@class=\"large-2 columns\"]//a[@class=\"button alert\"]";
  String green_Link ="//div[@class=\"large-2 columns\"]//a[@class=\"button success\"]";
  String answer_Link ="//div[@id=\"content\"]/script";
  String second_row_edit_Link ="(//tr[2]//td[7]//a)[1]";
  String seventh_row_delete_Link ="(//tr[7]//td[7]//a)[2]";

  String Checkboxes_Link ="Checkboxes";
  String first_Checkbox ="(//input[@type=\"checkbox\"])[1]";
  String second_Checkbox ="(//input[@type=\"checkbox\"])[2]";

  String Context_Menu_Link ="Context Menu";
  String hot_spot_box ="hot-spot";

  String Disappearing_Link ="Disappearing Elements";
  String Gallery_link ="//a[text()=\"Gallery\"]";

  String DragDrop_Link ="Drag and Drop";
  String column_a = "column-a";
  String column_b = "column-b";
  String column_a_Text = "//div[@id=\"column-a\"]//header";
  String column_b_Text = "//div[@id=\"column-b\"]//header";

  String Dropdown_Link ="Dropdown";
  String option_Dropdown ="dropdown";

  String Dynamic_Content_Link ="Dynamic Content";
  String dynamic_Content_Row_1_Image ="(//div[@id=\"content\"])[2]//div[@class=\"row\"][1]//div[@class=\"large-2 columns\"]//img";
  String dynamic_Content_Row_2_Image ="(//div[@id=\"content\"])[2]//div[@class=\"row\"][2]//div[@class=\"large-2 columns\"]//img";
  String dynamic_Content_Row_3_Image ="(//div[@id=\"content\"])[2]//div[@class=\"row\"][3]//div[@class=\"large-2 columns\"]//img";
  String dynamic_Content_Row_1_Text ="(//div[@id=\"content\"])[2]//div[@class=\"row\"][1]//div[@class=\"large-10 columns\"]";
  String dynamic_Content_Row_2_Text ="(//div[@id=\"content\"])[2]//div[@class=\"row\"][2]//div[@class=\"large-10 columns\"]";
  String dynamic_Content_Row_3_Text ="(//div[@id=\"content\"])[2]//div[@class=\"row\"][3]//div[@class=\"large-10 columns\"]";

  String Dynamic_Controls_Link ="Dynamic Controls";
  String dynamic_Controls_Checkbox_Link ="//input[@type=\"checkbox\"]";
  String dynamic_Controls_Remove_Link ="//button[text()=\"Remove\"]";
  String dynamic_Controls_Add_Link ="//button[text()=\"Add\"]";
  String dynamic_Controls_Input_Link ="//input[@type=\"text\"]";
  String dynamic_Controls_Enable_Link ="//button[text()=\"Enable\"]";
  String dynamic_Controls_Disable_Link ="//button[text()=\"Disable\"]";
  String dynamic_Controls_Loading_Link ="//img[@src=\"/img/ajax-loader.gif\"]";

  PageObjects(WebDriver driver){
    this.driver = driver;
    this.wait = new FluentWait<>(this.driver)
            .withTimeout(Duration.ofSeconds(10))
            .pollingEvery(Duration.ofSeconds(3))
            .ignoring(NoSuchElementException.class);
  }

  WebElement findEle( By locator){
    WebElement ele = null;
    try {
      ele = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    } catch (TimeoutException e) {
      System.out.println("Element did not appear.");
    }
    return ele;
  }

  List<WebElement> findElements(By locator) {
    return wait.until(new Function<WebDriver, List<WebElement>>() {
      public List<WebElement> apply(WebDriver webDriver) {
        return webDriver.findElements(locator);
      }
    });
  }

  String get_Basic_Auth_Post_Success_Login_Text(){
    WebElement ele = findEle(By.xpath(get_Basic_Auth_Post_Success_Login_Text));
    return ele.getText();
  }

  void click_On_Broken_Image_Link(){
    WebElement ele = findEle(By.linkText(Broken_Image_Link));
    ele.click();
  }

  String get_URL_of_First_Image(){
    WebElement ele = findEle(By.xpath(first_Image));
    return ele.getAttribute("src");
  }

  String get_URL_of_Second_Image(){
    WebElement ele = findEle(By.xpath(second_Image));
    return ele.getAttribute("src");
  }

  String get_URL_of_Third_Image(){
    WebElement ele = findEle(By.xpath(third_Image));
    return ele.getAttribute("src");
  }

  void click_On_Challenging_DOM_Link(){
    WebElement ele = findEle(By.linkText(Challenging_DOM_Link));
    ele.click();
  }

  void click_On_Blue_Link(){
    WebElement ele = findEle(By.xpath(blue_Link));
    ele.click();
  }

  String get_text_Of_Blue_Link(){
    WebElement ele = findEle(By.xpath(blue_Link));
    return ele.getText();
  }

  void click_On_Red_Link(){
    WebElement ele = findEle(By.xpath(red_Link));
    ele.click();
  }

  String get_text_Of_Red_Link(){
    WebElement ele = findEle(By.xpath(red_Link));
    return ele.getText();
  }

  void click_On_Green_Link(){
    WebElement ele = findEle(By.xpath(green_Link));
    ele.click();
  }

  String get_text_Of_Green_Link(){
    WebElement ele = findEle(By.xpath(green_Link));
    return ele.getText();
  }

  String get_text_Of_Answer(){
    WebElement ele = findEle(By.xpath(answer_Link));
    return ele.getAttribute("innerHTML");
  }

  void click_On_second_row_edit_Link(){
    WebElement ele = findEle(By.xpath(second_row_edit_Link));
    ele.click();
  }

  void click_On_seventh_row_delete_Link(){
    WebElement ele = findEle(By.xpath(seventh_row_delete_Link));
    ele.click();
  }

  void click_On_CheckBoxes_Link(){
    WebElement ele = findEle(By.linkText(Checkboxes_Link));
    ele.click();
  }

  void click_First_CheckBox(){
    WebElement ele = findEle(By.xpath(first_Checkbox));
    ele.click();
  }

  void click_Second_CheckBox(){
    WebElement ele = findEle(By.xpath(second_Checkbox));
    ele.click();
  }

  boolean check_First_CheckBox_isSelected(){
    WebElement ele = findEle(By.xpath(first_Checkbox));
    return ele.isSelected();
  }

  boolean check_Second_CheckBox_isSelected(){
    WebElement ele = findEle(By.xpath(second_Checkbox));
    return ele.isSelected();
  }

  void click_On_Context_Menu_Link(){
    WebElement ele = findEle(By.linkText(Context_Menu_Link));
    ele.click();
  }

  boolean right_Click_Box(){
    boolean alertPresence;
    WebElement paragraph = findEle(By.xpath("(//div[@id='content']//p)[2]"));
    WebElement ele = driver.findElement(with(By.id(hot_spot_box)).below(paragraph));
    Actions action = new Actions(driver);
    action.contextClick(ele).perform();
    try{
      wait.until(ExpectedConditions.alertIsPresent());
      alertPresence = true;
    }
    catch (TimeoutException e){
      alertPresence = false;
    }
  return alertPresence;
  }

  void click_On_Disappearing_Link(){
    WebElement ele = findEle(By.linkText(Disappearing_Link));
    ele.click();
  }

  boolean verify_gallery_present_on_page_refresh(){
    try{
      findEle(By.xpath(Gallery_link));
    }
    catch(NoSuchElementException e){
      driver.navigate().refresh();
    }
    return findEle(By.xpath(Gallery_link)).isDisplayed();
  }

  void click_On_Dragdrop_Link(){
    WebElement ele = findEle(By.linkText(DragDrop_Link));
    ele.click();
  }

  void move_columnA_to_B(){
    Actions action = new Actions(driver);
    WebElement columnA = findEle(By.id(column_a));
    WebElement columnB = findEle(By.id(column_b));
    action.dragAndDrop(columnA, columnB).perform();
  }

  String get_Text_at_columnA(){
    return findEle(By.xpath(column_a_Text)).getText();
  }

  String get_Text_at_columnB(){
    return findEle(By.xpath(column_b_Text)).getText();
  }

  void click_On_Dropdown_Link(){
    WebElement ele = findEle(By.linkText(Dropdown_Link));
    ele.click();
  }

  String select_value_by_value(String value) {
    Select select = new Select(findEle(By.id(option_Dropdown)));
    select.selectByVisibleText(value);
    return select.getFirstSelectedOption().getText();
  }

  void click_On_Dynamic_Content_Link(){
    WebElement ele = findEle(By.linkText(Dynamic_Content_Link));
    ele.click();
  }

  String get_Dynamic_Text(String locator) {
    return findEle(By.xpath(locator)).getText();
  }

  String get_Dynamic_Attribute(String locator, String attribute) {
    return findEle(By.xpath(locator)).getAttribute(attribute);
  }

  void click_On_Dynamic_Controls_Link(){
    WebElement ele = findEle(By.linkText(Dynamic_Controls_Link));
    ele.click();
  }

  boolean check_Dynamic_Control_Checkbox_is_Checked(){
    WebElement ele = null;
    try{
      ele = findEle(By.xpath(dynamic_Controls_Checkbox_Link));
      return ele.isSelected();
    }catch(NoSuchElementException e) {
      return false;
    }
  }

  boolean check_Dynamic_Control_Input_Field_is_Enabled(){
    WebElement ele = null;
    try{
      ele = findEle(By.xpath(dynamic_Controls_Input_Link));
      return ele.isEnabled();
    }catch(NoSuchElementException e) {
      return false;
    }
  }

  boolean remove_Dynamic_Controls_Checkbox_Link() {
    WebElement ele = findEle(By.xpath(dynamic_Controls_Remove_Link));
    ele.click();
    try {
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dynamic_Controls_Loading_Link)));
    } catch (TimeoutException e) {
      System.out.println("Loading element did not appear.");
    }

    try {
      wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(dynamic_Controls_Loading_Link)));
    } catch (TimeoutException e) {
      System.out.println("Loading element did not disappear.");
    }
    return check_Dynamic_Controls_Checkbox_Link_Displayed();
  }

  boolean check_Dynamic_Controls_Checkbox_Link_Displayed() {
    WebElement ele = null;
    try {
      ele = findEle(By.xpath(dynamic_Controls_Checkbox_Link));
      return ele != null && ele.isDisplayed();
    } catch (NoSuchElementException e) {
      return false;
    } catch (Exception e) {
      return false;
    }
  }

  boolean add_Dynamic_Controls_Checkbox_Link() {
    WebElement ele = findEle(By.xpath(dynamic_Controls_Add_Link));
    ele.click();
    try {
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dynamic_Controls_Loading_Link)));
    } catch (TimeoutException e) {
      System.out.println("Loading element did not appear.");
    }

    try {
      wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(dynamic_Controls_Loading_Link)));
    } catch (TimeoutException e) {
      System.out.println("Loading element did not disappear.");
    }

    check_Dynamic_Controls_Checkbox_Link_Displayed();
    findEle(By.xpath(dynamic_Controls_Checkbox_Link)).click();
    return check_Dynamic_Control_Checkbox_is_Checked();
  }

  boolean click_On_Dynamic_Controls_Enable_Link() {
    WebElement ele = findEle(By.xpath(dynamic_Controls_Enable_Link));
    ele.click();
    try {
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dynamic_Controls_Loading_Link)));
    } catch (TimeoutException e) {
      System.out.println("Loading element did not appear.");
    }

    try {
      wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(dynamic_Controls_Loading_Link)));
    } catch (TimeoutException e) {
      System.out.println("Loading element did not disappear.");
    }
    return check_Dynamic_Control_Input_Field_is_Enabled();
  }

  boolean click_On_Dynamic_Controls_Disable_Link() {
    WebElement ele = findEle(By.xpath(dynamic_Controls_Disable_Link));
    ele.click();
    try {
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dynamic_Controls_Loading_Link)));
    } catch (TimeoutException e) {
      System.out.println("Loading element did not appear.");
    }

    try {
      wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(dynamic_Controls_Loading_Link)));
    } catch (TimeoutException e) {
      System.out.println("Loading element did not disappear.");
    }
    return check_Dynamic_Control_Input_Field_is_Enabled();
  }
}
