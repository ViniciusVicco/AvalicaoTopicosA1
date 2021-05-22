package avaliacao.utils;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class Session {

	private static Session session = null;

	private Session() {
	}

	public static Session getInstance() {
		if (session == null)
			session = new Session();
		return session;
	}

	private ExternalContext getExternalContext() {
		if (FacesContext.getCurrentInstance() == null)
			throw new RuntimeException("Voce não está rodando a aplicação no JSF");
		return FacesContext.getCurrentInstance().getExternalContext();
	}

	public void setAtribute(String key, Object value) {
		getExternalContext().getSessionMap().put(key, value);

	}
}
