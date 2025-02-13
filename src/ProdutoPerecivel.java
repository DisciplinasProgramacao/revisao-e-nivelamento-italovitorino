import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ProdutoPerecivel extends Produto{
  
  private static double DESCONTO = 0.25;
  private static int PRAZO_DESCONTO = 7;
  private LocalDate dataDeValidade;
  
  public ProdutoPerecivel(String desc, double precoCusto, double margemLucro, LocalDate validade) {  
    if (validade.isBefore(LocalDate.now())) {
      throw new IllegalArgumentException("Validade anterior à data atual");
    }

    super(desc, precoCusto, margemLucro);
    dataDeValidade = validade;
  }

  @Override
  public double valorDeVenda() {
    if (dataDeValidade.isBefore(LocalDate.now())) {
      throw new IllegalArgumentException("Produto vencido");
    }

    if (LocalDate.now().until(dataDeValidade).getDays() <= PRAZO_DESCONTO) {
      return precoCusto * (1+margemLucro) * (1 - DESCONTO);
    }

    return precoCusto * (1+margemLucro);
  }

  @Override
  public String toString() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String dados = super.toString();
    dados += String.format("\nVálidade: %s", formatter.format(dataDeValidade));
    return dados;
  }
}
