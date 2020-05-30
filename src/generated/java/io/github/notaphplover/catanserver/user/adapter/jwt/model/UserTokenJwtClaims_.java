package io.github.notaphplover.catanserver.user.adapter.jwt.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserTokenJwtClaims.class)
public abstract class UserTokenJwtClaims_ {

	public static volatile SingularAttribute<UserTokenJwtClaims, Long> id;
	public static volatile SingularAttribute<UserTokenJwtClaims, String> username;

	public static final String ID = "id";
	public static final String USERNAME = "username";

}

