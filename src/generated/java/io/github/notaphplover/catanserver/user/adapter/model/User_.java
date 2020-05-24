package io.github.notaphplover.catanserver.user.adapter.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, Long> id;
	public static volatile SingularAttribute<User, String> passwordHash;
	public static volatile SingularAttribute<User, String> username;

	public static final String ID = "id";
	public static final String PASSWORD_HASH = "passwordHash";
	public static final String USERNAME = "username";

}

