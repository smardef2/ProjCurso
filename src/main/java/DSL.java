import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {
   private WebDriver driver;
   
   public DSL(WebDriver driver) {
	   this.driver = driver;
   }
   
   public void escreve(By by, String texto) {
	   //limpar antes de escrever, sen�o acumula entre duas escritas
	   driver.findElement(by).clear();
	   driver.findElement(by).sendKeys(texto);
   }
   
   public void escreve(String id_campo, String texto) {
	   //driver.findElement(By.id(id_campo)).sendKeys(texto);
	   escreve(By.id(id_campo),texto);   //aproveita metodo de cima
   }
   
   public String obterValorCampo(String id_campo) {
	   return driver.findElement(By.id(id_campo)).getAttribute("value");
   }
   
   public void clicarRadio(String id) {
	   driver.findElement(By.id(id)).click();
   }
   
   public boolean isRadioMarcado(String id) {
	  return driver.findElement(By.id(id)).isSelected();
   }
  
   /***** Combo  *****/
   public void selecionarCombo(String id, String valor) {
	   WebElement element = driver.findElement(By.id(id));
       Select combo = new Select(element);
        combo.selectByVisibleText(valor);
   }
   
   public void desselecionarCombo(String id, String valor) {
	   WebElement element = driver.findElement(By.id(id));
       Select combo = new Select(element);
        combo.deselectByVisibleText(valor);
   }
   public String obterValorCombo(String id) {
	   WebElement element = driver.findElement(By.id(id));
       Select combo = new Select(element);
        return combo.getFirstSelectedOption().getText();
   }
   
   public List<String> obterValoresCombo(String id) {
	   WebElement element = driver.findElement(By.id(id));
       Select combo = new Select(element);
       List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
       List<String> valores = new ArrayList<String>();
       for(WebElement opcao : allSelectedOptions) {
    	   valores.add(opcao.getText());
    	   
       }
       return valores;
   }
   
   public int obterQuantidadeOpcoesCombo(String id) {
	   WebElement element = driver.findElement(By.id(id));
       Select combo = new Select(element);
       List<WebElement> options = combo.getOptions();
       return options.size();
   }
   
   public boolean VerificarOpcaoCombo(String id, String opcao) {
	   WebElement element = driver.findElement(By.id(id));
       Select combo = new Select(element);
       List<WebElement> options = combo.getOptions();
       for(WebElement option : options) {
    	   if(option.getText().equals(opcao)) {
    		   return true;
    		   
    	   }
       }
       return false;
   }   
   
   /*** Bot�o  ***/
   public void clicarBotao(String id) {
	   driver.findElement(By.id(id)).click();
	   
   }
   
   //vale para qqr coisa, ober o value do obj
   public String obterValueElemento(String id) {
	  return driver.findElement(By.id(id)).getAttribute("value");
	   
   }
   
   /*** Link ****/
   public void clicarLink(String link) {
	   driver.findElement(By.linkText(link)).click();
   }
//   public String obterTexto(String id) {
//	   return driver.findElement(By.id(id)).getText();
   //}
   
   //By � para localizar elemento: By,id, By.tagnmae - obter texto pelo id, mas tem hora q qr obter pelo tagName, ent�o o tipo como localizar
   //� que ser� o parametro do metodo   
   public String obterTexto(By by) {
	   //return driver.findElement(By.id(id)).getText();
	   return driver.findElement(by).getText();
   }
   
   public String obterTexto(String id) {
	   //return driver.findElement(By.id(id)).getText();
	   return obterTexto(By.id(id));
   }   
   
   /*** Alertas ***/
   public String alertaObterTexto() {
	   Alert alert = driver.switchTo().alert();
	   return alert.getText();
   }
   
   public String alertaObterTextoAceito() {
	   Alert alert = driver.switchTo().alert();
	   String valor = alert.getText();
	   alert.accept();
	   return valor;
   }
   
   public String alertaObterTextoENega() {
	   Alert alert = driver.switchTo().alert();
	   String valor = alert.getText();
	   alert.dismiss();
	   return valor;
   }
   
   public void alertaEscreverTexto(String texto) {
	   Alert alert = driver.switchTo().alert();
	   alert.sendKeys(texto);   //enviar um numero. 
       alert.accept(); 
   }
   
   /** Frames e janelas ***/
   public void entrarFrame(String id) {
	   driver.switchTo().frame(id);
   }
   
   public void sairFrame() {
	   driver.switchTo().defaultContent();
   }
   
   public void trocarJanela(String id) {
	   driver.switchTo().window(id);
   }
   
   /************ JS ***********************/
   public Object executarJS(String cmd, Object... param) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript(cmd, param);
   }
   
   /************ Tabela ***********************/
   //metodo antes de refatorar   
   /***********************************   
   public void clicarBotaoTabela(String colunaBusca, String valor, String colunaBotao, String x) {
	   //procurar coluna do registro, e depois varre coluna atr�s do registro
	   //procura o elem tabela e dentro dele � q vai buscar as outra coisas
	   WebElement tabela = driver.findElement(By.xpath("//*[@id='elementosForm:tableUsuarios']"));
	   //se deixar apenas //th vai procurar a partir do inicio do docto, para procurar relativo por pto
	   // na frente. (dois ptos) ".." leva diretorio superior, "." indica diretorio corrente
	   //procurar o titulo, pega findElementS q traz todos registros de acordo com a regra
	   //'.' ou ponto, indica do pto onde estou procure por th em qqr nivel
	   tabela.findElements(By.xpath(".//th"));
	   List<WebElement> colunas = tabela.findElements(By.xpath("th"));  //procura por varios elemtos, n�o s� o primeiro q bate com a regra
	   int idColuna = -1;   // = obterIndiceColuna(colunaBusca, tabela);
	   for(int i=0; i < colunas.size(); i++) {
		   if(colunas.get(i).getText().equals(colunaBusca)) {
			   idColuna = i+1;   //colunas come�a do zero, mas XPath come�a do 1, por isso adicionar 1
			   break;
		   }
	   }
	   //encontrar a linha do registro
	   
	   //procurar coluna do bot�o
	   
	   //clicar no botao da celula encontrada
	   
   }   
***************************************************************************/   
   
   
   public void clicarBotaoTabela(String colunaBusca, String valor, String colunaBotao, String x) {
	   //procurar coluna do registro, e depois varre coluna atr�s do registro
	   //procura o elem tabela e dentro dele � q vai buscar as outra coisas
	   //para xpath use o try xpath para ver se com o xpath realmente encontra um elemento 
	   //v� no browser, abra a pag html, e use o try xpath, colocando o comando abaixo e ve q acha tabela
	   WebElement tabela = driver.findElement(By.xpath("//*[@id='elementosForm:tableUsuarios']"));

	   int idColuna = obterIndiceColuna(colunaBusca, tabela);
	   
	   //encontrar a linha do registro
	   //ver qual xpath verifica a primeira coluna: .//*[@id='elementosForm:tableUsuarios']//tr/td[1]
	   //apesar q s� vai usar uma parte do xpath 
	   int idLinha = obterIndiceLinha(valor, tabela, idColuna);

	   
	   //procurar coluna do bot�o. essa celula contem um bot�o (para linha 'Maria' tem um bot�o nessa linha q 
	   //qr clicar. obter o bot�o para clicar nele
	   int idColunaBotao = obterIndiceColuna(colunaBotao, tabela);
	   
	   //clicar no botao da celula encontrada
	   //verificar pelo xpath: .//*[@id='elementosForm:tableUsuarios']//tr[2]/td[3]  
	   //j� sabe que linha da Maria � a 2, e 3 � o coluna do bot�o nesse caso. mas para pgm n�o pode ser fixo

	   WebElement celula = tabela.findElement(By.xpath(".//tr["+idLinha+"]/td["+idColunaBotao+"]"));
	   //pto pq busca � na celula. pode por input pq bot�o � input, e input tem tabela tb n�o s� radio, ou estar dentro de uma div, etc
	   //ent�o deixe //input
	   celula.findElement(By.xpath(".//input")).click();
	   
   }

   //se n�o encontrar, se tivesse mais dados, poderia colocar a a��o de clicar para prox p�gina para fazer
   //nova pesquisa, ou scroll caso fosse necess�rio paginar, at� achar a �ltima pagina e se n�o encontra
   //retorna -1. mas este caso � simples e por isso n�o precisa
protected int obterIndiceLinha(String valor, WebElement tabela, int idColuna) {
	List<WebElement> linhas = tabela.findElements(By.xpath("./tbody/tr/td["+idColuna+"]"));
	   int idLinha = -1;
	   for(int i=0; i < linhas.size(); i++) {
		   if(linhas.get(i).getText().equals(valor)) {
			   idLinha = i+1;   //linhas come�a do zero, mas XPath come�a do 1, por isso adicionar 1
			   break;
		   }
	   }
	   return idLinha;
}

  private int obterIndiceColuna(String colunaBusca, WebElement tabela) {
	List<WebElement> colunas = tabela.findElements(By.xpath(".//th"));
	   int idColuna=-1;
	   for(int i=0; i < colunas.size(); i++) {
		   if(colunas.get(i).getText().equals(colunaBusca)) {
			   idColuna = i+1;
			   break;
		   }
	   }
	   return idColuna;
  }
}
