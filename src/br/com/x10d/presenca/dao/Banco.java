package br.com.x10d.presenca.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import br.com.x10d.presenca.util.MeuAlerta;

public class Banco extends SQLiteOpenHelper {

	public static final String BANCO_NOME = "presenca";
	public static final int BANCO_VERSAO = 6;
	private Context context;

	public Banco(Context _context) {
		super(_context, BANCO_NOME, null, BANCO_VERSAO);
		this.context = _context;
	}

	@Override	
	public void onCreate(SQLiteDatabase sQLiteDatabase) {

		for (String tabela : ListaComTabelasModel.devolveListaComTabelasModel()) {

			try {
				Class<?> classe = Class.forName(Pacote.NOME_PACOTE.getNome()+tabela);

				sQLiteDatabase.execSQL(Query.criaCreateTableComKeyy(classe));
			} catch (Exception erro) {
				new MeuAlerta("Erro onCreate", erro.toString(), context).meuAlertaOk();
			}
		}
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase sQLiteDatabase, int oldVersion, int newVersion) {

		for (String tabela : ListaComTabelasModel.devolveListaComTabelasModel()) {

			try {
				Class<?> classe = Class.forName(Pacote.NOME_PACOTE.getNome()+tabela);

				sQLiteDatabase.execSQL(Query.criaDropTable_final(classe));
			} catch (Exception erro) {
				new MeuAlerta("Erro onUpgrade", erro.toString(), context).meuAlertaOk();
			}
		}

		onCreate(sQLiteDatabase);
	}
}