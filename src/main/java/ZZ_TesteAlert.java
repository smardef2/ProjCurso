import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ZZ_TesteAlert {
	@Test
	public void deveInteragirComAlertSimples() {
		//link na pagina n�o tem id. Para encntrar o link ao inves de usar id, usa o findbylinktext. 
		// pega o valor visivel para n�s usu�rios no link
		WebDriver driver = new FirefoxDriver();
		//redimensiona browser
        driver.manage().window().setSize(new Dimension(1200,765)); 
        driver.get("file:///"+System.getProperty("user.dir")+"/src/main/resources/componentes.html");
        driver.findElement(By.id("alert")).click();
        //agora pede para o Selenium mudar o foco, para o alerta
        //o Alert � do webdriver selenium
        Alert alert = driver.switchTo().alert();  //devolve o alerta
        String texto = alert.getText();
        // � do Assert pega do org.junit
        Assert.assertEquals("Alert Simples", alert.getText());  //texto do alerta
        //o texto n�o apareceu pq o alerta ainda estava cobrindo a tela, tem q cuidar disso para escrever
        //tem bot�o de ok no alerta, pede para aceitar o alerta
        alert.accept();
        driver.findElement(By.id("elementosForm:nome")).sendKeys(texto);
        driver.quit();  
	}
	
	//alert confirm, alerta do tipo sim ou n�o. se clica cancelar vem outra msg
	@Test
	public void deveInteragirComAlertConfirm() {
		//link na pagina n�o tem id. Para encntrar o link ao inves de usar id, usa o findbylinktext. 
		// pega o valor visivel para n�s usu�rios no link
		WebDriver driver = new FirefoxDriver();
		//redimensiona browser
        driver.manage().window().setSize(new Dimension(1200,765)); 
        driver.get("file:///"+System.getProperty("user.dir")+"/src/main/resources/componentes.html");
        
        driver.findElement(By.id("confirm")).click();
        //agora pede para o Selenium mudar o foco, para o alerta
        //o Alert � do webdriver selenium
        Alert alert = driver.switchTo().alert();  //devolve o alerta
        Assert.assertEquals("Confirm Simples", alert.getText());
        alert.accept(); // qdo clica no bot�o ok
        Assert.assertEquals("Confirmado", alert.getText()); //� alerta simples
        alert.accept();  //para fechar pq confirmado tb � alerta simples
        
        //agora fazer cancelar bot�o
        driver.findElement(By.id("confirm")).click();
        alert = driver.switchTo().alert();  //devolve o alerta
        Assert.assertEquals("Confirm Simples", alert.getText());
        alert.dismiss();  //clica bot�o cancela do confirm
        Assert.assertEquals("Negado", alert.getText());
        alert.dismiss();   // alert.accept();  // ou alert.dismiss(); para fechar o alerta


        
        driver.quit();  
	}	
	
	//alert do tipo prompt qdo quer pedir informa��o para o user
	@Test
	public void deveInteragirComAlertPrompt() {
		//link na pagina n�o tem id. Para encntrar o link ao inves de usar id, usa o findbylinktext. 
		// pega o valor visivel para n�s usu�rios no link
		WebDriver driver = new FirefoxDriver();
		//redimensiona browser
        driver.manage().window().setSize(new Dimension(1200,765)); 
        driver.get("file:///"+System.getProperty("user.dir")+"/src/main/resources/componentes.html");
        driver.findElement(By.id("prompt")).click();
        //agora pede para o Selenium mudar o foco, para o alerta, o Alert � do webdriver selenium
        Alert alert = driver.switchTo().alert();  //devolve o alerta
        //a msg q vai aparecer � digite um numero
        // � do Assert pega do org.junit
        Assert.assertEquals("Digite um numero", alert.getText());  //texto do alerta
        //o texto n�o apareceu pq o alerta ainda estava cobrindo a tela, tem q cuidar disso para escrever
        //tem bot�o de ok no alerta, pede para aceitar o alerta
        alert.sendKeys("12");   //enviar um numero. 
        alert.accept();   //clicar em ok. Vai aparecer msg era 12?
        Assert.assertEquals("Era 12?", alert.getText());
        alert.accept();   //clicar em ok. Vai aparecer msg era 12?
        //System.out.println("kekokeko: " + alert.getText());
        Assert.assertEquals(":D", alert.getText());
       
        alert.accept();

        driver.quit();  
	}	
	
}
