package compromissoapp.servico;

import java.util.List;

import compromissoapp.modelo.Usuario;

public interface UsuarioServico {

	List<Usuario> getUsuarios();

	Usuario getUsuario(String pk);

	Usuario salvar(Usuario usuario);

	Usuario atualizar(String pk, Usuario usuario);

	void excluir(String pk);
}
