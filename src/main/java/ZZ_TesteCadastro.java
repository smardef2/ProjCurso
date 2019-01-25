import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class ZZ_TesteCadastro {
	@Test
	public void deveRealizarCadastroComSucesso() {
		//link na pagina não tem id. Para encntrar o link ao inves de usar id, usa o findbylinktext. 
		// pega o valor visivel para nós usuários no link
		
		/*Vai preencher os dados do form e vai mandar cadastrar. Qdo termina ele responde que cadastrado na tela
		e devolve resposta:
		Cadastrado!
Nome: Wagner
Sobrenome: Costa
Sexo: Masculino
Comida: Pizza
Escolaridade: 1grauincomp
Esportes: Natacao
Sugestoes:
 
  Nota: no Selenium não considera as tags, só o conteúdo. Porque a resposta para "nome" na verdade 
  tem tag <span> e não considerou.
			*/
		WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1200,765)); 
        driver.get("file:///"+System.getProperty("user.dir")+"/src/main/resources/componentes.html");
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Wagner");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Costa");
        driver.findElement(By.id("elementosForm:sexo:0")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
        new Select(driver.findElement(By.id("elementosForm:escolaridade")))
        .selectByVisibleText("Mestrado");
        new Select(driver.findElement(By.id("elementosForm:esportes")))
        .selectByVisibleText("Natacao");
        
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Assert.assertTrue(driver.findElement(By.id("resultado")).getText().startsWith("Cadastrado!"));
        Assert.assertTrue(driver.findElement(By.id("descNome")).getText().endsWith("Wagner"));
        Assert.assertEquals("Sobrenome: Costa",driver.findElement(By.id("descSobrenome")).getText());
        Assert.assertEquals("Sexo: Masculino",driver.findElement(By.id("descSexo")).getText());
        Assert.assertEquals("Comida: Pizza",driver.findElement(By.id("descComida")).getText());
        Assert.assertEquals("Escolaridade: mestrado",driver.findElement(By.id("descEscolaridade")).getText());
        Assert.assertEquals("Esportes: Natacao",driver.findElement(By.id("descEsportes")).getText());
   

        
        driver.quit();  //fechar o browser
	}
	
	//fazer um metodo para cada validação
	@Test
	public void deveValidarNomeObrigatorio() {
		WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1200,765)); 
        driver.get("file:///"+System.getProperty("user.dir")+"/src/main/resources/componentes.html");
        
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        //não preencheu nome, deve aparecer alert reclamando
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Nome eh obrigatorio", alert.getText());
        driver.quit();
	}
	
	//para validar sobrenome, tem que ter nome preenchido se não dá outro erro e não valida se sobrenome obrigatoro
	@Test
	public void deveValidarSobreNomeObrigatorio() {
		WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1200,765)); 
        driver.get("file:///"+System.getProperty("user.dir")+"/src/main/resources/componentes.html");
        
        driver.findElement(By.id("elementosForm:nome")).sendKeys("nome qqr");
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        //não preencheu sobrenome, deve aparecer alert reclamando
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Sobrenome eh obrigatorio", alert.getText());
        driver.quit();
	}
	
	@Test
	public void deveValidarSexoObrigatorio() {
		WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1200,765)); 
        driver.get("file:///"+System.getProperty("user.dir")+"/src/main/resources/componentes.html");
        
        driver.findElement(By.id("elementosForm:nome")).sendKeys("nome qqr");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("sobrnome qqr");
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        //não preencheu sobrenome, deve aparecer alert reclamando
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Sexo eh obrigatorio", alert.getText());
        driver.quit();
	}	
	
	@Test
	public void deveValidarComidaVegetariana() {
		WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1200,765)); 
        driver.get("file:///"+System.getProperty("user.dir")+"/src/main/resources/componentes.html");
        
        driver.findElement(By.id("elementosForm:nome")).sendKeys("nome qqr");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("sobrnome qqr");
        driver.findElement(By.id("elementosForm:sexo:0")).click();  //selecionou sexo masc
        driver.findElement(By.id("elementosForm:comidaFavorita:0")).click(); //selec carne
        driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();  //vegetariano
        
        
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        //não preencheu sobrenome, deve aparecer alert reclamando
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
        driver.quit();
	}	
	
	@Test
	public void deveValidarEsportistaIndeciso() {
		WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1200,765)); 
        driver.get("file:///"+System.getProperty("user.dir")+"/src/main/resources/componentes.html");
        
        driver.findElement(By.id("elementosForm:nome")).sendKeys("nome qqr");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("sobrnome qqr");
        driver.findElement(By.id("elementosForm:sexo:0")).click();  //selecionou sexo masc
        driver.findElement(By.id("elementosForm:comidaFavorita:0")).click(); //selec carne
        Select combo = new Select(driver.findElement(By.id("elementosForm:esportes")));
        combo.selectByVisibleText("Karate");
        combo.selectByVisibleText("O que eh esporte?");
        
        
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alert = driver.switchTo().alert();
        driver.quit();
	}		

}
