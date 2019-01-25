import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

//suite de testes - para executar um conj de classes de testes e definir ordem de chamada
//a classe terá um runner diferente para fazer a suite de testes
//runner do org.junit.runners
// SuiteClasses - indicar tb quais classes deverão ser executadas na suite
//Qdo executar, na aba JUNit de logs, dará para ver Suite e as classes q executaram dentro dessa suite
//Não dá para definir a ordem q os metodos são executados dentro do junit, mas dá para definir a ordem q as classes de  testes serão executadas

@RunWith(Suite.class)
@SuiteClasses({
	TesteCadastro.class,
	TesteRegraCadastro.class,
	TesteCampoTreinamento.class
}
		)
public class SuiteTeste {

}
