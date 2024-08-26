package br.com.sistemacadastro.model;

import javax.persistence.*;

import br.com.sistemacadastro.utils.Model;

import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.sistemacadastro.utils.LocalDateJsonAdapter;


@Entity
public class Pedido implements Model<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
	
    @Column(name = "dataPedido")
    private LocalDate dataPedido = LocalDate.now();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;


    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setDataPedido(LocalDate dataPedido) { 
        System.out.println("Setando data do pedido: " + dataPedido);
        this.dataPedido = dataPedido; 
    }
    
    @XmlJavaTypeAdapter(LocalDateJsonAdapter.class)
    public LocalDate getDataPedido() {
        System.out.println("Obtendo data do pedido: " + dataPedido);
        return dataPedido;
    }
    
}

