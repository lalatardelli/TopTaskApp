package br.com.android.cotuca.toptask.DAO;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.com.android.cotuca.toptask.BD.ContratoTarefas;
import br.com.android.cotuca.toptask.BD.DBHelper;
import br.com.android.cotuca.toptask.Beans.Tarefa;
import br.com.android.cotuca.toptask.tags.Tags;

public class TarefaDAO {

	private static TarefaDAO instancia;
	private DBHelper dbHelper;
	private SQLiteDatabase db;
	private String[] colunas = new String[] { 
			ContratoTarefas.Colunas._ID,
			ContratoTarefas.Colunas.NOME,
			ContratoTarefas.Colunas.DESCRICAO,
			ContratoTarefas.Colunas.DONO,
			ContratoTarefas.Colunas.DATA_ENTREGA,
			ContratoTarefas.Colunas.PRIORIDADE,
			ContratoTarefas.Colunas.PROJETO, 
			ContratoTarefas.Colunas.CONCLUIDA};

	public static TarefaDAO getInstance(Context contexto) {
		if (instancia == null) {
			instancia = new TarefaDAO(contexto.getApplicationContext());
		}
		return instancia;
	}

	private TarefaDAO(Context contexto) {
		dbHelper = DBHelper.getInstance(contexto);
		db = dbHelper.getWritableDatabase();
	}


	public List<Tarefa> getTarefas() {


		Cursor c = db.query(ContratoTarefas.NOME_TABELA,colunas, null, null,
				null, null, ContratoTarefas.Colunas.DATA_ENTREGA);

		List<Tarefa> tarefas = new ArrayList<Tarefa>();

		try {
			if (c.moveToFirst()) {
				do {
					Tarefa t = TarefaDAO.getCursor(c);
					tarefas.add(t);
				} while (c.moveToNext());
			}

		} finally {
			c.close();
		}

		return tarefas;
	}
	
	public List<Tarefa> getTarefasProjeto(int idProjeto){
		Cursor c = db.query(ContratoTarefas.NOME_TABELA,colunas, 
				ContratoTarefas.Colunas.PROJETO + " = ? ", new String[] {String.valueOf(idProjeto)},
				null, null, ContratoTarefas.Colunas.DATA_ENTREGA);

		List<Tarefa> tarefas = new ArrayList<Tarefa>();

		try {
			if (c.moveToFirst()) {
				do {
					Tarefa t = TarefaDAO.getCursor(c);
					tarefas.add(t);
				} while (c.moveToNext());
			}

		} finally {
			c.close();
		}

		return tarefas;

 
	}
	
	public List<Tarefa> getTarefasDoUsuarioNoProjetos(int idProjeto, int idDono){
		Cursor c = db.query(ContratoTarefas.NOME_TABELA,colunas, 
				ContratoTarefas.Colunas.PROJETO + " = ? and " +  ContratoTarefas.Colunas.DONO + " = ?", new String[] {String.valueOf(idProjeto),String.valueOf(idDono)},
				null, null, ContratoTarefas.Colunas.DATA_ENTREGA);

		List<Tarefa> tarefas = new ArrayList<Tarefa>();

		try {
			if (c.moveToFirst()) {
				do {
					Tarefa t = TarefaDAO.getCursor(c);
					tarefas.add(t);
				} while (c.moveToNext());
			}

		} finally {
			c.close();
		}

		return tarefas;

 
	}
	
	public List<Tarefa> getNaoConcluidas(){
		Cursor c = db.query(ContratoTarefas.NOME_TABELA,colunas, 
				ContratoTarefas.Colunas.CONCLUIDA + " = ? ", new String[] {String.valueOf(0)},
				null, null, ContratoTarefas.Colunas.DATA_ENTREGA);

		List<Tarefa> tarefas = new ArrayList<Tarefa>();

		try {
			if (c.moveToFirst()) {
				do {
					Tarefa t = TarefaDAO.getCursor(c);
					tarefas.add(t);
				} while (c.moveToNext());
			}

		} finally {
			c.close();
		}

		return tarefas;

 
	}
	
	
	//Nao concluido tarefa com Coluna.Concluida <> 2
	public List<Tarefa> getNaoConcluidasDoProjeto(int idProjeto){
		Cursor c = db.query(ContratoTarefas.NOME_TABELA,colunas, 
				ContratoTarefas.Colunas.CONCLUIDA + " = ? and " + ContratoTarefas.Colunas.PROJETO + " = ?", new String[] {String.valueOf(0),String.valueOf(idProjeto)},
				null, null, ContratoTarefas.Colunas.DATA_ENTREGA);
		
//		Cursor c = db.query(ContratoTarefas.NOME_TABELA,colunas, 
//				ContratoTarefas.Colunas.CONCLUIDA + " = ? ", new String[] {String.valueOf(0)},
//				null, null, ContratoTarefas.Colunas.DATA_ENTREGA);

		List<Tarefa> tarefas = new ArrayList<Tarefa>();

		try {
			if (c.moveToFirst()) {
				do {
					Tarefa t = TarefaDAO.getCursor(c);
					tarefas.add(t);
				} while (c.moveToNext());
			}

		} finally {
			c.close();
		}

		return tarefas;
	}
	
