package com.acme.core.ranking.exception;

import com.acme.core.shared.exception.MarvelRankingException;
import com.acme.core.shared.utils.I18nUtils;

public class CharacterNotFoundException extends MarvelRankingException {
    public CharacterNotFoundException(Long id) {
        super(String.format(I18nUtils.getMessage(CharacterNotFoundException.class.getSimpleName() + ".message"), id));
    }
}
