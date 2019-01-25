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

public class TesteCadastro {

	private WebDriver driver;
	private DSL dsl;
	private CampoTreinamentoPage page;
	
	// before para executar método antes de cada teste, não precisará chamar o inicializa() em cada método
	@Before
	public void inicializa() {
		 driver = new FirefoxDriver();
		    //WebDriver driver = new ChromeDriver();
         driver.manage().window().setSize(new Dimension(1200,765)); 
           driver.get("file:///"+System.getProperty("user.dir")+"/src/main/resources/componentes.html");
           dsl = new DSL(driver);
           page = new CampoTreinamentoPage(driver);
	}
	
	@After
	public void finaliza() {
		//driver.quit(); 	
	}
	
	@Test
	public void deveRealizarCadastroComSucesso() {
		page.setNome("Wagner");
		page.setSobrenome("Costa");
		page.setSexoMasculino();
		page.setComidaPizza();
		page.setEscolaridade("Mestrado");
		page.setEsporte("Natacao");
		page.cadastrar();
        
       // Assert.assertTrue(page.obterResultadoCadastro().startsWith("Cadastrado!"));
		 Assert.assertEquals("Cadastrado!",page.obterResultadoCadastro());
        //Assert.assertTrue(page.obterNomeCadastro().endsWith("Wagner"));
		 Assert.assertEquals("Wagner",page.obterNomeCadastro());
        Assert.assertEquals("Costa",page.obterSobrenomeCadastro());
        Assert.assertEquals("Masculino", page.obterSexoCadastro());
        Assert.assertEquals("Pizza",page.obterComidaCadastro());
        Assert.assertEquals("mestrado",page.obterEscolaridadeCadastro());
        Assert.assertEquals("Natacao",page.obterEsporteCadastro());
   
	}
	
	//fazer um metodo para cada validação
	@Test
	public void deveValidarNomeObrigatorio() {
		page.cadastrar();
        Assert.assertEquals("Nome eh obrigatorio", dsl.alertaObterTextoAceito());
       
	}
	
	//para validar sobrenome, tem que ter nome preenchido se não dá outro erro e não valida se sobrenome obrigatoro
	@Test
	public void deveValidarSobreNomeObrigatorio() {
		page.setNome("nome qqr");
        page.cadastrar();
        //não preencheu sobrenome, deve aparecer alert reclamando
        Assert.assertEquals("Sobrenome eh obrigatorio", dsl.alertaObterTextoAceito());
        
	}
	
	@Test
	public void deveValidarSexoObrigatorio() {
		page.setNome("nome qqr");
		page.setSobrenome("sobrnome qqr");
        page.cadastrar();		        
        //não preencheu sobrenome, deve aparecer alert reclamando
        Assert.assertEquals("Sexo eh obrigatorio", dsl.alertaObterTextoAceito());
        
	}	
	
	@Test
	public void deveValidarComidaVegetariana() {
		page.setNome("nome qqr");
		page.setSobrenome("sobrnome qqr");
		page.setSexoMasculino();
		page.setSexoFeminino();
		page.setComidaCarne();
		page.setComidaVegetariana();
        page.cadastrar();		       
        //não preencheu sobrenome, deve aparecer alert reclamando
        Assert.assertEquals("Tem certeza que voce eh vegetariano?",  dsl.alertaObterTextoAceito());
	}	
	
	@Test
	public void deveValidarEsportistaIndeciso() {
		page.setNome("nome qqr");
		page.setSobrenome("sobrnome qqr");
		page.setSexoMasculino();
		page.setSexoMasculino();
		page.setComidaCarne();
		page.setEsporte("Karate", "O que eh esporte?");
        page.cadastrar();		       
        Assert.assertEquals("Voce faz esporte ou nao?",  dsl.alertaObterTextoAceito());
        
	}		

	
}