	public List<Tarefa> getNaoConcluidasDoMembroNoProjeto(int idProjeto, int idDono){
		Cursor c = db.query(ContratoTarefas.NOME_TABELA,colunas, 
				ContratoTarefas.Colunas.CONCLUIDA + " = ? and " + ContratoTarefas.Colunas.PROJETO + " = ? and " + ContratoTarefas.Colunas.DONO + " = ?", new String[] {String.valueOf(0),String.valueOf(idProjeto),String.valueOf(idDono)},
				null, null, ContratoTarefas.Colunas.DATA_ENTREGA);

		List<Tarefa> tarefas = new ArrayList<Tarefa>();

		try {
			if (c.moveToFirst()) {
				do {
					Tarefa t = TarefaDAO.getCursor(c);
					tarefas.add(t);
				} while (c.moveToNext());
			}

		} finally {
			c.close();
		}

		return tarefas;
	}
	
    public List<Tarefa> getFazendoDoProjeto(int idProjeto){
			Cursor c = db.query(ContratoTarefas.NOME_TABELA,colunas, 
					ContratoTarefas.Colunas.CONCLUIDA + " = ? and " + ContratoTarefas.Colunas.PROJETO + " = ?", new String[] {String.valueOf(1),String.valueOf(idProjeto)},
					null, null, ContratoTarefas.Colunas.DATA_ENTREGA);

			List<Tarefa> tarefas = new ArrayList<Tarefa>();

			try {
				if (c.moveToFirst()) {
					do {
						Tarefa t = TarefaDAO.getCursor(c);
						tarefas.add(t);
					} while (c.moveToNext());
				}

			} finally {
				c.close();
			}

			return tarefas;
	 }
    
    public List<Tarefa> getFazendoDoMembroNoProjeto(int idProjeto, int idDono){
		Cursor c = db.query(ContratoTarefas.NOME_TABELA,colunas, 
				ContratoTarefas.Colunas.CONCLUIDA + " = ? and " + ContratoTarefas.Colunas.PROJETO + " = ? and " + ContratoTarefas.Colunas.DONO + " = ?", new String[] {String.valueOf(1),String.valueOf(idProjeto),String.valueOf(idDono)},
				null, null, ContratoTarefas.Colunas.DATA_ENTREGA);

		List<Tarefa> tarefas = new ArrayList<Tarefa>();

		try {
			if (c.moveToFirst()) {
				do {
					Tarefa t = TarefaDAO.getCursor(c);
					tarefas.add(t);
				} while (c.moveToNext());
			}

		} finally {
			c.close();
		}

		return tarefas;
 }
	
		public List<Tarefa> getConcluidasDoProjeto(int idProjeto){
			Cursor c = db.query(ContratoTarefas.NOME_TABELA,colunas, 
					ContratoTarefas.Colunas.CONCLUIDA + " = ? and " + ContratoTarefas.Colunas.PROJETO + " = ?", new String[] {String.valueOf(2),String.valueOf(idProjeto)},
					null, null, ContratoTarefas.Colunas.DATA_ENTREGA);

			List<Tarefa> tarefas = new ArrayList<Tarefa>();

			try {
				if (c.moveToFirst()) {
					do {
						Tarefa t = TarefaDAO.getCursor(c);
						tarefas.add(t);
					} while (c.moveToNext());
				}

			} finally {
				c.close();
			}

			return tarefas;
		}		
		
		public List<Tarefa> getConcluidasDoMembroNoProjeto(int idProjeto, int idDono){
			Cursor c = db.query(ContratoTarefas.NOME_TABELA,colunas, 
					ContratoTarefas.Colunas.CONCLUIDA + " = ? and " + ContratoTarefas.Colunas.PROJETO + " = ? and " + ContratoTarefas.Colunas.DONO + " = ?", new String[] {String.valueOf(2),String.valueOf(idProjeto),String.valueOf(idDono)},
					null, null, ContratoTarefas.Colunas.DATA_ENTREGA);

			List<Tarefa> tarefas = new ArrayList<Tarefa>();

			try {
				if (c.moveToFirst()) {
					do {
						Tarefa t = TarefaDAO.getCursor(c);
						tarefas.add(t);
					} while (c.moveToNext());
				}

			} finally {
				c.close();
			}

			return tarefas;
		}
		
