package br.unibh.sdm.revisao1.negocio;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import br.unibh.sdm.revisao1.entidades.Compra;
import br.unibh.sdm.revisao1.entidades.ItemCompra;
import br.unibh.sdm.revisao1.entidades.PessoaFisica;
import br.unibh.sdm.revisao1.entidades.PessoaJuridica;

public class CaixaImpl implements Caixa {

	@Override
	public Compra registar(Set<ItemCompra> itens, PessoaFisica cliente, PessoaJuridica loja, BigDecimal frete, BigDecimal desconto) {
		Compra c = new Compra();
		c.setCliente(cliente);
		c.setLoja(loja);
		c.setValorFrete(frete);
		c.setDesconto(desconto);
		c.setValorTotal(new BigDecimal(0.0));
		for (ItemCompra item: itens) {
			System.out.println(item.getProduto().getPreco().multiply(new BigDecimal(item.getQuantidade())));
			c.setValorTotal(c.getValorTotal().add(item.getProduto().getPreco().multiply(new BigDecimal(item.getQuantidade()))));
		}
		c.setValorFinal(c.getValorTotal().add(frete).subtract(desconto));
		c.setData(new Date());
		return c;
	}

	@Override
	public Compra cancelar(Compra compra) {
		compra.setId(null);
		compra.setDesconto(null);
		compra.setCliente(null);
		compra.setValorFinal(null);
		compra.setValorFrete(null);
		compra.setValorFrete(null);
		return compra;
	}

}
