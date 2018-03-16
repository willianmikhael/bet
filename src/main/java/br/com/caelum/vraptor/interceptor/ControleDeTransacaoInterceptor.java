package br.com.caelum.vraptor.interceptor;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.caelum.vraptor.AroundCall;
import br.com.caelum.vraptor.Intercepts;

@Intercepts
public class ControleDeTransacaoInterceptor {

    private final EntityManager em;

    @Inject
    public ControleDeTransacaoInterceptor(EntityManager em) {
        this.em = em;
    }

    @Deprecated
    ControleDeTransacaoInterceptor() {
        this(null); // para uso do CDI
    }

    @AroundCall
    public void intercept(SimpleInterceptorStack stack) {
        em.getTransaction().begin();
        stack.next();
        em.getTransaction().commit();
    }
}