import java.beans.PropertyVetoException;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import br.com.caelum.JpaConfigurator;

public class TestePoolConnection {

	public static void main(String[] args) throws PropertyVetoException, SQLException {

		ComboPooledDataSource dataSource = (ComboPooledDataSource) new JpaConfigurator().getDataSource();

		for (int i = 1; i < 10; i++) {
			dataSource.getConnection();

			System.out.println(i + " - Conex�es existentes: " + dataSource.getNumConnections());
			System.out.println(i + " - Conex�es ocupadas: " + dataSource.getNumBusyConnections());
			System.out.println(i + " - Conex�es ociosas: " + dataSource.getNumIdleConnections());

			System.out.println("");
		}
	}

}
