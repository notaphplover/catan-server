package io.github.notaphplover.catanserver.user.adapter.db.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserDb.class)
public abstract class UserDb_ {

	public static volatile SingularAttribute<UserDb, Long> id;
	public static volatile SingularAttribute<UserDb, String> passwordHash;
	public static volatile SingularAttribute<UserDb, String> username;

	public static final String ID = "id";
	public static final String PASSWORD_HASH = "passwordHash";
	public static final String USERNAME = "username";

}

