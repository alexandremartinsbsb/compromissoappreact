package compromissoapp.servico.impl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import compromissoapp.dao.UsuarioDAO;
import compromissoapp.modelo.Usuario;
import compromissoapp.servico.UsuarioServico;

public class UsuarioServicoImplementacao implements Serializable, UsuarioServico {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioDAO usuarioDAO;

	public List<Usuario> getUsuarios() {
		return this.usuarioDAO.getUsuarios();
	}

	public Usuario getUsuario(String pk) {
		return this.usuarioDAO.getUsuario(pk);
	}

	public Usuario salvar(Usuario usuario) {
		return this.usuarioDAO.salvar(usuario);
	}

	public Usuario atualizar(String pk, Usuario usuario) {
		return this.usuarioDAO.atualizar(pk, usuario);
	}

	public void excluir(String pk) {
		this.usuarioDAO.excluir(pk);
	}

}
