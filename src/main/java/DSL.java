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
	   //limpar antes de escrever, senão acumula entre duas escritas
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
   
   /*** Botão  ***/
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
   
   //By é para localizar elemento: By,id, By.tagnmae - obter texto pelo id, mas tem hora q qr obter pelo tagName, então o tipo como localizar
   //é que será o parametro do metodo   
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
	   //procurar coluna do registro, e depois varre coluna atrás do registro
	   //procura o elem tabela e dentro dele é q vai buscar as outra coisas
	   WebElement tabela = driver.findElement(By.xpath("//*[@id='elementosForm:tableUsuarios']"));
	   //se deixar apenas //th vai procurar a partir do inicio do docto, para procurar relativo por pto
	   // na frente. (dois ptos) ".." leva diretorio superior, "." indica diretorio corrente
	   //procurar o titulo, pega findElementS q traz todos registros de acordo com a regra
	   //'.' ou ponto, indica do pto onde estou procure por th em qqr nivel
	   tabela.findElements(By.xpath(".//th"));
	   List<WebElement> colunas = tabela.findElements(By.xpath("th"));  //procura por varios elemtos, não só o primeiro q bate com a regra
	   int idColuna = -1;   // = obterIndiceColuna(colunaBusca, tabela);
	   for(int i=0; i < colunas.size(); i++) {
		   if(colunas.get(i).getText().equals(colunaBusca)) {
			   idColuna = i+1;   //colunas começa do zero, mas XPath começa do 1, por isso adicionar 1
			   break;
		   }
	   }
	   //encontrar a linha do registro
	   
	   //procurar coluna do botão
	   
	   //clicar no botao da celula encontrada
	   
   }   
***************************************************************************/   
   
   
   public void clicarBotaoTabela(String colunaBusca, String valor, String colunaBotao, String x) {
	   //procurar coluna do registro, e depois varre coluna atrás do registro
	   //procura o elem tabela e dentro dele é q vai buscar as outra coisas
	   //para xpath use o try xpath para ver se com o xpath realmente encontra um elemento 
	   //vá no browser, abra a pag html, e use o try xpath, colocando o comando abaixo e ve q acha tabela
	   WebElement tabela = driver.findElement(By.xpath("//*[@id='elementosForm:tableUsuarios']"));

	   int idColuna = obterIndiceColuna(colunaBusca, tabela);
	   
	   //encontrar a linha do registro
	   //ver qual xpath verifica a primeira coluna: .//*[@id='elementosForm:tableUsuarios']//tr/td[1]
	   //apesar q só vai usar uma parte do xpath 
	   int idLinha = obterIndiceLinha(valor, tabela, idColuna);

	   
	   //procurar coluna do botão. essa celula contem um botão (para linha 'Maria' tem um botão nessa linha q 
	   //qr clicar. obter o botão para clicar nele
	   int idColunaBotao = obterIndiceColuna(colunaBotao, tabela);
	   
	   //clicar no botao da celula encontrada
	   //verificar pelo xpath: .//*[@id='elementosForm:tableUsuarios']//tr[2]/td[3]  
	   //já sabe que linha da Maria é a 2, e 3 é o coluna do botão nesse caso. mas para pgm não pode ser fixo

	   WebElement celula = tabela.findElement(By.xpath(".//tr["+idLinha+"]/td["+idColunaBotao+"]"));
	   //pto pq busca é na celula. pode por input pq botão é input, e input tem tabela tb não só radio, ou estar dentro de uma div, etc
	   //então deixe //input
	   celula.findElement(By.xpath(".//input")).click();
	   
   }

   //se não encontrar, se tivesse mais dados, poderia colocar a ação de clicar para prox página para fazer
   //nova pesquisa, ou scroll caso fosse necessário paginar, até achar a última pagina e se não encontra
   //retorna -1. mas este caso é simples e por isso não precisa
protected int obterIndiceLinha(String valor, WebElement tabela, int idColuna) {
	List<WebElement> linhas = tabela.findElements(By.xpath("./tbody/tr/td["+idColuna+"]"));
	   int idLinha = -1;
	   for(int i=0; i < linhas.size(); i++) {
		   if(linhas.get(i).getText().equals(valor)) {
			   idLinha = i+1;   //linhas começa do zero, mas XPath começa do 1, por isso adicionar 1
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
