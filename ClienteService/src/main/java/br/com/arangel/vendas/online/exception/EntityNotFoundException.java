package br.com.arangel.vendas.online.exception;

import br.com.arangel.vendas.online.domain.Cliente;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(Class<Cliente> clienteClass, String... searchParams) {
        super(EntityNotFoundException.generateMessage(clienteClass.getSimpleName(),
                toMap(String.class, String.class, searchParams)));
    }

    private static String generateMessage(String entity, Map<String, String> searchParams) {
        return StringUtils.capitalize(entity) +
                " was not found for parameters " +
                searchParams;
    }

    private static <K, V> Map<K, V> toMap(
            Class<K> keyType, Class<V> valueType, Object... entries) {
        if (entries.length % 2 == 1)
            throw new IllegalArgumentException("Invalid entries");
        return IntStream.range(0, entries.length / 2).map(i -> i * 2)
                .collect(HashMap::new,
                        (m, i) -> m.put(keyType.cast(entries[i]), valueType.cast(entries[i + 1])),
                        Map::putAll);
    }

}
