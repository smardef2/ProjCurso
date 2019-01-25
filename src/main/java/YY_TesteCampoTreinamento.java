//Versão antes de começar o padrão Page Objects. Uso da classe DSL
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class YY_TesteCampoTreinamento {
	
private WebDriver driver;
private DSL dsl;

// before para executar método antes de cada teste, não precisará chamar o inicializa() em cada método
@Before
public void inicializa() {
	 driver = new FirefoxDriver();
	    //WebDriver driver = new ChromeDriver();
     driver.manage().window().setSize(new Dimension(1200,765)); 
    //pede para driver acessar o  google
    //driver.get("file:///E:/_aCursos/selenuium/modulo3/componentes.html");
    //fazer chamada de forma dinamica, copiar os .html na pasta resource do proj  e chamar de lá
     //assim proj funcionará em qqr maquina
       driver.get("file:///"+System.getProperty("user.dir")+"/src/main/resources/componentes.html");
       dsl = new DSL(driver);
}

@After
public void finaliza() {
	driver.quit(); 	
}

@Test
public void teste() {
    
     //pelo selenium ide, vc grava a pag e ve o nome dos campos pelo campo target. procure o id
     //id=elementosForm:nome
    		  //procuro o elemento. e depois o q quer fazer com o elemento. qr escrever
    //driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste de escrita");
	//substitui linha acima
	dsl.escreve("elementosForm:nome", "Teste de escrita");
    //checar o q tá escrito no campo e pede para pegar texto q está lá dentro
    // Assert.assertEquals("Teste de escrita",driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
	//mesmo valor q linha acima, mas substitui com dsl
	Assert.assertEquals("Teste de escrita",dsl.obterValorCampo("elementosForm:nome"));
     
		//   Assert.assertEquals("Google", driver.getTitle());
		  
}

//para saber nome do elmento, ver o id dele, pode ser no codigo html, mas fica dificil de ver
//pode ser pelo selenium ide ou
//se usar o firefox, clica no elemento, botão dir do mouse-> inspecionar. ele mostra cada
//elem da pag e já mostra o id do elemento, não preciso do selenium ide.
@Test
public void deveInteragirComTextArea() {
	dsl.escreve("elementosForm:sugestoes", "Teste\nSegunda linha");
   // driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Teste\nSegunda linha");
   // Assert.assertEquals("Teste\nSegunda linha",driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));
	 Assert.assertEquals("Teste\nSegunda linha",dsl.obterValorCampo("elementosForm:sugestoes"));
    
}

@Test
public void deveInteragirComRadioButton() {
   // driver.findElement(By.id("elementosForm:sexo:0")).click();  //clica no elemento radioButton sexo masculino
	dsl.clicarRadio("elementosForm:sexo:0");
  //agora ver se elem está clicado

  Assert.assertTrue(dsl.isRadioMarcado("elementosForm:sexo:0"));
   
}	

@Test
public void deveInteragirComCheckbox() {
    driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();  //clica no elemento radioButton sexo masculino
  //agora ver se elem está clicado

  Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());
    
}	

@Test
public void deveInteragirComCombobox() {
	dsl.selecionarCombo("elementosForm:escolaridade", "2o grau incompleto");
   //ver o valor selecionado
   Assert.assertEquals("2o grau incompleto", dsl.obterValorCombo("elementosForm:escolaridade"));
    
}		


@Test
public void deveVerificarValoresCombo() {
    //ver tam da coleção
     Assert.assertEquals(8,dsl.obterQuantidadeOpcoesCombo("elementosForm:escolaridade"));

    //ver se um valor está na combo
     Assert.assertTrue(dsl.VerificarOpcaoCombo("elementosForm:escolaridade", "Mestrado"));
    
     
}

@Test
public void deveVerificarValoresComboMultiplo() {
	dsl.selecionarCombo("elementosForm:esportes", "Natacao");
	dsl.selecionarCombo("elementosForm:esportes", "Corrida");
	dsl.selecionarCombo("elementosForm:esportes", "Futebol");
	
    List<String> opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
    Assert.assertEquals(3,opcoesMarcadas.size());
    
    dsl.desselecionarCombo("elementosForm:esportes","Corrida");
    opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
    Assert.assertEquals(2,opcoesMarcadas.size());
  //Arrays.asList(...) cria uma lista a partir dos valores enviados
  Assert.assertTrue(opcoesMarcadas.containsAll(Arrays.asList("Natacao","Futebol")));
    
     
}	

@Test
@Ignore
public void deveIgnorar() {
	dsl.clicarBotao("buttonSimple");
    WebElement botao = driver.findElement(By.id("buttonSimple"));
    Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
    
}

@Test
public void deveInteragirComBotoes() {
	dsl.clicarBotao("buttonSimple");
  
    Assert.assertEquals("Obrigado!",dsl.obterValueElemento("buttonSimple"));
    
}	

@Test
public void deveInteragirComLinks() {
	//link na pagina não tem id. Para encntrar o link ao inves de usar id, usa o findbylinktext. 
	// pega o valor visivel para nós usuários no link
    //driver.findElement(By.linkText("Voltar")).click();
    dsl.clicarLink("Voltar");
    //Assert.assertEquals("Voltou!",driver.findElement(By.id("resultado")).getText());
    Assert.assertEquals("Voltou!",dsl.obterTexto("resultado"));
   //System.out.println("em resposta: " + driver.findElement(By.id("resultado")).getText());
   
}

@Test
public void deveBuscarTextosNaPagina() {
	//link na pagina não tem id. Para encntrar o link ao inves de usar id, usa o findbylinktext. 
	// pega o valor visivel para nós usuários no link
    //body abaixo traz todo o texto visivel da tela é o q está contido no <body>, mas como o user ve e não como formato html
    //System.out.println(driver.findElement(By.tagName("body")).getText());
    //Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("Campo de Treinamento"));
    //Assert.assertEquals("Campo de Treinamento",driver.findElement(By.tagName("h3")).getText());
    
    Assert.assertEquals("Campo de Treinamento",dsl.obterTexto(By.tagName("h3")));
    
    //Qdo procurar abaixo dará erro, pq o codigo tem varios <span> e Selenium retorna o primeira tag que encontrar
    //tem q ver uma forma de ser unico, pois tela pode ser alterada. tente encontrar forma mais especifica
    //tem classe  no span q qro então vamos usar
    //Assert.assertEquals("Cuidado onde clica, muitas armadilhas...",driver.findElement(By.tagName("span")).getText());
    //Assert.assertEquals("Cuidado onde clica, muitas armadilhas...",driver.findElement(By.className("facilAchar")).getText());
    
    Assert.assertEquals("Cuidado onde clica, muitas armadilhas...",dsl.obterTexto(By.className("facilAchar")));
    
}	

//Alerta - vai clicar no botão para gerar um alerta e vai pegar texto do alerta e colocar no campo de nome

@Test
public void testTextFieldDuplo() {
	//segunda escrita dará erro, pq não limpa. Na escrita vai ajustar cod para limpar antes de escrever
	dsl.escreve("elementosForm:nome", "Wagner");
	Assert.assertEquals("Wagner", dsl.obterValorCampo("elementosForm:nome"));
	dsl.escreve(By.id("elementosForm:nome"), "outro nome");  //limpará antes de escrever
	Assert.assertEquals("outro nome", dsl.obterValorCampo("elementosForm:nome"));
}

}
