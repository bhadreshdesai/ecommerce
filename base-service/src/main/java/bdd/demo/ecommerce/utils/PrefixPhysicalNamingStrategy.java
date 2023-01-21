package bdd.demo.ecommerce.utils;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class PrefixPhysicalNamingStrategy extends PhysicalNamingStrategyStandardImpl {

    /**
     * TODO Make this an injectable application property
     */
    public static final String TABLE_NAME_PREFIX = "T_";

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
        Identifier newIdentifier = new Identifier(TABLE_NAME_PREFIX + name.getText().toUpperCase(), name.isQuoted());
        return super.toPhysicalTableName(newIdentifier, context);
    }
}