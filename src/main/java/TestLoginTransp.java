import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.gargoylesoftware.htmlunit.javascript.host.Console;

public class TestLoginTransp {
	
	@Test
	public void entrada() {
		//WebDriver driver = new FirefoxDriver();
		WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1200,765)); 
        driver.get("http://aplic-edap.diadema.sp.gov.br/edu_teste/index/");
        driver.findElement(By.id("id_sc_field_txt_login")).sendKeys("106930");
        driver.findElement(By.id("id_sc_field_txt_senha")).sendKeys("senha");
        WebElement botao = driver.findElement(By.id("sub_form_b"));
        botao.click();
     //checar o q tá escrito no campo e pede para pegar texto q está lá dentro
        String var_aux = driver.findElement(By.id("id_error_message_fixed")).getText();
        Assert.assertTrue(driver.findElement(By.id("id_error_message_fixed")).getText().contains("texto digitado não confere com a imagem"));	
}
	

}
