package net.wrobeseb.sqlbuilder.dao;

import java.util.List;

import net.wrobeseb.sqlbuilder.model.NamedQuery;
import net.wrobeseb.sqlbuilder.model.ResultRow;
import net.wrobeseb.sqlbuilder.model.SqlBuilderHistory;

import org.hibernate.SQLQuery;
import org.hibernate.ScrollableResults;
import org.springframework.jdbc.core.RowCallbackHandler;

/**
 * Interfejs DAO odpowiadajacy za dostep do bazy danych dla modulu sqlbuilder
 * 
 * @author wrobese2
 */
public interface ISqlBuilderDao {
	
	/**
	 * Uruchamia wskazane zapytanie na dynamicznej bazie danych,
	 * a nastepnie zwraca dane wiersz po wierszu do wskazanego
	 * w parametrach objektu RowCallbackHandler
	 * 
	 * @param query Uruchamiane zapytanie
	 * @param rch Objekt implementujacy interfejs RowCallbackHendler
	 */
	void executeImportQuery(String query, RowCallbackHandler rch);
	
	/**
	 * Uruchamia wskazane zapytanie na statycznej bazie danych,
	 * a nastepnie zwraca wynik jako kursor
	 * 
	 * @param query Uruchamiane zapytanie
	 * @return Korsor wynikowy
	 */
	ScrollableResults getScrollableResults(SQLQuery query);

	/**
	 * Zapisuje w statycznej bazie danych, tabeli z historia
	 * uruchomienia danego zapytania, ostatnia date uruchomienia
	 * 
	 * @param id Identyfikator zapytania
	 */
	void setLastExecuteDate(Integer id);
	
	List<ResultRow> executeQuery(String query);
	
	List<NamedQuery> getListOfNamedQueriesByUsername(String username);
	
	NamedQuery getNamedQueryById(Integer queryId);
	
	void saveQuery(NamedQuery query);
	
	void updateQuery(NamedQuery query);
	
	void registerHistory(SqlBuilderHistory sqlBuilderHistory);
}
