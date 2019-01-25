import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteUrlVerdadeira {
	@Test
	public void deveInteragirComTextArea() {
		WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1200,765)); 
        driver.get("http://aplic-pmd.diadema.sp.gov.br/ff/form_login_publico/");
        driver.findElement(By.id("id_sc_field_var_cpf")).sendKeys("01234567890");
        Assert.assertEquals("01234567890",driver.findElement(By.id("id_sc_field_var_cpf")).getAttribute("value"));
       // driver.quit();  
	}
}
