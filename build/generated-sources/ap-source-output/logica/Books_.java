package logica;

import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-17T00:50:40")
@StaticMetamodel(Books.class)
public class Books_ { 

    public static volatile SingularAttribute<Books, Integer> year;
    public static volatile SingularAttribute<Books, String> author;
    public static volatile SingularAttribute<Books, BigInteger> price;
    public static volatile SingularAttribute<Books, String> isbn;
    public static volatile SingularAttribute<Books, String> name;
    public static volatile SingularAttribute<Books, String> publisher;
    public static volatile SingularAttribute<Books, Integer> id;
    public static volatile SingularAttribute<Books, Boolean> state;

}