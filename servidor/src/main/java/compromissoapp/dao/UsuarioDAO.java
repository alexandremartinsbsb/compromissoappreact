package compromissoapp.dao;

import java.util.List;

import compromissoapp.modelo.Usuario;

public interface UsuarioDAO {

	Usuario getUsuario(String pk);

	List<Usuario> getUsuarios();

	Usuario salvar(Usuario usuario);

	Usuario atualizar(String pk, Usuario usuario);

	void excluir(String pk);

}
