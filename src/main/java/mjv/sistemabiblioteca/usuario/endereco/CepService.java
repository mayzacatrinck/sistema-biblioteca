package mjv.sistemabiblioteca.usuario.endereco;

import mjv.sistemabiblioteca.exception.CepNaoEncontradoException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CepService {

	private final RestTemplate template = new RestTemplate();

	public EnderecoResponse consultaCep(String cep) {
		EnderecoResponse endereco = template.getForObject("https://viacep.com.br/ws/{cep}/json", EnderecoResponse.class, cep);

		if (StringUtils.isAnyBlank(endereco.getCep(), endereco.getLogradouro())) {
			throw new CepNaoEncontradoException("Cep não encontrado.");
		}
		return endereco;
	}

}
