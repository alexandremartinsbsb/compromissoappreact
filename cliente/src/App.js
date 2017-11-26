import React, { Component } from 'react';
import './css/pure-min.css';
import './css/side-menu.css';
import './lib/font-awesome/css/font-awesome.min.css';
import $ from 'jquery';

class App extends Component {

  constructor() {
    super();
    this.state = { listaUsuario: [] };
  }

  componentDidMount() {
    $.ajax({
      url: "http://localhost:8080/compromissoapp-0.0.1-SNAPSHOT/rs/usuarios",
      dataType: 'json',
      success: function (usuarios) {
        this.setState({ listaUsuario: usuarios });
      }.bind(this)
    });
  }

  render() {
    return (
      <div id="layout">
        <a href="#menu" id="menuLink" className="menu-link">
          <span></span>
        </a>

        <div id="menu">
          <div className="pure-menu">
            <a className="pure-menu-heading" href="#">CompromissoAPP</a>
            <ul className="pure-menu-list">
              <li className="pure-menu-item" className="menu-item-divided pure-menu-selected">
                <a href="#" className="pure-menu-link">
                  <i className="fa fa-home" aria-hidden="true"></i> Home
                </a>
              </li>
              <li className="pure-menu-item">
                <a href="#" className="pure-menu-link">
                  <i className="fa fa-calendar" aria-hidden="true"></i> Agenda
                </a>
              </li>
              <li className="pure-menu-item">
                <a href="#" className="pure-menu-link">
                  <i className="fa fa-wrench" aria-hidden="true"></i> Serviço
                </a>
              </li>
              <li className="pure-menu-item pure-menu-has-children">
                <a href="#" id="menuLink1" className="pure-menu-link">Cadastro</a>
                <ul className="pure-menu-children">
                  <li className="pure-menu-item">
                    <a href="#" className="pure-menu-link">
                      <i className="fa fa-user-circle" aria-hidden="true"></i> Usuario
                    </a>
                  </li>
                  <li className="pure-menu-item">
                    <a href="#" className="pure-menu-link">
                      <i className="fa fa-product-hunt" aria-hidden="true"></i> Produto
                    </a>
                  </li>
                </ul>
              </li>
            </ul>
          </div>
        </div>

        <div id="main">
          <div className="header">
            <h1>Listagem de usuarios</h1>
            <h2>Apresenta a listagem de todos usuarios cadastrados no sistema</h2>
          </div>

          <div className="content" id="content">
            <div className="pure-form pure-form-aligned content-subhead">

              <form className="pure-form pure-form-aligned">
                <fieldset>
                  <div className="pure-control-group">
                    <label htmlFor="nome">Nome</label>
                    <input id="nome" type="text" placeholder="Nome" />
                    <span className="pure-form-message-inline">É necessario informar o nome completo.</span>
                  </div>
                  <div className="pure-control-group">
                    <label htmlFor="email">Email</label>
                    <input id="email" type="text" placeholder="teste@email.com.br" />
                    <span className="pure-form-message-inline">É necessario informar o email.</span>
                  </div>
                  <div className="pure-control-group">
                    <label htmlFor="login">Login</label>
                    <input id="login" type="text" placeholder="login.teste" />
                    <span className="pure-form-message-inline">É necessario informar o login.</span>
                  </div>
                  <div className="pure-control-group">
                    <label htmlFor="email">Email</label>
                    <input id="email" type="text" placeholder="teste@email.com.br" />
                    <span className="pure-form-message-inline">É necessario informar o email.</span>
                  </div>
                  <div className="pure-controls">
                    <label htmlFor="situacao" className="pure-checkbox">
                      <input id="situacao" type="checkbox"/> Situação
                    </label>

                    <button type="submit" className="pure-button button-success">
                      <i className="fa fa-check" aria-hidden="true"></i> Salvar
                    </button>
                  </div>
                </fieldset>
              </form>

              <table className="pure-table">
                <thead>
                  <tr>
                    <th>Nome</th>
                    <th>Email</th>
                    <th>Login</th>
                    <th>Situação</th>
                    <th>Ações</th>
                  </tr>
                </thead>

                <tbody>
                  {
                    this.state.listaUsuario.map(function (usuario) {
                      return (
                        <tr key={usuario.pk} className="pure-table-odd">
                          <td>{usuario.nome}</td>
                          <td>{usuario.email}</td>
                          <td>{usuario.login}</td>
                          <td>
                            <button className="button-success pure-button-disabled">
                              <i className="fa fa-check" aria-hidden="true"></i> {usuario.situacao}
                            </button>
                            <button className="button-error pure-button-disabled">
                              <i className="fa fa-ban" aria-hidden="true"></i> {usuario.situacao}
                            </button>
                          </td>
                          <td>
                            <a className="button-secondary pure-button botao-acoes" href="#">
                              <i className="fa fa-search" aria-hidden="true"></i>
                            </a>
                            <a className="button-warning pure-button botao-acoes" href="#">
                              <i className="fa fa-pencil" aria-hidden="true"></i>
                            </a>
                            <button className="button-error pure-button botao-acoes">
                              <i className="fa fa-trash" aria-hidden="true"></i>
                            </button>
                          </td>
                        </tr>
                      );
                    })
                  }
                </tbody>
              </table>
              <a className="pure-button pure-button-primary botao-novo" href="#">
                <i className="fa fa-plus" aria-hidden="true"></i> Cadastrar
              </a>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default App;
