package example;

import java.util.UUID;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class FooTest 
    extends TestCase
{
    public FooTest( String testName )
    {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite( FooTest.class );
    }

    // This works
    public void testInsertWithValue()
    {
        Foo foo = new Foo();
        foo.setUid(UUID.fromString("44d39817-4064-4027-b291-ac3f899d96d8"));
        tryInsert(foo);
    }

    // This fails
    public void testInsertWithNull()
    {
        Foo foo = new Foo();
        foo.setUid(null);
        tryInsert(foo);
    }

    private void tryInsert(Foo foo) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();
        try {
            em.persist(foo);
            em.flush();
        } finally {
            transaction.rollback();
        }
    }
}
