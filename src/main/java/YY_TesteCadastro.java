//versão 2 - antes de começar padrão page objects. Usa classe DLS

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class YY_TesteCadastro {
	private WebDriver driver;
	private DSL dsl;
	
	// before para executar método antes de cada teste, não precisará chamar o inicializa() em cada método
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
	public void deveRealizarCadastroComSucesso() {
        dsl.escreve("elementosForm:nome", "Wagner");
        dsl.escreve("elementosForm:sobrenome", "Costa");
        dsl.clicarRadio("elementosForm:sexo:0");
        dsl.clicarRadio("elementosForm:comidaFavorita:2");
        dsl.selecionarCombo("elementosForm:escolaridade", "Mestrado");
        dsl.selecionarCombo("elementosForm:esportes", "Natacao");
        dsl.clicarBotao("elementosForm:cadastrar");
        
        Assert.assertTrue(dsl.obterTexto("resultado").startsWith("Cadastrado!"));
        Assert.assertTrue(dsl.obterTexto("descNome").endsWith("Wagner"));
        Assert.assertEquals("Sobrenome: Costa",dsl.obterTexto("descSobrenome"));
        Assert.assertEquals("Sexo: Masculino",dsl.obterTexto("descSexo"));
        Assert.assertEquals("Comida: Pizza",dsl.obterTexto("descComida"));
        Assert.assertEquals("Escolaridade: mestrado",dsl.obterTexto("descEscolaridade"));
        Assert.assertEquals("Esportes: Natacao",dsl.obterTexto("descEsportes"));
   
	}
	
	//fazer um metodo para cada validação
	@Test
	public void deveValidarNomeObrigatorio() {
		dsl.clicarBotao("elementosForm:cadastrar");
        Assert.assertEquals("Nome eh obrigatorio", dsl.alertaObterTextoAceito());
       
	}
	
	//para validar sobrenome, tem que ter nome preenchido se não dá outro erro e não valida se sobrenome obrigatoro
	@Test
	public void deveValidarSobreNomeObrigatorio() {
		dsl.escreve("elementosForm:nome", "nome qqr");
        dsl.clicarBotao("elementosForm:cadastrar");
        //não preencheu sobrenome, deve aparecer alert reclamando
        Assert.assertEquals("Sobrenome eh obrigatorio", dsl.alertaObterTextoAceito());
        
	}
	
	@Test
	public void deveValidarSexoObrigatorio() {
		        
        driver.findElement(By.id("elementosForm:nome")).sendKeys("nome qqr");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("sobrnome qqr");
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        //não preencheu sobrenome, deve aparecer alert reclamando
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Sexo eh obrigatorio", alert.getText());
        
	}	
	
	@Test
	public void deveValidarComidaVegetariana() {
	       
        driver.findElement(By.id("elementosForm:nome")).sendKeys("nome qqr");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("sobrnome qqr");
        driver.findElement(By.id("elementosForm:sexo:0")).click();  //selecionou sexo masc
        driver.findElement(By.id("elementosForm:comidaFavorita:0")).click(); //selec carne
        driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();  //vegetariano
        
        
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        //não preencheu sobrenome, deve aparecer alert reclamando
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
	}	
	
	@Test
	public void deveValidarEsportistaIndeciso() {
       
        driver.findElement(By.id("elementosForm:nome")).sendKeys("nome qqr");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("sobrnome qqr");
        driver.findElement(By.id("elementosForm:sexo:0")).click();  //selecionou sexo masc
        driver.findElement(By.id("elementosForm:comidaFavorita:0")).click(); //selec carne
        Select combo = new Select(driver.findElement(By.id("elementosForm:esportes")));
        combo.selectByVisibleText("Karate");
        combo.selectByVisibleText("O que eh esporte?");
        
        
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alert = driver.switchTo().alert();
        
	}		
}
