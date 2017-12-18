package br.com.buch.sysChamados.service;

import java.util.List;

import br.com.buch.sysChamados.dao.GenericDao;
import br.com.buch.sysChamados.entity.Usuario;
import br.com.buch.sysChamados.util.Criptografia;
import br.com.buch.sysChamados.util.NegocioException;
import br.com.buch.sysChamados.util.PersistenciaException;
import br.com.buch.sysChamados.util.UtilErros;

public class UsuarioService implements GenericService<Usuario> {

	public static final String BUSCAR_PELO_NOME = "select u from Usuario u where u.nomeUsuario = ?1";

	public static final String BUSCAR_PELO_USUARIO_SENHA = "select u from Usuario u where u.nomeUsuario = ?1 and u.senha = ?2";

	public static final String BUSCAR_SETORES = "Select distinct u.setor From Usuario u";

	public static final String BUSCAR_TODOS = "Select u From Usuario u order by u.ativo, u.nomeUsuario";

	public static final String FILTRAR_POR_CODIGO = "Select u From Usuario u where u.idUsusario = ?1";

	public static final String FILTRAR_POR_NOME_USER = "Select u From Usuario u where u.nomeUsuario like ?";

	
	
	@Override
	public String salvar(Usuario entidate) throws Exception {
		if (entidate.getId() == null) {

			try {
				entidate.setSenha(Criptografia.criptografarSha256(entidate.getSenha()));
				GenericDao.save(entidate);
				return "Usuário Cadastrado com Sucesso!";
			} catch (Exception e) {
				e.printStackTrace();
				throw new PersistenciaException(
						"Ocorreu uma exceção ao Alterar o Usuário!" + " \nErro: " + UtilErros.getMensagemErro(e));
			}
		} else {

			if (entidate.getSenha().length() <= 40) {
				entidate.setSenha(Criptografia.criptografarSha256(entidate.getSenha()));
			}

			try {
				GenericDao.update(entidate);
				return "Usuário Alterado com Sucesso!";
			} catch (Exception e) {
				e.printStackTrace();
				throw new PersistenciaException(
						"Ocorreu uma exceção ao Alterar o Usuário!" + " \nErro: " + UtilErros.getMensagemErro(e));
			}
		}
	}

	@Override
	public String excluir(Usuario entidade) throws Exception {
		try {
			GenericDao.delete(entidade);
			return "";
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new PersistenciaException(
					"Ocorreu uma exceção ao excluir o Usuário!" + " \nErro: " + UtilErros.getMensagemErro(ex));
		}
	}

	public Usuario carregarEntidade(Usuario usuario) throws PersistenciaException {
		try {
			return GenericDao.findById(Usuario.class, usuario.getId());
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(
					"Ocorreu uma exceção ao buscar os dados do Usuário!" + " \nErro: " + UtilErros.getMensagemErro(e));
		}
	}

	public List<Usuario> buscarTodos() throws PersistenciaException {
		try {

			return GenericDao.findAll(Usuario.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void consisteAntesEditar(Usuario entidade) throws NegocioException {
	}

	public Usuario fazerLogin(String login, String senha) throws Exception {
		Usuario usuario = null;

		try {
			if (login == null || senha == null) {
				throw new NegocioException("Informe o Usuário e senha!");
			}
			usuario = GenericDao.findOne(BUSCAR_PELO_USUARIO_SENHA, login, Criptografia.criptografarSha256(senha));
		}catch(javax.persistence.NoResultException ex){
			throw new NegocioException("Usuário ou Senha informados estão incorretos!");
		}
		catch (Exception e) {
			throw e;
		}		

		return usuario;
	}

	public Usuario buscarPeloNome(Usuario usuario) {
		try {
			return GenericDao.findOne(BUSCAR_PELO_NOME, usuario.getNomeUsuario().toUpperCase());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<String> buscarSetores() throws Exception {
		return GenericDao.find(BUSCAR_SETORES);
	}

	public Usuario buscarPorId(Long id) {
		try {
			return GenericDao.findById(Usuario.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
