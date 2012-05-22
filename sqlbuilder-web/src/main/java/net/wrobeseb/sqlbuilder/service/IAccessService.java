package net.wrobeseb.sqlbuilder.service;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.Table;

/**
 * 
 * Interfejs serwisu odpowiadajacego za tworzenie/dodawanie/modyfikacje danych w pliku *.mdb
 * 
 * @author wrobese2
 */
public interface IAccessService {
	
	/**
	 * Tworzy objekt bazy danych dla wskazanego pliku
	 * 
	 * @param file Plik reprezentujacy baze danych *.mdb
	 * @return Objekt bazy danych
	 * @throws IOException W przypadku problemow z wskazanym plikiem
	 */
	Database createDatabase(File file) throws IOException;
	
	/**
	 * Tworzy tabele w objekcie bazy danych
	 * 
	 * @param db Objekt bazy danych
	 * @param tableName Nazwa tabeli
	 * @param rsMetaData Meta dane zawierajace spis kolumn tabeli
	 * @return Objekt tabeli
	 * @throws SQLException W przypadku problemow zwiazanych z zrodlowa baza danych
	 * @throws IOException W przypadku problemow zwiazanych z zapisem do docelowej bazy danych
	 */
	Table createTable(Database db, String tableName, ResultSetMetaData rsMetaData) throws SQLException, IOException;
	
	/**
	 * Tworzy wiersz w wskazanej tabeli
	 * 
	 * @param table Objekt docelowej tabeli
	 * @param values Wartosci kolejnych kolumn
	 * @throws IOException W przypadku problemow zwiazanych z zapisem do docelowej bazy danych
	 */
	void insertRow(Table table, Object[] values) throws IOException;
}
