import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ProdutoPerecivelTest {
  static Produto produto;

  @BeforeAll
  static public void prepare() {
    produto = new ProdutoPerecivel("Produto teste perecível", 100, 0.1, LocalDate.now().plusDays(10));
  }

  @Test
  public void naoCriaProdutoComPrecoNegativo() {
    assertThrows(IllegalArgumentException.class, () -> new ProdutoPerecivel("teste", -5, 0.5, LocalDate.now().plusDays(10)));
  }

  @Test
  public void naoCriaProdutoComMargemNegativa() {
    assertThrows(IllegalArgumentException.class, () -> new ProdutoPerecivel("teste", 5, -1, LocalDate.now().plusDays(10)));
  }

  @Test
  public void naoCriaProdutoComValidadeInvalida() {
    assertThrows(IllegalArgumentException.class, () -> new ProdutoPerecivel("teste", 5, 0.5, LocalDate.now().minusDays(10)));
  }

  @Test
  public void stringComDescricaoEValor() {
    String desc = produto.toString();
    assertTrue(desc.contains("Produto teste") && desc.contains("R$ 110,00"));
  }

  @Test
  public void stringComDescricaoValorEValidade() {
    String desc = produto.toString();
    assertTrue(desc.contains("Produto teste") && desc.contains("R$ 110,00"));
  }

  @Test
  public void calculaPrecoCorretamente() {
    assertEquals(110.0, produto.valorDeVenda(), 0.01);
  }

  @Test
  public void calcularPrecoComDesconto() {
    Produto produto2 = new ProdutoPerecivel("Produto teste 2", 100, 0.1, LocalDate.now().plusDays(7));
    assertEquals(82.50, produto2.valorDeVenda(), 0.01);
  }
}

