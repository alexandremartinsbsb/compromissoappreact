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

    @Override
    public List<Usuario> getUsuarios() {
        return this.usuarioDAO.getUsuarios();
    }

    @Override
    public Usuario getUsuario(String pk) {
        return this.usuarioDAO.getUsuario(pk);
    }

    @Override
    public Usuario salvar(Usuario usuario) {
        return this.usuarioDAO.salvar(usuario);
    }

    @Override
    public Usuario atualizar(String pk, Usuario usuario) {
        return this.usuarioDAO.atualizar(pk, usuario);
    }

    @Override
    public void excluir(String pk) {
        this.usuarioDAO.excluir(pk);
    }

}
