package generator;

import java.io.Serializable;
import java.sql.*;

import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

public class PronoGenerator implements IdentifierGenerator {

	@Override
	public Serializable generate(SessionImplementor session, Object object) throws HibernateException {

		String str = "%06d";
		String prono = null;
		Connection con = session.connection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT pro_seq.NEXTVAL as nextval FROM DUAL");
			rs.next();
			Integer nextval = rs.getInt("nextval");
			prono = String.format(str, nextval);
			con.close();
		} catch (SQLException e) {
			throw new HibernateException("Unable to generate Sequence");
		}

		return prono;
	}

}
