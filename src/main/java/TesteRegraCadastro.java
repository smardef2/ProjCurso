//criar parametros como variaveis. cria metodo q dá valor as parametros
//mudar o runner da classe para informar q vai conter parametros tb
//parece q variaveis tm q ser unicas
import org.junit.Assert;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@RunWith(Parameterized.class)
public class TesteRegraCadastro {

	private WebDriver driver;
	private DSL dsl;
	private CampoTreinamentoPage page;
	@Parameter
	public String nome;
	@Parameter(value=1)
	public String sobrenome;
	@Parameter(value=2)
	public String sexo;
	@Parameter(value=3)
	public List<String> comidas;   //como comida pode ser mais de um valor, vai ser transformado em uma lista
	@Parameter(value=4)
	public String[] esportes;
	@Parameter(value=5)
	public String msg;
	
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
		driver.quit(); 	
	}
	
	@Parameters
	public static Collection<Object[]> getCollection() {
		return Arrays.asList(new Object[][] {
			{"","","",Arrays.asList(),new String[] {}, "Nome eh obrigatorio"},    //teste com todos dados em seq do q definido em parameters nome, sobrenpom,e
			{"Wagner","","",Arrays.asList(),new String[] {}, "Sobrenome eh obrigatorio"},
			{"Wagner","Costa","",Arrays.asList(),new String[] {}, "Sexo eh obrigatorio"},
			{"Wagner","Costa","Masculino",Arrays.asList("Carne","Vegetariano"),new String[] {}, "Tem certeza que voce eh vegetariano?"},
			{"Wagner","Costa","Masculino",Arrays.asList("Carne"),new String[] {"Karate", "O que eh esporte?"}, "Voce faz esporte ou nao?"}
		});
	}
	
	@Test
	public void deveValidarRegras() {
		page.setNome(nome);
		page.setSobrenome(sobrenome);
		if(sexo.equals("Masculino")) {
			page.setSexoMasculino();	
		}
		if(sexo.equals("Feminino"))  {
		   page.setSexoFeminino();	
		}
		if(comidas.contains("Carne")) page.setComidaCarne();
		if(comidas.contains("Pizza")) page.setComidaPizza();
		if(comidas.contains("Vegetariano")) page.setComidaVegetariana();
		

		page.setEsporte(esportes);
        page.cadastrar();	
        System.out.println(msg);
        Assert.assertEquals(msg,  dsl.alertaObterTextoAceito());
        
	}	
}
