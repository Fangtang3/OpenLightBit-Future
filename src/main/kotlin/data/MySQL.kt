package am9.openLightBit.data

import java.sql.Connection
import java.sql.DriverManager
import java.sql.Statement
import javax.naming.InitialContext
import javax.sql.DataSource


class MySQL {
    /* Something from dev.mysql.com
    @Throws(Exception::class)
    fun doSomething() {
        /*
         * Create a JNDI Initial context to be able to
         *   lookup  the DataSource
         *
         * In production-level code, this should be cached as
         * an instance or static variable, as it can
         * be quite expensive to create a JNDI context.
         *
         * Note: This code only works when you are using servlets
         * or EJBs in a J2EE application server. If you are
         * using connection pooling in standalone Java code, you
         * will have to create/configure data sources using whatever
         * mechanisms your particular connection pooling library
         * provides.
         */

        val ctx = InitialContext()

        /*
          * Lookup the DataSource, which will be backed by a pool
          * that the application server provides. DataSource instances
          * are also a good candidate for caching as an instance
          * variable, as JNDI lookups can be expensive as well.
          */
        val ds =
            ctx.lookup("java:comp/env/jdbc/MySQLDB") as DataSource

        /*
         * The following code is what would actually be in your
         * Servlet, JSP or EJB 'service' method...where you need
         * to work with a JDBC connection.
         */
        var conn: Connection? = null
        var stmt: Statement? = null

        try {
            conn = ds.connection

            /*
             * Now, use normal JDBC programming to work with
             * MySQL, making sure to close each resource when you're
             * finished with it, which permits the connection pool
             * resources to be recovered as quickly as possible
             */
            stmt = conn.createStatement()
            stmt.execute("SOME SQL QUERY")

            stmt.close()
            stmt = null

            conn.close()
            conn = null
        } finally {
            /*
             * close any jdbc instances here that weren't
             * explicitly closed during normal code path, so
             * that we don't 'leak' resources...
             */

            if (SQL.s != null) {
                SQL.s.close()
                SQL.s = null
            }

            if (SQL.c != null) {
                SQL.c.close()
                SQL.c = null
            }
        }
    }*/
    companion object {
        fun connect() {
            TODO("Not implemented")
        }
    }
}