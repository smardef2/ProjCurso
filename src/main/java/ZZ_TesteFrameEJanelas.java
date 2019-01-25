import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ZZ_TesteFrameEJanelas {
	@Test
	public void deveInteragirComFrames() {
		WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1200,765)); 
        driver.get("file:///"+System.getProperty("user.dir")+"/src/main/resources/componentes.html");
        //pede para focar no frame e depois procura elem. Selenium muda o foco
        driver.switchTo().frame("frame1");
        driver.findElement(By.id("frameButton")).click();
        Alert alert = driver.switchTo().alert();
        String msg = alert.getText();
        Assert.assertEquals("Frame OK!", msg);
        alert.accept();
        //agora o foco está no frame, tem que voltar foco para página para funcionar
        driver.switchTo().defaultContent();   //volta para pag principal
        driver.findElement(By.id("elementosForm:nome")).sendKeys(msg);
        
        
//        Assert.assertEquals("Voltou!",driver.findElement(By.id("resultado")).getText());
        driver.quit();  
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
        driver.close();   //fecha s´o a aberta. não a principal. quit fecha todas janelas
        driver.switchTo().window("");   //volta para janela q não tem titulo, a prinicpal
        driver.findElement(By.tagName("textarea")).sendKeys("e agora?");
        
        driver.quit();  
	}
	
	@Test
	public void deveInteragirComJanelasSemTitulo() {
		WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1200,765)); 
        driver.get("file:///"+System.getProperty("user.dir")+"/src/main/resources/componentes.html");
        
        driver.findElement(By.id("buttonPopUpHard")).click();
        //windwow handler diponivel no driver, q tb tem coleçã ode janelas executando
        System.out.println(driver.getWindowHandle());  //resposta: 8589934593 que é o numero de janela corrente, ainda ta na janela principal porque não mudei foco, e por isso retorna o primeiro valor
        System.out.println(driver.getWindowHandles());  //resposta: [8589934593, 8589934600] , aparece todos num de janela executando. o segundo id é da popup. pode ser usado para trocar janela. o num da principal aparece primeiro
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
