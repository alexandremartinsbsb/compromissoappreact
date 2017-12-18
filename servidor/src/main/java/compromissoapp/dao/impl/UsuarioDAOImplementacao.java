package compromissoapp.dao.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import compromissoapp.dao.Firebase;
import compromissoapp.dao.UsuarioDAO;
import compromissoapp.modelo.FirebaseResponse;
import compromissoapp.modelo.Usuario;
import compromissoapp.util.error.FirebaseException;
import compromissoapp.util.error.UtilException;

public class UsuarioDAOImplementacao implements UsuarioDAO {

	private String nomeTabela = Usuario.class.getSimpleName().toLowerCase();

	private static final String	ID			= "_id";
	private static final String	NOME		= "nome";
	private static final String	EMAIL		= "email";
	private static final String	LOGIN		= "login";
	private static final String	SENHA		= "senha";
	private static final String	SITUACAO	= "situacao";

	public Usuario getUsuario(String pk) {
		Firebase firebase = null;
		try {
			firebase = new Firebase(this.nomeTabela);
		} catch (FirebaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		FirebaseResponse firebaseResponse = null;
		try {
			firebaseResponse = firebase.get(pk);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FirebaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (firebaseResponse != null) {
			return this.converteParaUsuario(firebaseResponse.getCorpo(), pk);
		} else {
			return null;
		}

	}

	public List<Usuario> getUsuarios() {

		Firebase firebase = null;
		try {
			firebase = new Firebase(this.nomeTabela);
		} catch (FirebaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		FirebaseResponse firebaseResponse = null;
		try {
			firebaseResponse = firebase.get();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FirebaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return this.converteParaListaUsuarios(firebaseResponse.getCorpo(), firebaseResponse.getCorpoBruto());
	}

	public Usuario salvar(Usuario usuario) {

		Firebase firebase = null;
		try {
			firebase = new Firebase(this.nomeTabela);
		} catch (FirebaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Map<String, Object> dataMap = new LinkedHashMap<String, Object>();
		dataMap.put(ID, usuario.getPk());
		dataMap.put(NOME, usuario.getNome());
		dataMap.put(EMAIL, usuario.getEmail());
		dataMap.put(LOGIN, usuario.getLogin());
		dataMap.put(SENHA, usuario.getSenha());
		dataMap.put(SITUACAO, usuario.getSituacao());

		try {
			firebase.post(dataMap);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UtilException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FirebaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return usuario;
	}

	public Usuario atualizar(String pk, Usuario usuario) {

		Firebase firebase = null;
		try {
			firebase = new Firebase(this.nomeTabela);
		} catch (FirebaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Map<String, Object> dataMap = new LinkedHashMap<String, Object>();
		dataMap.put(NOME, usuario.getNome());
		dataMap.put(EMAIL, usuario.getEmail());
		dataMap.put(LOGIN, usuario.getLogin());
		dataMap.put(SENHA, usuario.getSenha());
		dataMap.put(SITUACAO, usuario.getSituacao());

		try {
			firebase.put(pk, dataMap);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UtilException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FirebaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return usuario;
	}

	public void excluir(String pk) {

		Firebase firebase = null;
		try {
			firebase = new Firebase(this.nomeTabela);
		} catch (FirebaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			firebase.delete(pk);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FirebaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Usuario converteParaUsuario(Map<String, Object> corpo, String pk) {

		Usuario usuarioConvertido = new Usuario();

		usuarioConvertido.setPk(pk);
		usuarioConvertido.setNome(corpo.get(NOME).toString());
		usuarioConvertido.setEmail(corpo.get(EMAIL).toString());
		usuarioConvertido.setLogin(corpo.get(LOGIN).toString());
		usuarioConvertido.setSenha(corpo.get(SENHA).toString());
		usuarioConvertido.setSituacao(Boolean.valueOf(corpo.get(SITUACAO).toString()));

		return usuarioConvertido;

	}

	@SuppressWarnings("unchecked")
	private List<Usuario> converteParaListaUsuarios(Map<String, Object> corpo, String corpoBruto) {

		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		ObjectMapper mapper = new ObjectMapper();
		Object o;
		Map<String, Object> result = null;
		try {
			o = mapper.readValue(corpoBruto, Object.class);
			if (o instanceof Map) {
				result = (Map<String, Object>) o;

				for (Object key : result.keySet()) {
					Usuario usuario = new Usuario();

					usuario.setPk(key.toString());

					Map<String, Object> result2 = (Map<String, Object>) result.get(key);

					usuario.setNome(result2.get(NOME).toString());
					usuario.setEmail(result2.get(EMAIL).toString());
					usuario.setLogin(result2.get(LOGIN).toString());
					usuario.setSenha(result2.get(SENHA).toString());
					usuario.setSituacao(Boolean.parseBoolean(result2.get(SITUACAO).toString()));

					listaUsuarios.add(usuario);
				}

			}
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listaUsuarios;
	}

}