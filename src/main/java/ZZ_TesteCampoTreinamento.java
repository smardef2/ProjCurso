import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

//Vers�o inicial da classe antes de ajustes para deixar c�digo enxuto om DSL
// Antes de criar classe DSL

public class ZZ_TesteCampoTreinamento {
	@Test
	public void teste() {
	     WebDriver driver = new FirefoxDriver();
    			    //WebDriver driver = new ChromeDriver();
	     driver.manage().window().setSize(new Dimension(1200,765)); 
		 //pede para driver acessar o  google
		 //driver.get("file:///E:/_aCursos/selenuium/modulo3/componentes.html");
	     //fazer chamada de forma dinamica, copiar os .html na pasta resource do proj  e chamar de l�
	      //assim proj funcionar� em qqr maquina
	     driver.get("file:///"+System.getProperty("user.dir")+"/src/main/resources/componentes.html");
	     //pelo selenium ide, vc grava a pag e ve o nome dos campos pelo campo target. procure o id
	     //id=elementosForm:nome
	    		  //procuro o elemento. e depois o q quer fazer com o elemento. qr escrever
	    driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste de escrita");
	    //checar o q t� escrito no campo e pede para pegar texto q est� l� dentro
	     Assert.assertEquals("Teste de escrita",driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
	     
			//   Assert.assertEquals("Google", driver.getTitle());
			   driver.quit();  
	}
	
	//para saber nome do elmento, ver o id dele, pode ser no codigo html, mas fica dificil de ver
	//pode ser pelo selenium ide ou
	//se usar o firefox, clica no elemento, bot�o dir do mouse-> inspecionar. ele mostra cada
	//elem da pag e j� mostra o id do elemento, n�o preciso do selenium ide.
	@Test
	public void deveInteragirComTextArea() {
		WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1200,765)); 
        driver.get("file:///"+System.getProperty("user.dir")+"/src/main/resources/componentes.html");
        driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Teste\nSegunda linha");
        Assert.assertEquals("Teste\nSegunda linha",driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));
        driver.quit();  
	}
	
	@Test
	public void deveInteragirComRadioButton() {
		WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1200,765)); 
        driver.get("file:///"+System.getProperty("user.dir")+"/src/main/resources/componentes.html");
        driver.findElement(By.id("elementosForm:sexo:0")).click();  //clica no elemento radioButton sexo masculino
      //agora ver se elem est� clicado
    
      Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
        driver.quit();  
	}	
	
	@Test
	public void deveInteragirComCheckbox() {
		WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1200,765)); 
        driver.get("file:///"+System.getProperty("user.dir")+"/src/main/resources/componentes.html");
        driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();  //clica no elemento radioButton sexo masculino
      //agora ver se elem est� clicado
    
      Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());
        driver.quit();  
	}	
	
	@Test
	public void deveInteragirComCombobox() {
		WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1200,765)); 
        driver.get("file:///"+System.getProperty("user.dir")+"/src/main/resources/componentes.html");
        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(element);
        combo.selectByIndex(3);   //seleciona a quarta op��o da combo. index come�a do zero
        //ou usar o value do elemento. qdo inspeciona elem html, tem o value da combo .
         combo.selectByValue("superior");
       //mas, prof prefere valor do combo visivel ao user, pq � o q o user ve
       combo.selectByVisibleText("2o grau incompleto");
       //ver o valor selecionado
       Assert.assertEquals("2o grau incompleto", combo.getFirstSelectedOption().getText());
        driver.quit();  
	}		
	

	@Test
	public void deveVerificarValoresCombo() {
		WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1200,765)); 
        driver.get("file:///"+System.getProperty("user.dir")+"/src/main/resources/componentes.html");
        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(element);
        List<WebElement> options = combo.getOptions();  //devolve lista de web elements
        //ver tam da cole��o
         Assert.assertEquals(8,options.size());

        //ver se um valor est� na combo
         boolean encontrou = false;
         for(WebElement option: options) {
           if(option.getText().equals("Mestrado")) {
              encontrou = true;
              break;
           }
         }
         Assert.assertTrue(encontrou);
        
        driver.quit();  
	}
	
	@Test
	public void deveVerificarValoresComboMultiplo() {
		WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1200,765)); 
        driver.get("file:///"+System.getProperty("user.dir")+"/src/main/resources/componentes.html");
        WebElement element = driver.findElement(By.id("elementosForm:esportes"));
        Select combo = new Select(element);
        combo.selectByVisibleText("Natacao");
        combo.selectByVisibleText("Corrida");
        combo.selectByVisibleText("O que eh esporte?");
        List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
        Assert.assertEquals(3,allSelectedOptions.size());
        combo.deselectByVisibleText("Corrida");
        allSelectedOptions = combo.getAllSelectedOptions();
        Assert.assertEquals(2,allSelectedOptions.size());
        
        driver.quit();  
	}	
	
	@Test
	@Ignore
	public void deveInteragirComBotoes() {
		WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1200,765)); 
        driver.get("file:///"+System.getProperty("user.dir")+"/src/main/resources/componentes.html");
        WebElement botao = driver.findElement(By.id("buttonSimple"));
        botao.click();
        Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
        driver.quit();  
	}
	
	@Test
	public void deveInteragirComLinks() {
		//link na pagina n�o tem id. Para encntrar o link ao inves de usar id, usa o findbylinktext. 
		// pega o valor visivel para n�s usu�rios no link
		WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1200,765)); 
        driver.get("file:///"+System.getProperty("user.dir")+"/src/main/resources/componentes.html");
        driver.findElement(By.linkText("Voltar")).click();
        Assert.assertEquals("Voltou!",driver.findElement(By.id("resultado")).getText());
        System.out.println("em resposta: " + driver.findElement(By.id("resultado")).getText());
        driver.quit();  
	}
	
	@Test
	public void deveBuscarTextosNaPagina() {
		//link na pagina n�o tem id. Para encntrar o link ao inves de usar id, usa o findbylinktext. 
		// pega o valor visivel para n�s usu�rios no link
		WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1200,765)); 
        driver.get("file:///"+System.getProperty("user.dir")+"/src/main/resources/componentes.html");
        //body abaixo traz todo o texto visivel da tela � o q est� contido no <body>, mas como o user ve e n�o como formato html
        //System.out.println(driver.findElement(By.tagName("body")).getText());
        //Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("Campo de Treinamento"));
        Assert.assertEquals("Campo de Treinamento",driver.findElement(By.tagName("h3")).getText());
        
        //Qdo procurar abaixo dar� erro, pq o codigo tem varios <span> e Selenium retorna o primeira tag que encontrar
        //tem q ver uma forma de ser unico, pois tela pode ser alterada. tente encontrar forma mais especifica
        //tem classe  no span q qro ent�o vamos usar
        //Assert.assertEquals("Cuidado onde clica, muitas armadilhas...",driver.findElement(By.tagName("span")).getText());
        Assert.assertEquals("Cuidado onde clica, muitas armadilhas...",driver.findElement(By.className("facilAchar")).getText());
        driver.quit();  
	}	

	//Alerta - vai clicar no bot�o para gerar um alerta e vai pegar texto do alerta e colocar no campo de nome
}

/* 
 * ---------------------------------------
 * Teste incompleto
 *    //n�o deixe testes passando sem estar completos, porque o  teste diz q itera���o com link est� passando
mas teste n�o teve o codigo terminado. pode-se usar o assert e diz fail para falhar o teste e eu saber
que n�o est� terminado ainda
  Assert.fail();

//ou utilize outro recurso use anota��o
@Ignore - use namespace do org.junit - ele diz para junit que n�o execute os testes,
eu fa�o a execu��o, mas status executado tem informa�� ode skipped porque teste foi ignorado.
    @Test
	@Ignore
	public void metodo() {}
 * ----------------------------------------
 * COMANDOS
 *    ctrl+barra de espa�o - completa comando.
 *    ctrl+S = salvar e j� compila
 * ---------------------------------------
 * */
