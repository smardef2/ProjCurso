import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class TesteGoogle {
	//public static void main(String[] args)
	
	@Test
	public void teste() {
		//System.setProperty("webdriver.gecko.driver","E:\\Utils\\selenim_aux\\Gecko\\geckodriver.exe");  //põe caminho do gecko driver se não estivesse no path
		//se não estivesse no path deveria dar o caminho do driver
		//System.setProperty("webdriver.chrome.driver","E:\\Utils\\selenim_aux\\ChromeDriver\\chromedriver.exe");  //põe caminho do gecko driver se não estivesse no path
		  
		   WebDriver driver = new FirefoxDriver();
		    //WebDriver driver = new ChromeDriver();
		//WebDriver driver = new InternetExplorerDriver();
		driver.manage().window().setPosition(new Point(100,100)); //iniciar browser de um det pto, não funciona com firefox
		driver.manage().window().setSize(new Dimension(1200,765));   //largura e altura
		// para browser iniciar maximizado
		//driver.manage().window().maximize();
		   //pede para driver acessar o  google
		    driver.get("http://www.google.com");
		    //checar titulo
		   //System.out.println(driver.getTitle());  //pede para driver dar titulo da pag do google
		   Assert.assertEquals("Google", driver.getTitle());
		   driver.quit();    //fecha tudo e termina instancia do browser, matar os processos do gecko q estão execuçã
		  // driver.close(); //só fecha uma aba do driver


	}

}
