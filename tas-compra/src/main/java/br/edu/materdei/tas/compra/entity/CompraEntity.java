package br.edu.materdei.tas.compra.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "compra")
public class CompraEntity {
    @Id @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id;
    
    @Column(length = 6, nullable = false)
    private String codigo;
    
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dtcompra;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private FornecedorEntity fornecedor;
    
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ItemCompraEntity> itens;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the dtcompra
     */
    public Date getDtcompra() {
        return dtcompra;
    }

    /**
     * @param dtcompra the dtcompra to set
     */
    public void setDtcompra(Date dtcompra) {
        this.dtcompra = dtcompra;
    }

    /**
     * @return the fornecedor
     */
    public FornecedorEntity getFornecedor() {
        return fornecedor;
    }

    /**
     * @param fornecedor the fornecedor to set
     */
    public void setFornecedor(FornecedorEntity fornecedor) {
        this.fornecedor = fornecedor;
    }

    /**
     * @return the itens
     */
    public List<ItemCompraEntity> getItens() {
        return itens;
    }

    /**
     * @param itens the itens to set
     */
    public void setItens(List<ItemCompraEntity> itens) {
        this.itens = itens;
    }
}
