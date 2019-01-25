import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestePrine {
	private WebDriver driver;
	private DSL dsl;
	
	// before para executar m�todo antes de cada teste, n�o precisar� chamar o inicializa() em cada m�todo
	@Before
	public void inicializa() {
		 driver = new FirefoxDriver();
		    //WebDriver driver = new ChromeDriver();
         driver.manage().window().setSize(new Dimension(1200,765)); 
           driver.get("file:///"+System.getProperty("user.dir")+"/src/main/resources/componentes.html");
        //   page = new CampoTreinamentoPage(driver);
	}
	
	@After
	public void finaliza() {
	//	driver.quit(); 	
	}	
	
}
