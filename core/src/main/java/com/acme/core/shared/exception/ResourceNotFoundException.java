package com.acme.core.shared.exception;

import com.acme.core.shared.utils.I18nUtils;

public class ResourceNotFoundException extends MarvelRankingException {
    public ResourceNotFoundException(String nomeRecurso, Long id) {
        super(String.format(I18nUtils.getMessage(ResourceNotFoundException.class.getSimpleName() + ".message"), nomeRecurso, id));
    }
}