	public Tarefa getTarefa (String _id) {
		Cursor c = db.query(ContratoTarefas.NOME_TABELA,colunas, ContratoTarefas.Colunas._ID + " = ? ", new String[]{_id},
				null, null, ContratoTarefas.Colunas.DATA_ENTREGA);
		
		Tarefa t = null; 
		try {
			if (c.moveToFirst()) {
					t = TarefaDAO.getCursor(c);
			}

		} finally {
			c.close();
		}

		return t;
		
	}

	public static Tarefa getCursor(Cursor c) {

		String nome = c.getString(c
				.getColumnIndex(ContratoTarefas.Colunas.NOME));
		String descricao = c.getString(c
				.getColumnIndex(ContratoTarefas.Colunas.DESCRICAO));
		String data = c.getString(c
				.getColumnIndex(ContratoTarefas.Colunas.DATA_ENTREGA));
		int dono = c.getInt((c.getColumnIndex(ContratoTarefas.Colunas.DONO)));
		int prioridade = c.getInt(c
				.getColumnIndex(ContratoTarefas.Colunas.PRIORIDADE));
		int projeto = c.getInt(c.getColumnIndex(ContratoTarefas.Colunas.PROJETO));
		
		int _id = c.getInt(c.getColumnIndex(ContratoTarefas.Colunas._ID));
		
		int concluida = c.getInt(c.getColumnIndex(ContratoTarefas.Colunas.CONCLUIDA));

		return new Tarefa(_id,nome, descricao,dono,data, prioridade, projeto,concluida);
	}

	public void save(Tarefa tarefa) {
		ContentValues values = new ContentValues();
		values.put(ContratoTarefas.Colunas.NOME, tarefa.getNome());
		values.put(ContratoTarefas.Colunas.DATA_ENTREGA,tarefa.getDataEntrega());
		values.put(ContratoTarefas.Colunas.DESCRICAO, tarefa.getDescricao());
		values.put(ContratoTarefas.Colunas.DONO, tarefa.getDono());
		values.put(ContratoTarefas.Colunas.PRIORIDADE, tarefa.getPrioridade());
		values.put(ContratoTarefas.Colunas.PROJETO, tarefa.getIdProjeto());
		values.put(ContratoTarefas.Colunas.CONCLUIDA, tarefa.getConcluida());

		
		Log.d(Tags.TOPTASK_BD, "Proximo passo cadastrar");
		
		long id = db.insert(ContratoTarefas.NOME_TABELA, null, values);
		
		tarefa.setID((int) id);
	}

	public void update(Tarefa tarefa) {
		ContentValues values = new ContentValues();
		values.put(ContratoTarefas.Colunas.NOME, tarefa.getNome());
		values.put(ContratoTarefas.Colunas.DATA_ENTREGA,
				tarefa.getDataEntrega());
		values.put(ContratoTarefas.Colunas.DESCRICAO, tarefa.getDescricao());
		values.put(ContratoTarefas.Colunas.DONO, tarefa.getDono());
		values.put(ContratoTarefas.Colunas.PRIORIDADE, tarefa.getPrioridade());
		values.put(ContratoTarefas.Colunas.PROJETO, tarefa.getIdProjeto());
		values.put(ContratoTarefas.Colunas.CONCLUIDA, tarefa.getConcluida());

		db.update(ContratoTarefas.NOME_TABELA, values,
				ContratoTarefas.Colunas._ID + " = ? ",
				new String[] { String.valueOf(tarefa.getID()) });
	}

	public void delete(Tarefa tarefa) {
	
		db.delete(ContratoTarefas.NOME_TABELA, ContratoTarefas.Colunas._ID
				+ " = ?", new String[] { String.valueOf(tarefa.getID()) });
	}
	
	public void concluirTarefa (Tarefa tarefa) {
		int id = tarefa.getID(); //� Concluida quando 0 e concluida qdo != 0
		
		ContentValues values = new ContentValues();
		values.put(ContratoTarefas.Colunas.CONCLUIDA, 2);
		
		db.update(ContratoTarefas.NOME_TABELA, values, ContratoTarefas.Colunas._ID + " = ? ", new String[] {String.valueOf(id)});
		
	}

}
