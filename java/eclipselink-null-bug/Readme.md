### "AttributeConverter + null" bug example.

#### Preparation

Create a PostgreSQL database `foo`, run `init.sql` in it. The database coordinates are hardcoded in `src/main/resources/META-INF/persistence.xml`.

### Running the example

```shell
mvn test
```

### Result

```
[INFO] Scanning for projects...
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] Building eclipselink-null-bug 1.0-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO] 
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ eclipselink-null-bug ---
[WARNING] Using platform encoding (UTF-8 actually) to copy filtered resources, i.e. build is platform dependent!
[INFO] Copying 1 resource
[INFO] 
[INFO] --- maven-compiler-plugin:3.1:compile (default-compile) @ eclipselink-null-bug ---
[INFO] Changes detected - recompiling the module!
[WARNING] File encoding has not been set, using platform encoding UTF-8, i.e. build is platform dependent!
[INFO] Compiling 2 source files to /home/dmitry/work/various-examples/java/eclipselink-null-bug/target/classes
[INFO] 
[INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ eclipselink-null-bug ---
[WARNING] Using platform encoding (UTF-8 actually) to copy filtered resources, i.e. build is platform dependent!
[INFO] skip non existing resourceDirectory /home/dmitry/work/various-examples/java/eclipselink-null-bug/src/test/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.1:testCompile (default-testCompile) @ eclipselink-null-bug ---
[INFO] Nothing to compile - all classes are up to date
[INFO] 
[INFO] --- maven-surefire-plugin:2.12.4:test (default-test) @ eclipselink-null-bug ---
[INFO] Surefire report directory: /home/dmitry/work/various-examples/java/eclipselink-null-bug/target/surefire-reports

-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running example.FooTest
[EL Fine]: server: 2018-08-22 09:50:22.639--Thread(Thread[main,5,main])--Configured server platform: org.eclipse.persistence.platform.server.NoServerPlatform
[EL Config]: metadata: 2018-08-22 09:50:22.711--ServerSession(990398217)--Thread(Thread[main,5,main])--The access type for the persistent class [class example.Foo] is set to [FIELD].
[EL Config]: metadata: 2018-08-22 09:50:22.722--ServerSession(990398217)--Thread(Thread[main,5,main])--The alias name for the entity class [class example.Foo] is being defaulted to: Foo.
[EL Config]: metadata: 2018-08-22 09:50:22.724--ServerSession(990398217)--Thread(Thread[main,5,main])--The table name for entity [class example.Foo] is being defaulted to: FOO.
[EL Config]: metadata: 2018-08-22 09:50:22.735--ServerSession(990398217)--Thread(Thread[main,5,main])--The column name for element [uid] is being defaulted to: UID.
[EL Config]: metadata: 2018-08-22 09:50:22.736--ServerSession(990398217)--Thread(Thread[main,5,main])--The column name for element [id] is being defaulted to: ID.
[EL Info]: 2018-08-22 09:50:22.796--ServerSession(990398217)--Thread(Thread[main,5,main])--EclipseLink, version: Eclipse Persistence Services - 2.7.3.v20180807-4be1041
[EL Fine]: connection: 2018-08-22 09:50:22.85--Thread(Thread[main,5,main])--Detected database platform: org.eclipse.persistence.platform.database.PostgreSQLPlatform
[EL Config]: connection: 2018-08-22 09:50:22.856--ServerSession(990398217)--Connection(143695640)--Thread(Thread[main,5,main])--connecting(DatabaseLogin(
	platform=>PostgreSQLPlatform
	user name=> "portal"
	datasource URL=> "jdbc:postgresql:foo"
))
[EL Config]: connection: 2018-08-22 09:50:22.86--ServerSession(990398217)--Connection(2043318969)--Thread(Thread[main,5,main])--Connected: jdbc:postgresql:foo
	User: portal
	Database: PostgreSQL  Version: 9.6.8
	Driver: PostgreSQL JDBC Driver  Version: 42.2.4
[EL Info]: connection: 2018-08-22 09:50:22.884--ServerSession(990398217)--Thread(Thread[main,5,main])--/file:/home/dmitry/work/various-examples/java/eclipselink-null-bug/target/classes/_default login successful
[EL Fine]: sql: 2018-08-22 09:50:22.904--ClientSession(776700275)--Connection(2043318969)--Thread(Thread[main,5,main])--INSERT INTO FOO (ID, UID) VALUES (?, ?)
	bind => [0, 44d39817-4064-4027-b291-ac3f899d96d8]
[EL Fine]: moxy: 2018-08-22 09:50:22.914--Thread(Thread[main,5,main])--SAXParserFactory instance: com.sun.org.apache.xerces.internal.jaxp.SAXParserFactoryImpl@50a638b5
[EL Fine]: sql: 2018-08-22 09:50:22.917--ClientSession(1354003114)--Connection(2043318969)--Thread(Thread[main,5,main])--INSERT INTO FOO (ID, UID) VALUES (?, ?)
	bind => [0, null]
[EL Fine]: sql: 2018-08-22 09:50:22.92--ClientSession(1354003114)--Thread(Thread[main,5,main])--SELECT 1
[EL Warning]: 2018-08-22 09:50:22.921--UnitOfWork(558216562)--Thread(Thread[main,5,main])--Exception [EclipseLink-4002] (Eclipse Persistence Services - 2.7.3.v20180807-4be1041): org.eclipse.persistence.exceptions.DatabaseException
Internal Exception: org.postgresql.util.PSQLException: ERROR: column "uid" is of type uuid but expression is of type character varying
  Hint: You will need to rewrite or cast the expression.
  Position: 39
Error Code: 0
Call: INSERT INTO FOO (ID, UID) VALUES (?, ?)
	bind => [0, null]
Query: InsertObjectQuery(example.Foo@1817d444)
[EL Config]: connection: 2018-08-22 09:50:22.922--ServerSession(990398217)--Connection(2043318969)--Thread(Thread[main,5,main])--disconnect
Tests run: 2, Failures: 0, Errors: 1, Skipped: 0, Time elapsed: 0.451 sec <<< FAILURE!
testInsertWithNull(example.FooTest)  Time elapsed: 0.013 sec  <<< ERROR!
javax.persistence.PersistenceException: Exception [EclipseLink-4002] (Eclipse Persistence Services - 2.7.3.v20180807-4be1041): org.eclipse.persistence.exceptions.DatabaseException
Internal Exception: org.postgresql.util.PSQLException: ERROR: column "uid" is of type uuid but expression is of type character varying
  Hint: You will need to rewrite or cast the expression.
  Position: 39
Error Code: 0
Call: INSERT INTO FOO (ID, UID) VALUES (?, ?)
	bind => [0, null]
Query: InsertObjectQuery(example.Foo@1817d444)
	at org.eclipse.persistence.internal.jpa.EntityManagerImpl.flush(EntityManagerImpl.java:975)
	at example.FooTest.tryInsert(FooTest.java:49)
	at example.FooTest.testInsertWithNull(FooTest.java:38)
Caused by: Exception [EclipseLink-4002] (Eclipse Persistence Services - 2.7.3.v20180807-4be1041): org.eclipse.persistence.exceptions.DatabaseException
Internal Exception: org.postgresql.util.PSQLException: ERROR: column "uid" is of type uuid but expression is of type character varying
  Hint: You will need to rewrite or cast the expression.
  Position: 39
Error Code: 0
Call: INSERT INTO FOO (ID, UID) VALUES (?, ?)
	bind => [0, null]
Query: InsertObjectQuery(example.Foo@1817d444)
	at org.eclipse.persistence.exceptions.DatabaseException.sqlException(DatabaseException.java:342)
	at org.eclipse.persistence.internal.databaseaccess.DatabaseAccessor.processExceptionForCommError(DatabaseAccessor.java:1650)
	at org.eclipse.persistence.internal.databaseaccess.DatabaseAccessor.executeDirectNoSelect(DatabaseAccessor.java:906)
	at org.eclipse.persistence.internal.databaseaccess.DatabaseAccessor.executeNoSelect(DatabaseAccessor.java:970)
	at org.eclipse.persistence.internal.databaseaccess.DatabaseAccessor.basicExecuteCall(DatabaseAccessor.java:640)
	at org.eclipse.persistence.internal.databaseaccess.DatabaseAccessor.executeCall(DatabaseAccessor.java:567)
	at org.eclipse.persistence.internal.sessions.AbstractSession.basicExecuteCall(AbstractSession.java:2096)
	at org.eclipse.persistence.sessions.server.ClientSession.executeCall(ClientSession.java:311)
	at org.eclipse.persistence.internal.queries.DatasourceCallQueryMechanism.executeCall(DatasourceCallQueryMechanism.java:275)
	at org.eclipse.persistence.internal.queries.DatasourceCallQueryMechanism.executeCall(DatasourceCallQueryMechanism.java:261)
	at org.eclipse.persistence.internal.queries.DatasourceCallQueryMechanism.insertObject(DatasourceCallQueryMechanism.java:411)
	at org.eclipse.persistence.internal.queries.StatementQueryMechanism.insertObject(StatementQueryMechanism.java:167)
	at org.eclipse.persistence.internal.queries.StatementQueryMechanism.insertObject(StatementQueryMechanism.java:182)
	at org.eclipse.persistence.internal.queries.DatabaseQueryMechanism.insertObjectForWrite(DatabaseQueryMechanism.java:504)
	at org.eclipse.persistence.queries.InsertObjectQuery.executeCommit(InsertObjectQuery.java:82)
	at org.eclipse.persistence.queries.InsertObjectQuery.executeCommitWithChangeSet(InsertObjectQuery.java:92)
	at org.eclipse.persistence.internal.queries.DatabaseQueryMechanism.executeWriteWithChangeSet(DatabaseQueryMechanism.java:316)
	at org.eclipse.persistence.queries.WriteObjectQuery.executeDatabaseQuery(WriteObjectQuery.java:60)
	at org.eclipse.persistence.queries.DatabaseQuery.execute(DatabaseQuery.java:914)
	at org.eclipse.persistence.queries.DatabaseQuery.executeInUnitOfWork(DatabaseQuery.java:813)
	at org.eclipse.persistence.queries.ObjectLevelModifyQuery.executeInUnitOfWorkObjectLevelModifyQuery(ObjectLevelModifyQuery.java:110)
	at org.eclipse.persistence.queries.ObjectLevelModifyQuery.executeInUnitOfWork(ObjectLevelModifyQuery.java:87)
	at org.eclipse.persistence.internal.sessions.UnitOfWorkImpl.internalExecuteQuery(UnitOfWorkImpl.java:2981)
	at org.eclipse.persistence.internal.sessions.AbstractSession.executeQuery(AbstractSession.java:1895)
	at org.eclipse.persistence.internal.sessions.AbstractSession.executeQuery(AbstractSession.java:1877)
	at org.eclipse.persistence.internal.sessions.AbstractSession.executeQuery(AbstractSession.java:1827)
	at org.eclipse.persistence.internal.sessions.CommitManager.commitNewObjectsForClassWithChangeSet(CommitManager.java:229)
	at org.eclipse.persistence.internal.sessions.CommitManager.commitAllObjectsWithChangeSet(CommitManager.java:128)
	at org.eclipse.persistence.internal.sessions.AbstractSession.writeAllObjectsWithChangeSet(AbstractSession.java:4387)
	at org.eclipse.persistence.internal.sessions.UnitOfWorkImpl.commitToDatabase(UnitOfWorkImpl.java:1493)
	at org.eclipse.persistence.internal.sessions.UnitOfWorkImpl.commitToDatabaseWithPreBuiltChangeSet(UnitOfWorkImpl.java:1639)
	at org.eclipse.persistence.internal.sessions.RepeatableWriteUnitOfWork.writeChanges(RepeatableWriteUnitOfWork.java:457)
	at org.eclipse.persistence.internal.jpa.EntityManagerImpl.flush(EntityManagerImpl.java:970)
	... 30 more
Caused by: org.postgresql.util.PSQLException: ERROR: column "uid" is of type uuid but expression is of type character varying
  Hint: You will need to rewrite or cast the expression.
  Position: 39
	at org.postgresql.core.v3.QueryExecutorImpl.receiveErrorResponse(QueryExecutorImpl.java:2440)
	at org.postgresql.core.v3.QueryExecutorImpl.processResults(QueryExecutorImpl.java:2183)
	at org.postgresql.core.v3.QueryExecutorImpl.execute(QueryExecutorImpl.java:308)
	at org.postgresql.jdbc.PgStatement.executeInternal(PgStatement.java:441)
	at org.postgresql.jdbc.PgStatement.execute(PgStatement.java:365)
	at org.postgresql.jdbc.PgPreparedStatement.executeWithFlags(PgPreparedStatement.java:150)
	at org.postgresql.jdbc.PgPreparedStatement.executeUpdate(PgPreparedStatement.java:127)
	at org.eclipse.persistence.internal.databaseaccess.DatabaseAccessor.executeDirectNoSelect(DatabaseAccessor.java:898)
	... 60 more


Results :

Tests in error: 
  testInsertWithNull(example.FooTest): Exception [EclipseLink-4002] (Eclipse Persistence Services - 2.7.3.v20180807-4be1041): org.eclipse.persistence.exceptions.DatabaseException(..)

Tests run: 2, Failures: 0, Errors: 1, Skipped: 0

[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 1.510 s
[INFO] Finished at: 2018-08-22T09:50:22+07:00
[INFO] Final Memory: 15M/206M
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-surefire-plugin:2.12.4:test (default-test) on project eclipselink-null-bug: There are test failures.
[ERROR] 
[ERROR] Please refer to /home/dmitry/work/various-examples/java/eclipselink-null-bug/target/surefire-reports for the individual test results.
[ERROR] -> [Help 1]
[ERROR] 
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR] 
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoFailureException
```
