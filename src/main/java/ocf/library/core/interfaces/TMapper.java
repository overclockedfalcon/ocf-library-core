package ocf.library.core.interfaces;

import org.springframework.stereotype.Component;

@Component
public interface TMapper <T,U>{
	public U map(T source,U destination);
}
