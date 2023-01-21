package bdd.demo.ecommerce.utils;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class CustomPhysicalNamingStrategy implements PhysicalNamingStrategy {

   public static final String TABLE_NAME_PREFIX = "t_";

   @Override
   public Identifier toPhysicalCatalogName(final Identifier identifier, final JdbcEnvironment jdbcEnv) {
      return convertToSnakeCase(identifier);
   }

   @Override
   public Identifier toPhysicalColumnName(final Identifier identifier, final JdbcEnvironment jdbcEnv) {
      return convertToSnakeCase(identifier);
   }

   @Override
   public Identifier toPhysicalSchemaName(final Identifier identifier, final JdbcEnvironment jdbcEnv) {
      return convertToSnakeCase(identifier);
   }

   @Override
   public Identifier toPhysicalSequenceName(final Identifier identifier, final JdbcEnvironment jdbcEnv) {
      return convertToSnakeCase(identifier);
   }

   @Override
   public Identifier toPhysicalTableName(final Identifier identifier, final JdbcEnvironment jdbcEnv) {
      return Identifier.toIdentifier(TABLE_NAME_PREFIX + camelToSnake(identifier.getText()));
   }

   // Function to convert camel case
   // string to snake case string
   public static String camelToSnake(String str) {
      // Regular Expression
      String regex = "([a-z])([A-Z]+)";

      // Replacement string
      String replacement = "$1_$2";

      // Replace the given regex
      // with replacement string
      // and convert it to lower case.
      str = str.replaceAll(regex, replacement).toLowerCase();

      // return string
      return str;
   }

   private Identifier convertToSnakeCase(final Identifier identifier) {
      if (identifier == null) return null;
      final String regex = "([a-z])([A-Z])";
      final String replacement = "$1_$2";
      final String newName = identifier.getText()
         .replaceAll(regex, replacement)
         .toLowerCase();
      return Identifier.toIdentifier(newName);
   }
}