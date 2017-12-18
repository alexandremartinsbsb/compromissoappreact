package compromissoapp.fachada.rs;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import compromissoapp.modelo.Usuario;
import compromissoapp.servico.UsuarioServico;

@RequestScoped
@Path("/usuarios")
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioFachadaRest {

	@Inject
	private UsuarioServico usuarioServico;

	@GET
	public List<Usuario> getUsuarios() {
		return this.usuarioServico.getUsuarios();
	}

	@GET
	@Path("/{pk}")
	public Usuario getUsuario(@PathParam("pk") String pk) {
		return this.usuarioServico.getUsuario(pk);
	}

	@POST
	public Usuario salvar(Usuario usuario) {
		return this.usuarioServico.salvar(usuario);
	}

	@PUT
	@Path("/{pk}")
	public Usuario atualizar(@PathParam("pk") String pk, Usuario usuario) {
		return this.usuarioServico.atualizar(pk, usuario);
	}

	@DELETE
	@Path("/{pk}")
	public void excluir(@PathParam("pk") String pk) {
		this.usuarioServico.excluir(pk);
	}
}