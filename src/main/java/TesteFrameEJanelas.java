import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteFrameEJanelas {
	
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
	public void deveInteragirComFrames() {
		dsl.entrarFrame("frame1");
		dsl.clicarBotao("frameButton");
		String msg = dsl.alertaObterTextoAceito();
        Assert.assertEquals("Frame OK!", msg);
        dsl.sairFrame();
        dsl.escreve("elementosForm:nome", msg);
        driver.quit();  
	}
	
	@Test
	public void deveInteragirComFrameEscondido() {
		
		//usar frame como referencia pq bot�o dentro do frame
		WebElement frame = driver.findElement(By.id("frame2"));
		dsl.executarJS("window.scrollBy(0,arguments[0])", frame.getLocation().y);
		dsl.entrarFrame("frame2");   //tm q fazer scroll antes de entrar no frame
		dsl.clicarBotao("frameButton");
		String msg = dsl.alertaObterTextoAceito();
        Assert.assertEquals("Frame OK!", msg);
	}
	
	//Vai ser dado window.open e a janela tem um titulo
	@Test
	public void deveInteragirComJanelas() {
		WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1200,765)); 
        driver.get("file:///"+System.getProperty("user.dir")+"/src/main/resources/componentes.html");
        
        driver.findElement(By.id("buttonPopUpEasy")).click();
        //Abriu o metodo javascript para ver o q faz, vai aparecer pop up - window.open. essa janela tem titulo, nem todas tem 
        driver.switchTo().window("Popup");
        //janela tem uma textarea
        driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
        driver.close();   //fecha s�o a aberta. n�o a principal. quit fecha todas janelas
        driver.switchTo().window("");   //volta para janela q n�o tem titulo, a prinicpal
        driver.findElement(By.tagName("textarea")).sendKeys("e agora?");
        
        driver.quit();  
	}
	
	@Test
	public void deveInteragirComJanelasSemTitulo() {
		WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1200,765)); 
        driver.get("file:///"+System.getProperty("user.dir")+"/src/main/resources/componentes.html");
        
        driver.findElement(By.id("buttonPopUpHard")).click();
        //windwow handler diponivel no driver, q tb tem cole�� ode janelas executando
        System.out.println(driver.getWindowHandle());  //resposta: 8589934593 que � o numero de janela corrente, ainda ta na janela principal porque n�o mudei foco, e por isso retorna o primeiro valor
        System.out.println(driver.getWindowHandles());  //resposta: [8589934593, 8589934600] , aparece todos num de janela executando. o segundo id � da popup. pode ser usado para trocar janela. o num da principal aparece primeiro
        //Abriu o metodo javascript para ver o q faz, vai aparecer pop up - window.open. essa janela tem titulo, nem todas tem
        //quero o segundo valor, transformo em array e pego segunda ocorrencia
        driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);
        driver.findElement(By.tagName("textarea")).sendKeys("Deu certo 2?");
        //voltar foco para janela principal
        driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);
        driver.findElement(By.tagName("textarea")).sendKeys("E agora 2?");
        driver.quit();  
	}	
	
}
