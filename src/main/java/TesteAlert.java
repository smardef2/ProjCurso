import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteAlert {
	
	private WebDriver driver;
	private DSL dsl;
	
	@Before
	public void inicializa() {
		driver = new FirefoxDriver();
	    //WebDriver driver = new ChromeDriver();
     driver.manage().window().setSize(new Dimension(1200,765)); 
       driver.get("file:///"+System.getProperty("user.dir")+"/src/main/resources/componentes.html");
       dsl = new DSL(driver);
	}
	
	@After
	public void finaliza() {
		driver.quit(); 	
	}
	
	@Test
	public void deveInteragirComAlertSimples() {
		//link na pagina não tem id. Para encntrar o link ao inves de usar id, usa o findbylinktext. 
		// pega o valor visivel para nós usuários no link
		dsl.clicarBotao("alert");
        String texto = dsl.alertaObterTextoAceito();
        // é do Assert pega do org.junit
        Assert.assertEquals("Alert Simples", texto);  //texto do alerta
        dsl.escreve("elementosForm:nome", texto);
        driver.quit();  
	}
	
	//alert confirm, alerta do tipo sim ou não. se clica cancelar vem outra msg
	@Test
	public void deveInteragirComAlertConfirm() {
		dsl.clicarBotao("confirm");
        // é do Assert pega do org.junit
        Assert.assertEquals("Confirm Simples", dsl.alertaObterTextoAceito()); 		
        Assert.assertEquals("Confirmado", dsl.alertaObterTextoAceito());
        dsl.clicarBotao("confirm");
        Assert.assertEquals("Confirm Simples", dsl.alertaObterTextoENega()); 
        Assert.assertEquals("Negado", dsl.alertaObterTextoENega());
		
        driver.quit();  
	}	
	
	//alert do tipo prompt qdo quer pedir informação para o user
	@Test
	public void deveInteragirComAlertPrompt() {
		dsl.clicarBotao("prompt");
        // é do Assert pega do org.junit
        Assert.assertEquals("Digite um numero", dsl.alertaObterTexto());
        dsl.alertaEscreverTexto("12");
        Assert.assertEquals("Era 12?", dsl.alertaObterTextoAceito());
        Assert.assertEquals(":D", dsl.alertaObterTextoAceito());
        driver.quit();  
	}	
	
	
}
