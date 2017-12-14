package br.com.buch.sysChamados.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;

import org.hibernate.validator.constraints.Email;

@Entity
@Table(name="CAD_CLIENTE")
public class Cliente implements Serializable, BaseEntity{

	private static final long serialVersionUID = -5255327841647346787L;

	@Id
	@SequenceGenerator(name="G_CAD_CLIENTE", sequenceName="\"G_CAD_CLIENTE\"", allocationSize=1)  
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="G_CAD_CLIENTE")
    @Column(name = "COD_CADCLIENTE")
	private Long id;
	
	
	@Column(name = "CODIGO", unique = true)
	private Integer codigo;
	
	
	@Column(name = "DOCUMENTO" ,nullable = true , unique = true)
	private String documento;
	 
	
	@Column(name = "NOME" , length = 100)
	private String nome;
	
		 
    @Email(message="Informe um e-mail válido")
    @Column(name = "EMAIL" , length = 60)
    private String email;
       
    
    @Column(name = "TELEFONE1", length = 20)
    private String telefone1;
    
    
    @Column(name = "TELEFONE2", length = 20)
    private String telefone2;
    
		
	@Column(name = "OBS" , length = 255)
    private String obs;
    
    
    @Column(name = "ATIVO")
    private Character ativo;
    
     
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name ="COD_CADENDERECO")
    private Endereco endereco;
    
    
    @Column(name = "DATA_CADASTRO")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataCadastro;
    
    
	
    //--------------------------------	GETs and SETs------------------------------//
    
	
	@Override
	public Long getId() {return id;}

	@Override
	public void setId(Long id) {this.id = id;}

	
	public Integer getCodigo() {return codigo;}
	public void setCodigo(Integer codigo) {this.codigo = codigo;}

	
	public String getDocumento() {return documento;}
	public void setDocumento(String documento) {this.documento = documento;}

	
	public String getNome() {return nome;}
	public void setNome(String nome) {this.nome = nome;}

	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}

	
	public String getTelefone1() {return telefone1;}
	public void setTelefone1(String telefone1) {this.telefone1 = telefone1;}

	
	public String getTelefone2() {return telefone2;}
	public void setTelefone2(String telefone2) {this.telefone2 = telefone2;}

	
	public String getObs() {return obs;}
	public void setObs(String obs) {this.obs = obs;}

	
	public Character getAtivo() {return ativo;}
	public void setAtivo(Character ativo) {this.ativo = ativo;}

	
	public Endereco getEndereco() {return endereco;}
	public void setEndereco(Endereco endereco) {this.endereco = endereco;}

	
	public String getDataCadastroFormatada() {
		if(this.dataCadastro != null){
			return new SimpleDateFormat("dd/MM/yyyy").format(dataCadastro);
		}else{
			return null;
		}
	}
	public Date getDataCadastro() {return dataCadastro;}
	public void setDataCadastro(Date dataCadastro) {this.dataCadastro = dataCadastro;}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
	@Override
	public String toString() {return codigo + " - " + nome;}	
}
