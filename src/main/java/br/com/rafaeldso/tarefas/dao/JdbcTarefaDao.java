package br.com.rafaeldso.tarefas.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

import br.com.rafaeldso.tarefas.jdbc.ConnectionFactory;
import br.com.rafaeldso.tarefas.modelo.Tarefa;

public class JdbcTarefaDao {
	private Connection connection;
	/**
	public JdbcTarefaDao(HttpServletRequest request, HttpServletResponse response) {
		this.connection = (Connection) request.getAttribute("conexao");
	}
	**/
	public JdbcTarefaDao() {
		this.connection = new ConnectionFactory().getConnection();
	}
	public void adiciona(Tarefa tarefa) {
		try {
			String sql = "insert into tarefas (descricao, finalizado, dataFinalizacao) values (?,?,?)";
			PreparedStatement stmt = connection.prepareStatement(sql);

			//stmt.setLong(1, tarefa.getId());
			stmt.setString(1, tarefa.getDescricao());
			stmt.setBoolean(2, tarefa.isFinalizado());
			stmt.setDate(3, new Date(tarefa.getDataFinalizacao().getTimeInMillis()));

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Tarefa> getLista() {
		try {
			List<Tarefa> tarefas = new ArrayList<Tarefa>();
			PreparedStatement stmt = this.connection.prepareStatement("select * from tarefas");

			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				Tarefa tarefa = new Tarefa();
				//popula o objeto contato
				tarefa.setId(rs.getLong("id"));
				tarefa.setDescricao(rs.getString("descricao"));
				tarefa.setFinalizado(rs.getBoolean("finalizado"));
				
				//popula a data de nascimento do contato, fazendo a conversao
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataFinalizacao"));
				tarefa.setDataFinalizacao(data);

				//adiciona o contato na lista
				tarefas.add(tarefa);
			}

			rs.close();
			stmt.close();

			return tarefas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void exclui(Tarefa tarefa) {
		String sql = "delete from tarefas where id=?";
		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setLong(1, tarefa.getId());
			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void atualiza(Tarefa tarefa) {
		String sql = "update tarefas set descricao = ?, finalizado = ?, dataNascimento = ? where id = ?";
		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, tarefa.getDescricao());
			stmt.setBoolean(2, tarefa.isFinalizado());
			stmt.setDate(3, new java.sql.Date(tarefa.getDataFinalizacao().getTimeInMillis()));
			stmt.setLong(4, tarefa.getId());

			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
