package br.com.fiap.techchallengeproduct.domain.type;

import io.hypersistence.utils.hibernate.type.ImmutableType;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.EnhancedUserType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.UUID;

@SuppressWarnings("checkstyle:abbreviationaswordinname") // UUID is a known name
public class StringRepresentationUUIDType extends ImmutableType<UUID> implements EnhancedUserType<UUID> {
    private static final long serialVersionUID = 1L;

    public StringRepresentationUUIDType() {
        super(UUID.class);
    }

    @Override
    protected UUID get(ResultSet rs, int index, SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws SQLException {
        String value = rs.getString(index);
        if (value == null) {
            return null;
        } else {
            UUID result = UUID.fromString(value);
            return result;
        }
    }

    @Override
    protected void set(PreparedStatement ps, UUID uuid, int i, SharedSessionContractImplementor sharedSessionContractImplementor) throws SQLException {
        if (uuid == null) {
            ps.setNull(i, Types.VARCHAR);
        } else {
            ps.setString(i, uuid.toString());
        }
    }

    @Override
    public String toSqlLiteral(UUID value) {
        return toString(value);
    }

    @Override
    public String toString(UUID value) throws HibernateException {
        return (value == null) ? null : value.toString();
    }

    @Override
    public UUID fromStringValue(CharSequence sequence) throws HibernateException {
        return (sequence == null) ? null : UUID.fromString(String.valueOf(sequence));
    }

    @Override
    public int getSqlType() {
        return Types.VARCHAR;
    }
}
