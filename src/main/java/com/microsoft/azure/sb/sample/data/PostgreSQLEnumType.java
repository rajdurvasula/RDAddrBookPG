package com.microsoft.azure.sb.sample.data;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.EnumType;

public class PostgreSQLEnumType extends EnumType<AddressType> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4815367026366370545L;

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session)
			throws HibernateException, SQLException {
		if (null == value) {
			st.setNull(index, Types.OTHER);
		} else {
			st.setObject(index, value.toString(), Types.OTHER);
		}
	}
}
